package com.ohtukki.citations.models;

public class ProceedingsCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "title", "year"};
    public String[] optionalFields = new String[] {"editor", "publisher", "organization", "address", "month", "note", "key"};
}
