const { When, Then, Given } = require('@cucumber/cucumber')
const { expect } = require('@playwright/test');
const playwright = require('@playwright/test');

Given('a login to Ecommerce application with {string} and {string}', {timeout : 100*1000 }, async function (string, string2) {
    // Write code here that turns the phrase above into concrete actions

    const browser = await playwright.chromium.launch({
          headless: false
    });
    const context = await browser.newContext();
     this.page = await context.newPage();
     this.email = "mandeepsingh.sherry@gmail.com";
    
    await this.page.goto("https://rahulshettyacademy.com/client");
    await this.page.locator("#userEmail").fill(this.email);
    await this.page.locator("#userPassword").fill("Iamking@000");
    await this.page.locator("[value='Login']").click();
    await this.page.waitForLoadState('networkidle');
    await this.page.locator(".card-body b").first().waitFor();
    const titles = await this.page.locator(".card-body b").allTextContents();
    console.log(titles); 
  });

When('Add {string} to Cart', async function (string) {
    // Write code here that turns the phrase above into concrete actions
    const productName = 'zara coat 3';
    const products = this.page.locator(".card-body");
    const count = await products.count();
    for (let i = 0; i < count; ++i) {
       if (await products.nth(i).locator("b").textContent() === productName) {
          //add to cart
          await products.nth(i).locator("text= Add To Cart").click();
          break;
       }
    }
 
    await this.page.locator("[routerlink*='cart']").click();
  });

Then('Verify {string} is displayed into the Cart', async function (string) {
    // Write code here that turns the phrase above into concrete actions
    await this.page.locator("div li").first().waitFor();
    const bool = await this.page.locator("h3:has-text('zara coat 3')").isVisible();
    expect(bool).toBeTruthy();
});

When('Enter valid details and place the Order', {timeout : 100*1000 }, async function () {
        // Write code here that turns the phrase above into concrete actions
        await this.page.locator("text=Checkout").click();
   
        await this.page.locator("[placeholder*='Country']").type("Ind",{delay:100});
     
        const dropdown = this.page.locator(".ta-results");
        await dropdown.waitFor();
        //await page.pause();
        const optionsCount = await dropdown.locator("button").count();
        for (let i = 0; i < optionsCount; ++i) {
           const text = await dropdown.locator("button").nth(i).textContent();
           if (text === " India") {
              await dropdown.locator("button").nth(i).click();
              break;
           }
        }
     
      });

Then('verify order in persent in the Orderhistory',{timeout : 100*1000 }, async function () {
        // Write code here that turns the phrase above into concrete actions
        expect(this.page.locator(".user__name [type='text']").first()).toHaveText(this.email);
   await this.page.locator(".action__submit").click();
   await expect(this.page.locator(".hero-primary")).toHaveText(" Thankyou for the order. ");
   const orderId = await this.page.locator(".em-spacer-1 .ng-star-inserted").textContent();
   console.log(orderId);

   await this.page.locator("button[routerlink*='myorders']").click();
   await this.page.locator("tbody").waitFor();
   const rows = await this.page.locator("tbody tr");


   for (let i = 0; i < await rows.count(); ++i) {
      const rowOrderId = await rows.nth(i).locator("th").textContent();
      if (orderId.includes(rowOrderId)) {
         await rows.nth(i).locator("button").first().click();
         break;
      }
   }
   const orderIdDetails = await this.page.locator(".col-text").textContent();
   expect(orderId.includes(orderIdDetails)).toBeTruthy();

      });
