package com.ohtukki.citations.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ohtukki.citations.models.*;
import io.gsonfire.GsonFireBuilder;
import org.apache.commons.io.FileUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseJsonDao implements Database {
    public static String DEFAULT_FILE = "citations.json";
    
    private String filename;
    private List<Citation> citations;
    private final boolean validations;
    
    public DatabaseJsonDao() {
        this.filename = DEFAULT_FILE;
        this.citations = new ArrayList<>();
        this.validations = true;
    }
    
    public DatabaseJsonDao(boolean validations) {
        this.filename = DEFAULT_FILE;
        this.citations = new ArrayList<>();
        this.validations = validations;
    }
    
    public DatabaseJsonDao(String filename) {
        this.filename = filename;
        this.citations = new ArrayList<>();
        this.validations = true;
    }
    
    @Override
    public List<Citation> all() {
        GsonFireBuilder builder = new GsonFireBuilder().registerTypeSelector(Citation.class, new CitationCreator());
        Gson gson = builder.createGson();
        String data = null;
        try {
            data = FileUtils.readFileToString(new File(filename));
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(DatabaseJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.citations = gson.fromJson(data, new TypeToken<ArrayList<Citation>>(){}.getType());
        
        return this.citations;
    }
    
    public List<Citation> allByPredicate(Predicate<Citation> predicate) {
        Iterable<Citation> c = Iterables.filter(citations,	predicate);
        List<Citation> targetCollection = new ArrayList<>();
        for (Iterator iterator = c.iterator(); iterator.hasNext();) {
            Citation citation = (Citation) iterator.next();
            targetCollection.add(citation);
        }
        
        return targetCollection;
    }
    
    @Override
    public Citation find(String id) {
        for(int i = 0; i < this.all().size(); i++){
            if(this.citations.get(i).id.equals(id)){
                return this.citations.get(i);
            }
        }
        
        return null;
    }
    
    @Override
    public void save(Citation citation) {
        this.add(citation);
        this.saveAll();
    }
    
    @Override
    public void add(Citation citation) {
        if(!this.validations || citation.validate(false))
            citations.add(citation);
    }
    
    @Override
    public void update(String id, Citation citation){
        Citation oldCitation = this.find(id);
        
        for(String req : oldCitation.getRequiredFields()){
            oldCitation.setField(req, citation.getField(req));
        }
        
        if(oldCitation.validate(true)){
            for(String opt : oldCitation.getOptionalFields()){
                oldCitation.setField(opt, citation.getField(opt));
            }
            
            for(int i = 0; i < this.all().size(); i++){
                if(this.citations.get(i).id.equals(id)){
                    this.citations.set(i, oldCitation);
                    this.saveAll();
                    break;
                }
            }
        }
    }
    
    @Override
    public void saveAll() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this.citations);
        try {
            FileUtils.writeStringToFile(new File(filename), json);
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(DatabaseJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void destroy(String id) {
        this.delete(id);
        
        this.saveAll();
    }
    
    @Override
    public void destroy(Citation citation) {
        this.destroy(citation.id);
    }
    
    @Override
    public void delete(String id) {
        for(int i = 0; i < this.all().size(); i++){
            if(this.citations.get(i).id.equals(id)){
                this.citations.remove(i);
                break;
            }
        }
    }
    
    @Override
    public void delete(Citation citation) {
        this.delete(citation.id);
    }

    @Override
    public void clear() {
        this.citations = new ArrayList<>();
        this.citations.clear();
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this.citations);
        try {
            FileUtils.writeStringToFile(new File(filename), json);
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(DatabaseJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
