# AirportService

Servicio que realiza operaciones CRUD sobre entidades como:
- Empleado.
- Lenguaje.
- País.
- Aeropuerto.

## Ejecución

1. El servicio puede ser descargado desde este repositorio y ejecutarlo en un IDE como:
- STS.
- Intellij IDEA.

2. El servicio puede ser descargado desde este repositorio y después crear la imagen Docker con el archivo Dockerfile.

3. Descargar la imagen Docker desde el repositorio DockerHub y ejecutarlo:
- docker pull rjoel99/airport-service
- docker run --rm -d -p 8080:8080 rjoel99/airport-service