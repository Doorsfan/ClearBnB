export default class Booking {
  constructor(
    userId,
    leaseId,
    location,
    startDate,
    endDate,
    guests,
    totalPrice,
    bookedStay
  ) {
    this.userId = userId;
    this.leaseId = leaseId;
    this.location = location;
    this.startDate = startDate;
    this.endDate = endDate;
    this.guests = guests;
    this.totalPrice = totalPrice;
    this.bookedStay = bookedStay;
  }

  getUserId() {
    return this.userId;
  }
  setUserId(newUserId) {
    this.userId = newUserId;
  }
  getLeaseId() {
    return this.leaseId;
  }
  setLeaseId(newLeaseId) {
    this.leaseId = newLeaseId;
  }
  getLocation() {
    return this.location;
  }
  setLocation(newLocation) {
    this.location = newLocation;
  }
  getStartDate() {
    return this.startDate;
  }
  setStartDate(newStartDate) {
    this.startDate = newStartDate;
  }
  getEndDate() {
    return this.endDate;
  }
  setEndDate(newEndDate) {
    this.endDate = newEndDate;
  }
  getGuests() {
    return this.guests;
  }
  setGuests(newAmountOfGuests) {
    this.guests = newAmountOfGuests;
  }
  getTotalPrice() {
    return this.totalPrice;
  }
  setTotalPrice(newTotalPrice) {
    this.totalPrice = newTotalPrice;
  }
  getBookedStay() {
    return this.bookedStay;
  }
  setBookedStay(newBookedStay) {
    this.bookedStay = newBookedStay;
  }
  isInThePast(toCompare) {
    let correctFormatToCompare = this.getDateInCorrectFormat(toCompare);
    let splitFirstDate = correctFormatToCompare.split('-');
    let todayInCorrectFormat = this.getDateInCorrectFormat(new Date());
    let splitSecondDate = todayInCorrectFormat.split('-');
    if (
      splitFirstDate[0] <= splitSecondDate[0] &&
      splitFirstDate[1] <= splitSecondDate[1] &&
      splitFirstDate[2] <= splitSecondDate[2]
    ) {
      return true;
    }
    return false;
  }
  getDateInCorrectFormat(myDate) {
    let today = new Date(myDate);
    let month = today.getMonth() + 1;
    let day = today.getDate();
    let zeroBeforeMonth = '';
    let zeroBeforeDay = '';
    if (month < 10) {
      zeroBeforeMonth += '0' + month;
      month = zeroBeforeMonth;
    }
    if (day < 10) {
      zeroBeforeDay += '0' + day;
      day = zeroBeforeDay;
    }
    return today.getFullYear() + '-' + month + '-' + day;
  }
}
