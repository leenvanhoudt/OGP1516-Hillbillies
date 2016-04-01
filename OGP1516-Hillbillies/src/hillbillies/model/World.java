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
	
	public void advanceTime(double dt) throws IllegalArgumentException{
		for (Unit unit: this.getUnits()){
			unit.advanceTime(dt);
		}
	/*	//check solid cube niet aan border verbonden -> afbreken binnen max 5sec
		//cubes die van terrainType zijn veranderd: 
				//		modelListener.notifyTerrainChanged(x, y, z)
				//		--> hoe laat het spel ons weten of een cube veranderd is??
				//			--> heb ik denk ik opgelost (zie lijst cubesChanged)
		
		//GEEN FOR LUS, NOG AANPASSEN
		for (int[] cube : this.getCubesChanged()){
			double P = 0.25;
			Random random = new Random();
			if (random.nextInt(100) <= (P*100)){
				//rock
				if (this.getCubeType(cube[0], cube[1], cube[2])==1){
					Boulder boulder = new Boulder();
					boulder.setPosition(cube[0], cube[1], cube[2]);
					this.bouldersSet.add(boulder);
				}
				//wood
				else if (this.getCubeType(cube[0], cube[1], cube[2])==2){
					Log log = new Log();
					log.setPosition(cube[0], cube[1], cube[2]);
					this.logsSet.add(log);
				}
			}
			this.setCubeType(cube[0], cube[1], cube[2], 0);
			modelListener.notifyTerrainChanged(cube[0], cube[1], cube[2]);
		}*/
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
	
	public boolean isPassable(int x, int y, int z){
		return (this.terrainType[x][y][z]==0 
				|| this.terrainType[x][y][z]==3);
	}
	
	public boolean isSolidConnectedToBorder(int x, int y, int z) {
		return connectedToBorder.isSolidConnectedToBorder(x,y,z);
	}
	
	public Unit spawnUnit(boolean enableDefaultBehavior){
		// lijst maken van actieve factions om daar bewerkingen op uit te voeren
		if (this.getUnits().size() < 100){
			if (this.activeFactionSet.size()<5){
				Faction newFaction = new Faction();
				this.activeFactionSet.add(newFaction);
				this.activeFactionList.add(newFaction);
			}			
			this.sort();
			int[] validPosition = this.searchValidPosition();
			Random random = new Random();
			int characteristics = 25 + random.nextInt(76);
			Unit newUnit = new Unit("Jan",validPosition, characteristics, characteristics,characteristics,characteristics, enableDefaultBehavior);
			this.activeFactionList.get(0).addUnitToFaction(newUnit);
			newUnit.setFaction(this.activeFactionList.get(0));
			newUnit.setWorld(this);
			return newUnit;
		}
		// vreemde unit zonder faction en niet tot world behorend maken of null returnen?
		System.out.println("null");
		return null;
	}
	
	public int[] searchValidPosition() {
		Random random = new Random();
		//zoek valid positie
		int x = random.nextInt(this.getNbCubesX());
		int y = random.nextInt(this.getNbCubesY());
		int z = random.nextInt(this.getNbCubesZ());
		//zoek betere oplossing
		while (!this.isValidStandingPosition(x,y,z)){
			if (x < this.getNbCubesX()-1)
				x++;
			else if(y < this.getNbCubesY()-1){
				x = 0;
				y++;
			}
			else if (z< this.getNbCubesZ()-1){
				x = 0;
				y = 0;
				z++;	
			}
			else{
				x = 0;
				y = 0;
				z = 0;
			}
		}
		return new int[] {x,y,z};
	}
	
	public void sort(){
		Collections.sort(this.activeFactionList, new Comparator<Faction>() {
	        @Override
	        public int compare(Faction faction1, Faction faction2)
	        {
	            if(faction2.getNbUnitsOfFaction() < faction1.getNbUnitsOfFaction())
	            	return 1;
	            else if(faction2.getNbUnitsOfFaction() > faction1.getNbUnitsOfFaction())
	            	return -1;
	            else{
	            	return 0;
	            }
	        }
	    });
	}

	public void addUnit(Unit unit){
		this.sort();
		this.activeFactionList.get(0).addUnitToFaction(unit);
		unit.setFaction(this.activeFactionList.get(0));
		unit.setWorld(this);
	}
	
	public boolean isValidStandingPosition(int x,int y,int z){
		return ((z == 0 || this.terrainType[x][y][z-1] == 1 || this.terrainType[x][y][z-1] == 2) 
				&& (this.terrainType[x][y][z] == 0 || this.terrainType[x][y][z] == 3));
	}
	
	public Set<Unit> getUnits() {
		Set<Unit> worldUnits = new HashSet<>();
		for (Faction f : this.activeFactionSet){
			worldUnits.addAll(f.getUnitsOfFaction());
		}
		return worldUnits;
		// for alle factions in activeFactionSet: getUnitsOfFaction(-> set) 
		// en die verschillende sets samenvoegen?
		// -> GEDAAN
	}
	
	public Set<Faction> getActiveFactions(){
		return this.activeFactionSet;
	}
	
	private Set<Faction> activeFactionSet = new HashSet<Faction>();
	private ArrayList<Faction> activeFactionList = new ArrayList<Faction>();
	
	
	
	
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
	
	public Set<Log> getLogs(){
		return this.logsSet;
	}
	
	public Set<Boulder> getBoulders(){
		return this.bouldersSet;
	}
	
	private Set<Log> logsSet;
	private Set<Boulder> bouldersSet;
}