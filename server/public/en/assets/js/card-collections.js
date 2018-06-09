var deleteCollection = function(collection_id, collection_name) {
    // Confirm box
    var txt;
    var r = confirm("You want to delete the collection: \""+collection_name+"\" ?");
    if (r == true) {
        // Create a new collection
        $.ajax({
            url: "/en/api/collection/delete",
            type: "post",
            data: {"username": localStorage.username,
                "token": localStorage.token,
                "collection_id": collection_id
            },
            success: function (response) {
                if (response['success'] != false) {
                    // refresh page
                    location.reload();
                } else {
                    alert("Some errors happened.")
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Some errors happened.")
            }
        });
    }
}

$(document).ready(function(){

    // Function to create Collection
    $("#new-collection").click(function(){

        // Create a new collection
        $.ajax({
            url: "/en/api/collection/create",
            type: "post",
            data: {"username": localStorage.username,
                "token": localStorage.token
            },
            success: function (response) {
                if (response['success'] != false) {
                    window.location.href = "/vi/navi.html?get=edit-card-collection&collection_id=" + response.collection_id;
                } else {
                    alert("Some errors happened")
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Some errors happened")
            }
        });
        
    });

    

    // Create a new collection
    $.ajax({
        url: "/en/api/collection/getall",
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
                                <p class="m-0">Đã thuộc: `+collectionList[i].remember_score+`%</p>
                                <div class="progress mb-2">
                                    <div class="progress-bar" style="width:`+collectionList[i].remember_score+`%"></div>
                                </div>

                                <div class="collection-actions">
                                    <a class="btn btn-light" href="/vi/navi.html?get=view-card-collection&collection_id=`+collectionList[i].id+`">
                                    <i class="fa fa-eye" aria-hidden="true"></i>
                                    Show</a> |
                                    <a class="btn btn-light" href="/vi/navi.html?get=review-card-collection&collection_id=`+collectionList[i].id+`">
                                    <i class="fa fa-leanpub" aria-hidden="true"></i>
                                    Review
                                    </a> |
                                    <a class="btn btn-light" href="/vi/navi.html?get=edit-card-collection&collection_id=`+collectionList[i].id+`">
                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                    Edit
                                    </a> |
                                    <a class="btn btn-light" href="javascript:void" onclick="deleteCollection(`+collectionList[i].id+`,'` + collectionList[i].name + `' )">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                    Delete
                                    </a>
                                </div>
                                
                                </div>
                            </div>
                        </div>
                        
                        `
                    );

                }

                if (!collectionList || collectionList.length == 0) {
                    $("#collection-list-view").html(`
                        <div class="alert alert-dark">
                            You haven't had any collection. Please create new one!
                        </div>
                    `);
                }

            } else {
                alert("Some errors happened")
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Some errors happened")
        }
    });

    
});