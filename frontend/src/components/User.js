export default class User {
  constructor(username, password, myBookings, userInfo) {
    this.username = username;
    this.password = password;
    this.myBookings = myBookings;
    this.userInfo = userInfo;
  }
  getUsername() {
    return this.username;
  }
  setUsername(newUsername) {
    this.username = newUsername;
  }
  getBookings() {
    return this.myBookings;
  }
  setBookings(newSetOfBookings) {
    this.myBookings = newSetOfBookings;
  }
  addBooking(newBooking) {
    this.myBookings.push(newBooking);
  }
  getUserInfo() {
    return this.userInfo;
  }
  setUserInfo(newUserInfo) {
    this.userInfo = newUserInfo;
  }
}
