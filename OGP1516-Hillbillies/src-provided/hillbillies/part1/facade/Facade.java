package hillbillies.part1.facade;

import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

public class Facade implements IFacade{
	
/*
 *•Methods in this interface are only allowed to throw exceptions of type
 * ogp.framework.util.ModelException (this class is provided). No other exception types are allowed. 
 * This exception can only be thrown if (1) calling a method of your Unit class with the given
 * parameters would violate a precondition, or (2) if the method of your Unit class throws 
 * an exception (if so, wrap the exception in a ModelException).
 *	•ModelException should not be used anywhere outside of your Facade implementation.
 */
	
	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		// TODO Auto-generated method stub
		try{
			Unit unit = new Unit(name,initialPosition, weight, agility, strength, toughness, enableDefaultBehavior);
			return unit;
		} catch (IllegalArgumentException e){
			throw new ModelException();
		}
		
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getPosition();
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getCubeCoordinate();
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getName();
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setName(newName);
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getWeight();
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setWeight(newValue);
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getStrength();			
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setStrength(newValue);	
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getAgility();
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setAgility(newValue);
		}catch (IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getToughness();			
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setToughness(newValue);	
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getMaxHitPoints();			
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getCurrentHitPoints();	
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getMaxStaminaPoints();	
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getCurrentStaminaPoints();	
	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.advanceTime(dt);		
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.moveToAdjacent(dx, dy, dz);	
		} catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getCurrentSpeed();
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.isMoving();
		} catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.startSprinting();	
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.stopSprinting();
		} catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.isSprinting();
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getOrientation();	
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.moveTo(cube);
		} catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public void work(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.work();	
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.isWorking();	
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		// TODO Auto-generated method stub
		try{
			attacker.fight(defender);
		} catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.isAttacking();	
		} catch (IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.rest();	
		}catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.isResting();	
		} catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setDefaultBehaviorEnabled(value);	
		} catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.isDefaultBehaviorEnabled();	
		} catch(IllegalArgumentException e){
			throw new ModelException();
		}
	}

	
}


      