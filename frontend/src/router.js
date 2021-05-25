import { createRouter, createWebHistory } from 'vue-router';

// import views
import StartPage from './views/StartPage.vue';
import UserPage from './views/UserPage.vue';
import AddLease from './views/AddLease.vue';
import BookingView from './views/BookingView.vue';
import SignUpPage from './views/SignUpPage.vue';
import PreviewPage from './views/PreviewView.vue';
import newLogin from './views/newLogin.vue';

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/signUp',
      component: SignUpPage,
    },
    {
      path: '/AddLease',
      component: AddLease,
    },
    {
      path: '/',
      component: StartPage,
    },
    {
      path: '/userPage',
      component: UserPage,
    },
    {
      path: '/login',
      component: newLogin,
    },
    {
      path: '/BookingView',
      component: BookingView,
    },
    {
      path: '/preview',
      component: PreviewPage,
    },
  ],
});
