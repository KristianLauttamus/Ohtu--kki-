import com.ohtukki.citations.*
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import com.ohtukki.citations.data.DatabaseJsonDao

description 'User can add all types of citations'

HtmlUnitDriver driver = new HtmlUnitDriver()
DatabaseJsonDao db = new DatabaseJsonDao()
db.clear()


scenario "user can choose to add an article citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when '"Article" is chosen as the citation type', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Article")
    }

    then 'the proper form will open', {
        driver.findElementsById("Article-id").size().shouldBe 1
        driver.findElementsById("Article-author").size().shouldBe 1
        driver.findElementsById("Article-title").size().shouldBe 1
        driver.findElementsById("Article-journal").size().shouldBe 1
        driver.findElementsById("Article-year").size().shouldBe 1
        driver.findElementsById("Article-volume").size().shouldBe 1
        driver.findElementsById("Article-number").size().shouldBe 1
        driver.findElementsById("Article-pages").size().shouldBe 1
        driver.findElementsById("Article-month").size().shouldBe 1
        driver.findElementsById("Article-note").size().shouldBe 1
        driver.findElementsById("Article-key").size().shouldBe 1
    }
}


scenario "user can create article citation by filling the required fields", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Article")

        element = driver.findElementById("Article-id")
        element.sendKeys("easyBTestId")

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

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can not create article citation without filling the required fields", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user tries to create new article citation without filling all the required fields', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Article")
        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is not created', {
        db.all().size().shouldBe 0

        db.clear()
    }
}

scenario "user can create book citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Book")

        element = driver.findElementById("Book-id")
        element.sendKeys("easyBTestId")

        element = driver.findElementById("Book-editor")
        element.sendKeys("easyBTestEditor")

        element = driver.findElementById("Book-title")
        element.sendKeys("easyBTestTitle")

        element = driver.findElementById("Book-publisher")
        element.sendKeys("easyBTestPublisher")

        element = driver.findElementById("Book-year")
        element.sendKeys("easyBTestYear")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create inproceedings citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Inproceedings")

        element = driver.findElementById("Inproceedings-id")
        element.sendKeys("easyBTestId")

        element = driver.findElementById("Inproceedings-author")
        element.sendKeys("easyBTestAuthor")

        element = driver.findElementById("Inproceedings-title")
        element.sendKeys("easyBTestTitle")

        element = driver.findElementById("Inproceedings-booktitle")
        element.sendKeys("easyBTestJournal")

        element = driver.findElementById("Inproceedings-year")
        element.sendKeys("easyBTestYear")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create booklet citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Booklet")

        element = driver.findElementById("Booklet-id")
        element.sendKeys("easyBTestId")

        element = driver.findElementById("Booklet-title")
        element.sendKeys("easyBTestTitle")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create conference citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Conference")

        element = driver.findElementById("Conference-id")
        element.sendKeys("test")

        element = driver.findElementById("Conference-author")
        element.sendKeys("test")

        element = driver.findElementById("Conference-title")
        element.sendKeys("test")

        element = driver.findElementById("Conference-booktitle")
        element.sendKeys("test")

        element = driver.findElementById("Conference-year")
        element.sendKeys("test")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create inbook citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Inbook")

        element = driver.findElementById("Inbook-id")
        element.sendKeys("test")

        element = driver.findElementById("Inbook-author")
        element.sendKeys("test")

        element = driver.findElementById("Inbook-title")
        element.sendKeys("test")

        element = driver.findElementById("Inbook-editor")
        element.sendKeys("test")

        element = driver.findElementById("Inbook-year")
        element.sendKeys("test")

        element = driver.findElementById("Inbook-chapter")
        element.sendKeys("test")

        element = driver.findElementById("Inbook-pages")
        element.sendKeys("test")

        element = driver.findElementById("Inbook-publisher")
        element.sendKeys("test")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create incollection citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Incollection")

        element = driver.findElementById("Incollection-id")
        element.sendKeys("test")

        element = driver.findElementById("Incollection-author")
        element.sendKeys("test")

        element = driver.findElementById("Incollection-title")
        element.sendKeys("test")

        element = driver.findElementById("Incollection-booktitle")
        element.sendKeys("test")

        element = driver.findElementById("Incollection-year")
        element.sendKeys("test")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create manual citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Manual")

        element = driver.findElementById("Manual-id")
        element.sendKeys("test")

        element = driver.findElementById("Manual-title")
        element.sendKeys("test")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create masters thesis citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Masterthesis")

        element = driver.findElementById("Mastersthesis-id")
        element.sendKeys("test")

        element = driver.findElementById("Mastersthesis-author")
        element.sendKeys("test")

        element = driver.findElementById("Mastersthesis-title")
        element.sendKeys("test")

        element = driver.findElementById("Mastersthesis-school")
        element.sendKeys("test")

        element = driver.findElementById("Mastersthesis-year")
        element.sendKeys("test")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create miscellaneous citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Misc")

        element = driver.findElementById("Misc-id")
        element.sendKeys("test")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create PHD thesis citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("PHDThesis")

        element = driver.findElementById("PHDThesis-id")
        element.sendKeys("test")

        element = driver.findElementById("PHDThesis-author")
        element.sendKeys("test")

        element = driver.findElementById("PHDThesis-title")
        element.sendKeys("test")

        element = driver.findElementById("PHDThesis-school")
        element.sendKeys("test")

        element = driver.findElementById("PHDThesis-year")
        element.sendKeys("test")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create proceedings citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Proceedings")

        element = driver.findElementById("Proceedings-id")
        element.sendKeys("test")

        element = driver.findElementById("Proceedings-title")
        element.sendKeys("test")

        element = driver.findElementById("Proceedings-year")
        element.sendKeys("test")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create tech report citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Techreport")

        element = driver.findElementById("TechReport-id")
        element.sendKeys("test")

        element = driver.findElementById("TechReport-author")
        element.sendKeys("test")

        element = driver.findElementById("TechReport-title")
        element.sendKeys("test")

        element = driver.findElementById("TechReport-institution")
        element.sendKeys("test")

        element = driver.findElementById("TechReport-year")
        element.sendKeys("test")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

scenario "user can create unpublished citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("citationType"))
        select.selectByVisibleText("Unpublished")

        element = driver.findElementById("Unpublished-id")
        element.sendKeys("test")

        element = driver.findElementById("Unpublished-author")
        element.sendKeys("test")

        element = driver.findElementById("Unpublished-title")
        element.sendKeys("test")

        element = driver.findElementById("Unpublished-note")
        element.sendKeys("test")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}