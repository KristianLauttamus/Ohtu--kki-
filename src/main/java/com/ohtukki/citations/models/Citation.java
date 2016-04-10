package com.ohtukki.citations.models;

/**
 * Abstract class for all the types of Citations
 */
public abstract class Citation {
    protected String id = new String();
    protected String author = new String();
    protected String name = new String();
    protected String journal = new String();
    protected String year = new String();
    protected String volume = new String();
    protected String number = new String();
    protected String pages = new String();
    protected String month = new String();
    protected String note = new String();
    protected String key = new String();
    
    // Setters and Getters
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
    
    // Methods to create bibtex format
    public String idToBibtex() {
        return isNullOrEmpty(id) ? "\n" : id + ",\n";
    }
    
    public String authorToBibtex() {
        return isNullOrEmpty(author) ? "" : "author = {" + author + "},\n";
    }
    
    public String titleToBibtex() {
        return isNullOrEmpty(name) ? "" : "title = {" + name + "},\n";
    }
    
    public String journalToBibtex() {
        return isNullOrEmpty(journal) ? "" : "title = {" + journal + "},\n";
    }
    
    public String yearToBibtex() {
        return isNullOrEmpty(year) ? "" : "title = {" + year + "},\n";
    }
    
    public String volumeToBibtex() {
        return isNullOrEmpty(volume) ? "" : "title = {" + volume + "},\n";
    }
    
    public String numberToBibtex() {
        return isNullOrEmpty(number) ? "" : "title = {" + number + "},\n";
    }
    
    public String pagesToBibtex() {
        return isNullOrEmpty(pages) ? "" : "title = {" + pages + "},\n";
    }
    
    public String monthToBibtex() {
        return isNullOrEmpty(month) ? "" : "title = {" + month + "},\n";
    }
    
    public String noteToBibtex() {
        return isNullOrEmpty(note) ? "" : "title = {" + note + "},\n";
    }
    
    public String keyToBibtex() {
        return isNullOrEmpty(key) ? "" : "title = {" + key + "},\n";
    }
    
    private boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
