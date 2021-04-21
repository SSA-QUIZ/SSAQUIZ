import Vue from 'vue'
import VueRouter from 'vue-router'
import ComponentSet from '../views/ComponentSet.vue'
import Login from '../views/Login.vue'
import SolvingQuiz from '../views/SolvingQuiz.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'ComponentSet',
    component: ComponentSet
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/solving-quiz',
    name: 'SolvingQuiz',
    component: SolvingQuiz
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
