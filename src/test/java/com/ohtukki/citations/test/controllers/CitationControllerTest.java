package com.ohtukki.citations.test.controllers;

import com.ohtukki.citations.Application;
import com.ohtukki.citations.data.Database;
import com.ohtukki.citations.data.DatabaseJsonDao;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CitationControllerTest {
    @Autowired
    private WebApplicationContext webAppContext;
    
    private MockMvc mockMvc;
    private final Database database;
    
    public CitationControllerTest(){
        this.database = new DatabaseJsonDao();
    }
    
    @Before
    public void setUp() throws Exception {
        // Needed for Thymeleaf
        webAppContext.getServletContext().setAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, webAppContext);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        
        this.database.clear();
    }
    
    @After
    public void tearDown() {
        this.database.clear();
        try {
            mockMvc.perform(get("/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void postArticleCitation() throws Exception {
        int size = this.database.all().size();
        
        mockMvc.perform(post("/citation")
                .param("citationType", "article")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("journal", "Journal")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        
        assertEquals("Author", this.database.all().get(this.database.all().size()-1).getAuthor());
        assertEquals(size+1, this.database.all().size());
    }
    
    @Test
    public void postArticleCitationWithOptionals() throws Exception {
        int size = this.database.all().size();
        
        mockMvc.perform(post("/citation")
                .param("citationType", "article")
                .param("id", "ID-")
                .param("author", "Optionals-Author")
                .param("title", "Title")
                .param("journal", "Journal")
                .param("year", "Year")
                
                .param("volume", "Volume")
                .param("number", "Number")
                .param("pages", "Pages")
                .param("month", "Month")
                .param("note", "Note")
                .param("key", "Key"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        
        assertEquals("Optionals-Author", this.database.all().get(this.database.all().size()-1).getAuthor());
        assertEquals(size+1, this.database.all().size());
    }
    @Test
    public void testDownload() {
        this.database.clear();
        try {
        mockMvc.perform(post("/citation")
                .param("citationType", "article")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("journal", "Journal")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
            MvcResult result = mockMvc.perform(get("/download")).andReturn();
            String content = result.getResponse().getContentAsString();

            assertEquals(true, content.startsWith("@ARTICLE"));
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
}