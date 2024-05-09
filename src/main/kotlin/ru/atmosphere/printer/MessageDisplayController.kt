package ru.atmosphere.printer

import javafx.concurrent.Worker
import javafx.scene.Parent
import javafx.fxml.FXML
import javafx.scene.web.WebView
import netscape.javascript.JSObject

class MessageDisplayController {

    @FXML
    private lateinit var root: Parent

    @FXML
    private lateinit var webView: WebView

    fun initialize() {
        val webEngine = webView.engine
        webEngine.loadWorker.stateProperty().addListener { _, _, newState ->
            if (newState == Worker.State.SUCCEEDED) {
                val win = webEngine.executeScript("window") as JSObject
                win.setMember("javaApp", this)
            }
        }
    }

    fun loadMessage(url: String) {
        val webEngine = webView.engine
        webEngine.load(url)
    }

    fun downloadStarted() {
        println("Download started!")
    }

    fun downloadCompleted() {
        println("Download completed!")
    }
}

