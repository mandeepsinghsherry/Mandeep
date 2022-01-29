/// <reference types= "Cypress"  />
/// <reference types= "cypress-iframe"  />

describe('My eigth Test Suite', function()  {

    it('My eigth test case', function(){

    //
    cy.visit("https://rahulshettyacademy.com/AutomationPractice/")

    // for iframes

    cy.frameLoaded('#courses-iframe')
    cy.iframe().find("a[href*='mentorship]").eq(0).click()
    cy.iframe().find('h1[class*="pricing-title"]').should("have.length",'2')
        




})

    
} ) 