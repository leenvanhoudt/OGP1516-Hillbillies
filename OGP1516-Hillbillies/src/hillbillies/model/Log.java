package hillbillies.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class Log {
	
	public Log(){
		this.setLogWeight();
	}
	
	public void advanceTime(double dt){
		if (!this.getWorld().isValidStandingPosition((int)Math.floor(this.startFallingPosition[0]), 
				(int)Math.floor(this.startFallingPosition[1]), (int)Math.floor(this.startFallingPosition[2]))){
			this.falling(dt);
		}
	}

	public int getLogWeight() {
		return this.logWeight;
	}
	
	private final void setLogWeight(){
		Random random = new Random();
		this.logWeight = random.nextInt(41)+10;
	}
	
	private int logWeight;
	
	public double[] getPosition(){
		return this.logPosition;
	}
	
	public void setPosition(double x, double y, double z){
		this.logPosition = new double[]{x,y,z};
		if (!this.isFalling){
			this.startFallingPosition = new double[]{this.getPosition()[0],this.getPosition()[1],this.getPosition()[2]};
			this.nextPosition = new double[]{this.getPosition()[0],this.getPosition()[1],this.getPosition()[2]-1};
		}
	}
	
	private double[] startFallingPosition;
	private double[] nextPosition;
	private double[] logPosition;
	
	/**
	 * Return the world of this log.
	 */
	@Basic
	@Raw
	public World getWorld() {
		return this.world;
	}
	
	public void setWorld(World world){
		this.world = world;
	}
	
	private World world;
	
	private void falling(double dt){
		this.isFalling = true;
		double[] v = new double[]{0,0,-3};
		double[] fuzzyPositionUnder = new double[] { 0, 0, 0 };
		double[] fuzzyPositionUpper = new double[] { 0, 0, 0 };
		double[] distance = new double[] { 0, 0, 0 };
		for (int i = 0; i < v.length; i++) {
			distance[i] = Math.abs(v[i] * dt);
			fuzzyPositionUnder[i] = this.nextPosition[i] - distance[i];
			fuzzyPositionUpper[i] = this.nextPosition[i] + distance[i];
		}
		if (this.getPosition()[0] < fuzzyPositionUnder[0] || this.getPosition()[0] > fuzzyPositionUpper[0]
				|| this.getPosition()[1] < fuzzyPositionUnder[1] || this.getPosition()[1] > fuzzyPositionUpper[1]
				|| this.getPosition()[2] < fuzzyPositionUnder[2] || this.getPosition()[2] > fuzzyPositionUpper[2]) {
			double[] newPosition = new double[] { this.getPosition()[0] + v[0] * dt, this.getPosition()[1] + v[1] * dt,
					this.getPosition()[2] + v[2] * dt };
			if (isValidPosition(newPosition)&&this.getWorld().isPassable((int)Math.floor(newPosition[0]), (int)Math.floor(newPosition[1]), (int)Math.floor(newPosition[2])))
				this.setPosition(newPosition[0],newPosition[1],newPosition[2]);
		} else {
			this.isFalling = false;
			this.setPosition(this.nextPosition[0],this.nextPosition[1],this.nextPosition[2]);
		}
	}
	
	
	private boolean isFalling = false;
	
	/**
	 * Check whether the given position is a valid position for any unit.
	 * 
	 * @param position
	 *            The position to check.
	 * @return ... | result is true if the position has 3 coordinates, which are
	 *         equal and larger than 0 and smaller than 50.
	 */
	public boolean isValidPosition(double[] position) {
		if (position.length == 3 && 
				this.getWorld().isPassable((int) Math.floor(position[0]), (int) Math.floor(position[1]),
						(int) Math.floor(position[2]))) {
			if ((position[0] >= this.getWorld().getNbCubesX() || position[0] < 0)
					|| (position[1] >= this.getWorld().getNbCubesY() || position[1] < 0)
					|| (position[2] >= this.getWorld().getNbCubesZ() || position[2] < 0)){
				return false;
			}
			return true;
		}
		return false;
	}

}
