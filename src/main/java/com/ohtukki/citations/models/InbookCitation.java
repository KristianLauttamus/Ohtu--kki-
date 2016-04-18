package com.ohtukki.citations.models;

public class InbookCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "editor", "title", "chapter", "pages", "publisher", "year"};
    public String[] optionalFields = new String[] {"volume", "series", "address", "edition", "month", "note", "key"};
}
