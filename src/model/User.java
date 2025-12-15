package model;
import interfaces.Matchable;

//User.java
public abstract class User implements Matchable {
 protected int id;
 protected String name;
 protected int age;
 

 protected ProfileDetails details; 

 public static int totalUsers = 0;

 public User(int id, String name, int age, ProfileDetails details) {
     this.id = id;
     this.name = name;
     this.age = age;
     this.details = details;
     totalUsers++; 
 }

 public abstract void showProfileType();

 public static void displayTotalUserCount() {
     System.out.println("Total Users Created in Friefind: " + totalUsers);
 }

 public int getId() {
     return id;
 }

 public String getName() {
     return name;
 }
 
 public int getAge() {
     return age;
 }

 @Override
 public String toString() {
     return "\nID: " + id + "\nName: " + name + " (" + age + ")\n" + details;
 }
}
