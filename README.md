# 🤖 API de Integración con Gemini

## 📌 Descripción

**APISMODELOGEMINI** es una API REST desarrollada con **Spring Boot** cuyo objetivo es integrar funcionalidades de IA mediante el modelo Gemini.  
El proyecto está organizado mediante una arquitectura por capas que garantiza claridad y escalabilidad.

La API está preparada para ser consumida por aplicaciones externas, como el proyecto Android **MotivaT**.

---

## 🛠️ Tecnologías utilizadas

- Java 21
- Spring Boot 3 
- Maven
- Google Gemini

---

## ✨ Características

- API REST modular y escalable.
- Integración con modelos de IA (Gemini).
- Arquitectura por capas (Controller, Service).
- Configuración mediante variables de entorno.

---

## 🗂️ Estructura del proyecto

```bash
APISMODELOGEMINI/
├─ motivatt2/
│  ├─ .mvn/
│  ├─ src/
│  │  ├─ main/
│  │  │   ├─ java/com/prueba2/motivatt2/
│  │  │   │    ├─ controller/
│  │  │   │    ├─ service/
│  │  │   │    └─ Motivat2Application.java
│  │  │   └─ resources/
│  │  │        ├─ application.properties
│  │  │        └─ logback.xml
│  │  └─ test/
│  ├─ pom.xml
│  └─ .gitignore
└─
```

---

# 🧱 Arquitectura de Capas  

A continuación se describen todas las capas del proyecto, basadas en la estructura exacta.

---

## 🏁 Capa Controller

**Ubicación:** `controller/`

### Responsabilidades

- Exponer los endpoints REST.
- Recibir peticiones del cliente y validarlas.
- Gestionar respuestas HTTP.
- Delegar la lógica a la capa Service.
- **No debe:** contener lógica de negocio o acceder directamente a la API de Gemini.

---

## 🔗 Capa Service

**Ubicación:** `service/`

### Responsabilidades

- Implementar la lógica de negocio.
- Gestionar la comunicación con la API de Gemini.
- Procesar datos antes de devolverlos al controlador.
- Manejar errores y excepciones internas.
- **No debe:** exponer endpoints o realizar validaciones propias del controlador.

---

## 🧩 Capa Principal (Application)

**Ubicación:** `Motivat2Application.java`

### Responsabilidades

- Punto de entrada de la aplicación Spring Boot.
- Inicialización del contexto.
- Configuración global del proyecto.
