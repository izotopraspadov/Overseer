### *Development based on this specification*:

The application should be a `web service` for managing all the necessary data representing the resources of a particular organization:

1. Payment data.
2. Lists of employees and customers.
3. Lists of orders.
4. Lists of tasks, sets of which make up orders.
5. History of salaries of each of the employees.
6. Information about the planned and actual time for a particular order.
7. Lists of contact persons of customers.
8. Information about a phone numbers and e-mails of employees and contact persons.

Full description of all data that needs to be stored in the database:

1. **_Companies_** `Each company has required fields:`
     * Title
     * Region (from the drop-down list of Russian regions)
     * ITN
     * The address
     * Contact person (it should be possible to create up to 5 contacts to one company, each contact can have up to 3 phone numbers and postal addresses)
     * Degree of reliability (3 options are possible - Low, Medium, High)
     * WhatsApp Group Name
     * Company type (Our, Customer, Others)
     
2. **_Payments by objects_** `Each payment has obligatory fields:`
    * The date
    * Company name (from the list of companies)
    * The name of our company (from the list of companies with "Our" types)
    * Name of the property
    * Operation (from -9.99 billion rub to 9.99 billion rub)
    * Bank transfer (yes / no)
    * An optional field: Comment
 
3. **_Employees_** `Each employee has required fields:`
    * Full name
    * Region (from the drop-down list of Russian regions)
    * Phone number (you can add up to 3 phones)
    * E-mail address (up to 3 postal addresses can be entered)
    * An optional field: Address

4. **_Employee payments_** `Each payment has required fields:`
    * The date
    * Full name (from the list of employees)
    * Counterparty (either a company is selected from the list of companies, or an employee from the list of employees)
    * Operation (from -9.99 billion rub to 9.99 billion rub)
    * Type of payment (Cash / Bank transfer / Charge / Write-off)
    * An optional field: Comment

5. **_Salaries_** `Each record has required fields:`
    * Full name (from the list of employees)
    * The date of the beginning
    * Expiration date
    * Salary

6. **_Objects_** `Each object has required fields:`
    * Customer (from the list of companies)
    * Title
    * Payment by bank transfer (yes / no)
    * Need a contract (yes / no)
    * There is a contract (yes / no)
    * Planned start date
    * Actual start date
    * Planned completion date
    * Actual completion date
    * Amount
    * Type (Project / Individual Documentation / Estimate / Legal services)
    * Payment order (two or three numbers, the amount of which is 100%, for example 40-60, or 30-40-30 or 100)
    * Number of lines (only if type = estimate)
    * Group (from the drop-down list of groups, at the moment there is 001-005, then there will be 006, 007, etc.)
    * Account manager (from the list of employees)
    * In operation (yes / no)

7. **_Planned time_** `Each record has required fields:`
    * Object name (from the list of objects)
    * Full name (from the list of employees)
    * Planned time

8. **_Actual time_** `Each record has required fields:`
    * Object name (from the list of objects)
    * Full name (from the list of employees)
    * The date
    * Actual time
    * Accounting time

9. **_Tasks_** `Each task has required fields:`
    * Object name (from the list of objects)
    * A task
    * A responsible person (from the list of employees)
    * Date of completion
    * Result (Completed / Not Completed / Partially Completed)
    * An optional field: Comment of the responsible person
    * An optional field: To Team Lead (select a customer or employee and select an email, you can select up to 3 emails)
    * An optional field: To the Manager (select a customer or employee and select an email, you can select up to 3 emails)

_App user roles:_

The application needs to implement the roles of "Standard User" and Administrator. Both the one and the other in the system are employees. The administrator has all the rights that a regular user has, but in addition to everything else, he has the ability to edit data about other employees.

_Description of application functionality:_

  * If it is not specified that the function is available only for the administrator, then it is available to all employees (users)

_The main items on the left in the menu are:_
* **_Personal information:_**
    * Must contain personal information (full name), phone number, mail, etc.
    * Ability to change password
    * Login is assigned by the administrator
* **_Companies:_**
    * Must contain the entire list
    * Can be filtered by:
      * Region
      * Title
      * degree of reliability
      * company type
    * Searchable by:
      * ITN
      * The address
      * Title
      * Contact person
* **_Employees:_**
    * Must contain the entire list
    * Can be filtered by:
      * Login
    * Searchable by:
      * Region
      * Address
      * Full name
    * Should be able to go deeper into the employee and see all employee payments
    * The administrator should be able to correct the data on the employee himself: login, phone number, email, salary
* **_Objects:_**
   * Must contain the entire list
       * Can be filtered by:
         * Title
         * To the customer
         * Payment must be
         * Payment by bank transfer
         * A type
         * Group
         * Need a contract
         * There is a contract
         * Planned start date
         * Actual start date
         * Planned completion date
         * Actual completion date
         * Amount
         * Payment order
         * Number of lines
         * Account Manager
         * In work
       * It is possible to go to the list of payments for a specific object, as well as in the planned, actual time for the object and the list of tasks for the object.
   * **_Finance:_**
       * You can select a date and all payments for employees and objects are displayed with a division by this date
   * **_Exit_**