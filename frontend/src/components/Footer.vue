<template>
  <footer>
    <div class="overlay"></div>
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
  margin-top: 0;
  background-color: #fdbf81;
  background-image: url("../assets/skyline_sunset.png");
  background-repeat: no-repeat;
  background-position: center;
  padding: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding-top: 140px;
  position: absolute;
  z-index: 1;
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
  font-size: 1.6rem;
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
  background-color: white;
}

.last {
  color: white;
  margin-top: 10px;
  font-size: 18px;
  -webkit-text-stroke: 0.4px black;
}
@media only screen and (max-width: 600px) {
  * {
    transform: scale(0.9);
  }
  .last {
    font-size: 14px;
    padding-bottom: 20px;
  }
}
@media only screen and (max-width: 475px) {
  footer {
    transform: scale(0.8);
  }
  .last {
    font-size: 14px;
    padding-bottom: 20px;
  }
}
@media only screen and (max-width: 375px) {
  footer {
    transform: scale(0.7);
  }
  .last {
    font-size: 14px;
    padding-bottom: 20px;
  }
}
@media only screen and (max-width: 270px) {
  footer {
    transform: scale(0.7);
  }
  .last {
    font-size: 14px;
    padding-bottom: 20px;
  }
}
</style>