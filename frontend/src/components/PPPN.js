//computeDateRange(this.startDate, this.endDate)
//computeCost(this.days, this.price)

export default class PPPN {
  startDate = "2021-05-10"; //mock data
  endDate = "2021-06-15"; //mock data
  days = 5; //mock data
  price = 10; //mock data
  totalPrice = 0;

  constructor(startDate, endDate, days, price, totalPrice) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.days = days;
    this.price = price;
    this.totalPrice = totalPrice;
  }

  computeDateRange(startDate, endDate) {
    startDate = this.convertToDate(startDate);
    endDate = this.convertToDate(endDate);

    let dateDifferenceInMs = endDate.getTime() - startDate.getTime();
    let days = dateDifferenceInMs / (1000 * 60 * 60 * 24);
    days = Math.round(days)
    return days;
  }

  convertToDate(isoDate) {
    let year = isoDate.substring(0, 4);
    let month = isoDate.substring(5, 7);
    let day = isoDate.substring(9, 10);
    let date = new Date();
    year = parseInt(year)
    month = parseInt(month) - 1
    day = parseInt(day)
    date.setFullYear(year);
    date.setMonth(month);
    date.setDate(day);

    return date;
  }

  computeCost(days, price) {
    return days * price;
  }
}