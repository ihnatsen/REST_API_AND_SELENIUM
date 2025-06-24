package org.wit.edu.pl.elements.WebTable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wit.edu.pl.BasePage;
import org.wit.edu.pl.PageMenu;
import org.wit.edu.pl.elements.WebTable.skeleton.Form;

import java.util.Optional;

public class RegistrationForm extends BasePage implements Form {

    @FindBy(id="firstName")
    WebElement firstName;

    @FindBy(id="lastName")
    WebElement lastName;

    @FindBy(id="userEmail")
    WebElement email;

    @FindBy(id="age")
    WebElement age;

    @FindBy(id="salary")
    WebElement salary;

    @FindBy(id="department")
    WebElement department;

    @FindBy(id="submit")
    WebElement submit;


    public RegistrationForm(WebDriver driver) {
        super(driver);
        URL = PageMenu.WebTables.getURL();
    }

    @Override
    public void fillFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    @Override
    public void fillLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    @Override
    public void fillEmail(String email) {
        this.email.sendKeys(email);
    }

    @Override
    public void fillAge(String age) {
        this.age.sendKeys(age);
    }

    @Override
    public void fillSalary(String salary) {
        this.salary.sendKeys(salary);
    }

    @Override
    public void fillDepartment(String department) {
        this.department.sendKeys(department);
    }

    @Override
    public Boolean isHasMaxLen(WebElement element, int len) {
        int maxlength = Integer.parseInt(Optional.ofNullable(element.getAttribute("maxlength")).orElse("-1"));
        return  maxlength <= len;
    }

    @Override
    public Boolean hasPattern(WebElement element, String pattern) {
        return hasAttribute(element, "pattern", pattern);
    }

    @Override
    public void clickSubmit() {
        waitUntilElementToBeClickable(submit);
        submit.click();
    }

    @Override
    public void clearFirstName(){
        clearField(firstName);
    }
}
