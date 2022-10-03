package com.br.douglasalipio.presentation.components.viewAdapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.br.douglasalipio.domain.entities.Post
import com.br.douglasalipio.presentation.databinding.PosterFragmentItemBinding

class PosterRecyclerViewAdapter(
    private val values: List<Post>,
    private val onRetweetActionClick: (String) -> Unit
) : RecyclerView.Adapter<PosterRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            PosterFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.content
        holder.contentView.setOnClickListener { onRetweetActionClick("") }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PosterFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}