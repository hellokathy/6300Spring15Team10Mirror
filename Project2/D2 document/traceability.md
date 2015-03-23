# traceability information

**Author**: Team 10



	Use Case Description               | Design Elements  | Corresponding Code           | Tests                                
-----------------------------------| -----------------|------------------------------|-------------------
1.User adds a new customer         |  AddCustomer     | protected void AddCustomer::onCreate(Bundle);  public void AddCustomer::submitNewCustomerPressed(View); |AddCustomerTest::onCreateTest0X;  AddCustomerTest::submitNewCustomerPressedTest0X;             
2.User edits an existing Customer  |  EditCustomer; SelectcustomerActivity | protected void EditCustomer::onCreate(Bundle); protected void SelectcustomerActivity::onCreate(Bundle); public void SelectcustomerActivity::editPressed(View); private void SelectcustomerActivity::displayListView()  |EditCustomerTest::onCreateTest0X; SelectcustomerActivity::onCreateTest0X; SelectcustomerActivity::editPressedTest0X;           
3.User deletes en existing Customer|  SelectcustomerActivity    |protected void SelectcustomerActivity::onCreate(Bundle); public void  SelectcustomerActivity::deletePressed(View); private void SelectcustomerActivity::displayListView()  |SelectcustomerActivity::deletePressedTest0X;                
4.User opens a new transaction     |                
5.User gets past purchases         |             


