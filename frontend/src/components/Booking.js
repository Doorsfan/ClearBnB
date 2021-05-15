import { toDisplayString } from "@vue/shared";

export default class Booking {
  constructor(userId, leaseId, location, startDate, endDate, guests, totalPrice, bookedStay) {
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
    this.startDate = newStartDate
  }
  getEndDate() {
    return this.endDate;
  }
  setEndDate(newEndDate) {
    this.endDate = newEndDate;
  }
  getGuests() {
    return this.guests
  }
  setGuests(newAmountOfGuests) {
    this.guests = newAmountOfGuests
  }
  getTotalPrice() {
    return this.totalPrice
  }
  setTotalPrice(newTotalPrice) {
    this.totalPrice = newTotalPrice
  }
  getBookedStay() {
    return this.bookedStay
  }
  setBookedStay(newBookedStay) {
    this.bookedStay = newBookedStay
  }
  isInTheFuture(toCompare) {
    console.log("Based on : ", toCompare);
    console.log("Versus : ", new Date());
    console.log("It was deemed to be in the future: ", toCompare < new Date());
    let isInTheFuture = toCompare < new Date()
    return isInTheFuture
  }
}
