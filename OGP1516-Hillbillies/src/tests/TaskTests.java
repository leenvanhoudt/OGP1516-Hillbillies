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
import ogp.framework.util.ModelException;

public class TaskTests {
	
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
	public void testPriorityMinValue() throws ModelException{
		String name = "name"; int priority = 5; 
		MyExpression expression = new FriendExpression();
		MyStatement activity = new PrintStatement(expression); 
		int[] selectedCubes = null;
		Task task = new Task(name, priority, activity, selectedCubes);
		task.setPriority(Integer.MIN_VALUE+1);
		assertTrue("priority is min value", task.getPriority()==Integer.MIN_VALUE);
	}
	
	//TODO: fix
	@Test (expected=Error.class)
	public void testNotWellFormedBreakBeforeWhile() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		TaskFactory factory = new TaskFactory();

		TaskParser.parseTasksFromString("name: \"task\"\npriority: 1\nactivities: break;"
				+ " while is_passable here do work here; done",
				factory, Collections.singletonList(null));
	}
	
	@Test
	public void testOneTaskInMultipleSchedulers() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 9, 9, 2 }, 50, 50, 50, 50, false);
		Unit enemy = new Unit("Test", new int[] { 0, 0, 2 }, 50, 50, 50, 50, false);
		Unit otherEnemy = new Unit("Test", new int[] { 0, 0, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(enemy);
		world.addUnit(otherEnemy);
		Faction faction = unit.getFaction();
		Faction enemyFaction = enemy.getFaction();
		Faction otherEnemyFaction = otherEnemy.getFaction();

		Scheduler scheduler = faction.getScheduler();
		Scheduler enemyScheduler = faction.getScheduler();
		Scheduler otherEnemyScheduler = faction.getScheduler();
		
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: while is_alive this do follow enemy; done",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		assertTrue("task belongs to 3 schedulers", task.getSchedulersForTask().size()==3);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 2, 0.02);
		//assertTrue("is following", unit.isMoving());
	}

}
