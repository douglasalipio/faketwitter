package com.br.douglasalipio.presentation.components.tweet

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.presentation.R

import com.br.douglasalipio.presentation.databinding.TweetFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TweetFragment : BottomSheetDialogFragment() {

    private val viewBiding by viewBinding(TweetFragmentBinding::bind)
    private val viewModel: TweetViewModel by viewModel()
    private val args: TweetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tweet_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllUsernames()
        setUpEvents()
        setUpPostContentButton()
    }

    private fun setUpEvents() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is TweetViewState.UsernamesLoadFail -> {}
                is TweetViewState.UsernamesLoaded -> setUpAutocompleteUsername(state.usernames)
            }
        }
    }

    private fun setUpAutocompleteUsername(usernames: List<String>) {
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_dropdown_item_1line, usernames
        )
        viewBiding.postContentAutocompleteText.setAdapter(adapter)
    }

    private fun setUpPostContentButton() {
        viewBiding.postContentBottomSheetButton.setOnClickListener { postContent() }
        viewBiding.postContentAutocompleteText.setOnKeyListener { _, _, keyEvent ->
            if (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                postContent()
            }
            true
        }
    }

    private fun postContent() {
        val post = viewBiding.postContentAutocompleteText.text.toString()
        val content = "$post \n\n--------------\n\n ${args.retweetContent}"
        viewModel.requestPostContent(content)
        dismiss()
        navigateBackToPostListScreen()
    }

    private fun navigateBackToPostListScreen() {
        val retweetContent = Bundle().apply {
            putString(
                RETWEET_CONTENT_KEY, viewBiding.postContentAutocompleteText.toString()
            )
        }
        findNavController().navigate(R.id.action_bottom_sheet_dialog_to_feedPostNav, retweetContent)
    }

    companion object {
        const val RETWEET_CONTENT_KEY = "retweet_content_bundle"
    }
}