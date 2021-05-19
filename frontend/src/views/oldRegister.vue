<form class="registerForm" @submit.prevent="registerUser">
    <div class="theBigDiv">
      <div class="mainDiv">
        <div class="nameDiv">
          <p class="userRegistrationText">User Registration</p>
          <input v-model="firstName" required type="text" placeholder="First Name">
          <input v-model="lastName" required type="text" placeholder="Last Name">
        </div>
        <div class="streetDiv">
          <input v-model="streetAddress" required type="text" class="StreetInput" placeholder="Street address">
        </div>
        <div class="zipcodeDiv">
          <input required v-model="zipCode" class="zipCodeInput" type="text" placeholder="Zip code"><input required v-model="country" class="countryInput" type="text" placeholder="Country"><input v-model="city" required class="cityInput" type="text" placeholder="City">
        </div>
        <div class="emailDiv">
          <input v-model="email" required type="email" placeholder="e-mail address"><input v-model="repeatEmail" required type="email" placeholder="Repeat e-mail">
        </div>
        <div class="passwordDiv">
          <input v-model="password" required type="password" placeholder="Password"><input v-model="repeatPassword" required type="password" placeholder="Repeat password">
        </div>
        <div class="phoneDiv">
          <input v-model="phoneNumber" required type="text" placeholder="Phone number"><input v-model="newsLetter" class="newsLetterCheckBox" type="checkbox"> Newsletter
        </div>
        <div class="errorBox">
          <p class="errorText">Passwords do not match up</p>
        </div>
        <div class="takenUserBox">
          <p class="errorText">User with the email {{ email }} already exists.</p>
        </div>
      </div>
    </div>
  </form>