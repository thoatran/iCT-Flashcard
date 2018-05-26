// Global variables
var url = new URI(window.location.href);

// Sounds for game
var correctSound = new Audio('/vi/assets/sound/ting.wav');
var wrongSound = new Audio('/vi/assets/sound/wrong.wav');

function shuffleArray(array) {
  var currentIndex = array.length, temporaryValue, randomIndex;

  // While there remain elements to shuffle...
  while (0 !== currentIndex) {

    // Pick a remaining element...
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;

    // And swap it with the current element.
    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
}

var Quiz = {}
Quiz.collectionId = url.search(true)["collection_id"] || -1;
Quiz.flashcardList = [];
Quiz.questions = [];
Quiz.remainTime;
Quiz.init = function(data) {
    Quiz.flashcardList = data;
    $(".quiz-nquestion").html(this.flashcardList.length);
    $("#quiz-time").html(this.calculateTime());
    this.initQuestions();
}

Quiz.initQuestions = function () {
    // Empty the question list
    this.questions = [];

    let tmpFlashcardList = shuffleArray(this.flashcardList);
    for (let i = 0; i < tmpFlashcardList.length; i++) {
        const flashcard = tmpFlashcardList[i];
        this.questions[i] = {};
        this.questions[i].remember_score = 0;
        this.questions[i].flashcard_id = tmpFlashcardList[i].id;
        // Use Math random to random type of the question: Give meaning, ask word or give word ask meaning
        if (Math.random() < 0.5) {
            // Give word ask meaning
            this.questions[i].type = 1;
            this.questions[i].question = flashcard.word;
            this.questions[i].rightAnswer = flashcard.meaning;
            this.questions[i].answers = [];
            let wrongAnswers = [];
            for (let j = 0; j < tmpFlashcardList.length; j++) {
                if (tmpFlashcardList[j].id != tmpFlashcardList[i].id) {
                    wrongAnswers.push(tmpFlashcardList[j].meaning);
                }
            }
            wrongAnswers = shuffleArray(wrongAnswers);

            j = 0;
            while (wrongAnswers.length > 0 && j < 3) {
                this.questions[i].answers.push(wrongAnswers[j]);
                j++;
            }
            // Add right answer to answer list
            this.questions[i].answers.push(this.questions[i].rightAnswer);
            // Shuffle the answer list
            this.questions[i].answers = shuffleArray(this.questions[i].answers);
        } else {
            // Give meaning ask word
            this.questions[i].type = 2;
            this.questions[i].question = flashcard.meaning;
            this.questions[i].rightAnswer = flashcard.word;
            this.questions[i].answers = [];
            let wrongAnswers = [];
            for (let j = 0; j < tmpFlashcardList.length; j++) {
                if (tmpFlashcardList[j].id != tmpFlashcardList[i].id) {
                    wrongAnswers.push(tmpFlashcardList[j].word);
                }
            }
            wrongAnswers = shuffleArray(wrongAnswers);

            j = 0;
            while (wrongAnswers.length > 0 && j < 3) {
                this.questions[i].answers.push(wrongAnswers[j]);
                j++;
            }
            // Add right answer to answer list
            this.questions[i].answers.push(this.questions[i].rightAnswer);
            // Shuffle the answer list
            this.questions[i].answers = shuffleArray(this.questions[i].answers);
        }

    }
    

}
Quiz.startQuestion = function(qid) {
    if (qid >= this.questions.length) {
        this.endQuiz();
    } else {
        // Print question
        $("#quiz-quest-number").html(qid+1);
        $("#quiz-question").html(this.questions[qid].question);
        $("#quiz-question").attr("question-id", qid);
        // Print Answers
        $("#quiz-answers").html("");
        for (let i = 0; i < this.questions[qid].answers.length; i++) {
            $("#quiz-answers").append('<button id="quiz-answer-'+i+'" type="button" class="btn btn-danger btn-lg p-3 m-3" style="min-width: 40%;" onclick="Quiz.selectAnswer('+i+')">'+this.questions[qid].answers[i]+'</button>');
            $("#quiz-answers #quiz-answer-"+i).attr("answer-value", this.questions[qid].answers[i]);
        }

    }
}
Quiz.selectAnswer = function(aid) {
    let qid = $("#quiz-question").attr("question-id");
    let answer = $("#quiz-answer-"+aid).attr("answer-value");
    if (answer == Quiz.questions[qid].rightAnswer) {
        Quiz.selectAnswerRight(qid);
    } else {
        Quiz.selectAnswerWrong(qid);
    }
}
Quiz.selectAnswerRight = function(qid) {
    this.questions[qid].correct = true;
    $("#quiz-correct").fadeIn(100);
    setTimeout(function(){
        $("#quiz-correct").animate({
            fontSize: "9rem"
        }, 150);
        $("#quiz-correct").animate({
            fontSize: "8rem"
        }, 200);
        $("#quiz-correct").fadeOut(100);
    }, 100);
    setTimeout(function(){
        correctSound.play();
        Quiz.startQuestion(++qid);
    }, 200);
}
Quiz.selectAnswerWrong = function(qid) {
    this.questions[qid].correct = false;
    $("#quiz-wrong").fadeIn(100);
    setTimeout(function(){
        $("#quiz-wrong").animate({
            fontSize: "9rem"
        }, 150);
        $("#quiz-wrong").animate({
            fontSize: "8rem"
        }, 200);
        $("#quiz-wrong").fadeOut(100);
    }, 100);
    setTimeout(function(){
        wrongSound.play();
        Quiz.startQuestion(++qid);
    }, 200);
}
Quiz.countCorrect = function() {
    let count = 0;
    for (let i = 0; i < this.questions.length; i++) {
        if (this.questions[i].correct) {
            count++;
        }
    }
    return count;
}
Quiz.countDown = function(callback) {
    // Count down to start
    $("#countdown-to-start").html(3);
    $("#countdown-screen").show();
    var countdownItv = setInterval(function(){
        let timeLeft = parseInt($("#countdown-to-start").html());
        if (timeLeft <= 0) {
            clearInterval(countdownItv);
            $("#countdown-screen").hide();
            callback();
        } else {
            $("#countdown-to-start").html(timeLeft-1);
            correctSound.play();
        }
    }, 1000);
}
Quiz.start = function() {
    // Change screen from welcome to quiz
    $("#welcome-screen").hide();

    // Count down to get ready
    Quiz.countDown(function(){
        // Show quiz screen
        $("#quiz-screen").show();

        // Set time
        Quiz.remainTime = parseInt(Quiz.calculateTime());
        $("#remain-time").html(Quiz.remainTime);

        // Start from te first question;
        Quiz.initQuestions();
        Quiz.startQuestion(0);

        // Time decrease  01 second
        // Clear previous time timer
        var timeCounter = function() {
            if ($("#remain-time").length == 0) {
                return;
            }

            if (Quiz.remainTime <= 0) {
                Quiz.endQuiz();
                return;
            }

            Quiz.remainTime -= 1;
            $("#remain-time").html(Quiz.remainTime);
            setTimeout(timeCounter, 1000);
        };
        timeCounter();

    });

}
Quiz.endQuiz = function() {
    $("#quiz-correct-number").html(Quiz.countCorrect());
    $("#quiz-screen").hide();
    $("#endquiz-screen").show();

    let rememberScore = parseInt(Quiz.countCorrect() / Quiz.questions.length * 100);
    showLoadingModal();
    // Save result to remote server
    // Update collection info 
    $.ajax({
        url: "/api/collection/update",
        type: "post",
        data: {"username": localStorage.username,
            "collection_id": Quiz.collectionId,
            "token": localStorage.token,
            "remember_score" : rememberScore
        },
        success: function (response) {
            if (response['success'] != false) {
                hideLoadingModal();
            } else {
                hideLoadingModal();
                alert("Có lỗi xảy ra khi lưu điểm. Vui lòng thử lại.");
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            hideLoadingModal();
            alert("Có lỗi xảy ra khi lưu điểm. Vui lòng thử lại.");
        }
    });

}
Quiz.calculateTime = function() {
    return this.flashcardList.length * 8;
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

