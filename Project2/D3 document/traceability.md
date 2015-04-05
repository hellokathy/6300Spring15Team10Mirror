# traceability information

**Author**: Team 10



	Use Case Description           | Design Elements  | Corresponding Code           | Tests                                
----------------------------------| -----------------|------------------------------|-------------------
1.Add New Customer                |  MainActivity; AddCustomer; DatabaseOperations; CustomerTableData     | protected void AddCustomer::onCreate(Bundle);  public void AddCustomer::submitNewCustomerPressed(View); | Test Case 1-5           
2.Edit an existing Customer       |  MainActivity; EditCustomer; SelectcustomerActivity; DatabaseOperations; CustomerTableData | protected void EditCustomer::onCreate(Bundle); protected void SelectcustomerActivity::onCreate(Bundle); public void SelectcustomerActivity::editPressed(View); private void SelectcustomerActivity::displayListView()  |   Test Case 9-13        
3.Delete en existing Customer| MainActivity; SelectcustomerActivity;DatabaseOperations; CustomerTableData  |protected void SelectcustomerActivity::onCreate(Bundle); public void  SelectcustomerActivity::deletePressed(View); private void SelectcustomerActivity::displayListView()  |Test Case 14-16              
4.Get a customer's information     | MainActivity; GetCustomerInfo; DatabaseOperations; CustomerTableData | - |Test Case 17-21 
5.Add a transaction                | MainActivity; AddTransaction; DatabaseOperations; CustomerTableData; TransactionTable; SelectCustomerForTransactionActivity | - | Test Case 22-28            
6.Display transactions for a customer| MainActivity; ViewTransactions; DatabaseOperations; CustomerTableData; TransactionTable; SelectCustomerForTransactionActivity | - | Test Case 29-32            


