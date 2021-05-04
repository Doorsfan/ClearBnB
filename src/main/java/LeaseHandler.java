import express.Express;
import models.Lease;

import static nosqlite.Database.collection;

public class LeaseHandler {

    private final Express app; // DEBUGABLE remove final if errors on this line

    public LeaseHandler(Express app) {
        this.app = app;
        initLeaseHandler();
    }

    private void initLeaseHandler() {
        // save a lease and return the object on the server
        app.post("/rest/leases", (req,res) -> {
            Lease lease = req.body(Lease.class);
            collection("Lease").save(lease);
            res.json(lease);
        });
        // get all leases
        app.get("/rest/leases", (req,res) -> {
            //res.json(req.session("current-lease")); //???
            res.json(collection("Lease").find());
        });
        // get a specific lease
        app.get("/rest/leases:id", (req,res) -> {
            res.json(collection("Lease").findById(req.params("id")));
        });
        // delete a specific lease
        app.get("/rest/leases:id", (req,res) -> {
            res.json(collection("Lease").deleteById(req.params("id")));
        });
    }
}
