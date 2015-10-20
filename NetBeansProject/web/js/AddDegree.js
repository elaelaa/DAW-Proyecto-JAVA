window.onload = function(){
        
    var element= document.getElementById("add");
    element.addEventListener("click", addDegree, false);   
 
};


function addDegree(){
    var oldhtml = document.getElementById("allCertificates").innerHTML;
    var html =  '<div class="certificate">' +
       ' <p><label>Tipo: <select name="type" value="profesional">' +
        ' <option value="profesional">Titulo profesional</option> ' +
            '<option value="posgrado">Posgrado</option>' +
            '<option value="certificado">Certificado</option>' +
        '</select>' +
        '</label>' +
    '</p>' +
    '<p><label>Nombre del certificado:' + 
            '<input type="text" name="degreename"></label>' +
    '</p>' +
    '<p><label>Universidad: ' +
        '<input type="text" name="university"></label>' +
    '</p>' +
'</div>'; 

    document.getElementById("allCertificates").innerHTML = oldhtml + html; 

                       
}