import { host_context, nowDate } from './shopproductCommon.js';
import { saveDataToSessionStorage } from './shopproductCommon.js';

const vm = Vue.createApp({
    data() {
        return {
            nowDate: '',
            minDate: '',
            products: []
        };
    },
    created() {
        // vm.getAllProduct();
    },
    mounted() {
        // vm.getAllProduct();
        axios({
            method: "GET",
            // url: "http://localhost:8080/MyShop/demo/getAllCouponActivity_json",
            url: host_context + "shopDispatcher/getAllProduct",
        })
            .then(function (value) {
                vm.products = value.data;
                console.log("getAllProduct then");

            })
            .catch(function (e) {
                console.log("getAllProduct error " + e);
            });
    },
    methods: {
        getAllProduct: function () {
            console.log('getAllProduct');
            axios({
                method: "GET",
                // url: "http://localhost:8080/MyShop/demo/getAllCouponActivity_json",
                url: host_context + "shopDispatcher/getAllProduct",
            })
                .then(function (value) {
                    vm.products = value.data;
                    console.log("getAllProduct then");

                })
                .catch(function (e) {
                    console.log("getAllProduct error " + e);
                });
        },

        addCart: function (id) {
            axios({
                method: "Get",
                url: host_context + "shopDispatcher/addCart",
                params: {
                    id: id
                }
            })
                .then(function (value) {
                    console.log("addCart then");

                })
                .catch(function (e) {
                    console.log("addCart error " + e);
                });


        },
        clickShopDetail: function (id) {
            saveDataToSessionStorage("currentShopProductDetail_id", id);
        },

    },
}).mount(".shopmain");

vm.getAllProduct();


