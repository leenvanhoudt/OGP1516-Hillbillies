package hillbillies.model;

import java.util.*;

public class Faction {

	
	public Faction() {
	}

	private Set<Unit> unitsInFaction = new HashSet<Unit>();
	
	public void addUnitToFaction(Unit unit) throws IllegalArgumentException{
		if (this.getUnitsOfFaction().size()>=50)
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
