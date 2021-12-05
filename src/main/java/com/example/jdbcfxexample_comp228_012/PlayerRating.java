package com.example.jdbcfxexample_comp228_012;

import java.util.Date;

public class PlayerRating {
    Date playing_date;
    Integer score;
    String g_name;

    public PlayerRating(Date playing_date, Integer score, String g_name) {
        this.playing_date = playing_date;
        this.score = score;
        this.g_name = g_name;
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

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }
}
