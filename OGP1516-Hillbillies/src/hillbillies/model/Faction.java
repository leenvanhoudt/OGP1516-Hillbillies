package hillbillies.model;

import java.util.Set;

public class Faction {

	public Faction(FactionNames factionName){
		this.factionName = factionName;
	}	
	
	private final FactionNames factionName;
	
	public Set<Unit> getUnitsOfFaction(Faction faction){
		return null;
	}
	
	//kan alleen vechten tegen andere faction
	//max 5 factions in wereld
	//max 50 in 1 faction
	//unit bijgevoegd bij kleinste faction
}
