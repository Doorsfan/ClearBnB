import express.Express;

import static nosqlite.Database.collection;

public class Main {
  public static void main(String[] args) {
    Express app = new Express();
    
    collection(config -> config.useBrowser = true);

    //Init auth
    new Auth(app);
    new LeaseHandler(app);
    new BookingHandler(app);
    new UserInfoHandler(app);
    new AdminBookingHandler(app);
    new ProfitHandler(app);

    // start server
    app.listen(4000);
  }
}
