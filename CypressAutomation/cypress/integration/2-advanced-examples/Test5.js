/// <reference types= "Cypress"  />

describe('My fifth Test Suite', function()  {

    it('My fifth test case', function(){

    //Handling web table and find the next sibling
    cy.visit("https://rahulshettyacademy.com/AutomationPractice/")
    
    cy.get('td:nth-child(2)').each(($e1, index, $list) => 
    {

        const text = $e1.text()
        if(text.includes("Python"))
        {
           cy.get('td:nth-child(2)').eq(index).next().then(function(price)
           {

            const pricetext = price.text()
            expect(pricetext).to.equal('25')
           })
        }
   
    })


})

    
} ) 