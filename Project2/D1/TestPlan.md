# Test Plan

**Author**: Team 10

## 1 Testing Strategy

### 1.1 Overall strategy

We will test each unit, test all kinds of combinations of units and test the entile system.
Jacob and Yanglei will perform such activities.

### 1.2 Test Selection

1. Mainly use manual tests.
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

TestCase| Purpose             | NecessarySteps: (1),(2),(3) represent different steps. |ExpectedResult                                   
--------| --------------------|--------------------------------------------------------|------------------------------------------------
1.      | TestAddCustomer1    | (1)MGR.enInfo(info) (2)MGR.add()                       | add a new customer with correct infomation     
2.      | TestAddCustomer2    | (1)MGR.add()                                           | WARNING:Must enter customer information!       
3.      | TestAddCustomer3    | (1)MGR.enInfo(null) (2)MGR.add()                       | WARNING:No information entered!                
4.      | TestAddCustomer4    | (1)MGR.enInfo(invalidInfo) (2)MGR.add()                | WARNING:Invalid customer information!
5.      | TestEditCustomer1   | (1)MGR.seCust(cusID) (2)MGR.edit(info) (3)MGR.save()   | update customer information
6.      | TestEditCustomer1   | (1)MGR.seCust(wrongID) (2)MGR.edit(info) (3)MGR.save() | WARNING:Customer not exist!
7.      | TestEditCustomer3   | (1)MGR.seCust(cusID) (2)MGR.edit(info)                 | WARNING:information not saved!
8.      | TestEditCustomer2   | (1)MGR.seCust(cusID) (2)MGR.edit(null) (3)MGR.save()   | WARNING:Nothing changed!
9.      | TestEditCustomer4   | (1)MGR.seCust(cusID) (2)MGR.save()                     | WARNING:Nothing changed!
10.     | TestEditCustomer5   | (1)MGR.save()                                          | WARNING:Nothing changed!
11.     | TestDeleteCustomer  | (1)MGR.seCust(cusID) (2)MGR.dele()                     | delete the selected customer's information
12.     | TestNewTransaction1 | (1)MGR.newT() (2)MGR.enInfo(info) (3)MGR.process()     | System creates a new transaction
13.     | TestNewTransaction2 | (1)MGR.newT() (2)MGR.enInfo(null) (3)MGR.process()     | WARNING:Invalid transaction information!
14.     | TestNewTransaction3 | (1)MGR.newT() (2)MGR.process()                         | WARNING:Please enter transaction information!
15.     | TestNewTransaction4 | (1)MGR.process()                                       | WARNING:Please first open a new transaction!
16.     | TestPastPurchases1  | (1)MGR.pastPur() (2)MGR.select(cusID)                  | system show a list of all past purchases of that custID
17.     | TestPastPurchases2  | (1)MGR.pastPur() (2)MGR.select(null)                   | WARNING:No customer is selected!
18.     | TestPastPurchases3  | (1)MGR.pastPur() (2)MGR.select(wrongID)                | WARNING:Selected customer not exist!
