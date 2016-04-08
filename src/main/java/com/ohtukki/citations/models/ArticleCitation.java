package com.ohtukki.citations.models;

public class ArticleCitation extends Citation {
    // Required fields
    private String journal;
    private String year;

    // Optional fields
    private String volume;
    private String number;
    private String pages;
    private String month;
    private String note;
    private String key;

    // temp. test to see that the citation fields get filled
    @Override
    public String toString(){
        return "id: " + this.getId() + " author: " + this.getAuthor() + " name: " + this.getName();
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
