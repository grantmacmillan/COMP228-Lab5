package com.example.jdbcfxexample_comp228_012;

import java.util.Date;

public class PlayerAndGame {
    Integer p_g_id;
    Integer g_id;
    Integer p_id;
    Date playing_date;
    Integer score;

    public PlayerAndGame(Integer p_g_id, Integer g_id, Integer p_id, Date playing_date, Integer score) {
        this.p_g_id = p_g_id;
        this.g_id = g_id;
        this.p_id = p_id;
        this.playing_date = playing_date;
        this.score = score;
    }

    public Integer getP_g_id() {
        return p_g_id;
    }

    public void setP_g_id(Integer p_g_id) {
        this.p_g_id = p_g_id;
    }

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Date getPlaying_date() {
        return playing_date;
    }

    public void setPlaying_date(Date playing_date) {
        this.playing_date = playing_date;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}


