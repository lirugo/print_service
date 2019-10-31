import App from './pages/App.vue'
import Vue from 'vue'
import vuetify from 'vuetify.js'
import 'es6-promise/auto'
import store from 'store.js'
import router from 'router.js'

new Vue({
    el: '#app',
    vuetify,
    store,
    router,
    render: a => a(App),
})