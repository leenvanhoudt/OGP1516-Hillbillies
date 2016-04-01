package hillbillies.model;

public class Cube {
	
	//int hCost = 0;
	private int fCost = 0;
	private int x, y, z;
	Cube parent;
	
	public Cube (int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getZ(){
		return this.z;
	}
	
	public void setFCost(int cost){
		this.fCost += cost;
	}
	
	public int getFCost(){
		return this.fCost;
	}
	
	public void setParent(Cube parent){
		this.parent = parent;
	}
	
	public Cube getParent(){
		return this.parent;
	}
}
