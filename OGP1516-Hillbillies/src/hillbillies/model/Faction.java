package hillbillies.model;

import java.util.*;

public class Faction {

	
	public Faction() {
	}
	
	public static final int MAX_NUMBER_UNITS_IN_FACTION = 50;

	private Set<Unit> unitsInFaction = new HashSet<Unit>();
	
	public void addUnitToFaction(Unit unit) throws IllegalArgumentException{
		if (this.getUnitsOfFaction().size()>= MAX_NUMBER_UNITS_IN_FACTION)
			throw new IllegalArgumentException();
		this.unitsInFaction.add(unit);
	}
	
	public void removeUnitFromFaction(Unit unit){
		this.unitsInFaction.remove(unit);
	}
	
	public Set<Unit> getUnitsOfFaction() {
		return this.unitsInFaction;
	}
	
	public int getNbUnitsOfFaction(){
		return (this.getUnitsOfFaction()).size();
	}

}
