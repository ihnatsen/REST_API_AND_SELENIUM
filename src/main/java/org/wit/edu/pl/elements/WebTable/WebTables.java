package org.wit.edu.pl.elements.WebTable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wit.edu.pl.BasePage;
import org.wit.edu.pl.PageMenu;
import org.wit.edu.pl.elements.WebTable.skeleton.Employee;
import org.wit.edu.pl.elements.WebTable.skeleton.Form;
import org.wit.edu.pl.elements.WebTable.skeleton.Table;

import java.util.List;
import java.util.function.Function;

public class WebTables extends BasePage implements Form, Table {

    @FindBy(id="addNewRecordButton")
    WebElement add;

    Form form;
    Table table;

    public WebTables(WebDriver driver, Form form, Table table) {
        super(driver);
        this.form = form;
        this.table = table;
        URL = PageMenu.WebTables.getURL();
    }

    public void clickAdd(){
        waitUntilElementToBeClickable(add);
        add.click();
    }

    @Override
    public void fillFirstName(String firstName) {
        form.fillFirstName(firstName);
    }

    @Override
    public void fillLastName(String lastName) {
        form.fillLastName(lastName);
    }

    @Override
    public void fillEmail(String email) {
        form.fillEmail(email);
    }

    @Override
    public void fillAge(String age) {
        form.fillAge(age);
    }

    @Override
    public void fillSalary(String salary) {
        form.fillSalary(salary);
    }

    @Override
    public void fillDepartment(String department) {
        form.fillDepartment(department);
    }

    @Override
    public void clickSubmit() {
        form.clickSubmit();
    }

    @Override
    public Boolean isHasMaxLen(WebElement element, int len) {
        return form.isHasMaxLen(element, len);
    }

    @Override
    public Boolean hasPattern(WebElement element, String pattern) {
        return form.hasPattern(element, pattern);
    }

    @Override
    public void clearFirstName() {
        form.clearFirstName();
    }

    @Override
    public void clickFirstName() {
        table.clickFirstName();
    }

    @Override
    public void clickLastName() {
        table.clickLastName();
    }

    @Override
    public void clickAge() {
        table.clickAge();
    }

    @Override
    public void clickEmail() {
        table.clickEmail();
    }

    @Override
    public void clickSalary() {
        table.clickSalary();
    }

    @Override
    public void clickDepartment() {
        table.clickDepartment();
    }

    @Override
    public void clickEdit() {
        table.clickEdit();
    }

    @Override
    public void clickDelete() {
        table.clickDelete();
    }


    @Override
    public List<Employee> getTable() {
        return table.getTable();
    }
    @Override
    public List<Employee> sortBy(Function<Employee, Integer> keyExtractor, List<Employee> table){
        return this.table.sortBy(keyExtractor, table);
    }

}
