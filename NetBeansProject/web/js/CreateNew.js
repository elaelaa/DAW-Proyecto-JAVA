window.onload = function(){
        
    var element= document.getElementById("addCert");
    element.addEventListener("click", addDegree, false);
    var element= document.getElementById("addJob");
    element.addEventListener("click", addWork, false);
    
    $('body').on('focus',".datepicker", function(){
    $(this).datepicker();});

    $.validator.addMethod("lettersonly", function(value, element) 
    {
        return this.optional(element) || /^[a-z ]+$/i.test(value);
    }, "Letters and spaces only please");
    
    $.validator.addMethod("date", function(value, element) 
    {
        return this.optional(element) || /^(?:(?:(?:0?[13578]|1[02])(\/)31)\1|(?:(?:0?[1,3-9]|1[0-2])(\/)(?:29|30)\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:0?2(\/)29\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\/)(?:0?[1-9]|1\d|2[0-8])\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$/i.test(value);
    }, "Date in mm/ddyyyy");
    
     $.validator.addMethod("phone", function(value, element) 
    {
        return this.optional(element) || /((((\+[0-9]{2})?)([ .-]?)([0-9]{3})([ .-]?)([0-9]{3})([ .-]?)([0-9]{4}))|(([0-9]{3})([ .-]?)([0-9]{3})([ .-]?)([0-9]{4})))$/i.test(value);
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
                date: true
            },
            title: {
                lettersonly: true
            },
            degreename:{
                required: true,
                lettersonly: true
            },
            university:{
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
            birthday:"Please add date in mm/dd/yyyy format",
            title:"Please add a valid title, no numbers",
            degreename:"Please add a degree",
            university:"Please add a university",
            dateacquired:"Please add date in mm/ddyyyy format", 
            salary:"Please add your salary",
            company:"Please add a company",
            description:"Please add a short description",
            enddate:"Please add date in mm/dd/yyyy format",
            startdate:"Please add date in mm/dd/yyyy format",            
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
