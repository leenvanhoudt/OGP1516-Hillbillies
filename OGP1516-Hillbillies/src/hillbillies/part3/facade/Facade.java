package hillbillies.part3.facade;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.scheduler.Scheduler;
import hillbillies.scheduler.Task;
import hillbillies.taskFactory.TaskFactory;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {

	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		try{
			World world = new World(terrainTypes,modelListener);
			return world;
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		return world.getNbCubesX();
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		return world.getNbCubesY();
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		return world.getNbCubesZ();
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		try{
			world.advanceTime(dt);
		}catch(Throwable e){
			throw new ModelException();
		}		
	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		return world.getCubeType(x, y, z);
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		try{
			world.setCubeType(x, y, z, value);
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		return world.isSolidConnectedToBorder(x, y, z);
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		try{
			return world.spawnUnit(enableDefaultBehavior);
		}catch(Throwable e){
			throw new ModelException();
		}
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		try{
			world.addUnit(unit);
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}		
	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		return world.getUnits();
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		return unit.isCarryingLog();
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		return unit.isCarryingBoulder();
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		return unit.isAlive();
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		return unit.getExperiencePoints();
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		unit.workAt(x, y, z);
	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		return unit.getFaction();
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		return faction.getUnitsOfFaction();
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		return world.getActiveFactions();
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		return boulder.getPosition();
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		return world.getBoulders();
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		return log.getPosition();
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		return world.getLogs();
	}

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		try{
			Unit unit = new Unit(name,initialPosition, weight, agility, strength, toughness, enableDefaultBehavior);
			return unit;
		} catch (IllegalArgumentException e){
			throw new ModelException();
		} 
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		return unit.getPosition();
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		return unit.getCubeCoordinate();
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		return unit.getName();
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		try{
			unit.setName(newName);
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		return unit.getWeight();
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		unit.setWeight(newValue);
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		return unit.getStrength();
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		unit.setStrength(newValue);
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		return unit.getAgility();
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		unit.setAgility(newValue);
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		return unit.getToughness();
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		unit.setToughness(newValue);
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		return unit.getMaxHitPoints();
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		return unit.getCurrentHitPoints();
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		return unit.getMaxStaminaPoints();
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		return unit.getCurrentStaminaPoints();
	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		try{
			unit.advanceTime(dt);		
		}catch(Throwable e){
			throw new ModelException();
		}
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		try{
			unit.moveToAdjacent(dx,dy,dz);		
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		return unit.getCurrentSpeed();
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		return unit.isMoving();
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		try{
			unit.startSprinting();		
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		try{
			unit.stopSprinting();		
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		return unit.isSprinting();
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		return unit.getOrientation();
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		try{
			unit.moveTo(cube);		
		}catch(Throwable e){
			throw new ModelException();
		}
	}

	@Override
	public void work(Unit unit) throws ModelException {
		unit.work();
	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		return unit.isWorking();
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		try{
			attacker.fight(defender);		
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}	
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		return unit.isAttacking();
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		unit.rest();
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		return unit.isResting();
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		unit.setDefaultBehaviorEnabled(value);
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		return unit.isDefaultBehaviorEnabled();
	}

	@Override
	public ITaskFactory<?, ?, Task> createTaskFactory() {
		return new TaskFactory();
	}

	@Override
	public boolean isWellFormed(Task task) throws ModelException {
		try{
			return task.isWellFormed();
		} catch (Throwable e){
			throw new ModelException();
		}
	}

	@Override
	public Scheduler getScheduler(Faction faction) throws ModelException {
		return faction.getScheduler();
	}

	@Override
	public void schedule(Scheduler scheduler, Task task) throws ModelException {
		scheduler.schedule(task);
	}

	@Override
	public void replace(Scheduler scheduler, Task original, Task replacement) throws ModelException {
		scheduler.replace(original, replacement);
	}

	@Override
	public boolean areTasksPartOf(Scheduler scheduler, Collection<Task> tasks) throws ModelException {
		return scheduler.areTasksPartOf(tasks);
	}

	@Override
	public Iterator<Task> getAllTasksIterator(Scheduler scheduler) throws ModelException {
		return scheduler.getAllTasksIterator();
	}

	@Override
	public Set<Scheduler> getSchedulersForTask(Task task) throws ModelException {
		return task.getSchedulersForTask();
	}

	@Override
	public Unit getAssignedUnit(Task task) throws ModelException {
		return task.getAssignedUnit();
	}

	@Override
	public Task getAssignedTask(Unit unit) throws ModelException {
		return unit.getAssignedTask();
	}

	@Override
	public String getName(Task task) throws ModelException {
		return task.getName();
	}

	@Override
	public int getPriority(Task task) throws ModelException {
		return task.getPriority();
	}

}
