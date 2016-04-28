package com.ohtukki.citations.data;

import org.springframework.web.bind.annotation.ModelAttribute;

public class ScoreSystem {
    private int score = 0;

    @ModelAttribute("score")
    public int getScore() {
        return score;
    }

    public void addScore(int amount) {
        score += amount;
    }
}
