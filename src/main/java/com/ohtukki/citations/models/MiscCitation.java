package com.ohtukki.citations.models;

public class MiscCitation extends Citation {
    public String[] requiredFields = new String[] {"id"};
    public String[] optionalFields = new String[] {"author", "title", "howpublished", "month", "year", "note", "key"};
}
