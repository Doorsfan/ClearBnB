import express.Express;
import models.AdminBooking;
import models.Booking;
import models.UserInfo;
import nosqlite.utilities.Filter;

import static nosqlite.Database.collection;

public class AdminBookingHandler {

    private final Express app; // DEBUGGABLE remove final if errors on this line

    public AdminBookingHandler(Express app) {
        this.app = app;
        initAdminBookingHandler();
    }

    private void initAdminBookingHandler() {
        // save a adminBooking and return the object on the server
        app.post("/rest/adminBookings", (req, res) -> {
            AdminBooking adminBooking = req.body(AdminBooking.class);
            adminBooking.getBookedStay().setId(adminBooking.getBookedStay().getLeaseId());
            collection("AdminBooking").save(adminBooking);
            res.json(adminBooking);
        });
        // get all adminBookings
        app.get("/rest/adminBookings", (req, res) -> {
            res.json(collection("AdminBooking").find());
        });
        // get a specific adminBooking
        app.get("/rest/adminBookings/:id", (req, res) -> {
            String relevantId = req.params("id");
            AdminBooking adminBooking = collection("AdminBooking").findOne(Filter.eq("id", relevantId));
            res.json(adminBooking);
        });
        // delete a specific booking
        app.delete("/rest/adminBookings/:id", (req, res) -> {
            String relevantId = req.params("id");
            collection("AdminBooking").deleteOne(Filter.eq("id", relevantId));
            res.json("Deleted a Booking");
        });
    }
}
