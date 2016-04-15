import com.ohtukki.citations.*
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.firefox.FirefoxDriver
import com.ohtukki.citations.data.DatabaseJsonDao

description 'User can view added citations'

driver = new FirefoxDriver();

scenario "After adding a new citation, it is viewable at the frontpage", {
    given 'user adds a new citation', {
        driver.get("http://localhost:8080")
        WebElement element = driver.findElement(By.linkText("Add Citation"))
        element.click()

        Select select = new Select(driver.findElementById("type"))
        select.selectByVisibleText("Artikkeli")

        element = driver.findElementById("article-id")
        element.sendKeys("easyBTestId")

        element = driver.findElementById("article-author")
        element.sendKeys("easyBTestAuthor")

        element = driver.findElementById("article-name")
        element.sendKeys("easyBTestName")

        element = driver.findElementById("article-journal")
        element.sendKeys("easyBTestJournal")

        element = driver.findElementById("article-year")
        element.sendKeys("easyBTestYear")

        element = driver.findElementByClassName("btn")
        element.click()
    }

    when 'user navigates to the frontpage', {
        driver.get("http://localhost:8080");
    }

    then 'added citation is visible', {
        driver.getPageSource().contains("easyBTestId").shouldBe true

        new DatabaseJsonDao().clear()
        driver.quit()
    }
}