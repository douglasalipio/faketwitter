package com.br.douglasalipio.presentation.components.tweet

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.presentation.R

import com.br.douglasalipio.presentation.databinding.TweetFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TweetFragment : BottomSheetDialogFragment() {

    private val viewBiding by viewBinding(TweetFragmentBinding::bind)
    private val viewModel: TwitteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            usernames
        )
        viewBiding.postContentAutocompleteText.setAdapter(adapter)
    }

    private fun setUpPostContentButton() {
        viewBiding.postContentBottomSheetButton.setOnClickListener { postContent() }
        viewBiding.postContentAutocompleteText.setOnKeyListener { view, i, keyEvent ->
            if (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                postContent()
            }
            true
        }
    }

    private fun postContent() {
        viewModel.requestPostContent(
            viewBiding.postContentAutocompleteText.text.toString()
        )
        dismiss()
        navigateBackToPostListScreen()
    }

    private fun navigateBackToPostListScreen() {
        findNavController().navigate(TweetFragmentDirections.actionBottomSheetDialogToFeedPostNav())
    }
}