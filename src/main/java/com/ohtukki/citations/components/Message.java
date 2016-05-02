package com.ohtukki.citations.components;

public class Message {
    private String title;
    private String message;
    private String type;
    
    public Message(String title, String message){
        this.title = title;
        this.message = message;
        this.type = "default";
    }
    public Message(String title, String message, String type){
        this.title = title;
        this.message = message;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
