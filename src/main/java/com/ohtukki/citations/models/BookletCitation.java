package com.ohtukki.citations.models;

public class BookletCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "title"};
    public String[] optionalFields = new String[] {"author", "howpublished", "address", "month", "year", "note", "key"};
}
