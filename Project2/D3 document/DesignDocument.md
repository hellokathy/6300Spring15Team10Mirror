# Design Document

**Author**: Team 10

## 1 Design Considerations

One big issue needed to finish design is information about the utility software we will be using for (1) credit-card scanning, (2) payment  
processing, and (3) email management capabilities. It would also be good to know what the primary devise the program will be run on (small  
tablet, large tablet etc.). We also need information about the GUI and how they want the design to look. Lastly, will other people and companies  
use the software? This would change the design to allow different users to customize. Lastly, will the data be stored in the cloud? Or will all  
the customer information be stored in the app? How will customer’s information and privacy be secured? Will this be left to the third party  
software provided with Android Utility?


### 1.1 Assumptions

We assume there will only be one user at a time but an issue might arise if they experience growth and need to have two registers with multiple  
accesses to same software. Another big assumption is cost benefit. This software production needs to be justified and should show the value  
added to the company. Users know how to run and install Android Apps. System will use API 19 with minimum of Android 16. System will be written and displayed in English.



### 1.2 Constraints

Common constraints are budget and time. These shouldn’t be a big issue for this project but will affect the quality of the GUI and additional  
functionality. The time constraints are subject to the deadlines of the class and the capacity of our group. Lasty, the system will be constrained to run only on Android devices.

### 1.3 System Environment

The program will likely run on a tablet at a kiosk. It will need to be wifi enabled or have data package to upload data to central storage,  
process credit cards, and send email. It will be an Android application programmed in Java and available to run on any Android device. 

## 2 Architectural Design
We have an “even-driven ” architecture. Buttons will drive the methods to “add customer”, “view customer transactions”, etc. Transactions will be the other event triggers that: send emails, apply discounts, update reward status, etc.

### Component Diagram

![Component Diagram](component.PNG "on hover description")

This diagram shows the components of the system and how they interact. The manager is the main user at the center, and uses the intercase UI.  
The customer database is constant and persistent which the manager takes advantage of along with the card reader to process the transactions.

### Deployment Diagram

![Deployment Diagram](deployment.PNG "on hover description")

This diagram shows each physical device and the interactions they have.  The main system, with the manager and transaction, use the DBServer device  
which creates the persistence infrastructure. They also use the main Android OS to run the system. The processing service is also used to process  
payments.

## 3 Low-Level Design

### Class Diagram

![Class Diagram](teamdesign.PNG "on hover description")

All of our design decisions were made deliberately and with purpose. We chose to use attributes to represent the different types  
of rewards rather than separate classes. We feel this is cleaner and reduces clutter in our diagram. We’ve also decided to add  
prefixes to our variable names indicating what type they are. This makes the diagram easier to read and makes it clearer which variables are of  
which type. Additionally, we’re confident in our choice of classes – Manger, Transaction, CreditCard, and Customer. We feel these are the main  
players in the system and all of them have enough importance to justify their inclusion. We feel that every attribute we included in the diagram  
has its place and serves a distinct purpose, and we feel the same about the methods we chose to include.

## 4 User Interface Design

The user interface will be comprised of a simple form for each page. For the main page there will be four buttons: Add Customer, View/Edit/Delete Customer, Get Customer Info, Add Transaction, and View Transactions. Add Customer and View/Edit/Delete Customer will have very similar layouts, simple labels  
for text boxes describing the needed input (First and Last Name, email, etc.) with Edit/Delete Customer having an added page containing a list  
of all existing customers to choose and buttons to choose "Edit", "Delete", or "Home". Add Transaction will be a simple form with the required  
information for the transaction and a button for Processing.  View Transactions will be a screen will all existing customers to select from, leading  
to a screen with a list of all of the selected customer's past purchases.  This screen will also have a "Home" button to go back to the main page. There is also option to type in last name of customer to refine list of transactions which is also the same as View/Edit/Delete Customer view.

