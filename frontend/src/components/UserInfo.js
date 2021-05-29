export default class UserInfo {
  constructor(
    userId,
    firstName,
    lastName,
    streetAddress,
    zipCode,
    city,
    country,
    phoneNumber,
    newsLetter
  ) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.streetAddress = streetAddress;
    this.zipCode = zipCode;
    this.city = city;
    this.country = country;
    this.phoneNumber = phoneNumber;
    this.newsLetter = newsLetter;
  }
  getUserId() {
    return this.userId;
  }
  getFirstName() {
    return this.firstName;
  }
  setFirstName(newFirstName) {
    this.firstName = newFirstName;
  }
  getLastName() {
    return this.lastName;
  }
  setLastName(newLastName) {
    this.lastName = newLastName;
  }
  getStreetAddress() {
    return this.streetAddress;
  }
  setStreetAddress(newStreetAddress) {
    this.streetAddress = newStreetAddress;
  }
  getZipCode() {
    return this.zipCode;
  }
  setZipCode(newZipCode) {
    this.zipCode = newZipCode;
  }
  getCity() {
    return this.city;
  }
  setCity(newCity) {
    this.city = newCity;
  }
  getCountry() {
    return this.country;
  }
  setCountry(newCountry) {
    this.country = newCountry;
  }
  getPhoneNumber() {
    return this.phoneNumber;
  }
  setPhoneNumber(newPhoneNumber) {
    this.phoneNumber = newPhoneNumber;
  }
  getNewsLetter() {
    return this.newsLetter;
  }
  setNewsLetter(wantsNewsLetter) {
    this.newsLetter = wantsNewsLetter;
  }
}
