/// <reference types= "Cypress"  />

describe('My seventh Test Suite', function()  {

    it('My seventh test case', function(){

    //
    cy.visit("https://rahulshettyacademy.com/AutomationPractice/")

    // for visit same domain name, this is code is useful
    cy.get('#opentab').then(function(el)
    {

        const url = e1.prop('href')
        cy.log(url)
        cy.visit(url)


    })
    
   



})

    
} ) 