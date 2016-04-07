package hillbillies.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of logs with their weight and position. A log can fall and be picked up.
 *
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 * 
 * @invar The position of each log must be a valid position for any log.
 *      | isValidPosition(getPosition())
 */
public class Log {
	
	/**
	 * Creating a log with random weight.
	 * 
	 * @effect ...
	 * 		| The weight of the log is set.
	 */
	public Log(){
		this.setLogWeight();
	}

	/**
	 * Return the weight of this log.
	 */
	@Basic
	public int getLogWeight() {
		return this.logWeight;
	}
	
	/**
	 * Set the log weight to a random number between 10 and 50.
	 * 
	 * @post ...
	 * 		| logWeight is set to a random int between 10 and 50 inclusively.
	 */
	private final void setLogWeight(){
		Random random = new Random();
		this.logWeight = random.nextInt(41)+10;
	}
	
	/**
	 * Variable registering the weight of this log.
	 */
	private int logWeight;
	
	/**
	 * Return the position of this log.
	 */
	@Basic
	@Raw
	public double[] getPosition(){
		return this.logPosition;
	}
	
	/**
	 * Check whether the given position is a valid position for any unit.
	 * 
	 * @param position
	 *            The position to check.
	 * @return ... 
	 * 		| Return true if the position has 3 coordinates, which are
	 *         equal and larger than 0 and smaller than 50.
	 */
	private boolean isValidPosition(double[] position) {
		if (position.length != 3 
				|| (position[0] >= this.getWorld().getNbCubesX() || position[0] < 0)
				|| (position[1] >= this.getWorld().getNbCubesY() || position[1] < 0)
				|| (position[2] >= this.getWorld().getNbCubesZ() || position[2] < 0)
				|| (!this.getWorld().isPassable((int) Math.floor(position[0]), 
						(int) Math.floor(position[1]),(int) Math.floor(position[2])))) {
			return false;
			}
		return true;
	}
	
	/**
	 * Set the position of this log to the given position.
	 * Set the nextPosition to the position below the given position.
	 * This is the position the log would fall to if it wasn't solid.
	 * 
	 * @param x
	 * 		The x coordinate of the new position.
	 * @param y
	 * 		The y coordinate of the new position.
	 * @param z
	 * 		The z coordinate of the new position.
	 * @post ...
	 * 		| logPosition is set to the given position.
	 * 		| If the log isn't falling, startFallingPosition is set to the given
	 * 		| position and nextPosition is set to the position below the given position.
	 */
	@Raw
	public void setPosition(double x, double y, double z){
		this.logPosition = new double[]{x,y,z};
		if (!this.isFalling){
			this.startFallingPosition = new double[]{this.getPosition()[0],this.getPosition()[1],this.getPosition()[2]};
			this.nextPosition = new double[]{this.getPosition()[0],this.getPosition()[1],this.getPosition()[2]-1};
		}
	}
	
	/**
	 * Variables registering the startFallingPosition, nexPosition and boulderPosition
	 * of the log.
	 */
	private double[] startFallingPosition;
	private double[] nextPosition;
	private double[] logPosition;
	
	/**
	 * Update the game time of the log. If the log is placed above a passable
	 * cube, it will start falling.
	 * 
	 * @param dt
	 * 		The time between each update of the log. 
	 * @effect ...
	 * 		| If the position of the log is not a valid standing position, it will
	 * 		| start falling.
	 */
	public void advanceTime(double dt){
		if (!this.getWorld().isValidStandingPosition((int)Math.floor(this.startFallingPosition[0]), 
				(int)Math.floor(this.startFallingPosition[1]), (int)Math.floor(this.startFallingPosition[2]))){
			this.falling(dt);
		}
	}
	
	/**
	 * Make the log fall. If the log has reached a position above an inpassable
	 * cube, it will stop falling. Otherwise, it will keep falling until it reaches
	 * such position.
	 * 
	 * @param dt
	 * 		The time between each update of the log.
	 * @effect ...
	 * 		| If the log has reached a valid standing position, it will stop falling.
	 * 		| If the logs position still isn't a valid standing position, it will
	 * 		| keep falling.
	 */
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
	
	/**
	 * Boolean registering if the log is falling.
	 */
	private boolean isFalling = false;
	
	
	/**
	 * Return the world of this log.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Set the world of this log to the given world.
	 * 
	 * @param world
	 * 		The world in which the log is placed.
	 * @effect ...
	 * 		| The world of the log is set to the given world.
	 */
	public void setWorld(World world){
		this.world = world;
	}
	
	/**
	 * Initialise an object of the class world in which the log is placed.
	 */
	private World world;
}
