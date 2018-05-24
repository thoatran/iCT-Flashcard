// ====================================
// Navigation modules to ajax load website components

$(document).ready(function(){
    // Update Page Info first
    updatePageInfo();
    //console.log(NaviGetCurrentPage());
    NaviGoto(NaviGetCurrentPage());
});


function NaviGetCurrentPage() {
    let url = new URI(window.location.href);
    if (url.search(true)["get"] !== undefined) {
        
        return url.search(true)["get"];
    } else {
        return "home"; // return homepage if cannot find page address in get query
    }
}

function NaviGoto(page, isKeepParam) {
    //isKeepParam : keep other params except for "get"

    // Update Page Info first
    updatePageInfo();

    $( "#page-content-ajax" ).html(`
        <div class="loading">
            <img src="assets/loading.svg" style="width: 48px; height: 48px">
        </div>
    `);

    page = encodeURI(page);
    $.ajax({
        type: 'GET',
        url: "/vi/" + page + ".html",
        contentType: 'application/json; charset=utf-8',
        cache: false,
        success: function(data) {
            // Update page content
            $( "#page-content-ajax" ).html(data);
            
        
            // Update history
            var queryParameters = {}, queryString = location.search.substring(1), re = /([^&=]+)=([^&]*)/g, m;
        
            while (m = re.exec(queryString)) {
                queryParameters[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);
            }

            console.log($.param(queryParameters));

            // Get old page
            var oldpage = queryParameters['get'];
            if (isKeepParam)
                history.pushState(null, null,  window.location.pathname + "?"+  $.param(queryParameters));
            else if (oldpage != page)
                history.pushState(null, null, window.location.pathname + "?get=" + page);
            else
                history.pushState(null, null,  window.location.pathname + "?"+  $.param(queryParameters));

        }, error: function(xhr) {
            if (page != "404") {
                NaviGoto("404");
            }
        }});
}



function updatePageInfo() {
    // Check support for HTML5 Local Storage
    if (typeof(Storage) === "undefined") {
        if (NaviGetCurrentPage() != 'browser-not-supported') {
            window.location = "/vi/navi.html?get=browser-not-supported";
        }
    }

    if (!localStorage.username || !localStorage.token) {
        window.location = "/vi/login.html"; 
    }

    $.ajax({
        url: "/api/user/islogin",
        type: "post",
        data: {"username": localStorage.username,
            "token": localStorage.token
        },
        success: function (response) {
            if (response['success'] != false && response['isLoggedIn'] == true) {
                return updateUserInfo(response);
            } else {
                window.location = "/vi/login.html"; 
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            return resetDataOnError();
        }
    });
}



function updateUserInfo (info){
    $('#user-display-name').html(info.displayName);
}

function resetDataOnError() {

}


function showLoadingModal() {
    $("main").append(`
    <!-- Loading modal -->
    <div id="loading-modal" class="modal fade loading-modal" data-backdrop="static" data-keyboard="true" tabindex="-1">
        <div class="modal-dialog modal-sm">
            <div class="modal-content" style="width: 48px">
                <span class="fa fa-spinner fa-spin fa-3x"></span>
            </div>
        </div>
    </div>
    `);
    $("#loading-modal").modal("show");
}


function hideLoadingModal() {
    $("#loading-modal").removeClass("in");
    $(".modal-backdrop").remove();
    $('body').removeClass('modal-open');
    $('body').css('padding-right', '');
    $("#loading-modal").hide();
    $("#loading-modal").remove();
}