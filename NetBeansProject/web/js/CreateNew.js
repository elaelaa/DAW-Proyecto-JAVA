window.onload = function(){
        
    var element= document.getElementById("addCert");
    element.addEventListener("click", addDegree, false);
    var element= document.getElementById("addJob");
    element.addEventListener("click", addWork, false);
 
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
'</div>' ;

    document.getElementById("allCertificates").innerHTML = oldhtml + html; 
    
    
                       
}

function addWork(){
    var oldhtml = document.getElementById("allWorks").innerHTML;
    var html = '<div class="certificate"><p><label>Título profesional:' +
                        '<input type="text" name="jobTitle"></label>' +
                    '</p>' +
                    '<p><label>Empresa:' +
                        '<input type="text" name="company"></label>' +
                    '</p>' +
                    '<p><label>Duración:' +
                        '<input type="text" name="duration"></label>' +
                    '</p>' +
                    '</div>';
            
    document.getElementById("allWorks").innerHTML = oldhtml + html; 
}