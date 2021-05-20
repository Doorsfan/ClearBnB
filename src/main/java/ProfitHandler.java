import express.Express;
import models.Booking;
import models.Profit;
import models.UserInfo;
import nosqlite.utilities.Filter;

import java.util.ArrayList;
import java.util.List;

import static nosqlite.Database.collection;

public class ProfitHandler {

    private final Express app; // DEBUGGABLE remove final if errors on this line

    public ProfitHandler(Express app) {
        this.app = app;
        initProfitHandler();
    }

    private void initProfitHandler() {
        app.post("/rest/profit/", (req,res) -> {
            Profit myProfit = req.body(Profit.class);
            List<Booking> allBookingsInAList = collection("Booking").find();
            ArrayList<Booking> allBookingsInAnArrayList = new ArrayList<Booking>(allBookingsInAList);
            double totalProfit = 0.0;
            for(int i = 0; i < allBookingsInAnArrayList.size() ; i++){
                totalProfit += (allBookingsInAnArrayList.get(i).getTotalPrice() * 1.15) - allBookingsInAnArrayList.get(i).getTotalPrice();
            }
            totalProfit = Math.round(totalProfit * 100.0) / 100.0;
            myProfit.setTotalProfit(totalProfit);
            collection("Profit").delete();
            collection("Profit").save(myProfit);
            res.json("Updated the Total Profit");
        });

        app.get("/rest/profit", (req, res) -> {
            res.json(collection("Profit").find());
        });
    }
}
