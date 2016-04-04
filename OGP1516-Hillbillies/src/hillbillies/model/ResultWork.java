package hillbillies.model;

import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public class ResultWork {

	public void resultWorkAt(int x, int y, int z) throws IllegalArgumentException{
		if ((unit.isCarryingLog() || unit.isCarryingBoulder()) && unit.getWorld().isPassable(x, y, z)){
			this.dropLogOrBoulder(x, y, z);
		}
		else if (unit.getWorld().getCubeType(x, y, z)==3
				&& !unit.getWorld().getBoulders().isEmpty() && unit.getWorld().cubeContainsBoulder(x, y, z)
				&& !unit.getWorld().getLogs().isEmpty() && unit.getWorld().cubeContainsLog(x, y, z)){
			this.improveEquipment(x, y, z);
		}
		else if (!unit.getWorld().getBoulders().isEmpty() && unit.getWorld().cubeContainsBoulder(x, y, z)){
			this.pickUpBoulder(x, y, z);
		}
		else if (!unit.getWorld().getLogs().isEmpty() && unit.getWorld().cubeContainsLog(x, y, z)){
			this.pickUpLog(x, y, z);
		}
		else if (unit.getWorld().getCubeType(x, y, z)==2){
			this.workOnWood(x, y, z);
		}
		else if (unit.getWorld().getCubeType(x, y, z)==1){
			this.workOnRock(x, y, z);
		}
		unit.setExperiencePoints(unit.getExperiencePoints()+10);
	}
	
	private void dropLogOrBoulder(int x,int y,int z){
		if (unit.isCarryingLog()){
			unit.isCarryingLog = false;
			unit.log.setPosition(x+Unit.LC/2,y+Unit.LC/2,z+Unit.LC/2);
			unit.setWeight(unit.getWeight()-unit.log.getLogWeight());
			unit.getWorld().addLog(unit.log);
		} else{
			unit.isCarryingBoulder = false;
			unit.boulder.setPosition(x+Unit.LC/2,y+Unit.LC/2,z+Unit.LC/2);
			unit.setWeight(unit.getWeight()-unit.boulder.getBoulderWeight());
			unit.getWorld().addBoulder(unit.boulder);
		}
	}
	
	private void improveEquipment(int x, int y, int z){
		unit.setWeight(unit.getWeight()+1);
		unit.setToughness(unit.getToughness()+1);
		unit.getWorld().removeLog(unit.getWorld().getCubeLog(x, y, z));
		unit.getWorld().removeBoulder(unit.getWorld().getCubeBoulder(x, y, z));
	}
	
	private void pickUpBoulder(int x, int y, int z){
		unit.boulder = unit.getWorld().getCubeBoulder(x, y, z);
		unit.boulder.setWorld(unit.getWorld());
		unit.weight = (unit.getWeight() + unit.boulder.getBoulderWeight());
		unit.getWorld().removeBoulder(unit.boulder);
		unit.isCarryingBoulder = true;
	}
	
	private void pickUpLog(int x, int y, int z){
		unit.log = unit.getWorld().getCubeLog(x, y, z);
		unit.log.setWorld(unit.getWorld());
		unit.weight = (unit.getWeight() + unit.log.getLogWeight());
		unit.getWorld().removeLog(unit.log);
		unit.isCarryingLog = true;
	}
	
	private void workOnWood(int x, int y, int z){
		List<int[]> changedCubes = unit.getWorld().connectedToBorder.changeSolidToPassable(x,y,z);
		if (!changedCubes.isEmpty())
			unit.getWorld().addCubesChanged(changedCubes);
		unit.getWorld().setCubeType(x, y, z, 0);
		Log newlog = new Log();
		newlog.setWorld(unit.getWorld());
		newlog.setPosition(x+Unit.LC/2,y+Unit.LC/2,z+Unit.LC/2);
		unit.getWorld().addLog(newlog);
		unit.getWorld().modelListener.notifyTerrainChanged(x, y, z);
	}
	
	private void workOnRock(int x, int y, int z){
		List<int[]> changedCubes = unit.getWorld().connectedToBorder.changeSolidToPassable(x,y,z);
		if (!changedCubes.isEmpty())
			unit.getWorld().addCubesChanged(changedCubes);
		unit.getWorld().setCubeType(x, y, z, 0);
		Boulder newboulder = new Boulder();
		newboulder.setWorld(unit.getWorld());
		newboulder.setPosition(x+Unit.LC/2,y+Unit.LC/2,z+Unit.LC/2);
		unit.getWorld().addBoulder(newboulder);
		unit.getWorld().modelListener.notifyTerrainChanged(x, y, z);
	}
	
	@Basic
	@Raw
	public Unit getUnit() {
		return this.unit;
	}
	
	public void setUnit(Unit unit){
		this.unit = unit;
	}
	
	private Unit unit;
}
