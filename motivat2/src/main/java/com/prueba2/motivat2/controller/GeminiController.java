package com.prueba2.motivat2.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba2.motivat2.service.GeminiService;

/**
 * @author Sofia Aguilar
 * Controlador encargado de analizar las notas diarias.
 * Incluye un enpoint de envio que devuelve un json con informacion del analisis.
 */
@RestController
@RequestMapping("/notaDiaria")
public class GeminiController {

    private final GeminiService geminiservice;

    public GeminiController(GeminiService geminiservice) {
        this.geminiservice = geminiservice;
    }

    // ---------------- ENDPOINT: ANALIZAR ----------------
    @RequestMapping("/analizar")
    public String analizarTexto(@RequestBody String texto){

        System.out.println("RECIBIDO EN CONTROLADOR: " + texto);
        return geminiservice.analizarTexto(texto);
    }

}
