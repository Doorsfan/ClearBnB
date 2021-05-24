import { createStore } from 'vuex'

export default createStore({
  state: {
    currentUser: null,
    latestLease: null,
    bookingHelper: null,
    bookedDates: null
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
    },
    setTakenDays(state, takenDays) {
      state.takenDays = takenDays;
    },
    updateBookedDates(state, bookedDates) {
      state.bookedDates = bookedDates
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
    },
    getTakenDays: state => {
      return state.takenDays
    },
    getBookedDates: state => {
      return state.bookedDates
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
    },
    async updateTakenDays(store, takenDays) {
      store.commit('setTakenDays', takenDays);
    },
    async updateBookedDates(store, bookedDates) {
      store.commit('setBookedDates', bookedDates);
    }
  }
})