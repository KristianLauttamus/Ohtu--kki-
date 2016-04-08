package com.ohtukki.citations.models;

public class ArticleCitation extends Citation {
    // temp. test to see that the citation fields get filled
    @Override
    public String toString(){
        return "id: " + this.getId() + " author: " + this.getAuthor() + " name: " + this.getName() +
                " journal: " + this.getJournal() + " year: " + this.getYear() + " volume: " + this.getVolume() +
                " number: " + this.getNumber() + " pages: " + this.getPages() + " month: " + this.getMonth() +
                " note: " + this.getMonth() + " key: " + this.getKey();
    }

}
