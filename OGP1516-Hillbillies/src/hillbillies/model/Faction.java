package hillbillies.model;

import java.util.*;

public class Faction {

	
	public Faction() {
	}

	private Set<Unit> unitsInFaction = new HashSet<Unit>();
	
	public void addUnitToFaction(Unit unit){
		//TODO als er al 50 zitten in set (lengte set), exception gooien? wordt wel in unit bij setfaction gecheckt
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
