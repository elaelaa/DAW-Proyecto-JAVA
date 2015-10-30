window.onload = function(){
        
    var element= document.getElementById("addCert");
    element.addEventListener("click", addDegree, false);
    var element= document.getElementById("addJob");
    element.addEventListener("click", addWork, false);
    
    $('body').on('focus',".datepicker", function(){
    $(this).datepicker({
                dateFormat: "yy-mm-dd"
                });}
    );
            
    $.validator.addMethod("lettersonly", function(value, element) 
    {
        return this.optional(element) || /^[a-z ]+$/i.test(value);
    }, "Letters and spaces only please");
    
    $.validator.addMethod("date", function(value, element) 
    {
        return this.optional(element) || /^(19|20)\d\d-(0\d|1[012])-(0\d|1\d|2\d|3[01])$/i.test(value);
    }, "Date in yyyy-mm-dd");
    
     
    $.validator.addMethod("phone", function(value, element) 
    {
        return this.optional(element) || /((((\+[0-9]{2})?)([ ]?)([0-9]{3})([ ]?)([0-9]{3})([ ]?)([0-9]{4}))|(([0-9]{3})([ ]?)([0-9]{3})([ ]?)([0-9]{4})))$/i.test(value);
    }, "Add a valid phone number");
    
    

    $('#candidateform').validate({
        rules: {
            name: {
                required: true,
                lettersonly: true
            },
            lastname: {
                required: true,
                lettersonly: true
            },
            address:{
                required: true
            },
                    
            email: {
                required: true,
                email: true
            },
            phone: {
                required: true,
                phone: true
            },
            birthday:{
                required: true,
                date: true
            },
            title: {
                required: true,
                lettersonly: true
            },
            degreename:{
                required: true,
                lettersonly: true
            },
            organization:{
                required: true,
                lettersonly: true
            },
            dateacquired:{
                required: true, 
                date: true
            },
            salary:{
                required: true
            },
            company:{
                required: true
            },
            description:{
                required: true
            },
            enddate: {
                required: true,
                date: true
            },
            startdate:{
                required: true,
                date: true
            },
        },
        
        messages: {
            name: "Please enter your first name",
            lastname: "Please enter your last name",
            email: "Please enter a valid email address",
            address: "Please fill your address",
            phone:"Please add a valid phone number",
            birthday:"Please add date in yyyy-mm-dd format",
            title:"Please add a valid title, no numbers",
            degreename:"Please add a degree",
            organization:"Please add a university",
            dateacquired:"Please add date in yyyy-mm-dd format", 
            salary:"Please add your salary",
            company:"Please add a company",
            description:"Please add a short description",
            enddate:"Please add date in yyyy-mm-dd format",
            startdate:"Please add date in yyyy-mm-dd format",            
        },
        
        errorElement: "div",
        wrapper: "div",  // a wrapper around the error message
        errorPlacement: function(error, element) {
            error.insertAfter(element);
            error.addClass('errorMessage');  // add a class to the wrapper
            error.css("color", "red");
            error.css("font-style", "italic");
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    }) ;
 
};


function addDegree(){
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
        '<input type="text" name="organization" required></label>' +
    '</p>' +
    '<p><label>Fecha de adquisición: ' +
        '<input type="text" class="datepicker" name="dateacquired" required></label>' +
    '</p>' +
    '<a href="javascript:;" onclick="Remove(this)">Borrar</a>' +
'</div>' ;

    $("#allCertificates").append(html);
    
}

function addWork(){
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
                    '<a href="javascript:;" onclick="Remove(this)">Borrar</a>'+
                    '</div>';
    $('#allWorks').append(html);
    
}

function Remove(link) { 
    //alert("removing" + link.parentNode + "from" + link.parentNode.parentNode);
    link.parentNode.parentNode.removeChild(link.parentNode);
}
