$(document).ready(function() {

    // Get current user info
    $.ajax({
        url: "/en/api/user/getinfo",
        type: "post",
        data: {"username": localStorage.username,
            "token": localStorage.token
        },
        success: function (response) {
            if (response['success'] != false && typeof response['data'] !== 'undefined') {
                return updateUserInfo(response['data']);
            } else {
                alert("Error happened when loading information.Please reload.");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Error happened when loading information.Please reload.");
        }
    });


    // Handle save button
    $('#save-user-info').click(function() {
        showLoadingModal();
        updateQuery = {};
        updateQuery.username = localStorage.username;
        updateQuery.token = localStorage.token;
        updateQuery.newUserInfo = {};
        updateQuery.newUserInfo.email = $('#userinfo-email').val();
        updateQuery.newUserInfo.fullname = $('#userinfo-fullname').val();
        updateQuery.newUserInfo.bio = $('#userinfo-bio').val();
    
        $.ajax({
            url: "/en/api/user/updateinfo",
            type: "post",
            data: updateQuery,
            success: function (response) {
                if (response['success'] != false) {
                    alert("Finished updating information");
                    location.reload();
                } else {
                    alert("Error happened when updating information.Please reload.");
                }
                hideLoadingModal();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                hideLoadingModal();
                alert("Error happened when updating information.Please reload.");
            }
        });
        
    });


    // Handle changing password
    $('#btn-change-password').click(function(event) {

        showLoadingModal();

        event.preventDefault();

        if ($('#inp-old-password').val() == '') {
            alert("Input old password");
            hideLoadingModal();
            return;
        }

        if ($('#inp-new-password').val() == '') {
            alert("Input new password");
            hideLoadingModal();
            return;
        }

        if ($('#inp-new-password').val() != $('#inp-new-password-2').val()) {
            alert("2 times for inputing new password must be matched");
            hideLoadingModal();
            return;
        }

        
        updateQuery = {};
        updateQuery.username = localStorage.username;
        updateQuery.token = localStorage.token;
        updateQuery.oldPassword = $('#inp-old-password').val();
        updateQuery.newUserInfo = {};
        updateQuery.newUserInfo.newPassword = $('#inp-new-password').val();
    
        $.ajax({
            url: "/en/api/user/updateinfo",
            type: "post",
            data: updateQuery,
            success: function (response) {
                if (response['success'] != false) {
                    alert("Finished updating password");
                } else {
                    alert("Error happened when updating password.Please reload.");
                }
                hideLoadingModal();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Error happened when updating password.Please reload.");
                hideLoadingModal();
            }
        });
        
    });
   
});

function updateUserInfo(data) {
    if (data.profile_photo) {
        $('#profile_photo').attr('src', data.profile_photo);
    } else {
        $('#profile_photo').attr('src', '/vi/assets/images/profile_photo/default-profile-photo.png');
    }
   
    $('#userinfo-fullname').val(data.fullname);
    $('#userinfo-bio').val(data.bio);
    $('#userinfo-email').val(data.email);
}


//  Function to upload images
function ajaxUploadFile(base64, callback) {
    data = {
        "username": localStorage.username,
        "token": localStorage.token,
        imageURI: base64
    }

    $.ajax({
        type: "POST",
        url: "/en/api/image/upload",
        data: data,
        dataType: "json",
        success: function (response) {
            console.log(response);
            callback(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });

}

var updateProfilePhoto = function(flashcard_id) {
    showLoadingModal();
    // Update the image
    let file = document.getElementById('btn-select-profilephoto');
    if (file.files.length) {
        var reader = new FileReader();
        reader.onload = function (e) {
            ajaxUploadFile(reader.result, function(response) {

                if (!response.success) {
                    // Clear loading screen
                    hideLoadingModal();
                    return alert("Error happened when uplaoing image");
                }

                // Update info 
                updateQuery = {};
                updateQuery.username = localStorage.username;
                updateQuery.token = localStorage.token;
                updateQuery.newUserInfo = {};
                updateQuery.newUserInfo.profile_photo = response.imageUrl;
    
                $.ajax({
                    url: "/en/api/user/updateinfo",
                    type: "post",
                    data: updateQuery,
                    success: function (response) {
                        if (response['success'] != false) {
                            alert("Finished uploading profile picture");
                            location.reload();
                        } else {
                            alert("Error happened when updating information.Please reload.");
                        }
                        hideLoadingModal();
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        hideLoadingModal();
                        alert("Error happened when updating information.Please reload.");
                    }
                });

            });
        };
        reader.readAsDataURL(file.files[0]);
    }
}
