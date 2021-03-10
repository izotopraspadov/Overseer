## Orders

#### **Get order**

Retrieve a single order.

    GET /orders/:id
    
**Parameters**

| Param | type    | Description
| :---  | :---    | :-----------
|`id`   | Integer | The order’s ID. Required.
 
**Response**

    HTTP/1.1 200 
    Content-Type: application/json;charset=UTF-8

**Body**

```
{
   "id": 100103,
   "company":    {
      "id": 100013,
      "title": "Первая Компания",
      "region":       {
         "id": 100000,
         "title": "Алтайский край"
      },
      "itn": "000000000000",
      "address": "Никакая ул. 1",
      "contactPersons": null,
      "reliabilityType": "LOW",
      "chatGroupName": "Первая",
      "companyType": "OUR"
   },
   "title": "First Project",
   "cashless": false,
   "contractIsNeed": false,
   "contractExists": true,
   "plannedStartDate": "2019-09-01",
   "actualStartDate": "2019-09-10",
   "plannedEndDate": "2019-10-01",
   "sum": 100000,
   "expectedPayment": 17000,
   "paymentFormat": "100",
   "group":    {
      "id": 100098,
      "title": "001",
      "types": [      {
         "id": 100094,
         "title": "Проект"
      }],
      "comment": "Need to get the result! Now!"
   },
   "manager":    {
      "id": 100024,
      "fullName": "Романов Роман Романович",
      "region":       {
         "id": 100000,
         "title": "Алтайский край"
      },
      "login": "user",
      "password": "{noop}user",
      "address": "Заставская ул. 6",
      "roles": ["ROLE_USER"],
      "payments": null,
      "salary": null,
      "phones": null,
      "emails": null
   },
   "underway": true,
   "orderType":    {
      "id": 100094,
      "title": "Проект"
   },
   "actualTime": null,
   "plannedTime": null,
   "payments": null,
   "tasks": null
}
```

#### **List orders**

Get a single page from the list of all orders.

    GET /orders
    
**Parameters**

| Param | type | Description
| :---  | :---  | :-----------
|`page`              | Integer   | Page number to retrieve. (Optional; default: 1)
|`company_id`        | Integer   | The customer's company ID. (Optional; default: null)
|`cashless`          | Boolean   | Cashless payment. (Optional; default: null)
|`group_id`          | Integer   | The group’s ID. (Optional; default: null)
|`contract_is_need`  | Boolean   | Need a contract. (Optional; default: null)
|`contract_exists`   | Boolean   | There is a contract. (Optional; default: null)
|`planned_start_date`| LocalDate | The order’s planned start date. (Optional; default: null)
|`actual_start_date` | LocalDate | The order’s actual start date. (Optional; default: null)
|`planned_end_date`  | LocalDate | The order’s planned end date. (Optional; default: null)
|`actual_end_date`   | LocalDate | The order’s actual end date. (Optional; default: null)
|`current_sum`       | BigDecimal| The order’s current sum. (Optional; default: null)
|`manager_id`        | Integer   | The order manager's ID. (Optional; default: null)
|`underway`          | Boolean   | The order is underway. (Optional; default: null)
|`expected_payment`  | BigDecimal| The order’s expected payment. (Optional; default: null)
|`lines`             | Integer   | Number of lines (only if type = estimate). (Optional; default: null)
|`format`            | String    | Payment order (Valid values: `40-60`, `30-40-30`, `100`). Possible by partial coincidence. (Optional; default: null)
|`title`             | String    | The order’s title. Possible by partial coincidence. (Optional; default: null)

**Response**

    HTTP/1.1 200 
    Content-Type: application/json;charset=UTF-8

**Example**

    /orders?page=1&cashless=true&current_sum=10000.00

**Body**

```
[{
   "id": 100104,
   "company":    {
      "id": 100013,
      "title": "Первая Компания",
      "region":       {
         "id": 100000,
         "title": "Алтайский край"
      },
      "itn": "000000000000",
      "address": "Никакая ул. 1",
      "contactPersons": null,
      "reliabilityType": "LOW",
      "chatGroupName": "Первая",
      "companyType": "OUR"
   },
   "title": "First  Estimate",
   "cashless": true,
   "contractIsNeed": true,
   "contractExists": false,
   "plannedStartDate": "2019-09-03",
   "actualStartDate": "2019-09-03",
   "plannedEndDate": "2019-10-05",
   "sum": 10000,
   "expectedPayment": 15000,
   "paymentFormat": "30-70",
   "numberOfLines": 50,
   "group":    {
      "id": 100100,
      "title": "003",
      "types": [      {
         "id": 100096,
         "title": "Смета"
      }]
   },
   "manager":    {
      "id": 100025,
      "fullName": "Степанов Степан Степанович",
      "region":       {
         "id": 100000,
         "title": "Алтайский край"
      },
      "login": "stepanov",
      "password": "{noop}user2",
      "address": "Четвёртый пр. 10",
      "roles": ["ROLE_USER"],
      "payments": null,
      "salary": null,
      "phones": null,
      "emails": null
   },
   "underway": true,
   "orderType":    {
      "id": 100096,
      "title": "Смета"
   },
   "actualTime": null,
   "plannedTime": null,
   "payments": null,
   "tasks": null
}]
```

#### **Update order**

Update the order by adding a new version to the body

    PUT /orders/:id
    
**Parameters**

| Param | type    | Description
| :---  | :---    | :-----------
|`id`   | Integer | The order’s ID. Required.

**Body**
```
{
   "id": 100104,
   "company":    {
      "id": 100013,
      "title": "Первая Компания",
      "region":       {
         "id": 100000,
         "title": "Алтайский край"
      },
      "itn": "000000000000",
      "address": "Никакая ул. 1",
      "contactPersons": null,
      "reliabilityType": "LOW",
      "chatGroupName": "Первая",
      "companyType": "OUR"
   },
   "title": "First  Estimate",
   "cashless": true,
   "contractIsNeed": true,
   "contractExists": false,
   "plannedStartDate": "2019-09-03",
   "actualStartDate": "2019-09-03",
   "plannedEndDate": "2019-10-05",
   "sum": 10000,
   "expectedPayment": 15000,
   "paymentFormat": "30-70",
   "numberOfLines": 50,
   "group":    {
      "id": 100100,
      "title": "003",
      "types": [      {
         "id": 100096,
         "title": "Смета"
      }]
   },
   "manager":    {
      "id": 100025,
      "fullName": "Степанов Степан Степанович",
      "region":       {
         "id": 100000,
         "title": "Алтайский край"
      },
      "login": "stepanov",
      "password": "{noop}user2",
      "address": "Четвёртый пр. 10",
      "roles": ["ROLE_USER"],
      "payments": null,
      "salary": null,
      "phones": null,
      "emails": null
   },
   "underway": true,
   "orderType":    {
      "id": 100096,
      "title": "Смета"
   },
   "actualTime": null,
   "plannedTime": null,
   "payments": null,
   "tasks": null
}
```
**Response**

    HTTP/1.1 204 

#### **Create order**

Create the order by adding order to the body

    POST /order
    
**Body**
```
{
   "id": null,
   "company":    {
      "id": 100013,
      "title": "Первая Компания",
      "region":       {
         "id": 100000,
         "title": "Алтайский край"
      },
      "itn": "000000000000",
      "address": "Никакая ул. 1",
      "contactPersons": null,
      "reliabilityType": "LOW",
      "chatGroupName": "Первая",
      "companyType": "OUR"
   },
   "title": "New order",
   "cashless": true,
   "contractIsNeed": true,
   "contractExists": false,
   "plannedStartDate": "2019-09-03",
   "actualStartDate": "2019-09-03",
   "plannedEndDate": "2019-10-05",
   "sum": 10000,
   "expectedPayment": 15000,
   "paymentFormat": "30-70",
   "numberOfLines": 500,
   "group":    {
      "id": 100100,
      "title": "003",
      "types": [      {
         "id": 100096,
         "title": "Смета"
      }]
   },
   "manager":    {
      "id": 100025,
      "fullName": "Степанов Степан Степанович",
      "region":       {
         "id": 100000,
         "title": "Алтайский край"
      },
      "login": "stepanov",
      "password": "{noop}user2",
      "address": "Четвёртый пр. 10",
      "roles": ["ROLE_USER"],
      "payments": null,
      "salary": null,
      "phones": null,
      "emails": null
   },
   "underway": false,
   "orderType":    {
      "id": 100096,
      "title": "Смета"
   },
   "actualTime": null,
   "plannedTime": null,
   "payments": null,
   "tasks": null
}  
```
**Response**

    HTTP/1.1 201
    Content-Type: application/json;charset=UTF-8

**Body**
```
{
   "id": 100132,
   "company":    {
      "id": 100013,
      "title": "Первая Компания",
      "region":       {
         "id": 100000,
         "title": "Алтайский край"
      },
      "itn": "000000000000",
      "address": "Никакая ул. 1",
      "contactPersons": null,
      "reliabilityType": "LOW",
      "chatGroupName": "Первая",
      "companyType": "OUR"
   },
   "title": "New order",
   "cashless": true,
   "contractIsNeed": true,
   "contractExists": false,
   "plannedStartDate": "2019-09-03",
   "actualStartDate": "2019-09-03",
   "plannedEndDate": "2019-10-05",
   "sum": 10000,
   "expectedPayment": 15000,
   "paymentFormat": "30-70",
   "numberOfLines": 500,
   "group":    {
      "id": 100100,
      "title": "003",
      "types": [      {
         "id": 100096,
         "title": "Смета"
      }]
   },
   "manager":    {
      "id": 100025,
      "fullName": "Степанов Степан Степанович",
      "region":       {
         "id": 100000,
         "title": "Алтайский край"
      },
      "login": "stepanov",
      "password": "{noop}user2",
      "address": "Четвёртый пр. 10",
      "roles": ["ROLE_USER"],
      "payments": null,
      "salary": null,
      "phones": null,
      "emails": null
   },
   "underway": false,
   "orderType":    {
      "id": 100096,
      "title": "Смета"
   },
   "actualTime": null,
   "plannedTime": null,
   "payments": null,
   "tasks": null
}  
```

#### **Delete order**

    DELETE /orders/:id
    
**Parameters**

| Param | type    | Description
| :---  | :---    | :-----------
|`id`   | Integer | The order’s ID. Required.

**Response**

    HTTP/1.1 204 