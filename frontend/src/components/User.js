export default class User {
  constructor(username, password, userInfo) {
    this.username = username;
    this.password = password;
    this.userInfo = userInfo;
  }
  getUsername() {
    return this.username;
  }
  setUsername(newUsername) {
    this.username = newUsername;
  }
  getUserInfo() {
    return this.userInfo;
  }
  setUserInfo(newUserInfo) {
    this.userInfo = newUserInfo;
  }
}
