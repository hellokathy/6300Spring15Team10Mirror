# Test Plan

**Author**: Team 10

## 1 Testing Strategy

### 1.1 Overall strategy

We will test each unit, test all kinds of combinations of units and test the entile system.
Jacob and Yanglei will perform such activities.

### 1.2 Test Selection

1. We mainly use manual tests.
2. We test each unit, test all kinds of combinations of units and test the entile system.
3. We select our test cases to have as much coverage of possible situations as possible. 
4. We will control the number of tests.


### 1.3 Adequacy Criterion

  No.   | criterions          | minimum accepted coverage    | Remark                                 
--------| --------------------|------------------------------|------------------------------------------------
1.      | units               | 100%                         | Tests should test all units in the code.
2.      | test cases          | 100%                         | Tests should cover all possible scenarios described in the use cases.


### 1.4 Bug Tracking

We will try to find as many bugs as possible from our tests and then deal with bugs and enhancement requests as a group, everyone should get involved.Same

### 1.5 Technology

We will use JUnit to build the tests.

## 2 Test Cases

Number| Purpose        | Test step 1          | Test step 2 | Test step 3 | Test step 4 | Test step 5 | ExpectedResult                     |ActualResult             
------|:--------------:|:--------------------:|:-----------:|:-----------:|:-----------:|:-----------:|:------------------------------------:|------------
1.    | TestAddCustomer1 | Click "Add Customer" | N/A            | N/A | N/A | N/A | Display a new screen that people can enter information| Same as expected   
2.    | TestAddCustomer2 | Click "Add Customer" | Click "return" | N/A | N/A | N/A | Display the home screen                               | Same as expected    
3.    | TestAddCustomer3 | Click "Add Customer" | Click "Submit" | N/A | N/A | N/A | Notice: please enter information                      | Same as expected 
4.    | TestAddCustomer4 | Click "Add Customer" | Enter partial information | Click "Submit" | N/A | N/A | Notice: some information is missing| Same as expected              
5.    | TestAddCustomer5 | Click "Add Customer" | Enter all information     | Click "Submit" | N/A | N/A | Successfully added a customer to the database | Same as expected 
6.    | TestViewCustomer1 | Click "View/Edit/Delete Customer" | N/A | N/A | N/A | N/A  | Display a new screen that shows all customer's information| Same as expected 
7.    | TestViewCustomer2 | Click "View/Edit/Delete Customer" | Enter a customer's last name          | N/A | N/A | N/A | Display a list of customer with the same last name| Same as expected 
8.    | TestViewCustomer3 | Click "View/Edit/Delete Customer" | Enter a last name not in the database | N/A | N/A | N/A | Display nothing | Same as expected 
9.    | TestEditCustomer1 | Click "View/Edit/Delete Customer" | Click "Edit"      |      N/A     | N/A | N/A | Notice: please choose a customer | same as expected 
10.   | TestEditCustomer2 | Click "View/Edit/Delete Customer" | Choose a customer | Click "Edit" | N/A | N/A | Display a new screen that people can edit this customer's information| same as expected 
11.   | TestEditCustomer3 | Click "View/Edit/Delete Customer" | Choose a customer | Click "Edit" | Edit the information | Click "Submit" | Successfully edited a customer's information in the database| same as expected
12.   | TestEditCustomer4 | Click "View/Edit/Delete Customer" | Choose a customer | Click "Edit" | Remove the name      | Click "Submit" | Notice: Invalid name!| Same as expected
13.   | TestEditCustomer5 | Click "View/Edit/Delete Customer" | Choose a customer | Click "Edit" | Click "Return"       |      N/A       | Return to the "View/Edit/Delete Customer" screen| Same as expected
14.   | TestDeleteCustomer1 | Click "View/Edit/Delete Customer" | Click "Delete"    |      N/A       |      N/A         | N/A | Notice: please choose a customer| same as expected 
15.   | TestDeleteCustomer2 | Click "View/Edit/Delete Customer" | Choose a customer | Click "Delete" | Confirm "Delete" | N/A | Delete the customer in the database| same as expected 
16.   | TestDeleteCustomer3 | Click "View/Edit/Delete Customer" | Choose a customer | Click "Delete" | Confirm "keep"   | N/A | Do not delete anything and return to the "View/Edit/Delete Customer" screen| same as expected 
17.   | TestGetCustomerInfo1 | Click "Get Customer Info" |         N/A                  | N/A | N/A | N/A | Display a new screen that people can enter customer ID| same as expected 
18.   | TestGetCustomerInfo2 | Click "Get Customer Info" | Click "Return"               | N/A | N/A | N/A | Return to the home screen        | same as expected 
19.   | TestGetCustomerInfo3 | Click "Get Customer Info" | Enter a correct customer ID  | Click "Submit"  | N/A | N/A | Display the customer's infomation| same as expected 
20.   | TestGetCustomerInfo4 | Click "Get Customer Info" | Enter an invalid customer ID | Click "Submit"  | N/A | N/A | Notice: customer not found       | same as expected 
21.   | TestGetCustomerInfo5 | Start with an empty database | Click "Get Customer Info" | N/A | N/A | N/A | Notice: empty database           | same as expected 
22.   | TestAddTransaction1 | Click "Add Transaction" |      N/A       | N/A | N/A | N/A | Display a new screen with all customer's information | same as expected 
23.   | TestAddTransaction2 | Click "Add Transaction" | Click "Select" | N/A | N/A | N/A | Notice: please choose a customer                     | same as expected 
24.   | TestAddTransaction3 | Click "Add Transaction" | Click "Return" | N/A | N/A | N/A | Display the home screen                              | same as expected 
25.   | TestAddTransaction4 | Click "Add Transaction" | Choose a customer | Click "Select" |      N/A       | N/A |  Display a new screen that allows p5ople to add transaction information| same as expected 
26.   | TestAddTransaction5 | Click "Add Transaction" | Choose a customer | Click "Select" | Click "Return" | N/A |  Return to the screen with all customer's informatio| same as expected 
27.   | TestAddTransaction6 | Click "Add Transaction" | Choose a customer | Click "Select" | Click "Submit" | N/A |  Notice: please enter a amount| same as expected 
28.   | TestAddTransaction7 | Click "Add Transaction" | Choose a customer | Click "Select" | Enter amount   | Click "Submit"  |  Add the transaction to database and return to the home screen | same as expected 
29.   | TestViewTransactions1 | Click "View Transactions" |        N/A     | N/A | N/A | N/A | Display a new screen with all customer's information | same as expected 
30.   | TestViewTransactions2 | Click "View Transactions" | Click "Select" | N/A | N/A | N/A | Notice: please choose a customer | same as expected 
31.   | TestViewTransactions3 | Click "View Transactions" | Choose a customer | Click "Select" |     N/A      | N/A | Display a new screen that shows all transaction information of this customer| same as expected 
32.   | TestViewTransactions3 | Click "View Transactions" | Choose a customer | Click "Select" | Click "Home" | N/A | Display a new screen that shows all transaction information of this customer and then display the home screen| same as expected 