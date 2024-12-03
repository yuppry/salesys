### Database Design : fileName - DB_design

### Report fileName : Daily_Sale_Report_yyyy-mm-dd.csv

### Docker Compose - MySQL
```bash
docker-compose up
```

### Postman Collection fileName : SaleSys.postman_collection

### Request body for [Post] addNewCustomer
```Json
{
    "firstName":"test",
    "lastName":"test",
    "email":"test@gmail.com",
    "phoneNumber":"1234567890",
    "address":"bangkok"
}
```
### Request body for [Post] addNewProduct
```Json
{
  "productName":"Monchichi",
  "category":"toys",
  "price": 100.5,
  "quantity": 10
}
```
### Request body for [Post] addNewSale
```Json
{
  "customerId": 2,
  "saleProductRequest": [
    {
      "productId": 1,
      "quantity": 6
    },
    {
      "productId": 2,
      "quantity": 2
    }
  ]
}
```