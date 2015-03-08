# Test Plan

**Author**: Team 10

## 1 Testing Strategy

### 1.1 Overall strategy

We will test each unit(Class), test all kinds of combinations of units and test the entile system.
Jacob and Yanglei will perform such activities.

### 1.2 Test Selection

We will test all units as well as the entile system.
We will select our test cases to have as much coverage of branchs and conditions as possible, i.e., including different kinds of boundary values, including different kinds of inputs that can go through as many branches and conditions as possible and also confine the tests to a reasonable small number. 


### 1.3 Adequacy Criterion

coverage of the scenarios defined in the use cases 90%
branch coverage 85%
condition coverage 90%
units coverage 100%

### 1.4 Bug Tracking

We will deal with bugs and enhancement requests as a group, everyone should get involved.

### 1.5 Technology

We will use JUnit to build the tests.

## 2 Test Cases
 
TestCase# | Purpose            | NecessarySteps                                | ExpectedResult         
| ActualResult()       | Pass/Fail
1.         | TestAddCustomer1   | MGR.enInfo(info)->MGR.add()                   | add a new customer with correct infomation
2.         | TestAddCustomer2   | MGR.add()                                     | WARNING:Must enter customer information!
3.         | TestAddCustomer3   | MGR.enInfo(null)->MGR.add()                   | WARNING:No information entered!
4.         | TestAddCustomer4   | MGR.enInfo(invalidInfo)->MGR.add()            | WARNING:Invalid customer information!
5.         | TestEditCustomer1  | MGR.seCust(cusID)->MGR.edit(info)->MGR.save() | update customer information
6.         | TestEditCustomer1  | MGR.seCust(wrongID)->MGR.edit(info)->MGR.save() | WARNING:Customer not exist!
7.         | TestEditCustomer3  | MGR.seCust(cusID)->MGR.edit(info)             | WARNING:information not saved!
8.         | TestEditCustomer2  | MGR.seCust(cusID)->MGR.edit(null)->MGR.save() | WARNING:Nothing changed!
9.         | TestEditCustomer4  | MGR.seCust(cusID)->MGR.save()                 | WARNING:Nothing changed!
10.        | TestEditCustomer5  | MGR.save()                                    | WARNING:Nothing changed!
11.        | TestDeleteCustomer | MGR.seCust(cusID)->MGR.dele()                 | delete the selected customer's information
12.        | TestNewTransaction1| MGR.newT()->MGR.enInfo(info)->MGR.process()   | System creates a new transaction
13.        | TestNewTransaction2| MGR.newT()->MGR.enInfo(null)->MGR.process()   | WARNING:Invalid transaction information!
14.        | TestNewTransaction3| MGR.newT()->MGR.process()                     | WARNING:Please enter transaction information!
15.        | TestNewTransaction4| MGR.process()                                 | WARNING:Please first open a new transaction!
16.        | TestPastPurchases1 | MGR.pastPur()->MGR.select(cusID)              | system show a list of all past purchases of that custID
17.        | TestPastPurchases2 | MGR.pastPur()->MGR.select(null)               | WARNING:No customer is selected!
18.        | TestPastPurchases3 | MGR.pastPur()->MGR.select(wrongID)            | WARNING:Selected customer not exist!
