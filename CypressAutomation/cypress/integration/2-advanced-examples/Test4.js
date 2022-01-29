/// <reference types= "Cypress"  />

describe('My fourth Test Suite', function()  {

    it('My fourth test case', function(){

    //popups and notifications
    cy.visit("https://rahulshettyacademy.com/AutomationPractice/")
    cy.get('#alertbtn').click()
    cy.get('[value="Confirm"]').click()

    // window alert 
   cy.on('window:alert',(str) =>
   {
       // Mocha
       expect(str).to.equal('Hello , share this practice page and share your knowledge')
   })
    
   
   // window confirm
   cy.on('window:confirm',(str) =>
   {
       // Mocha
       expect(str).to.equal('Hello , Are you sure you want to confirm?')
   })

   
   // open new tab
   cy.get('#opentab').invoke('removeAttr','target').click()
   cy.url().should('include','rahulshetty')
   cy.go('back')




})

    
}  ) 