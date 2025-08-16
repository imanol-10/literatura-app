# 📚 Challenge Literatura - Alura Latam

Este proyecto es una aplicación de consola desarrollada en **Java con Spring Boot** que interactúa con la API de [Gutendex](https://gutendex.com/) para buscar, registrar y listar libros y autores.

## ✨ Funcionalidades

La aplicación permite:

- 🔎 **Buscar libro por título**: consulta en la API de Gutendex y guarda el libro en la base de datos.
- 📖 **Listar libros registrados**: muestra en consola todos los libros guardados.
- 👤 **Listar autores registrados**: imprime los autores cargados en la base de datos.
- 📆 **Listar autores vivos en un año**: filtra autores que estaban vivos en el año ingresado.
- 🌍 **Listar libros por idioma**: permite buscar libros registrados filtrados por idioma.

Los resultados se muestran en consola con un formato amigable, por ejemplo:

****** LIBRO *******
TÍTULO: Moby-Dick
AUTOR: Herman Melville
IDIOMA: en
NÚMERO DE DESCARGAS: 5789


---

## 🛠️ Tecnologías utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL / H2 Database** (según configuración)
- **Gutendex API** (fuente de datos de libros)

---

## ▶️ Cómo ejecutar el proyecto

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio.git

2. Abrir el proyecto en tu IDE favorito (IntelliJ IDEA recomendado).

3. Configurar la base de datos en application.properties:
   spring.datasource.url=jdbc:postgresql://localhost:5432/literatura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update

4. Ejecutar la aplicación:
   ./mvnw spring-boot:run
   o desde tu IDE con el botón ▶️ en LiteraturaApplication.


5. Usar el menú de consola para interactuar:
   === Challenge Literatura ===
1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un año
5 - Listar libros por idioma
0 - Salir


📂 Estructura del proyecto
src/main/java/com/alura/literatura
│
├── model/          # Entidades (Libro, Autor)
├── repository/     # Interfaces de acceso a datos (Spring Data JPA)
├── service/        # Lógica de negocio (consumo de API, registros)
└── LiteraturaApplication.java  # Clase principa

🌟 Futuras mejoras
-Agregar tests automatizados con JUnit y Mockito.
-Implementar un frontend web con Spring MVC o React.
-Internacionalización para múltiples idiomas.
-Mejor manejo de errores al consumir la API.

👨‍💻 Autor:
Proyecto desarrollado como parte del Challenge de Alura Latam - Oracle Next Education.
Creado por (ELISAUL IMANOL)
