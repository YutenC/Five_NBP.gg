import { host_context, nowDate } from './shopproductCommon.js';

const vm = Vue.createApp({
    data() {
        return {
            nowDate: '',
            minDate: '',
            newCouponActivity: {},
            newCoupon: {},
            newcouponActivity: {},
            couponActivity: [],
            coupon: []
        };
    },
    methods: {

        getAllCouponActivity: function () {
            console.log('getproduct');
            axios({
                method: "GET",
                // url: "http://localhost:8080/MyShop/demo/getAllCouponActivity_json",
                url: host_context + "shopDispatcher/getAllCouponActivity",
                // params: {
                //     pro_id: 2
                // }
            })
                .then(function (value) {

                    vm.couponActivity = value.data;
                    console.log("vm.couponActivity " + vm.couponActivityf);
                    for (let i = 0; i < vm.couponActivity.length; i++) {
                        console.log(vm.couponActivity[i].coupon);
                        let json = JSON.parse(vm.couponActivity[i].coupon);
                        vm.coupon.push(json);

                        vm.couponActivity[i].coupon = json;
                    }

                    console.log("getAllCouponActivity then");

                })
                .catch(function (e) {
                    console.log("getAllCouponActivity error " + e);
                });
        },
        deleteCouponActivity: function (id) {
            console.log("deleteCouponActivity: id " + id);
            axios({
                method: "GET",
                // url: "http://localhost:8080/MyShop/demo/deleteCoupon",
                url: host_context + "shopDispatcher/deleteCoupon",
                params: {
                    coupon_id: id
                }
            })
                .then(function (value) {
                    console.log("deleteCouponActivity then ");
                    vm.getAllCouponActivity();
                })
                .catch(function (e) {
                    console.log("deleteCouponActivity error " + e);
                });

        },
        addCouponActivity: function () {
            // console.log("message: " + vm.message);
            // console.log("newProduct: " + vm.newProduct);
            // console.log("newProduct.product_name: " + vm.newProduct.product_name);
            // console.log("newProduct.launch_time: " + vm.newProduct.launch_time);
            vm.newcouponActivity.coupon = vm.newCoupon;
            let jsonnewcouponActivity = JSON.stringify(vm.newcouponActivity);
            axios({
                method: "POST",
                // url: "http://localhost:8080/MyShop/nbpShop/addCouponActivity",
                url: host_context + "shopDispatcher/addCouponActivity",
                params: {
                    newCouponActivity: jsonnewcouponActivity
                }
            })
                .then(function (value) {
                    // vm.products = value.data;
                    console.log("addCouponActivity then");

                })
                .catch(function (e) {
                    console.log("addCouponActivity error " + e);
                });


        },
    },
}).mount("#page-top");

// vm.$data.message = 'SSSSS';
vm.getAllCouponActivity();

// const currentDate = new Date();
// const year = currentDate.getFullYear();
// const month = String(currentDate.getMonth() + 1).padStart(2, '0');
// const day = String(currentDate.getDate()).padStart(2, '0');
// vm.nowDate = `${year}-${month}-${day}`;

vm.nowDate = nowDate;
vm.minDate = vm.nowDate;
// vm.newcouponActivity.coupon.deadline = vm.nowDate;
