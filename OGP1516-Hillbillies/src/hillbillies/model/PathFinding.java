package hillbillies.model;

import java.util.ArrayList;
import java.util.Collections;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class which can be used to find paths between cubes.
 *
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 */
public class PathFinding {

	/**
	 * Return a list of cubes that form a path to the given cubeEndPosition.
	 * 
	 * @param cubeEndPosition
	 * 		The position of the cube to which to calculate the path.
	 * @throws IndexOutOfBoundsException
	 * 		| No path can be found to the given cubeEndPosition.
	 * @return ...
	 * 		| Return an ArrayList of Cubes that form a path to the given cubeEndPosition.
	 */
	public ArrayList<Cube> findPath(int[] cubeEndPosition) throws IllegalArgumentException,
			IndexOutOfBoundsException{
		Cube[][][] grid = this.makeGrid(cubeEndPosition);
		ArrayList<Cube> open = new ArrayList<Cube>();
		ArrayList<Cube> closed = new ArrayList<Cube>();
		Cube startCube = grid[this.getUnit().getCubeCoordinate()[0]][this.getUnit().getCubeCoordinate()[1]][this.getUnit().getCubeCoordinate()[2]];
		open.add(startCube);
		Cube current = startCube;
		Cube minimum = startCube;
		
		while (!(current.getX() == cubeEndPosition[0])
				|| !(current.getY() == cubeEndPosition[1])
				|| !(current.getZ() == cubeEndPosition[2])){
			if (open.isEmpty()){
				this.getUnit().setCurrentSpeed(0);
				this.getUnit().defaultBehaviorCase3 = false;
				this.getUnit().isMovingTo = false;
				throw new IndexOutOfBoundsException();
			}

			current = this.searchMinimumCostCube(minimum, open);
			open.remove(current);
			closed.add(current);
			
			this.neighbouringCubes(current, grid, open, closed);
		}
		ArrayList<Cube> path = this.makePath(current, startCube);
		return path;
	}
	
	/**
	 * Return a grid of cubes with the same dimensions as the world the unit that
	 * wants to find the path is placed in. Also set the hCost of the cubes in this grid.
	 * The hCost is the distance from a cube to the cubeEndPosition.
	 * 
	 * @param cubeEndPosition
	 * 		The position of the cube to which to calculate the path.
	 * @effect ...
	 * 		| The hCost of every cube in the list is set.
	 * @return ...
	 * 		| Result is a 3D list of Cubes.
	 */
	private Cube[][][] makeGrid(int[] cubeEndPosition){
		Cube[][][] grid = new Cube[this.getUnit().getWorld().getNbCubesX()][this.getUnit().getWorld().getNbCubesY()][this.getUnit().getWorld().getNbCubesZ()];
		for (int i=0; i<this.getUnit().getWorld().getNbCubesX(); i++){
			for (int j=0; j<this.getUnit().getWorld().getNbCubesY(); j++){
				for (int k=0; k<this.getUnit().getWorld().getNbCubesZ(); k++){
					grid[i][j][k] = new Cube(i,j,k);
					grid[i][j][k].setHCost(cubeEndPosition);
				}
			}
		}
		return grid;
	}
	
	/**
	 * Return the cube from the given list that has the minimum cost.
	 * 
	 * @param miminum
	 * 		The current minimum cost to which we compare the costs of the cubes in open.
	 * @param open
	 * 		The list of cubes to check.
	 * @return ...
	 * 		| Return the cube in open with the smallest cost.
	 * 		| If there is only one cube in open, this cube is returned.
	 */
	private Cube searchMinimumCostCube(Cube minimum, ArrayList<Cube> open){
		minimum = open.get(0);
		for (Cube i: open){
			if (open.size()==1){
				minimum = open.get(0);
			}
			else if (i.getFCost()< minimum.getFCost() 
					|| (i.getFCost()==minimum.getFCost() && i.getHCost()<minimum.getHCost())){
				minimum = i;
			}
		}
		return minimum;
	}
	
	/**
	 * Update the cost of all the neighbouring cubes of the current cube.
	 * 
	 * @param current
	 * 		The cube of which the neighbouring cubes are updated.
	 * @param open
	 * 		The list of cubes that are still open and still have to be checked.
	 * @param closed
	 * 		The list of cubes that are closed and are already checked or aren't valid positions.
	 * @effect..
	 * 		| If a neighbouring cube is passable and not a falling position, its cost
	 * 		| is updated.
	 * 		| The amount by which the cost is updated depends on the distance between
	 * 		| the neighbouring cube and the current cube.
	 * 		| If a neighbouring cube is checked, it is added to closed.
	 */
	private void neighbouringCubes(Cube current, Cube[][][] grid,ArrayList<Cube> open, ArrayList<Cube> closed ){
		for (int i=-1; i<2; i++){
			for (int j=-1; j<2; j++){
				for (int k=-1; k<2; k++){
					if (current.getX()+i>=0 && current.getX()+i<this.getUnit().getWorld().getNbCubesX() 
							&& current.getY()+j>=0 && current.getY()+j<this.getUnit().getWorld().getNbCubesY() 
							&& current.getZ()+k>=0 && current.getZ()+k<unit.getWorld().getNbCubesZ()){
						Cube adjacent = grid[current.getX()+i][current.getY()+j][current.getZ()+k];
						if (this.getUnit().getWorld().isPassable(adjacent.getX(), adjacent.getY(), adjacent.getZ())
								&& !this.getUnit().isFallingPosition(adjacent.getX(), adjacent.getY(), adjacent.getZ())){
							if (((i==-1 || i==1) && j==0 && k==0) || ((j==-1 || j==1) && i==0 && k==0) || ((k==-1 || k==1) && j==0 && i==0)){
								this.updateCost(current, adjacent, current.getGCost()+10, open, closed);
							}
							else if (((i==-1||i==1)&&(j==-1||j==1)&&k==0)||((i==-1||i==1)&&(k==-1||k==1)&&j==0)||((k==-1||k==1)&&(j==-1||j==1)&&i==0)){
								this.updateCost(current, adjacent, current.getGCost()+14, open, closed);
							}
							else if ((i==-1||i==1)&&(j==-1||j==1)&&(k==-1||k==1)){
								this.updateCost(current, adjacent, current.getGCost()+17, open, closed);
							}	
						}
						else{
							closed.add(adjacent);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Update the cost of the given cube.
	 * 
	 * @param current
	 * 		The cube to which to set the parent of adjacent.
	 * @param adjacent
	 * 		The cube to update.
	 * @param cost
	 * 		The amount that is used to update the cost of adjacent.
	 * @param open
	 * 		The list of cubes that are still open and still have to be checked.
	 * @param closed
	 * 		The list of cubes that are closed and are already checked or aren't valid positions.
	 * @effect ...
	 * 		| If the adjacent cube is passable and the new final cost is smaller than the
	 * 		| current fCost of adjacent or adjacent isn't in open, the fCost of adjacent 
	 * 		| is set to the sum of the current hCost of adjacent and the given cost.
	 * 		| Also, the gCost of adjacent is set to the given cost and its parent is set
	 * 		| to current.
	 * 		| If adjacent isn't in open yet, it is added to open.
	 */
	private void updateCost(Cube current, Cube adjacent, int cost,ArrayList<Cube> open, ArrayList<Cube> closed){
		int finalCost = adjacent.getHCost()+cost;
		if ((this.getUnit().getWorld().isPassable(adjacent.getX(),adjacent.getY(),adjacent.getZ()) && !closed.contains(adjacent))
				&& (finalCost < adjacent.getFCost() || !open.contains(adjacent))){
			adjacent.setFCost(finalCost);
			adjacent.setGCost(cost);
			adjacent.setParent(current);
			if (!open.contains(adjacent)){
				open.add(adjacent);
			}
		}
	}
	
	/**
	 * Return a list of the cubes that form a path by consecutively adding the parent
	 * of the current cube and changing the current cube to its parent.
	 * 
	 * @param current
	 * 		The current cube that is added to the path.
	 * @param startCube 
	 * 		The cube from where the path has to start.
	 * @return ...
	 * 		| Result is the ArrayList of Cubes that form the path.
	 */
	private ArrayList<Cube> makePath(Cube current, Cube startCube){
		ArrayList<Cube> path = new ArrayList<Cube>();
		if (current != startCube){
			while (current.getParent()!=startCube){
				path.add(current);
				current = current.getParent();
			}
		}
		path.add(current);
		path.add(startCube);
		Collections.reverse(path);
		return path;
	}
	
	/**
	 * Return the unit that wants to find the path.
	 */
	@Basic
	public Unit getUnit() {
		return this.unit;
	}
	
	/**
	 * Set the unit to the given unit.
	 * @param unit
	 * 		the unit for which the path is searched.
	 * @post ...
	 * 		| The unit is set to the given unit.
	 */
	public void setUnit(Unit unit){
		this.unit = unit;
	}
	
	/**
	 * Object of the class unit registering the unit that wants to find the path.
	 */
	private Unit unit;
	
	
	
	
}
