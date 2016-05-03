package hillbillies.model;

import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class that can be used to see what result a completed work order will have.
 * 
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 */
public class ResultWork {

	/**
	 * Execute the result that comes from a unit working on the cube on the given 
	 * position. The result is determined by checking which conditions the cube satisfies.
	 * 
	 * @param x
	 * 		The x coordinate of the cube that was worked on.
	 * @param y
	 * 		The y coordinate of the cube that was worked on.
	 * @param z
	 * 		The z coordinate of the cube that was worked on.
	 * @effect ...
	 * 		| If the unit is carrying a boulder or log, dropLogOrBoulder is called.
	 * 		| Else if the cube that was worked on has cube type workshop and contains
	 * 		| a boulder and a log, improveEquipment is called.
	 * 		| Else if the cube that was worked on contains a boulder or log,
	 * 		| pickUpBoulder or pickUpLog is called.
	 * 		| Else if the cube that was worked on has cube type wood or rock,
	 * 		| workOnWood or workOnRock is called.
	 * @effect ...
	 * 		| The units experience points increase by 10.
	 */

	public void resultWorkAt(int x, int y, int z) throws IllegalArgumentException{
		if ((unit.isCarryingLog() || unit.isCarryingBoulder()) && unit.getWorld().isPassable(x, y, z)){
			this.dropLogOrBoulder(x, y, z);
		}
		else if (unit.getWorld().getCubeType(x, y, z)==3
				&& !unit.getWorld().getBoulders().isEmpty() && unit.getWorld().cubeContainsBoulder(x, y, z)
				&& !unit.getWorld().getLogs().isEmpty() && unit.getWorld().cubeContainsLog(x, y, z)){
			this.improveEquipment(x, y, z);
		}
		else if (!unit.getWorld().getBoulders().isEmpty() && unit.getWorld().cubeContainsBoulder(x, y, z)){
			this.pickUpBoulder(x, y, z);
		}
		else if (!unit.getWorld().getLogs().isEmpty() && unit.getWorld().cubeContainsLog(x, y, z)){
			this.pickUpLog(x, y, z);
		}
		else if (unit.getWorld().getCubeType(x, y, z)==2){
			this.workOnWood(x, y, z);
		}
		else if (unit.getWorld().getCubeType(x, y, z)==1){
			this.workOnRock(x, y, z);
		}
		unit.setExperiencePoints(unit.getExperiencePoints()+10);
	}
	
	/**
	 * Drop the boulder or log the unit is carrying at the given position.
	 * 
	 * @param x
	 * 		The x coordinate of the cube that was worked on.
	 * @param y
	 * 		The y coordinate of the cube that was worked on.
	 * @param z
	 * 		The z coordinate of the cube that was worked on.
	 * @effect...
	 * 		| If the unit is carrying a log, it will stop carrying this log and drop it
	 * 		| at the given position. The units weight will be reduced by the weight of
	 * 		| the log and the log will be added to the world.
	 * @effect...
	 * 		| If the unit is carrying a boulder, it will stop carrying this boulder and drop it
	 * 		| at the given position. The units weight will be reduced by the weight of
	 * 		| the boulder and the boulder will be added to the world.
	 */
	private void dropLogOrBoulder(int x,int y,int z){
		if (unit.isCarryingLog()){
			unit.isCarryingLog = false;
			unit.log.setPosition(x+Unit.LC/2,y+Unit.LC/2,z+Unit.LC/2);
			unit.setWeight(unit.getWeight()-unit.log.getCarriedItemWeight());
			unit.getWorld().addLog(unit.log);
		} else{
			unit.isCarryingBoulder = false;
			unit.boulder.setPosition(x+Unit.LC/2,y+Unit.LC/2,z+Unit.LC/2);
			unit.setWeight(unit.getWeight()-unit.boulder.getCarriedItemWeight());
			unit.getWorld().addBoulder(unit.boulder);
		}
	}
	
	/**
	 * Improve the equipment of the unit, consuming one boulder and one log and
	 * increasing the units toughness and weight by one point.
	 * 
	 * @param x
	 * 		The x coordinate of the cube that was worked on.
	 * @param y
	 * 		The y coordinate of the cube that was worked on.
	 * @param z
	 * 		The z coordinate of the cube that was worked on.
	 * @effect ...
	 * 		| The units weight and toughness are increased by one point.
	 * @effect ...
	 * 		| The boulder and log are removed from the world.
	 */
	private void improveEquipment(int x, int y, int z) throws IllegalArgumentException{
		unit.setWeight(unit.getWeight()+1);
		unit.setToughness(unit.getToughness()+1);
		unit.getWorld().removeLog(unit.getWorld().getCubeLog(x, y, z));
		unit.getWorld().removeBoulder(unit.getWorld().getCubeBoulder(x, y, z));
	}
	
	/**
	 * Pick up the boulder located at the given position.
	 * 
	 * @param x
	 * 		The x coordinate of the cube that was worked on.
	 * @param y
	 * 		The y coordinate of the cube that was worked on.
	 * @param z
	 * 		The z coordinate of the cube that was worked on.
	 * @post ...
	 * 		| The units weight is increased by the weight of the boulder.
	 * @effect ...
	 * 		| The unit is carrying the boulder and the boulder is removed from the world.
	 */
	private void pickUpBoulder(int x, int y, int z)throws IllegalArgumentException{
		unit.boulder = unit.getWorld().getCubeBoulder(x, y, z);
		unit.boulder.setWorld(unit.getWorld());
		unit.weight = (unit.getWeight() + unit.boulder.getCarriedItemWeight());
		unit.getWorld().removeBoulder(unit.boulder);
		unit.isCarryingBoulder = true;
	}
	
	/**
	 * Pick up the log located at the given position.
	 * 
	 * @param x
	 * 		The x coordinate of the cube that was worked on.
	 * @param y
	 * 		The y coordinate of the cube that was worked on.
	 * @param z
	 * 		The z coordinate of the cube that was worked on.
	 * @post ...
	 * 		| The units weight is increased by the weight of the log.
	 * @effect ...
	 * 		| The unit is carrying the log and the log is removed from the world.
	 */
	private void pickUpLog(int x, int y, int z) throws IllegalArgumentException{
		unit.log = unit.getWorld().getCubeLog(x, y, z);
		unit.log.setWorld(unit.getWorld());
		unit.weight = (unit.getWeight() + unit.log.getCarriedItemWeight());
		unit.getWorld().removeLog(unit.log);
		unit.isCarryingLog = true;
	}
	
	/**
	 * The wooden cube at the given position changes into air and leaves a log at 
	 * the same position.
	 * 
	 * @param x
	 * 		The x coordinate of the cube that was worked on.
	 * @param y
	 * 		The y coordinate of the cube that was worked on.
	 * @param z
	 * 		The z coordinate of the cube that was worked on.
	 * @effect ...
	 * 		| The cube type changes to air.
	 * @effect ...
	 * 		| A new log is created and placed into the world at the given position.
	 */
	private void workOnWood(int x, int y, int z) throws IllegalArgumentException{
		List<int[]> changedCubes = unit.getWorld().connectedToBorder.changeSolidToPassable(x,y,z);
		if (!changedCubes.isEmpty())
			unit.getWorld().addCubesChanged(changedCubes);
		unit.getWorld().setCubeType(x, y, z, 0);
		Log newlog = new Log();
		newlog.setWorld(unit.getWorld());
		newlog.setPosition(x+Unit.LC/2,y+Unit.LC/2,z+Unit.LC/2);
		unit.getWorld().addLog(newlog);
		unit.getWorld().modelListener.notifyTerrainChanged(x, y, z);
	}
	
	/**
	 * The rock cube at the given position changes into air and leaves a boulder at 
	 * the same position.
	 * 
	 * @param x
	 * 		The x coordinate of the cube that was worked on.
	 * @param y
	 * 		The y coordinate of the cube that was worked on.
	 * @param z
	 * 		The z coordinate of the cube that was worked on.
	 * @effect ...
	 * 		| The cube type changes to air.
	 * @effect ...
	 * 		| A new boulder is created and placed into the world at the given position.
	 */
	private void workOnRock(int x, int y, int z)throws IllegalArgumentException{
		List<int[]> changedCubes = unit.getWorld().connectedToBorder.changeSolidToPassable(x,y,z);
		if (!changedCubes.isEmpty())
			unit.getWorld().addCubesChanged(changedCubes);
		unit.getWorld().setCubeType(x, y, z, 0);
		Boulder newboulder = new Boulder();
		newboulder.setWorld(unit.getWorld());
		newboulder.setPosition(x+Unit.LC/2,y+Unit.LC/2,z+Unit.LC/2);
		unit.getWorld().addBoulder(newboulder);
		unit.getWorld().modelListener.notifyTerrainChanged(x, y, z);
	}
	
	/**
	 * Return the unit that has worked.
	 */
	@Basic
	public Unit getUnit() {
		return this.unit;
	}
	
	/**
	 * Set the unit to the given unit.
	 * @param
	 * 		The unit that has worked.
	 * @post ...
	 * 		| The unit is set to the given unit.
	 */
	public void setUnit(Unit unit){
		this.unit = unit;
	}
	
	/**
	 * Object of the class unit registering the unit that has worked.
	 */
	private Unit unit;
}
