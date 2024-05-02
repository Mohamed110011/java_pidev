package tn.star.pi5star.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import tn.star.pi5star.services.Chatbot;

import java.io.File;

public class ChatbotController {

    @FXML
    private VBox root;

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField userInputField;

    private Chatbot chatbot;

    public ChatbotController() {
        chatbot = new Chatbot("C:\\Users\\mohamed\\Desktop\\Pi5star\\src\\main\\resources\\autism_data.txt", "C:\\Users\\mohamed\\Desktop\\Pi5star\\src\\main\\resources\\reponse.txt");
    }

    @FXML
    private void initialize() {
        // Remplacez les chemins des fichiers selon votre configuration
       // chatbot.reloadResponses("C:\\Users\\mohamed\\Desktop\\Pi5star\\src\\main\\resources\\autism_data.txt", "C:\\Users\\mohamed\\Desktop\\Pi5star\\src\\main\\resources\\reponse.txt");
    }

    @FXML
    private void handleUserInput() {
        String userQuery = userInputField.getText();
        String botResponse = chatbot.generateResponse(userQuery);
        displayUserQuery(userQuery);
        displayBotResponse(botResponse);
        clearUserInput();
    }

    private void displayUserQuery(String query) {
        chatArea.appendText("User: " + query + "\n");
    }

    private void displayBotResponse(String response) {
        chatArea.appendText("Bot: " + response + "\n");
    }

    private void clearUserInput() {
        userInputField.clear();
    }
}