import { createRouter, createWebHistory } from 'vue-router'

// import views
import Home from './views/Home.vue'
import UserPage from './views/UserPage.vue'
import SignUp from './views/SignUp.vue'
import Login from './views/Login.vue'
import AddResidence from './views/AddResidence.vue'

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/addResidence',
      component: AddResidence
    },
    {
      path: '/',
      component: Home
    },
    {
      path: '/userPage',
      component: UserPage
    },
    {
      path: '/signUp',
      component: SignUp
    },
    {
      path: '/login',
      component: Login
    },
  ]
})