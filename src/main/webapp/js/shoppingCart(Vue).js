// 增添商品到購物車內按鈕變化

// '+' 號按鈕預設展開
$('.cartHeader div a.addItem img.icon.plus').addClass('on');

// 紀錄目前按下的按鈕
let clickTagAttr;
// 按鈕切換
$('.cartHeader div a.addItem').on('click', function (e) {
    e.preventDefault();
    // 按過的收合
    if (clickTagAttr !== undefined && clickTagAttr === $(this).attr('data-from')) {
        $(this).children('img').toggleClass('on');
        let switchTarget = $(this).attr('data-from');
        $(`.addDetail table.${switchTarget}`).toggleClass('on');
        // 非按過的展開並收起先前開過的
    } else {
        $('a.addItem img').removeClass('on');
        $('a.addItem img.icon.plus').addClass('on');
        $(this).children('img').toggleClass('on');
        let switchTarget = $(this).attr('data-from');
        $(`.addDetail table`).removeClass('on');
        $(`.addDetail table.${switchTarget}`).toggleClass('on');
    }
    clickTagAttr = $(this).attr('data-from');
});

// 視窗變化時調整增添視窗內的文字
$(window).resize(function () {
    let windowSize = $(this).width();
    if (windowSize <= 830) {
        $('.addDetail table.byMyPick th:nth-child(3)').text('追蹤日期');
        $('.addDetail table.byMyPick th:last-child').text('加入購物');
        $('.addDetail table.byPurchaseLog th:nth-child(3)').text('購買日期');
        $('.addDetail table.byPurchaseLog th:last-child').text('加入購物');
        $('.addDetail table button').text('加入');
    } else {
        $('.addDetail table.byMyPick th:nth-child(3)').text('加入追蹤日期');
        $('.addDetail table.byMyPick th:last-child').text('加入本次購物');
        $('.addDetail table.byPurchaseLog th:nth-child(3)').text('近期購買日期');
        $('.addDetail table.byPurchaseLog th:last-child').text('加入購物');
        $('.addDetail table button').text('加入購物');
    }
});

// 從記錄添加商品到購物車
/*
- 顯示記錄商品
1. 取得商品資料
2. 顯示商品資料
3. 無庫存商品disable按鈕，並將按鈕文字改為無庫存
- 添加記錄商品到購物車
1. 取得該列的資料物件
2. 添加到購物車的table內
*/

//
axios.get('')

// 從追蹤商品
const byMyPick = Vue.createApp({
    data() {
        return {
            mypick: [
                { id: 1, name: 'Zelda', followdate: '2022/10/10', price: 2000 },
                { id: 3, name: 'FFXI', followdate: '2022/12/31', price: 2500 },
            ]
        }
    },
    methods: {
        addToCart: function (id) {
            // 向後端發id調產品資訊
            // 添加部分項目
            // 加到購物車Vue商品列表
            purchaseDetail.$data.purchaseDetail.push();
        }
    }
}).mount("table.byMyPick");

$('div.addDetail').on('click', 'button', function (e) {
    let productId = $(this).closest('tr').find('td.productId').text();
});

// 從購買紀錄
const byPurchaseLog = Vue.createApp({
    data() {
        return {
            byPurchaseLog: [
                { id: 2222, name: 'HappyFarm', boughtDate: '2023/1/1', price: 1500 }
            ]
        }
    },
    methods: {
        addToCart: function (id) {
            // 向後端發id調產品資訊
            // 添加部分項目
            // 加到購物車Vue商品列表
            purchaseDetail.$data.purchaseDetail.push();
        }
    }
}).mount('table.byPurchaseLog');

// 購物車全選checkBox
$(document).ready(function () {
    // Set the "Check All" button as checked by default
    $('input#checkAll').prop('checked', true);
    // Child checkboxes event handler
    $('table#purchaseDetail tbody').on('change', 'input[type="checkbox"]', function () {
        var checkBoxNum = $('tbody input[type="checkbox"]').length;
        var checkedBoxNum = $('tbody input:checked').length;
        // Update "Check All" button based on the checked state of child checkboxes
        $('input#checkAll').prop('checked', checkedBoxNum === checkBoxNum);
    });
    // "Check All" button event handler
    $('input#checkAll').on('change', function () {
        var isChecked = $(this).prop('checked');
        // Set the checked state of child checkboxes based on the "Check All" button
        $('table#purchaseDetail input[type="checkbox"]').prop('checked', isChecked);
    });
});

// 購物車商品
const shoppingContent = Vue.createApp({
    data() {
        return {
            // 購物商品明細
            purchaseDetail: [
                {
                    productId: 1111, productName: 'Diablo', productImg: '../img/peripherals/Nintendo/Zelda/2a14aa702d831e8f7f2803e1601l4t05.jpg',
                    productLink: '?productId=1111', checked: true, productVer: '日版中文', buyAmount: 6, stock: 20, price: 3000,
                },
                {
                    productId: 1111, productName: 'Diablo', productImg: '../img/peripherals/Nintendo/Zelda/2a14aa702d831e8f7f2803e1601l4t05.jpg',
                    productLink: '?productId=1111', checked: true, productVer: '日版中文', buyAmount: 6, stock: 20, price: 3000,
                }
            ],
            // 消費折抵
            couponId: '',
            couponDiscount: 0,
            bounusStock: 10000,
            bonus: 0,
            // 配送方式
            deliver: 'takuhai',
        }
    },
    methods: {
        // 購物商品明細
        subtotal: function (buyAmount, price) {
            return buyAmount * price;
        },
        removeItem: function (index) {
            let purchaseList = this.purchaseDetail;
            Swal.fire({
                title: '確定刪除?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '是',
                cancelButtonText: '否'
            }).then(function (result) {
                if (result.isConfirmed) {
                    purchaseList.splice(index, 1);
                }
            });
        },
        // 消費折抵選擇
        useCoupon: function () {
            // 向後端確認是否為有效折購代碼
            this.bonus = this.couponId === '' ? this.bonus : 0;
            // 查詢到資料後更新this.couponDiscount
        },
        useBonus: function () {
            if (this.bonus > this.bounusStock) {
                this.bonus = this.bounusStock;
            }
            this.couponId = this.bonus === 0 ? this.couponId : '';
        },
    },
    computed: {
        productSubtotal: function () {
            let productSub = 0;
            for (pic of this.purchaseDetail) {
                productSub += pic.buyAmount * pic.price;
            }
            return productSub - this.couponDiscount - this.bonus;
        },
        deliverCal() {
            return this.deliver === 'toCvs' ? 200 : 100;
        }
    }
}).mount('.shoppingContent');

// 監控紅利input的非數字
$('form#discount').on('keyup', 'input', function (e) {
    let key = e.keyCode;
    let inputValue = $('input#bonus')[0].value;
    if (((key >= 48 && key <= 57)
        || (key >= 96 && key <= 105) //數字鍵盤
        || 8 == key || 46 == key || 37 == key || 39 == key //8:backspace 46:delete 37:左 39:右 (倒退鍵、刪除鍵、左、右鍵也允許作用)
    )) {
    } else {
        $('input#bonus')[0].value = inputValue.replace(/[^\d]/, '');
    }
});

// 推薦商品呈現
const promoProduct = Vue.createApp({
    data() {
        return {
            promoProduct: [
                {
                    id: 1111, productName: 'abcdefg', link: 'dddddd', productImg: '../img/peripherals/Nintendo/Zelda/2a14aa702d831e8f7f2803e1601l4t05.jpg',
                    price: 2000
                },
                {
                    id: 1111, productName: 'abcdefg', link: 'dddddd', productImg: '../img/peripherals/Nintendo/Zelda/2a14aa702d831e8f7f2803e1601l4t05.jpg',
                    price: 2000
                },
                {
                    id: 1111, productName: 'abcdefg', link: 'dddddd', productImg: '../img/peripherals/Nintendo/Zelda/2a14aa702d831e8f7f2803e1601l4t05.jpg',
                    price: 2000
                }
            ]
        }
    }
}).mount('#promoProduct');