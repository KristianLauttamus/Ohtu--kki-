package com.ohtukki.citations.controllers;

import com.ohtukki.citations.data.Database;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CitationController {
    private Database database;
    
    public CitationController(){
        // Todo
        // this.database = new Database;
    }
    
    /**
     * List all Citations
     * @param model
     * @return view from resources/templates
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        // Todo
        // Get all Citations
        // model.addAttribute("citations", citationsArray(?));
        return "index";
    }

}
