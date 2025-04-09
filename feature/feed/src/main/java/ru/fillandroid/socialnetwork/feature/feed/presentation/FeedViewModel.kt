package ru.fillandroid.socialnetwork.feature.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList = _postList.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launchIO(
            onAction = {
                _postList.value = getPostsUseCase()
            },
            onError = {
                it.message?.let { message ->
                    _eventFlow.emit(UiEvent.ShowToast(message))
                }
            }
        )
    }

    fun likeImage(post: Post, position: Int) {
        viewModelScope.launchIO(
            onAction = {
                likeImageUseCase(post)
                val list = _postList.value as ArrayList
                list[position] = post
                _postList.value = list
            },
            onError = {
                it.message?.let { message ->
                    _eventFlow.emit(UiEvent.ShowToast(message))
                }
            }
        )
    }

    fun commentPost(post: Post, position: Int) {
        viewModelScope.launchIO(
            onAction = {
                commentPostUseCase(post)
                val list = _postList.value as ArrayList
                list[position] = post
                _postList.value = list
            },
            onError = {
                it.message?.let { message ->
                    _eventFlow.emit(UiEvent.ShowToast(message))
                }
            }
        )
    }

    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
    }
}