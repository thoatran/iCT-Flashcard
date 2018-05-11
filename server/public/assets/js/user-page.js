$(document).ready(function() {

    // Get current user info
    $.ajax({
        url: "/api/user/getinfo",
        type: "post",
        data: {"username": localStorage.username,
            "token": localStorage.token
        },
        success: function (response) {
            if (response['success'] != false && typeof response['data'] !== 'undefined') {
                return updateUserInfo(response['data']);
            } else {
                alert("Xảy ra lỗi trong quá trình tải thông tin. Xin vui lòng tải lại trang.");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Xảy ra lỗi trong quá trình tải thông tin. Xin vui lòng tải lại trang.");
        }
    });


    // Handle save button
    $('#save-user-info').click(function() {
        updateQuery = {};
        updateQuery.username = localStorage.username;
        updateQuery.token = localStorage.token;
        updateQuery.newUserInfo = {};
        updateQuery.newUserInfo.email = $('#userinfo-email').val();
        updateQuery.newUserInfo.fullname = $('#userinfo-fullname').val();
        updateQuery.newUserInfo.bio = $('#userinfo-bio').val();
    
        $.ajax({
            url: "/api/user/updateinfo",
            type: "post",
            data: updateQuery,
            success: function (response) {
                if (response['success'] != false) {
                    alert("Cập nhật thông tin thành công.");
                    location.reload();
                } else {
                    alert("Xảy ra lỗi trong quá trình cập nhật thông tin. Xin vui lòng thử lại.");
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Xảy ra lỗi trong quá trình cập nhật thông tin. Xin vui lòng thử lại.");
            }
        });
        
    });


    // Handle changing password
    $('#btn-change-password').click(function(event) {

        event.preventDefault();

        if ($('#inp-old-password').val() == '') {
            alert("Vui lòng nhập mật khẩu cũ.");
            return;
        }

        if ($('#inp-new-password').val() == '') {
            alert("Vui lòng nhập mật khẩu mới.");
            return;
        }

        if ($('#inp-new-password').val() != $('#inp-new-password-2').val()) {
            alert("Hai lần nhập mật khẩu mới phải trùng nhau!");
            return;
        }

        
        updateQuery = {};
        updateQuery.username = localStorage.username;
        updateQuery.token = localStorage.token;
        updateQuery.oldPassword = $('#inp-old-password').val();
        updateQuery.newUserInfo = {};
        updateQuery.newUserInfo.newPassword = $('#inp-new-password').val();
    
        $.ajax({
            url: "/api/user/updateinfo",
            type: "post",
            data: updateQuery,
            success: function (response) {
                if (response['success'] != false) {
                    alert("Cập nhật mật khẩu thành công.");
                } else {
                    alert("Xảy ra lỗi trong quá trình cập nhật mật khẩu. Xin vui lòng thử lại.");
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Xảy ra lỗi trong quá trình cập nhật mật khẩu. Xin vui lòng thử lại.");
            }
        });
        
    });
   
});


function updateUserInfo(data) {
    $('#profile_photo').attr('src', '/assets/images/profile_photo/' + data.profile_photo);
    $('#userinfo-fullname').val(data.fullname);
    $('#userinfo-bio').val(data.bio);
    $('#userinfo-email').val(data.email);
}


