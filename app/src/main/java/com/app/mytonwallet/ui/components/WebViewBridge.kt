package com.app.mytonwallet.ui.components

import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebView.setWebContentsDebuggingEnabled
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.app.mytonwallet.services.NativeStorage
import com.app.mytonwallet.web.WebAppInterface

/**
 * My idea was about fully connect js project with android app,
 * Unfortunately, there a lot of problems with js need to be solved,
 * I spent more than 3 full-time days to solve all problems, build custom
 * library using webpack, but anyway it's very difficult to use it without
 * persistent storage - in case of using DOM models, all our data will dropped when
 * the WebView will be destroyed, so we need to implement our own storage in Android,
 * Another problem here is that evaluateJavascript() function doesn't support
 * JS promises, so when we try to call it, usually we get empty object, means
 * that Promise still creating... So I built observer in Android to handle results of
 * promises, but it was not enough for correct work...
 */
@Composable
fun WebViewPage(url: String, activity: (WebView) -> Unit,
                jsInterface: WebAppInterface,
                nativeStorage: NativeStorage
) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        view?.evaluateJavascript("javascript:initAPI()") {
                            Log.d("WebViewPage", "Called JS method to initialise API")
                        }
                    }
                }
                webChromeClient = WebChromeClient()
                settings.javaScriptEnabled = true
                settings.setAllowFileAccessFromFileURLs(true)
                addJavascriptInterface(jsInterface, "Android")
//                addJavascriptInterface(nativeStorage, "AndroidStorage")
                activity(this)
                settings.domStorageEnabled = true
                settings.databaseEnabled = true
                settings.allowFileAccess = true
                settings.allowContentAccess = true
                settings.cacheMode = WebSettings.LOAD_DEFAULT
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                settings.userAgentString = WebSettings.getDefaultUserAgent(context)
                settings.mediaPlaybackRequiresUserGesture = false
                settings.userAgentString = "Mozilla/5.0 (Linux; Android 10; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.99 Mobile Safari/537.36"
                setWebContentsDebuggingEnabled(true)
                settings.setSupportMultipleWindows(true)
                loadUrl(url)
            }
        },
        modifier = Modifier.size(0.dp)
    )
}