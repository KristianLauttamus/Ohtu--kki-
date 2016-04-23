package com.ohtukki.citations.models;

public class BookCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author:only_other=editor", "editor:only_other=author", "title", "publisher", "year"};
    public String[] optionalFields = new String[] {"volume", "series", "address", "edition", "month", "note", "key"};
}
