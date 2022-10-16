package com.br.douglasalipio.presentation.components.feed

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.domain.entities.PostType
import com.br.douglasalipio.presentation.databinding.FeedFragmentItemBinding
import de.hdodenhof.circleimageview.CircleImageView


class FeedRecyclerViewAdapter(
    private val values: List<Post>,
    private val onQuotePostActionClick: (String) -> Unit,
    private val onRepostActionClick: (String) -> Unit
) : RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FeedFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.userNameText.text = item.user.username
        holder.contentView.text = item.content
        val profileImageStream = holder.contentView.resources.assets.open(item.user.imageName)
        val profileImageDrawable = Drawable.createFromStream(profileImageStream, null)
        holder.userImage.background = profileImageDrawable

        holder.feedItemListLayout.setOnLongClickListener {
            onRepostActionClick(item.content)
            true
        }
        holder.feedItemListLayout.setOnClickListener { onQuotePostActionClick(item.content) }
        rePostingFormatText(holder, item)
    }

    private fun rePostingFormatText(holder: ViewHolder, item: Post) {
        when (item.postType) {
            PostType.QUOTE_POST -> {
                holder.userNameText.visibility = View.GONE
                holder.rePostingText.text = "Quote Post"
            }
            PostType.POST -> {
                holder.rePostingText.text = "Posting"
            }
            PostType.RE_POSTING -> {
                holder.rePostingText.text = "Reposting"
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FeedFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val userNameText: TextView = binding.userNameText
        val contentView: TextView = binding.content
        val feedItemListLayout: ConstraintLayout = binding.feedItemListLayout
        val userImage: CircleImageView = binding.userImage
        val rePostingText: TextView = binding.repostingText

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}