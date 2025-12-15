package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import model.*;


public class FriefindSystem {
   
    private ArrayList<User> userList;
    private HashSet<Integer> userIdSet; 

    public FriefindSystem() {
        this.userList = new ArrayList<>();
        this.userIdSet = new HashSet<>();
    }


    public boolean addUser(User user) {
        if (userIdSet.contains(user.getId())) {
            System.out.println("Error: User with ID " + user.getId() + " already exists.");
            return false;
        }
        userList.add(user);
        userIdSet.add(user.getId());
        System.out.println("User added: " + user.getName());
        return true;
    }


    public void displayAllUsers() {
        System.out.println("\n--- All Friefind Users ---");
        if (userList.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User u : userList) {
                System.out.print(u); 
                System.out.print(" -> ");
                u.showProfileType(); 
            }
        }
    }

    public Meeting setMeeting(User U1,User U2, Location location) {
        Scanner scanner = new Scanner(System.in);
        
        
        Activity activity = new Activity();

        
        activity.getInput(); 

        System.out.print("Enter meeting time (e.g., 09/09/2025): ");
        String time = scanner.nextLine();
        Meeting meeting = new Meeting(U1, U2, location, activity, time);

      
        System.out.println("\n--- Meeting Created Successfully ---");
        System.out.println(meeting.toString()); 

       
        System.out.print("\nDo you want to change the time? (Y/N): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            System.out.print("Enter new time: ");
            String newTime = scanner.nextLine();
            meeting.setTime(newTime);
            
            System.out.println("\nTime updated.");
            System.out.println("Updated Meeting Details:");
            System.out.println(meeting.toString());
        } else {
            System.out.println("Meeting confirmed.");
        }
        return meeting;
    }
 
    public User searchUser(String name) {
        for (User u : userList) {
            if (u.getName().equalsIgnoreCase(name)) {
                System.out.println("User Found: " + u.getName());
                return u;
            }
        }
        System.out.println("User " + name + " not found.");
        return null;
    }

   
    public void deleteUser(int id) {
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User u = iterator.next();
            if (u.getId() == id) {
                iterator.remove();
                userIdSet.remove(id);
                System.out.println("User with ID " + id + " has been deleted.");
                User.totalUsers--;
                return;
            }
        }
        System.out.println("Deletion failed. ID " + id + " not found.");
    }

   
    public void calculateAverageAge() {
        if (userList.isEmpty()) {
            System.out.println("Average Age: 0");
            return;
        }
        int sum = 0;
        for (User u : userList) {
            sum += u.getAge();
        }
        double avg = (double) sum / userList.size();
        System.out.println("Average Age of Users: " + String.format("%.2f", avg));
    }
}