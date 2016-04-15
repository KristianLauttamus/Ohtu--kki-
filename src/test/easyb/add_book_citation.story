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

    when '"Kirja" is chosen as the citation type', {
        Select select = new Select(driver.findElementById("type"))
        select.selectByVisibleText("Kirja")
    }

    then 'the proper form will open', {
        driver.findElementsById("book-id").size().shouldBe 1
        driver.findElementsById("book-editor").size().shouldBe 1
        driver.findElementsById("book-title").size().shouldBe 1
        driver.findElementsById("book-publisher").size().shouldBe 1
        driver.findElementsById("book-year").size().shouldBe 1
        driver.findElementsById("book-volume").size().shouldBe 1
        driver.findElementsById("book-series").size().shouldBe 1
        driver.findElementsById("book-address").size().shouldBe 1
        driver.findElementsById("book-edition").size().shouldBe 1
        driver.findElementsById("book-month").size().shouldBe 1
        driver.findElementsById("book-note").size().shouldBe 1
        driver.findElementsById("book-key").size().shouldBe 1

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
        select.selectByVisibleText("Kirja")

        element = driver.findElementById("book-id")
        element.sendKeys("easyBTestId")

        element = driver.findElementById("book-editor")
        element.sendKeys("easyBTestEditor")

        element = driver.findElementById("book-title")
        element.sendKeys("easyBTestTitle")

        element = driver.findElementById("book-publisher")
        element.sendKeys("easyBTestPublisher")

        element = driver.findElementById("book-year")
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
        select.selectByVisibleText("Kirja")
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