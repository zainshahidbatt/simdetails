package com.example.simdetails.severs

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simdetails.R
import com.example.simdetails.component.ServerComponent
import com.example.simdetails.databinding.FragmentServerBinding
import com.example.simdetails.url_links.Urls
import com.example.simdetails.utils.colorList

class FragmentServer : Fragment(R.layout.fragment_server) {
    private lateinit var _binding: FragmentServerBinding
    private val binding get() = _binding
    private var urls: Urls? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentServerBinding.bind(view)
        urls = Urls()
        val server1: String = urls!!.server1
        val server2: String = urls!!.server2
        val server3: String = urls!!.server3
        val server4: String = urls!!.server4
        _binding.serverOne.root.setOnClickListener {
            if (checkForInternet()) {
                findNavController().navigate(
                    FragmentServerDirections.actionFragmentServerToFragmentServerDetails(
                        server1
                    )
                )

            } else {
                alertBox()
            }
        }
        _binding.serverTwo.root.setOnClickListener {
            if (checkForInternet()) {
                findNavController().navigate(
                    FragmentServerDirections.actionFragmentServerToFragmentServerDetails(
                        server2
                    )
                )
            } else {
                alertBox()
            }
        }
        _binding.serverThere.root.setOnClickListener {
            if (checkForInternet()) {
                findNavController().navigate(
                    FragmentServerDirections.actionFragmentServerToFragmentServerDetails(
                        server3
                    )
                )
            } else {
                alertBox()
            }
        }
        _binding.serverFour.root.setOnClickListener {
            if (checkForInternet()) {
                findNavController().navigate(
                    FragmentServerDirections.actionFragmentServerToFragmentServerDetails(
                        server4
                    )
                )
            } else {
                alertBox()
            }
        }
        ServerComponent(
            binding = binding.serverOne,
            titleText = "Server 1",
            img = R.drawable.nextpng,
            backgroundColor = colorList(R.color.green)
        )
        ServerComponent(
            binding = binding.serverTwo,
            titleText = "Server 2",
            img = R.drawable.nextpng,
            backgroundColor = colorList(R.color.teal_200)
        )
        ServerComponent(
            binding = binding.serverThere,
            titleText = "Server 3",
            img = R.drawable.nextpng,
            backgroundColor = colorList(androidx.transition.R.color.material_grey_100)
        )
        ServerComponent(
            binding = binding.serverFour,
            titleText = "Server4",
            img = R.drawable.nextpng,
            backgroundColor = colorList(R.color.orange)
        )
    }

    private fun checkForInternet(): Boolean {
        // register activity with the connectivity manager service
        val connectivityManager =
            this.activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false
            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    //Show alert when no internet connection
    private fun alertBox() {
        val builder = AlertDialog.Builder(requireActivity())
        //set title for alert dialog
        builder.setTitle(R.string.dialogTitle)
        //set message for alert dialog
        builder.setMessage(R.string.dialogMessage)
        builder.setIcon(R.drawable.interneterror)

        //performing positive action
        builder.setPositiveButton("OK") { _, _ ->
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}

