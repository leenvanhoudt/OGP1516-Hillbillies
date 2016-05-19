package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.CubeType;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class boulderTests {
	
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
	public void testFalling() {
		int[][][] types = new int[4][4][4];
		types[1][2][1] = CubeType.ROCK.getCubeType();
		types[2][2][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 2, 2, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		Boulder boulder = new Boulder();
		world.addBoulder(boulder);
		boulder.setWorld(world);
		boulder.setPosition(1, 2, 2);
		unit.workAt(1, 2, 1);
		advanceTimeFor(world,100,0.1);
		assertTrue("boulder has fallen",boulder.getPosition()[2]==0);
	}

}
