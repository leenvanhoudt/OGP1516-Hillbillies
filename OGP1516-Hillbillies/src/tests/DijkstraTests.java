package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.DijkstraPathFinding;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class DijkstraTests {

	public World makeWorld(){
		int[][][] types = new int[10][10][5];
		for (int i=0; i<10; i++)
			for (int j=0; j<10; j++)
				types[i][j][1] = 1;
		return new World(types, new DefaultTerrainChangeListener());
	}
	
	@Test
	public void testDijkstraFindBoulder() {
		World world = makeWorld();
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

}
