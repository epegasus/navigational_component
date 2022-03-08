package com.example.navigationalcomponents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationalcomponents.CHANNEL_ID
import com.example.navigationalcomponents.R
import com.example.navigationalcomponents.databinding.FragmentProfileBinding
import com.example.navigationalcomponents.models.Player
import com.example.navigationalcomponents.utils.Helper

class FragmentProfile : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private var navController: NavController? = null
    private val args: FragmentProfileArgs by navArgs()

    private fun initializations() {
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializations()
        setUI()

        binding?.btnSaveProfile?.setOnClickListener { onSaveClick() }
    }

    private fun setUI() {
        binding?.etUsernameProfile?.setText(args.username)
    }

    private fun onSaveClick() {
        val player = getValues()
        createExplicitDeepLink(player)
        if (Helper.isCurrentDestination((navController!!), R.id.fragmentProfile)) {
            val action = FragmentProfileDirections.actionFragmentProfileToFragmentTest(player)
            navController?.navigate(action)
        }
    }

    private fun createExplicitDeepLink(player: Player) {
        val pendingIntent = findNavController()
            .createDeepLink()
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.fragmentTest)
            .setArguments(FragmentTestArgs(player).toBundle())
            .createPendingIntent()

        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Complete Test?")
            .setContentText("${player.username}, you haven't completed your quiz yet.")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(requireContext()).notify(1002, notification)
    }

    private fun getValues(): Player {
        return Player().apply {
            binding?.apply {
                username = etUsernameProfile.text.toString().trim()
                age = (etAgeProfile.text.toString().trim()).toInt()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}