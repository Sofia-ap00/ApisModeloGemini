package com.prueba2.motivat2.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

/**
 * @author Sofia Aguilar
 * Clase de servicio encargado de gestionar la conversacion con el chatbot IA.
 */
@Service
public class ChatBotService {

    //Modelo de IA para generar las respuestas
    private final ChatModel chatModel;

    public ChatBotService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * Metodo que enviar un mensaje del usuario a la IA y devuelve una respuesta.
     * @param texto Mensaje a enviar del usuario
     * @return Devuelve la respuesta de la IA
     */
    public String chatTexto(String texto){
        
        //Texto que funciona como prompt que debe seguir la IA
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

                Answer in spanish.

                Now analyze this text:
                %s
                """.formatted(texto);

        Prompt promptFinal = new Prompt(prompt); //Construccion del prompt


        System.out.println(texto);
        System.out.println(chatModel.call(promptFinal).getResult().getOutput().getText());
        
        return chatModel.call(promptFinal).getResult().getOutput().getText(); //Devuelve la respuesta generada por la IA
    }

}
