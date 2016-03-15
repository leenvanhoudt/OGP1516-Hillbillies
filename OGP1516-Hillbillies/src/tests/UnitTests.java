package tests;

import static hillbillies.tests.util.PositionAsserts.assertIntegerPositionEquals;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hillbillies.model.Unit;
import hillbillies.part1.facade.Facade;
import hillbillies.part1.facade.IFacade;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;

public class UnitTests {

	private Facade facade;

	@Before
	public void setup() {
		this.facade = new Facade();
	}
	
	@Test
	public void testSetNameDoubleQuotes() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.setName(unit, "Billy \"the Hillbilly\" Jones");
		assertEquals("This should be a valid name","Billy \"the Hillbilly\" Jones" , facade.getName(unit));
	}
	
	@Test(expected=ModelException.class)
	public void testSetNameBeginLetter() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.setName(unit, "jan de smet");
	}

	@Test(expected=ModelException.class)
	public void testSetNameNumber() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.setName(unit, "John 4 life");
	}
	
	@Test(expected=ModelException.class)
	public void testSetNameShort() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.setName(unit, "L");
	}
	
	@Test(expected=ModelException.class)
	public void testSetNameEmpty() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.setName(unit, "");
	}
	
	@Test
	public void testInitialStrengthTooLow() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 2, 50, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				25 <= facade.getStrength(unit) && facade.getStrength(unit) <= 100);
	}
	
	@Test
	public void testSetStrengthTooHigh() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 250, 50, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				1 <= facade.getStrength(unit) && facade.getStrength(unit) <= 200);
	}
	
	@Test
	public void testSetStrength() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,facade.getStrength(unit));
	}
	
	@Test
	public void testInitialAgilityTooLow() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 2, 50, 50, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				25 <= facade.getAgility(unit) && facade.getAgility(unit) <= 100);
	}
	
	@Test
	public void testSetAgilityTooHigh() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 250, 50, 50, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				1 <= facade.getAgility(unit) && facade.getAgility(unit) <= 200);
	}
	
	@Test
	public void testSetAgility() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,facade.getAgility(unit));
	}
	
	@Test
	public void testInitialToughnessTooLow() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 2, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				25 <= facade.getToughness(unit) && facade.getToughness(unit) <= 100);
	}
	
	@Test
	public void testSetToughnessTooHigh() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 250, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				1 <= facade.getToughness(unit) && facade.getToughness(unit) <= 200);
	}
	
	@Test
	public void testSetToughness() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,facade.getToughness(unit));
	}
	
	@Test
	public void testInitialWeightTooLow() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 2, 50, false);
		assertTrue("An attribute value of 2 should be replaced with a valid value",
				(facade.getStrength(unit) + facade.getAgility(unit)) / 2 <= facade.getWeight(unit) 
				&& facade.getWeight(unit) <= 100);
	}
	
	@Test
	public void testSetWeightTooHigh() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 250, 50, false);
		assertTrue("An attribute value of 250 should be replaced with a valid value",
				(facade.getStrength(unit) + facade.getAgility(unit)) / 2 <= facade.getWeight(unit) 
				&& facade.getWeight(unit) <= 200);
	}
	
	@Test
	public void testSetWeight() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertEquals("An attribute value of 50 is a valid value",
				50 ,facade.getWeight(unit));
	}
	
	@Test (expected=ModelException.class)
	public void testSetPositionMoreThan50() throws ModelException {
		facade.createUnit("TestUnit", new int[] { 1, 70, 3 }, 50, 50, 50, 50, false);
	}
	
	@Test (expected=ModelException.class)
	public void testSetPositionLessThanZero() throws ModelException {
		facade.createUnit("TestUnit", new int[] { 0, 2, -20 }, 50, 50, 50, 50, false);
	}
	
	@Test (expected=ModelException.class)
	public void testSetInitialPositionLengthTooLong() throws ModelException {
		facade.createUnit("TestUnit", new int[] { 0, 2, 3, 4}, 50, 50, 50, 50, false);
	}
	
	@Test (expected=ModelException.class)
	public void testSetInitialPositionLessLengthTooShort() throws ModelException {
		facade.createUnit("TestUnit", new int[] { 0, 2 }, 50, 50, 50, 50, false);
	}
	
	@Test 
	public void testCubeCoordinate() throws ModelException {
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		assertIntegerPositionEquals("A valid position should be accepted", 1, 2, 3, facade.getCubeCoordinate(unit));
	}
	
	@Test
	public void testInitializeHitPointsToMax() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Assert.assertEquals("valid amount initial hitpoints", 
				(int) Math.ceil(200 * (facade.getWeight(unit) / 100.0) * (facade.getToughness(unit) / 100.0)), 
				facade.getCurrentHitPoints(unit));
	}
	
	@Test
	public void testInitializeStaminaPointsToMax() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Assert.assertEquals("valid amount initial staminapoints", 
				(int) Math.ceil(200 * (facade.getWeight(unit) / 100.0) * (facade.getToughness(unit) / 100.0)), 
				facade.getCurrentStaminaPoints(unit));
	}
	
	@Test
	public void testSetOrientationBiggerThan2PI() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setOrientation(3*Math.PI);
		assert Util.fuzzyEquals(Math.PI, facade.getOrientation(unit));
	}

	@Test
	public void testSetOrientationSmallerThan0() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setOrientation(-0.5*Math.PI);
		assert Util.fuzzyEquals(1.5*Math.PI, facade.getOrientation(unit));
	}
	
	@Test(expected=ModelException.class)
	public void testValidDurationAdvanceTime() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.advanceTime(unit, 0.5);
	}
	
	/*@Test
	public void testSprintingOutOfStaminaPoints() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.startSprinting(unit);
		unit.setStaminaPoints(0);
		assertFalse("out of staminapoints, can not sprint anymore", facade.isSprinting(unit));
	}*/
	
	@Test
	public void testRestAfterMove() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.moveTo(unit, new int[] {25,25,25});
		facade.rest(unit);
		assertFalse("movement interrupterd", facade.isMoving(unit));
	}
	
	
	@Test
	public void testWorkAfterMove() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.moveTo(unit, new int[] {3,2,3});
		facade.work(unit);
		facade.advanceTime(unit,0.1);
		assertFalse("movement interrupterd", facade.isMoving(unit));
	}
	
	/*@Test
	public void testRestLessThan1HitPointInterruptedAttack() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = facade.createUnit("TestUnit", new int[] { 2, 2, 3 }, 50, 50, 50, 50, false);
		facade.rest(unit);
		unit.setHitPoints(unit.getCurrentHitPoints()-5);
		facade.fight(unit, defender);
		facade.advanceTime(unit, 0.1);
		assertFalse("stopped resting during attack", facade.isResting(unit));
	}*/
	
	/*@Test
	public void testRestNotInterruptedByWork() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.rest(unit);
		unit.setHitPoints(unit.getCurrentHitPoints()-5);
		facade.work(unit);
		facade.advanceTime(unit, 0.1);
		assertTrue("rest not interrupted by work", facade.isResting(unit));
	}*/
	
	/*@Test
	public void testRestStopRestingWhenFullyLoaded() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.rest(unit);
		unit.setHitPoints(unit.getMaxHitPoints());
		unit.setStaminaPoints(unit.getMaxStaminaPoints());
		facade.advanceTime(unit, 0.1);
		assertFalse("stop resting when fully loaded points", facade.isResting(unit));
	}*/
	
	@Test
	public void testWorkInterruptedByRest() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.work(unit);
		facade.rest(unit);
		facade.advanceTime(unit,0.1);
		assertFalse("stop working and start resting", facade.isWorking(unit));
	}
	
	@Test
	public void testWorkInterruptedByAttack() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = facade.createUnit("TestUnit", new int[] { 2, 2, 3 }, 50, 50, 50, 50, false);
		facade.work(unit);
		facade.fight(unit,defender);
		facade.advanceTime(unit,0.1);
		assertFalse("stop working and start attacking", facade.isWorking(unit));
	}
	
	@Test
	public void testWorkNotInterruptedByMoving() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.work(unit);
		facade.moveTo(unit, new int[] {5,5,5});
		facade.advanceTime(unit,0.1);
		assertTrue("keep working and don't move", facade.isWorking(unit));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetCurrentSpeedSmallerThanZero() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setCurrentSpeed(-5);
	}
	
	@Test
	public void testMoveToAdjacent() throws ModelException{
		IFacade facade = new Facade();
		Unit unit = facade.createUnit("TestUnit", new int[] { 5, 5, 3 }, 50, 50, 50, 50, false);
		facade.moveToAdjacent(unit, 0, 1, 0);
		double speed = facade.getCurrentSpeed(unit);
		double distance = Math.sqrt(2);
		double time = distance / speed;
		advanceTimeFor(facade, unit, time, 0.1);
		assertArrayEquals("Moved to adjacent cube", new double[]{5.5,6.5,3.5}, 
				facade.getPosition(unit), Util.DEFAULT_EPSILON);
	}
	
	/**
	 * Helper method to advance time for the given unit by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(IFacade facade, Unit unit, double time, double step) throws ModelException {
		int n = (int) (time / step);
		for (int i = 0; i < n+1; i++)
			facade.advanceTime(unit, step);
		facade.advanceTime(unit, time - n * step);
	}
	
	
	
	@Test
	public void testIsMoving() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.setCurrentSpeed(6);
		assert facade.isMoving(unit);
	}
	
	@Test
	public void testIsSprinting() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.moveToAdjacent(unit, 0, 1, 0);
		facade.advanceTime(unit, 0.1);
		facade.startSprinting(unit);
		assert facade.isSprinting(unit);
		}
	
	@Test
	public void testIsNotSprinting() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.startSprinting(unit);
		facade.stopSprinting(unit);
		assert !facade.isSprinting(unit);
		}
	
	@Test
	public void testMoveTo() throws ModelException{
		IFacade facade = new Facade();
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.moveTo(unit, new int[] {3,4,3});
		double speed = facade.getCurrentSpeed(unit);
		double distance = 2*Math.sqrt(2);
		double time = distance / speed;
		advanceTimeFor(facade, unit, time, 0.1);
		assertArrayEquals("arrived at chosen position", new double[]{3.5,4.5,3.5}, 
				facade.getPosition(unit), Util.DEFAULT_EPSILON);	}
	
		
	@Test
	public void testWork() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.work();
		assertTrue("if unit is working: true", facade.isWorking(unit));
	}
	
	@Test
	public void testFight() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = facade.createUnit("TestUnit", new int[] { 2, 2, 3 }, 50, 50, 50, 50, false);
		unit.fight(defender);
		assertTrue("unit is attacking defender", facade.isAttacking(unit));
	}
	
	@Test
	public void testFightToFarApart() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		Unit defender = facade.createUnit("TestUnit", new int[] { 6, 2, 3 }, 50, 50, 50, 50, false);
		unit.fight(defender);
		assertFalse("unit is attacking defender", facade.isAttacking(unit));
	}
	
	@Test
	public void testRest() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		unit.rest();
		assertTrue("if unit is resting: true", facade.isResting(unit));
	}
	
	@Test
	public void testDefaultBehaviorTrue() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.setDefaultBehaviorEnabled(unit, true);
		assertTrue("default behavior enabled", facade.isDefaultBehaviorEnabled(unit));
	}
	
	@Test
	public void testDefaultBehaviorFalse() throws ModelException{
		Unit unit = facade.createUnit("TestUnit", new int[] { 1, 2, 3 }, 50, 50, 50, 50, false);
		facade.setDefaultBehaviorEnabled(unit, false);
		assertFalse("default behavior dissbled", facade.isDefaultBehaviorEnabled(unit));
	}
	
}