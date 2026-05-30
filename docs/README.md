# Proyecto Rewinds Rentals

**Descripción**
Rewinds Rentals es un sistema creado para administrar un local de renta y venta de videojuegos y películas. El programa permite llevar el control del inventario, registrar a los clientes y manejar las transacciones del día a día.

## Tecnologías utilizadas
* **Lenguaje:** Java (JDK 21+)
* **IDE:** Eclipse
* **Base de Datos:** MySQL
* **Diseño de Interfaz:** Figma
* **Control de Versiones:** Git / GitHub

**Archivos del proyecto (Dropbox):** https://www.dropbox.com/scl/fo/pjj50k8f1jl69alkw1h7a/ANTDuSoRUGBvK0uDmlHpeAE?rlkey=exrez8uhpkykuhde83tfkyg0h&st=5sxo8hpx&dl=0

## Dependencias
Para que el proyecto compile correctamente, es necesario agregar las siguientes librerías al Build Path de Eclipse (todas se encuentran en la carpeta lib del proyecto):

**Conexión a Base de Datos:**
* mysql-connector-j-8.4.0.jar
* commons-pool2-2.12.0.jar
* jedis-5.1.0.jar

**Generación de reportes PDF (iText):**
* commons-7.2.5.jar
* io-7.2.5.jar
* kernel-7.2.5.jar
* layout-7.2.5.jar
* slf4j-api-1.7.36.jar

## Instalación y Configuración
1. **Importar el proyecto:** Al abrirlo en tu IDE, asegúrate de que la carpeta java esté dentro de src/main/. Si quedó fuera, muévela para que la ruta correcta sea src/main/java.
2. **Conexión a la base de datos:** Por seguridad, las credenciales del servidor no están en el código fuente. Debes colocar el archivo config.properties directamente en la raíz del proyecto (al mismo nivel que la carpeta src). Si no lo tienes, solicítalo al administrador.
3. **Carpeta de PDFs:** El sistema genera reportes automáticamente. Estos se guardarán en la ruta src/main/java/pdfs.

## Ejecución
Para iniciar el sistema, corre la clase Main. 

Puedes ingresar con estas credenciales de prueba:
* **Usuario:** prueba@gmail.com
* **Contraseña:** 1234

## Funcionalidades principales
* **Login:** Autenticación de usuarios para entrar al sistema.
* **Operaciones:** Pantalla para registrar las rentas y ventas de los artículos.
* **Clientes:** Registro de socios donde se pueden editar sus datos y subir una foto de perfil.
* **Inventario:** Catálogo de películas y videojuegos para consultar detalles y existencias.
* **Reportes:** Generación de archivos PDF para crear tarjetas de clientes y fichas técnicas de los juegos/películas.

## Diseño
La interfaz gráfica se maquetó primero en Figma buscando que fuera limpia, usando menús laterales y la tipografía Inter.
