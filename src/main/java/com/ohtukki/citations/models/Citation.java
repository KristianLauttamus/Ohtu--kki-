package com.ohtukki.citations.models;

import com.ohtukki.citations.domain.AlphabetConverter;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * Abstract class for all the types of Citations
 */
public abstract class Citation {
    // Fields for every entry
    public String id = new String();
    public String citationType = new String();
    
    // Other fields in alphabetic order
    public String address = new String();
    public String author = new String();
    public String booktitle = new String();
    public String chapter = new String();
    public String edition = new String();
    public String editor = new String();
    public String howpublished = new String();
    public String institution = new String();
    public String journal = new String();
    public String key = new String();
    public String month = new String();
    public String note = new String();
    public String number = new String();
    public String organization = new String();
    public String pages = new String();
    public String publisher = new String();
    public String school = new String();
    public String series = new String();
    public String title = new String();
    public String type = new String();
    public String volume = new String();
    public String year = new String();
    
    public Citation(){
        this.citationType = this.getCitationType();
    }
    
    /**
     * Validate by checking the required fields
     * @return 
     */
    public boolean validate(){
        for(String required : this.getRequiredFields()){
            String value = "";
            try {
                Field f = this.getClass().getField(required);

                value = ((String)f.get(this));
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(Citation.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

            if(isNullOrEmpty(value)){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Creates Bibtex entry from the fields given
     * @return 
     */
    public String createBibtexEntry() {
        String entry = "@" + StringUtils.upperCase(getCitationType()) + "{";
        
        for (String requiredField : this.getRequiredFields()) {
            entry += toBibtex(requiredField);
        }
        for (String optionalField : this.getOptionalFields()) {
            entry += toBibtex(optionalField);
        }
        
        entry += "}\n\n";

        return new AlphabetConverter().umlautsToBibtex(entry);
    }
    
    /**
     * Get citation type by removing word "Citation" from the class name
     * @return 
     */
    public String getCitationType(){
        String[] split = this.getClass().getName().split("\\.");
        String value = split[split.length-1];
        int index = value.indexOf("Citation");
        
        return value.substring(0, index);
    }
    
    /**
     * Convert field to bibtex format
     * @param field
     * @return 
     */
    public String toBibtex(String field) {
        String value = "";
        try {
            Field f = this.getClass().getField(field);
            
            value = ((String)f.get(this));
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(Citation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(field.equals("id")){
            return isNullOrEmpty(value) ? "\n" : value + ",\n";
        }
        
        return isNullOrEmpty(value) ? "" : field + " = {" + value + "},\n";
    }
    
    public String[] getRequiredFields(){
        try {
            Field field = this.getClass().getField("requiredFields");
            if(((String[])field.get(this)).length > 0)
                return ((String[])field.get(this));
            
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(Citation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new String[]{};
    }
    
    public String[] getOptionalFields(){
        try {
            Field field = this.getClass().getField("optionalFields");
            if(((String[])field.get(this)).length > 0)
                return ((String[])field.get(this));
            
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(Citation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new String[]{};
    }
    
    /**
     * Check that value is not null or empty
     * @param value
     * @return 
     */
    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
    
    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getHowpublished() {
        return howpublished;
    }

    public void setHowpublished(String howpublished) {
        this.howpublished = howpublished;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
}
