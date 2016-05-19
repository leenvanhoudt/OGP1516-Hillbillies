package hillbillies.model;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.Basic;

/**
 *  A class which can be used to find the closest boulder/log/unit/friend/enemy.
 * 
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 *
 */
public class DijkstraPathFinding {

	/**
	 * Find the position of the closest boulder/log/unit/friend/enemy
	 * 
	 * @param value
	 * 		the value specifies the condition for which we are applying Dijkstra Algorithm.
	 * @return ...
	 * 		| the position of the cube where the closest log/boulder/unit/friend/enemy
	 * 		| is located.
	 * @throws IndexOutOfBoundsException ...
	 * 		| throws an exception when no log/boulder/unit/friend/enemy is found.
	 */
	public int[] Dijkstra(int value) throws IndexOutOfBoundsException{
		ArrayList<Cube> unchecked = new ArrayList<Cube>();
		Cube[][][] grid = this.makegrid(unchecked);
		Cube startCube = grid[this.getUnit().getCubeCoordinate()[0]][this.getUnit().getCubeCoordinate()[1]][this.getUnit().getCubeCoordinate()[2]];
		startCube.setDijkstraCost(0);
		Cube minimum = unchecked.get(0);
		
		while (!unchecked.isEmpty()){
			minimum = unchecked.get(0);
			for(Cube cube : unchecked){
				if (cube.getDijkstraCost() < minimum.getDijkstraCost())
					minimum = cube;
			}
			if (minimum.getDijkstraCost() == Integer.MAX_VALUE)
				throw new IndexOutOfBoundsException();
			unchecked.remove(minimum);
			int[] pos = {minimum.getX(),minimum.getY(),minimum.getZ()};
			if(value == 0 && this.getUnit().getWorld().cubeContainsBoulder(pos[0], pos[1], pos[2]) ||
				(value == 1 && this.getUnit().getWorld().cubeContainsLog(pos[0], pos[1], pos[2])) ||
				(value == 2 && (this.getUnit().getWorld().getCubeType(pos[0], pos[1], pos[2]) == 3)) ||
				(value == 3 && this.getUnit().getWorld().CubeContainOtherUnit(pos[0], pos[1], pos[2], this.getUnit()))||
				(value == 4 && this.getUnit().getWorld().CubeContainFriend(pos[0], pos[1], pos[2], this.getUnit())) ||
				(value == 5 && this.getUnit().getWorld().CubeContainEnemy(pos[0], pos[1], pos[2], this.getUnit()))){
				break;
			}
			this.calculateDijkstraCost(minimum, grid);
		}
		
		int[] cubePosition = {minimum.getX(),minimum.getY(),minimum.getZ()};
		return cubePosition;
	}
	
	/**
	 * Return a grid of cubes with the same dimensions as the world where the
	 * boulder/log/unit/friend/enemy is placed in.
	 * 
	 * @param unchecked
	 * 		the list wherein the passable and not fallingposition cubes are added.
	 * @post ...
	 * 		| The dijkstraCost of every cube in the grid is set to maximum integer value.
	 * @post ...
	 * 		| Every passable and not-fallingposition cube is added to the ArrayList unchecked.
	 * @return ...
	 * 		| Result is a 3D list of Cubes.
	 */
	private Cube[][][] makegrid(ArrayList<Cube> unchecked){
		Cube[][][] grid = new Cube[this.getUnit().getWorld().getNbCubesX()][this.getUnit().getWorld().getNbCubesY()][this.getUnit().getWorld().getNbCubesZ()];
		for (int i=0; i<this.getUnit().getWorld().getNbCubesX(); i++){
			for (int j=0; j<this.getUnit().getWorld().getNbCubesY(); j++){
				for (int k=0; k<this.getUnit().getWorld().getNbCubesZ(); k++){
					grid[i][j][k] = new Cube(i,j,k);
					grid[i][j][k].setDijkstraCost(Integer.MAX_VALUE);
					if (this.getUnit().getWorld().isPassable(grid[i][j][k].getX(), grid[i][j][k].getY(), grid[i][j][k].getZ())
							&& !this.getUnit().isFallingPosition(grid[i][j][k].getX(), grid[i][j][k].getY(), grid[i][j][k].getZ())){
					unchecked.add(grid[i][j][k]);
					}
				}
			}
		}
		return grid;
	}
	
	/**
	 * Calculate for every adjacent cube of the minimum cube the dijkstraCost. 
	 * 
	 * @param minimum
	 * 		The minimum cube for which we are going to check the adjacent cubes.
	 * @param grid
	 * 		The grid which contains all the cubes.
	 * @post ...
	 * 		| The dijkstraCost of all the adjacent cubes of the minimum cube are set.
	 */
	private void calculateDijkstraCost(Cube minimum, Cube[][][] grid){
		for (int i=-1; i<2; i++){
			for (int j=-1; j<2; j++){
				for (int k=-1; k<2; k++){
					if (minimum.getX()+i>=0 && minimum.getX()+i<this.getUnit().getWorld().getNbCubesX() 
							&& minimum.getY()+j>=0 && minimum.getY()+j<this.getUnit().getWorld().getNbCubesY() 
							&& minimum.getZ()+k>=0 && minimum.getZ()+k<unit.getWorld().getNbCubesZ()){
						Cube adjacent = grid[minimum.getX()+i][minimum.getY()+j][minimum.getZ()+k];
						int tempdistance = Integer.MAX_VALUE;
						if (this.getUnit().getWorld().isPassable(adjacent.getX(), adjacent.getY(), adjacent.getZ())
								&& !this.getUnit().isFallingPosition(adjacent.getX(), adjacent.getY(), adjacent.getZ())){
							if (((i==-1 || i==1) && j==0 && k==0) || ((j==-1 || j==1) && i==0 && k==0) || ((k==-1 || k==1) && j==0 && i==0)){
								tempdistance = minimum.getDijkstraCost() + 10;
							}
							else if (((i==-1||i==1)&&(j==-1||j==1)&&k==0)||((i==-1||i==1)&&(k==-1||k==1)&&j==0)||((k==-1||k==1)&&(j==-1||j==1)&&i==0)){
								tempdistance = minimum.getDijkstraCost() + 14;								
							}
							else if ((i==-1||i==1)&&(j==-1||j==1)&&(k==-1||k==1)){
								tempdistance = minimum.getDijkstraCost() + 17;
							}
							if (tempdistance < adjacent.getDijkstraCost()){
								adjacent.setDijkstraCost(tempdistance);
								adjacent.setDijkstraParent(minimum);
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Return the unit that wants to find the boulder/log/unit/friend/enemy.
	 */
	@Basic
	public Unit getUnit() {
		return this.unit;
	}
	
	/**
	 * Set the unit to the given unit.
	 * 
	 * @param unit
	 * 		the unit for which the boulder/log/unit/friend/enemy is searched.
	 * @post ...
	 * 		| The unit is set to the given unit.
	 */
	public void setUnit(Unit unit){
		this.unit = unit;
	}
	
	/**
	 * Object of the class unit registering the unit that wants 
	 * to find the boulder/log/unit/friend/enemy.
	 */
	private Unit unit;
}
