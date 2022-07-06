# AirportService

Aplicación que actúa como servicio web que realiza operaciones CRUD sobre entidades como:
- Lenguaje.
- Empleado.
- País.
- Aeropuerto.

Además, la aplicación cuenta con pruebas unitarias.

## Ejecución

1. La aplicación puede ser descargada este repositorio y ejecutarla en un IDE como:
- STS.
- Intellij IDEA.

2. La aplicación puede ser descargada este repositorio y después crear la imagen Docker con el archivo Dockerfile.
```bash
docker build -t airport-service .
```

3. Descargar la imagen Docker desde el repositorio DockerHub y ejecutarla:
```bash
docker pull rjoel99/airport-service
```
```bash
docker run --rm -d -p 8080:8080 rjoel99/airport-service
```