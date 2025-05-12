package com.example.cataliftnativetask

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class PrivacyPolicyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webView = WebView(this)
        setContentView(webView)

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        // Replace with actual link or your own GitHub README or dummy policy
        webView.loadUrl("https://www.termsfeed.com/live/5f962f1f-46ed-41d4-89b3-e00022f3f929")
    }
}
