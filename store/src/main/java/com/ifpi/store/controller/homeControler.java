package com.ifpi.store.controller;

import com.ifpi.store.model.Jogador;
import com.ifpi.store.service.JogadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class homeControler {

    private final JogadorService jogadorService;

    public homeControler(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("jogador", new Jogador());
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(@ModelAttribute Jogador jogador) {
        // Aqui estamos simplesmente salvando o jogador no banco de dados
        jogadorService.cadastrarJogador(jogador);
        return "redirect:/iniciar-jogo";
    }
}
