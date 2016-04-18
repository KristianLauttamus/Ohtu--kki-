package com.ohtukki.citations.models;

public class ArticleCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "title", "journal", "year"};
    public String[] optionalFields = new String[] {"volume", "number", "pages", "month", "note", "key"};
}
