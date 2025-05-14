# Manos Locales

**Manos Locales** es una aplicación móvil desarrollada en **Kotlin** que permite a los usuarios descubrir, seguir y contactar a productores y emprendedores locales. Su objetivo principal es fomentar el consumo regional, el contacto directo y la promoción de productos auténticos.

## Tabla de Contenidos

- [Manos Locales](#manos-locales)
  - [Tabla de Contenidos](#tabla-de-contenidos)
  - [Descripción](#descripción)
  - [Características Principales](#características-principales)
  - [Requisitos](#requisitos)
    - [Requerimientos Funcionales](#requerimientos-funcionales)
    - [Requerimientos No Funcionales](#requerimientos-no-funcionales)
  - [Estructura del Proyecto](#estructura-del-proyecto)
  - [Demostracion de la APP](#demostracion-de-la-app)
    - [Video de demostración](#video-de-demostración)

## Descripción

La aplicación **Manos Locales** permite a los usuarios:

- Registrarse, iniciar sesión y cambiar su contraseña.
- Explorar una lista de emprendimientos y productores locales.
- Filtrar productos por categorías como alimentos, textiles, artesanías, cosmética natural, entre otros.
- Visualizar detalles de los productores: nombre, ubicación, productos ofrecidos, fotos y formas de contacto.
- Marcar favoritos y recibir notificaciones sobre novedades.
- Compartir productos o perfiles de productores a través de WhatsApp o redes sociales.

## Características Principales

- Pantalla de bienvenida (Splash) con carga inicial de datos desde el servidor.
- Registro, inicio de sesión y gestión de perfil de usuario.
- Listado de productos y productores con buscador por categorías y ubicación.
- Favoritos con notificaciones configurables.
- Pantalla de configuración para preferencias de búsqueda y notificaciones.
- Conexión a una API para obtener datos actualizados de productos y productores.
- Envío de consultas al desarrollador mediante correo electrónico.

## Requisitos

### Requerimientos Funcionales

1. Implementar una pantalla de bienvenida (Splash).
2. Flujo de registro, inicio de sesión y cambio de contraseña.
3. Gestión del perfil del usuario.
4. Listado de productos y productores con buscador.
5. Favoritos con navegación al detalle del producto.
6. Configuración de alertas y preferencias.
7. Conexión a una API para sincronización de datos.
8. Envío de correos electrónicos al desarrollador.

### Requerimientos No Funcionales

1. Soporte para internacionalización.
2. Operaciones de red en segundo plano para mantener la interfaz fluida.
3. Uso de **Activities**, **Fragments** y al menos una pantalla en **Jetpack Compose**.
4. Gestión de permisos para funcionalidades como ubicación.
5. Código limpio y organizado siguiendo el patrón **MVVM**.

## Estructura del Proyecto

TPTecnoMobil/
├── app/                     # Contiene el código fuente principal de la aplicación
│   ├── src/                 # Código fuente dividido en módulos 
│   │   ├── main/            # Código principal de la aplicación
│   │   │   ├── java/        # Archivos de código Kotlin
│   │   │   ├── res/         # Recursos como layouts, imágenes y cadenas
│   │   │   └── AndroidManifest.xml # Configuración del manifiesto de la aplicación
│   │   └── test/            # Pruebas unitarias
├── build.gradle.kts         # Configuración de Gradle para el proyecto
├── gradle/                  # Configuración adicional de Gradle
├── gradlew                  # Script para ejecutar Gradle en Linux/Mac
├── gradlew.bat              # Script para ejecutar Gradle en Windows
├── settings.gradle.kts      # Configuración de los módulos del proyecto
└── README.md                # Documentación del proyecto

## Demostracion de la APP
### Video de demostración

[Ver el video](./Demo1.0.mp4)