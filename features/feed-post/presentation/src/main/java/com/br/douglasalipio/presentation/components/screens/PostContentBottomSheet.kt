package com.br.douglasalipio.presentation.components.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.components.states.PostContentBottomSheetState
import com.br.douglasalipio.presentation.components.viewmodels.PostContentBottomSheetViewModel
import com.br.douglasalipio.presentation.databinding.PostContentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class PostContentBottomSheet : BottomSheetDialogFragment() {

    private val viewBiding by viewBinding(PostContentBottomSheetBinding::bind)
    private val viewModel: PostContentBottomSheetViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_content_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllUsernames()
        setUpEvents()
    }

    private fun setUpEvents() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is PostContentBottomSheetState.UsernamesLoadFail -> {}
                is PostContentBottomSheetState.UsernamesLoaded -> setUpAutocompleteUsername(state.usernames)
            }
        })
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
        viewBiding.postContentBottomSheetButton.setOnClickListener { }
    }
}