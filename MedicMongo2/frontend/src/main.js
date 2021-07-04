import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import router from "./router"
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'
import store from './store'
import vuetify from '@/plugins/vuetify'
import BootstrapVue from 'bootstrap-vue/dist/bootstrap-vue.esm';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import 'bootstrap/dist/css/bootstrap.css';
import VueLodash from 'vue-lodash'

Vue.config.productionTip = false
Vue.use(VueRouter);
Vue.use(BootstrapVue);
Vue.use(VueLodash)

new Vue({
  render: h => h(App),
  router,
  store,
  vuetify
}).$mount('#app')
