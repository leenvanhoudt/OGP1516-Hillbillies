package hillbillies.model;

import java.lang.reflect.Array;
import java.util.*;

import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

public class World {
	
	
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener){
		this.dimension = terrainTypes.length;
		this.terrainType = terrainTypes;
		ConnectedToBorder connectedToBorder = new ConnectedToBorder(this.getNbCubesX(),this.getNbCubesY(),this.getNbCubesZ());
		// 3 for lussen
		// eigen methodes om kubussen te getten en setten naar passable in aparte klasse
		//zet solids van connectedtoborder naar lucht zie terraintype
	}
	
	ConnectedToBorder connectedToBorder;
	
	private int dimension;
	private int[][][] terrainType;
	
	public int getNbCubesX(){
		return this.dimension;
	}
	
	public int getNbCubesY(){
		return this.dimension;
	}
	
	public int getNbCubesZ(){
		return this.dimension;
	}
	
	public void advanceTime(double dt){
		//check solid cube niet aan border verbonden -> afbreken binnen max 5sec
	}
	
	public int getCubeType(int x, int y, int z){
		return terrainType[x][y][z];
	}
	
	public static boolean isValidCubeType(int value) {
		return value <= 3 && value >=0;
	}
	
	public void setCubeType(int x, int y, int z, int value) throws IllegalArgumentException{
		if (!isValidCubeType(value))
			throw new IllegalArgumentException();
		this.terrainType[x][y][z] = value;
	}
	
	public boolean isSolidConnectedToBorder(int x, int y, int z) {
		return connectedToBorder.isSolidConnectedToBorder(x,y,z);
	}
	
	public Unit spawnUnit(boolean enableDefaultBehavior){
		//unit direct in faction met kleinste aantal mensen of maakt een nieuwe
		//check minder dan 100 units in totaal
	//	if (this.getUnits().size() <= 100){
		Faction faction = new Faction(FactionNames.DEATH_EATERS);
		this.setActiveFaction(faction);
		System.out.println(this.activeFactionSet.contains(faction));
			ArrayList<FactionNames> allFactions = new ArrayList<FactionNames>(Arrays.asList(FactionNames.values()));
			ArrayList<FactionNames> nonExistingFactions = new ArrayList<> (allFactions.size());
			nonExistingFactions.addAll(allFactions);
			nonExistingFactions.removeAll(this.getActiveFactions());
			System.out.println(nonExistingFactions);
			
			FactionNames factionName = FactionNames.GRYFFINDOR;
			if (this.getActiveFactions().size()<5){
				if (!this.getActiveFactions().contains(factionName)){
					Unit unit = this.addUnitToNonExistingFaction(factionName, enableDefaultBehavior);
					return unit;
				}
				return null;
			}
			
			else{
				return null;
			}
		//	else (faction.getUnitsOfFaction().size() <=50){
			//	min();
		//	}
			
		}


	
	public Unit addUnitToNonExistingFaction(FactionNames factionName, boolean enableDefaultBehavior){
		Faction faction = new Faction(factionName);
		this.setActiveFaction(faction);
		Unit unit = new Unit(factionName.getName(), new int[] { 1, 2, 1 } ,factionName.getWeight(), factionName.getAgility(),
				factionName.getStrength(), factionName.getToughness(), enableDefaultBehavior);
		unit.setFaction(faction);
		//faction.addUnitToFaction(unit);
		return unit;
	}
	
	public Set<Unit> getUnits() {
		return null;
	}
	
	public Set<Faction> getActiveFactions(){
		return this.activeFactionSet;
	}
	
	public void setActiveFaction(Faction faction){
		this.activeFactionSet.add(faction);
	}
	
	private Set<Faction> activeFactionSet = new HashSet<Faction>();
}