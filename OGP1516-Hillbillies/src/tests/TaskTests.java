package tests;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import hillbillies.expressions.FriendExpression;
import hillbillies.model.CubeType;
import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.programs.TaskParser;
import hillbillies.scheduler.MyExpression;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.Scheduler;
import hillbillies.scheduler.Task;
import hillbillies.statements.PrintStatement;
import hillbillies.taskFactory.TaskFactory;

public class TaskTests {
	
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
	
	/**
	 * 
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
	public void testPriorityMinValue(){
		String name = "name"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		task.setPriority(Integer.MIN_VALUE+1);
		assertTrue("priority is min value", task.getPriority()==Integer.MIN_VALUE);
	}
	
	@Test
	public void testOneTaskInMultipleSchedulers() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 9, 9, 2 }, 50, 50, 50, 50, true);
		Unit enemy = new Unit("Test", new int[] { 5, 4, 2 }, 50, 50, 50, 50, false);
		Unit otherEnemy = new Unit("Test", new int[] { 0, 0, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(enemy);
		world.addUnit(otherEnemy);
		Faction faction = unit.getFaction();
		Faction enemyFaction = enemy.getFaction();
		Faction otherEnemyFaction = otherEnemy.getFaction();

		Scheduler scheduler = faction.getScheduler();
		Scheduler enemyScheduler = enemyFaction.getScheduler();
		Scheduler otherEnemyScheduler = otherEnemyFaction.getScheduler();
		
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: while is_alive this do work (9,9,1); done",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		enemyScheduler.schedule(task);
		otherEnemyScheduler.schedule(task);
		
		assertTrue("task belongs to 3 schedulers", task.getSchedulersForTask().size()==3);
		
		advanceTimeFor(world, 1, 0.02);
		try{ 
			enemy.setDefaultBehaviorEnabled(true);
			advanceTimeFor(world, 2, 0.02);
		} catch (Throwable e){}
		
		assertEquals(enemy.getAssignedTask(), null);
	}
	
	@Test (expected=Error.class)
	public void testNotWellFormedBreakBeforeWhile() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		TaskFactory factory = new TaskFactory();

		TaskParser.parseTasksFromString("name: \"task\"\npriority: 1\nactivities: break;"
				+ " while is_passable here do work here; done",
				factory, Collections.singletonList(null));
	}
	
	@Test (expected=Error.class)
	public void testNotWellFormedBreakNoWhile() {
		TaskFactory factory = new TaskFactory();
		TaskParser.parseTasksFromString("name: \"task\"\npriority: 1\nactivities: break;",
				factory, Collections.singletonList(null));
	}
	
	@Test (expected=Error.class)
	public void testNotWellFormedReadVariableBeforeAssign() {
		TaskFactory factory = new TaskFactory();
		TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: moveTo e; e:=any;",
				factory, Collections.singletonList(null));
	}
	
	@Test (expected=Error.class)
	public void testNotWellFormedReadVariableNoAssign() {
		TaskFactory factory = new TaskFactory();
		TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: print w;",
				factory, Collections.singletonList(null));
	}
	
	@Test (expected=Error.class)
	public void testNotWellFormedIfReadVariableElseAssign() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		TaskFactory factory = new TaskFactory();

		TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: if is_alive any then print k; else k:=any; fi",
				factory, Collections.singletonList(null));
	}
	
	@Test (expected=Error.class)
	public void testNotWellFormedIfAssignElseReadVariable() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		TaskFactory factory = new TaskFactory();

		TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: if is_alive any then k:=any; else print k; fi",
				factory, Collections.singletonList(null));
	}
	
	@Test (expected=Error.class)
	public void testNotWellFormedIfWhileElseBreak(){
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		TaskFactory factory = new TaskFactory();

		TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: if is_alive any then while is_alive any do work here; done else break; fi",
				factory, Collections.singletonList(null));
	}
	
	@Test (expected=Error.class)
	public void testNotWellFormedIfBreakElseWhile(){
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		TaskFactory factory = new TaskFactory();

		TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: if !is_alive any then break; else while is_alive any do work here; done fi",
				factory, Collections.singletonList(null));
	}
	
	@Test (expected=Error.class)
	public void testNotWellFormedBreakAfterNotInWhile() {
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		TaskFactory factory = new TaskFactory();

		TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: while is_alive any do work here; done break;",
				factory, Collections.singletonList(null));
	}
}
