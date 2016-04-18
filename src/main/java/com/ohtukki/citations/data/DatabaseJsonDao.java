package com.ohtukki.citations.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ohtukki.citations.models.Citation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseJsonDao implements Database {
    public static String DEFAULT_FILE = "citations.json";
    
    private String filename;
    private List<Citation> citations;
    
    public DatabaseJsonDao() {
        this.filename = DEFAULT_FILE;
        this.citations = new ArrayList<>();
    }
    
    public DatabaseJsonDao(String filename) {
        this.filename = filename;
        this.citations = new ArrayList<>();
    }
    
    @Override
    public List<Citation> all() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Citation.class, new CitationCreator());
        Gson gson = builder.create();
        String data = null;
        try {
            data = FileUtils.readFileToString(new File(filename));
        } catch (IOException ex) {
            Logger.getLogger(DatabaseJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.citations = gson.fromJson(data, new TypeToken<ArrayList<Citation>>(){}.getType());
        
        return citations;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void save(Citation citation) {
        this.add(citation);
        try {
            this.saveAll();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void add(Citation citation) {
        if(citation.validate())
            citations.add(citation);
    }
    
    @Override
    public void saveAll() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this.citations);
        try {
            FileUtils.writeStringToFile(new File(filename), json);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void destroy(String id) {
        for(int i = 0; i < this.citations.size(); i++){
            if(citations.get(i).id.equals(id)){
                this.citations.remove(i);
            }
        }
        
        this.saveAll();
    }
    
    @Override
    public void destroy(Citation citation) {
        this.destroy(citation.id);
    }
    
    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(Citation citation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        } catch (IOException ex) {
            Logger.getLogger(DatabaseJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
