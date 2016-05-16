package hillbillies.model;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.Basic;

public class DijkstraPathFinding {

	public int[] Dijkstra(int value) throws IndexOutOfBoundsException{
		Cube[][][] grid = new Cube[this.getUnit().getWorld().getNbCubesX()][this.getUnit().getWorld().getNbCubesY()][this.getUnit().getWorld().getNbCubesZ()];
		ArrayList<Cube> unchecked = new ArrayList<Cube>();
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
				(value == 2 && this.getUnit().getWorld().cubeContainsUnit(pos[0], pos[1], pos[2]) &&
				this.getUnit().getWorld().CubeContainOtherUnit(pos[0], pos[1], pos[2], this.getUnit())) ||
				(value == 3 && (this.getUnit().getWorld().getCubeType(pos[0], pos[1], pos[2]) == 3))){
				break;
			}			
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
									tempdistance = minimum.getDijkstraCost() + 14;								}
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
		int[] cubePosition = {minimum.getX(),minimum.getY(),minimum.getZ()};
		return cubePosition;

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
