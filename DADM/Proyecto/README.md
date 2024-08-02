# Desarrollo de Aplicaciones en Dispositivos Móviles.
## Proyecto Final: Desarrollo de una apliación usando un Framework distinto visto en la asignatura
---
## <center><u> Framework elegido: Angular 17 </u></center>
El objetivo final de este proyecto es comprender el funcionamiento de este framework y desarrollar una aplicación sencilla usando las principales características de este.

[alt]

### Requisitos para hacer el desplegue de esta aplicación con Angular 17 y Capacitor:
1. Tener instalado <b>Node.js y npm</b> en tu sistema <u>para entorno web</u>, y además <b>Gradle</b> y <b>Android Studio</b> <u>para entorno móvil</u>.
2. Tener instalado Angular CLI globalmente. Puedes instalarlo ejecutando el siguiente comando:
    ```
    npm install -g @angular/cli
    ```

Recuerda que estos son solo los pasos básicos para hacer el deploy de una aplicación con Angular 17 y Capacitor. Pueden haber otros requisitos o configuraciones específicas dependiendo de tu proyecto y plataforma de destino.

---

### Como desplegar el proyecto en tu entorno, ya sea para web o móvil (Android)

1. Antes de ejecutar el proyecto, es recomendable realizar los siguientes comandos en orden:
    ```
    npm install 
    ng build
    ```
2. Si quieres ejecutar la aplicación en tu navegado usa el siguiente comando: 
    ```
    ng serve
    ```
    Y si quieres que se abra directamente en el navegador añade el flag '-o':
    ```
    ng serve -o
    ```
<br>

3. Por otra parte si quieres ejecutar la app en un emulador de Android:
    ```
    npx cap sync
    npx cap run android
    ```
    Y si quieres que se abra el proyecto en Android Studio:
    ```
    npx cap sync
    npx cap open android
    ```
