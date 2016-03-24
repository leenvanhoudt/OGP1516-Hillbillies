package hillbillies.model;

import java.util.*;

import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

public class World {
	
	
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener){
		this.dimension = terrainTypes.length;
		this.terrainType = terrainTypes;
		this.modelListener = modelListener;
		ConnectedToBorder connectedToBorder = new ConnectedToBorder(this.getNbCubesX(),this.getNbCubesY(),this.getNbCubesZ());
		for (int x=0;x<this.getNbCubesX();x++){
			for (int y=0;y<this.getNbCubesY();y++){
				for (int z=0;z<this.getNbCubesZ();z++){
					int value = terrainTypes[x][y][z];
					if (value == 0 || value == 3){
						this.temporary = connectedToBorder.changeSolidToPassable(x, y, z);
						this.temporary.clear();
					}
					//if geen terrainType meegegeven voor cube --> default air
				}
			}
		}
		// 3 for lussen
		// eigen methodes om kubussen te getten en setten naar passable in aparte klasse cube
		
		//zet solids van connectedtoborder naar lucht zie terraintype
	}
	
	private List<int[]> temporary;
	private ConnectedToBorder connectedToBorder;
	
	// zelf creeeren wegdoen en in de plaats moeten we dit zelf aanmaken in spawn unit
	private Faction Gryffindor = new Faction();
	private Faction Hufflepuff = new Faction();
	private Faction Ravenclaw = new Faction();
	private Faction Slytherin = new Faction();
	private Faction Death_Eaters = new Faction();
	private Faction[] factions = new Faction[]{Gryffindor,Hufflepuff,Ravenclaw, Slytherin, Death_Eaters};

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
		//cubes die van terrainType zijn veranderd: 
				//		modelListener.notifyTerrainChanged(x, y, z)
				//		--> hoe laat het spel ons weten of een cube veranderd is??
				//			--> heb ik denk ik opgelost (zie lijst cubesChanged)
		
		//GEEN FOR LUS, NOG AANPASSEN
		for (int[] cube : this.getCubesChanged()){
			this.setCubeType(cube[0], cube[1], cube[2], 0);
			modelListener.notifyTerrainChanged(cube[0], cube[1], cube[2]);
			double P = 0.25;
			Random random = new Random();
			if (random.nextInt(100) <= (P*100)){
				//rock
				if (this.getCubeType(cube[0], cube[1], cube[2])==1){
					Boulder boulder = new Boulder();
					boulder.setPosition(cube[0], cube[1], cube[2]);
				}
				//wood
				else if (this.getCubeType(cube[0], cube[1], cube[2])==2){
					Log log = new Log();
					log.setPosition(cube[0], cube[1], cube[2]);
				}
			}
		}
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
		
		// in plaats van zelf factions te maken en naam te geven: if aantal factions (lengte lijst/set) kleiner dan 5:
		// creeer nieuwe faction en voeg toe aan lijst/set. (deze lijst mss dezelfde set als de die met actieve factions?)(als je dit via variabele doet, alle factions zelfde naam in set, probleem?)
		// en zet een manneke in deze lijst
		// als al 5 factions bestaan: kijken of kleiner dan 100 units in totaal en dan unit toevoegen aan kleinste faction
		
		//check minder dan 100 units in totaal
		if (this.getUnits().size() <= 100){
			// aantal units per faction tellen, bij minste bijvoegen -> Math.minimum
			Math.min(Gryffindor.getNbUnitsOfFaction(), Math.min(Hufflepuff.getNbUnitsOfFaction(),
					Math.min(Ravenclaw.getNbUnitsOfFaction(), Math.min(Slytherin.getNbUnitsOfFaction(), 
							Death_Eaters.getNbUnitsOfFaction()))));
			//Arrays.sort(factions, ); lijst sorteren met comparator?
			// unit maken: new Unit (random waarden)
			// unit toevoegen aan faction: faction.addUnit
			// ?? omgekeerd: bij unit ook faction onthouden??
		}
		return null;
	}

	
	public Set<Unit> getUnits() {
		Set<Unit> worldUnits = new HashSet<>();
		Set<Faction> activeFactions = this.getActiveFactions();
		for (Faction f : activeFactions){
			Set<Unit> factionUnits = f.getUnitsOfFaction();
			worldUnits.addAll(factionUnits);
		}
		return worldUnits;
		// for alle factions in activeFactionSet: getUnitsOfFaction(-> set) 
		// en die verschillende sets samenvoegen?
		// -> GEDAAN
	}
	
	public Set<Faction> getActiveFactions(){
		return this.activeFactionSet;
	}
	
	public void setActiveFaction(Faction faction){
		this.activeFactionSet.add(faction);
	}
	
	private Set<Faction> activeFactionSet = new HashSet<Faction>();
	
	//NOG DOEN: (in Unit) als je werkt op een cube en solid cube verdwijnt:
	//	connectedToBorder.changeSolidToPassable(coordinaten cube)
	//	dit geeft een lijst terug van de cubes die niet langer aan de border hangen
	//	deze lijst opslaan: world.addCubesChanged(lijst)
	//		+ tijdens werken cubes bijhouden die air worden en deze ook in de lijst
	//			opslaan
	//  dan in advanceTime (al gedaan):
	//		itereren over deze lijst en aanpassing doen (cubes afbreken +
	//		GUI op de hoogte brengen adhv modelListener)
	public void addCubesChanged(List<int[]> cubes){
		this.cubesChanged.addAll(cubes);
	}
	
	public List<int[]> getCubesChanged(){
		return this.cubesChanged;
	}
	
	private List<int[]> cubesChanged;
	
	private TerrainChangeListener modelListener;
}