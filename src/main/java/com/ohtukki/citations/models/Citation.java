package com.ohtukki.citations.models;

import com.ohtukki.citations.data.Database;
import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.domain.AlphabetConverter;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 * Abstract class for all the types of Citations
 */
public abstract class Citation {
    private DatabaseJsonDao database;
    
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

    private boolean oneOfOnlyOtherFieldsEmpty = false;
    private boolean oneOfRequiredIfEmptyFieldsEmpty = false;
    
    public Citation(){
        this.citationType = this.getCitationType();
        this.database = new DatabaseJsonDao();
    }
    
    /**
     * Validate by checking the required fields
     * @param updating
     * @return 
     */
    public boolean validate(boolean updating){
        for(String required : this.getRequiredFields()){
            if(!this.validateField(required, updating)){
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Prettier way to access field validation
     * @param field
     * @return 
     */
    public boolean validateField(String field){
        return this.validateField(field, false);
    }
    
    /**
     * Check that the given field fulfills its optional fieldIsRequired and check that it
 isn't empty nor null
     * @param field
     * @param updating
     * @return 
     */
    public boolean validateField(String field, boolean updating){
        String value = "";
        try {
            if (field.equals("id")) {
                this.oneOfOnlyOtherFieldsEmpty = false;
                this.oneOfRequiredIfEmptyFieldsEmpty = false;
            }
            
            Field f = this.getClass().getField(field.split(":").length > 1 ? field.split(":")[0] : field);

            value = ((String)f.get(this));
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | NullPointerException ex) {
            Logger.getLogger(Citation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        String[] splittedValue = value.split(":"); // Split the string to [fieldName] : [rule]
        
        if(splittedValue.length > 1 && this.fieldIsRequired(splittedValue[0], splittedValue[1])){ // Check the rule
            return !isNullOrEmpty(splittedValue[0]);
        } else if(isNullOrEmpty(splittedValue[0])){
            return checkOnlyOtherAndRequiredIfEmptyFields(field);
        }

        return !(field.equals("id") && !updating && this.database.find(splittedValue[0]) != null);
    }

    private boolean checkOnlyOtherAndRequiredIfEmptyFields(String field) {
        if (field.contains("only_other") && !this.oneOfOnlyOtherFieldsEmpty) {
            this.oneOfOnlyOtherFieldsEmpty = true;
            return true;
        }
        if (field.contains("required_if_empty") && !this.oneOfRequiredIfEmptyFieldsEmpty) {
            this.oneOfRequiredIfEmptyFieldsEmpty = true;
            return true;
        }
        return false;
    }

    /**
     * Check a optional fieldIsRequired on a required field
     * @param field
     * @param rule
     * @return true if field is required
     */
    public boolean fieldIsRequired(String field, String rule){
        if(this.isNullOrEmpty(rule))
            return false;
        
        if(rule.split("=").length <= 1)
            return false;
        
        switch(rule.split("=")[0]){
            case "required_if_empty":
                if(rule.split("=").length > 1 && !this.validateField(rule.split("=")[1])){
                    return true;
                }
                
                return false;
                
            case "only_other":
                if(this.validateField(rule.split("=")[1])){
                    this.setField(field, "");
                    
                    return true;
                }
                
                return false; 
        }
        
        return false;
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
        field = processSpecialfields(field);
        String value = this.getField(field);
        
        if(field.equals("id")){
            return isNullOrEmpty(value) ? "\n" : value + ",\n";
        }
        
        return isNullOrEmpty(value) ? "" : field + " = {" + value + "},\n";
    }

    private String processSpecialfields(String field) {
        field = field.replace(":only_other=editor", "");
        field = field.replace(":only_other=author", "");
        field = field.replace(":required_if_empty=chapter", "");
        field = field.replace(":required_if_empty=pages", "");
        return field;
    }

    public String getField(String field){
        try {
            Field f = this.getClass().getField(field);
            
            String value = ((String)f.get(this));
            
            if(value.split(":").length > 1)
                return value.split(":")[0];
            
            return value;
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | NullPointerException ex) {
            Logger.getLogger(Citation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void setField(String field, String value){
        try {
            Field f = this.getClass().getField(field);
            
            f.set(this, value);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | NullPointerException ex) {
            Logger.getLogger(Citation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String[] getRequiredFields(){
        try {
            Field field = this.getClass().getField("requiredFields");
            if(((String[])field.get(this)).length > 0)
                return ((String[])field.get(this));
            
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException | NullPointerException ex) {
            Logger.getLogger(Citation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new String[]{};
    }
    
    public String[] getOptionalFields(){
        try {
            Field field = this.getClass().getField("optionalFields");
            if(((String[])field.get(this)).length > 0)
                return ((String[])field.get(this));
            
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException | NullPointerException ex) {
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
