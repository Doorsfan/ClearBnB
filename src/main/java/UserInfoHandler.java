import express.Express;
import models.UserInfo;
import nosqlite.utilities.Filter;

import static nosqlite.Database.collection;

public class UserInfoHandler {

    private final Express app; // DEBUGGABLE remove final if errors on this line

    public UserInfoHandler(Express app) {
        this.app = app;
        initUserInfoHandler();
    }

    private void initUserInfoHandler() {
        // Updated a UserInfo - if it already exists, delete it - and save it anew
        app.post("/rest/userinfos/:id", (req,res) -> {
            UserInfo userInfo = req.body(UserInfo.class);
            UserInfo userInfoToDelete = collection("UserInfo").findOne(Filter.eq("userId", req.params("id")));
            if(userInfoToDelete != null){
                collection("UserInfo").deleteOne(Filter.eq("userId", req.params("id")));
            }
            collection("UserInfo").save(userInfo);
            res.json("Updated User info");
        });

        app.post("/rest/userinfos", (req,res) -> {
            UserInfo newUserInfo = req.body(UserInfo.class);
            collection("UserInfo").save(newUserInfo);
        });

        // get all userInfos
        app.get("/rest/userinfos", (req,res) -> {
            //res.json(req.session("current-userInfo")); //???
            res.json(collection("UserInfo").find());
        });
        // get a specific userInfo
        app.get("/rest/userinfos/:id", (req,res) -> {
            UserInfo relevantInfo = collection("UserInfo").findOne(Filter.eq("userId", req.params("id")));
            System.out.println("Found info in backend: " + relevantInfo);
            res.json(relevantInfo);
        });
        // delete a specific userInfo
        app.delete("/rest/userinfos/:id", (req,res) -> {
            collection("UserInfo").deleteOne(Filter.eq("userId", req.params("id")));
        });
    }
}
