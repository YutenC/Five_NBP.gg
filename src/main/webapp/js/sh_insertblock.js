const header = document.createElement('header');
header.innerHTML = `
<nav>
 <div class ="login" ><span>登入</span> / <span>註冊</span></div>
    <div class="mall_title">
        二手商城
    </div>
</nav>
<div class="search">
    <div class="nav">
       <div>回首頁</div>
       <div>NS</div>
       <div>XBOX</div>
       <div>PlayStation</div>
       <div>一般商城</div>
</div>
    <div class="search_div">
        <div id="input_search"><input type="text" placeholder="搜尋"></div>
        <span class="search_span">搜尋<img src="..\\img\\secondHand\\search_icon.png"></span>
    </div>`;

const footer = document.createElement('footer');
footer.innerHTML = `
<div>
此網頁僅用於學術探討，若有侵權請通知我們立即下架
<!--  footer第一行 -->
</div>
        <div>
        ######@$$mail.com
        <!-- footer 第二行 -->
        </div>
        <div>
        © NBP.gg since 2023 spring
        </div>`


const side = document.createElement('div');
side.setAttribute('class', 'side');
side.innerHTML = `
<div class="brandlist">
                <span class="N_brand">
                    Nitendo
                </span>
                <div class="brandItem">
                    <div class="listtype"><span>主機</span></div>
                    <div class="listtype"><span>手把</span></div>
                    <div class="listtype"><span>卡帶</span></div>
                    <div class="listtype"><span>周邊</span></div>
                </div>
            </div>

            <div class="brandlist">
                <span class="B_brand">
                    XBOX
                </span>
                <div class="brandItem">
                    <div class="listtype"><span>主機</span></div>
                    <div class="listtype"><span>手把</span></div>
                    <div class="listtype"><span>遊戲片</span></div>
                    <div class="listtype"><span>周邊</span></div>
                </div>
            </div>
            <div class="brandlist">
                <span class="P_brand">
                    PlayStation
                </span>
                <div class="brandItem">
                    <div class="listtype"><span>主機</span></div>
                    <div class="listtype"><span>手把</span></div>
                    <div class="listtype"><span>遊戲片</span></div>
                    <div class="listtype"><span>周邊</span></div>
                </div>
            </div>
            <div class="brandlist">
                <span class="buy_btn">
                    二手回收
                </span>
            </div>`;



$('body').prepend(header).append(footer);
$('main').prepend(side);



$(".brandlist span").on('mouseenter', cursor_pointer);

$(".nav>div").on('mouseenter', cursor_pointer);

$('.search_span').on('mouseenter', cursor_pointer);


$(".brandlist>span").on('click', function (e) {
    $(this).toggleClass("-is_click");

    if ($(this).hasClass("-is_click")) {
        $(e.target).next().animate({ height: "180px" }, 200, 'linear')
    } else {
        $(e.target).next().animate({ height: "0px" }, 200)
    }
})



$('.search_span').on('click', function (e) {
    $(this).toggle();
    $('#input_search').toggle().find('input').focus();
})

$('#input_search input').on('blur', function (e) {
    $('.search_span').toggle();
    $('#input_search').toggle();
})


function cursor_pointer(e) {
    e.target.setAttribute('style', 'cursor : pointer');
}



window.addEventListener('scroll', e => {
    // 250 作用
    if (window.scrollY > 250) {
        $('.search').css({ position: 'fixed', top: 0, left: 0, backgroundColor: 'black' });
    } else {
        $('.search').css({ position: 'static ', backgroundColor: '' });
    }
})



document.querySelectorAll('.nav div')[0].addEventListener('click', e => {
    e.preventDefault;
    window.location.href = 'SecondHand_MainView.html';

})