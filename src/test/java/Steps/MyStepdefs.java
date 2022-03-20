package Steps;

import PageObjectUI.All_pageobjects;
import UI.UI_utils;
import cucumber.api.java8.En;
//import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
//import cucumber.api.java8.En;

public class MyStepdefs implements En {

    UI_utils utils;
    WebDriver driver;

    public MyStepdefs(UI_utils utils) throws IOException {
        this.utils = utils;

        //to invoke browser:
        driver = utils.initializeDriver();

        All_pageobjects pageobjects = new All_pageobjects(driver, utils);

        Given("^: I have opened the shopping site$", () -> {


            String urlName = utils.prop.getProperty("url");
            System.out.println(urlName);

            //open the shopping site page
            driver.get(urlName);

            //Assert if header text and slide image present in the shopping page once successfully opened the web pg
            String headerText = pageobjects.headerText.getText();

            List<WebElement> slideImageList = pageobjects.headerTitleList();

            if (headerText.equals("CORNERSTONE DEMO") && (slideImageList.size() > 0)) {
                System.out.println("Successfully logged in the shopping site");
                Assert.assertEquals("Successfully logged in the shopping site", true, true);
            } else {
                System.out.println("Not logged in the shopping site");
                Assert.assertEquals("Not logged in the shopping site", true, false);
            }

            driver.manage().window().maximize();

        });

        And("^: I have searched for products$", () -> {

            //searches for the product and validates product present or not
            try {

                pageobjects.searchProduct(utils.prop.getProperty("searchtext"));

                //Assert if searched product appears after successfully searching product
                boolean productSearched = pageobjects.brushImg.isDisplayed();
                if (productSearched == true) {
                    System.out.println("Successfully searches for the product");
                    Assert.assertEquals("Successfully searches for the product", true, true);
                } else {
                    System.out.println("Product not searched");
                    Assert.assertEquals("Product not searched", true, false);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        And("^: I have a product in my cart$", () -> {

            // add the product to cart

            pageobjects.addProductToCart();

            //Assert 'checkout' button present after product successfully added to cart
            boolean checkOutButtonPresent = pageobjects.checkoutButton.isDisplayed();
            if (checkOutButtonPresent == true) {
                System.out.println("Successfully added product to cart");
                Assert.assertEquals("Successfully added product to cart", true, true);
            } else {
                System.out.println("Product not added to cart");
                Assert.assertEquals("Product not added to cart", true, false);
            }

        });
        When("^: I complete the checkout process$", () -> {
            //complete checkout process
            try {
                //checkout:
                pageobjects.checkout();
                //fill details:
                pageobjects.fillDetails(utils.prop.getProperty("email"));

                String FirstName = utils.prop.getProperty("FirstName");
                String LastName = utils.prop.getProperty("LastName");
                String Address = utils.prop.getProperty("Address");
                String City = utils.prop.getProperty("City");
                String PostalCode = utils.prop.getProperty("PostalCode");
                String PhoneNumber = utils.prop.getProperty("PhoneNumber");

                //fill shipping details:
                pageobjects.fillShippingDetails(FirstName, LastName, Address, City, PostalCode, PhoneNumber);

                String creditCardnum = utils.prop.getProperty("creditCardnumber");
                String ccv = utils.prop.getProperty("CCV");
                String expiryDate = utils.prop.getProperty("expiryDate");
                String nameOnCard = utils.prop.getProperty("nameOnCard");


                utils.waitTillObjectClickable(pageobjects.placeOrderBtn);

                //Assert 'place order' button present after checkout done successfully and all details filled
                boolean placeOrderPresent = pageobjects.placeOrderBtn.isDisplayed();
                if (placeOrderPresent == true) {
                    System.out.println("Successfully completed checkout process");
                    Assert.assertEquals("Successfully completed checkout process", true, true);
                } else {
                    System.out.println("Checkout process not successfull");
                    Assert.assertEquals("Checkout process not successfull", true, false);
                }

                //place order:
                pageobjects.placeOrder(creditCardnum, ccv, expiryDate, nameOnCard);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Then("^: I am presented with a purchase confirmation page for my order$", () -> {


            try {
                //get confirmation page message text
                pageobjects.purchaseConfirmationValidation();
                String confirmationMsgText = pageobjects.purchaseConfirmationValidation();

                //Assert if Confirmation message appears on purchase confirmation page on successfull placement of order
                if (confirmationMsgText.contains("Thank you")) {
                    Assert.assertEquals("successfully presented with a purchase confirmation page", true, true);
                    System.out.println("successfully presented with a purchase confirmation page with " + confirmationMsgText + " msg");
                } else {
                    System.out.println("Not presented with a purchase confirmation page");
                    Assert.assertEquals("Not presented with a purchase confirmation page", true, false);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        });


    }
}
