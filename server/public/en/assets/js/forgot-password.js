$(document).ready(function(){

    $("#get-new-password").click(function() {

        let userEmail  = $("#email").val();
        // Submit forgot password
        $.ajax({
            url: "/en/api/user/forgot_password",
            type: "post",
            data: {"email":userEmail},
            success: function (response) {
                if (response['success'] != false) {
                    alert("If your email address is true. You will get the new email soon");
                    window.location = "/";
                } else {
                    alert("Some errors happened.Please check.");
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Some errors happened.Please check.")
            }
        });

    });

});