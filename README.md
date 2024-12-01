# 🚀 Servidor_1-Eval  DAW Alicia Hernández Contreras

## Índice
1. [🎯 Introducción](#-introducción)
2. [🛠️ Tecnologías Usadas](#-tecnologías-usadas)
3. [⚙️ Funcionalidades del Proyecto](#-funcionalidades-del-proyecto)
4. [📚 Guía de Uso](#-guía-de-uso)
5. [🎉 Conclusión](#-conclusión)
6. [📬 Contacto](#-contacto)

---

## 🎯 Introducción

#### Descripción del Proyecto
Este proyecto es una **API RESTful** que gestiona la información relacionada con **proyectos**, **desarrolladores** y **tecnologías** utilizadas en el desarrollo de dichos proyectos. La aplicación está construida utilizando el patrón de diseño **Controlador-Servicio-Repositorio** (CSR), proporcionando una estructura clara y organizada para el manejo de la lógica empresarial y la persistencia de datos.

#### Objetivo
El objetivo de este proyecto es desarrollar una **API** que permita realizar operaciones CRUD sobre proyectos, desarrolladores y tecnologías, proporcionando endpoints fáciles de usar y documentación interactiva para facilitar la integración y pruebas.

---

### 🔧 Tecnologías Usadas

- **Spring Boot**: Framework principal para el desarrollo de la aplicación.
- **Spring Data JPA**: Para la gestión de la persistencia de datos.
- **Swagger (SpringDoc)**: Documentación interactiva de la API.
- **Git**: Control de versiones para gestionar el código fuente.

  
---

### ⚙️ Funcionalidades del Proyecto

La aplicación soporta los siguientes **endpoints** (base `/api/v1`):

#### Endpoints Proyectos:

 1. **GET /projects**: Obtiene todos los proyectos paginados con su estado, tecnologías y desarrolladores asociados.
 2. **GET /projects/{word}**: Filtra los proyectos que contienen la palabra `word` en su nombre.
 3. **POST /projects/insert**: Inserta un nuevo proyecto.
 4. **PUT /projects/{id}**: Actualiza un proyecto existente.
 5. **DELETE /projects/{id}**: Elimina un proyecto.

#### Endpoints Programadores:

 1. **POST /developers/insert**: Inserta un nuevo desarrollador.
 2. **DELETE /developers/{id}**: Elimina un desarrollador.

#### Endpoints Tecnologías:

1. **POST /technologies/insert**: Inserta una nueva tecnología.
2. **DELETE /technologies/{id}**: Elimina una tecnología.


---

## 📚 Guía de Uso

#### Instalación

1. Clona este repositorio en tu máquina local:

    ```bash
    git clone https://github.com/Alicia-74/Servidor_1-Eval.git
    ```

2. La aplicación estará disponible en `http://localhost:8080`.
   

#### Swagger (Documentación Interactiva)

La documentación interactiva de la API está disponible gracias a **SpringDoc**. Puedes acceder a ella en la siguiente URL: http://localhost:8080/swagger-ui.html.

En esta documentación podrás ver todos los endpoints, sus parámetros y las respuestas que puedes esperar de la API, además de probar las solicitudes directamente desde la interfaz web.


#### Ejemplo de Uso

**POST /projects/insert** - Crear un nuevo proyecto:

```json
{
  "projectName": "Nuevo Proyecto",
  "decrip": "Descripción del nuevo proyecto",
  "fechaInicio": "2024-02-01",
  "fechaFin": "2024-12-31",
  "urlRepo": "https://github.com/nuevo-proyecto",
  "urlDemo": "https://demo-nuevo.com",
  "picture": "url_imagen",
  "estadoProject": { "statusId": 1 },
  "tecnoUsada": [{ "tecnoId": 1 }, { "tecnoId": 2 }],
  "programadores": [{ "devId": 1 }]
}
```

---

## 🎉 Conclusión

Este proyecto proporciona una API RESTful eficiente y bien estructurada para la gestión de proyectos, desarrolladores y tecnologías en el contexto de equipos de desarrollo de software. Gracias al uso de tecnologías como Spring Boot, Spring Data JPA y Swagger, la aplicación es fácil de mantener, documentada y permite realizar las operaciones CRUD necesarias para gestionar la información de manera efectiva.

Además, el manejo centralizado de excepciones y el control adecuado de errores mediante RestControllerAdvice garantiza una experiencia robusta y segura para los usuarios de la API.


---


## 📬 Contacto

- **GitHub**: [@Alicia-74](https://github.com/Alicia-74)
- **LinkedIn**: [Alicia Hernández Contreras](https://www.linkedin.com/in/alicia-hern%C3%A1ndez-contreras-537101307/).
