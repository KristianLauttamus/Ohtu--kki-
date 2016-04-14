package com.ohtukki.citations.models;

/**
 * Abstract class for all the types of Citations
 */
public abstract class Citation {
    // Fields for article entry
    protected String id = new String();
    protected String author = new String();
    protected String name = new String(); // = title in bibtex
    protected String journal = new String();
    protected String year = new String();
    protected String volume = new String();
    protected String number = new String();
    protected String pages = new String();
    protected String month = new String();
    protected String note = new String();
    protected String key = new String();
    
    // Fields for book entry (+ some above)
    protected String editor = new String();
    protected String publisher = new String();
    protected String series = new String();
    protected String address = new String();
    protected String edition = new String();
    
    // Fields for inproceedings entry (+ some above)
    protected String booktitle = new String();
    protected String organization = new String();
    
    // Setters and getters
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

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    
    // Helper methods to create bibtex format
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
        return isNullOrEmpty(journal) ? "" : "journal = {" + journal + "},\n";
    }
    
    public String yearToBibtex() {
        return isNullOrEmpty(year) ? "" : "year = {" + year + "},\n";
    }
    
    public String volumeToBibtex() {
        return isNullOrEmpty(volume) ? "" : "volume = {" + volume + "},\n";
    }
    
    public String numberToBibtex() {
        return isNullOrEmpty(number) ? "" : "number = {" + number + "},\n";
    }
    
    public String pagesToBibtex() {
        return isNullOrEmpty(pages) ? "" : "pages = {" + pages + "},\n";
    }
    
    public String monthToBibtex() {
        return isNullOrEmpty(month) ? "" : "month = {" + month + "},\n";
    }
    
    public String noteToBibtex() {
        return isNullOrEmpty(note) ? "" : "note = {" + note + "},\n";
    }
    
    public String keyToBibtex() {
        return isNullOrEmpty(key) ? "" : "key = {" + key + "},\n";
    }
    
    
    public String editorToBibtex() {
        return isNullOrEmpty(editor) ? "" : "editor = {" + editor + "},\n";
    }
    
    public String publisherToBibtex() {
        return isNullOrEmpty(publisher) ? "" : "publisher = {" + publisher + "},\n";
    }
    
    public String seriesToBibtex() {
        return isNullOrEmpty(series) ? "" : "series = {" + series + "},\n";
    }
    
    public String addressToBibtex() {
        return isNullOrEmpty(address) ? "" : "address = {" + address + "},\n";
    }
    
    public String editionToBibtex() {
        return isNullOrEmpty(edition) ? "" : "edition = {" + edition + "},\n";
    }
    
    public String booktitleToBibtex() {
        return isNullOrEmpty(booktitle) ? "" : "booktitle = {" + booktitle + "},\n";
    }
    
    public String organizationToBibtex() {
        return isNullOrEmpty(organization) ? "" : "organization = {" + organization + "},\n";
    }
    
    private boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
