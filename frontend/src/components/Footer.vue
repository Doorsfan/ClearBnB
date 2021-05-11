<template>
<footer>
  <div class='icons'>
    <div>
      <i class="fab fa-facebook-f"></i>
    </div>
    <div>
      <i class="fab fa-instagram"></i>
    </div>
    <div>
      <i class="fab fa-twitter"></i>
    </div>
  </div>
  <div class='links'>
    <router-link to="/">home</router-link>
    <div></div>
    <router-link to="/Login">login</router-link>
    <div></div>
    <router-link to="/signUp">signup</router-link>
  </div>
  <div class="last">
      &copy; 2021 ClearBNB
  </div>
</footer>
  
</template>

<style scoped>
* {
  font-size: 13px;
}
footer {
  background-color: #00200c;
  padding: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.icons{
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-bottom: 2rem;
}

.icons div{
  width: 4.5rem;
  height: 4.5rem;
  margin: 0rem 1rem;
  display: grid;
  place-content: center;
  background-color: white;
  border-radius: 50%;
}

.icons div i{
  font-size: 2rem;
  color: black;
}

.links{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.links a{
  text-decoration: none;
  text-transform: uppercase;
  font-weight: 100;
  color: white;
}

.links a:hover{
  text-decoration: underline;
}

.links div{
  width: 0.5rem;
  height: 0.5rem;
  border-radius: 50%;
  margin: 0rem 1rem;
  background-color: rgb(153, 153, 153);
}

.last{
  color: rgb(153, 153, 153);
  margin-top: 1rem;
}
</style>