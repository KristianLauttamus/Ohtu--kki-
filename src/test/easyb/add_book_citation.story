import com.ohtukki.citations.*
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import com.ohtukki.citations.data.DatabaseJsonDao

description 'User can add a book citation'

driver = new HtmlUnitDriver()
DatabaseJsonDao db = new DatabaseJsonDao()
db.clear()

/*
scenario "user can choose to add an book citation", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when '"Book" is chosen as the citation type', {
        Select select = new Select(driver.findElementById("type"))
        select.selectByVisibleText("Book")
    }

    then 'the proper form will open', {
        driver.findElementsById("Book-id").size().shouldBe 1
        driver.findElementsById("Book-editor").size().shouldBe 1
        driver.findElementsById("Book-title").size().shouldBe 1
        driver.findElementsById("Book-publisher").size().shouldBe 1
        driver.findElementsById("Book-year").size().shouldBe 1
        driver.findElementsById("Book-volume").size().shouldBe 1
        driver.findElementsById("Book-series").size().shouldBe 1
        driver.findElementsById("Book-address").size().shouldBe 1
        driver.findElementsById("Book-edition").size().shouldBe 1
        driver.findElementsById("Book-month").size().shouldBe 1
        driver.findElementsById("Book-note").size().shouldBe 1
        driver.findElementsById("Book-key").size().shouldBe 1

        db.clear()
    }
}
*/

scenario "user can create book citation by filling the required fields", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user fills (at least) required fields and clicks create button', {
        Select select = new Select(driver.findElementById("type"))
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

/*
scenario "user can not create book citation without filling the required fields", {
    given 'user is on the "Add Citation" page', {
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("Add Citation"))
        element.click()
    }

    when 'user tries to create new book citation without filling all the required fields', {
        Select select = new Select(driver.findElementById("type"))
        select.selectByVisibleText("Book")
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