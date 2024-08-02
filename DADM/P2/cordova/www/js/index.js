// Wait for the deviceready event before using any of Cordova's device APIs.
// See https://cordova.apache.org/docs/en/latest/cordova/events/events.html#deviceready
document.addEventListener('deviceready', onDeviceReady, false);

function onDeviceReady() {
     // Espera a que el dispositivo esté listo

     paginaInicial();
     // Ajusta la altura del contenedor según sea necesario
     var contenedor = document.getElementById('contenedor');
     var contenidoLargo = document.getElementById('contenido-largo');
     var contenidoLargoAltura = contenidoLargo.offsetHeight;
     //var btnRegistro = document.getElementById('btn-registro');
     if (contenidoLargoAltura > contenedor.offsetHeight) {
         // Si el contenido excede la altura del contenedor, ajusta la altura del contenedor
         contenedor.style.height = contenidoLargoAltura + 'px';
     }
 
 
     var btnRegistro = document.getElementById("btn-registro");
     var  texto_informativo = document.getElementById('textoInformativo');
     texto_informativo.innerText="Por favor, acepta los términos y condiciones";
     function mostrarDialogo() {
         // Obtener el correo electrónico introducido
             console.log("MOSTRAS DIALOGO");
             var email = document.getElementById("email").value;
             var psw = document.getElementById('password').value;
             //const userEmail = email;
             if(email!='' && psw!=''){
             // Mostrar el cuadro de diálogo
             navigator.notification.confirm(
                 'Te has registrado con el siguiente correo electrónico:\n\n' + email,  // Mensaje
                 onConfirm,              // Función de confirmación
                 '¡Bienvenido!',         // Título
                 ['Continuar']           // Opciones del botón
                 );
             }else{
                 texto_informativo.style.display = "block";
                 texto_informativo.innerText="*Los campos email y contraseña son obligatorios";
                 texto_informativo.style.color= "#FF3333";
                 texto_informativo.style.textTransform = "none";
             }
             
     }
         
     function onConfirm(buttonIndex) {
         var email = document.getElementById("email").value;
         console.log("You clicked " + buttonIndex + " button!");
         if (buttonIndex === 1) {
             localStorage.setItem('userEmail', email);
             nuevaPagina();
             // Botón "Continuar" presionado, eliminamos contenido dentro de div app
             // y creamos uno nuevo
         }
     }   
     
     
     btnRegistro.onclick = mostrarDialogo;
     
     var chkBox_termYconds = document.getElementById('termYconds');
     
     function verificar_chkb(){
         
         var btn_registro = document.getElementById('btn-registro');
         if(chkBox_termYconds.checked){
             
             btn_registro.disabled=false;
             texto_informativo.style.display = "none";
             texto_informativo.innerText="";
         }else{
             
             btn_registro.disabled=true;
             texto_informativo.style.display = "block";
             texto_informativo.style.color= "inherit";
             texto_informativo.style.transform="uppercase";
             texto_informativo.innerText="Por favor, acepta los términos y condiciones";
         }
     }
     chkBox_termYconds.onchange = verificar_chkb;
 
}
/* 
document.addEventListener('deviceready', function() {
    // Espera a que el dispositivo esté listo

    paginaInicial();
    // Ajusta la altura del contenedor según sea necesario
    var contenedor = document.getElementById('contenedor');
    var contenidoLargo = document.getElementById('contenido-largo');
    var contenidoLargoAltura = contenidoLargo.offsetHeight;
    //var btnRegistro = document.getElementById('btn-registro');
    if (contenidoLargoAltura > contenedor.offsetHeight) {
        // Si el contenido excede la altura del contenedor, ajusta la altura del contenedor
        contenedor.style.height = contenidoLargoAltura + 'px';
    }


    var btnRegistro = document.getElementById("btn-registro");
    var  texto_informativo = document.getElementById('textoInformativo');
    texto_informativo.innerText="Por favor, acepta los términos y condiciones";
    function mostrarDialogo() {
        // Obtener el correo electrónico introducido
            console.log("MOSTRAS DIALOGO");
            var email = document.getElementById("email").value;
            var psw = document.getElementById('password').value;
            //const userEmail = email;
            if(email!='' && psw!=''){
            // Mostrar el cuadro de diálogo
            navigator.notification.confirm(
                'Te has registrado con el siguiente correo electrónico:\n\n' + email,  // Mensaje
                onConfirm,              // Función de confirmación
                '¡Bienvenido!',         // Título
                ['Continuar']           // Opciones del botón
                );
            }else{
                texto_informativo.style.display = "block";
                texto_informativo.innerText="*Los campos email y contraseña son obligatorios";
                texto_informativo.style.color= "#FF3333";
                texto_informativo.style.textTransform = "none";
            }
            
    }
        
    function onConfirm(buttonIndex) {
        var email = document.getElementById("email").value;
        console.log("You clicked " + buttonIndex + " button!");
        if (buttonIndex === 1) {
            localStorage.setItem('userEmail', email);
            nuevaPagina();
            // Botón "Continuar" presionado, eliminamos contenido dentro de div app
            // y creamos uno nuevo
        }
    }   
    
    
    btnRegistro.onclick = mostrarDialogo;
    
    var chkBox_termYconds = document.getElementById('termYconds');
    
    function verificar_chkb(){
        
        var btn_registro = document.getElementById('btn-registro');
        if(chkBox_termYconds.checked){
            
            btn_registro.disabled=false;
            texto_informativo.style.display = "none";
            texto_informativo.innerText="";
        }else{
            
            btn_registro.disabled=true;
            texto_informativo.style.display = "block";
            texto_informativo.style.color= "inherit";
            texto_informativo.style.transform="uppercase";
            texto_informativo.innerText="Por favor, acepta los términos y condiciones";
        }
    }
    chkBox_termYconds.onchange = verificar_chkb;


});
 */
var contentApp = document.getElementById('deviceready');
function paginaInicial() {
    // Limpiar el contenido previo si es necesario
    contentApp.innerHTML = '';

    // Crear elementos HTML dinámicamente
    var h1 = document.createElement('h1');
    h1.innerHTML = 'Registro<br>';

    var labelEmail = document.createElement('label');
    labelEmail.setAttribute('for', 'email');
    labelEmail.innerHTML = 'Email:';

    var inputEmail = document.createElement('input');
    inputEmail.setAttribute('type', 'email');
    inputEmail.setAttribute('id', 'email');
    inputEmail.setAttribute('name', 'email');
    inputEmail.setAttribute('required', true);

    // Continuar creando los demás elementos...
    var labelPsw = document.createElement('label');
    labelPsw.setAttribute('id', 'lblPsw');
    labelPsw.setAttribute('for', 'password');
    labelPsw.innerHTML = 'Contraseña:';

    var inputPsw = document.createElement('input');
    inputPsw.setAttribute('type', 'password');
    inputPsw.setAttribute('id', 'password');
    inputPsw.setAttribute('name', 'password');
    inputPsw.setAttribute('required', true);
    // Agregar los elementos al contenedor
    contentApp.appendChild(h1);
    contentApp.appendChild(labelEmail);
    contentApp.appendChild(inputEmail);
    contentApp.appendChild(labelPsw);
    contentApp.appendChild(inputPsw);
    // Agregar más elementos...
    // Crear el div contenedor
    var divContenedor = document.createElement('div');
    divContenedor.setAttribute('id', 'contenedor');
    divContenedor.classList.add('contenedor-con-scroll');

    // Crear el div de contenido largo
    var divContenidoLargo = document.createElement('div');
    divContenidoLargo.setAttribute('id', 'contenido-largo');
    divContenidoLargo.style.textAlign = 'justify';
    divContenidoLargo.innerHTML = txtTermCond();
    // Agregar el div de contenido largo al div contenedor
    divContenedor.appendChild(divContenidoLargo);

    // Agregar el div contenedor al elemento con el id 'contentApp'
    contentApp.appendChild(divContenedor);

    // Botón de entrada
    var btnRegistro = document.createElement('button');
    btnRegistro.setAttribute('id', 'btn-registro');
    btnRegistro.setAttribute('disabled', 'disabled');
    btnRegistro.textContent = 'Entrar';
    contentApp.appendChild(btnRegistro);

    // Crear un párrafo para mensajes informativos
    var textoInformativo = document.createElement('p');
    textoInformativo.setAttribute('id', 'textoInformativo');
    textoInformativo.classList.add('texto-informativo');
    contentApp.appendChild(textoInformativo);
}
function nuevaPagina(){
    var email = localStorage.getItem('userEmail');
    if (email == null) {
        // La variable existe, hacer algo con ella
        email = "!!!";
    } 
    contentApp.innerHTML="";
// Crear elementos HTML dinámicamente
    var h1 = document.createElement('h1');
    h1.textContent = "¡Te has registrado con exito!";

    var h2 = document.createElement('h2');
    h2.textContent = `Bienvenido ${email}`;

    var btnSalir = document.createElement('button');
    btnSalir.setAttribute('id', 'btn-salir');
    btnSalir.addEventListener('click', onDeviceReady);
    btnSalir.textContent = 'SALIR';
    
    // Agregar los elementos al contenedor
    contentApp.appendChild(h1);
    contentApp.appendChild(h2);
    contentApp.appendChild(btnSalir);
}


function txtTermCond(){

    return `Por favor, lee atentamente estos términos y condiciones de uso antes de utilizar nuestra aplicación móvil.<br>
    
    1. Aceptación de los Términos
    Al utilizar esta aplicación móvil, aceptas automáticamente estos términos y condiciones de uso. Si no estás de acuerdo con alguno de los términos establecidos aquí, por favor, no utilices la aplicación.<br>
    
    2. Uso de la Aplicación
    La aplicación está diseñada para proporcionar [descripción breve del propósito de la aplicación]. Queda prohibido cualquier uso de la aplicación que viole las leyes locales, nacionales o internacionales.<br>
    
    3. Propiedad Intelectual
    Todos los derechos de propiedad intelectual relacionados con la aplicación y su contenido son propiedad de [nombre de la empresa o desarrollador]. No está permitido copiar, distribuir, modificar o utilizar el contenido de la aplicación sin autorización.<br>
    
    4. Privacidad
    Respetamos tu privacidad y protegemos tus datos personales de acuerdo con nuestra Política de Privacidad. Al utilizar la aplicación, aceptas las prácticas de privacidad descritas en dicha política.<br>
    
    5. Modificaciones
    Nos reservamos el derecho de modificar estos términos y condiciones en cualquier momento. Se te notificará de cualquier cambio significativo en los términos a través de la aplicación.<br>
    
    Al hacer clic en 'Aceptar', confirmas que has leído, comprendido y aceptado los términos y condiciones de uso de esta aplicación móvil.<br>
    
    Por favor, ponte en contacto con nosotros si tienes alguna pregunta o inquietud relacionada con estos términos y condiciones de uso.<br><br>
     
    <!-- Toggle -->
    <label class='toggle' style='margin-bottom: 20px;'>
        <input type='checkbox' id="termYconds" onchange="verificar_chkb()">
        <span class='slider round'></span>
    </label>
    <span>Acepto los términos y condiciones</span>
    `;
}
/* 

function paginaInicial(){
    contentApp.innerHTML =`
    <h1>Registro</h1>
    <label for='email'>email:</label><br>
    <input type='email' id='email' name='email' required><br><br>
    
    <label for='password'>Contraseña:</label><br>
    <input type='password' id='password' name='password' required><br><br>
    
    <div id='contenedor' class='contenedor-con-scroll'>
        <div id='contenido-largo' style='text-align: justify;'>
            Términos y Condiciones de Uso
    
    Por favor, lee atentamente estos términos y condiciones de uso antes de utilizar nuestra aplicación móvil.<br>
    
    1. Aceptación de los Términos
    Al utilizar esta aplicación móvil, aceptas automáticamente estos términos y condiciones de uso. Si no estás de acuerdo con alguno de los términos establecidos aquí, por favor, no utilices la aplicación.<br>
    
    2. Uso de la Aplicación
    La aplicación está diseñada para proporcionar [descripción breve del propósito de la aplicación]. Queda prohibido cualquier uso de la aplicación que viole las leyes locales, nacionales o internacionales.<br>
    
    3. Propiedad Intelectual
    Todos los derechos de propiedad intelectual relacionados con la aplicación y su contenido son propiedad de [nombre de la empresa o desarrollador]. No está permitido copiar, distribuir, modificar o utilizar el contenido de la aplicación sin autorización.<br>
    
    4. Privacidad
    Respetamos tu privacidad y protegemos tus datos personales de acuerdo con nuestra Política de Privacidad. Al utilizar la aplicación, aceptas las prácticas de privacidad descritas en dicha política.<br>
    
    5. Modificaciones
    Nos reservamos el derecho de modificar estos términos y condiciones en cualquier momento. Se te notificará de cualquier cambio significativo en los términos a través de la aplicación.
    
    Al hacer clic en 'Aceptar', confirmas que has leído, comprendido y aceptado los términos y condiciones de uso de esta aplicación móvil.<br>
    
    Por favor, ponte en contacto con nosotros si tienes alguna pregunta o inquietud relacionada con estos términos y condiciones de uso.<br><br>
        <!-- Toggle -->
        <label class='toggle' style='margin-bottom: 20px;'>
            <input type='checkbox' id="termYconds" onchange="verificar_chkb()">
            <span class='slider round'></span>
        </label>
        <span>Acepto los terminos y condiciones</span>
        </div>
    </div>
    <button id='btn-registro' style='margin-top: 20px;'>Entrar</button>
    <p id='textoInformativo' class='texto-informativo' style='display:block'></p>
    `;
    } 
*/