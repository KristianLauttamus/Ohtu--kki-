package com.ohtukki.citations.components;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User {
    private int score = 0;

    public int getScore() {
        return score;
    }

    public void addScore(int amount) {
        score += amount;
    }
}
