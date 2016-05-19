package tests;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.CubeType;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.programs.TaskParser;
import hillbillies.scheduler.Scheduler;
import hillbillies.scheduler.Task;
import hillbillies.taskFactory.TaskFactory;
import hillbillies.model.UtilCompareList;
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
	
	/*@Test
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
				"name: \"task\"\npriority: 1\nactivities: follow enemy;",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 2, 0.02);
		assertTrue("is following", unit.isMoving());
	}

	@Test
	public void testWhileFollowBreak() throws ModelException{
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
				"name: \"task\"\npriority: 1\nactivities: while is_alive(enemy) do\nfollow enemy;\nbreak;\ndone",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 2, 0.02);
		assertTrue("is following", unit.isMoving());
		unit.setDefaultBehaviorEnabled(false);
		advanceTimeFor(world, 7, 0.02);
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
				"name: \"task\"\npriority: 1\nactivities: follow any;",
				factory, Collections.singletonList(new int[] { 1, 1, 1 }));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 2, 0.02);
		assertTrue("is following", unit.isMoving());
	}
	
	@Test
	public void testIfCarriesBoulderThenWork() throws ModelException{
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
				"name: \"task\"\npriority: 1\nactivities: if carries_item(this) then \n work selected; \nfi",
				factory, Collections.singletonList(new int[] { 5, 5, 1 }));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		unit.workAt(5, 4, 2);
		advanceTimeFor(world, 10, 0.02);
		assertTrue("working", unit.isWorking());
	}
	
	@Test (expected=Error.class)
	public void testSelectedNotGiven() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		Faction faction = unit.getFaction();

		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: work selected;",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 10, 0.02);
	}
	
	@Test
	public void testIfAndThenMovePositionOfReadVariable() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		Unit enemy = new Unit("Test", new int[] { 7, 7, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(enemy);
		Faction faction = unit.getFaction();

		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1"
				+ "\nactivities: e := enemy; \n if is_solid (5,5,1) && is_enemy e then \nmoveTo position_of e; \n fi",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 0.5, 0.02);
		unit.setDefaultBehaviorEnabled(false);
		advanceTimeFor(world, 10, 0.02);
		assertTrue("unit moved", UtilCompareList.compareIntList(unit.getCubeCoordinate(), enemy.getCubeCoordinate()));
	}
	
	@Test
	public void testWhileNotKeepWorkingNextTo() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		Faction faction = unit.getFaction();

		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1"
				+ "\nactivities: e :=  false; \n while !e do\nwork next_to here;\ndone",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 100, 0.02);
		assertTrue("unit worked", unit.isWorking());
	}
	
	@Test
	public void testWhileKeepWorkingHere() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		Unit enemy = new Unit("Test", new int[] { 7, 7, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(enemy);
		Faction faction = unit.getFaction();

		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1"
				+ "\nactivities: while is_alive(enemy) do\nwork (here);\ndone",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 100, 0.02);
		assertTrue("unit worked", unit.isWorking());
	}*/
	
	//TODO: fix
	@Test
	public void testIfNotTrueWorkElseAttack() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		Unit enemy = new Unit("Test", new int[] { 5, 6, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(enemy);
		Faction faction = unit.getFaction();

		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1"
				+ "\nactivities: e:=true; if !e then work here; else attack enemy; fi",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		System.out.println("scheduled");
		advanceTimeFor(world, 0.5, 0.02);
		assertFalse("unit isn't working", unit.isWorking());
		assertTrue("unit is attacking", unit.isAttacking());
	}
	
	/*@Test
	public void testIfPassableOrFriendMoveToWorkshop() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		types[7][6][2] = CubeType.WORKSHOP.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		for (int i=0; i<8; i++)
			world.spawnUnit(false);
		Unit friend = new Unit("Test", new int[] { 7, 7, 2 }, 50, 50, 50, 50, false);
		world.addUnit(friend);
		Faction faction = unit.getFaction();

		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1 "
				+ "\nactivities: e:=friend; \n while (is_alive(e) || is_passable(5,5,1)) do\nmoveTo position_of e; \n work workshop; \ndone",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 5, 0.02);
		assertTrue("unit moved to friend", UtilCompareList.compareIntList(unit.getCubeCoordinate(), friend.getCubeCoordinate()));
		assertTrue("unit worked", unit.isWorking());
	}
	
	@Test
	public void testIfIsNotFriendMoveBoulderCarryLog() throws ModelException{
		int[][][] types = this.cubeTypesFlatSurface();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("Test", new int[] { 5, 5, 2 }, 50, 50, 50, 50, true);
		world.addUnit(unit);
		Unit otherUnit = new Unit("Test", new int[] { 6, 6, 2 }, 50, 50, 50, 50, false);
		world.addUnit(otherUnit);
		Faction faction = unit.getFaction();

		Boulder boulder = new Boulder();
		world.addBoulder(boulder);
		boulder.setWorld(world);
		boulder.setPosition(5, 4, 2);
		
		Log log = new Log();
		world.addLog(log);
		log.setWorld(world);
		log.setPosition(4, 4, 2);
		
		Scheduler scheduler = faction.getScheduler();
		TaskFactory factory = new TaskFactory();

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1 "
				+ "\nactivities: if !(is_friend any) then \nmoveTo boulder; \nwork log;\n print any;\n fi",
				factory, Collections.singletonList(null));
		Task task = tasks.get(0);
		
		scheduler.schedule(task);
		advanceTimeFor(world, 1, 0.02);
		unit.setDefaultBehaviorEnabled(false);
		advanceTimeFor(world, 20, 0.02);
		assertTrue("unit moved to boulder", unit.getCubeCoordinate()[0]==boulder.getPosition()[0] 
				&& unit.getCubeCoordinate()[1]==boulder.getPosition()[1]);
		assertTrue("unit is carrying log", unit.isCarryingLog());
	}*/
}
