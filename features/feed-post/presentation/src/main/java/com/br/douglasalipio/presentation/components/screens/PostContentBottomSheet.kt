package com.br.douglasalipio.presentation.components.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.databinding.PostContentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PostContentBottomSheet : BottomSheetDialogFragment() {

    private val viewBiding by viewBinding(PostContentBottomSheetBinding::bind)
    //private val args: PostContentDial by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun setUpPostContentButton() {
        viewBiding.postContentBottomSheetButton.setOnClickListener {  }
    }
}