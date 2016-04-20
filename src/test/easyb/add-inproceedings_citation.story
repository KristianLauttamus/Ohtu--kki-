import com.ohtukki.citations.*
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import com.ohtukki.citations.data.DatabaseJsonDao

description 'User can add an inproceedings citation'

DatabaseJsonDao db = new DatabaseJsonDao()
db.clear()

scenario "user can create inproceedings citation by filling the required fields", {
    given 'user is on the "Add Citation" page', {
        driver = new HtmlUnitDriver()
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