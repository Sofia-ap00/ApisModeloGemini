package com.prueba2.motivat2;

import com.prueba2.motivat2.controller.ChatBotController;
import com.prueba2.motivat2.service.ChatBotService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Samara Garcia
 * Clase de prueba del controlador del chat de IA
 * que verifica el correcto funcionamiento de chateo.
 */
class ChatBotControllerTest {

    //Mock del servicio
    @Mock
    private ChatBotService chatBotService;

    //El controller donde se inyecta el mock
    @InjectMocks
    private ChatBotController chatBotController;

    @BeforeEach
    void setup() {
        //Inicializa las anotaciones de mockito
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testChatear() {
        //Entrada simulada
        String textoEntrada = "Hola IA";
        String respuestaMock = "Respuesta generada";

        //Define comportamiento del mock
        when(chatBotService.chatTexto(textoEntrada)).thenReturn(respuestaMock);

        //Ejecutar metodo del controller
        String resultado = chatBotController.chatear(textoEntrada);

        //Valida resultado
        assertEquals(respuestaMock, resultado);

        //Verifica que el servicio fue llamado correctamente
        verify(chatBotService, times(1)).chatTexto(textoEntrada);
    }
}
