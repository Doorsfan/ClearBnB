import express.Express;
import models.Profit;

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
            List<Profit> allProfitsAsList = collection("Profit").find();
            ArrayList<Profit> allProfitsInAnArrayList = new ArrayList<Profit>(allProfitsAsList);
            double totalProfit = 0.0;
            for(int i = 0; i < allProfitsInAnArrayList.size() ; i++){
                totalProfit += allProfitsInAnArrayList.get(i).getTotalProfit();
            }
            totalProfit += myProfit.getTotalProfit();
            Profit profitToSave = new Profit(totalProfit);
            collection("Profit").delete();
            collection("Profit").save(profitToSave);
            res.json("Updated the Total Profit");
        });

        app.get("/rest/profit", (req, res) -> {
            res.json(collection("Profit").find());
        });
    }
}
