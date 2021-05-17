import express.Express;
import models.Booking;
import nosqlite.utilities.Filter;

import static nosqlite.Database.collection;

public class BookingHandler {

    private final Express app; // DEBUGGABLE remove final if errors on this line

    public BookingHandler(Express app) {
        this.app = app;
        initBookingHandler();
    }

    private void initBookingHandler() {
        // save a Booking and return the object on the server
        app.post("/rest/bookings", (req, res) -> {
            Booking Booking = req.body(Booking.class);
            Booking.getBookedStay().setId(Booking.getLeaseId());
            collection("Booking").save(Booking);
            res.json(Booking);
        });
        // get all bookings
        app.get("/rest/bookings", (req, res) -> {
            System.out.println("All bookings was: " + collection("Booking").find());
            res.json(collection("Booking").find());
        });
        // get a specific booking
        app.get("/rest/bookings/:id", (req, res) -> {
            res.json(collection("Booking").findById(req.params("id")));
        });
        // delete a specific booking
        app.delete("/rest/bookings/:id", (req, res) -> {
            res.json(collection("Booking").deleteOne(Filter.eq("id", req.params("id"))));
        });
    }
}
