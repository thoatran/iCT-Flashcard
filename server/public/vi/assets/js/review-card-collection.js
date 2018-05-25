// Global variables
var url = new URI(window.location.href);
var Quiz = {}
Quiz.timeIntv = setInterval(function(){}, 1000);
Quiz.collectionId = url.search(true)["collection_id"] || -1;
Quiz.flashcardList = [];
Quiz.remainTime;
Quiz.init = function(data) {
    Quiz.flashcardList = data;
    $(".quiz-nquestion").html(this.flashcardList.length);
    $("#quiz-time").html(this.calculateTime());
}
Quiz.start = function() {
    // Change screen from welcome to quiz
    $("#welcome-screen").hide();
    $("#quiz-screen").show();

    // Set time
    this.remainTime = parseInt(this.calculateTime());
    $("#remain-time").html(this.remainTime);

    

    // Time decrease after 1 second
    // Clear previous time timer
    var timeItvFunction = function() {
        if ($("#remain-time").length == 0) {
            return;
        }

        if (Quiz.remainTime <= 0) {
            $("#quiz-screen").hide();
            $("#endquiz-screen").show();
            return;
        }

        Quiz.remainTime -= 1;
        $("#remain-time").html(Quiz.remainTime);
        setTimeout(timeItvFunction, 1000);
    };

    timeItvFunction();

}
Quiz.calculateTime = function() {
    return this.flashcardList.length * 5;
}

// Initialize data when doctument ready
$(document).ready(function(){
   // Load collection info
   $.ajax({
       url: "/api/collection/get",
       type: "post",
       data: {"username": localStorage.username,
           "token": localStorage.token,
           "collection_id" : Quiz.collectionId
       },
       success: function (response) {
           if (response['success'] != false) {

               $("#collection-name").html(response.data.name);
               $("#collection-description").html(response.data.description);
               $("#collection-photo").attr("src", response.data.photo);

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
               "collection_id": Quiz.collectionId,
               "token": localStorage.token
       },
       success: function (response) {
            if (!response.success) {
                alert("Có lỗi xảy ra. Vui lòng thử lại.");
            } else {
                Quiz.init(response.data);
            }
       },
       error: function(jqXHR, textStatus, errorThrown) {
               alert("Có lỗi xảy ra. Vui lòng thử lại.");
       }
   });


});

