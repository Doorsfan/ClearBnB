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
      path: '/',
      component: Home,
    },
    {
      path: '/AddResidence',
      component: AddResidence,
    },
    {
      path: '/UserPage',
      component: UserPage,
    },
    {
      path: '/SignUp',
      component: SignUp,
    },
    {
      path: '/Login',
      component: Login,
    },
  ],
});