package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.CubeType;
import hillbillies.model.DijkstraPathFinding;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class DijkstraTests {

	/**
	 * Method to create cube types for a world with a flat rock surface.
	 */
	public int[][][] cubeTypesFlatSurface(){
		int[][][] types = new int[10][10][5];
		for (int i=0; i<10; i++)
			for (int j=0; j<10; j++)
				types[i][j][1] = CubeType.ROCK.getCubeType();
		return types;
	}
	
	@Test
	public void testDijkstraFindBoulder() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Boulder firstBoulder = new Boulder();
		Boulder secondBoulder = new Boulder();
		world.addBoulder(firstBoulder);
		world.addBoulder(secondBoulder);
		firstBoulder.setPosition(0, 0, 2);
		secondBoulder.setPosition(3, 3, 2);
		Unit unit = new Unit("TestUnit", new int[] { 4, 3, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		DijkstraPathFinding dijkstra = new DijkstraPathFinding();
		dijkstra.setUnit(unit);
		int[] position  = dijkstra.Dijkstra(0);
		assertTrue("found closest boulder", position[0]==3 && position[1]==3);
	}
	
	@Test
	public void testDijkstraFindLog() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Log firstLog = new Log();
		Log secondLog = new Log();
		world.addLog(firstLog);
		world.addLog(secondLog);
		firstLog.setPosition(1, 2, 2);
		secondLog.setPosition(3, 3, 2);
		Unit unit = new Unit("TestUnit", new int[] { 3, 3, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		DijkstraPathFinding dijkstra = new DijkstraPathFinding();
		dijkstra.setUnit(unit);
		int[] position  = dijkstra.Dijkstra(1);
		assertTrue("found closest log", position[0]==3 && position[1]==3);
	}
	
	@Test
	public void testDijkstraFindWorkshop() {
		int[][][] types = this.cubeTypesFlatSurface();
		types[1][1][2] = CubeType.WORKSHOP.getCubeType();
		types[5][4][2] = CubeType.WORKSHOP.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 3, 3, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		DijkstraPathFinding dijkstra = new DijkstraPathFinding();
		dijkstra.setUnit(unit);
		int[] position  = dijkstra.Dijkstra(2);
		assertTrue("found closest workshop", position[0]==5 && position[1]==4);
	}
	
	@Test
	public void testDijkstraFindOtherUnit() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit firstUnit = new Unit("TestUnit", new int[] { 3, 3, 2 }, 50, 50, 50, 50, false);
		Unit secondUnit = new Unit("TestUnit", new int[] { 0, 0, 2 }, 50, 50, 50, 50, false);
		world.addUnit(firstUnit);
		world.addUnit(secondUnit);
		DijkstraPathFinding dijkstra = new DijkstraPathFinding();
		dijkstra.setUnit(firstUnit);
		int[] position  = dijkstra.Dijkstra(3);
		assertTrue("found closest other unit", position[0]==0 && position[1]==0);
	}
	
	@Test
	public void testDijkstraFindFriend() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 3, 3, 2 }, 50, 50, 50, 50, false);
		Unit enemyUnit = new Unit("TestUnit", new int[] { 0, 0, 2 }, 50, 50, 50, 50, false);
		Unit friendUnit = new Unit("TestUnit", new int[] { 0, 0, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		for (int i=0; i<7; i++)
			world.spawnUnit(false);
		world.addUnit(enemyUnit);
		world.addUnit(friendUnit);
		DijkstraPathFinding dijkstra = new DijkstraPathFinding();
		dijkstra.setUnit(unit);
		int[] position  = dijkstra.Dijkstra(4);
		assertTrue("found closest friend", position[0]==0 && position[1]==0);
	}
	
	@Test
	public void testDijkstraFindEnemy() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 3, 3, 2 }, 50, 50, 50, 50, false);
		Unit enemyUnit = new Unit("TestUnit", new int[] { 3, 3, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(enemyUnit);
		DijkstraPathFinding dijkstra = new DijkstraPathFinding();
		dijkstra.setUnit(unit);
		int[] position  = dijkstra.Dijkstra(5);
		assertTrue("found closest enemy", position[0]==3 && position[1]==3);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void testDijkstraNoUnitFound() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit firstUnit = new Unit("TestUnit", new int[] { 3, 3, 2 }, 50, 50, 50, 50, false);
		world.addUnit(firstUnit);
		DijkstraPathFinding dijkstra = new DijkstraPathFinding();
		dijkstra.setUnit(firstUnit);
		dijkstra.Dijkstra(3);
	}

}
