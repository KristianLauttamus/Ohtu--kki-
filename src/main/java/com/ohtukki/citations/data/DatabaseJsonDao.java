package com.ohtukki.citations.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohtukki.citations.models.Citation;
import com.ohtukki.citations.models.CitationList;
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
        CitationList list = gson.fromJson(data, CitationList.class);
        this.citations = list.getReferences();
        
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
        citations.add(citation);
    }
    
    @Override
    public void saveAll() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        CitationList list = new CitationList(this.citations);
        String json = gson.toJson(list);
        try {
            FileUtils.writeStringToFile(new File(filename), json);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void destroy(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void destroy(Citation citation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        CitationList list = new CitationList(new ArrayList<Citation>());
        String json = gson.toJson(list);
        try {
            FileUtils.writeStringToFile(new File(filename), json);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseJsonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
