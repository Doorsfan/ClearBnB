import express.Express;
import models.User;
import nosqlite.utilities.Filter;
import utilities.HashPassword;

import java.util.Map;

import static nosqlite.Database.collection;

// /rest is ONLY for manipulating raw entities

// /api - is used when the route handler need to perform logic
public class Auth {
    private Express app;
    public Auth(Express app) {
        this.app = app;
        initAuth();
    }

    private void initAuth() {

        app.post("/api/updateUser", (req, res) -> {
            User user = req.body(User.class);
            User existingUser = collection("User").findOne(Filter.eq("username", user.getUsername()));
            if(existingUser != null){
                collection("User").save(existingUser);
            }
            else{
                collection("User").save(user);
            }
            res.json("The new user info was: " + existingUser);
        });

        // register user
        app.post("/api/register", (req,res) -> {
            User user = req.body(User.class);

            // check if email is not taken
            User exists = collection("User").findOne(Filter.eq("username", user.getUsername()));

            //If it found a match, it's not null
            if(exists != null) {
                res.json(Map.of("error", "User already exists"));
                return;
            }

            // hash password (encrypt password)
            String hashedPassword = HashPassword.hash(user.getPassword()); // "hemligt" -> Hashed SHA String
            user.setPassword(hashedPassword);

            collection("User").save(user);

            res.json(user);
        });

        // log in user
        app.post("/api/login", (req, res) -> {
            User user = req.body(User.class);

            User userInColl = collection("User").findOne("username==" + user.getUsername());

            if(userInColl == null) {
                res.json(Map.of("error", "Bad credentials"));
                return;
            }

            // Validate password
            if(HashPassword.match(user.getPassword(), userInColl.getPassword())){
                // Save user in session, to remember logged in state
                req.session("current-user", userInColl);
                res.json(userInColl);
            }else {
                res.json(Map.of("error", "Bad credentials"));
            }
        });

        // who am i? Get logged in user
        app.get("/api/whoami", (req,res) -> {
            // return user in saved session
           res.json(req.session("current-user"));
        });
        //Logout user
        app.get("/api/logout", (req,res) -> {
           req.session("current-user", null); //Set the logged in user as being Null
           res.json(Map.of("ok", "logged out"));
        });

    }
}
