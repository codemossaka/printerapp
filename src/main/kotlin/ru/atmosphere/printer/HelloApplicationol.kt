//package ru.atmosphere.printer
//
//import javafx.application.Application
//import javafx.geometry.Pos
//import javafx.scene.Scene
//import javafx.scene.control.Button
//import javafx.scene.layout.HBox
//import javafx.scene.layout.VBox
//import javafx.scene.web.WebView
//import javafx.stage.Modality
//import javafx.stage.Stage
//import netscape.javascript.JSObject
//
//class HelloApplicationol : Application() {
//    override fun start(primaryStage: Stage) {
//        // Create a WebView instance
//        val webView = WebView()
//
//        // Create buttons for language selection
//        val langButtons = HBox().apply {
//            alignment = Pos.CENTER
//            spacing = 10.0
//
//            val languages = arrayOf("French", "English", "Russian")
//            for (lang in languages) {
//                val button = Button(lang)
//                button.setOnAction {
//                    selectLanguage(webView, lang)
//                }
//                children.add(button)
//            }
//        }
//
//        // Create a VBox to hold the WebView, language buttons, and keyboard
//        val root = VBox().apply {
//            children.addAll(langButtons, webView)
//        }
//
//        // Create the scene
//        val scene = Scene(root, 800.0, 600.0)
//
//        // Set the scene to the stage
//        primaryStage.scene = scene
//
//        // Set the title of the stage
//        primaryStage.title = "PDF Preview"
//
//        // Show the stage
//        primaryStage.show()
//
//        // Inject JavaScript into the WebView
//        val webEngine = webView.engine
//        webEngine.loadWorker.stateProperty().addListener { _, _, newState ->
//            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {
//                val doc = webEngine.executeScript("document")
//                val tableRows = (doc as JSObject).call("querySelectorAll", "tr.dataRow")
//
//                // Add click event listener to each table row
//                for (i in 0 until (tableRows as JSObject).getMember("length") as Int) {
//                    val row = tableRows.getSlot(i) as JSObject
//                    row.call("addEventListener", "click", { _: Any? ->
//                        // Get the PDF URL
//                        val pdfUrl = row.call("getAttribute", "onclick").toString().replace("downloadMessage('", "").replace("')", "")
//
//                        // Show PDF in modal dialog
//                        showPdfInModalDialog(pdfUrl)
//                    })
//                }
//            }
//        }
//    }
//
//    private fun selectLanguage(webView: WebView, language: String) {
//        val pdfUrl = when (language) {
//            "French" -> "https://www.messagehub.info/fr/messages.do"
//            "English" -> "https://www.messagehub.info/en/messages.do"
//            "Russian" -> "https://www.messagehub.info/ru/messages.do"
//            else -> ""
//        }
//        webView.engine.load(pdfUrl)
//    }
//
//    private fun showPdfInModalDialog(pdfUrl: String) {
//        // Create a new stage for the modal dialog
//        val dialogStage = Stage()
//        dialogStage.initModality(Modality.APPLICATION_MODAL)
//        dialogStage.title = "PDF Preview"
//        dialogStage.width = 800.0
//        dialogStage.height = 600.0
//
//        // Create a WebView for the PDF
//        val webView = WebView()
//        val webEngine = webView.engine
//        webEngine.load(pdfUrl)
//
//        // Add the WebView to the scene
//        val scene = Scene(webView)
//        dialogStage.scene = scene
//
//        // Show the modal dialog
//        dialogStage.show()
//    }
//
//
//    companion object {
//        @JvmStatic
//        fun main(args: Array<String>) {
//            launch(HelloApplicationol::class.java, *args)
//        }
//    }
//}
