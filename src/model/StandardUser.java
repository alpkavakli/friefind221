package model;

public class StandardUser extends User {

	public StandardUser(int id, String name, int age, ProfileDetails details) {
		super(id, name, age, details);
	}

	@Override
	public void showProfileType() {
		System.out.println("Type: Standard Member (Free)");
	}

	public void swipeRight(User targetUser) {
		System.out.println(this.name + " liked " + targetUser.getName() + "! (Standard Limit: 5/day)");
	}

	@Override
	public void swipeLeft(User targetUser) {
		System.out.println(this.name + "did not like " + targetUser.getName() + "! (Standard Limit: 5/day)");
	}
	
	@Override
	public String toString() {
		return "Standard User" + super.toString();
	}

}
