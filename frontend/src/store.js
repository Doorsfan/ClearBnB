import { createStore } from 'vuex'

export default createStore({
  state: {
    currentUser: null,
    latestLease: null,
    bookingHelper: null
  },
  // this.$store.commit('mutationName')
  mutations: {
    setRoute(state, newRoute) {
      state.route = newRoute
    },
    setLeaseToBuild(state, lease) {
      state.latestLease = lease
    },
    setUser(state, user) {
      state.currentUser = user
    },
    setBookingHelper(state, bookingHelper) {
      state.bookingHelper = bookingHelper
    }
  },
  getters: {
    getLatestRoute: state => {
      return state.route
    },
    getLeaseToBuild: state => {
      return state.latestLease
    },
    getCurrentUser: state => {
      return state.currentUser
    },
    getBookingHelper: state => {
      return state.bookingHelper
    }
  },
  actions: {
    async saveLatestRoute(store, latestRoute) {
      store.commit('setRoute', latestRoute);
    },
    async saveLease(store, myLease) {
      store.commit('setLeaseToBuild', myLease)
    },
    async login(store, loggedInUser) {
      store.commit('setUser', loggedInUser)
    },
    async saveBookingHelper(store, bookingHelper) {
      store.commit('setBookingHelper', bookingHelper);
    }
  }
})