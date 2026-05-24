# Proyecto Rewinds Rentals

**Descripción**
El proyecto consiste en el diseño y desarrollo de una plataforma administrativa orientada a la gestión de operaciones de un establecimiento de renta y venta de videojuegos y contenido multimedia. El sistema permite un control integral sobre el inventario, la base de datos de clientes y las transacciones financieras.

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java (JDK 21+)
* **IDE:** Eclipse
* **Control de Versiones:** Git / GitHub
* **Diseño:** Figma

## 📚 Dependencias del Proyecto
El proyecto requiere las siguientes librerías externas configuradas en el *Build Path*:

* **Base de datos:** `mysql-connector-j-8.4.0.jar`
* **Generación de PDFs (iText Suite):** *
    * `commons-7.2.5.jar`
    * `io-7.2.5.jar`
    * `kernel-7.2.5.jar`
    * `layout-7.2.5.jar`
    * `slf4j-api-1.7.36.jar`


## 🚀 Requisitos Previos
* Las librerías listadas anteriormente deben estar agregadas al *Build Path* de tu proyecto en Eclipse. Puedes encontrar las librerías en la carpeta `lib`

## 📥 Instalación y Configuración (Importante)
Para asegurar el correcto funcionamiento del sistema y la generación de archivos, sigue estos pasos:

1. **Estructura de paquetes:** Al importar el proyecto, verifica la ruta de los archivos fuente.
    * **Nota sobre la carpeta de origen:** Si al importar observas una carpeta llamada `java` fuera de `src/main`, muévela dentro de `src/main/` para que la ruta sea `src/main/java`.
    * **Carpeta de reportes:** El sistema genera archivos PDF automáticamente en `src/main/java/pdfs`.

## 🔑 Instrucciones de Ejecución
1. Ejecuta la clase `Main` (o la clase de inicio de sesión).
2. **Credenciales de acceso:**
   * **Usuario:** `prueba@gmail.com`
   * **Contraseña:** `1234`

## 📑 Funcionalidades Principales
* **Autenticación:** Sistema de login seguro.
* **Módulos de Gestión:**
    * **Operación:** Registro y detalle de transacciones (Renta/Venta).
    * **Clientes:** Base de datos de socios con edición de perfiles.
    * **Videojuegos & Películas:** Catálogos interactivos con gestión de stock y detalles.
* **Generación de Reportes:** El sistema permite exportar a **PDF** fichas técnicas y tarjetas de cliente.
    * Los archivos generados se guardan automáticamente en la carpeta `pdfs/` dentro de tu proyecto. 

## 🎨 Diseño y Frontend
* **Prototipo:** Implementado bajo un diseño UI moderno basado en Figma.
* **Tipografía:** Inter (limpia y profesional).
* **Interacción:** Interfaz intuitiva con menús laterales y modales de confirmación personalizados.
