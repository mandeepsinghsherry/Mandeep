const base = require('@playwright/test');


exports.customtest = base.test.extend(
{
testDataForOrder :    {
    username : "mandeepsingh.sherry@gmail.com",
    password : "Iamking@000",
    productName:"adidas original"
    }

}

)




