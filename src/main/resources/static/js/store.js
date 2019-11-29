import Vue from 'vue'
import Vuex from 'vuex'

import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: userData,
        users: [],
    },
    mutations: {
        fetchUsersMutation(state, users){
            state.users = users.users
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
                    '{' +
                    '    users {' +
                    '        name, email, picture, lastVisit, roles' +
                    '    }' +
                    '}'
            })
                .then(res => {
                    commit('fetchUsersMutation', res.data.data)
                })
        },
    }
})