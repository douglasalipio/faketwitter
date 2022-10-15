package com.br.douglasalipio.presentation.components.feed

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.douglasalipio.domain.entities.Tweet
import com.br.douglasalipio.presentation.databinding.FeedFragmentItemBinding
import de.hdodenhof.circleimageview.CircleImageView


class FeedRecyclerViewAdapter(
    private val values: List<Tweet>,
    private val onRetweetActionClick: (String) -> Unit
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
        val resId: Int =
            holder.contentView.resources.getIdentifier(
                item.user.imageName,
                "drawable",
                holder.contentView.context.packageName
            )


        val stream = holder.contentView.resources.assets.open(item.user.imageName)
        val d = Drawable.createFromStream(stream, null)
        holder.userImage.background = d
        holder.contentView.setOnClickListener { onRetweetActionClick("") }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FeedFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val userNameText: TextView = binding.userNameText
        val contentView: TextView = binding.content
        val userImage: CircleImageView = binding.userImage

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}