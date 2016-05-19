package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.CubeType;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class resultWorkTests {
	
	/**
	 * Helper method to advance time for the given world by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(World world, double time, double step) {
		int n = (int) (time / step);
		for (int i = 0; i < n+1; i++)
			world.advanceTime(step);
		world.advanceTime(time - n * step);
	}
	
	@Test
	public void testDropBoulder() {
		int[][][] types = new int[4][4][4];
		types[1][2][0] = CubeType.ROCK.getCubeType();
		types[2][2][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 2, 2, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		Boulder boulder = new Boulder();
		world.addBoulder(boulder);
		boulder.setWorld(world);
		boulder.setPosition(1, 2, 1);
		unit.workAt(1, 2, 1);
		advanceTimeFor(world,100,0.1);
		unit.workAt(1, 2, 1);
		advanceTimeFor(world,100,0.1);
		assertTrue("boulder was dropped",world.cubeContainsBoulder(1, 2, 1));
	}

	@Test
	public void testDropLog() {
		int[][][] types = new int[4][4][4];
		types[1][2][0] = CubeType.ROCK.getCubeType();
		types[2][2][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 2, 2, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		Log log = new Log();
		world.addLog(log);
		log.setWorld(world);
		log.setPosition(1, 2, 1);
		unit.workAt(1, 2, 1);
		advanceTimeFor(world,100,0.1);
		unit.workAt(1, 2, 1);
		advanceTimeFor(world,100,0.1);
		assertTrue("log was dropped",world.cubeContainsLog(1, 2, 1));
	}
	
	@Test
	public void testPickUpBoulder(){
		int[][][] types = new int[4][4][4];
		types[1][2][0] = CubeType.ROCK.getCubeType();
		types[2][2][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 2, 2, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		Boulder boulder = new Boulder();
		world.addBoulder(boulder);
		boulder.setWorld(world);
		boulder.setPosition(1, 2, 1);
		unit.workAt(1, 2, 1);
		advanceTimeFor(world,100,0.1);
		assertTrue("unit is carrying boulder", unit.isCarryingBoulder());
	}
	
	@Test
	public void testPickUpLog(){
		int[][][] types = new int[4][4][4];
		types[1][2][0] = CubeType.ROCK.getCubeType();
		types[2][2][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 2, 2, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		Log log = new Log();
		world.addLog(log);
		log.setWorld(world);
		log.setPosition(1, 2, 1);
		unit.workAt(1, 2, 1);
		advanceTimeFor(world,100,0.1);
		assertTrue("unit is carrying log", unit.isCarryingLog());
	}
	
	@Test
	public void testMakeBoulderFromRock(){
		int[][][] types = new int[4][4][4];
		types[1][2][1] = CubeType.ROCK.getCubeType();
		types[1][2][0] = CubeType.ROCK.getCubeType();
		types[2][2][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 2, 2, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.workAt(1, 2, 1);
		advanceTimeFor(world,100,0.1);
		assertTrue("cube contains boulder",world.cubeContainsBoulder(1, 2, 1));
	}
	
	@Test
	public void testMakeLogFromWood() {
		int[][][] types = new int[4][4][4];
		types[1][2][1] = CubeType.TREE.getCubeType();
		types[1][2][0] = CubeType.ROCK.getCubeType();
		types[2][2][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 2, 2, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.workAt(1, 2, 1);
		advanceTimeFor(world,100,0.1);
		assertTrue("unit is carrying log", world.cubeContainsLog(1, 2, 1));
	}
	
	@Test
	public void testImproveEquipment(){
		int[][][] types = new int[4][4][4];
		types[1][2][1] = CubeType.WORKSHOP.getCubeType();
		types[1][2][0] = CubeType.ROCK.getCubeType();
		types[2][2][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 2, 2, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		int weight = unit.getWeight();
		int toughness = unit.getToughness();
		Log log = new Log();
		world.addLog(log);
		log.setWorld(world);
		log.setPosition(1, 2, 1);
		Boulder boulder = new Boulder();
		world.addBoulder(boulder);
		boulder.setWorld(world);
		boulder.setPosition(1, 2, 1);
		unit.workAt(1, 2, 1);
		advanceTimeFor(world,10,0.1);
		assertTrue("weight increased",unit.getWeight()-weight == 1);
		assertTrue("toughness increased",unit.getToughness()-toughness >= 1);
		assertFalse("log disappeared",world.cubeContainsLog(1, 2, 1));
		assertFalse("boulder disappeared",world.cubeContainsBoulder(1, 2, 1));
	}
}
