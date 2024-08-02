const NAVEGADOR = "Firefox";
const tvida = [10000, 30000, 5000, 20000];
const rebaja = ['4%','8%','21%']
const bannerWidth = 480;
const bannerHeight = 320;
let currentDiscount = null;


var mostrarBanner = true;

function showBanner() {
    if (navigator.userAgent.indexOf(NAVEGADOR) === -1) { //<--comprobamos que el navegador sea firefox
        return;
    }
    if (!mostrarBanner) {
        return;
    }
    var banner = document.getElementById("banner");
    var descuento = document.getElementById("banner-descuento");
    var descuentos = rebaja; 
    if(currentDiscount === null){
        currentDiscount = descuentos[Math.floor(Math.random() * descuentos.length)];
        descuento.innerHTML = currentDiscount;
    }
    var x = Math.floor(Math.random() * (window.innerWidth - bannerWidth)); // random x position
    var y = Math.floor(Math.random() * (window.innerHeight - bannerHeight)); // random y position

    banner.style.left = x + "px";
    banner.style.top = y + "px";
    descuento.innerHTML = descuentos[Math.floor(Math.random() * descuentos.length)];
    banner.style.display = "block";
    banner.style.animation = "resplandorAnimation 2s infinite,moverBanner 10s linear, rebotarBanner 10s linear, moverBanner2 10s linear, rebotarBanner2 10s linear";
    banner.style.animationFillMode = "forwards";
    banner.style.animationIterationCount = "4";
    setTimeout(function() {
    banner.style.animation = "resplandorAnimation 2s infinite,rebotarBanner 10s linear, moverBanner2 10s linear, rebotarBanner2 10s linear";
    }, tvida[Math.floor(Math.random() * tvida.length)]);//<--tiempo de vida del banner
    banner.addEventListener("click", function() {
        mostrarBanner = false;
        banner.style.display = "none";
        currentDiscount = null;
        alert("Descuento: " + descuento.innerHTML + "%");
    });
}
setInterval(showBanner, 300000);  //<--tiempo de aparicion del banner
