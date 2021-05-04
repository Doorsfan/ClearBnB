import express.Express;
import models.Cat;
import models.User;

import java.util.Map;

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
    
    collection(config -> {
      config.useBrowser = true;
    });

    //Init auth
    new Auth(app);

    /*
    Cat tyson = new Cat();
    tyson.setName("Cocos");
    tyson.setAge(10);
    tyson.setColor("Orange");
    tyson.setOwnerId("J2exkr9j6_mTZvegUkwE1");
    System.out.println("Tysen was: " + tyson);
    collection("Cat").save(tyson);
    
    User john = new User();
    john.setId("abc");
    john.setFirstName("John Doe");
    john.setEmail("john@doe.com");
    john.setPassword("secret");
    
    john.setCats(List.of(tyson));
    System.out.println("John was: " + john);
    //Keep in mind that Cats is put to be transient and as such, to get them, you must find them explicitly by
    //the owner ID
    collection(User.class).save(john);
  
//    collection(User.class).changeFieldName("firstName", "name");
    
    List<Cat> catsOwnedByJohn = collection("Cat").find("ownerId==J2exkr9j6_mTZvegUkwE1");
    User johnFormColl = collection("User").findById("J2exkr9j6_mTZvegUkwE1");
    
    johnFormColl.setCats(catsOwnedByJohn);
    
    System.out.println(johnFormColl); */
    
    app.get("/rest/hello", (req, res) -> {
      res.json(Map.of("message", "Hello from express"));
    });
    
    
    // user CRUD
    app.post("/rest/users", (req, res) -> {
      // doesn't crash with @JsonIgnoreProperties(ignoreUnknown = true)
      User user = req.body(User.class);
      
      // save user to collection
      collection("User").save(user);
      
      // return user with new id
      res.json(user);
    });
    
    // start server
    app.listen(4000);
  }
}
