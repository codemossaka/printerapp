package ru.atmosphere.printer

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.stage.Stage
import javafx.scene.Scene
import java.awt.Desktop
import java.io.IOException
import java.net.URI
import java.net.URL
import java.util.ResourceBundle

class LanguageSelectionController : Initializable {

    @FXML
    private lateinit var root: VBox

    @FXML
    private lateinit var englishButton: Button

    @FXML
    private lateinit var frenchButton: Button

    @FXML
    private lateinit var russianButton: Button

    override fun initialize(url: URL?, resourceBundle: ResourceBundle?) {
    }

    private fun loadPage(lang: String) {
        try {
            val loader = FXMLLoader(javaClass.getResource("MessageDisplay.fxml"))
            val root: Parent = loader.load()
            val controller = loader.getController<MessageDisplayController>()
            controller.loadMessage("https://www.messagehub.info/$lang/messages.do")

            val stage = Stage()
            val scene = Scene(root)
            stage.scene = scene
            stage.show()

            // Close current window
            val currentWindow = root.scene?.window as? Stage
            currentWindow?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun openWebPage(lang: String) {
        val url = "https://www.messagehub.info/$lang/messages.do"
        try {
            val desktop = Desktop.getDesktop()
            desktop.browse(URI(url))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    @FXML
    private fun loadEnglish() {
        loadPage("en")
    }

    @FXML
    private fun loadFrench() {
        loadPage("fr")
    }

    @FXML
    private fun loadRussian() {
        loadPage("ru")
    }
}
