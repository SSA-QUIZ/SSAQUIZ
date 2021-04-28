import Vue from 'vue';
import VueRouter from 'vue-router';
import WelcomePage from '@/views/WelcomePage.vue';
import SolvingQuiz from '@/views/SolvingQuiz.vue';
import LobbyPageT from '@/views/LobbyPageT.vue';
import Login from '@/views/Login.vue';
import UserPage from '@/views/UserPage.vue';
import CreatorPage from '@/views/CreatorPage.vue';



Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'WelcomePage',
    component: WelcomePage
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
  {
    path: '/lobby/:quizID',
    name: 'LobbyPageT',
    component: LobbyPageT
  },
  {
    path: '/user-page',
    name: 'UserPage',
    component: UserPage
  },
  {
    path: '/creator',
    name: 'CreatorPage',
    component: CreatorPage
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
