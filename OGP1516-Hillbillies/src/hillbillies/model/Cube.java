package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Value;

/**
 * A class of cubes and their position, costs and parent.
 * 
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 */
@Value
public class Cube {
		
	/**
	 * Create a cube at position x,y,z.
	 * @param x
	 * 		The x coordinate of the position.
	 * @param y
	 * 		The y coordinate of the position.
	 * @param z
	 * 		The z coordinate of the position.
	 * @effect ...
	 * 		| The x, y and z coordinate of the cubes position are set to the given x,
	 * 		| y and z coordinate.
	 */
	public Cube (int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Variables registering the x,y and z coordinate of the cubes position.
	 */
	private int x, y, z;
	
	/**
	 * Return the x coordinate of the cubes position.
	 */
	@Basic
	public int getX(){
		return this.x;
	}
	
	/**
	 * Return the y coordinate of the cubes position.
	 */
	@Basic
	public int getY(){
		return this.y;
	}
	
	/**
	 * Return the z coordinate of the cubes position.
	 */
	@Basic
	public int getZ(){
		return this.z;
	}
	
	
	/**
	 * Return the fCost of the cube.
	 */
	@Basic
	public int getFCost(){
		return this.fCost;
	}
	
	/**
	 * Set the fCost of the cube to the given cost.
	 * The fCost is the final cost of the cube == gCost + hCost.
	 * 
	 * @param cost
	 * 		The cost to which to set the fCost of the cube.
	 * @post ...
	 * 		| The fCost of the cube is set to the given cost.
	 */
	public void setFCost(int cost){
		this.fCost = cost;
	}
	
	/**
	 * Variable registering the fCost of the cube. The default value is the maximum
	 * integer value.
	 */
	private int fCost = Integer.MAX_VALUE;
	
	/**
	 * Return the hCost of the cube.
	 */
	@Basic
	public int getHCost(){
		return this.hCost;
	}
	
	/**
	 * Set the hCost of the cube to the cost calculated using the endPosition.
	 * The hCost is the distance from this cube to the endPosition.
	 * 
	 * @param endPosition
	 * 		The endPosition to which the hCost is calculated.
	 * @post ...
	 * 		| The hCost of the cube is set to the calculated cost.
	 */
	@Basic
	public final void setHCost(int[] endPosition){
		this.hCost = (int) Math.floor((Math.sqrt((Math.pow((endPosition[0]-this.getX())*10, 2))+
				(Math.pow((endPosition[1]-this.getY())*10, 2))+
				(Math.pow((endPosition[2]-this.getZ())*10, 2)))));
	}

	/**
	 * Variable registering the hCost of the cube. The default value is 0.
	 */
	private int hCost = 0;
	
	/**
	 * Return the gCost of the cube.
	 */
	@Basic
	public int getGCost(){
		return this.gCost;
	}
	
	/**
	 * Set the gCost of the cube to the given cost.
	 * The gCost is the distance from this cube to the startPosition.
	 * 
	 * @param cost
	 * 		The cost to which to set the gCost of the cube.
	 * @post ...
	 * 		| The gCost of the cube is set to the given cost.
	 */
	public void setGCost(int cost){
		this.gCost = cost;
	}
	
	/**
	 * Variable registering the gCost of the cube. The default value is 0.
	 */
	private int gCost = 0;

	/**
	 * Return the parent of the cube.
	 */
	@Basic
	public Cube getParent(){
		return this.parent;
	}
	
	/**
	 * Set the parent of the cube to the given parent.
	 * 
	 * @param parent
	 * 		The cube to which to set the cubes parent.
	 * @post ...
	 * 		| The cubes parent is set to the given parent.
	 */
	public void setParent(Cube parent){
		this.parent = parent;
	}
	
	/**
	 * Object of the class cube registering the parent of the cube.
	 */
	private Cube parent;
	
	
	/**
	 * Return the dijkstraCost of this cube.
	 */
	@Basic
	public int getDijkstraCost(){
		return this.dijkstraCost;
	}

	/**
	 * Set the dijkstraCost of this cube to the given value.
	 * The dijkstraCost is the distance from the startCube to this cube.
	 * 
	 * @param value
	 * 		The cost to which to set the gCost of the cube.
	 * @post ...
	 * 		| The dijkstraCost of the cube is set to the given value.
	 */
	public void setDijkstraCost(int value){
		this.dijkstraCost = value;
	}
	
	/**
	 * Variable registering the dijkstraCost of the cube.
	 */
	private int dijkstraCost;
	
	
	/**
	 * Return the dijkstraParent of the cube.
	 */
	@Basic
	public Cube getDijkstraParent(){
		return this.DijkstraParent;
	}
	
	/**
	 * Set the dijkstraParent of the cube to the given parent.
	 * 
	 * @param parent
	 * 		The cube to which to set the cubes dijkstraParent.
	 * @post ...
	 * 		| The cubes dijkstraParent is set to the given parent.
	 */
	public void setDijkstraParent(Cube parent){
		this.DijkstraParent = parent;
	}
	
	/**
	 * Object of the class cube registering the dijkstraParent of the cube.
	 */
	private Cube DijkstraParent;
}
