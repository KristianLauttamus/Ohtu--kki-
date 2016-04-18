import com.ohtukki.citations.*
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import com.ohtukki.citations.data.DatabaseJsonDao

description 'User can add an article citation'

DatabaseJsonDao db = new DatabaseJsonDao()
db.clear()

/*
scenario "user can choose to add an article citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when '"Artikkeli" is chosen as the citation type', {
        Select select = new Select(driver.findElementById("type"))
        select.selectByVisibleText("Artikkeli")
    }

    then 'the proper form will open', {
        driver.findElementsById("article-id").size().shouldBe 1
        driver.findElementsById("article-author").size().shouldBe 1
        driver.findElementsById("article-title").size().shouldBe 1
        driver.findElementsById("article-journal").size().shouldBe 1
        driver.findElementsById("article-year").size().shouldBe 1
        driver.findElementsById("article-volume").size().shouldBe 1
        driver.findElementsById("article-number").size().shouldBe 1
        driver.findElementsById("article-pages").size().shouldBe 1
        driver.findElementsById("article-month").size().shouldBe 1
        driver.findElementsById("article-note").size().shouldBe 1
        driver.findElementsById("article-key").size().shouldBe 1
    }
}
*/

scenario "user can create article citation by filling the required fields", {
    given 'user is on the "Add Citation" page', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("type"))
        select.selectByVisibleText("Artikkeli")

        element = driver.findElementById("article-id")
        element.sendKeys("easyBTestId")

        element = driver.findElementById("article-author")
        element.sendKeys("easyBTestAuthor")

        element = driver.findElementById("article-title")
        element.sendKeys("easyBTestTitle")

        element = driver.findElementById("article-journal")
        element.sendKeys("easyBTestJournal")

        element = driver.findElementById("article-year")
        element.sendKeys("easyBTestYear")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is created', {
        db.all().size().shouldBe 1

        db.clear()
    }
}

/*
scenario "user can not create article citation without filling the required fields", {
    given 'user is on the "Add Citation" page', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user tries to create new article citation without filling all the required fields', {
        Select select = new Select(driver.findElementById("type"))
        select.selectByVisibleText("Artikkeli")
        element = driver.findElementByClassName("btn")
        element.click()
    }

    then 'new citation is not created', {
        db.all().size().shouldBe 0

        db.clear()
        driver.quit()
    }
}
*/