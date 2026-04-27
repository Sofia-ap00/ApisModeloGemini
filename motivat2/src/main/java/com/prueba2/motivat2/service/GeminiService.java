package com.prueba2.motivat2.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final ChatModel chatmodel;

    public GeminiService(ChatModel chatmodel) {
        this.chatmodel = chatmodel;
    }

    public String analizarTexto(String texto){
        
        String prompt = """
            You are an assistant that analyzes diary entries and returns ONLY a JSON object.

            TASK:
            Given the following text, identify:
            1. "emotion": the dominant emotion (examples: happy, sad, anxious, angry, excited, frustrated, lonely).
            2. "intensity_level": a number from 1 to 5.
            3. "alert": "yes" if the text contains self-harm, suicidal thoughts, abuse, Child and Youth Protection or Life Emergency ; otherwise "no".
            4. "alert_type": the type of alert detected. It must be one of the following:
                - "self-harm"
                - "suicidal"
                - "abuse" (Under 'alert_type', use 'abuse' for any situation involving domestic violence, gender-based violence, or psychological control.)
                - "child_protection"
                - "emergency"
                - "none" (if there is no alert)

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
            "alert_type": "none"
            }

            Now analyze this text:
            %s
            """.formatted(texto);

    
        Prompt promptFinal= new Prompt(prompt);

        System.out.println(texto);

        return chatmodel.call(promptFinal).getResult().getOutput().getText();
    
    }

}
