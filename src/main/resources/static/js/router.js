import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

import ListOfOrders from "./components/print/ListOfOrders.vue";
import StatisticUsers from "./components/statistic/Users.vue";
const routes = [
    { path: '/statistic/users', component: StatisticUsers },
    { path: '/', component: ListOfOrders },
]

export default new VueRouter({
    routes
})