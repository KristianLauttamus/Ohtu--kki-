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
    
    public String getBackground(){
        if(this.score >= 5){
            // Cats pattern
            return "cats.png";
        } else if(this.score >= 10){
            // PHP Elephant
            return "elephant.jpg";
        } else if(this.score >= 50){
            // Matrix falling numbers
            return "matrix.jpg";
        } else if(this.score >= 100){
            // Elon Musk
            return "elon-musk.jpg";
        }
        
        return "";
    }
    
    /**
     * Helper for the container class in css
     * @return class for the styling
     */
    public String getBackgroundClass(){
        if(this.score >= 5)
            return "white-bg";
        
        return "";
    }
}
