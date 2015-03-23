# traceability information

**Author**: Team 10



	Use Case Description               | Design Elements  | Corresponding Code           | Tests                                
-----------------------------------| -----------------|------------------------------|-------------------
1.User adds a new customer         |  AddCustomer     | protected void AddCustomer::onCreate(Bundle);  public void AddCustomer::submitNewCustomerPressed(View); |addCustomerTest::onCreateTest0X;  addCustomerTest::submitNewCustomerPressedTest0X;             
2.User edits an existing Customer  |  EditCustomer; SelectcustomerActivity | protected void EditCustomer::onCreate(Bundle); protected void SelectcustomerActivity::onCreate(Bundle); public void SelectcustomerActivity::EditPressed(View); private void SelectcustomerActivity::displayListView()  |editCustomerTest::onCreateTest0X; SelectcustomerActivity::onCreateTest0X; SelectcustomerActivity::EditPressedTest0X;           
3.User deletes en existing Customer|  SelectcustomerActivity    |protected void SelectcustomerActivity::onCreate(Bundle); public void  SelectcustomerActivity::DeletePressed(View); private void SelectcustomerActivity::displayListView()  |SelectcustomerActivity::DeletePressedTest0X;                
4.User opens a new transaction     |                
5.User gets past purchases         |             


