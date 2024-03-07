
@tag
Feature: purchase the order from ecommerce site
  I want to use this template for my feature file
Background:
Given I landed on Ecommerce page

  @Regression
  Scenario Outline: positive test of submitting the order
    Given logged in with username <name> and password <password>
    When I add product <productname> to cart
    And checkout <productname>  and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page

    Examples: 
      | name                 | password      | productname     |
      | rajicse159@gmail.com | Aqws@1111     | ADIDAS ORIGINAL |
      
