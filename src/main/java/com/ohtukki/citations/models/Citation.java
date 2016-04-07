package com.ohtukki.citations.models;

/**
 * Abstract class for all the types of Citations
 */
public abstract class Citation {
    private String id;
    private String author;
    private String name;
    
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
