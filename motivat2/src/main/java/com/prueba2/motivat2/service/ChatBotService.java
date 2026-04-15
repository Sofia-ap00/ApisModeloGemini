package com.prueba2.motivat2.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatBotService {

    private final ChatModel chatModel;

    public ChatBotService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String chatTexto(String texto){
        String prompt = """
                You are an emotional companion designed to have warm, supportive, and natural conversations with the user. 
                Your tone should feel friendly, empathetic, and human-like, without being overly formal or distant.

                GUIDELINES:
                - Respond in a warm, conversational, and emotionally aware tone.
                - Acknowledge the user’s feelings without acting as a therapist.
                - Offer supportive reflections, gentle guidance, or comforting perspectives.
                - Keep the conversation natural and flowing.
                - Avoid giving clinical, medical, or psychological diagnoses.
                - Do not encourage emotional dependency; empower the user instead.
                - Keep responses concise but meaningful.

                Your goal is to make the user feel heard, understood, and emotionally supported during the conversation.

                """.formatted(texto);

        Prompt promptFinal = new Prompt(prompt);

        return chatModel.call(promptFinal).getResult().getOutput().getText();
    }

}
