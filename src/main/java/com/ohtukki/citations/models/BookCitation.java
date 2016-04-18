package com.ohtukki.citations.models;

public class BookCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "editor", "title", "publisher", "year"};
    public String[] optionalFields = new String[] {"volume", "series", "address", "edition", "month", "note", "key"};
}
