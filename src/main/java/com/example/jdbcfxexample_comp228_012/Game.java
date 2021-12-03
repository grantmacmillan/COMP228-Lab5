package com.example.jdbcfxexample_comp228_012;

public class Game {
    Integer g_id;
    String g_title;

    public Game(Integer g_id, String g_title) {
        this.g_id = g_id;
        this.g_title = g_title;
    }

    public Integer getG_id() {
        return g_id;
    }

    public String getG_title() {
        return g_title;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public void setG_title(String g_title) {
        this.g_title = g_title;
    }
}
