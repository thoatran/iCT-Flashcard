$(function() {

	$( "#register-form" ).submit(function( event ) {
		event.preventDefault();

        $('#error-message').hide();
		$('#please-wait').show();

		let userInfo = {"username": $('#username').val(),
            "fullname": $('#fullname').val(),
            "password": $('#password').val(),
            "email": $('#email').val()
		};

		$.ajax({
			url: "/api/user/register",
			type: "post",
			data: userInfo,
			success: function (response) {

				$('#please-wait').hide();
				if (response['success'] == false) {
                    if (typeof response.message != 'undefined') {
                        $('#error-message').html(response.message);
				        $('#error-message').show();
                    } else {
                        $('#error-message').html('Lỗi không xác định.');
				        $('#error-message').show();
                    }
				} else {
					window.location = "/login.html"; 
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				
                $('#please-wait').hide();
                $('#error-message').html('Lỗi không xác định.');
				$('#error-message').show();
			}
		});
		
	});
	
});