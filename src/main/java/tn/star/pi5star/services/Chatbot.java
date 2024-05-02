package tn.star.pi5star.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Chatbot {

    private Map<String, String> responses;

    public Chatbot(String questionsFile, String answersFile) {
        responses = loadResponses(questionsFile, answersFile);
    }

    private Map<String, String> loadResponses(String questionsFile, String answersFile) {
        Map<String, String> data = new HashMap<>();
        try (BufferedReader questionsReader = new BufferedReader(new FileReader(questionsFile));
             BufferedReader answersReader = new BufferedReader(new FileReader(answersFile))) {

            String question;
            String answer;
            while ((question = questionsReader.readLine()) != null && (answer = answersReader.readLine()) != null) {
                data.put(question.toLowerCase(), answer);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return data;
    }

    public String generateResponse(String userQuery) {
        String response = responses.get(userQuery.toLowerCase());
        return response != null ? response : "Désolé, je n'ai pas compris votre requête.";
    }
}