### Database Design : fileName - [DB_design](https://github.com/yuppry/salesys/blob/main/DB_design.drawio.png)

### Report fileName : [Daily_Sale_Report_yyyy-mm-dd.csv](https://github.com/yuppry/salesys/blob/main/Daily_Sale_Report_2024-12-03.csv)
### Docker Compose - MySQL
```bash
docker-compose up
```

### Postman Collection fileName : [SaleSys.postman_collection](https://github.com/yuppry/salesys/blob/main/SaleSys.postman_collection.json)

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