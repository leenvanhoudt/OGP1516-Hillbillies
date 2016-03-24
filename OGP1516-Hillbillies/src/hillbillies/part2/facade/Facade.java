package hillbillies.part2.facade;

import java.util.Set;

import org.junit.internal.Throwables;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade implements IFacade{

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
	
	//The methods advanceTime(Unit, double) and work(Unit) from part 1 are deprecated and no longer need to be implemented.
	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		try{
			unit.advanceTime(dt);		
		}catch(IllegalArgumentException e){
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
		}
	}

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
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}
	//The methods advanceTime(Unit, double) and work(Unit) from part 1 are deprecated and no longer need to be implemented.
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
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		// TODO Auto-generated method stub
		World world = new World(terrainTypes,modelListener);
		return world;
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getNbCubesX();
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getNbCubesY();
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getNbCubesZ();
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		return world.getCubeType(x, y, z);
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		// TODO Auto-generated method stub
		world.setCubeType(x, y, z, value);
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return world.isSolidConnectedToBorder(x, y, z);
		} catch (IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		// TODO Auto-generated method stub
		return world.spawnUnit(false);
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getUnits();
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getFaction();
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		// TODO Auto-generated method stub
		return faction.getUnitsOfFaction();
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getActiveFactions();
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		// TODO Auto-generated method stub
		return boulder.getPosition();
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		// TODO Auto-generated method stub
		return log.getPosition();
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

}
