package com.prueba2.motivat2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.*;
import org.springframework.ai.chat.prompt.Prompt;

import com.prueba2.motivat2.service.GeminiService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Samara Garcia 
 * Clase de test que comprueba la correcta construccion del prompt enviado y logica de respuesta.
 */
public class GeminiServiceTest {

    private ChatModel chatModel;
    private GeminiService geminiService;

    @BeforeEach
    void setUp() {
        chatModel = mock(ChatModel.class);
        geminiService = new GeminiService(chatModel);
    }

    @Test
    void testAnalizarTexto_ReturnsJsonResponse() {

        String input = "Hoy no tengo ganas de nada.";
        String jsonEsperado = """
                {
                  "emotion": "sad",
                  "intensity_level": 3,
                  "alert": "no",
                  "alert_type": "none"
                }
                """;

        //Mock del mensaje generado por la IA
        AssistantMessage assistantMessage = mock(AssistantMessage.class);
        when(assistantMessage.getText()).thenReturn(jsonEsperado);

        //Mock de la generacion del mensaje
        Generation generation = mock(Generation.class);
        when(generation.getOutput()).thenReturn(assistantMessage);

        //Mock de la respuesta completa del modelo
        ChatResponse response = mock(ChatResponse.class);
        when(response.getResult()).thenReturn(generation);

        //Cuando el modelo reciba el prompt, devolvera la respuesta
        when(chatModel.call(any(Prompt.class))).thenReturn(response);

        //Ejecutamos el metodo del servicio real
        String result = geminiService.analizarTexto(input);

        assertEquals(jsonEsperado, result); //Comparamos

        //Capturamos el prompt enviado enviado al modelo
        ArgumentCaptor<Prompt> captor = ArgumentCaptor.forClass(Prompt.class);
        verify(chatModel, times(2)).call(captor.capture());

        //Obtenemos y extraemos el contenido del ultimo prompt
        Prompt promptSent = captor.getValue();
        String promptText = promptSent.getContents();

        //Comparamos y validamos
        assert(promptText.contains(input));
        assert(promptText.contains("Respond ONLY with valid JSON"));
    }
}
