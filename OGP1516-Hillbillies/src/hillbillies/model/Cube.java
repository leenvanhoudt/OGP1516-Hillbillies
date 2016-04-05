package hillbillies.model;

public class Cube {
		
	public Cube (int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	private int x, y, z;
	
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
		this.fCost = cost;
	}
	
	public int getFCost(){
		return this.fCost;
	}
	
	private int fCost = Integer.MAX_VALUE;
	
	public final void setHCost(int[] endPosition){
		this.hCost = (int) Math.floor((Math.sqrt((Math.pow((endPosition[0]-this.getX())*10, 2))+
				(Math.pow((endPosition[1]-this.getY())*10, 2))+
				(Math.pow((endPosition[2]-this.getZ())*10, 2)))));
	}
	
	public int getHCost(){
		return this.hCost;
	}
	
	private int hCost = 0;
	
	public void setGCost(int cost){
		this.gCost = cost;
	}
	
	public int getGCost(){
		return this.gCost;
	}
	
	private int gCost = 0;

	
	public void setParent(Cube parent){
		this.parent = parent;
	}
	
	public Cube getParent(){
		return this.parent;
	}
	
	private Cube parent;

}
