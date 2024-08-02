// Llamar la función cuando se cambie el valor del input list
document.getElementById("total-iva2").addEventListener("change", calculaIVA); 
const precio_siniva = null;
const tipo_iva = {
    iva21:null,
    iva3:null,
    iva10:null
}
var idActual = null;
var auxSubtotal = null;

var lista = document.getElementById("tipo-iva2");
// Obtener el índice de la opción que se ha seleccionado
var indiceSeleccionado = lista.selectedIndex;
// Con el índice y el array "options", obtener la opción seleccionada
var opcionSeleccionada = lista.options[indiceSeleccionado];

// Obtener el valor y el texto de la opción seleccionada
var textoSeleccionado = opcionSeleccionada.text;
var valorSeleccionado = opcionSeleccionada.value;

console.log("Opción seleccionada: " + textoSeleccionado + "\n Valor de la opción: " + valorSeleccionado);




function obtenerNombre(idP){
var id=parseInt(idP.slice(1),10);
var valhtml = Array.from(document.getElementsByClassName("title__product"));  
var valnombre = null;
valnombre = valhtml[id];
  return valnombre.innerText;
}
function obtenerPrecio(idP){
  var id=parseInt(idP.slice(1),10);
  var valhtml = Array.from(document.getElementsByClassName("precio__product"));  
  var valprecio = null;
  valprecio = valhtml[id];
    return valprecio.innerText;
}
function obtenerDescuento(idP){

}

function obtenerPosId(idProducto,trs){
  var posId=null;
  var contador = 0;
  var trs_aux = null;
  if(trs != null){
    trs_aux = trs;
    var numelem = trs.length;

    for(var i=0;i<numelem;i++){
      if(idProducto===trs_aux[i].innerText)
      contador = i;
    }
    posId=contador;
  }else
  posId=0;

  return posId;
}

function buscarLinea(idProducto){
  var trs = Array.from(document.getElementById("cart-products").getElementsByTagName("p"));
  var lineaBuscada = null;
  trs.forEach (tr => {
    var trId = tr.innerText;
    if (idProducto === trId){
      lineaBuscada = trs;
    }
  });
  
  return lineaBuscada;
}

function addCarrito(idProducto,prDescuento){
  var trLinea = buscarLinea(idProducto);
  var nombre = obtenerNombre(idProducto);
  var precio = obtenerPrecio(idProducto);
  var descontado = null;
  var descuento = parseFloat((100-parseFloat(prDescuento))/100);
  if(prDescuento === '0'){
    descontado = '';
  }else{
    descontado = parseFloat((parseFloat(precio)/descuento)-precio).toFixed(2)+' €';
  }
/*   console.log(descontado); */
  var posId = obtenerPosId(idProducto,trLinea);
  var cantidad = 1;
  
  if (trLinea == null){
    
    var tbody = document.getElementById("cart-products");
    var filaProducto ='<div class="row-cart-product" '+'id="br'+idProducto.slice(1)+'">'+
    '<div class="cols-cart-product">'+
    '<p class="id--product">'+idProducto+
    '</p><p class="nombre-product">'+nombre+
    '</p><div class="cantidad-producto">'+
    '<button class="btn-decrementar">-</button>'+
    '<p class="cantidad-valor">'+cantidad+'</p>'+
    '<button class="btn-incrementar">+</button>'+
    '</div><p>'+precio+' €'+
    '</p><p class="descuento-producto">'+descontado+
    '</p><p class="subtotal-producto">'+precio+
    '</p><p>'+'21%'+
    '</p></div>'+'<div class="elim__pr"><label class="btn-elm__producto">X</label></div>'+'</div>';
    tbody.innerHTML += filaProducto;
  } 
  else{
    tdCantidad = trLinea[posId+2];
    tdCantidad.innerText = parseInt(tdCantidad.innerText)+1;
    tdPrecio = trLinea[posId+5];
    auxSubtotal=tdPrecio;
    tdPrecio.innerText = (parseFloat(tdPrecio.innerText)+parseFloat(precio)).toFixed(2);
  }

  idActual=idProducto;
  sumrestCantidad()
  eliminarLinea();
  estiloDecrementar()
  var total = document.getElementById("cart-totals");
  total.innerText=(parseFloat(total.innerText)+parseFloat(precio)).toFixed(2);
}

function eliminarLinea() {
  var botones = document.querySelectorAll(".btn-elm__producto");
  for (var i = 0; i < botones.length; i++) {
    botones[i].addEventListener("click", function() {
      var fila = this.closest(".row-cart-product");
      var precio = parseFloat(fila.querySelector(".cols-cart-product p:nth-child(6)").innerText);
      var cantidad = parseInt(fila.querySelector(".cantidad-valor").innerText);
      /* var unidades = document.querySelector(".total-unit p:nth-child(2)"); */
      var total = document.querySelector("#cart-totals");
      /* unidades.innerText = parseInt(unidades.innerText) - cantidad; */
      total.innerText = (parseFloat(total.innerText) - precio).toFixed(2);
      fila.parentNode.removeChild(fila);
    });
  }
}

function sumrestCantidad(){
var buttons = document.getElementsByClassName("cantidad-producto");
for (var i = 0; i < buttons.length; i++) {
    buttons[i].addEventListener("click", function(event) {
        var button = event.target;
        var cantidadValor = button.parentElement.getElementsByClassName("cantidad-valor")[0];
        var idProducto = button.parentElement.parentElement.getElementsByClassName("id--product")[0].innerText;
        var precio = obtenerPrecio(idProducto);
        var total = document.getElementById("cart-totals");
        var subtotalProducto = button.parentElement.parentElement.getElementsByClassName("subtotal-producto")[0];
        subtotalProducto.innerText = (parseFloat(precio) * parseInt(cantidadValor.innerText)).toFixed(2);

        if (button.classList.contains("btn-incrementar")) {
          cantidadValor.innerText = parseInt(cantidadValor.innerText) + 1;
          total.innerText = (parseFloat(total.innerText) + parseFloat(precio)).toFixed(2);
          var subtotalProducto = button.parentElement.parentElement.getElementsByClassName("subtotal-producto")[0];
          subtotalProducto.innerText = (parseFloat(precio) * parseInt(cantidadValor.innerText)).toFixed(2);
      } else if (button.classList.contains("btn-decrementar")) {
          if (parseInt(cantidadValor.innerText) > 1) {
              cantidadValor.innerText = parseInt(cantidadValor.innerText) - 1;
              total.innerText = (parseFloat(total.innerText) - parseFloat(precio)).toFixed(2);
              var subtotalProducto = button.parentElement.parentElement.getElementsByClassName("subtotal-producto")[0];
              subtotalProducto.innerText = (parseFloat(precio) * parseInt(cantidadValor.innerText)).toFixed(2);
          }
      }

    });
}
}
//.select-iva.value
function calculaIVA() {
  var ivaSeleccionado = document.getElementById("total-iva2").value;
  var iva = parseFloat(ivaSeleccionado);
  var totals = document.getElementById("cart-totals").value;
  var totalConIVA = totals + (totals * (iva / 100));
  document.getElementById("cart-totals").innerText = totalConIVA;
}

function estiloDecrementar(){
var buttons = document.getElementsByClassName("cantidad-producto");
for (var i = 0; i < buttons.length; i++) {
    buttons[i].addEventListener("click", function(event) {
        var button = event.target;
        var cantidadValor = button.parentElement.getElementsByClassName("cantidad-valor")[0];
        if (button.classList.contains("btn-decrementar")) {
            if (parseInt(cantidadValor.innerText) === 1) {
                button.setAttribute("style", "color: gray; cursor:default;");
                button.previousElementSibling.setAttribute("disabled", "true");
            } else {
                button.setAttribute("style", "color: black;");
                button.previousElementSibling.removeAttribute("disabled");
            }
        }
    });
}
}

