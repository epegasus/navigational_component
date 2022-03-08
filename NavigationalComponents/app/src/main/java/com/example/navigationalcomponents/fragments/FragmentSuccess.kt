package com.example.navigationalcomponents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationalcomponents.R
import com.example.navigationalcomponents.databinding.FragmentSuccessBinding
import com.example.navigationalcomponents.utils.Helper

class FragmentSuccess : Fragment() {

    private var binding: FragmentSuccessBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuccessBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnHomeSuccess?.setOnClickListener { onHomeClick() }
    }

    private fun onHomeClick() {
        val navController = findNavController()
        if (Helper.isCurrentDestination(navController, R.id.fragmentSuccess)) {
            navController.navigate(R.id.action_fragmentSuccess_to_fragmentHome)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}