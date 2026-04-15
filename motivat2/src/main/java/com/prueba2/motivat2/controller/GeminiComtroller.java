package com.prueba2.motivat2.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba2.motivat2.service.GeminiService;


@RestController
@RequestMapping("/notaDiaria")
public class GeminiComtroller {

    private final GeminiService geminiservice;

    public GeminiComtroller(GeminiService geminiservice) {
        this.geminiservice = geminiservice;
    }


    @RequestMapping("/analizar")
    public String analizarTexto(@RequestBody String texto){

        System.out.println("RECIBIDO EN CONTROLADOR: " + texto);
        return geminiservice.analizarTexto(texto);
    }

}
