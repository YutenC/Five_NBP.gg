// 路徑常數
const href = window.location.href;
const host = href.substring(0, href.indexOf('/', 8));
const projectHref = href.substring(0, href.lastIndexOf('Five_NBP.gg') + 11);
const projectFolder = '/Five_NBP.gg';

const omManage = Vue.createApp({
    data() {
        return {
            omList: [],
            nowPage: 1,  // 與offset間的轉換： nowPage = offset + 1
            totalListItem: 0
        }
    },
    methods: {
        watchDetail: function (omid) {
            sessionStorage.setItem('orderId', omid);
            sessionStorage.setItem('offset', this.nowPage - 1);
            window.location.href = projectHref + '/manager/orderManageDetail.html';
        },
        renewList: function (pageNum) {
            this.nowPage = pageNum;
            let offset = pageNum - 1;
            console.log(offset);
            sessionStorage.setItem('offset', offset);
            axios.get('/Five_NBP.gg/OrderMaster?manageAll=' + offset)
                .then(res => this.omList = res.data)
                .catch(err => console.log(err));
        }
    },
    created() {
        let offset = sessionStorage.getItem('offset');
        if (offset === null) {
            offset = 0;
        } else {
            offset = Number.parseInt(offset);
        }

        this.nowPage = offset + 1;

        axios.get('/Five_NBP.gg/OrderMaster?manageAll=' + offset)
            .then(res => this.omList = res.data)
            .catch(err => console.log(err));
        axios.get('/Five_NBP.gg/OrderMaster?countListLength=manager&matchId=')
            .then(res => this.totalListItem = res.data)
            .catch(err => console.log(err));

        sessionStorage.removeItem('offset');
    }
}).mount('#omManage');