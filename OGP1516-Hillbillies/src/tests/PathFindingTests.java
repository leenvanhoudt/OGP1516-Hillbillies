package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.model.CubeType;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class PathFindingTests {
	
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
		for (int i = 0; i < n; i++)
			world.advanceTime(step);
		world.advanceTime(time - n * step);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testNoWayFound(){
		int[][][] types = new int[4][4][4];
		for (int i=-1; i<2; i++){
			for (int j=-1; j<2; j++){
				for (int k=-1; k<2; k++){
					if (i==0 && j==0 && k==0)
						types[1+i][1+j][1+k] = CubeType.AIR.getCubeType();
					else{
						types[1+i][1+j][1+k] = CubeType.ROCK.getCubeType();
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
	public void testPathFound(){
		int[][][] types = new int[4][4][4];
		types[0][0][0] = CubeType.ROCK.getCubeType();
		types[1][0][0] = CubeType.ROCK.getCubeType();
		types[1][0][1] = CubeType.ROCK.getCubeType();
		types[1][1][1] = CubeType.ROCK.getCubeType();
		types[1][2][1] = CubeType.ROCK.getCubeType();
		types[2][2][1] = CubeType.ROCK.getCubeType();
		types[2][2][2] = CubeType.ROCK.getCubeType();
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
