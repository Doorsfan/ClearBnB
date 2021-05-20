<template>
  <LoginForm />
</template>

<script>
import LoginForm from '../components/LoginView/LoginForm.vue'
export default {
  name: 'Login',
  components: {
    LoginForm
  },
  mounted(){
    if(document.getElementsByClassName("sunIconInHeader").length > 0){
      document.getElementsByClassName("sunIconInHeader")[0].src = '/public/home_icon.png'
      document.getElementsByClassName("sunIconInHeader")[0].className = 'house_icon'
      document.getElementsByClassName("homeText")[0].style.display = 'block';
      document.getElementsByClassName("center")[0].style.height = '70px';
    }
  }
}
</script>

<style scoped>
.backgroundImage{
  background-image:none;
}
</style>
