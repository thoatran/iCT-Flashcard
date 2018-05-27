// Each card has an order in a collection.
// This function compare two cards' order
function compareCardOrder(a,b) {
    if (a.order < b.order)
        return -1;
    if (a.order > b.order)
        return 1;
    return 0;
}
  
  
$(document).ready(function(){

    // Global variables
    var url = new URI(window.location.href);
    var G_collection_id = url.search(true)["collection_id"];

    // Error handling
    if (typeof G_collection_id === "undefined") {
        // redirect to collection page if error
        NaviGoto("card-collections");
    }

    // =============== FUNCTIONS TO EDIT LOCAL FLASHCARD LIST ================

    var LocalFlashcardList = {};
    LocalFlashcardList.init = function() {
        LocalFlashcardList.data = [];
    }
    LocalFlashcardList.remoteSync = function(cb) {
        // Assign right data
        for (i = 0; i < this.data.length; i++) {
            this.data.order = i;
        }

        // Create new flashcard from remote
        $.ajax({
            url: "/api/flashcard/updateall",
            type: "post",
            data: {"username": localStorage.username,
                "collection_id": G_collection_id,
                "token": localStorage.token,
                "data": LocalFlashcardList.data
            },
            success: function (response) {

                if (!response.success) {
                    alert("Có lỗi xảy ra. Vui lòng thử lại.");
                } else {
                    alert("Đã lưu toàn bộ flashcard thành công!");
                }

                cb();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Có lỗi xảy ra. Vui lòng thử lại.");
                cb();
            }
        });
        cb();
    }
    LocalFlashcardList.add = function(inp, shouldPrepend, insertIndex) {
        // Create a new flashcard in local list
        let flashcard = {};
        flashcard.id = inp.id;
        flashcard.word = inp.word;
        flashcard.pronunciation = inp.pronunciation;
        flashcard.meaning = inp.meaning;
        flashcard.image = inp.image;
        flashcard.remember_score = inp.remember_score;

        if (typeof insertIndex == "number") {
            this.data.splice(insertIndex, 0, flashcard);
            this.addFlashcardDOM(flashcard, true, insertIndex);
        } else if (shouldPrepend) {
            this.data.unshift(flashcard);
            this.addFlashcardDOM(flashcard, true);
        } else {
            this.data.push(flashcard);
            this.addFlashcardDOM(flashcard, false);
        }

        // Handle focus event
        $("#flashcard-tree").on('click', '#flashcard-' + flashcard.id, function(){
            LocalFlashcardList.changeCardFocus(flashcard.id);
        });

        // Handle add card
        $("#flashcard-tree").on('click', '#flashcard-' + flashcard.id + ' .btn-add-card', function(){
            LocalFlashcardList.createNewCard(flashcard.id);  
        });

        // Handle delete event
        $("#flashcard-tree").on('click', '#flashcard-' + flashcard.id + " button.delete", function(){
            let txt;
            let r = confirm("Bạn muốn xóa flashcard của từ: \""+flashcard.word+"\" ?");
            if (r == true) {
                LocalFlashcardList.delete(flashcard.id);
            }
        });

        // Handle edit word event
        $("#flashcard-tree").on('change', '#flashcard-' + flashcard.id + "  .inp-word", function(){
            LocalFlashcardList.change(flashcard.id, "word", this.value);
        });

        // Handle edit word event
        $("#flashcard-tree").on('change', '#flashcard-' + flashcard.id + "  .inp-pronon", function(){
            LocalFlashcardList.change(flashcard.id, "pronunciation", this.value);
        });

        // Handle edit word event
        $("#flashcard-tree").on('change', '#flashcard-' + flashcard.id + "  .inp-meaning", function(){
            LocalFlashcardList.change(flashcard.id, "meaning", this.value);
        });
        
    }
    LocalFlashcardList.delete = function(flashcard_id) {
        for (i = 0; i < this.data.length; i++) {
            if (this.data[i].id == flashcard_id) {
                // Remove from locallist
                this.data.splice(i, 1);
                // Remove from DOM
                this.removeFlashcardDOM(flashcard_id);
                return
            }
        }
    }
    LocalFlashcardList.createNewCard = function(currentCardId) {
        // Find insert index
        let insertIndex = 0;
        for (i = 0; i < this.data.length; i++) {
            if (this.data[i].id == currentCardId) {
                insertIndex = i;
                break;
            }
        }

        // Create new flashcard from remote
        $.ajax({
            url: "/api/flashcard/create",
            type: "post",
            data: {"username": localStorage.username,
                "collection_id": G_collection_id,
                "token": localStorage.token
            },
            success: function (response) {

                if (!response.success) {
                    alert("Có lỗi xảy ra. Vui lòng thử lại.");
                } else {
                
                    let flashcard = {};
                    flashcard.id = response.flashcardInfo.id;
                    flashcard.word = "";
                    flashcard.meaning = "";
                    flashcard.image = "";
                    flashcard.pronunciation = "";
                    LocalFlashcardList.add(flashcard, true, insertIndex);
                    LocalFlashcardList.changeCardFocus(flashcard.id);

                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Có lỗi xảy ra. Vui lòng thử lại.");
            }
        });
    }
    LocalFlashcardList.change = function(flashcard_id, field, newValue) {
        for (i = 0; i < this.data.length; i++) {
            if (this.data[i].id == flashcard_id) {
                this.data[i][field] = newValue;
                return
            }
        }
    }
    LocalFlashcardList.changeCardFocus = function(flashcard_id) {
        // Remove focus on old card
        $(".flashcard-wrapper.active .toolbox").removeClass("active");
        $(".flashcard-wrapper.active").removeClass("active");

        // Focus on new card
        $("#flashcard-"+flashcard_id).addClass("active");
        $("#flashcard-"+flashcard_id+" .toolbox").addClass("active");

        // Scroll to focused card
        if ($("#flashcard-"+flashcard_id).length > 0) {
            $('html, body').animate({
                scrollTop: $("#flashcard-"+flashcard_id).offset().top
            }, 500);
            $.scrollTo('#flashcard-tree');
            $('#flashcard-tree').scrollTo("#flashcard-"+flashcard_id);
        }
    }
    LocalFlashcardList.removeFlashcardDOM = function (flashcard_id) {
        $("#flashcard-" + flashcard_id).remove();
    }
    LocalFlashcardList.addFlashcardDOM = function(flashcardInfo, shouldPrepend, insertIndex) {

        // Set default photo if not specify a flashcard photo
        if (typeof flashcardInfo.image == "undefined" || flashcardInfo.image == "") {
            flashcardInfo.image = "/vi/assets/images/placeholder333x333.png";
        }

        let DOMElem = 
        `<div class="flashcard-wrapper" id="flashcard-`+flashcardInfo.id+`">
            <h2 class="card-no">&nbsp</h2>
            <div class="front p-1">
                <div class="card">
                    <div style="position: relative;">
                        <img class="card-img-top" src="`+flashcardInfo.image+`" alt="Card image">
                        <button onclick="$('#input-flashcard-fphoto-`+flashcardInfo.id+`').click()" class="btn btn-light" style="position: absolute;
                                    bottom: 0;
                                    left: 0;
                                    opacity: 0.5;">
                            <i class="fa fa-pencil" aria-hidden="true"></i>
                            Thay hình
                        </button>
                        <input card style="display: none" type="file" class="form-control-file"  id="input-flashcard-fphoto-`+flashcardInfo.id+`"
                        onchange="$.fn.updateFlashcardPhoto(`+flashcardInfo.id+`)">
                    </div>
                    <div class="card-body">
                        <input type="text" class="inp-word form-control" placeholder="Từ vựng">
                        <input type="text" class="inp-pronon form-control mt-1" placeholder="Phát âm">
                    </div>
                    <div class="card-footer text-muted flip-card-btn">
                        Mặt trước
                    </div>
                </div>
            </div>
            <div class="back p-1">
                <div class="card">
                    <div class="card-body">
                            <textarea class="inp-meaning form-control" rows="9" placeholder="Ý nghĩa"></textarea>
                    </div>
                    <div class="card-footer text-muted flip-card-btn">
                        Mặt sau
                    </div>
                </div>
            </div>
            <div class="toolbox">
                <button flashcardId="`+flashcardInfo.id+`" class="btn btn-success btn-add-card" >
                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                </button>
                <button class="delete btn btn-danger">
                        <i class="fa fa-trash-o" aria-hidden="true"></i>
                </button>
                <!--<button class="btn btn-info">
                        <i class="fa fa-arrow-circle-up" aria-hidden="true"></i>
                </button>
                <button class="btn btn-info">
                        <i class="fa fa-arrow-circle-down" aria-hidden="true"></i>
                </button>-->
            </div>

            <div style="clear: both;"></div>
        </div>
        `;


        if (typeof insertIndex == "number" && insertIndex > 0) {
            $('#flashcard-tree .flashcard-wrapper:eq('+(insertIndex-1)+')').after(DOMElem);
        } else if (insertIndex === 0) {
            $("#flashcard-tree").prepend(DOMElem);
        } else if (shouldPrepend) {
            $("#flashcard-tree").prepend(DOMElem);
        } else {
            $("#flashcard-tree").append(DOMElem);
        }

        $("#flashcard-"+flashcardInfo.id+" .inp-word").val(flashcardInfo.word);
        $("#flashcard-"+flashcardInfo.id+" .inp-pronon").val(flashcardInfo.pronunciation);
        $("#flashcard-"+flashcardInfo.id+" .inp-meaning").val(flashcardInfo.meaning);

    }

    LocalFlashcardList.init();

    // ==========================================================

    // =============== LOAD PAGE CONTENT ================

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


    // Load Flashcards
    $.ajax({
        url: "/api/flashcard/getall",
        type: "post",
        data: {"username": localStorage.username,
            "collection_id": G_collection_id,
            "token": localStorage.token
        },
        success: function (response) {

            console.log(response);
            
            if (!response.success) {
                alert("Có lỗi xảy ra. Vui lòng thử lại.");
            } else {

                // Re-init local list
                LocalFlashcardList.init();

                // Re-order by order number
                response.data.sort(compareCardOrder);

                // Add card to the list
                for (i = 0; i < response.data.length; i++) {
                    LocalFlashcardList.add(response.data[i]);
                }

                $(".flashcard-wrapper:first").addClass("active");
                $(".flashcard-wrapper .toolbox:first").addClass("active");

            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("Có lỗi xảy ra. Vui lòng thử lại.");
        }
    });


    // =============== END OF PAGE CONTENT ================

    //  Function to upload images
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


    // Handle collection feature photo change
    $("#input-collection-fphoto").change(function() {
        // Loading screen
        $('#loading-modal').modal('show');

        // Update the image
        let file = document.getElementById('input-collection-fphoto');

        if (file.files.length) {
            var reader = new FileReader();
            reader.onload = function (e) {
                ajaxUploadFile(reader.result, function(response) {

                    if (!response.success) {
                        // Clear loading screen
                        hideLoadingModal();
                        return alert("Có lỗi xảy ra khi upload hình ảnh");
                    }

                    let imageUrl = response.imageUrl;
                    
                    // Update info 
                    $.ajax({
                        url: "/api/collection/update",
                        type: "post",
                        data: {"username": localStorage.username,
                            "collection_id": G_collection_id,
                            "token": localStorage.token,
                            "name" : $("#input-collection-name").val(),
                            "description" : $("#input-collection-description").val(),
                            "photo": imageUrl
                        },
                        success: function (response) {
                            if (response['success'] != false) {
                                // Update DOM
                                $("#collection-image").attr("src", imageUrl);
                            } else {
                                alert("Có lỗi xảy ra. Vui lòng thử lại.");
                            }
                            // Clear loading screen
                            hideLoadingModal();
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            // Clear loading screen
                            hideLoadingModal();
                            alert("Có lỗi xảy ra. Vui lòng thử lại.");
                        }
                    });

                });
            };
        }
            reader.readAsDataURL(file.files[0]);
    });

    // Handle save button
    $("#btn-save-collection-info").click(function() {
        // Loading screen
        showLoadingModal();
        syncCollectionInfo(function() {
            LocalFlashcardList.remoteSync(function(){
                hideLoadingModal();
            })
        });
    });

    var syncCollectionInfo = function (cb) {
        // Update collection info 
        $.ajax({
            url: "/api/collection/update",
            type: "post",
            data: {"username": localStorage.username,
                "collection_id": G_collection_id,
                "token": localStorage.token,
                "name" : $("#input-collection-name").val(),
                "description" : $("#input-collection-description").val()
            },
            success: function (response) {
                if (response['success'] != false) {
                    //alert("Đã thay đổi thông tin bộ flashcard.");
                } else {
                    alert("Có lỗi xảy ra khi lưu thông tin bộ sưu tập.");
                }
                cb();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("Có lỗi xảy ra khi lưu thông tin bộ sưu tập.");
                cb();
            }
        });
    }

    // Handle change flashcard image
    $.fn.updateFlashcardPhoto = function(flashcard_id) {
        showLoadingModal();
        // Update the image
        let file = document.getElementById('input-flashcard-fphoto-' + flashcard_id);
        if (file.files.length) {
            var reader = new FileReader();
            reader.onload = function (e) {
                ajaxUploadFile(reader.result, function(response) {

                    if (!response.success) {
                        // Clear loading screen
                        hideLoadingModal();
                        return alert("Có lỗi xảy ra khi upload hình ảnh");
                    }

                    let imageUrl = response.imageUrl;
                    
                    // Update info 
                    $.ajax({
                        url: "/api/flashcard/update",
                        type: "post",
                        data: {"username": localStorage.username,
                            "token": localStorage.token,
                            "flashcard_id": flashcard_id,
                            "image": imageUrl
                        },
                        success: function (response) {
                            if (response['success'] != false) {
                                // Update local list
                                LocalFlashcardList.change(flashcard_id, "image", imageUrl);

                                // Reload the page
                                $("#flashcard-"+flashcard_id+"  img").attr("src", imageUrl);
                            } else {
                                alert("Có lỗi xảy ra. Vui lòng thử lại.");
                            }
                            // Clear loading screen
                            hideLoadingModal();
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            // Clear loading screen
                            hideLoadingModal();
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
                    "description" : $("#input-collection-description").val()
                },
                success: function (response) {
                    if (response['success'] != false) {
                        alert("Đã thay đổi thông tin bộ flashcard.");
                    } else {
                        alert("Có lỗi xảy ra. Vui lòng thử lại.");
                    }
                    // Clear loading screen
                    hideLoadingModal();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    // Clear loading screen
                    hideLoadingModal();
                    alert("Có lỗi xảy ra. Vui lòng thử lại.");
                }
            });
        }
    }

});

