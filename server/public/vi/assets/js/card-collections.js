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

    // Create a new collection
    $.ajax({
        url: "/api/collection/getall",
        type: "post",
        data: {"username": localStorage.username,
            "token": localStorage.token
        },
        success: function (response) {
            if (response['success'] != false) {
                
                let collectionList = response.data;
                for (i = 0; i < collectionList.length; i++) {

                    $("#collection-list-view").append(
                        `
                        <div class="flashcard-collection col-md-3 p-1">
                            <div class="card" style="width: 100%;">
                                <img class="card-img-top" src="`+collectionList[i].photo+`">
                                <div class="card-body">
                                <h4 class="card-title">`+collectionList[i].name+`</h4>
                                    <p class="card-text">`+collectionList[i].description+`</p>
                                    
                                    <div class="collection-actions">
                                        <a href="/vi/navi.html?get=view-card-collection&collection_id=`+collectionList[i].id+`">Xem</a> |
                                        <a href="/vi/navi.html?get=review-card-collection&collection_id=`+collectionList[i].id+`">Ôn tập</a> |
                                        <a href="/vi/navi.html?get=edit-card-collection&collection_id=`+collectionList[i].id+`">Chỉnh sửa</a> |
                                        <a href="alert('not implemented')">Xoá</a>
                                    </div>
                                
                                </div>
                            </div>
                        </div>
                        
                        `
                    );

                }

            } else {
                alert("Có lỗi xảy ra.")
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Có lỗi xảy ra.")
        }
    });

});