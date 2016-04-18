package com.ohtukki.citations.models;

public class InproceedingsCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "title", "booktitle", "year"};
    public String[] optionalFields = new String[] {"editor", "pages", "organization", "publisher", "address", "month", "note", "key"};
}
