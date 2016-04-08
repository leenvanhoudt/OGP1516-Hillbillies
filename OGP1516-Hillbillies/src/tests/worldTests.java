package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.ModelException;

public class worldTests {
	
	/**
	 * Helper method to advance time for the given world by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(World world, double time, double step) throws ModelException {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			world.advanceTime(step);
		world.advanceTime(time - n * step);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testValidDurationAdvanceTime() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[1][2][2] = 1;
		types[2][2][3] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		world.advanceTime(0.5);
	}
	
	@Test
	public void testIsPassable() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[1][2][2] = 0;
		World world = new World(types, new DefaultTerrainChangeListener());
		assertTrue("cube is passable",world.isPassable(1, 2, 2));
	}
	
	@Test
	public void testSpawnUnit() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[1][2][2] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		world.spawnUnit(false);
		assertTrue("one unit in world", world.getUnits().size()==1);
		assertTrue("one unit in faction", world.getActiveFactions().size()==1);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSpawnUnitOver100() throws ModelException{
		int[][][] types = new int[102][1][2];
		for (int i=0;i<101;i++)
			types[i][0][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		for (int i=0;i<100;i++)
			world.spawnUnit(false);
		world.spawnUnit(false);
	}
	
	@Test
	public void testAddUnit() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[1][2][2] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		assertTrue("one unit in world", world.getUnits().size()==1);
		assertTrue("one unit in faction", world.getActiveFactions().size()==1);
	}
	
	@Test
	public void testIsValidStandingPosition() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[1][2][2] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		assertTrue("valid standing position", world.isValidStandingPosition(1, 2, 3));
	}
	
	@Test
	public void testMaxAmountOfFactions() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[1][2][2] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		for (int i = 0; i<6; i++)
			world.spawnUnit(false);
		assertTrue("5 factions", world.getActiveFactions().size()==5);
	}
	
	@Test
	public void testAddCubesChanged() throws ModelException{
		int[][][] types = new int[4][4][4];
		World world = new World(types, new DefaultTerrainChangeListener());
		ArrayList<int[]> cubes = new ArrayList<>();
		cubes.add(new int[]{1,1,1});
		world.addCubesChanged(cubes);
		assertTrue("1 cube changed", world.getCubesChanged().size()==1);
	}
	
	@Test
	public void testAddLog() throws ModelException{
		int[][][] types = new int[4][4][4];
		World world = new World(types, new DefaultTerrainChangeListener());
		Log log = new Log();
		world.addLog(log);
		assertTrue("1 log in world", world.getLogs().size()==1);
		world.removeLog(log);
		assertTrue("no logs in world", world.getLogs().size()==0);
	}
	
	@Test
	public void testAddBoulder() throws ModelException{
		int[][][] types = new int[4][4][4];
		World world = new World(types, new DefaultTerrainChangeListener());
		Boulder boulder = new Boulder();
		world.addBoulder(boulder);
		assertTrue("1 boulder in world", world.getBoulders().size()==1);
		world.removeBoulder(boulder);
		assertTrue("no boulders in world", world.getBoulders().size()==0);
	}
	
	@Test
	public void testCubeContainsLog() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Log log = new Log();
		world.addLog(log);
		log.setPosition(1, 1, 2);
		assertTrue("cube contains log", world.cubeContainsLog(1, 1, 2));
	}
	
	@Test
	public void testCubeContainsBoulder() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Boulder boulder = new Boulder();
		world.addBoulder(boulder);
		boulder.setPosition(1, 1, 2);
		assertTrue("cube contains boulder", world.cubeContainsBoulder(1, 1, 2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetCubeLogNoLog() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		world.getCubeLog(1, 1, 2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetCubeBoulderNoBoulder() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[1][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		world.getCubeBoulder(1, 1, 2);
	}
	
	@Test
	public void testAdvanceTimeUpdateCubes() throws ModelException {
		int[][][] types = new int[5][5][5];
		types[0][1][1] = 1;
		types[1][1][1] = 1;		
		types[2][1][1] = 1;
		types[3][1][1] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 0, 1, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.workAt(1, 1, 1);
		advanceTimeFor(world, 100, 0.1);
		assertTrue("worked cube is passable",world.getCubeType(1, 1, 1)==0);
		assertTrue("adjacent cube not connected to border is passable",world.getCubeType(2, 1, 1)==0);		
		assertTrue("further cube not connected to border is passable",world.getCubeType(3, 1, 1)==0);
		assertTrue("connected to border cube is solid", world.getCubeType(0, 1, 1)==1);
	}

	@Test
	public void testCaveInWithin5Seconds() throws ModelException{
		int[][][] types = new int[80][80][3];
		types[1][0][1] = 1;
		for (int i = 0; i<77; i++){
			for (int j = 0; j<77; j++){
				types[i+1][j+1][1] = 1;
			}
		}
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 0, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.workAt(1, 0, 1);
		advanceTimeFor(world,15,0.1);
		boolean noSolidCubes = this.noSolidCubes(world);
		assertTrue("no cubes left which are not connected to border",noSolidCubes);
	}
	
	@Test
	public void testCaveInMakingWorld() throws ModelException{
		int[][][] types = new int[80][80][3];
		types[0][0][1] = 1;
		for (int i = 0; i<77; i++){
			for (int j = 0; j<77; j++){
				types[i+1][j+1][1] = 1;
			}
		}
		World world = new World(types, new DefaultTerrainChangeListener());
		advanceTimeFor(world,5,0.1);
		boolean noSolidCubes = this.noSolidCubes(world);
		assertTrue("no cubes left which are not connected to border",noSolidCubes);
	}
	
	/**
	 * Check if there still are solid blocks in the world after caving in.
	 * @param world
	 * 		the world where the blocks has to cave in.
	 * @return ...
	 * 		| Return true if no solid blocks are found.
	 */
	public boolean noSolidCubes(World world){
		for (int i = 1; i<79; i++){
			for (int j = 1; j<79; j++){
					if (!world.isPassable(i, j, 1)){
						return false;
				}
			}
		}
		return true;
	}
}
