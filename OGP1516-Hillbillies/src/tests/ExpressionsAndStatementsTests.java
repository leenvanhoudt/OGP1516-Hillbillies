package tests;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.CubeType;
import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.programs.TaskParser;
import hillbillies.scheduler.Scheduler;
import hillbillies.scheduler.Task;
import hillbillies.taskFactory.TaskFactory;
import ogp.framework.util.ModelException;

public class ExpressionsAndStatementsTests {
	
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
	public void testSimpleFollowTask() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 9, 9, 2 }, 50, 50, 50, 50, true);
		Unit enemy = new Unit("Test", new int[] { 0, 0, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(enemy);
		Faction faction = unit.getFaction();

		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"follow task\"\npriority: 1\nactivities: follow enemy;",
				factory, Collections.singletonList(new int[] {}));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 2, 0.02);
		assertTrue("is following", unit.isMoving());
	}

	@Test
	public void testWhileBreak() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 9, 9, 2 }, 50, 50, 50, 50, true);
		Unit enemy = new Unit("Test", new int[] { 0, 0, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(enemy);
		Faction faction = unit.getFaction();

		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"while break task\"\npriority: 1\nactivities: print enemy; \nwhile is_alive(enemy) do\nfollow enemy;\nbreak;\ndone",
				factory, Collections.singletonList(new int[] { 1, 1, 1 }));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 2, 0.02);
		assertTrue("is following", unit.isMoving());
		advanceTimeFor(world, 10, 0.02);
		assertFalse("break after followed", unit.isMoving());
	}
	
	@Test
	public void testFollowAny() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		Unit otherUnit = new Unit("Test", new int[] { 0, 0, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(otherUnit);
		Faction faction = unit.getFaction();

		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"while break task\"\npriority: 1\nactivities: follow any;",
				factory, Collections.singletonList(new int[] { 1, 1, 1 }));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 2, 0.02);
		assertTrue("is following", unit.isMoving());
	}
	
	@Test
	public void testCarriesBoulder() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		Boulder boulder = new Boulder();
		world.addBoulder(boulder);
		boulder.setWorld(world);
		boulder.setPosition(5, 4, 2);
		Faction faction = unit.getFaction();

		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"while break task\"\npriority: 1\nactivities: if carries_item(this) then \n work selected; \nfi",
				factory, Collections.singletonList(new int[] { 5, 5, 1 }));
		System.out.println("tasks");
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		unit.workAt(5, 4, 2);
		advanceTimeFor(world, 15, 0.02);
		assertTrue("working", unit.isWorking());
	}

}
