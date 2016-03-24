package hillbillies.model;

import java.util.*;

public class Faction {

	
	public Faction() {
	}

	private Set<Unit> unitsInFaction = new HashSet<Unit>();
	
	public void addUnitToFaction(Unit unit){
		// als er al 50 zitten in set (lengte set), exception gooien?
		this.unitsInFaction.add(unit);
	}
	
	public void removeUnitToFaction(Unit unit){
		this.unitsInFaction.remove(unit);
	}
	
	public Set<Unit> getUnitsOfFaction() {
		return this.unitsInFaction;
	}
	
	public int getNbUnitsOfFaction(){
		return (this.getUnitsOfFaction()).size();
	}

	// kan alleen vechten tegen andere faction
	// max 5 factions in wereld
	// max 50 in 1 faction
	// unit bijgevoegd bij kleinste faction
}
