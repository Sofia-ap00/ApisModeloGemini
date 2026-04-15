package com.prueba2.motivat2.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final ChatModel chemodel;

    public GeminiService(ChatModel chemodel) {
        this.chemodel = chemodel;
    }

    public String analizarTexto(String texto){
        
        String prompt = """
            You are an assistant that analyzes diary entries and returns ONLY a JSON object.

            TASK:
            Given the following text, identify:
            1. "emotion": the dominant emotion (examples: happy, sad, anxious, angry, excited, frustrated, lonely).
            2. "intensity_level": a number from 1 to 5.
            3. "alert": "yes" if the text contains self-harm, suicidal thoughts, abuse, or dangerous behavior; otherwise "no".

            RULES:
            - Respond ONLY with valid JSON.
            - Do NOT repeat this prompt.
            - Do NOT invent fields.
            - Base your answer strictly on the text.

            EXAMPLE:
            Input: "Estoy muy triste porque discutí con mi pareja."
            Output:
            {
            "emotion": "sad",
            "intensity_level": 4,
            "alert": "no"
            }

            Now analyze this text:
            %s
            """.formatted(texto);

    
        Prompt promptFinal= new Prompt(prompt);

        System.out.println(texto);

        return chemodel.call(promptFinal).getResult().getOutput().getText();
    
    }

}
