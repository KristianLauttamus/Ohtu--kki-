import com.ohtukki.citations.*
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import com.ohtukki.citations.data.DatabaseJsonDao

/*
*/

description 'User can download added citations'

driver = new HtmlUnitDriver()

scenario "After adding a new citation, it is downloadable at the frontpage", {
    given 'user adds a new citation', {
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

    when 'user starts download feature', {
        driver.get("http://localhost:8080/download");
    }

    then 'added citation is visible', {
		//print "Page :" + driver.getPageSource()
        driver.getPageSource().contains("@BOOK").shouldBe true

        new DatabaseJsonDao().clear()
        driver.quit()
    }
}
