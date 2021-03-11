package dob1;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class dob2 {
  public static void main(String[] args) throws IOException {
   
      WebDriver driver = null;

Properties prop = new Properties();
    FileInputStream ip = null;
    try {
        ip = new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\dob\\src\\dob1\\dob2.properties");
    } catch (FileNotFoundException e) {
     e.printStackTrace();
    }
    prop.load(ip);
    
    
    System.out.println(prop.getProperty("browser"));
    String browserName = prop.getProperty("browser");
    
    if(browserName.contentEquals("Chrome")) {
     
System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\eclipse-workspace\\dob\\drivers\\chromedriver.exe");
     driver = new ChromeDriver();
    }
    else if(browserName.contentEquals("Firefox")) {
     System.setProperty("webdriver.gecko.driver","C:\\Users\\Admin\\eclipse-workspace\\dob\\drivers\\geckodriver.exe");
     driver = new FirefoxDriver();
    }
    else {
     System.out.println("no browser value is given");
   
    } 
    
    //Opens facebook website
     driver.get(prop.getProperty("baseUrl"));
    
    //click on the link
    driver.findElement(By.linkText("Create New Account")).click();
    
    //Maximize the window
    driver.manage().window().maximize();
    
    //Apply implicitlyWait
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    
    //Apply pageLoadTimeout
    driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    
    //Insert firstname, lastname, mobilenumber & password
    
    driver.findElement(By.name("firstname")).sendKeys(prop.getProperty("Firstname"));
    driver.findElement(By.name("lastname")).sendKeys(prop.getProperty("Lastname"));
    driver.findElement(By.name("reg_email__")).sendKeys(prop.getProperty("mailorphone"));
    driver.findElement(By.name("reg_passwd__")).sendKeys(prop.getProperty("password"));
    
    
    //Selecting and verifying birthday, birthmonth, birthyear from Dropdown
    
    WebElement dayDropDown = driver.findElement(By.name("birthday_day"));
    Select selectDay = new Select(dayDropDown);
    selectDay.selectByVisibleText(prop.getProperty("birthday"));
    
    //It returns selected birthday from drop down
   
    WebElement firstEle = selectDay.getFirstSelectedOption();
    System.out.println("Selected birthday from drop down is = "+firstEle.getText());
    
    
    WebElement monthDropDown = driver.findElement(By.name("birthday_month"));
    Select selectMonth = new Select(monthDropDown);
    selectMonth.selectByIndex(5);
    //It returns selected month from drop down 
    WebElement secondEle = selectMonth.getFirstSelectedOption();
    System.out.println("Selected birthmonth from drop down is = "+secondEle.getText());
    
    WebElement yearDropDown = driver.findElement(By.name("birthday_year"));
    Select selectYear = new Select(yearDropDown);
    selectYear.selectByValue(prop.getProperty("birthyear"));
    //It returns selected year from drop down 
    WebElement thirdEle = selectYear.getFirstSelectedOption();
    System.out.println("Selected birthyear from drop down is = "+thirdEle.getText());
    
          String year = thirdEle.getText();
    
    if(year.equals(prop.getProperty("birthyear"))) {
     System.out.println("Results matched");
     
    }
    else {
     System.out.println("Results not matched");
    }
    
           driver.close();          
  }
}

