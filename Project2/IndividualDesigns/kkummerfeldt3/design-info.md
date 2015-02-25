# **Design Info**

I spent a lot of time moving my design around, adding and deleting classes, and deciding where to put each method.  
Eventually I decided to keep the Customer class as the class with nearly all of the pertinent attributes and the  
Manager class as the one with most of the methods, as the manager is the one performing most of the actions.  

Most of my time was spent deciding what to do with Transaction and Email. My final design was to have Transaction  
as its own class, which has all of the pertinent information about each transaction, and that transaction will be  
saved into the aPastTransactions array via the Customer's AddTransaction() method. The Transaction's  
ProcessTransaction() method will automatically call the UpdateCustDiscount() method if  
mPurchaseAmt - mDiscountApplied is greater than $100. The Customer class will then have the mDiscountTotal and  
mAmtSpentCalYr attributes updated based on the transaction information and bGoldStatus will be switched to True  
if that amount is increased above $1000.  The altering of either bGoldStatus or mDiscountTotal will trigger the  
SendEmail() method which will let the customer know the status that has changed.  

The Manager and the Customer are related through the OpenNewTransaction() method and the Manager gets the card info  
from CreditCard via the ReadCard() method using the CardScanner.
