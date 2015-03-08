# Vision Document

**Author**: Team 10

## 1 Introduction

The product to be developed is an android application, PReMS, which acts as a payment and rewards management system for mobile stalls.

## 2 Business Needs/Requirements

As time goes on, less and less people carry cash.  Instead, they simply buy everything with a credit card.  Because of this, companies need to accept credit cards or risk losing business.
Brad and Janet are two farmers who run a stall that sells produce.  They are aware of the falling use of cash and to keep business strong, they would like the ability to accept credit cards from their customers.
As time goes on, it's also becoming clear that customer reward programs can increase customer loyalty as well as sales for any given business.
To this end, Brad and Janet would also like to reward their most loyal customers in a couple of ways.  When a customer makes a large purchase, they will receive a credit that they can apply toward a future purchase.
Additionally, when a customer spends enough money at the stall over the course of a year, that customer will be guaranteed a flat discount on any purchase at the stall for the rest of their life (they achieve "gold status").
To ensure customers know about the discounts and credits they are receiving, emails will be sent out notifying customers when they qualify for rewards.
To make their lives easier, Brad and Janet would like the above features to be managed by a rewards and management system for the Android OS.
Since Brad and Janet anticipate new customers, they would like the ability to add a customer into the management system.  And, in case a customer's information changes, they would like the ability to edit an existing customer's information.
Finally, in case of a customer question or for their own information, Brad and Janet need the ability to display any customer's transaction history including the date, amount, and discounts applied for each transaction.
By implementing these features, Brad and Janet are confident their business will succeed.

## 3 Product / Solution Overview

-PReMS needs to keep track of every customer and their information.  At minimum, their name, zip code, email, and a unique ID to differentiate between customers with the same name.
-We need to keep track of each customer's yearly expenses at the stall to see if they qualify for the gold status.  
-We also need to track whether or not a given customer has gold status or not and award it to them when they qualify.
-We also need to keep track of a customer's credits, both gained and used.
-Speaking of credits, we can determine when a customer gains and uses credits using another major player in our system, the Transaction.
-Each customer can have many transactions and so we must keep track of these.  
-Each time a customer makes a transaction, the date and initial purchase price will be stored.  
-If a customer has gold status and/or uses credits, the appropriate discount will be applied and a record of that discount will be stored.  
-The final purchase price of a transaction will then be stored.  
-If the final purchase price is sufficient, credit will be awarded to the customer.
-When a customer achieves gold status or gains credit, an email utility will be used to send an email to that customer.
-Each customer can have many credit cards, and as such we must keep track of which cards belong to which customers.
-The final player in this system is the stall manager.   In this case, Brad and Janet will act as the stall manager.  
-Using the system, the manager will be able to add a new customer, edit an existing customer, and display transaction history for a given customer.
-Adding a customer will involve entering the information of the customer manually which the system will then store.
-To edit a customer, the system will allow the manager to choose the customer they want to edit and prompt the manager to enter new information where it's needed.  The system will then store this new information.
-Displaying transactions for a given customer will not be difficult for the system as it is already storing all the transactions, so it will simply the information to the manager.

## 4 Major Features (Optional)
N/A

## 5 Scope and Limitations

PReMS will focus on allowing customers to pay with credit cards and automatically be rewarded when appropriate.  As such, the system does not support the following:

-Customers who pay with cash
-Deleting a customer
-Displaying all customers
-Manually rewarding a customer
-Editing transactions
