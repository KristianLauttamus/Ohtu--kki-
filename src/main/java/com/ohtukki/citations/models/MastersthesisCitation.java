package com.ohtukki.citations.models;

public class MastersthesisCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author", "title", "school", "year"};
    public String[] optionalFields = new String[] {"address", "month", "note", "key"};
}
