import { createStore } from 'vuex'

export default createStore({
  state: {
    currentUser: null,
    latestLease: null
  },

  // this.$store.commit('mutationName')
  mutations: {
    setLeaseToBuild(state, lease) {
      state.latestLease = lease
    },
    setUser(state, user) {
      state.currentUser = user
    }
  },
  getters: {
    getLeaseToBuild: state => {
      return state.latestLease
    },
    getCurrentUser: state => {
      return state.currentUser
    }
  },
  actions: {
    async login(store, loggedInUser) {
      store.commit('setUser', loggedInUser)
    }
  }
})