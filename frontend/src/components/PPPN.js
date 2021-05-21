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
    console.log("got in the value of: ", wantedDate);
      let year = wantedDate.getFullYear();
      let month = wantedDate.getMonth() + 1;
      let day = wantedDate.getDate();
      if(month < 10){
        month = '0' + month;
      }
      if(day < 10){
        day = '0' + day;
      }
      return year + '-' + month + '-' + day;
  }

  convertToDate(isoDate) {
    isoDate = isoDate.toString();
    console.log("The iso String was: ", isoDate);
    let day = isoDate.substring(0, 4);
    let month = isoDate.substring(6, 8);
    let year = isoDate.substring(9, 11);
    console.log("year was: ", year);
    console.log("month was: ", month);
    console.log("day was : ", day);
    let date = new Date(year, month, day);
    console.log("At the end, date was: ", date);
    return date;
  }
  computeCost(days, price) {
    return days * price;
  }
}