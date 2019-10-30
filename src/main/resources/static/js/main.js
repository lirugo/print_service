import App from './pages/App.vue'
import Vue from 'vue'
import vuetify from 'vuetify.js'

new Vue({
    el: '#app',
    vuetify,
    render: a => a(App),
})