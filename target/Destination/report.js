$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("./src/test/resources/features/shoppingCart.feature");
formatter.feature({
  "line": 1,
  "name": "demo for bigcommerce shopping site",
  "description": "",
  "id": "demo-for-bigcommerce-shopping-site",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "purchase order",
  "description": "",
  "id": "demo-for-bigcommerce-shopping-site;purchase-order",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": ": I have logged in the shopping site",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": ": I have searched for products",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": ": I have a product in my cart",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": ": I complete the checkout process",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": ": I am presented with a purchase confirmation page for my order",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStepdefs.java:27"
});
formatter.result({
  "duration": 1347725000,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.java:55"
});
formatter.result({
  "duration": 2822639400,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.java:81"
});
formatter.result({
  "duration": 1273026200,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.java:99"
});
formatter.result({
  "duration": 9531010600,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.java:137"
});
formatter.result({
  "duration": 4211633000,
  "status": "passed"
});
});