import App from './pages/App.vue'
import Vue from 'vue'
import vuetify from 'vuetify.js'
import 'es6-promise/auto'
import store from 'store.js'


new Vue({
    el: '#app',
    vuetify,
    store,
    render: a => a(App),
})