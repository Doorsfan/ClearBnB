import express.Express;
import models.UserInfo;

import static nosqlite.Database.collection;

public class UserInfoHandler {

    private final Express app; // DEBUGGABLE remove final if errors on this line

    public UserInfoHandler(Express app) {
        this.app = app;
        initUserInfoHandler();
    }

    private void initUserInfoHandler() {
        // save a userInfo and return the object on the server
        app.post("/rest/userinfos", (req,res) -> {
            UserInfo userInfo = req.body(UserInfo.class);
            collection("UserInfo").save(userInfo);
            res.json(userInfo);
        });
        // get all userInfos
        app.get("/rest/userinfos", (req,res) -> {
            //res.json(req.session("current-userInfo")); //???
            res.json(collection("UserInfo").find());
        });
        // get a specific userInfo
        app.get("/rest/userinfos:id", (req,res) -> {
            res.json(collection("UserInfo").findById(req.params("id")));
        });
        // delete a specific userInfo
        app.delete("/rest/userinfos:id", (req,res) -> {
            res.json(collection("UserInfo").deleteById(req.params("id")));
        });
    }
}
