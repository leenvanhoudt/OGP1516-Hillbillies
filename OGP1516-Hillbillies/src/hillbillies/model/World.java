package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.scheduler.Scheduler;
import hillbillies.util.ConnectedToBorder;

/**
 * A class of the World where the Hillbillies can execute their activities and be divided in factions and
 * where boulders and logs can be added.
 * 
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 * 
 * @invar The cubeType of each cube must be a valid cubeType for any cube. 
 * 		| isValidCubeType(getCubeType())
 */
public class World {
	
	/**
	 * Create a new world with the given dimensions and terrainTypes.
	 * 
	 * @param terrainTypes
	 * 		The type of cube: air, rock, wood, workshop.
	 * @param modelListener
	 * 		The listener for updating the world GUI.
	 * @post ...
	 * 		| The class connectedToBorder is initialized as a world, with the given dimensions,
	 * 		| full of solid blocks.
	 * @effect ...
	 * 		| All cubes within the dimension of the world that are air or workshop are changed from
	 * 		| solid to passable.
	 * @effect ...
	 * 		| All cubes are checked if they are connected to border. If not, they are added to the list
	 * 		| of cubes that have to cave in.
	 * @throws IllegalArgumentException
	 * 		| If the cubeType doesn't have a valid value.
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener)throws IllegalArgumentException{
		this.dimensionX = terrainTypes.length;
		this.dimensionY = terrainTypes[0].length;
		this.dimensionZ = terrainTypes[0][0].length;
		this.terrainType = terrainTypes;
		this.modelListener = modelListener;
		this.connectedToBorder = new ConnectedToBorder(this.getNbCubesX(),this.getNbCubesY(),this.getNbCubesZ());
		for (int x=0;x<this.getNbCubesX();x++){
			for (int y=0;y<this.getNbCubesY();y++){
				for (int z=0;z<this.getNbCubesZ();z++){
					int value = terrainTypes[x][y][z];
					if(!isValidCubeType(value))
						throw new IllegalArgumentException();
					if (value == 0 || value == 3){
						this.temporary = this.connectedToBorder.changeSolidToPassable(x, y, z);
						if (!this.temporary.isEmpty())
							this.addCubesChanged(this.temporary);
						this.temporary.clear();
					}
				}
			}
		}
	}
	
	/**
	 * List for temporarily saving the returned list of this.connectedToBorder.changeSolidToPassable(x,y,z).
	 */
	private List<int[]> temporary = new ArrayList<int[]>();
	
	/**
	 * Initialize an object of the class ConnectedToBorder.
	 */
	public ConnectedToBorder connectedToBorder;
	
	/**
	 * Initialize the TerrainChangeListener.
	 */
	public TerrainChangeListener modelListener;

	/**
	 * Variables registering the dimensions of the world.
	 */
	private int dimensionX;
	private int dimensionY;
	private int dimensionZ;
	
	/**
	 * Variable registering the terrainType of each cube of the world.
	 */
	private int[][][] terrainType;
	
	/**
	 * Return the number of X cubes of this world.
	 */
	@Basic
	public int getNbCubesX(){
		return this.dimensionX;
	}
	
	/**
	 * Return the number of Y cubes of this world.
	 */
	@Basic
	public int getNbCubesY(){
		return this.dimensionY;
	}
	
	/**
	 * Return the number of Z cubes of this world.
	 */
	@Basic
	public int getNbCubesZ(){
		return this.dimensionZ;
	}
	
	/**
	 * Return the cubeType of the cube with coordinates x,y,z.
	 */
	@Basic
	@Raw
	public int getCubeType(int x, int y, int z){
		return this.terrainType[x][y][z];
	}
	
	/**
	 * Check whether the given value is a valid cubeType for any cube in the world: 
	 * air,rock,wood or workshop, which are represented by the values 0,1,2 and 3.
	 * 
	 * @param value
	 * 			The cubeType to check.
	 * @return ...
	 * 		| Return true if the value is between 0 and 3.
	 */
	public static boolean isValidCubeType(int value) {
		return value <= 3 && value >=0;
	}
	
	/**
	 * Set the cube at position (x,y,z) to the given value.
	 * 
	 * @param x
	 * 		the x coordinate of the cube.
	 * @param y
	 * 		the y coordinate of the cube.
	 * @param z
	 * 		the z coordinate of the cube.
	 * @param value
	 * 		the new cubeType for this cube.
	 * @throws IllegalArgumentException
	 * 		The given cubeType is not a valid cubeType for any cube. 
	 */
	@Raw
	public void setCubeType(int x, int y, int z, int value) throws IllegalArgumentException{
		if (!isValidCubeType(value))
			throw new IllegalArgumentException();
		this.terrainType[x][y][z] = value;
	}

	/**
	 * Check if the cube is passable. A cube is passable if its type is air or workshop.
	 * 
	 * @param x
	 * 		the x coordinate of the cube
	 * @param y
	 * 		the y coordinate of the cube
	 * @param z
	 * 		the z coordinate of the cube
	 * @return ...
	 * 		| Return true if a cube is passable.
	 */
	public boolean isPassable(int x, int y, int z){
		return (this.getCubeType(x, y, z)==0 
				|| this.getCubeType(x, y, z)==3);
	}
	
	/**
	 * Check whether the given duration is a valid duration for any world. It is
	 * valid when it doesn't exceed the boundaries (0 and MAX_DURATION(=0.2)).
	 * 
	 * @param duration
	 *            The duration to check.
	 * @return ...
	 * 		| Return true if the duration is valid.
	 */
	public boolean isValidDuration(double dt) {
		return dt >= 0 && dt < MAX_DURATION;
	}

	/**
	 * Constant value limiting the duration.
	 */
	public static final double MAX_DURATION = 0.2;
	
	/**
	 * Execute the advanceTime of unit for each unit in the world. 
	 * Execute the advanceTime of boulder for each boulder in the world.
	 * Execute the advanceTime of log for each log in the world.
	 * If there are cubes in the list of cubes which have to be changed, they will cave in.
	 * 
	 * @param dt
	 * 		The time between each update of the world.
	 * @throws IllegalArgumentException
	 * 		The given duration is not a valid duration for any unit.
	 */
	public void advanceTime(double dt) throws IllegalArgumentException, IndexOutOfBoundsException{
		if (isValidDuration(dt)) {
			for (Unit unit: this.getUnits()){
				unit.advanceTime(dt);				
			}
			for (Boulder boulder: this.getBoulders()){
				boulder.advanceTime(dt);
			}
			for(Log log: this.getLogs()){
				log.advanceTime(dt);
			}
			if (!this.getCubesChanged().isEmpty()){
				this.updateCubes();
			}
		}//else if (dt >= MAX_DURATION) {
			//throw new IllegalArgumentException();
		//}
	}
	
	/**
	 * Check if a cube is still connected to a border via direct adjacent cubes.
	 * 
	 * @param x
	 * 		The x coordinate of the cube.
	 * @param y
	 * 		The y coordinate of the cube.
	 * @param z
	 * 		The z coordinate of the cube.
	 * @return ...
	 * 		| Return true if the cube is connected to the border via direct adjacent cubes.
	 */
	public boolean isSolidConnectedToBorder(int x, int y, int z) {
		return this.connectedToBorder.isSolidConnectedToBorder(x,y,z);
	}
	
	/**
	 * Constant values limiting the maximum number of units and factions in a world.
	 */
	private static final int MAX_NB_UNITS_WORLD = 100;
	private static final int MAX_NB_FACTIONS = 5;
	
	/**
	 * Spawn a unit with random characteristics at a random standing place in the world. The unit
	 * It will be added to the faction with the smallest number of units.
	 * 
	 * @param enableDefaultBehavior
	 * 		Check if default behaviour is enabled.
	 * @return ...
	 * 		| Return the new created unit.
	 * @throws IllegalArgumentException ...
	 * 		| If there are already 100 units in the world.
	 */
	public Unit spawnUnit(boolean enableDefaultBehavior){
		if (this.getUnits().size() < MAX_NB_UNITS_WORLD){
			if (this.getActiveFactions().size()<MAX_NB_FACTIONS){
				Faction newFaction = new Faction();
				Scheduler newScheduler = new Scheduler();
				this.activeFactionSet.add(newFaction);
				this.activeFactionList.add(newFaction);
				newFaction.setScheduler(newScheduler);
			}
			this.sort();
			int[] validPosition = this.searchValidPosition();
			Random random = new Random();
			int characteristics = Unit.MIN_INITIAL_VALUE + 
					random.nextInt(Unit.MAX_INITIAL_VALUE - Unit.MIN_INITIAL_VALUE+1);
			Unit newUnit = new Unit(this.generateName(),validPosition, characteristics, characteristics,
					characteristics,characteristics, enableDefaultBehavior);
			this.activeFactionList.get(0).addUnitToFaction(newUnit);
			newUnit.setFaction(this.activeFactionList.get(0));
			newUnit.setWorld(this);
			return newUnit;
		}
		return null;
	}
	
	/**
	 * Search a valid standing position.
	 * 
	 * @return
	 * 		| Return the standing position that was found.
	 */
	private int[] searchValidPosition() {
		Random random = new Random();
		int x = random.nextInt(this.getNbCubesX());
		int y = random.nextInt(this.getNbCubesY());
		int z = random.nextInt(this.getNbCubesZ());
		while (!this.isValidStandingPosition(x,y,z)){
			if (x < this.getNbCubesX()-1)
				x++;
			else if(y < this.getNbCubesY()-1){
				x = 0;
				y++;
			}
			else if (z< this.getNbCubesZ()-1){
				x = 0;
				y = 0;
				z++;	
			}
			else{
				x = 0;
				y = 0;
				z = 0;
			}
		}
		return new int[] {x,y,z};
	}
	
	/**
	 * Sort the list of factions by number of units in a faction: from least to most.
	 * 
	 * @post
	 * 		| The list is sorted.
	 */
	private void sort(){
		Collections.sort(this.activeFactionList, new Comparator<Faction>() {
	        @Override
	        public int compare(Faction faction1, Faction faction2)
	        {
	            if(faction2.getNbUnitsOfFaction() < faction1.getNbUnitsOfFaction())
	            	return 1;
	            else if(faction2.getNbUnitsOfFaction() > faction1.getNbUnitsOfFaction())
	            	return -1;
	            else{
	            	return 0;
	            }
	        }
	    });
	}
	
	/**
	 * Generate a random name, meeting the conditions of a valid name.
	 * 
	 * @return
	 * 		| Return the generated name.
	 */
	private String generateName(){
		Random random = new Random();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String name = new String();
		name = name.concat(Character.toString(alphabet.charAt(random.nextInt(26))).toUpperCase());
		for (int i=0; i<2+random.nextInt(10);i++){
			name = name.concat(Character.toString(alphabet.charAt(random.nextInt(26))));
		}
		return name;
	}
	
	/**
	 * Add an already created unit to the world to the faction with the smallest number of units.
	 * 
	 * @param unit
	 * 		The unit which has to be added to the world.
	 * @post ...
	 * 		| The unit is added to the world.
	 */
	public void addUnit(Unit unit){
		if (this.getUnits().size() < MAX_NB_UNITS_WORLD){
			if (this.getActiveFactions().size()<MAX_NB_FACTIONS){
				Faction newFaction = new Faction();
				Scheduler newScheduler = new Scheduler();
				this.activeFactionSet.add(newFaction);
				this.activeFactionList.add(newFaction);
				newFaction.setScheduler(newScheduler);
			}
			this.sort();
			this.activeFactionList.get(0).addUnitToFaction(unit);
			unit.setFaction(this.activeFactionList.get(0));
			unit.setWorld(this);
		}
	}
	
	/**
	 * Check if the cube is a valid standing place: there is an inpassable cube 
	 * underneath the cube or the cube is located at the bottom of the world and 
	 * the cube itself must be passable.
	 * 
	 * @param x
	 * 		The x coordinate of the cube.
	 * @param y
	 * 		The y coordinate of the cube.
	 * @param z
	 * 		The z coordinate of the cube.
	 * @return ...
	 * 		| Return true if it is a valid standing position.
	 */
	public boolean isValidStandingPosition(int x,int y,int z){
		return ((z == 0 || this.getCubeType(x, y, z-1) == 1 || this.getCubeType(x, y, z-1) == 2) 
				&& (this.getCubeType(x, y, z) == 0 || this.getCubeType(x, y, z) == 3));
	}
	
	/**
	 * Return a set of active factions in the world.
	 */
	public Set<Faction> getActiveFactions(){
		return this.activeFactionSet;
	}
	
	/**
	 * Set and ArrayList registering the active factions of a world.
	 */
	private Set<Faction> activeFactionSet = new HashSet<Faction>();
	private ArrayList<Faction> activeFactionList = new ArrayList<Faction>();
	
	/**
	 * Return a set of all units in the world.
	 */
	public Set<Unit> getUnits() {
		Set<Unit> worldUnits = new HashSet<>();
		if (!this.getActiveFactions().isEmpty()){
			for (Faction f : this.getActiveFactions()){
				worldUnits.addAll(f.getUnitsOfFaction());
			}
		}
		return worldUnits;
	}
	
	/**
	 * Add a list of new cubes which have to cave in to the list of cubesChanged.
	 * @param cubes
	 * 		list of cubes that have to cave in.
	 * @effect ...
	 * 		| The cubes are added to the list cubesChanged.
	 */
	public void addCubesChanged(List<int[]> cubes){
		this.cubesChanged.addAll(cubes);
	}
	
	/**
	 * Return a List with the cubes that have to cave in.
	 */
	@Basic
	public List<int[]> getCubesChanged(){
		return this.cubesChanged;
	}
	
	/**
	 * List registering the cubes that have to cave in.
	 */
	private List<int[]> cubesChanged = new ArrayList<int[]>();
	
	/**
	 * Update the cube type of a cube that have to cave in from inpassable to air.
	 * If the cube was wood or rock, it has 25% change that a log or a boulder is dropped at that place after
	 * that the cube caved in.
	 * 
	 * @effect ...
	 * 		| The cubes of the list cubesChanged are caved in and sometimes there is a boulder or a log 
	 * 		| dropped at that place.
	 */
	private void updateCubes()throws IllegalArgumentException{
		for (int[] cube : this.getCubesChanged()){
			double P = 0.25;
			Random random = new Random();
			if (random.nextInt(100) <= (P*100)){
				//rock
				if (this.getCubeType(cube[0], cube[1], cube[2])==1){
					Boulder boulder = new Boulder();
					boulder.setWorld(this);
					boulder.setPosition(cube[0]+Unit.LC/2, cube[1]+Unit.LC/2, cube[2]+Unit.LC/2);
					this.addBoulder(boulder);
				}
				//wood
				else if (this.getCubeType(cube[0], cube[1], cube[2])==2){
					Log log = new Log();
					log.setWorld(this);
					log.setPosition(cube[0]+Unit.LC/2, cube[1]+Unit.LC/2, cube[2]+Unit.LC/2);
					this.addLog(log);
				}
			}
			this.setCubeType(cube[0], cube[1], cube[2], 0);
			this.modelListener.notifyTerrainChanged(cube[0], cube[1], cube[2]);
		}
	}
	
	/**
	 * Return a set of all logs in the world.
	 */
	@Basic
	public Set<Log> getLogs(){
		return this.logsSet;
	}
	
	/**
	 * Add a log to the set of logs of the world.
	 * 
	 * @param log
	 * 		The log that has to be added to the world.
	 * @post ...
	 * 		| The log is added to the world.
	 */
	public void addLog(Log log){
		this.logsSet.add(log);
	}
	
	/**
	 * Remove a log from the set of logs of the world.
	 * 
	 * @param log
	 * 		The log that has to be removed from the world.
	 * @post ...
	 * 		| The log is removed from the world.
	 */
	public void removeLog(Log log){
		this.logsSet.remove(log);
	}
	
	/**
	 * Return a set of all boulders in the world.
	 */
	@Basic
	public Set<Boulder> getBoulders(){
		return this.bouldersSet;
	}
	
	/**
	 * Add a boulder to the set of boulders of the world.
	 * 
	 * @param boulder
	 * 		The boulder that has to be added to the world.
	 * @post ...
	 * 		| The boulder is added to the world.
	 */
	public void addBoulder(Boulder boulder){
		this.bouldersSet.add(boulder);
	}
	
	/**
	 * Remove a boulder from the set of boulders of the world.
	 * 
	 * @param boulder
	 * 		The boulder that has to be removed from the world.
	 * @post ...
	 * 		| The boulder is removed from the world.
	 */
	public void removeBoulder(Boulder boulder){
		this.bouldersSet.remove(boulder);
	}
	
	/**
	 * Sets registering the logs and boulders of the world.
	 */
	private Set<Log> logsSet = new HashSet<Log>();
	private Set<Boulder> bouldersSet = new HashSet<Boulder>();
	
	/**
	 * Check if a cube contains a boulder.
	 * 
	 * @param x
	 * 		the x coordinate of the cube.
	 * @param y
	 * 		the y coordinate of the cube.
	 * @param z
	 * 		the z coordinate of the cube.
	 * @return ...
	 * 		| Return true if a cube contains a boulder.
	 */
	public boolean cubeContainsBoulder(int x, int y, int z){
		for (Boulder boulder:this.getBoulders()){
			if ((int)Math.floor(boulder.getPosition()[0])==x && (int)Math.floor(boulder.getPosition()[1])==y
					&& (int)Math.floor(boulder.getPosition()[2])==z){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if a cube contains a log.
	 * 
	 * @param x
	 * 		the x coordinate of the cube.
	 * @param y
	 * 		the y coordinate of the cube.
	 * @param z
	 * 		the z coordinate of the cube.
	 * @return ...
	 * 		| Return true if a cube contains a log.
	 */
	public boolean cubeContainsLog(int x, int y, int z){
		for (Log log:this.getLogs()){
			if ((int)Math.floor(log.getPosition()[0])==x && (int)Math.floor(log.getPosition()[1])==y
					&& (int)Math.floor(log.getPosition()[2])==z){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if a cube contains a unit.
	 * 
	 * @param x
	 * 		the x coordinate of the cube.
	 * @param y
	 * 		the y coordinate of the cube.
	 * @param z
	 * 		the z coordinate of the cube.
	 * @return ...
	 * 		| Return true if a cube contains a unit.
	 */
	public boolean cubeContainsUnit(int x, int y, int z){
		for (Unit unit:this.getUnits()){
			if (unit.getCubeCoordinate()[0]==x && unit.getCubeCoordinate()[1]==y
					&& unit.getCubeCoordinate()[2]==z){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return the boulder located at the given cube.
	 * 
	 * @param x
	 * 		the x coordinate of a cube.
	 * @param y
	 * 		the y coordinate of a cube.
	 * @param z
	 * 		the z coordinate of a cube.
	 * @return ...
	 * 		| Return the boulder located at that cube.
	 * @throws IllegalArgumentException ...
	 * 		| If there is no boulder located at that cube.
	 */
	public Boulder getCubeBoulder(int x, int y, int z)throws IllegalArgumentException{
		for (Boulder boulder:this.getBoulders()){
			if ((int)Math.floor(boulder.getPosition()[0])==x && (int)Math.floor(boulder.getPosition()[1])==y
					&& (int)Math.floor(boulder.getPosition()[2])==z){
				return boulder;
			}
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * Return the log located at the given cube.
	 * 
	 * @param x
	 * 		the x coordinate of a cube.
	 * @param y
	 * 		the y coordinate of a cube.
	 * @param z
	 * 		the z coordinate of a cube.
	 * @return ...
	 * 		| Return the log located at that cube.
	 * @throws IllegalArgumentException ...
	 * 		| If there is no log located at that cube.
	 */
	public Log getCubeLog(int x, int y, int z) throws IllegalArgumentException{
		for (Log log:this.getLogs()){
			if ((int)Math.floor(log.getPosition()[0])==x && (int)Math.floor(log.getPosition()[1])==y
					&& (int)Math.floor(log.getPosition()[2])==z){
				return log;
			}
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * Return the unit located at the given cube. The unit may not be equal to the given unit
	 * that is also standing on that cube.
	 * 
	 * @param x
	 * 		the x coordinate of a cube.
	 * @param y
	 * 		the y coordinate of a cube.
	 * @param z
	 * 		the z coordinate of a cube.
	 * @param givenUnit
	 * 		the unit already standing on the cube.
	 * @return ...
	 * 		| Return the other unit located at that cube.
	 * @throws IllegalArgumentException ...
	 * 		| If there is no other unit located at that cube.
	 */
	public Boolean CubeContainOtherUnit(int x, int y, int z, Unit givenUnit){
		for (Unit unit:this.getUnits()){
			if (unit.getCubeCoordinate()[0]==x && unit.getCubeCoordinate()[1]==y
					&& unit.getCubeCoordinate()[2]==z && unit != givenUnit){
				return true;
			}
		}
		return false;
	}
	
	public Unit getCubeOtherUnit(int x, int y, int z, Unit givenUnit){
		for (Unit unit:this.getUnits()){
			if (unit.getCubeCoordinate()[0]==x && unit.getCubeCoordinate()[1]==y
					&& unit.getCubeCoordinate()[2]==z && unit != givenUnit){
				return unit;
			}
		}
		return null;
	}
	
	
}