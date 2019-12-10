import Vue from 'vue'
import Vuex from 'vuex'

import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: userData,
        users: [],
        orders: [],
        orderCount: 0,
        paginator: null,
    },
    mutations: {
        fetchUsersMutation(state, users){
            state.users = users.users
        },
        fetchOrdersMutation(state, orders){
            state.orders = orders.orders
            state.orderCount = orders.orderCount
        },
        fetchPaginatorMutation(state, paginator){
            state.paginator = paginator
        },
    },
    getters: {
        getUsers: state => {
            return state.users
        },
    },
    actions: {
        fetchUsersAction({commit, state}){
            axios.post("/graphql", {
                query:
                    `{
                        users {
                            name, email, picture, lastVisit, roles,
                        }
                    }`
            })
                .then(res => {
                    commit('fetchUsersMutation', res.data.data)
                })
        },
        fetchOrdersAction({commit, state}, paginator){
            if(paginator) {
                commit('fetchPaginatorMutation', paginator)
            }
            let limit = 10
            let offset = 0
            if(state.paginator) {
                limit = state.paginator.limit
                offset = state.paginator.offset
            }
            if(paginator){
                limit = paginator.limit
                offset = paginator.offset
            }
            axios.post("/graphql", {
                query:
                    `{
                        orders(limit:${limit}, offset:${offset}) {
                             id, author { name, email, room, picture }, executor { name, email, room, picture }, name, groupName, description, fileName, copies, pages, orderStatus,
                             orderPriority, printType, paperType, colorType, manufactureDate, createdAt, updatedAt
                        },
                        orderCount
                    }`
            })
                .then(res => {
                    commit('fetchOrdersMutation', res.data.data)
                })
        },
    }
})