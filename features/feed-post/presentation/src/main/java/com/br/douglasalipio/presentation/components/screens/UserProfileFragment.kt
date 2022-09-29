package com.br.douglasalipio.presentation.components.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.br.douglasalipio.presentation.R
import com.br.douglasalipio.presentation.databinding.FragmentUserProfileBinding
import com.br.douglasalipio.presentation.databinding.PosterFragmentListBinding

class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

    private val viewBinding by viewBinding(FragmentUserProfileBinding::bind)

}