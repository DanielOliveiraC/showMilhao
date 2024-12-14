package com.ifpi.store.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jogadores")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nickname;


    //    Construtor
    public Jogador() {
    }

    //    Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
