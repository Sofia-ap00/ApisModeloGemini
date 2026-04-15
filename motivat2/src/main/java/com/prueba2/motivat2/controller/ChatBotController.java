package com.prueba2.motivat2.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba2.motivat2.service.ChatBotService;


@RestController
@RequestMapping("/chat")
public class ChatBotController {

    private final ChatBotService chatservice;

    public ChatBotController(ChatBotService chatservice) {
        this.chatservice = chatservice;
    }

   
    @RequestMapping("/enviar")
    public String chatear(@RequestBody String texto){
        return chatservice.chatTexto(texto);
    }

}
