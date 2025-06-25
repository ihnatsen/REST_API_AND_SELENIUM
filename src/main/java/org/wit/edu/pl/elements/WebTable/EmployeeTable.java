package org.wit.edu.pl.elements.WebTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.wit.edu.pl.BasePage;
import org.wit.edu.pl.PageMenu;
import org.wit.edu.pl.elements.WebTable.skeleton.Employee;
import org.wit.edu.pl.elements.WebTable.skeleton.Form;
import org.wit.edu.pl.elements.WebTable.skeleton.Table;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class EmployeeTable extends BasePage implements Table {

    @FindBy(xpath="//div[text()='First Name']/..")
    WebElement firstName;

    @FindBy(xpath="//div[text()='Last Name']/..")
    WebElement lastName;

    @FindBy(xpath="//div[text()='Age']/..")
    WebElement age;

    @FindBy(xpath="//div[text()='Email']/..")
    WebElement email;

    @FindBy(xpath="//div[text()='Salary']/..")
    WebElement salary;

    @FindBy(xpath="//div[text()='Department']/..")
    WebElement department;

    @FindBy(id="edit-record-1")
    WebElement edit;

    @FindBy(id="delete-record-1")
    WebElement delete;

    // That xpath lets to extract all WebElements, which contain cell value and are not empty.
    @FindBy(xpath="//div[@role='row' and div[not(span)][position()>1]]//div[@role='gridcell' and normalize-space()]")
    List<WebElement> webElementsCells;


    Form form;
    public EmployeeTable(WebDriver driver) {
        super(driver);
        URL = PageMenu.WebTables.getURL();
        Form form = new RegistrationForm(driver);
    }

    @Override
    public void clickFirstName() {
        waitUntilElementToBeClickable(firstName);
        firstName.click();
    }

    @Override
    public void clickLastName() {
        lastName.click();
    }

    @Override
    public void clickAge() {
        age.click();
    }

    @Override
    public void clickEmail() {
        email.click();
    }

    @Override
    public void clickSalary() {
        salary.click();
    }

    @Override
    public void clickDepartment() {
        department.click();
    }

    @Override
    public void clickEdit() {
        edit.click();
    }

    @Override
    public void clickDelete() {
        delete.click();
    }


    public void clearFiled(WebElement element) {
        element.clear();
    }

    public List<Employee> sortBy(Function<Employee, Integer> keyExtractor, List<Employee> table) {
        return table.stream()
                .sorted(Comparator.comparing(keyExtractor))
                .toList();

    }

    public void clearFirstName(){
        clearField(firstName);
    }

    @Override
    public List<Employee> getTable() {

        // Extract text values from WebElement cells using Stream API.
        List<String> cells = webElementsCells.stream().map(WebElement::getText).toList();

        // Getting number of columns
        int columnCount = Employee.class.getRecordComponents().length;

        // Getting number of rows
        int rowCount = cells.size()/Employee.class.getRecordComponents().length;

        // Split cells into rows and map each row to an Employee object.
        // Initialization List<Employee>.
        return IntStream
                .range(0, rowCount)
                .mapToObj(i -> new Employee(
                        cells.subList(i * columnCount, (i + 1) * columnCount)
                ))
                .toList();
    }

}
