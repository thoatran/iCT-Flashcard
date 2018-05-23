$(document).ready(function(){

    let url = new URI(window.location.href);

    // Global collection id
    var G_collection_id = url.search(true)["collection_id"];
    if (typeof G_collection_id === "undefined") {
        // redirect to collection page if error
        NaviGoto("card-collections");
    }

    // Get collection info
    $.ajax({
        url: "/api/collection/get",
        type: "post",
        data: {"username": localStorage.username,
            "token": localStorage.token,
            "collection_id" : G_collection_id
        },
        success: function (response) {
            if (response['success'] != false) {
                console.log(response);

                $("#input-collection-name").val(response.data.name);
                $("#input-collection-description").val(response.data.description);
                $("#collection-image").attr("src", response.data.photo);

            } else {
                // redirect to collection page if error
                NaviGoto("card-collections");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {

            // redirect to collection page if error
            NaviGoto("card-collections");
        }
    });


    //  Function to upload files
    function ajaxUploadFile(base64, callback) {
        data = {
            "username": localStorage.username,
            "token": localStorage.token,
            imageURI: base64
        }

        $.ajax({
            type: "POST",
            url: "/api/image/upload",
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


    // Handle change info button
    $("#btn-save-collection-info").click(function() {

        // Update the image
        let file = document.getElementById('input-collection-fphoto');

        if (file.files.length) {
            var reader = new FileReader();
            reader.onload = function (e) {
                ajaxUploadFile(reader.result, function(response) {

                    if (!response.success) {
                        return alert("Có lỗi xảy ra khi upload hình ảnh");
                    }
                    
                    // Update info 
                    $.ajax({
                        url: "/api/collection/update",
                        type: "post",
                        data: {"username": localStorage.username,
                            "collection_id": G_collection_id,
                            "token": localStorage.token,
                            "name" : $("#input-collection-name").val(),
                            "description" : $("#input-collection-description").val(),
                            "photo": response.imageUrl
                        },
                        success: function (response) {
                            if (response['success'] != false) {
                                // Reload the page
                                location.reload();
                            } else {
                                alert("Có lỗi xảy ra. Vui lòng thử lại.");
                            }
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            alert("Có lỗi xảy ra. Vui lòng thử lại.");
                        }
                    });

                });
            };
            reader.readAsDataURL(file.files[0]);
        } else {
            // Update info 
            $.ajax({
                url: "/api/collection/update",
                type: "post",
                data: {"username": localStorage.username,
                    "collection_id": G_collection_id,
                    "token": localStorage.token,
                    "name" : $("#input-collection-name").val(),
                    "description" : $("#input-collection-description").val(),
                },
                success: function (response) {
                    if (response['success'] != false) {
                        alert("Đã thay đổi thông tin bộ flashcard.");
                    } else {
                        alert("Có lỗi xảy ra. Vui lòng thử lại.");
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert("Có lỗi xảy ra. Vui lòng thử lại.");
                }
            });
        }
        

    });


    // Chane focus to another card
    $(".flashcard-wrapper").click(function(){
         
        // Remove focus on old card
        $(".flashcard-wrapper.active .toolbox").removeClass("active");
        $(".flashcard-wrapper.active").removeClass("active");

        // Change focus to clicked card
        $(this).addClass("active");
        $(".flashcard-wrapper.active .toolbox").addClass("active");
    });


});