package com.ohtukki.citations.test.controllers;

import com.ohtukki.citations.Application;
import com.ohtukki.citations.data.Database;
import com.ohtukki.citations.data.DatabaseJsonDao;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockMultipartFile;
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
    public void invalidCitationIsNotAdded() throws Exception {
        int size = this.database.all().size();
        
        // Booktitle field is missing
        mockMvc.perform(post("/citation")
                .param("citationType", "conference")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("publisher", "Publisher")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        
        assertEquals(size, this.database.all().size());
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
    @Test
    public void testEmptyUpload() {
        try {
            MockMultipartFile file = new MockMultipartFile("file", "orig", null, "".getBytes());
            mockMvc.perform(fileUpload("/upload").file(file))
                    .andExpect(model().attribute("message", "You failed to upload file because the file was empty"));
        } catch (Exception ex) {
            Logger.getLogger(CitationControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testUpload() {
        try {
            MockMultipartFile file = new MockMultipartFile("file", "orig", null, "[]".getBytes());
            mockMvc.perform(fileUpload("/upload").file(file))
                    .andExpect(model().attribute("message", "You successfully uploaded file with 0 Citations."));
        } catch (Exception ex) {
            Logger.getLogger(CitationControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void postBookCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "book")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("publisher", "Publisher")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postBookletCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "booklet")
                .param("id", "Id")
                .param("title", "Title"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postConferenceCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "conference")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("booktitle", "booktitle")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postInbookCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "inbook")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("pages", "Pages") // TODO one of fields pages and
                .param("chapter", "Chapter") // chapter should be enough
                .param("publisher", "Publisher")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postIncollectionCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "incollection")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("booktitle", "booktitle")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postInproceedingsCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "inproceedings")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("booktitle", "booktitle")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postManualCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "manual")
                .param("id", "Id")
                .param("title", "Title"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postMastersthesisCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "mastersthesis")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("school", "School")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postMiscCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "misc")
                .param("id", "Id"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postPhdthesisCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "phdthesis")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("school", "School")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postProceedingsCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "proceedings")
                .param("id", "Id")
                .param("title", "Title")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postTechReportCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "techreport")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("institution", "Institution")
                .param("year", "Year"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
    
    @Test
    public void postUnpublishedCitation() throws Exception {
        int size = this.database.all().size();
        mockMvc.perform(post("/citation")
                .param("citationType", "unpublished")
                .param("id", "Id")
                .param("author", "Author")
                .param("title", "Title")
                .param("note", "Note"))
                .andExpect(status().is3xxRedirection())
                .andReturn();
        assertEquals(size + 1, this.database.all().size());
    }
}