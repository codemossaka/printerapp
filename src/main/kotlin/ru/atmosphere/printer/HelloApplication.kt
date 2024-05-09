package ru.atmosphere.printer

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.input.KeyCode
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.text.*
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView
import javafx.stage.Stage
import java.util.*

class HelloApplication: Application() {
    override fun start(stage: Stage) {
        stage.apply {
            val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("LanguageSelection.fxml"))
            val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
            title = "Language Selection"
            stage.scene = scene
            stage.show()
        }
        // Create a WebView instance
//        val webView = WebView()
//
//        // Load a web page
//        webView.engine.load("https://www.messagehub.info/fr/messages.do")
//
//
//        // Create buttons for the keyboard
//        val keyboard = GridPane()
//        keyboard.alignment = Pos.CENTER
//
//        val keys = arrayOf(
//            arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "←"),
//            arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L", " ", "↩"),
//            arrayOf("Z", "X", "C", "V", "B", "N", "M", ",", ".", "↵")
//        )
//        val buttonSize = 100.0
//        val specialKeys = arrayOf(
//            arrayOf(
//                KeyCode.Q,
//                KeyCode.W,
//                KeyCode.E,
//                KeyCode.R,
//                KeyCode.T,
//                KeyCode.Y,
//                KeyCode.U,
//                KeyCode.I,
//                KeyCode.O,
//                KeyCode.P,
//                KeyCode.BACK_SPACE
//            ),
//            arrayOf(
//                KeyCode.A,
//                KeyCode.S,
//                KeyCode.D,
//                KeyCode.F,
//                KeyCode.G,
//                KeyCode.H,
//                KeyCode.J,
//                KeyCode.K,
//                KeyCode.L,
//                KeyCode.SPACE,
//                KeyCode.ENTER
//            ),
//            arrayOf(
//                KeyCode.Z,
//                KeyCode.X,
//                KeyCode.C,
//                KeyCode.V,
//                KeyCode.B,
//                KeyCode.N,
//                KeyCode.M,
//                KeyCode.COMMA,
//                KeyCode.PERIOD,
//                KeyCode.ENTER
//            )
//        )
//
//        keys.forEachIndexed { rowIndex, row ->
//            row.forEachIndexed { colIndex, key ->
//                val button = Button(key)
//                val specialKeyCode = specialKeys[rowIndex][colIndex]
//
//                val text = Text(key)
////                text.font = Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 12.0) // Initial font size
//
//                // Bind text size to button size
//                text.wrappingWidthProperty().bind(button.widthProperty())
//
//                button.graphic = text
////                button.style = "-fx-font-size: ${buttonSize / 3}px;" // Adjust this value for text size
//
//                text.textAlignment = TextAlignment.CENTER
//                button.minWidth = buttonSize
//                button.minHeight = buttonSize
//
//                button.alignment = Pos.CENTER // Center the text within the button
//
//                button.setOnAction {
//                    handleKeyPress(webView, key)
//                }
//                button.setOnKeyPressed {
//                    when (specialKeyCode) {
//                        KeyCode.BACK_SPACE -> {
//                            handleKeyPress(webView, " ")
//                        }
//                        KeyCode.DELETE -> {
//                            handleBackspace(webView)
//                        }
//                        KeyCode.ENTER -> {
//                            handleKeyPress(webView, "\n")
//                        }
//                        else -> {
//                            handleKeyPress(webView, it.text.uppercase(Locale.getDefault()))
//                        }
//                    }
//                }
//                keyboard.add(button, colIndex, rowIndex)
//            }
//        }
//
//        // Create a StackPane to hold the WebView and keyboard
//        val root = VBox()
//        root.children.addAll(webView, keyboard)
//
//        // Create the scene
//        val scene = Scene(root, 800.0, 600.0)
//
//        // Set the scene to the stage
//        primaryStage.scene = scene
//
//        // Set the title of the stage
//        primaryStage.title = "Censor Screen"
//        primaryStage.isFullScreen = true
//        // Show the stage
//        primaryStage.show()
    }

    private fun handleKeyPress(webView: WebView, key: String) {
        println("key $key")
        if (key == "←") {
            handleBackspace(webView)
        } else {
            val webEngine: WebEngine = webView.engine
            val jsCode = """
            var input = document.getElementsByName("filterText")[0];
            input.value += '$key';
            var event = new Event('keyup');
            input.dispatchEvent(event);
        """.trimIndent()
            webEngine.executeScript(jsCode)
        }
    }

    private fun handleBackspace(webView: WebView) {
        val webEngine: WebEngine = webView.engine
        val jsCode = """
            var input = document.getElementsByName("filterText")[0];
            input.value = input.value.slice(0, -1);
            var event = new Event('keyup');
            input.dispatchEvent(event);
        """.trimIndent()
        webEngine.executeScript(jsCode)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(HelloApplication::class.java, *args)
        }
    }
}
