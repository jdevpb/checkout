***Spring Boot Mavenized project***

POC uses H2 in-memory DB
data.sql contains inserts statements for inserting test data
H2 console: http://localhost:8080/h2-console
H2 username=dev	
H2 password=Devpwd@#45

Localization:
The services are configured to use Accept-Language request header.
DB is configured to localize item names, currency and taxes as per the locale.
Messages are localized

Security:
produces and consumes attributes are present on each service.
In-memory Basic Authentication using Spring Boot Starter security is provided for POC purposes. Can be upgraded to token based.
	- Only ADMIN role is configured. (Credentials: admin/password)
	- ADMIN is provided authorization access to both APIs
===============================================================================================================
API: /item/{itemCd}   (Item codes 1,2,3 are configured)
Method: GET

Request Header: 
Accept-Language
Locale should be passed in xx-XX format. POC is configured for en-US and fr-FR. 
If no or incorrect locale is passed, en-US is used by default

Description: 
Gets item details for a given item code. This API will be called when each item is scanned.
The returned details will be displayed on the checkout screen.
The details contain item description,  price, category, currency symbol, tax amount, final price, 
tax description (GST, sales tax etc), tax rate. API is configured to handle cases 
where an item may be subject to multiple taxes such as CGST, SGST

Response:
{
    "itemDescription": "MacBook Pro 15",
    "category": "Category A",
    "currencySymbol": "$",
    "price": 2000,
    "finalPrice": 2200,
    "totalTax": 200,
    "itemTaxDetailsBean": [
        {
            "taxDescription": "Sales Tax",
            "taxRate": 10,
            "taxAmount": 200
        }
    ]
}
====================================================================================================
API:/bill
Method: POST

Request Headers: 
Accept-Language
	Locale should be passed in xx-XX format. POC is configured for en-US and fr-FR. 
	If no or incorrect locale is passed, en-US is used by default
Content-Type
	application/json
	
	
Assumptions: 
Payment methods have not been incorporated. 
The service returns all the details, generation of bill in any format like PDF is not included in scope.

Description: 
This API will be called when all the items have been scanned and final bill needs to be generated.
Returns the total bill amount, total tax amount, pre-tax amount and 
individual item details as returned by the above API.


Request:
{
	"itemDetailsBean":[
			    {
			    "itemDescription": "Saffola Vegetable Oil",
			    "category": "Category C",
			    "currencySymbol": "$",
			    "price": 7.2,
			    "finalPrice": 7.2,
			    "totalTax": 0,
			    "itemTaxDetailsBean": [
				{
				    "taxDescription": "Sales Tax",
				    "taxRate": 0,
				    "taxAmount": 0
				}
			    ]
			  },
			  {
			  "itemDescription": "Kellogs Original",
			  "category": "Category B",
			  "currencySymbol": "$",
			  "price": 2,
			  "finalPrice": 2.4,
			  "totalTax": 0.4,
			  "itemTaxDetailsBean": [
			      {
				  "taxDescription": "Sales Tax",
				  "taxRate": 20,
				  "taxAmount": 0.4
			      }
			  ]
			},
			{
			  "itemDescription": "MacBook Pro 15",
			  "category": "Category A",
			  "currencySymbol": "$",
			  "price": 2000,
			  "finalPrice": 2200,
			  "totalTax": 200,
			  "itemTaxDetailsBean": [
			      {
				  "taxDescription": "Sales Tax",
				  "taxRate": 10,
				  "taxAmount": 200
			      }
			  ]
			}
		      ]
}

Response:
{
    "currency": "$",
    "totalAmount": 2209.6,
    "preTaxAmount": 2009.2,
    "totalTax": 200.4,
    "itemDetailsBean": [
        {
            "itemDescription": "Saffola Vegetable Oil",
            "category": "Category C",
            "currencySymbol": "$",
            "price": 7.2,
            "finalPrice": 7.2,
            "totalTax": 0,
            "itemTaxDetailsBean": [
                {
                    "taxDescription": "Sales Tax",
                    "taxRate": 0,
                    "taxAmount": 0
                }
            ]
        },
        {
            "itemDescription": "Kellogs Original",
            "category": "Category B",
            "currencySymbol": "$",
            "price": 2,
            "finalPrice": 2.4,
            "totalTax": 0.4,
            "itemTaxDetailsBean": [
                {
                    "taxDescription": "Sales Tax",
                    "taxRate": 20,
                    "taxAmount": 0.4
                }
            ]
        },
        {
            "itemDescription": "MacBook Pro 15",
            "category": "Category A",
            "currencySymbol": "$",
            "price": 2000,
            "finalPrice": 2200,
            "totalTax": 200,
            "itemTaxDetailsBean": [
                {
                    "taxDescription": "Sales Tax",
                    "taxRate": 10,
                    "taxAmount": 200
                }
            ]
        }
    ]
}

