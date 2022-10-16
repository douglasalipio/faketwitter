package com.br.douglasalipio.presentation.components.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.navigation.fragment.findNavController

import com.br.douglasalipio.domain.entities.Tweet
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.databinding.PosterFragmentListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedListFragment : Fragment(R.layout.feed_list_fragment) {

    private val viewBinding by viewBinding(PosterFragmentListBinding::bind)
    private val viewModel: FeedViewModel by viewModel()
    private val onRetweetActionClick: (String, Int) -> Unit = this::onRetweetActionClicked

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEvents()
        viewModel.loadFeedList()
        setUpComponents()
    }

    private fun setUpEvents() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is FeedViewState.LoadFail -> {}
                is FeedViewState.Loaded -> showFeedList(state.tweets)
            }
        }
    }

    private fun setUpComponents() {
        viewBinding.postContentButton.setOnClickListener { navigateToPostContentBottomSheet() }
    }

    private fun onRetweetActionClicked(content: String, itemPosition: Int) {

        navigateToPostContentBottomSheet(content, itemPosition)
    }

    private fun navigateToPostContentBottomSheet(value: String = "", itemPosition: Int = 0) {
        val action =
            FeedListFragmentDirections.actionPosterListFragmentToPostContentBottomSheet(
                value,
                itemPosition
            )
        findNavController().navigate(action)
    }

    private fun showFeedList(tweets: List<Tweet>) {
        viewBinding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FeedRecyclerViewAdapter(tweets, onRetweetActionClick)
        }
    }
}