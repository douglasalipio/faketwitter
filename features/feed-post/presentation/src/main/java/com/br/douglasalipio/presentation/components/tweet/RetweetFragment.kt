package com.br.douglasalipio.presentation.components.tweet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.databinding.RetweetFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RetweetFragment : BottomSheetDialogFragment() {

    private val viewBiding by viewBinding(RetweetFragmentBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.retweet_fragment, container, false)
    }


}