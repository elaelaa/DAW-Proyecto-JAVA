
window.onload = function(){
    $('body').on('focus',".datepicker", function(){
        $(this).datepicker({
                dateFormat: "yy-mm-dd"
            });
        }
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
        return this.optional(element) || /^((((\+[0-9]{2})?)( ?)([0-9]{3})( ?)([0-9]{3})( ?)([0-9]{4}))|(([0-9]{3})( ?)([0-9]{3})( ?)([0-9]{4})))$/i.test(value);
    }, "Add a valid phone number");
    
    

    $('#createform').validate({
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
            currentTitle: {
                required: true,
                lettersonly: true
            },
            startDate: {
                required: true,
                date: true
            },
            professionalTitle: {
                required: true,
                lettersonly: true
            },
            currentSalary: {
              required: true
            },
            vacations: {
                required: true
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
            jobTitle: {
              required: true,
              lettersonly: true
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
            date:{
                required: true,
                date: true
            },
            feedback:{
                required: true
            },
            platform:{
                required: true
            },
            candidateId:{
                required: true
            },
        },
        
        messages: {
            name: "Please enter your first name",
            lastname: "Please enter your last name",
            email: "Please enter a valid email address",
            address: "Please fill your address",
            phone:"Please add a valid phone number",
            birthday:"Please add date in yyyy-mm-dd format",
            currenttitle: "Please add a valid title, no numbers",
            startDate: "Please add date in yyyy-mm-dd format",
            professionalTitle:"Please add a valid title, no numbers",
            currentSalary: "Please add current salary",
            vacations: "Please add number of vacation days",
            degreename:"Please add a degree",
            organization:"Please add a university",
            dateacquired:"Please add date in yyyy-mm-dd format", 
            salary:"Please add your salary",
            company:"Please add a company",
            jobTitle: "Please add job title",
            description:"Please add a short description",
            enddate:"Please add date in yyyy-mm-dd format",
            date:"Please add date in yyyy-mm-dd format", 
            feedback:"Please add feedback",
            platform:"Please add a platform",
            candidateId:"Please add the candidate Id",
          
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
 
}


function Remove(link) { 
    //alert("removing" + link.parentNode + "from" + link.parentNode.parentNode);
    link.parentNode.parentNode.removeChild(link.parentNode);
}
