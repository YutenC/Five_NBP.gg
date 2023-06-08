
const vm = Vue.createApp({
    data() {
        return {
            message: "KKKKK",
            arr: ['008', 'HG', 'yy'],
            product: {},
            couponActivity: [],
            coupon: []
        };
    },
    methods: {
        getAllCouponActivity: function () {
            console.log('getproduct');
            axios({
                method: "GET",
                url: "http://localhost:8080/MyShop/demo/getCouponByActivity_json",
                params: {
                    activityCode: 2
                }
            })
                .then(function (value) {
                    vm.couponActivity = value.data;

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

    },
}).mount("#page-top");

vm.$data.message = 'SSSSS';
vm.getAllCouponActivity();

