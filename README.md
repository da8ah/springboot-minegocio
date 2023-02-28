# Spring Boot MiNegocio App

## Requerimientos
- [x] Objetos enviados y retornados en formato JSON
- [x] Validación de Entrada de Datos
- [x] Buenas prácticas (Clean Code, SOLID)
- [x] TDD
- [x] Java 8+
- [x] SpringBoot
- [x] PostgreSQL
- [x] Clean Architecture
- [x] JUnit y Mockito

## Funcionalidades
- [x] Funcionalidad para buscar y obtener un listado de Clientes con una Dirección
- [x] Funcionalidad para crear un nuevo Cliente con la Dirección matriz
- [x] Funcionalidad para editar los datos del Cliente
- [x] Funcionalidad para eliminar un Cliente
- [x] Funcionalidad para registrar una nueva Dirección por Cliente
- [x] Funcionalidad para listar las direcciones adicionales del Cliente

## Dependencias
- spring-boot-starter-parent 3.0.3
- Java 19
- spring-boot-starter-actuator
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- postgresql 15.2
- spring-boot-starter-test
- spring-boot-maven-plugin

## Descripción
El sistema Mi negocio permite registrar de manera rápida una factura a consumidor final o cualquier otro cliente registrando los datos al momento de la facturación. Pero se necesita mejorar este proceso y es necesario tener una base de clientes por cada empresa que utiliza el sistema, de tal manera que al momento de facturar se pueda buscar un cliente por número de identificación o nombre.

<div align="center" style="width:100%;flex-direction:row">
<img style="width:40%" src="./doc/UseCaseDiagram.png" >
<img style="width:40%" src="./doc/ClassDiagram.png" >
</div>

## Resultado

### Crear

Registro de un nuevo Cliente con una Dirección
<div align="center">
<img style="width:70%" src="./doc/postman-registro-1.png" >
</div>
</br>

Error de creación: Cliente duplicado
<div align="center">
<img style="width:70%" src="./doc/postman-registro-2.png" >
</div>
</br>

Error de creación: Validación de Entrada de Datos
<div align="center">
<img style="width:70%" src="./doc/postman-registro-3.png" >
</div>

### Actualizar

Actualización de los datos de un Cliente
<div align="center">
<img style="width:70%" src="./doc/postman-actualizar-1.png" >
</div>

### Agregar Dirección

Actualización de un Cliente agregando una Dirección adicional
<div align="center">
<img style="width:70%" src="./doc/postman-direccion-1.png" >
</div>

### Buscar

Al ingresar números busca automáticamente coincidencias en los **Números de Identificación**
<div align="center">
<img style="width:70%" src="./doc/postman-filtrar-1.png" >
</div>
</br>

Al ingresar letras busca autómaticamente coincidencias en los **Nombres**
<div align="center">
<img style="width:70%" src="./doc/postman-filtrar-2.png" >
</div>
</br>

Se puede ingresar cualquier combinación de letras y retorna las coincidencias
<div align="center">
<img style="width:70%" src="./doc/postman-filtrar-3.png" >
</div>

### Listar Direcciones

Devuelve un Cliente con todas las Direcciones registradas
<div align="center">
<img style="width:70%" src="./doc/postman-filtrar-direcciones.png" >
</div>

### Eliminar

Elimina un Cliente registrado
<div align="center">
<img style="width:70%" src="./doc/postman-destruir-1.png" >
</div>

### Testing

Se valida el correcto funcionamiento de cada Caso de Uso
<div align="center">
<img style="width:40%" src="./doc/testing.png" >
</div>

</br>
</br>

## Versionamiento

(Tiber) **Febrero 2023 v1.1**
* Testing


(Tiber) **Febrero 2023 v1.0**

* Funcionalidades implementadas