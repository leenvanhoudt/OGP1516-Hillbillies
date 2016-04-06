package hillbillies.model;

import java.util.ArrayList;
import java.util.Collections;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class PathFinding {

	public ArrayList<Cube> findPath(int[] cubeEndPosition) throws IllegalArgumentException,
			IndexOutOfBoundsException{
		Cube[][][] grid = this.makeGrid(cubeEndPosition);
		ArrayList<Cube> open = new ArrayList<Cube>();
		ArrayList<Cube> closed = new ArrayList<Cube>();
		Cube startCube = grid[unit.getCubeCoordinate()[0]][unit.getCubeCoordinate()[1]][unit.getCubeCoordinate()[2]];
		open.add(startCube);
		Cube current = startCube;
		Cube minimum = startCube;
		
		while (!(current.getX() == cubeEndPosition[0])
				|| !(current.getY() == cubeEndPosition[1])
				|| !(current.getZ() == cubeEndPosition[2])){
			if (open.isEmpty()){
				unit.setCurrentSpeed(0);
				unit.defaultBehaviorCase3 = false;
				unit.isMovingTo = false;
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
	
	private Cube[][][] makeGrid(int[] cubeEndPosition){
		Cube[][][] grid = new Cube[unit.getWorld().getNbCubesX()][unit.getWorld().getNbCubesY()][unit.getWorld().getNbCubesZ()];
		for (int i=0; i<unit.getWorld().getNbCubesX(); i++){
			for (int j=0; j<unit.getWorld().getNbCubesY(); j++){
				for (int k=0; k<unit.getWorld().getNbCubesZ(); k++){
					grid[i][j][k] = new Cube(i,j,k);
					grid[i][j][k].setHCost(cubeEndPosition);
				}
			}
		}
		return grid;
	}
	
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
	
	private void neighbouringCubes(Cube current, Cube[][][] grid,ArrayList<Cube> open, ArrayList<Cube> closed ){
		for (int i=-1; i<2; i++){
			for (int j=-1; j<2; j++){
				for (int k=-1; k<2; k++){
					if (current.getX()+i>=0 && current.getX()+i<unit.getWorld().getNbCubesX() 
							&& current.getY()+j>=0 && current.getY()+j<unit.getWorld().getNbCubesY() 
							&& current.getZ()+k>=0 && current.getZ()+k<unit.getWorld().getNbCubesZ()){
						Cube adjacent = grid[current.getX()+i][current.getY()+j][current.getZ()+k];
						if (unit.getWorld().isPassable(adjacent.getX(), adjacent.getY(), adjacent.getZ())
								&& !unit.isFallingPosition(adjacent.getX(), adjacent.getY(), adjacent.getZ())){
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
	
	private void updateCost(Cube current, Cube adjacent, int cost,ArrayList<Cube> open, ArrayList<Cube> closed){
		int finalCost = adjacent.getHCost()+cost;
		if ((unit.getWorld().isPassable(adjacent.getX(),adjacent.getY(),adjacent.getZ()) && !closed.contains(adjacent))
				&& (finalCost < adjacent.getFCost() || !open.contains(adjacent))){
			adjacent.setFCost(finalCost);
			adjacent.setGCost(cost);
			adjacent.setParent(current);
			if (!open.contains(adjacent)){
				open.add(adjacent);
			}
		}
	}
	
	private ArrayList<Cube> makePath(Cube current, Cube startCube){
		ArrayList<Cube> path = new ArrayList<Cube>();
		while (current.getParent()!=startCube){
			path.add(current);
			current = current.getParent();
		}
		path.add(current);
		path.add(startCube);
		Collections.reverse(path);
		return path;
	}
	
	
	@Basic
	@Raw
	public Unit getUnit() {
		return this.unit;
	}
	
	public void setUnit(Unit unit){
		this.unit = unit;
	}
	
	private Unit unit;
	
}
