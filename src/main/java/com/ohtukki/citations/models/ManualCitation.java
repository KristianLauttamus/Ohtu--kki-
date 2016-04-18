package com.ohtukki.citations.models;

public class ManualCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "title"};
    public String[] optionalFields = new String[] {"author", "organization", "address", "edition", "month", "year", "note", "key"};
}
