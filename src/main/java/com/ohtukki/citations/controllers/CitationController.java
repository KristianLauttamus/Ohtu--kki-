package com.ohtukki.citations.controllers;

import com.ohtukki.citations.data.Database;
import com.ohtukki.citations.data.DatabaseJsonDao;
import com.ohtukki.citations.models.ArticleCitation;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CitationController {
    private DatabaseJsonDao database;
    
    public CitationController(){
        this.database = new DatabaseJsonDao(DatabaseJsonDao.DEFAULT_FILE);
    }
    
    /**
     * List all Citations
     * @param model
     * @return view from resources/templates
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("citations", this.database.all());
        
        return "index";
    }
    
    /**
     * Return form to create a new Citation
     * @param model
     * @return view from resources/templates
     */
    @RequestMapping(value = "/citation", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("articleCitation", new ArticleCitation());
        
        return "create-citation";
    }
    
    /**
     * Handle inputs and store the posted Citation
     * @param type
     * @param articleCitation
     * @param articleCitationResult
     * @return view from resources/templates
     */
    @RequestMapping(value = "/citation", method = RequestMethod.POST)
    public String store(@RequestParam("type") String type,
            @ModelAttribute ArticleCitation articleCitation, BindingResult articleCitationResult) {
        
        if(type.equals("article")){
            this.database.save(articleCitation);
        }
        
        return "redirect:/";
    }

}
