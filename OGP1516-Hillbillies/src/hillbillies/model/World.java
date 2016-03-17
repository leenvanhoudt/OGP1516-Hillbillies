package hillbillies.model;

import java.util.Collections;

import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

public class World {
	
	ConnectedToBorder connectedToBorder;
	
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener){
		this.dimension = terrainTypes.length;
		this.terrainType = terrainTypes;
		//zet solids van connectedtoborder naar lucht zie terraintype
	}
	
	private int dimension;
	private int[][][] terrainType;
	
	public int getNbCubesX(){
		return this.dimension;
	}
	
	public int getNbCubesY(){
		return this.dimension;
	}
	
	public int getNbCubesZ(){
		return this.dimension;
	}
	
	public void advanceTime(double dt){
		//check solid cube niet aan border verbonden -> afbreken binnen max 5sec
	}
	
	public int getCubeType(int x, int y, int z){
		return terrainType[x][y][z];
	}
	
	public static boolean isValidCubeType(int value) {
		return value <= 3 && value >=0;
	}
	
	public void setCubeType(int x, int y, int z, int value) throws IllegalArgumentException{
		if (!isValidCubeType(value))
			throw new IllegalArgumentException();
		this.terrainType[x][y][z] = value;
	}
	
	public boolean isSolidConnectedToBorder(int x, int y, int z) {
		return connectedToBorder.isSolidConnectedToBorder(x,y,z);
	}
	
	public Unit spawnUnit(boolean enableDefaultBehavior){
		//unit direct in faction met kleinste aantal mensen of maakt een nieuwe
		//check minder dan 100 units in totaal
		return null;
	}
}