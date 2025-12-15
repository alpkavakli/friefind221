package model;

//PremiumUser.java
public class PremiumUser extends User {
	private boolean isIncognito; 

	public PremiumUser(int id, String name, int age, ProfileDetails details, boolean isIncognito) {
		super(id, name, age, details);
		this.isIncognito = isIncognito;
	}

	@Override
	public void showProfileType() {
		System.out.println("Type: Premium Member (Gold Badge) " + (isIncognito ? "[Incognito Mode]" : ""));
	}

	public void swipeRight(User targetUser) {
		System.out.println(this.name + " SUPER LIKED " + targetUser.getName() + "! (Unlimited Swipes)");
	}

	@Override
	public void swipeLeft(User targetUser) {
		System.out.println(this.name + "DID NOT LIKE " + targetUser.getName() + "! (Unlimited Swipes)");
	}
	
	@Override
	public String toString() {
		return "Premium User" + super.toString();
	}

}
