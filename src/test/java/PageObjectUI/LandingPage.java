package PageObjectUI;

import UI.UI_utils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Set;

public class LandingPage {
    UI_utils utils;
    WebDriver driver;
    private static long TIMEOUT_S = 35;

    @FindBy(xpath = "//button[@id='quick-search-expand']")
    public WebElement searchButton;

    @FindBy(xpath = "//input[@id='nav-quick-search']")
    public WebElement searchBox;

    @FindBy(xpath = "//div[@class='dropdown dropdown--quickSearch is-open f-open-dropdown']//li[@class='product']")
    public List<WebElement> productList;

    @FindBy(xpath = "//section[@class='quickSearchResults']//a[@data-button-type='add-cart']")
    public List<WebElement> addToCartList;

    @FindBy(xpath = "//a[@title='Click here to proceed to checkout']")
    public WebElement checkoutButton;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement mail;

    @FindBy(xpath = "//input[@id='privacyPolicy']")
    public WebElement checkbox;

    @FindBy(xpath = "//button[@id='checkout-customer-continue']")
    public WebElement continueAsGuestBtn;

    //By searchButton=By.xpath("//button[@id='quick-search-expand']");

    @FindBy(xpath = "//a[@id='checkout-guest-continue']")
    public WebElement continueAsGuestBtnNextPg;

    @FindBy(xpath = "//input[@id='firstNameInput']")
    public WebElement firstname;

    @FindBy(xpath = "//input[@id='lastNameInput']")
    public WebElement lastname;

    @FindBy(xpath = "//input[@id='addressLine1Input']")
    public WebElement addressUser;

    @FindBy(xpath = "//input[@id='cityInput']")
    public WebElement cityOfUser;

    @FindBy(xpath = "//input[@id='postCodeInput']")
    public WebElement postCodeOfUser;

    @FindBy(xpath = "//input[@id='phoneInput']")
    public WebElement phoneNum;

    @FindBy(xpath = "//button[@id='checkout-shipping-continue']")
    public WebElement continueBtn;

    @FindBy(xpath = "//input[@id='ccNumber']")
    public WebElement creditCardNo;

    @FindBy(xpath = "//input[@id='ccExpiry']")
    public WebElement expDate;

    @FindBy(xpath = "//input[@id='ccCvv']")
    public WebElement cvV;

    @FindBy(xpath = "//button[@id='checkout-payment-continue']")
    public WebElement placeOrderBtn;

    @FindBy(xpath = "//input[@id='ccName']")
    public WebElement cardname;

    @FindBy(xpath = "//h1[@class='optimizedCheckout-headingPrimary']")
    public WebElement confirmationMsg;

    @FindBy(xpath = "//img[@title='Scrub Brush']")
    public WebElement brushImg;

    @FindBy(xpath = "//span[@class='header-logo-text']")
    public WebElement headerText ;

    @FindBy(xpath = "//p[@class='heroCarousel-title']")
    public List<WebElement> slideImgList;



    public LandingPage(WebDriver driver, UI_utils utils) {
        this.utils = utils;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void searchProduct(String searchtext) throws InterruptedException {
        try {

            searchButton.click();

            //utils.ClickElement(searchButton);

            searchBox.sendKeys(searchtext);
            utils.waitTillObjectAppears(brushImg);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<WebElement> findProducts() {
        return productList;
    }

    public void addProductToCart() {
        try {

            utils.waitTillObjectAppears(brushImg);


            WebElement firstProduct = findProducts().get(0);
            //mouse hover the first product

            Actions action = new Actions(driver);

            action.moveToElement(firstProduct).perform();
            WebElement addCart = addToCart().get(0);
            addCart.click();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<WebElement> addToCart() {
        return addToCartList;
    }

    public void checkout() throws InterruptedException {
        Actions a = new Actions(driver);
        a.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        utils.waitTillObjectClickable(checkoutButton);

        utils.ClickElement(checkoutButton);
    }

    public void fillDetails(String email) throws InterruptedException {

        //Switch to new tab:
        Set<String> windowHandles = driver.getWindowHandles();
        for (String str : windowHandles) {
            driver.switchTo().window(str);

        }


        utils.waitTillObjectAppears(mail);
        //new Actions(driver).moveToElement(checkbox).perform();
        //utils.ClickElement((checkbox));
        //utils.ClickElement((checkbox));
        //new Actions(driver).moveToElement(mail).perform();
        mail.sendKeys(email);
        mail.sendKeys(email);
        utils.ClickElement((checkbox));
        //utils.ClickElement((checkbox));
        continueAsGuestBtn.click();
        utils.waitTillObjectAppears(continueAsGuestBtnNextPg);
        utils.ClickElement(continueAsGuestBtnNextPg);
    }

    public void fillShippingDetails(String firstName, String lastName, String address, String city, String postalCode, String phoneNumber) throws InterruptedException {
        //Thread.sleep(1000);
        utils.waitTillObjectAppears(firstname);
        try {
            if (firstname.isDisplayed()) {
                firstname.sendKeys(firstName);
                lastname.sendKeys(lastName);
                addressUser.sendKeys(address);
                cityOfUser.sendKeys(city);
                postCodeOfUser.sendKeys(postalCode);
                phoneNum.sendKeys(phoneNumber);


                JavascriptExecutor js = (JavascriptExecutor) driver;

                js.executeScript("arguments[0].scrollIntoView();", cityOfUser);
                utils.waitTillObjectClickable(continueBtn);

                utils.ClickElement(continueBtn);


            } else {
                System.out.println("still loading shipping details page");
                utils.waitTillObjectAppears(firstname);
                try {
                    if (firstname.isDisplayed()) {
                        firstname.sendKeys(firstName);
                        lastname.sendKeys(lastName);
                        addressUser.sendKeys(address);
                        cityOfUser.sendKeys(city);
                        postCodeOfUser.sendKeys(postalCode);
                        phoneNum.sendKeys(phoneNumber);


                        JavascriptExecutor js = (JavascriptExecutor) driver;

                        js.executeScript("arguments[0].scrollIntoView();", cityOfUser);
                        utils.waitTillObjectClickable(continueBtn);

                        utils.ClickElement(continueBtn);

                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void placeOrder(String cardNum, String ccv, String expiryDate, String creditCardname) {
        utils.waitTillObjectAppears(creditCardNo);
        try {
            //utils.waitTillObjectAppears(creditCardNo);
            if (creditCardNo.isDisplayed()) {
                creditCardNo.sendKeys(cardNum);
                expDate.sendKeys(expiryDate);
                cvV.sendKeys(ccv);
                cardname.sendKeys(creditCardname);
                placeOrderBtn.click();
            } else {
                System.out.println("loading purchase page");
                utils.waitTillObjectAppears(creditCardNo);
                try {
                    //utils.waitTillObjectAppears(creditCardNo);
                    if (creditCardNo.isDisplayed()) {
                        creditCardNo.sendKeys(cardNum);
                        expDate.sendKeys(expiryDate);
                        cvV.sendKeys(ccv);
                        cardname.sendKeys(creditCardname);
                        placeOrderBtn.click();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public String purchaseConfirmationValidation() throws InterruptedException {

        utils.waitTillObjectAppears(confirmationMsg);
        String confirmationText = confirmationMsg.getText().split("!")[0].toString();
        //System.out.println(confirmationText);


        return confirmationText;
    }

    public List<WebElement> headerTitleList() {
        return slideImgList;
    }
}
