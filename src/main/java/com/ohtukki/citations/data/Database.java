package com.ohtukki.citations.data;

import com.ohtukki.citations.models.Citation;
import java.util.List;

/**
 * Quick interface for future proofing
 */
public interface Database {
    
    // Get all
    public List<Citation> all();
    
    // Save single
    public void save(Citation citation);
    
}
