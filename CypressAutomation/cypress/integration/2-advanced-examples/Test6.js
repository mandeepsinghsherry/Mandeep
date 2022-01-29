/// <reference types= "Cypress"  />

describe('My sixth Test Suite', function()  {

    it('My sixth test case', function(){

    //Handling Mouse Over
    cy.visit("https://rahulshettyacademy.com/AutomationPractice/")
    
    cy.get('.mouse-hover-content').invoke('show')
    cy.contains('Top').click({ force: true})
    cy.url().should('include','top')
    


})

    
} ) 