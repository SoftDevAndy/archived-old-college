package ie.gmit.monster;

public interface Fightable {
	public void deprecateHealth(int val);
	public int getAttack();
	public void makeDead(boolean flag);
	public int getHealth();
	public boolean isDead();
}
