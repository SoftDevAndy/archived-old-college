package ie.gmit.food;

public interface Eatable {
	public int giveHealth();
	public int giveHunger();
	public boolean isEdable();
	public void pickedUp();
	public String getFoodName();
}
