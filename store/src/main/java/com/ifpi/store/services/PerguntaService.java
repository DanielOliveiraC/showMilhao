package com.ifpi.store.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifpi.store.model.Pergunta;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PerguntaService {

    public List<Pergunta> carregarPerguntas() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Pergunta> perguntas = null;
        try {
            File file = new File(getClass().getClassLoader().getResource("perguntas.json").getFile());
            perguntas = objectMapper.readValue(file, new TypeReference<List<Pergunta>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return perguntas;
    }
}
