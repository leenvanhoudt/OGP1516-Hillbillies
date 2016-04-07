package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.Basic;
/**
 * A class of factions and their units.
 * 
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 *
 */
public class Faction {

	/**
	 * Constant value registering the maximum amount of units allowed in one faction.
	 */
	public static final int MAX_NUMBER_UNITS_IN_FACTION = 50;

	/**
	 * Set registering all the units in this faction.
	 */
	private Set<Unit> unitsInFaction = new HashSet<Unit>();
	
	/**
	 * Add the given unit to this faction.
	 * 
	 * @param unit
	 * 		The unit to add to the faction.
	 *  @effect ...
	 * 		| The unit is added to the faction set.
	 * @throws IllegalArgumentException
	 * 		| The number of units in this faction has already reached the maximum amount.
	 */
	public void addUnitToFaction(Unit unit) throws IllegalArgumentException{
		if (this.getUnitsOfFaction().size()>= MAX_NUMBER_UNITS_IN_FACTION)
			throw new IllegalArgumentException();
		this.unitsInFaction.add(unit);
	}
	
	/**
	 * Remove the given unit from this faction.
	 * 
	 * @param unit
	 * 		The unit to remove from the faction.
	 * @effect ...
	 * 		| The given unit is removed from the faction set.
	 */
	public void removeUnitFromFaction(Unit unit){
		this.unitsInFaction.remove(unit);
	}
	
	/**
	 * Return the set of all units in this faction.
	 */
	@Basic
	public Set<Unit> getUnitsOfFaction() {
		return this.unitsInFaction;
	}
	
	/**
	 * Return the number of units in this faction.
	 */
	@Basic
	public int getNbUnitsOfFaction(){
		return (this.getUnitsOfFaction()).size();
	}

}
