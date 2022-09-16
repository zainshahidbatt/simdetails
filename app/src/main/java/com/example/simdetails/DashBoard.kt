package com.example.simdetails

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simdetails.component.CardComponent
import com.example.simdetails.databinding.FragmentDashBoardBinding
import com.example.simdetails.url_links.Urls
import com.example.simdetails.utils.colorList
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus

class DashBoard : Fragment(R.layout.fragment_dash_board) {
    private lateinit var _binding: FragmentDashBoardBinding
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDashBoardBinding.bind(view)
        val urls = Urls()
        val locationInfo = urls.locationInfo
        val cincInfo = urls.cincInfo
        val navController = this.findNavController()

        //navigating to web pages
        _binding.serverOne.root.setOnClickListener {
            navController.navigate(R.id.action_fragment_dash_board_to_fragmentServer, null)
        }
        _binding.locationInfo.root.setOnClickListener {
            if (checkForInternet()) {
                navController.navigate(
                    DashBoardDirections.actionFragmentDashBoardToFragmentServerDetails(
                        locationInfo
                    )
                )
            } else {
                alertBox()
            }
        }
        _binding.contactInfo.root.setOnClickListener {
            navController.navigate(DashBoardDirections.actionFragmentDashBoardToFragmentContactInfo())
        }
        _binding.cincInfo.root.setOnClickListener {
            if (checkForInternet()) {
                navController.navigate(
                    DashBoardDirections.actionFragmentDashBoardToFragmentServerDetails(
                        cincInfo
                    )
                )

            } else {
                alertBox()
            }
        }
        _binding.disclaimer.root.setOnClickListener {
            navController.navigate(DashBoardDirections.actionFragmentDashBoardToFragmentDiscalimer())
        }
        //giving cards its view
        CardComponent(
            binding = binding.serverOne,
            titleText = "SIM Details",
            img = R.drawable.simpng,
            backgroundColor = colorList(R.color.orange)
        )
        CardComponent(
            binding = binding.locationInfo,
            titleText = "Location Info",
            img = R.drawable.locationpng,
            backgroundColor = colorList(R.color.pink)
        )
        CardComponent(
            binding = binding.disclaimer,
            titleText = "Disclaimer",
            img = R.drawable.disclaimerpng,
            backgroundColor = colorList(R.color.brown)
        )
        CardComponent(
            binding = binding.cincInfo,
            titleText = "CINC Info",
            img = R.drawable.cincpng,
            backgroundColor = colorList(R.color.green)
        )
        CardComponent(
            binding = binding.contactInfo,
            titleText = "Contact Us",
            img = R.drawable.contactpng,
            backgroundColor = colorList(R.color.bluish)
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
//        MobileAds.initialize(requireActivity()) {}
//        val adRequest = AdRequest.Builder().build()
//        _binding.adView1.loadAd(adRequest)
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
