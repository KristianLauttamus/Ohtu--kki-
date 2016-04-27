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
        driver.quit()
    }
}
