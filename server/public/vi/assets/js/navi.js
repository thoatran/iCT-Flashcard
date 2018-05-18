// ====================================
// Navigation modules to ajax load website components

$(document).ready(function(){
    // Update Page Info first
    updatePageInfo();
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

function NaviGoto(page) {

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
            history.pushState(null, null, window.location.pathname + "?get=" + page);
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
