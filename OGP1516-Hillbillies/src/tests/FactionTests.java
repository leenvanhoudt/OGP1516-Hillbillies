package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.model.Faction;
import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

public class FactionTests {
	
	@Test
	public void addOrRemoveUnitFaction() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Faction faction = new Faction();
		faction.addUnitToFaction(unit);
		assertTrue("one unit in faction list", faction.getNbUnitsOfFaction()==1);
		faction.removeUnitFromFaction(unit);
		assertTrue("one unit in faction list", faction.getNbUnitsOfFaction()==0);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void moreThan50UnitsInFaction() throws ModelException{
		Faction faction = new Faction();
		for(int i = 0; i<50;i++){
			Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
			faction.addUnitToFaction(unit);
		}
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		faction.addUnitToFaction(unit);
	}
}
