# Test Plan

**Author**: Team 10

## 1 Testing Strategy

### 1.1 Overall strategy

We will test each unit, test all kinds of combinations of units and test the entile system.
Jacob and Yanglei will perform such activities.

### 1.2 Test Selection

1. We will mainly use manual tests.
2. In the black-box test, we will test each unit, test all kinds of combinations of units and test the entile system for all scenarios mentioned in the use cases.
3. In the white-box test, we will select our test cases to have as much coverage of branchs and conditions as possible. The tests should include different kinds of boundary values, different kinds of inputs that can cover as many branches and conditions as possible.
4. We will control the number of tests.


### 1.3 Adequacy Criterion

  No.   | criterions          | minimum accepted coverage    | Remark                                 
--------| --------------------|------------------------------|------------------------------------------------
1.      | units               | 100%                         | Tests should test all units in the code.
2.      | scenarios           | 100%                         | Tests should cover all possible scenarios described in the use cases.
3.      | branchs             | 85%                          | Tests should cover at least 85% of all the possible branches in the code.
4.      | conditions          | 90%                          | Tests should cover at least 90% of all the possible conditions in the code.


### 1.4 Bug Tracking

We will try to find as many bugs as possible from our tests and then deal with bugs and enhancement requests as a group, everyone should get involved.

### 1.5 Technology

We will use JUnit to build the tests.

## 2 Test Cases

TestCase| Purpose             | Test                                                   |ExpectedResult                      |ActualResult             
--------| --------------------|--------------------------------------------------------|------------------------------------|------------
1.      | TestAddCustomer1    | Click "Add Customer"                         | Display a new screen that people can enter information| same as expected   
2.      | TestAddCustomer2    | Click "Add Customer" -> Click "return"       | Display the home screen | same as expected    
3.      | TestAddCustomer3    | Click "Add Customer" -> Click "Submit"       | Notice: please enter information| same as expected 
4.      | TestAddCustomer4    | Click "Add Customer" -> Enter partial information -> Click "Submit"                       | Notice: some information is missing| same as expected              
5.      | TestAddCustomer5    | Click "Add Customer" -> Enter all information -> Click "Submit"                | Successfully added a customer to the database | same as expected 
6.      | TestViewCustomer1   | Click "View/Edit/Delete Customer"             | Display a new screen that shows all customer's information| same as expected 
7.      | TestViewCustomer2   | Click "View/Edit/Delete Customer" -> Enter a customer's last name          | Display a list of customer with the same last name| same as expected 
8.      | TestViewCustomer3   | Click "View/Edit/Delete Customer" -> Enter an invalid string or a last name that does not exist in the database          | Display nothing| same as expected 
9.      | TestEditCustomer1   | Click "View/Edit/Delete Customer" -> Click "Edit"      | Notice: please choose a customer | same as expected 
10.      | TestEditCustomer2   | Click "View/Edit/Delete Customer" -> Choose a customer -> Click "Edit"             | Display a new screen that people can edit this customer's information| same as expected 
11.      | TestEditCustomer3   | Click "View/Edit/Delete Customer" -> Choose a customer -> Click "Edit"-> Edit the information -> Click "Submit"             | Successfully edited a customer's information in the database| same as expected
12.     | TestEditCustomer4  | Click "View/Edit/Delete Customer" -> Choose a customer -> Click "Edit"-> Edit the information(removed the name) -> Click "Submit"   | Notice: Invalid name!| No notice
13.     | TestEditCustomer5  | Click "View/Edit/Delete Customer" -> Choose a customer -> Click "Edit"-> Click "Return"   | Return to the "View/Edit/Delete Customer" screen| No "Return" botton
14.      | TestDeleteCustomer1  | Click "View/Edit/Delete Customer" -> Click "Delete"   | Notice: please choose a customer| same as expected 
15.      | TestDeleteCustomer2  | Click "View/Edit/Delete Customer" -> Choose a customer -> Click "Delete" -> Confirm "Delete"  | Delete the customer in the database| same as expected 
16.      | TestDeleteCustomer3  | Click "View/Edit/Delete Customer" -> Choose a customer -> Click "Delete" -> Confirm "keep"  | Do not delete anything and return to the "View/Edit/Delete Customer" screen| same as expected 
17.      | TestGetCustomerInfo1  | Click "Get Customer Info"   | Display a new screen that people can enter customer ID| same as expected 
18.      | TestGetCustomerInfo2  | Click "Get Customer Info" ->  Click "Return"  | Return to the home screen| same as expected 
19.      | TestGetCustomerInfo3  | Click "Get Customer Info" -> enter a correct customer ID  | Display the customer's infomation| same as expected 
20.      | TestGetCustomerInfo4  | Click "Get Customer Info" -> enter an invalid customer ID  | Notice: customer not found| same as expected 
21.     | TestAddTransaction1 | Click "Add Transaction"   | | same as expected 
22.     | TestAddTransaction2 |    | | same as expected 
22.     | TestAddTransaction3 |    | | same as expected 