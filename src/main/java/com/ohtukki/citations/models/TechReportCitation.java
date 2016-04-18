package com.ohtukki.citations.models;

public class TechReportCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "title", "institution", "year"};
    public String[] optionalFields = new String[] {"type", "number", "address", "month", "note", "key"};
}
