package com.example.navigationalcomponents.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.navigationalcomponents.databinding.FragmentNotificationBinding

class FragmentNotification : Fragment() {

    private var binding: FragmentNotificationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillList()
    }

    private fun fillList() {
        val notificationList = mutableListOf<String>()
        for (i in 1..10) {
            notificationList.add("Notification # $i")
        }

        view?.context?.let {
            val arrayAdapter =
                ArrayAdapter(it, android.R.layout.simple_list_item_1, notificationList)
            binding?.lvNotificationsNotification?.adapter = arrayAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}