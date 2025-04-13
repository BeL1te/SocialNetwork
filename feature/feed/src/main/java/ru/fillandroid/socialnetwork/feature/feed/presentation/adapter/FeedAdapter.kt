package ru.fillandroid.socialnetwork.feature.feed.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import ru.fillandroid.socialnetwork.common.extensions.show
import ru.fillandroid.socialnetwork.common.extensions.toggleVisibility
import ru.fillandroid.socialnetwork.domain.model.Post
import ru.fillandroid.socialnetwork.feature.feed.R

class FeedAdapter(
    private val context: Context,
    private val onLikeClicked: (Post, Int) -> Unit,
    private val onSendComment: (Post, Int) -> Unit
): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    private var items: List<Post> = emptyList()

    fun setData(data: List<Post>) {
        items = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val firstImage: ImageView = view.findViewById(R.id.ivFirst)
        val secondImage: ImageView = view.findViewById(R.id.ivSecond)
        val description: TextView = view.findViewById(R.id.tvDescription)
        val likeFirst: ImageButton = view.findViewById(R.id.ibLikeFirst)
        val likeSecond: ImageButton = view.findViewById(R.id.ibLikeSecond)
        val comment: TextView = view.findViewById(R.id.tvComment)
        val inputComment: TextInputEditText = view.findViewById(R.id.etComment)
        val sendComment: ImageButton = view.findViewById(R.id.ibSendComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        Glide.with(context)
            .load(item.firstImage.url)
            .centerCrop()
            .into(holder.firstImage)

        Glide.with(context)
            .load(item.secondImage.url)
            .centerCrop()
            .into(holder.secondImage)

        if (item.haveLike()) {
            holder.likeFirst.toggleVisibility(item.firstImage.isLiked)
            holder.likeSecond.toggleVisibility(item.secondImage.isLiked)
        } else {
            holder.likeFirst.run {
                show()
                setOnClickListener {
                    val image = item.firstImage
                    onLikeClicked(item.copy(firstImage = image.copy(isLiked = true)), position)
                }
            }
            holder.likeSecond.run {
                show()
                setOnClickListener {
                    val image = item.secondImage
                    onLikeClicked(item.copy(secondImage = image.copy(isLiked = true)), position)
                }
            }
        }
        holder.description.text = item.description

        holder.comment.run {
            toggleVisibility(!item.comment.isNullOrEmpty())
            text = item.comment
        }


        holder.inputComment.toggleVisibility(item.comment.isNullOrEmpty())
        holder.sendComment.run {
            toggleVisibility(item.comment.isNullOrEmpty())
            setOnClickListener {
                val inputComment = holder.inputComment.text
                if (!inputComment.isNullOrEmpty()) {
                    onSendComment(item.copy(comment = inputComment.toString()), position)
                }
            }
        }
    }
}