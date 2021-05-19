<template>
  <footer>
    <div class="icons">
      <div class="faceBookIconDiv">
        <i class="fab fa-facebook-f"></i>
      </div>
      <div class="instagramIconDiv">
        <i class="fab fa-instagram"></i>
      </div>
      <div class="twitterIconDiv">
        <i class="fab fa-twitter"></i>
      </div>
    </div>
    <div class="links">
      <router-link to="/">home</router-link>
      <div></div>
      <router-link to="/Login">login</router-link>
      <div></div>
      <router-link to="/signUp">signup</router-link>
    </div>
    <div class="last">&copy; 2021 ClearBNB</div>
  </footer>
</template>

<style scoped>
footer {
  margin-top: 20px;
  background-color: #fff;
  background-image: url("/smaller_footer.png");
  background-repeat: no-repeat;
  background-position: center;
  padding: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width:100vw;
  height:3vh;
  padding-top:180px;
}

.icons {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-bottom: 20px;
}

.icons div {
  width: 45px;
  height: 45px;
  margin: 0rem 10px;
  display: grid;
  place-content: center;
  background-color: white;
  border-radius: 50%;
}

.icons div i {
  font-size: 20px;
  color: black;
}

.links {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.links a {
  text-decoration: none;
  text-transform: uppercase;
  font-weight: 700;
  font-size: 1.60rem;
  color: black;
  -webkit-text-stroke: 0.6px white;
}

.links a:hover {
  text-decoration: underline;
}

.links div {
  width: 5px;
  height: 5px;
  border-radius: 50%;
  margin: 0rem 10px;
  background-color: rgb(153, 153, 153);
}

.last {
  color: rgb(153, 153, 153);
  margin-top: 10px;
  font-size: 10px;
}
</style>