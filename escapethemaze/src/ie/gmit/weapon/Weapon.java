package ie.gmit.weapon;

import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class Weapon extends TilePiece implements Wieldable {

	public Weapon(TileType tileType, int x, int z,String w,int aV, int dV) {
		super(tileType, x, z);
		
		this.weaponName = w;
		this.attackValue = aV;
		this.durabilityValue = dV;
	}

	private String weaponName;
	private int attackValue;
	private int durabilityValue;
	
	public String getWeaponName() {
		return this.weaponName;
	}

	public int getAttackValue() {
		return this.attackValue;
	}
	
	public int getDurabilityValue() {
		return this.durabilityValue;
	}
	
	public void weaponUsed()
	{
		this.durabilityValue--;
	}

}
