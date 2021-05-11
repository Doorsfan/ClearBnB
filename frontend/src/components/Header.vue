<template>
  <div class="navbar">
    <div class="left">
    <router-link to="/">Home</router-link>
    </div>
    <div class="center">
    <router-link to="userPage">User Page</router-link>
    <router-link to="AddLease">Add Lease</router-link>
    </div>
    <div class="right">
    <router-link to="signUp"><i class="fas fa-user-plus"></i>Sign Up</router-link>
    <span>|</span>
    <router-link to="login"><i class="fas fa-sign-in-alt"></i>Login</router-link>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.navbar {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  display: flex;
  flex-direction: row;
  justify-content:space-between;
  border-bottom: 0.1rem solid #00200c;
  height: 7rem;
  align-items: center;
  padding: 0 2rem;
}
a {
  font-size: 1.7rem;
  text-decoration: none;
  color: rgb(63, 63, 63);

}
.center a{
margin: 0 1rem;
font-weight: bold;
}
.right{
  align-self: flex-start;
  margin-top: 1rem;
  display: flex;
  flex-direction: row;
  align-items: baseline;
}
.right span{
  font-size: 1.3rem;
  margin: 0 1rem;
  color: rgb(172, 172, 172);
}
.right a{
  font-size: 1.3rem;
  color: rgb(172, 172, 172);
  transition: color 0.3s;
}
.right i{
  font-size: 1.3rem;
  margin-right: 0.5rem;
}
.right a:hover{
  color: rgb(112, 112, 112);
}
</style>