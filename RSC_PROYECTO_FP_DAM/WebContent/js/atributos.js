
const puntosARepartir = document.getElementById("puntosARepartir");
const fuerza = document.getElementById("fuerza");
const agilidad = document.getElementById("agilidad");
const vigor = document.getElementById("vigor");
const magia = document.getElementById("magia");
const labia = document.getElementById("labia");



const  puntosARepartirIniciales = puntosARepartir.value;
const fuerzaValueMin = fuerza.min;
const agilidadValueMin = agilidad.min;
const vigorValueMin = vigor.min;
const magiaValueMin = magia.min;
const labiaValueMin = labia.min;
let fuerzaValueAnterior = fuerza.value;
let agilidadValueAnterior = agilidad.value;
let vigorValueAnterior = vigor.value;
let magiaValueAnterior = magia.value;
let labiaValueAnterior = labia.value;



fuerza.addEventListener("click", () => {
    
	fuerzaValueAnterior = parseInt(fuerzaValueAnterior);
	fuerza.value = parseInt(fuerza.value);
		
	if (fuerzaValueAnterior < fuerza.value) {
		fuerza.value = fuerzaValueAnterior + 1; 
        puntosARepartir.value--; 
    } else if (fuerzaValueAnterior > fuerza.value) {
    	fuerza.value = fuerzaValueAnterior - 1;
    	puntosARepartir.value++;
    }
    if (puntosARepartir.value <= 0) {
        detenerValoresMax()
	} else if (puntosARepartir.value >= puntosARepartirIniciales){
	    detenerValoresMin();
    } else if (!(puntosARepartir.value <= 0) && !(puntosARepartir.value >= puntosARepartirIniciales)){
        liberarValores();
    }
    
    fuerzaValueAnterior = fuerza.value;
    
    let numero = fuerza.value;
    let contador = 0;
    while(numero >= 1) {
    	contador++;
    	numero = numero/10;
    }
    if (contador < 2) {
    	fuerza.value = "0" + fuerza.value;
    }
    
});

agilidad.addEventListener("click", () => {
    
	agilidadValueAnterior = parseInt(agilidadValueAnterior);
	agilidad.value = parseInt(agilidad.value);
	
    if (agilidadValueAnterior < agilidad.value) {
    	agilidad.value = agilidadValueAnterior + 1; 
        puntosARepartir.value--; 
    } else if (agilidadValueAnterior > agilidad.value) {
    	agilidad.value = agilidadValueAnterior - 1;
        puntosARepartir.value++;
    }
    if (puntosARepartir.value <= 0) {
	    detenerValoresMax()
	} else if (puntosARepartir.value >= puntosARepartirIniciales){
        detenerValoresMin();
    } else if (!(puntosARepartir.value <= 0) && !(puntosARepartir.value >= puntosARepartirIniciales)){
        liberarValores();
    }
    
    agilidadValueAnterior = agilidad.value;
    
    let numero = agilidad.value;
    let contador = 0;
    while(numero >= 1) {
    	contador++;
    	numero = numero/10;
    }
    if (contador < 2) {
    	agilidad.value = "0" + agilidad.value;
    }
    
});

vigor.addEventListener("click", () => {

	vigorValueAnterior = parseInt(vigorValueAnterior);
	vigor.value = parseInt(vigor.value);
	
    if (vigorValueAnterior < vigor.value) {
    	vigor.value = vigorValueAnterior + 1; 
        puntosARepartir.value--; 
    } else if (vigorValueAnterior > vigor.value) {
    	vigor.value = vigorValueAnterior - 1;
        puntosARepartir.value++;
    }
    if (puntosARepartir.value <= 0) {
	    detenerValoresMax()
	} else if (puntosARepartir.value >= puntosARepartirIniciales){
        detenerValoresMin();
    } else if (!(puntosARepartir.value <= 0) && !(puntosARepartir.value >= puntosARepartirIniciales)){
        liberarValores();
    }
    
    vigorValueAnterior = vigor.value;
    
    let numero = vigor.value;
    let contador = 0;
    while(numero >= 1) {
    	contador++;
    	numero = numero/10;
    }
    if (contador < 2) {
    	vigor.value = "0" + vigor.value;
    }
    
});

magia.addEventListener("click", () => {
    
	magiaValueAnterior = parseInt(magiaValueAnterior);
	magia.value = parseInt(magia.value);
	
	if (magiaValueAnterior < magia.value) {
		magia.value = magiaValueAnterior + 1; 
        puntosARepartir.value--; 
    } else if (magiaValueAnterior > magia.value) {
    	magia.value = magiaValueAnterior - 1;
        puntosARepartir.value++;
    }
    if (puntosARepartir.value <= 0) {
	    detenerValoresMax()
	} else if (puntosARepartir.value >= puntosARepartirIniciales){
	    detenerValoresMin();
    } else if (!(puntosARepartir.value <= 0) && !(puntosARepartir.value >= puntosARepartirIniciales)){
        liberarValores();
    }
    
    magiaValueAnterior = magia.value;
    
    let numero = magia.value;
    let contador = 0;
    while(numero >= 1) {
    	contador++;
    	numero = numero/10;
    }
    if (contador < 2) {
    	magia.value = "0" + magia.value;
    }
    
});

labia.addEventListener("click", () => {
    
	labiaValueAnterior = parseInt(labiaValueAnterior);
	labia.value = parseInt(labia.value);
	
    if (labiaValueAnterior < labia.value) {
    	labia.value = labiaValueAnterior + 1; 
        puntosARepartir.value--; 
    } else if (labiaValueAnterior > labia.value) {
    	labia.value = labiaValueAnterior - 1;
        puntosARepartir.value++;
    }
    if (puntosARepartir.value <= 0) {
	    detenerValoresMax()
	} else if (puntosARepartir.value >= puntosARepartirIniciales){
        detenerValoresMin();
    } else if (!(puntosARepartir.value <= 0) && !(puntosARepartir.value >= puntosARepartirIniciales)){
        liberarValores();
    }
    
    labiaValueAnterior = labia.value;
    
    let numero = labia.value;
    let contador = 0;
    while(numero >= 1) {
    	contador++;
    	numero = numero/10;
    }
    if (contador < 2) {
    	labia.value = "0" + labia.value;
    }
    
});



function detenerValoresMax() {
    fuerza.max = fuerza.value;
    fuerza.min = fuerzaValueMin;
    agilidad.max = agilidad.value;
    agilidad.min = agilidadValueMin;
    vigor.max = vigor.value;
    vigor.min = vigorValueMin;
    magia.max = magia.value;
    magia.min = magiaValueMin;
    labia.max = labia.value;
    labia.min = labiaValueMin;
}
function detenerValoresMin() {
    fuerza.max = 100;
    fuerza.min = fuerza.value;
    agilidad.max = 100;
    agilidad.min = agilidad.value;
    vigor.max = 100;
    vigor.min = vigor.value;
    magia.max = 100;
    magia.min = magia.value;
    labia.max = 100;
    labia.min = labia.value
}
function liberarValores() {
    fuerza.max = 100;
    fuerza.min = fuerzaValueMin;
    agilidad.max = 100;
    agilidad.min = agilidadValueMin;
    vigor.max = 100;
    vigor.min = vigorValueMin;
    magia.max = 100;
    magia.min = magiaValueMin;
    labia.max = 100;
    labia.min = labiaValueMin;
}