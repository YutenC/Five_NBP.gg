import { host_context, nowDate } from './shopproductCommon.js';
import { saveDataToSessionStorage, getDataFromSessionStorage } from './shopproductCommon.js';




const vm = Vue.createApp({
    data() {
        return {
            nowDate: '',
            minDate: '',
            product: {},
            productDetail: {},
            productHistory: []
        };
    },
    created() {
    },
    mounted() {
        const ProductDetail_id = getDataFromSessionStorage("currentShopProductDetail_id");
        axios({
            method: "GET",
            url: host_context + "shopDispatcher/getProductDetail",
            params: { id: ProductDetail_id }
        })
            .then(function (value) {
                vm.productDetail = value.data;
                vm.product = vm.productDetail.product;
                console.log("getProductById then");

            })
            .catch(function (e) {
                console.log("getProductById error " + e);
            });

        getProductHistory();
    },
    methods: {
        getProduct: function () {
            const ProductDetail_id = getDataFromSessionStorage("currentShopProductDetail_id");
            axios({
                method: "GET",
                url: host_context + "shopDispatcher/getProductDetail",
                params: { id: ProductDetail_id }
            })
                .then(function (value) {
                    vm.productDetail = value.data;
                    console.log("getProductById then");

                })
                .catch(function (e) {
                    console.log("getProductById error " + e);
                });
        },

        addCart: function (id) {
            axios({
                method: "POST",
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


        // historymouseenter: function () {
        //     // e.preventDefault();
        //     // let historyArea = this.$refs.historyArea;

        //     // if (!historyArea.classList.contains("hidden")) {
        //     //     historyArea.classList.remove("hidden");
        //     // }
        // }
        // 
    },
}).mount(".shopmain");


function getProductHistory() {
    axios({
        method: "GET",
        url: host_context + "shopDispatcher/getProductHistory",
    })
        .then(function (value) {
            vm.productHistory = value.data;
            console.log("getProductHistory then");

        })
        .catch(function (e) {
            console.log("getProductHistory error " + e);
        });


}