package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import junit.framework.Assert;
import ogp.framework.util.ModelException;

public class PathFindingTests {

	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;
	
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
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void testNoWayFound() throws ModelException{
		int[][][] types = new int[4][4][4];
		for (int i=-1; i<2; i++){
			for (int j=-1; j<2; j++){
				for (int k=-1; k<2; k++){
					if (i==0 && j==0 && k==0)
						types[1+i][1+j][1+k] = TYPE_AIR;
					else{
						types[1+i][1+j][1+k] = TYPE_ROCK;
					}
				}
			}
		}
		
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		int[] destination = new int[]{3,3,3} ;
		unit.moveTo(destination);
		advanceTimeFor(world, 100, 0.1);
	}
	
	@Test
	public void testPathFound() throws ModelException{
		int[][][] types = new int[4][4][4];
		types[0][0][0] = 1;
		types[1][0][0] = 1;
		types[1][0][1] = 1;
		types[1][1][1] = 1;
		types[1][2][1] = 1;
		types[2][2][1] = 1;
		types[2][2][2] = 1;
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 0, 0, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.moveTo(new int[] {3,3,3});
		advanceTimeFor(world, 100, 0.1);
		boolean reachedEnd = unit.getPosition()[0]==3.5 && unit.getPosition()[1]==3.5
				&& unit.getPosition()[2]==3.5;
		assertTrue("unit reached end position", reachedEnd);
	}
}
