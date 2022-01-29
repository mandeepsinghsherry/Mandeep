/// <reference types= "Cypress"  />


import HomaPage from '../../support/pageObjects/HomePage'

describe('My sixth Test Suite', function()  
{

  before(function() 
    {
      cy.fixture('example').then(function(data)
      {

       this.data=data

      })

    })

  it('My sixth test case', function()
  {
    const homePage = new HomaPage()
   cy.visit(Cypress.env('url'))

   homePage.getEditBox().type(this.data.name)
   homePage.getGender().select(this.data.gender)
   
   // checking the name validation
   homePage.getTwoWayDataBinding().should('have.value',this.data.name)
   
   // checking the length of character entered 
   homePage.getEditBox().should('have.attr','minlength','2')
   
   //checking the disable of radio button
   homePage.getEnterPreneaur().should('be.disabled')

   // clicking on the shoping page
   homePage.getShopTab().click()

   
   
   this.data.productName.forEach(function(element){
    cy.selectProduct('element')

   });

   cy.get('#navbarResponsive > .navbar-nav > .nav-item > .nav-link').click()
   cy.contains('Checkout').click()
   var sum = 0

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

    
} ) 