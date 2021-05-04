import Vue from 'vue';
import VueRouter from 'vue-router';
import WelcomePage from '@/views/WelcomePage.vue';
import SolvingQuizPage from '@/views/SolvingQuizPage.vue';
import LobbyPageT from '@/views/LobbyPageT.vue';
import LobbyPageS from '@/views/LobbyPageS.vue';
import Login from '@/views/Login.vue';
import CreatorPage from '@/views/CreatorPage.vue';
import UserPage from '@/views/UserPage.vue';
import CorrectAnswer from '@/views/QuizResultsPage/CorrectAnswer.vue';
import WrongAnswer from '@/views/QuizResultsPage/WrongAnswer.vue';
import AnswerPage from '@/views/AnswerPage.vue';
import ComponentsPage from '@/views/ComponentsPage.vue';
import UserInfoPage from '@/views/UserInfoPage.vue';
import InterimScorePage from '@/views/InterimScorePage.vue';
import AwaitPage from '@/views/AwaitPage.vue';
import LoadingPage from '@/views/LoadingPage.vue';

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
    name: 'SolvingQuizPage',
    component: SolvingQuizPage
  },
  {
    path: '/lobby/:quizID',
    name: 'LobbyPageT',
    component: LobbyPageT
  },
  {
    path: '/lobby',
    name: 'LobbyPageS',
    component: LobbyPageS
  },
  {
    path: '/correct-answer',
    name: 'CorrectAnswer',
    component: CorrectAnswer
  },
  {
    path: '/wrong-answer',
    name: 'WrongAnswer',
    component: WrongAnswer
  },
  {
    path: '/user-page',
    name: 'UserPage',
    component: UserPage
  },
  {
    path: '/creator/:workbookId',
    name: 'CreatorPage',
    component: CreatorPage
  },
  {
    path: '/answer-page',
    name: 'AnswerPage',
    component: AnswerPage
  },
  {
    path: '/components-page',
    name: 'ComponentsPage',
    component: ComponentsPage
  },
  {
    path: '/user-info',
    name: 'UserInfo',
    component: UserInfoPage
  },
  {
    path: '/interim-score',
    name: 'InterimScorePage',
    component: InterimScorePage
  },
  {
    path: '/await',
    name: 'AwaitPage',
    component: AwaitPage
  },
  {
    path: '/loading',
    name: 'LoadingPage',
    component: LoadingPage
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
