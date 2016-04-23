package com.ohtukki.citations.models;

public class InbookCitation extends Citation {
    public String[] requiredFields = new String[] {"id", "author:only_other=editor", "editor:only_other=author", "title", "chapter:required_if_empty=pages", "pages:required_if_empty=chapter", "publisher", "year"};
    public String[] optionalFields = new String[] {"volume", "series", "address", "edition", "month", "note", "key"};
}
