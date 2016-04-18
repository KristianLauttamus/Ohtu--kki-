package com.ohtukki.citations.models;

public class UnpublishedCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "title", "note"};
    public String[] optionalFields = new String[] {"month", "year", "key"};
}
