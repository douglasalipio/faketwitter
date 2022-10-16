package com.br.douglasalipio.presentation.components.post

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
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
import com.br.douglasalipio.presentation.utils.SpaceTokenizer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostFragment : BottomSheetDialogFragment() {

    private val viewBiding by viewBinding(TweetFragmentBinding::bind)
    private val viewModel: PostViewModel by viewModel()
    private val args: PostFragmentArgs by navArgs()
    private val threshold = 700

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_content_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllUsernames()
        setUpEvents()
        setUpPostContentButton()
        setUpAutoCompleteContentText()
    }

    private fun setUpEvents() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PostViewState.UsernamesLoadFail -> {}
                is PostViewState.UsernamesLoaded -> setUpAutocompleteTagUserProfile(state.usernames)
            }
        }
    }

    private fun setUpAutoCompleteContentText() {
        viewBiding.postContentAutocompleteText.filters =
            arrayOf(InputFilter.LengthFilter(threshold))
        viewBiding.postContentAutocompleteText.setTokenizer(SpaceTokenizer())
        viewBiding.postContentAutocompleteText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                text: CharSequence?, start: Int, before: Int, count: Int
            ) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                viewBiding.countdownText.text =
                    "${threshold - editable.toString().length} /$threshold"

            }

        })
    }

    private fun setUpAutocompleteTagUserProfile(usernames: List<String>) {
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
        val content = "$post\n----------\n ${args.rePostingContent}"
        if (args.rePostingContent.isEmpty())
            viewModel.requestPostContent(post, false, args.itemPosition)
        else
            viewModel.requestPostContent(content, true, args.itemPosition)
        dismiss()
        navigateBackToPostListScreen()
    }

    private fun navigateBackToPostListScreen() {
        findNavController().navigate(R.id.action_bottom_sheet_dialog_to_feedPostNav)
    }
}