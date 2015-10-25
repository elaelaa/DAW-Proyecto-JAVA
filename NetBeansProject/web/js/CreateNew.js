window.onload = function(){
        
    var element= document.getElementById("addCert");
    element.addEventListener("click", addDegree, false);
    var element= document.getElementById("addJob");
    element.addEventListener("click", addWork, false);
    
    $('body').on('focus',".datepicker", function(){
    $(this).datepicker();}); 
 
};


function addDegree(){
    var oldhtml = document.getElementById("allCertificates").innerHTML;
    var html =  '<div class="certificate">' +
       ' <p><label>Tipo: <select name="type" value="profesional" required>' +
        ' <option value="profesional">Titulo profesional</option> ' +
            '<option value="posgrado">Posgrado</option>' +
            '<option value="certificado">Certificado</option>' +
        '</select>' +
        '</label>' +
    '</p>' +
    '<p><label>Nombre del certificado:' + 
            '<input type="text" name="degreename" required></label>' +
    '</p>' +
    '<p><label>Organización: ' +
        '<input type="text" name="university" required></label>' +
    '</p>' +
    '<p><label>Fecha de adquisición: ' +
        '<input type="text" class="datepicker" name="dateacquired" required></label>' +
    '</p>' +
    '<a href="#" onclick="Remove(this)">Borrar</a>' +
'</div>' ;

    document.getElementById("allCertificates").innerHTML = oldhtml + html; 
    
    
                       
}

function addWork(){
    var oldhtml = document.getElementById("allWorks").innerHTML;
    var html = '<div class="certificate"><p><label>Título profesional:' +
                        '<input type="text" name="jobTitle" required></label>' +
                    '</p>' +
                    '<p><label>Empresa:' +
                        '<input type="text" name="company" required></label>' +
                    '</p>' +
                    '<p><label>Descripción:' +
                        '<input type="text" rows="5" name="description" required></label>' +
                    '</p>' +
                    '<p><label>Salario: ' +
                        '<input type="number" name="salary" required></label>' +
                    '</p>' +
                    '<p><label>Fecha de inicio: ' +
                        '<input type="text" class="datepicker" name="startdate" required></label>' +
                    '</p>' +
                    '<p><label>Fecha final: ' +
                        '<input type="text" class="datepicker" name="enddate" required></label>' +
                    '</p>' +
                    '<a href="#" onclick="Remove(this)">Borrar</a>'+
                    '</div>';
            
    document.getElementById("allWorks").innerHTML = oldhtml + html; 
}

function Remove(link) { 
    //alert("removing" + link.parentNode + "from" + link.parentNode.parentNode);
    link.parentNode.parentNode.removeChild(link.parentNode);
}
