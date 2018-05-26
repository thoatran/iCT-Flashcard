$(document).ready(function(){

    $("#get-new-password").click(function() {

        let userEmail  = $("#email").val();
        // Submit forgot password
        $.ajax({
            url: "/api/user/forgot_password",
            type: "post",
            data: {"email":userEmail},
            success: function (response) {
                if (response['success'] != false) {
                    alert("Nếu email của bạn đúng, bạn sẽ sớm nhận được mật khẩu mới qua email.");
                    window.location = "/";
                } else {
                    alert("Có lỗi xảy ra. Xin vui lòng thử lại.");
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Có lỗi xảy ra. Xin vui lòng thử lại.")
            }
        });

    });

});