package com.example.navigationalcomponents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.navigationalcomponents.R
import com.example.navigationalcomponents.databinding.FragmentHomeBinding
import com.example.navigationalcomponents.utils.Helper

class FragmentHome : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var navController: NavController? = null

    private fun initializations() {
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializations()


        binding?.apply {
            btnPlayHome.setOnClickListener { onPlayClick() }
            btnScoreHome.setOnClickListener { onScoreClick() }
        }
    }

    private fun onPlayClick() {
        if (Helper.isCurrentDestination(navController!!, R.id.fragmentHome)) {
            navController?.navigate(R.id.action_fragmentHome_to_fragmentProfile)
        }
    }

    private fun onScoreClick() {
        if (Helper.isCurrentDestination(navController!!, R.id.fragmentHome)) {
            navController?.navigate(R.id.action_fragmentHome_to_fragmentScore)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}