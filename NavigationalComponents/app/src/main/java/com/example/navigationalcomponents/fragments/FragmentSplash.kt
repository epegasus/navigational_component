package com.example.navigationalcomponents.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationalcomponents.R
import com.example.navigationalcomponents.databinding.FragmentSplashBinding
import com.example.navigationalcomponents.utils.Helper

class FragmentSplash : Fragment() {

    private var binding: FragmentSplashBinding? = null
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    private fun initializations() {
        handler = Handler(Looper.getMainLooper())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializations()
    }

    override fun onResume() {
        super.onResume()
        setHandler()
    }

    private fun setHandler() {
        runnable = Runnable {
            val navController = findNavController()
            if (Helper.isCurrentDestination(navController, R.id.fragmentSplash)) {
                navController.navigate(R.id.action_fragmentSplash_to_fragmentHome)
            }
        }
        handler?.postDelayed(runnable!!, 2000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        runnable?.let { handler?.removeCallbacks(it) }
    }
}