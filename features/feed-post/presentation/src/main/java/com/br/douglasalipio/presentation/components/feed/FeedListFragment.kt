package com.br.douglasalipio.presentation.components.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.navigation.fragment.findNavController
import com.br.douglasalipio.domain.entities.Post
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.databinding.PosterFragmentListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedListFragment : Fragment(R.layout.poster_fragment_list) {

    private val viewBinding by viewBinding(PosterFragmentListBinding::bind)
    private val viewModel: FeedViewModel by viewModel()
    private val onRetweetActionClick: (String) -> Unit = this::onRetweetActionClicked

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
                is FeedViewState.Loaded -> showFeedList(state.posts)
            }
        }
    }

    private fun setUpComponents() {
        viewBinding.postContentButton.setOnClickListener { navigateToPostContentBottomSheet() }
    }

    private fun onRetweetActionClicked(value: String) {
        //TODO
    }

    private fun navigateToPostContentBottomSheet() {
        val action = FeedListFragmentDirections.actionPosterListFragmentToPostContentBottomSheet()
        findNavController().navigate(action)
    }

    private fun showFeedList(posts: List<Post>) {
        viewBinding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FeedRecyclerViewAdapter(posts, onRetweetActionClick)
        }
    }
}