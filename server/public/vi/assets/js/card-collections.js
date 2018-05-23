$(document).ready(function(){

    // Function to create Collection
    $("#new-collection").click(function(){

        // Create a new collection
        $.ajax({
            url: "/api/collection/create",
            type: "post",
            data: {"username": localStorage.username,
                "token": localStorage.token
            },
            success: function (response) {
                if (response['success'] != false) {
                    window.location.href = "/vi/navi.html?get=edit-card-collection&collection_id=" + response.collection_id;
                } else {
                    alert("Có lỗi xảy ra.")
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Có lỗi xảy ra.")
            }
        });
        
    });

});