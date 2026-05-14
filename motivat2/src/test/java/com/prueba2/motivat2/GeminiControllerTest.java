package com.prueba2.motivat2;

import com.prueba2.motivat2.service.GeminiService;
import com.prueba2.motivat2.controller.GeminiController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Samara Garcia 
 * Clase de test que comprueba el correcto funcionamiento del controlador y devuelve
 * la respuesta esperada.
 */
class GeminiControllerTest {

    @Mock
    private GeminiService geminiService; //Servicio mockeado

    @InjectMocks
    private GeminiController geminiController; //Controlador donde se inyecta el servicio

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); //Inicializa las anotaciones
    }

    @Test
    void testAnalizarTexto() {
        
        //Textos de entrada y salida simulados
        String textoEntrada = "Hoy me siento mal";
        String respuestaMock = "ANALISIS_OK";

        //Simulamos
        when(geminiService.analizarTexto(textoEntrada)).thenReturn(respuestaMock);

        String resultado = geminiController.analizarTexto(textoEntrada); //Llamada real

        assertEquals(respuestaMock, resultado); //Comparamos

        verify(geminiService, times(1)).analizarTexto(textoEntrada); //Verificamos llamada
    }
}
