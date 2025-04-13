package ru.fillandroid.socialnetwork.feature.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.fillandroid.socialnetwork.common.extensions.launchIO
import ru.fillandroid.socialnetwork.domain.model.Post
import ru.fillandroid.socialnetwork.domain.use_case.feed.CommentPostUseCase
import ru.fillandroid.socialnetwork.domain.use_case.feed.GetPostsUseCase
import ru.fillandroid.socialnetwork.domain.use_case.feed.LikeImageUseCase

class FeedViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val commentPostUseCase: CommentPostUseCase,
    private val likeImageUseCase: LikeImageUseCase
) : ViewModel() {

    private val likeJobs = mutableMapOf<String, Job>()
    private val commentJobs = mutableMapOf<String, Job>()

    private val _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList = _postList.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val mutex = Mutex()

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launchIO(
            onAction = {
                val posts = getPostsUseCase()
                _postList.value = posts
            },
            onError = {
                it.message?.let { message ->
                    _eventFlow.emit(UiEvent.ShowToast(message))
                }
            }
        )
    }

    fun likeImage(post: Post, position: Int) {
        val existedJob = likeJobs[post.id]
        if (existedJob != null) return
        likeJobs[post.id] = viewModelScope.launchIO(
            onAction = {
                likeImageUseCase(post)
                mutex.withLock {
                    val list = _postList.value.toMutableList()
                    list[position] = post
                    _postList.value = list
                }
            },
            onError = {
                it.message?.let { message ->
                    _eventFlow.emit(UiEvent.ShowToast(message))
                }
            }
        ).apply {
            invokeOnCompletion {
                likeJobs.remove(post.id)
            }
        }
    }

    fun commentPost(post: Post, position: Int) {
        val existedJob = commentJobs[post.id]
        if (existedJob != null) return
        commentJobs[post.id] = viewModelScope.launchIO(
            onAction = {
                commentPostUseCase(post)
                mutex.withLock {
                    val list = _postList.value.toMutableList()
                    list[position] = post
                    _postList.value = list
                }
            },
            onError = {
                it.message?.let { message ->
                    _eventFlow.emit(UiEvent.ShowToast(message))
                }
            }
        ).apply {
            invokeOnCompletion {
                commentJobs.remove(post.id)
            }
        }
    }

    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
    }
}