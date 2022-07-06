# AirportService

Aplicación que actúa como servicio web que realiza operaciones CRUD sobre entidades como:
- Lenguaje.
- Empleado.
- País.
- Aeropuerto.

Además, la aplicación cuenta con pruebas unitarias.

## Ejecución

1. La aplicación puede ser descargada desde este repositorio y ser ejecutada en con el siguiente comando:
```bash
mvn spring-boot:run
```

2. La aplicación puede ser descargada desde este repositorio, crear la imagen Docker con el archivo Dockerfile, y después ejecutar el contenedor.
```bash
docker build -t airport-service .
```
```bash
docker run --rm -d -p 8080:8080 airport-service
```

3. También se puede descargar la imagen Docker desde el repositorio DockerHub y ejecutarla:
```bash
docker pull rjoel99/airport-service
```
```bash
docker run --rm -d -p 8080:8080 rjoel99/airport-service
```