export default class BookingHelper {
  constructor() {
    this.takenBookings = [];
  }
  getTakenBookings() {
    return this.takenBookings;
  }
  setTakenBookings(newTakenBookings) {
    this.takenBookings = newTakenBookings;
  }
}
