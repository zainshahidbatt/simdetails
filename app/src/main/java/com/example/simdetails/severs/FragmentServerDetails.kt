@file:Suppress("DEPRECATION")

package com.example.simdetails.severs

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.simdetails.R
import com.example.simdetails.databinding.FragmentServerDetailsBinding


class FragmentServerDetails : Fragment(R.layout.fragment_server_details) {
    private lateinit var _binding: FragmentServerDetailsBinding

    lateinit var progress: ProgressDialog
    private val args: FragmentServerDetailsArgs by navArgs()
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentServerDetailsBinding.bind(view)
        _binding.apply {

            webView.webViewClient = WebViewClient()
            val url: String = args.url
            webView.loadUrl(url)
            webView.settings.javaScriptEnabled = true
            webView.settings.builtInZoomControls = false
            webView.settings.javaScriptCanOpenWindowsAutomatically = true
            webView.settings.domStorageEnabled = true
            webView.settings.setSupportZoom(true)
            progress = ProgressDialog.show(
                activity, "Please Wait a Moment",
                "Loading...", true
            )
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    progress.dismiss()
                }
            }
        }
    }

}
