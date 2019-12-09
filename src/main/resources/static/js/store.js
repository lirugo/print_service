import Vue from 'vue'
import Vuex from 'vuex'

import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: userData,
        users: [],
        orders: [],
    },
    mutations: {
        fetchUsersMutation(state, users){
            state.users = users.users
        },
        fetchOrdersMutation(state, orders){
            state.orders = orders.orders
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
        fetchOrdersAction({commit, state}){
            axios.post("/graphql", {
                query:
                    `{
                        orders {
                             id, author { name, email, room, picture }, executor { name, email, room, picture }, name, groupName, description, fileName, copies, pages, orderStatus,
                             orderPriority, printType, paperType, colorType, manufactureDate, createdAt, updatedAt
                        }
                    }`
            })
                .then(res => {
                    commit('fetchOrdersMutation', res.data.data)
                })
        },
    }
})