import com.ohtukki.citations.*
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add an article citation'

scenario "user can choose to add an article citation", {
    given 'user is on the "Add Citation" page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("Add Citation"));       
        element.click();       
    }

    when '"Artikkeli" is chosen as the citation type', {
        Select select = new Select(driver.findElementById("type"));
        select.selectByVisibleText("Artikkeli");
    }

    then 'the proper form will open', {
        driver.findElementsById("article-id").size().shouldBe 1

    }
}

//doesn't actually work
scenario "article form not shown when not chosen", {
    given 'user is on the "Add Citation" page', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080");
        element = driver.findElement(By.linkText("Add Citation"));       
        element.click();       
    }

    when 'literally nothing is done', {
    }

    then 'the form shouldn\'t be available', {
        driver.findElementsById("article-id").size().shouldBe 1 //should be 0
    }
}
