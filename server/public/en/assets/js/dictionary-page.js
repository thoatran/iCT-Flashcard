 // Overrides the default autocomplete filter function to search only from the beginning of the string
 $.ui.autocomplete.filter = function (array, term) {
    var matcher = new RegExp("^" + $.ui.autocomplete.escapeRegex(term), "i");
    return $.grep(array, function (value) {
        return matcher.test(value.label || value.value || value);
    });
};

function findWord(word) {

    // hide suggestions
    $(".ui-menu-item").hide();

    // Load word
    $.ajax({
        type: 'GET',
        url: '/en/api/dictionary/ev?lookup=' + encodeURIComponent(word),
        contentType: 'application/json; charset=utf-8',
        cache: false,
        success: function(data) {
            console.log(data);
            if (data.success != true) {
                $( "#meaning-wrapper" ).html("Not foung the word you want.");
            } else {
                // Load Quick Search
                $( "#meaning-wrapper" ).html(`
                <h3 style="display:inline" class="word text-primary border-bottom border-gray">`+data.word+`</h3>
                <span style="display:inline" class="ipapron text-secondary">`+data.ipapron+`</span>
                <!--<button style="display:inline;" type="button" class="btn btn-outline-dark btn-sm">
                    <i class="fa fa-plus-square-o" aria-hidden="true"></i>
                    Tạo flashcard
                </button>-->
                <div>
                    <button class="btn btn-light" onclick='responsiveVoice.speak("`+data.word+`", "UK English Female");' >🔊 UK</button>
                    <button class="btn btn-light" onclick='responsiveVoice.speak("`+data.word+`", "US English Female");' >🔊 US</button>
                </div>
                <p class="meaning"><pre>`+data.meaning+`</pre></p>
                `);
                updateRecentWordList(word);
            }

        }, error: function(xhr) {
            $( "#meaning-wrapper" ).html("Not found the word you want");
        }});

}

var updateRecentWordList = function(word) {

    // Save word to recently list and show up the list
    if (typeof localStorage !== 'undefined') {
        let recentWordList = [];
        if (localStorage.dictRecentList) {
            recentWordList = JSON.parse(localStorage.dictRecentList);
        }

        if (typeof word != "undefined") {
            recentWordList.push(word);
        }

        // Remove duplicated values
        let recentWordList_unduplicated = [];
        $.each(recentWordList, function(i, el){
            if($.inArray(el, recentWordList_unduplicated) === -1) recentWordList_unduplicated.push(el);
        });
        recentWordList = recentWordList_unduplicated;

        // Only keep 8 words in recent list
        while (recentWordList.length > 8) {
            recentWordList.shift();
        }

        localStorage.setItem("dictRecentList", JSON.stringify(recentWordList))
    
        // Show recent words
        if (recentWordList.length > 0) {
            let wordListBtns = "<h6 class='m-1'>Looked up word</h6>";
            for (let i = 0; i < recentWordList.length; i++) {
                wordListBtns += `<button class="btn btn-light m-1" onclick="findWord('`+recentWordList[i]+`')">`+recentWordList[i]+`</button>`;
            }

            $("#recent-word-list").html(wordListBtns);
        }
        
    
    }
}


$(document).ready(function() {

    // Show the recent word list
    updateRecentWordList();

    // Set event for Search button
    $('#btn-lookup').on('click', function(){
        findWord($('#dict-lookup-input').val());
    });

    // Press enter to search
    $("#dict-lookup-input").keypress(function(e){
        if (e.which == 13) // Press Enter to search
        {
            findWord($('#dict-lookup-input').val());
        };
    });

    $.ajax({
        type: 'GET',
        url: "wordlist-ev.json",
        contentType: 'application/json; charset=utf-8',
        cache: false,
        success: function(data) {
             // Load Quick Search
             $( "#dict-lookup-input" ).autocomplete({
               maxResults: 10,
               source: function(request, response) {
                    var results = $.ui.autocomplete.filter(data, request.term);
                    response(results.slice(0, this.options.maxResults));
                },
                select: function( event, ui ) {
                    findWord(ui.item.value);
               }
             });
        }, error: function(xhr) {
            console.log("Error: "+xhr.status);
        }});
});



