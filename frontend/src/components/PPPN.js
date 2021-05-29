export default class PPPN {
  constructor() {}

  computeDateRange(startDate, endDate) {
    let dateDifferenceInMs = endDate.getTime() - startDate.getTime();
    let days = dateDifferenceInMs / (1000 * 60 * 60 * 24);
    return days;
  }
  addDays(wantedDate, days) {
    var date = new Date(wantedDate.valueOf());
    date.setDate(date.getDate() + days);
    return date;
  }
  getCorrectDateFormat(wantedDate) {
    if (!wantedDate) {
      wantedDate = new Date();
    }
    let year = wantedDate.getFullYear();
    let month = wantedDate.getMonth() + 1;
    let day = wantedDate.getDate();
    if (month < 10) {
      month = '0' + month;
    }
    if (day < 10) {
      day = '0' + day;
    }
    return year + '-' + month + '-' + day;
  }

  computeCost(days, price) {
    return days * price;
  }
}
