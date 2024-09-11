# GFT Test Application

## Descripción

Esta es una aplicación desarrollada con Spring Boot. Proporciona una API REST con un endpoint `POST /products/price_info`, el cual requiere recibir la información de `productId`, `brandId` y una fecha (`requestedDate`). Este endpoint devuelve la información de la tarifa aplicable correspondiente a esos parámetros.

## Estructura del Código

La estructura del código está organizada en tres carpetas principales:

- **adapters**: Contiene el código de la API y la lógica para interactuar con la base de datos.
- **application**: Incluye la lógica que conecta los puertos de entrada (API) y salida (repositorio).
- **domain**: Contiene las interfaces y el modelo `Product`.

## Pruebas

Además de las pruebas unitarias, se incluyen pruebas de integración que cubren los 5 escenarios requeridos en la prueba, así como los escenarios de "not found" y "bad request". Para estas pruebas, se inicializa la base de datos y se realizan llamadas a la API.

## Uso

La base de datos es volátil y se crea cada vez que se inicia la aplicación. Para utilizar la aplicación, es necesario compilar y ejecutar la aplicación, y luego poblar la tabla (que se crea automáticamente a partir de la entidad `Prices`).

1. Accede a la página [H2 Console](http://localhost:8080/h2-console).
2. Inicia sesión con las credenciales:
   - **Username**: `sa`
   - **Password**: `password`

   Estas credenciales están configuradas en el archivo `application.properties`.

3. Ejecuta la consulta SQL para poblar la tabla. La consulta correspondiente se encuentra en el archivo `queries.sql` dentro de la carpeta `resources`.

4. Verifica que los datos se hayan guardado correctamente ejecutando:
   ```sql
   SELECT * FROM prices;
   ```
Deberías ver 4 filas de datos.

5. Utilizando Postman (o cualquier otra herramienta de tu preferencia), realiza una petición GET al endpoint: `http://localhost:8080/products/price_info` con atributos `productId`, `brandId` y `requestedDate`. La url de ejemplo en este caso sería: `http://localhost:8080/products/price_info?productId=35455&brandId=1&requestedDate=2020-06-15T10:00:00`.

6. Debería recibir la siguiente respuesta
```
{
    "productId": "35455",
    "brandId": "1",
    "priceList": 3,
    "startDate": "2020-06-15T00:00:00",
    "endDate": "2020-06-15T11:00:00",
    "price": 30.5
}
```

