package tests;

import static hillbillies.tests.util.PositionAsserts.assertIntegerPositionEquals;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import hillbillies.model.Unit;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;

public class UnitTests {
	
	@Test
	public void testSetNameDoubleQuotes() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setName("Billy \"the Hillbilly\" Jones");
		assertEquals("This should be a valid name","Billy \"the Hillbilly\" Jones" , unit.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameBeginLetter() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setName("jan de smet");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetNameNumber() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setName("John 4 life");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameShort() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setName("L");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameEmpty() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setName("");
	}
	
	@Test
	public void testInitialStrengthTooLow() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 2, 50, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				25 <= unit.getStrength() && unit.getStrength() <= 100);
	}
	
	@Test
	public void testSetStrengthTooHigh() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 250, 50, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				1 <= unit.getStrength() && unit.getStrength() <= 200);
	}
	
	@Test
	public void testSetStrength() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,unit.getStrength());
	}
	
	@Test
	public void testInitialAgilityTooLow() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 2, 50, 50, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				25 <= unit.getAgility() && unit.getAgility() <= 100);
	}
	
	@Test
	public void testSetAgilityTooHigh() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 250, 50, 50, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				1 <= unit.getAgility() && unit.getAgility() <= 200);
	}
	
	@Test
	public void testSetAgility() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,unit.getAgility());
	}
	
	@Test
	public void testInitialToughnessTooLow() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 2, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				25 <= unit.getToughness() && unit.getToughness() <= 100);
	}
	
	@Test
	public void testSetToughnessTooHigh() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 250, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				1 <= unit.getToughness() && unit.getToughness() <= 200);
	}
	
	@Test
	public void testSetToughness() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,unit.getToughness());
	}
	
	@Test
	public void testInitialWeightTooLow() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 2, 50, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				(unit.getStrength() + unit.getAgility()) / 2 <= unit.getWeight() 
				&& unit.getWeight() <= 100);
	}
	
	@Test
	public void testSetWeightTooHigh() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 250, 50, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				(unit.getStrength() + unit.getAgility()) / 2 <= unit.getWeight() 
				&& unit.getWeight() <= 200);
	}
	
	@Test
	public void testSetWeight() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,unit.getWeight());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetPositionMoreThan50() throws ModelException {
		new Unit("TestUnit", new int[] { 1, 70, 3 }, 50, 50, 50, 50, false);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetPositionLessThanZero() throws ModelException {
		new Unit("TestUnit", new int[] { 0, 2, -20 }, 50, 50, 50, 50, false);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetInitialPositionLengthTooLong() throws ModelException {
		new Unit("TestUnit", new int[] { 0, 2, 3, 4}, 50, 50, 50, 50, false);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetInitialPositionLessLengthTooShort() throws ModelException {
		new Unit("TestUnit", new int[] { 0, 2 }, 50, 50, 50, 50, false);
	}
	
	@Test 
	public void testCubeCoordinate() throws ModelException {
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertIntegerPositionEquals("A valid position should be accepted", 1, 2, 3, unit.getCubeCoordinate());
	}
	
	@Test
	public void testInitializeHitPointsToMax() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Assert.assertEquals("valid amount initial hitpoints", 
				(int) Math.ceil(200 * (unit.getWeight() / 100.0) * (unit.getToughness() / 100.0)), 
				unit.getCurrentHitPoints());
	}
	
	@Test
	public void testInitializeStaminaPointsToMax() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Assert.assertEquals("valid amount initial staminapoints", 
				(int) Math.ceil(200 * (unit.getWeight() / 100.0) * (unit.getToughness() / 100.0)), 
				unit.getCurrentStaminaPoints());
	}
	
	@Test
	public void testSetOrientationBiggerThan2PI() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setOrientation(3*Math.PI);
		assert Util.fuzzyEquals(Math.PI, unit.getOrientation());
	}

	@Test
	public void testSetOrientationSmallerThan0() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setOrientation(-0.5*Math.PI);
		assert Util.fuzzyEquals(1.5*Math.PI, unit.getOrientation());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidDurationAdvanceTime() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.advanceTime(0.5);
	}
	
	/*@Test
	public void testSprintingOutOfStaminaPoints() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.startSprinting();
		unit.setStaminaPoints(0);
		assertFalse("out of staminapoints, can not sprint anymore", unit.isSprinting());
	}*/
	
	@Test
	public void testRestAfterMove() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.moveTo(new int[] {25,25,25});
		unit.rest();
		assertFalse("movement interrupterd", unit.isMoving());
	}
	
	
	@Test
	public void testWorkAfterMove() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.moveTo(new int[] {3,2,3});
		unit.work();
		unit.advanceTime(0.1);
		assertFalse("movement interrupterd", unit.isMoving());
	}
	
	/*@Test
	public void testRestLessThan1HitPointInterruptedAttack() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = facade.createUnit("TestUnit", new int[] { 2, 2, 3 }, 50, 50, 50, 50, false);
		unit.rest();
		unit.setHitPoints(unit.getCurrentHitPoints()-5);
		unit.fight(defender);
		unit.advanceTime(0.1);
		assertFalse("stopped resting during attack", unit.isResting());
	}*/
	
	/*@Test
	public void testRestNotInterruptedByWork() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.rest();
		unit.setHitPoints(unit.getCurrentHitPoints()-5);
		unit.work();
		unit.advanceTime(0.1);
		assertTrue("rest not interrupted by work", unit.isResting());
	}*/
	
	/*@Test
	public void testRestStopRestingWhenFullyLoaded() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.rest();
		unit.setHitPoints(unit.getMaxHitPoints());
		unit.setStaminaPoints(unit.getMaxStaminaPoints());
		unit.advanceTime(0.1);
		assertFalse("stop resting when fully loaded points", unit.isResting());
	}*/
	
	@Test
	public void testWorkInterruptedByRest() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.work();
		unit.rest();
		unit.advanceTime(0.1);
		assertFalse("stop working and start resting", unit.isWorking());
	}
	
	@Test
	public void testWorkInterruptedByAttack() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = new Unit("TestUnit", new int[] { 2, 2, 3 }, 50, 50, 50, 50, false);
		unit.work();
		unit.fight(defender);
		unit.advanceTime(0.1);
		assertFalse("stop working and start attacking", unit.isWorking());
	}
	
	@Test
	public void testWorkNotInterruptedByMoving() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.work();
		unit.moveTo(new int[] {5,5,5});
		unit.advanceTime(0.1);
		assertTrue("keep working and don't move", unit.isWorking());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetCurrentSpeedSmallerThanZero() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setCurrentSpeed(-5);
	}
	
	@Test
	public void testMoveToAdjacent() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 5, 5, 3 }, 50, 50, 50, 50, false);
		unit.moveToAdjacent(0, 1, 0);
		double speed = unit.getCurrentSpeed();
		double distance = Math.sqrt(2);
		double time = distance / speed;
		advanceTimeFor(unit, time, 0.1);
		assertArrayEquals("Moved to adjacent cube", new double[]{5.5,6.5,3.5}, 
				unit.getPosition(), Util.DEFAULT_EPSILON);
	}
	
	/**
	 * Helper method to advance time for the given unit by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(Unit unit, double time, double step) throws ModelException {
		int n = (int) (time / step);
		for (int i = 0; i < n+1; i++)
			unit.advanceTime(step);
		unit.advanceTime(time - n * step);
	}
	
	
	
	@Test
	public void testIsMoving() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setCurrentSpeed(6);
		assert unit.isMoving();
	}
	
	@Test
	public void testIsSprinting() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.moveToAdjacent(0, 1, 0);
		unit.advanceTime(0.1);
		unit.startSprinting();
		assert unit.isSprinting();
		}
	
	@Test
	public void testIsNotSprinting() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.startSprinting();
		unit.stopSprinting();
		assert !unit.isSprinting();
		}
	
	@Test
	public void testMoveTo() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.moveTo(new int[] {3,4,3});
		double speed = unit.getCurrentSpeed();
		double distance = 2*Math.sqrt(2);
		double time = distance / speed;
		advanceTimeFor(unit, time, 0.1);
		assertArrayEquals("arrived at chosen position", new double[]{3.5,4.5,3.5}, 
				unit.getPosition(), Util.DEFAULT_EPSILON);	}
	
		
	@Test
	public void testWork() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.work();
		assertTrue("if unit is working: true", unit.isWorking());
	}
	
	@Test
	public void testFight() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = new Unit("TestUnit", new int[] { 2, 2, 3 }, 50, 50, 50, 50, false);
		unit.fight(defender);
		assertTrue("unit is attacking defender", unit.isAttacking());
	}
	
	@Test
	public void testFightToFarApart() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = new Unit("TestUnit", new int[] { 6, 2, 3 }, 50, 50, 50, 50, false);
		unit.fight(defender);
		assertFalse("unit is attacking defender", unit.isAttacking());
	}
	
	@Test
	public void testRest() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.rest();
		assertTrue("if unit is resting: true", unit.isResting());
	}
	
	@Test
	public void testDefaultBehaviorTrue() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setDefaultBehaviorEnabled(true);
		assertTrue("default behavior enabled", unit.isDefaultBehaviorEnabled());
	}
	
	@Test
	public void testDefaultBehaviorFalse() throws ModelException{
		Unit unit = new Unit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setDefaultBehaviorEnabled(false);
		assertFalse("default behavior dissbled", unit.isDefaultBehaviorEnabled());
	}
	
}