Feature: End to End Ecommerce validation


   application regression

   Scenario: Ecommerce Products delivery
   Given I open Ecommerce Page
   When I add items to Cart
   And validate the total prices
   Then Select the country submit and verify Thankyou



