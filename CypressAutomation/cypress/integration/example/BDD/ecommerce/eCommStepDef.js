
import HomaPage from '../../../../support/pageObjects/HomePage'
import { Given,When,Then, And } from "cypress-cucumber-preprocessor/steps";

const homePage = new HomaPage()
Given('I open Ecommerce Page',function()
{

    cy.visit(Cypress.env('url'))
})

//  When I add items to Cart
When('I add items to Cart',()=>
{
    homePage.getShopTab().click()

    this.data.productName.forEach(function(element){
     cy.selectProduct('element')
 
    });
 
    cy.get('#navbarResponsive > .navbar-nav > .nav-item > .nav-link').click()
    cy.contains('Checkout').click()
})

// And validate the total prices
And('validate the total prices',()=>
{

    cy.get(' tr td:nth-child(4) strong').each(($e1, index, $list) =>
   {
    // cy.log($e1)
    const amount = $e1.text()
    var res = amount.split(" ")
    res = res[1].trim()
    sum= Number(sum+Number(res))
    
   }).then(function(){
    cy.log(sum)
   })
  cy.get('h3 string').then(function(element)
  {
    const amount = element.text()
    var res = amount.split(" ")
    var total = res[1].trim()

    expect(Number(total)).to.equal(sum)
  })
})

// Then Select the country submit and verify Thankyou

Then('Select the country submit and verify Thankyou',()=>
{

    cy.get('#country').type('India')
   cy.get('.suggestions > ul > li > a').click()
   cy.get('#checkbox2').click({force: true})
   cy.get('.ng-untouched > .btn').click()
   //cy.get('.alert').should('have.text','Success! Thank you! Your order will be delivered in next few weeks :-).')

   cy.get('.alert').then(function(element)
   {
    
     expect(actualText.includes('Success!')).to.be.true
   })
})