package hillbillies.model;

public class Cube {
	
	public Cube(int[] position, int terrainType){
		this.terrainType = terrainType;
	}
	
	public int getCubeType(){
		return this.terrainType;
	}
	
	public static boolean isValidCubeType(int value) {
		return value <= 3 && value >=0;
	}
	
	public void setCubeType(int value) throws IllegalArgumentException{
		if (!isValidCubeType(value))
			throw new IllegalArgumentException();
		this.terrainType = value;
	}
	
	private int terrainType;
}
