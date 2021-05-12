import { createRouter, createWebHistory } from 'vue-router'

// import views
import StartPage from './views/StartPage.vue'
import UserPage from './views/UserPage.vue'
import SignUp from './views/SignUp.vue'
import Login from './views/Login.vue'
import AddLease from './views/AddLease.vue'
import BookingView from './views/BookingView.vue'
import SignUpPage from './views/SignUpPage.vue'

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/signUp',
      component: SignUpPage
    },
    {
      path: '/AddLease',
      component: AddLease
    },
    {
      path: '/',
      component: StartPage
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
    {
      path: '/BookingView',
      component: BookingView
    },
  ]
})