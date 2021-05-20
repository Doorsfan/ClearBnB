import express.Express;

import static nosqlite.Database.collection;

/*
    C - Create  POST
    R - Read    GET
    U - Update  PUT
    D - Delete  DELETE
 */

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

    //app.get("/rest/hello", (req, res) -> {
    //  res.json(Map.of("message", "Hello from express"));
    //});

    // start server
    app.listen(4000);
  }
}
