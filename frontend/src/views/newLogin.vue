<template>
  <div class="mainDiv">
    <LoginForm />
  </div>
</template>
<script>
import LoginForm from '../components/LoginView/LoginForm.vue'
export default {
  components: {
    LoginForm
  },
  data() {
    return {
    }
  },
  mounted(){
    if(document.getElementsByClassName("sunIconInHeader").length > 0){
      document.getElementsByClassName("sunIconInHeader")[0].src = '/public/home_icon.png'
      document.getElementsByClassName("sunIconInHeader")[0].className = 'house_icon'
      document.getElementsByClassName("homeText")[0].style.display = 'block';
      document.getElementsByClassName("center")[0].style.height = '70px';
    }
    this.$store.dispatch('saveLatestRoute', this.$route.path);
  },
  methods:{
  }
}
</script>
<style scoped>
.mainDiv{
  background-image: url("../../public/nice.jpg");
  background-position:center;
  background-size:cover;
  background-repeat:no-repeat;
  height:max-content;
  width:100vw;
  background-attachment: fixed;
  overflow-x:hidden;
}
</style>
