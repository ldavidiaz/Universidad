const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');
const correo_destino = "tucorreo@correo.com";
document.querySelector("input[name='fechanac']").removeAttribute('required');

const expresiones = {
	user: /^[a-zA-Z0-9\_\-]{4,16}$/, // Letras, numeros, guion y guion_bajo
	password: /^.{4,12}$/, // 4 a 12 digitos.
	numcuenta: /^\d{7,14}$/ ,
	fechanac: /^\d{7,14}$/ // 7 a 14 numeros.
}
const campos = {
	user: false,
	password: false,
    fechanac: false,
    numcuenta: true,
	nombre: false,
	apellido: false,
	dni: true ,dni2:false,
	ciudad: false,
	pais: false,
	zip:false,
	address: false,
	terms:false
}
//emailjs
var selectedNull = '';
var España = 'España';
var Francia = 'Francia';
var Portugal = 'Portugal';
var Otros = 'Otro país'

//const span = document.getElementById('text');
document.querySelector('.selector')
.addEventListener('change', function (event) {
	const thisValue = event.currentTarget.value; // get current value
	const text = window[thisValue];
	let ZZ = text;

	document.getElementById('pais_id').value = ZZ;
});
//
const validarFormulario = (e) => {
    console.log(e.target.name);
	switch (e.target.name) {
		case "user":
			validarCampo(expresiones.user, e.target, 'user');
		break;
		case "password":
			validarCampo(expresiones.password, e.target, 'password');
			validarPassword2('password2');
		break;
		case "password2":
			validarPassword2('password2');
		break;
		case "fechanac":
            validarFecha('fechanac');
		break;
		case "numcuenta":
			validaNumCuenta('numcuenta');
		break;
		case "dni":
			validaDNI('dni');
		break;
	}
}

const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`group__${campo}`).classList.remove('form__group-incorrecto');
		document.getElementById(`group__${campo}`).classList.add('form__group-correcto');
		document.querySelector(`#group__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#group__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#group__${campo} .form__input-error`).classList.remove('form__input-error-activo');
		campos[campo] = true;
	} else {
		document.getElementById(`group__${campo}`).classList.add('form__group-incorrecto');
		document.getElementById(`group__${campo}`).classList.remove('form__group-correcto');
		document.querySelector(`#group__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#group__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#group__${campo} .form__input-error`).classList.add('form__input-error-activo');
		campos[campo] = false;
	}
}
const validarPassword2 = (campo) => {
	const inputPassword1 = document.getElementById('password');
	const inputPassword2 = document.getElementById('password2');

	if(inputPassword1.value !== inputPassword2.value){
		document.getElementById(`group__${campo}`).classList.add('form__group-incorrecto');
		document.getElementById(`group__${campo}`).classList.remove('form__group-correcto');
		document.querySelector(`#group__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#group__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#group__${campo} .form__input-error`).classList.add('form__input-error-activo');
		campos['password'] = false;
	} else if((inputPassword1.value !== "") && (inputPassword1.value==inputPassword2.value)) {
		document.getElementById(`group__${campo}`).classList.remove('form__group-incorrecto');
		document.getElementById(`group__${campo}`).classList.add('form__group-correcto');
		document.querySelector(`#group__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#group__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#group__${campo} .form__input-error`).classList.remove('form__input-error-activo');
		campos['password'] = true;
	}
}

const validarFecha = (campo) =>{
	document.querySelector(`input[name=${campo}]`).setAttribute('required',true);
	const fecha = document.getElementById('fechanac').value;
	var hoy = new Date();
	var fechanac = new Date(fecha);
	var fechanacAux = new Date(fecha.split("/").reverse().join("/"));

	if(fecha===""){
		document.querySelector(`#group__${campo} .form__input-error`).classList.add('form__input-error-activo');
		campos[campo] = false;
	}else{
		var ageInMilliseconds = hoy - fechanacAux;
    	var ageInYears = ageInMilliseconds / (365.25 * 24 * 60 * 60 * 1000);
    	var age = Math.floor(ageInYears);
		if(fechanac.getFullYear()>=hoy.getFullYear() && fechanac.getMonth()>=hoy.getMonth() && fechanac.getDate() >= hoy.getDate()){
			alert("La fecha seleccionada no puede ser mayor que la actual");
			campos[campo] = false;
			document.getElementById("fechanac").value = "";
			document.querySelector(`#group__${campo} .form__input-error`).classList.add('form__input-error-activo');
		}else if(age<18){
			document.getElementById(`group__${campo}`).classList.add('form__group-incorrecto');
			document.getElementById(`group__${campo}`).classList.remove('form__group-correcto');
			document.querySelector(`#group__${campo} i`).classList.add('fa-times-circle');
			document.querySelector(`#group__${campo} i`).classList.remove('fa-check-circle');
			document.querySelector(`#group__${campo} .form__input-error`).classList.add('form__input-error-activo');
			campos[campo] = false;
			document.getElementById('fechanac').value = "";
		}else{
			document.getElementById(`group__${campo}`).classList.remove('form__group-incorrecto');
			document.getElementById(`group__${campo}`).classList.add('form__group-correcto');
			document.querySelector(`#group__${campo} i`).classList.remove('fa-times-circle');
			document.querySelector(`#group__${campo} i`).classList.add('fa-check-circle');
			document.querySelector(`#group__${campo} .form__input-error`).classList.remove('form__input-error-activo');
			campos[campo] = true;
			fecha.split("/").reverse().join("/");
		}
	}
}

function rellenaCeros(num,long){
	var ceros = long-num.length;
	var numero =num;
	//console.log(ceros,long,num)
	if(num.length<long){	
		for(i=0;i<ceros;i++){
			numero = 0+numero;
		}
	}
return numero;
}
//numCuenta=true o false
function validaNumCuenta(campo) {
	var numCuenta = document.getElementById("numcuenta").value;
	var entidad = numCuenta.substr(0, 4);
	var sucursal = numCuenta.substr(4, 4);
	var dc = numCuenta.substr(8, 2);
	var cuenta = numCuenta.substr(10, 10);
	
	entidad = rellenaCeros(entidad, 4);
	sucursal = rellenaCeros(sucursal, 4);
	dc = rellenaCeros(dc, 2);
	cuenta = rellenaCeros(cuenta, 10);

	var concatenado = entidad+sucursal;
	var dc1 = calculaDCParcial(concatenado);
	var dc2 = calculaDCParcial(cuenta);
	if (dc != (dc1+dc2)) {
		//alert("El número de cuenta es inválido");
		document.getElementById(`group__${campo}`).classList.add('form__group-incorrecto');
		document.getElementById(`group__${campo}`).
		classList.remove('form__group-correcto');
		document.querySelector(`#group__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#group__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#group__${campo} .form__input-error`).classList.add('form__input-error-activo');
		campos[`${campo}`]=false;
		//console.log(campos[`${campo}`]);
		}else{
		document.getElementById(`group__${campo}`).classList.remove('form__group-incorrecto');
		document.getElementById(`group__${campo}`).classList.add('form__group-correcto');
		document.querySelector(`#group__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#group__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#group__${campo} .form__input-error`).classList.remove('form__input-error-activo');
		campos[`${campo}`]=true;
		//console.log(campos[`${campo}`]);
		}
}
function calculaDCParcial(cadena){
	var dcParcial = 0;
	var tablaPesos = [6,3,7,9,10,5,8,4,2,1];
	var suma = 0;
	for(var i=0; i<cadena.length; i++){
	suma += parseInt(cadena[i]) * tablaPesos[i];
	}
	dcParcial = 11 - (suma % 11);
	if(dcParcial==11){
	dcParcial=0;
	}else if(dcParcial==10){
	dcParcial=1;
	}
	return dcParcial;
	}


inputs.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('blur', validarFormulario);
});

formulario.addEventListener('submit', (e) => {
	e.preventDefault();
	const terminos = document.getElementById('terms').checked;
	if(terminos) terms = true;

	//FROM EMAILJS
	const serviceID = 'default_service';
	const templateID = 'template_jqgh9ei';
	//SI LOS CAMPOS SON VALIDOS
	if(campos.user && campos.password && campos.fechanac && campos.numcuenta && terms ){
		//EMAILJS
		emailjs.sendForm(serviceID, templateID, '#formulario')
		.then(() => {
			alert('Solicitud enviada!');
		}, (err) => {
			alert(JSON.stringify(err));
		});
		//DAMOS VISIBILIDAD AL MENSAJE DE QUE SE HA ENVIADO CORRECTAMENTE DURANTE X TIEMPO
		document.getElementById('form__msg-ok').classList.add('form__msg-ok-activo');
		setTimeout(() => {
			
			document.getElementById('form__msg-ok').classList.remove('form__msg-ok-activo');
		}, 10000);
		//ELIMINAMOS VISIBILIDAD DEL MENSAJE
		document.querySelectorAll('.form__group-correcto').forEach((icono) => {
			icono.classList.remove('form__group-correcto');
		});
		//LIMPIAMOS FORMULARIO
		formulario.reset();
	} else {
		
		document.getElementById('form__msg').classList.add('form__msg-activo');
		if(campos.user===false)validarCampo(expresiones.user,user,'user');
		if(campos.password===false)validarPassword2(expresiones.password,password,'password');
		
		if(campos.fechanac===false)validarFecha('fechanac');
	}
});

//limitaciones de caracteres en los input
function limita(maximoCaracteres,campo) {
	var elemento = document.getElementById(`${campo}`);
	if(elemento.value.length >= maximoCaracteres ) {
	  return false;
	}
	else {
	  return true;
	}
  }
  
  function permite(elEvento, permitidos) {
	// Variables que definen los caracteres permitidos
	var numeros = "0123456789";
	var caracteres = " abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	var numeros_caracteres = numeros + caracteres;
	var teclas_especiales = [8, 37, 39, 46];
	// 8 = BackSpace, 46 = Supr, 37 = flecha izquierda, 39 = flecha derecha
  
  
	// Seleccionar los caracteres a partir del parámetro de la función
	switch(permitidos) {
	  case 'num':
		permitidos = numeros;
		break;
	  case 'car':
		permitidos = caracteres;
		break;
	  case 'num_car':
		permitidos = numeros_caracteres;
		break;
	}
  
	// Obtener la tecla pulsada
	var evento = elEvento || window.event;
	var codigoCaracter = evento.charCode || evento.keyCode;
	var caracter = String.fromCharCode(codigoCaracter);
  
	// Comprobar si la tecla pulsada es alguna de las teclas especiales
	// (teclas de borrado y flechas horizontales)
	var tecla_especial = false;
	for(var i in teclas_especiales) {
	  if(codigoCaracter == teclas_especiales[i]) {
		tecla_especial = true;
		break;
	  }
	}
  
	// Comprobar si la tecla pulsada se encuentra en los caracteres permitidos
	// o si es una tecla especial
	return permitidos.indexOf(caracter) != -1 || tecla_especial;
  }
  function validaDNI(campo) {
    var dni = document.getElementById("dni").value;
    var letra = document.getElementById("dni2").value;

    var letras = "TRWAGMYFPDXBNJZSQVHLCKE";
    var numero = parseInt(dni);
    var posicion = numero % 23;
    var letraCorrecta = letras[posicion];

    if (letraCorrecta !== letra.toUpperCase()) {
        document.getElementById(`group__${campo}`).classList.add('form__group-incorrecto');
        document.getElementById(`group__${campo}`).classList.remove('form__group-correcto');
        document.querySelector(`#group__${campo} i`).classList.add('fa-times-circle');
        document.querySelector(`#group__${campo} i`).classList.remove('fa-check-circle');
        document.querySelector(`#group__${campo} .form__input-error`).classList.add('form__input-error-activo');
        campos[`${campo}`]=false;
		campos['dni2'] = false;
    } else {
        document.getElementById(`group__${campo}`).classList.remove('form__group-incorrecto');
        document.getElementById(`group__${campo}`).classList.add('form__group-correcto');
        document.querySelector(`#group__${campo} i`).classList.remove('fa-times-circle');
        document.querySelector(`#group__${campo} i`).classList.add('fa-check-circle');
        document.querySelector(`#group__${campo} .form__input-error`).classList.remove('form__input-error-activo');
        campos[`${campo}`]=true;
		campos['dni2']=true;
    }
}


  /**Ejemplo validaNumCuenta */
  
  /*Número de cuenta inválido: 1234000011111112

    El número de entidad es 1234 y el número de sucursal es 0000.
    El primer dígito de control debería ser 2 y el segundo debería ser 2. Sin embargo, los dígitos de control ingresados son 11.

	Número de cuenta válido: 123400002222222222

    El número de entidad es 1234 y el número de sucursal es 0000.
    El primer dígito de control es 2 y el segundo es 2, los cuales son correctos según el algoritmo de checksum.
*/
