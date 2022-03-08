package com.example.navigationalcomponents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationalcomponents.R
import com.example.navigationalcomponents.databinding.FragmentTestBinding
import com.example.navigationalcomponents.utils.Helper

class FragmentTest : Fragment() {

    private var binding: FragmentTestBinding? = null
    private val args: FragmentTestArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI()

        binding?.apply {
            btnYesTest.setOnClickListener { onButtonClick(true) }
            btnNoTest.setOnClickListener { onButtonClick(false) }
        }
    }

    private fun setUI() {
        with(args.player) {
            binding?.apply {
                tvUsernameTest.text = username
                tvAgeTest.text = age.toString()
            }
        }
    }

    private fun onButtonClick(buttonType: Boolean) {
        val navController = findNavController()
        if (Helper.isCurrentDestination(navController, R.id.fragmentTest)) {
            buttonType.let {
                if (it)
                    navController.navigate(R.id.action_fragmentTest_to_fragmentSuccess)
                else
                    navController.navigate(R.id.action_fragmentTest_to_fragmentFailure)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}