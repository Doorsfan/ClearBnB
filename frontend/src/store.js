import { createStore } from 'vuex'

export default createStore({
  state: {
    currentUser: null,
    latestLease: null
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
    }
  }
})