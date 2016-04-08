package com.ohtukki.citations.data;

import com.ohtukki.citations.models.Citation;
import java.util.List;

/**
 * Quick interface for future proofing
 */
public interface Database {
    // Get all
    public List<Citation> all();
    
    // Find
    public Citation find(String id);
    
    // Save single
    public void save(Citation citation);
    
    // Add without saving
    public void add(Citation citation);
    
    // Save all
    public void saveAll();
    
    // Delete and save
    public void destroy(String id);
    public void destroy(Citation citation);
    
    // Delete without saving
    public void delete(String id);
    public void delete(Citation citation);
}
