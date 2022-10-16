package com.br.douglasalipio.presentation.components.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration

import com.br.douglasalipio.domain.entities.Post
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.data.local.models.PostModel
import com.br.douglasalipio.domain.entities.PostType
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.components.post.PostViewModel
import com.br.douglasalipio.presentation.databinding.FeedListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedListFragment : Fragment(R.layout.feed_list_fragment) {

    private val viewBinding by viewBinding(FeedListFragmentBinding::bind)
    private val viewModel: FeedViewModel by viewModel()
    private val sharedViewModel: PostViewModel by sharedViewModel()
    private val onQuotePostActionClick: (String) -> Unit = this::onQuotePostActionClicked
    private val onRepostActionClick: (String) -> Unit = this::onPostActionClicked
    private var content: String = ""
    private var postType: PostType = PostType.POST

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEvents()
        viewModel.loadFeedList()
        setUpComponents()
    }

    private fun setUpEvents() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is FeedViewState.FeedListLoaded -> showFeedList(state.tweets)
                is FeedViewState.PostContentAllowed -> {
                    if (PostType.RE_POSTING == postType) {
                        navigateBackToPostListScreen()
                    } else
                        navigateToPostContentBottomSheet()
                }
                is FeedViewState.PostContentNotAllowed -> {
                    Toast.makeText(requireContext(), "Only 5 posts per day.", Toast.LENGTH_SHORT)
                        .show()
                }
                is FeedViewState.Fail -> {}

            }
        }
    }

    private fun setUpComponents() {
        viewBinding.postContentButton.setOnClickListener {
            postType = PostType.POST
            viewModel.loadTotalUserPosts()
        }
    }

    private fun onPostActionClicked(content: String) {
        this.content = content
        this.postType = PostType.RE_POSTING
        viewModel.loadTotalUserPosts()
    }

    private fun onQuotePostActionClicked(content: String) {
        this.content = content
        this.postType = PostType.QUOTE_POST
        viewModel.loadTotalUserPosts()
    }

    private fun navigateBackToPostListScreen() {
        sharedViewModel.requestPostContent(content, postType)
        val action =
            FeedListFragmentDirections.actionPosterFragmentListViewSelf()
        findNavController().navigate(action)
    }

    private fun navigateToPostContentBottomSheet() {
        val action =
            FeedListFragmentDirections.actionPosterListFragmentToPostContentBottomSheet(
                content,
                postType
            )
        findNavController().navigate(action)
    }

    private fun showFeedList(tweets: List<Post>) {
        viewBinding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FeedRecyclerViewAdapter(tweets, onQuotePostActionClick, onRepostActionClick)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}