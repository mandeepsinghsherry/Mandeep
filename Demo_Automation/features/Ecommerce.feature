Feature: Ecommerce validations

  Scenario: Placing the Order
    Given a login to Ecommerce application with "<username>" and "<password>"
    When Add "zara code 3" to Cart
    Then Verify "zara code 3" is displayed into the Cart
    When Enter valid details and place the Order
    Then verify order in persent in the Orderhistory
    


    Examples:
        | username                       | password |
        | mandeepsingh.sherry@gmail.com  | Iamking@000  |