package tests;

import static hillbillies.tests.util.PositionAsserts.assertIntegerPositionEquals;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import hillbillies.model.CubeType;
import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.programs.TaskParser;
import hillbillies.scheduler.Scheduler;
import hillbillies.scheduler.Task;
import hillbillies.taskFactory.TaskFactory;
import ogp.framework.util.Util;

public class UnitTests {
	
	/**
	 * Helper method to advance time for the given unit by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(Unit unit, double time, double step) {
		int n = (int) (time / step);
		for (int i = 0; i < n+1; i++)
			unit.advanceTime(step);
		unit.advanceTime(time - n * step);
	}
	
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
	public void testSetNameDoubleQuotes() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setName("Billy \"the Hillbilly\" Jones");
		assertEquals("This should be a valid name","Billy \"the Hillbilly\" Jones" , unit.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameBeginLetter()  {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setName("jan de smet");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetNameNumber() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setName("John 4 life");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameShort(){
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setName("L");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameEmpty() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setName("");
	}
	
	@Test
	public void testInitialStrengthTooLow() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 2, 50, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				25 <= unit.getStrength() && unit.getStrength() <= 100);
	}
	
	@Test
	public void testSetStrengthTooHigh() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 250, 50, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				1 <= unit.getStrength() && unit.getStrength() <= 200);
	}
	
	@Test
	public void testSetStrength() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,unit.getStrength());
	}
	
	@Test
	public void testInitialAgilityTooLow() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 2, 50, 50, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				25 <= unit.getAgility() && unit.getAgility() <= 100);
	}
	
	@Test
	public void testSetAgilityTooHigh() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 250, 50, 50, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				1 <= unit.getAgility() && unit.getAgility() <= 200);
	}
	
	@Test
	public void testSetAgility()  {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,unit.getAgility());
	}
	
	@Test
	public void testInitialToughnessTooLow(){
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 2, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				25 <= unit.getToughness() && unit.getToughness() <= 100);
	}
	
	@Test
	public void testSetToughnessTooHigh() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 250, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				1 <= unit.getToughness() && unit.getToughness() <= 200);
	}
	
	@Test
	public void testSetToughness() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,unit.getToughness());
	}
	
	@Test
	public void testInitialWeightTooLow() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 2, 50, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				(unit.getStrength() + unit.getAgility()) / 2 <= unit.getWeight() 
				&& unit.getWeight() <= 100);
	}
	
	@Test
	public void testSetWeightTooHigh() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 250, 50, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				(unit.getStrength() + unit.getAgility()) / 2 <= unit.getWeight() 
				&& unit.getWeight() <= 200);
	}
	
	@Test
	public void testSetWeight() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,unit.getWeight());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetPositionMoreThanDimension() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.setPosition(new double[]{4.5,4.5,4.5});
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetPositionInpassable() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.setPosition(new double[]{1,2,2});
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetPositionLessThanZero() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 0, 2, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.setPosition(new double[]{0,0,-2});
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetInitialPositionLengthTooLong() {
		new Unit("TestUnit", new int[] { 0, 2, 3, 4}, 50, 50, 50, 50, false);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetInitialPositionLessLengthTooShort() {
		new Unit("TestUnit", new int[] { 0, 2 }, 50, 50, 50, 50, false);
	}
	
	@Test 
	public void testCubeCoordinate() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertIntegerPositionEquals("A valid position should be accepted", 1, 2, 3, unit.getCubeCoordinate());
	}
	
	@Test
	public void testInitializeHitPointsToMax(){
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Assert.assertEquals("valid amount initial hitpoints", 
				(int) Math.ceil(200 * (unit.getWeight() / 100.0) * (unit.getToughness() / 100.0)), 
				unit.getCurrentHitPoints());
	}
	
	@Test
	public void testInitializeStaminaPointsToMax(){
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Assert.assertEquals("valid amount initial staminapoints", 
				(int) Math.ceil(200 * (unit.getWeight() / 100.0) * (unit.getToughness() / 100.0)), 
				unit.getCurrentStaminaPoints());
	}
	
	@Test
	public void testSetOrientationBiggerThan2PI() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setOrientation(3*Math.PI);
		assert Util.fuzzyEquals(Math.PI, unit.getOrientation());
	}

	@Test
	public void testSetOrientationSmallerThan0() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setOrientation(-0.5*Math.PI);
		assert Util.fuzzyEquals(1.5*Math.PI, unit.getOrientation());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidDurationAdvanceTime() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.advanceTime(0.5);
	}
	
	@Test
	public void testSprintingOutOfStaminaPoints(){
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 25, 25, 25, 25, false);
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[1][1][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		world.addUnit(unit);
		unit.setCurrentSpeed(1);
		unit.startSprinting();
		advanceTimeFor(unit, 100, 0.15);
		assertFalse("out of staminapoints, can not sprint anymore", unit.isSprinting());
	}
	
	@Test
	public void testRestAfterMove() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[1][1][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		world.addUnit(unit);
		unit.moveTo(new int[] {1,1,1});
		unit.rest();
		assertFalse("movement interrupterd", unit.isMoving());
	}
	
	
	@Test
	public void testWorkAfterMove() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		world.addUnit(unit);
		unit.moveTo(new int[] {3,2,3});
		unit.work();
		unit.advanceTime(0.1);
		assertFalse("movement interrupterd", unit.isMoving());
	}
	
	@Test
	public void testRestLessThan1HitPointInterruptedAttack(){
		int[][][] types = new int[4][4][4];
		types[1][1][1] = CubeType.ROCK.getCubeType();
		types[0][1][1] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = new Unit("TestUnit", new int[] { 0, 1, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(defender);
		unit.setPosition(new double[]{1,1,2});
		unit.advanceTime(0.1);
		unit.rest();
		unit.fight(defender);
		unit.advanceTime(0.1);
		assertFalse("stopped resting during attack", unit.isResting());
	}
	
	@Test
	public void testRestNotInterruptedByWork(){
		int[][][] types = new int[4][4][4];
		types[1][2][1] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 2 }, 25, 25, 25, 25, false);
		world.addUnit(unit);
		unit.moveToAdjacent(0, 0, 1);
		advanceTimeFor(unit, 100, 0.1);
		unit.rest();
		unit.workAt(1,1,1);
		unit.advanceTime(0.1);
		assertTrue("rest not interrupted by work", unit.isResting());
	}
	
	@Test
	public void testRestStopRestingWhenFullyLoaded() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.rest();
		unit.advanceTime(0.1);
		assertFalse("stop resting when fully loaded points", unit.isResting());
	}
	
	@Test
	public void testWorkInterruptedByRest() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.workAt(2,2,3);
		unit.rest();
		unit.advanceTime(0.1);
		assertFalse("stop working and start resting", unit.isWorking());
	}
	
	@Test
	public void testWorkInterruptedByAttack() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = new Unit("TestUnit", new int[] { 2, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(defender);
		unit.workAt(2,2,3);
		unit.fight(defender);
		unit.advanceTime(0.1);
		assertFalse("stop working and start attacking", unit.isWorking());
	}
	
	@Test
	public void testWorkNotInterruptedByMoving(){
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[1][1][2] = CubeType.ROCK.getCubeType();
		types[1][0][2] = CubeType.ROCK.getCubeType();
		types[1][3][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		world.addUnit(unit);
		unit.workAt(1,3,3);
		unit.moveTo(new int[] {1,0,3});
		unit.advanceTime(0.1);
		assertTrue("keep working and don't move", unit.isWorking());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetCurrentSpeedSmallerThanZero() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setCurrentSpeed(-5);
	}
	
	@Test
	public void testMoveToAdjacent() {
		int[][][] types = new int[4][4][4];
		types[1][1][0] = CubeType.ROCK.getCubeType();
		types[1][2][0] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 1 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.moveToAdjacent(0, 1, 0);
		double speed = unit.getCurrentSpeed();
		double distance = Math.sqrt(2);
		double time = distance / speed;
		advanceTimeFor(unit, time, 0.1);
		assertArrayEquals("Moved to adjacent cube", new double[]{1.5,2.5,1.5}, 
				unit.getPosition(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testIsMoving(){
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setCurrentSpeed(6);
		assert unit.isMoving();
	}
	
	@Test
	public void testIsSprinting() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][2] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.moveToAdjacent(1, 0, 0);
		unit.advanceTime(0.1);
		unit.startSprinting();
		assert unit.isSprinting();
		}
	
	@Test
	public void testIsNotSprinting(){
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.startSprinting();
		unit.stopSprinting();
		assert !unit.isSprinting();
		}
	
	@Test
	public void testMoveTo(){
		int[][][] types = new int[4][4][4];
		types[1][1][2] = CubeType.ROCK.getCubeType();
		types[2][2][2] = CubeType.ROCK.getCubeType();
		types[3][3][2] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.moveTo(new int[] {3,3,3});
		double speed = unit.getCurrentSpeed();
		double distance = 2*Math.sqrt(2);
		double time = distance / speed;
		advanceTimeFor(unit, time, 0.1);
		assertArrayEquals("arrived at chosen position", new double[]{3.5,3.5,3.5}, 
				unit.getPosition(), Util.DEFAULT_EPSILON);	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testMoveToFallingPosition(){
		int[][][] types = new int[4][4][4];
		types[1][1][2] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.moveTo(new int[] {3,3,3});
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testMoveToInpassableCube() {
		int[][][] types = new int[4][4][4];
		types[1][1][2] = CubeType.ROCK.getCubeType();
		types[2][2][2] = CubeType.ROCK.getCubeType();
		types[3][3][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.moveTo(new int[] {3,3,3});
	}
	
	@Test
	public void testWorkAt() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.workAt(2,2,3);
		assertTrue("if unit is working: true", unit.isWorking());
	}
	
	@Test
	public void testFight() {
		int[][][] types = new int[4][4][4];
		types[1][1][2] = CubeType.ROCK.getCubeType();
		types[2][2][2] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 3 }, 50, 50, 50, 50, false);
		Unit defender = new Unit("TestUnit", new int[] { 2, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(defender);
		unit.fight(defender);
		assertTrue("unit is attacking defender", unit.isAttacking());
	}
	
	@Test
	public void testFightSameFaction() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		types[2][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit unit2 = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit unit3 = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit unit4 = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);		
		Unit unit5 = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = new Unit("TestUnit", new int[] { 2, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(unit2);
		world.addUnit(unit3);
		world.addUnit(unit4);
		world.addUnit(unit5);		
		world.addUnit(defender);
		unit5.fight(defender);
		unit5.advanceTime(0.1);
		assertFalse("units aren't fighting", unit.isAttacking());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testFightToFarApart(){
		int[][][] types = new int[4][4][4];
		types[0][0][1] = CubeType.ROCK.getCubeType();
		types[3][3][1] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 0, 0, 2 }, 50, 50, 50, 50, false);
		Unit defender = new Unit("TestUnit", new int[] { 3, 3, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(defender);
		unit.fight(defender);
		assertFalse("unit is attacking defender", unit.isAttacking());
	}
	
	@Test
	public void testRest() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.rest();
		assertTrue("if unit is resting: true", unit.isResting());
	}
	
	@Test
	public void testDefaultBehaviorTrue() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setDefaultBehaviorEnabled(true);
		assertTrue("default behavior enabled", unit.isDefaultBehaviorEnabled());
	}
	
	@Test
	public void testDefaultBehaviorFalse() {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setDefaultBehaviorEnabled(false);
		assertFalse("default behavior dissbled", unit.isDefaultBehaviorEnabled());
	}
		
	@Test
	public void testIsFallingPosition() {
		int[][][] types = new int[4][4][4];
		types[1][2][2] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		assertTrue("is falling position", unit.isFallingPosition(0, 0, 2));
	}
	
	@Test
	public void testExperiencePointsMoveToAdjacent() {
		int[][][] types = new int[4][4][4];
		types[1][1][2] = CubeType.ROCK.getCubeType();
		types[2][2][2] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.moveToAdjacent(1, 1, 0);
		advanceTimeFor(unit,100,0.15);
		assertTrue("1 experience point", unit.getExperiencePoints()==1);
	}
	
	@Test
	public void testExperiencePointsMoreThan10() {
		int[][][] types = new int[4][4][4];
		types[1][1][2] = CubeType.ROCK.getCubeType();
		types[2][2][2] = CubeType.ROCK.getCubeType();
		types[1][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		int agility = unit.getAgility();
		int strength = unit.getStrength();
		int toughness = unit.getToughness();
		unit.workAt(1, 2, 3);
		advanceTimeFor(unit,100,0.15);
		boolean characteristicPlus1 = unit.getAgility()-agility==1 || unit.getStrength()-strength == 1
				|| unit.getToughness()-toughness == 1;
		assertTrue("no experience points", unit.getExperiencePoints()==0);
		assertTrue("characteristic plus 1",characteristicPlus1);
	}
	
	@Test
	public void testIsAlive(){
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 3 }, 50, 50, 50, 50, false);
		assertTrue("unit is alive", unit.isAlive());
	}
	
	@Test
	public void testLoseHitPointsByFalling() {
		int[][][] types = new int[4][4][4];
		types[1][1][1] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		int hitPoints = unit.getCurrentHitPoints();
		unit.moveToAdjacent(0, 0, 1);
		advanceTimeFor(unit,100,0.1);		
		assertTrue("lost hitpoints", hitPoints-unit.getCurrentHitPoints() == 10);
	}
	
	@Test
	public void testIsCarryingBoulder() {
		int[][][] types = new int[4][4][4];
		types[1][1][2] = CubeType.ROCK.getCubeType();
		types[1][2][3] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.workAt(1, 2, 3);
		advanceTimeFor(unit,100,0.1);
		unit.workAt(1, 2, 3);
		advanceTimeFor(unit,100,0.1);
		assertTrue("unit is carrying boulder", unit.isCarryingBoulder());
	}
	
	@Test
	public void testIsCarryingLog(){
		int[][][] types = new int[4][4][4];
		types[1][1][2] = CubeType.ROCK.getCubeType();
		types[1][2][3] = CubeType.TREE.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 3 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.workAt(1, 2, 3);
		advanceTimeFor(unit,100,0.1);
		unit.workAt(1, 2, 3);
		advanceTimeFor(unit,100,0.1);
		assertTrue("unit is carrying log", unit.isCarryingLog());
	}
	
	@Test
	public void testFallingNotInterruptedByResting() {
		int[][][] types = new int[4][4][4];
		types[1][1][1] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.moveToAdjacent(0, 0, 1);
		advanceTimeFor(unit,100,0.1);
		unit.moveToAdjacent(0, 0, 1);
		advanceTimeFor(unit,0.7,0.1);
		unit.rest();
		unit.advanceTime(0.1);
		assertFalse("unit isn't resting", unit.isResting());
	}
	
	@Test
	public void testFallingNotInterruptedByWorking() {
		int[][][] types = new int[4][4][4];
		types[1][1][1] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.moveToAdjacent(0, 0, 1);
		advanceTimeFor(unit,0.7,0.1);
		unit.workAt(1,1,3);
		unit.advanceTime(0.1);
		assertFalse("unit isn't working", unit.isWorking());
	}
	
	@Test
	public void testFallingNotInterruptedByAttacking(){
		int[][][] types = new int[4][4][4];
		types[1][1][1] = CubeType.ROCK.getCubeType();
		types[1][2][1] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit unit = new Unit("TestUnit", new int[] { 1, 1, 2 }, 50, 50, 50, 50, false);
		Unit defender = new Unit("TestUnit", new int[] { 1, 2, 2 }, 50, 50, 50, 50, false);
		world.addUnit(unit);
		world.addUnit(defender);
		unit.moveToAdjacent(0, 0, 1);
		advanceTimeFor(unit,0.7,0.1);
		unit.fight(defender);
		unit.advanceTime(0.1);
		assertFalse("unit isn't working", unit.isAttacking());
	}
	
	@Test
	public void testFollowing(){
		int[][][] types = new int[8][4][4];
		for (int i=0; i<8; i++)
			types[i][1][1] = CubeType.ROCK.getCubeType();
		World world = new World(types, new DefaultTerrainChangeListener());
		Unit follower = new Unit("TestUnit", new int[] { 0, 1, 2 }, 50, 50, 50, 50, false);
		Unit followed = new Unit("TestUnit", new int[] { 3, 1, 2 }, 50, 50, 50, 50, false);
		world.addUnit(follower);
		world.addUnit(followed);
		follower.follow(followed);
		advanceTimeFor(world,3,0.1);
		assertTrue("followed", follower.getCubeCoordinate()[0]==3);
		followed.moveTo(new int[]{7,1,2});
		advanceTimeFor(world,3,0.1);
		assertTrue("stopped following", follower.getCubeCoordinate()[0]==3);
		follower.follow(followed);
		advanceTimeFor(world,3,0.1);
		assertTrue("followed again", follower.getCubeCoordinate()[0]==7);
	}
	
	@Test
	public void testFollowInterruptTask() {
		int[][][] types = new int[10][10][5];
		for (int i=0; i<10; i++)
			for (int j=0; j<10; j++)
				types[i][j][1] = CubeType.ROCK.getCubeType();
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
		int priority = task.getPriority();
		advanceTimeFor(world, 1, 0.02);
		assertTrue("is following", unit.isMoving());
		
		unit.setDefaultBehaviorEnabled(false);
		unit.interruptTask();
		advanceTimeFor(world, 1, 0.02);
		
		assertEquals(unit.getAssignedTask(), null);
		assertEquals(task.getAssignedUnit(),null);		
		assertFalse("task interrupted",unit.isMoving());
		
		assertFalse("task not executed",task.getActivity().isExecuted());
		assertEquals(priority-100,task.getPriority());
	}
}