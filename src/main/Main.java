package main;
import model.*;
import service.*;

//Main.java
public class Main {
 public static void main(String[] args) {
     FriefindSystem app = new FriefindSystem();

     ProfileDetails p1 = new ProfileDetails("Loves hiking", "Mountaineering");
     ProfileDetails p2 = new ProfileDetails("Coffee addict", "Reading");
     ProfileDetails p3 = new ProfileDetails("Traveler", "Photography");

     User u1 = new StandardUser(101, "Harvey", 25, p1);
     User u2 = new PremiumUser(102, "Barney", 29, p2, true);
     User u3 = new StandardUser(103, "Joe", 22, p3);

     app.addUser(u1);
     app.addUser(u2);
     app.addUser(u3);
     
    
     User.displayTotalUserCount(); 

    
     app.displayAllUsers();

     System.out.println("\n--- Searching for 'Harvey' ---");
     app.searchUser("Harvey");

     System.out.println("\n--- Statistics ---");
     app.calculateAverageAge();

     System.out.println("\n--- Swiping Action ---");
     u2.swipeRight(u1); 
     u1.swipeRight(u2); 

 
     System.out.println("\n--- Deleting User 103 ---");
     app.deleteUser(103);
     app.displayAllUsers();
 }
}