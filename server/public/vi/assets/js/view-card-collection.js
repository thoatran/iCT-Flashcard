$(document).ready(function(){

     // Global variables
     var url = new URI(window.location.href);
     var G_collection_id = url.search(true)["collection_id"];

    // Load collection info
    $.ajax({
        url: "/api/collection/get",
        type: "post",
        data: {"username": localStorage.username,
            "token": localStorage.token,
            "collection_id" : G_collection_id
        },
        success: function (response) {
            if (response['success'] != false) {

                $("#collection-name").html(response.data.name);
                $("#collection-description").html(response.data.description);
                $("#collection-photo").attr("src", response.data.photo);
                $("#collection-remember-score").html(response.data.remember_score);
                $("#collection-remember-progress").css("width", response.data.remember_score + "%");

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

                    let flashcardList = response.data;
                    
                    for (let i = 0; i < flashcardList.length; i++) {
                        const flashcard = flashcardList[i];
                        $("#Flashcard-Carousel").append(`
                            <div class="item">
                                <!-- Card Flip -->
                                <div id="fc-`+i+`" class="flashcard">
                                        <div class="flip">
                                            <div class="front">
                                                <!-- front content -->
                                                <div class="card">
                                                    <img class="card-img-top" src="`+flashcard.image+`" alt="Card image">
                                                    <div class="card-body">
                                                        <h4 class="card-title">`+flashcard.word+` `+flashcard.pronunciation+`</h4>
                                                            <button class="btn btn-light" onclick='responsiveVoice.speak("`+flashcard.word+`", "UK English Female");' >🔊 UK</button>
                                                            <button class="btn btn-light" onclick='responsiveVoice.speak("`+flashcard.word+`", "US English Female");' >🔊 US</button>
                                                        </div>
                                                    <div flashcard="#fc-`+i+`" class="card-footer text-muted flip-card-btn">
                                                            <i class="fa fa-mail-forward"></i>
                                                            Mặt sau
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="back">
                                                <!-- back content -->
                                                <div class="card">
                                                    <div class="card-body">
                                                        <div class="card-block">
                                                            <h4 class="card-title">`+flashcard.word+` `+flashcard.pronunciation+`</h4>
                                                        </div>
                                                        <div class="card-block">
                                                            <p class="card-text">`+flashcard.meaning+`</p>
                                                        </div>
                                                    </div>
                                                    <div flashcard="#fc-`+i+`" class="card-footer text-muted flip-card-btn">
                                                            <i class="fa fa-mail-forward"></i>
                                                            Mặt trước
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End Card Flip -->
                            </div>
                        `);
                    }
                    

                    // Init flashcard slider
                    $('#Flashcard-Carousel').owlCarousel({
                        loop:true,
                        margin:10,
                        responsiveClass:true,
                        responsive:{
                            0:{
                                items:1,
                                nav:true
                            },
                            300:{
                                items:2,
                                nav:false
                            },
                            1000:{
                                items:3,
                                nav:true,
                                loop:false
                            }
                        }
                    });

                    // Add flip button event
                    $(".flip-card-btn").click(function(){
                        $($(this).attr("flashcard")).toggleClass("hover");
                    });

                }
        },
        error: function(jqXHR, textStatus, errorThrown) {
                alert("Có lỗi xảy ra. Vui lòng thử lại.");
        }
    });
});
