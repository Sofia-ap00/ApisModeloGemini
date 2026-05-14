package com.prueba2.motivat2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.*;
import org.springframework.ai.chat.prompt.Prompt;

import com.prueba2.motivat2.service.ChatBotService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Samara Garcia
 * Clase de test que verifica que el servicio llama correctamente a la API
 * y funciona la logica de respuesta.
 */
public class ChatBotServiceTest {

    private ChatModel chatModel;
    private ChatBotService chatBotService;

    @BeforeEach
    void setUp() {
        chatModel = mock(ChatModel.class);
        chatBotService = new ChatBotService(chatModel);
    }

    @Test
    void testChatTexto_ReturnsExpectedResponse() {

        String input = "Hoy me siento triste."; //Texto de entrada
        String outputEsperado = "Siento que estés pasando por un momento difícil."; //Respuesta esperada

        //Mock del mensaje generado por la IA
        AssistantMessage assistantMessage = mock(AssistantMessage.class);
        when(assistantMessage.getText()).thenReturn(outputEsperado);

        //Mock de la generacion
        Generation generation = mock(Generation.class);
        when(generation.getOutput()).thenReturn(assistantMessage);

        //Mock de la respuesta completa del modelo
        ChatResponse response = mock(ChatResponse.class);
        when(response.getResult()).thenReturn(generation);

        //Simulamos que cuando el modelo reciba el prompt, devolvera la respuesta
        when(chatModel.call(any(Prompt.class))).thenReturn(response);

        String result = chatBotService.chatTexto(input); //Ejecucion de metodo real

        assertEquals(outputEsperado, result); //Comparamos

        //Capturamos el prompt enviado al modelo
        ArgumentCaptor<Prompt> captor = ArgumentCaptor.forClass(Prompt.class);
        verify(chatModel, times(2)).call(captor.capture());

        //Obtenemos y capturamos el contenido del prompt
        Prompt promptSent = captor.getValue();
        String promptText = promptSent.getContents();

        assert(promptText.contains(input)); //Validamos
    }
}
