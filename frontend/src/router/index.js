import Vue from 'vue'
import VueRouter from 'vue-router'
import ComponentSet from '../views/ComponentSet.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'ComponentSet',
    component: ComponentSet
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
