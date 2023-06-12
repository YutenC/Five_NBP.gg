$("li.logout").on("click", function (){
    sessionStorage.clear();
    $.ajax({
        url: "/Five_NBP_gg/memberLogoutServlet"
    })
    window.location.href="/Five_NBP_gg/html/index.html";
})


