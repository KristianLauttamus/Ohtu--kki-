import com.ohtukki.citations.*
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import com.ohtukki.citations.data.DatabaseJsonDao



description 'User can upload a bibtex file'

driver = new HtmlUnitDriver()
DatabaseJsonDao db = new DatabaseJsonDao()
db.clear()

scenario "After uploading a bibtex file, the citations are added to the database", {

    given 'user is on the index page', {
        driver.get("http://localhost:8080")    
    }

    when 'user uploads a bibtex file with two citations', {
        driver.findElement(By.name("file")).sendKeys(System.getProperty("user.dir")+"/test.bib");

        element = driver.findElement(By.name("upload"))
        element.click()
    }

    then 'the citations should be added to the database', {
        db.all().size().shouldBe 2

        db.clear()
    }
}

scenario "If the file is not valid, nothing is added to the database", {

    given 'user is on the index page', {
        driver.get("http://localhost:8080")    
    }

    when 'user uploads pom', {
        driver.findElement(By.name("file")).sendKeys(System.getProperty("user.dir")+"/pom.xml");

        element = driver.findElement(By.name("upload"))
        element.click()
    }

    then 'no citations should be added to the database', {
        db.all().size().shouldBe 0

        db.clear()
    }
}

scenario "Citations with the same ID cannot be uploaded again", {

    given 'user has already uploaded the citations', {
        driver.get("http://localhost:8080")

        driver.findElement(By.name("file")).sendKeys(System.getProperty("user.dir")+"/test.bib");

        element = driver.findElement(By.name("upload"))
        element.click()
    }

    when 'user uploads the same citations again', {
        driver.findElement(By.name("file")).sendKeys(System.getProperty("user.dir")+"/test.bib");

        element = driver.findElement(By.name("upload"))
        element.click()
    }

    then 'the citations should not be added again', {
        db.all().size().shouldBe 2

        db.clear()
        driver.quit()
    }
}

/*
scenario "The user gets an error message, if uploaded citation was rejected because of duplicate id", {

    given 'user has added a citation with some id', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()

        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Article")

        element = driver.findElementById("Article-id")
        element.sendKeys("test")

        element = driver.findElementById("Article-author")
        element.sendKeys("easyBTestAuthor")

        element = driver.findElementById("Article-title")
        element.sendKeys("easyBTestTitle")

        element = driver.findElementById("Article-journal")
        element.sendKeys("easyBTestJournal")

        element = driver.findElementById("Article-year")
        element.sendKeys("easyBTestYear")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    when 'user uploads a bibtex file containing a citation with the same id', {
        driver.findElement(By.name("file")).sendKeys(System.getProperty("user.dir")+"/test.bib");

        element = driver.findElement(By.name("upload"))
        element.click()
    }

    then 'an error message should appear', {
        driver.getPageSource().contains("Following citations were rejected in validation").shouldBe true

        db.clear()
        driver.quit()
    }
}*/
