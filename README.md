Projeto de Estudo (reciclagem) Javaspring boot

- REST API
- Sem front-end
- Configurado para MySql
Por esse motivo Models possuem: 
    
    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();
    

Requests Exemplos:
- Product
{
    "name": "Televisão",
    "value": 1120
}

- Client
{
    "name": "Tom Cruise",
    "docNumber": 23423,
    "birthday": "2000-11-25"
}

- Order
 {
  "client_id": "<String UUID>",
  "number": 1,
  "orderItems": [
    { "product_id": "<String UUID>", "quantity": 1 }
  ]
}
