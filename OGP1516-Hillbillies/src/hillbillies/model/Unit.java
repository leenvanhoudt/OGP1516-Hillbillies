package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import ogp.framework.util.Util;

// TODO alle commentaar formeel + informeel
// TODO formele commentaar bij gesplitste advance time
// TODO testen schrijven! ook oude methodes beter: 
		//advance time testen + attack + defend + .... testen op unit en niet facade

//vragen:
// documentation postconditions en niet perse if's
// facade try catch

/**
 * A class of Hillbillies and their basic movements, work, attack and rest
 * motions.
 * 
 * @author Laura Vranken & Leen Van Houdt, 2e bach Ingenieurswetenschappen:
 *         Objectgericht Programmeren link code repository:
 *         https://github.com/leenvanhoudt/OGP1516-Hillbillies
 * 
 * @invar The name of each unit must be a valid name for any unit. |
 *        isValidName(getName())
 * @invar The strength of each unit must be a valid strength for any unit. |
 *        isValidStrength(getStrength())
 * @invar The agility of each unit must be a valid agility for any unit. |
 *        isValidAgility(getAgility())
 * @invar The toughness of each unit must be a valid toughness for any unit. |
 *        isValidToughness(getToughness())
 * @invar The weight of each unit must be a valid weight for any unit. |
 *        isValidWeight(getWeight())
 * @invar The position of each unit must be a valid position for any unit. |
 *        isValidPosition(getPosition())
 * @invar The hitPoints of each unit must be a valid hitPoints for any unit. |
 *        isValidHitPoints(getHitPoints())
 * @invar The staminaPoints of each unit must be a valid staminaPoints for any
 *        unit. | isValidStaminaPoints(getStaminaPoints())
 * @invar The orientation of each unit must be a valid orientation for any unit.
 *        | isValidOrientation(getOrientation())
 * @invar The experiencePoints of each unit must be a valid experiencePoints for
 *        any unit. | isValidExperiencePoints(getExperiencePoints())
 */
public class Unit {
	/**
	 * 
	 * @param name
	 *            The name of the hillbilly.
	 * @param initialPosition
	 *            The position where the hillbilly is placed at the start of the
	 *            game.
	 * @param weight
	 *            The weight of the hillbilly.
	 * @param agility
	 *            The agility of the hillbilly.
	 * @param strength
	 *            The strength of the hillbilly.
	 * @param toughness
	 *            The toughness of the hillbilly.
	 * @param enableDefaultBehavior
	 *            Check if default behavior is enabled.
	 *
	 * @pre ... The given number of hitPoints must be a valid number of
	 *      hitPoints for any unit. | isValidHitPoints(hitPoints)
	 * @pre ... The given number of staminaPoints must be a valid number of
	 *      staminaPoints for any unit. | isValidStaminaPoints(staminaPoints)
	 * @post ... If the given strength is a valid strength for any unit, the
	 *       strength of this new unit is equal to the given strength.
	 *       Otherwise, the strength of this new unit is equal to
	 *       MIN_INITIAL_VALUE. | if (isValidStrength(strength)) | then
	 *       new.getStrength() == strength | else new.getStrength() ==
	 *       MIN_INITIAL_VALUE
	 * @post ... If the given agility is a valid agility for any unit, the
	 *       agility of this new unit is equal to the given agility. Otherwise,
	 *       the agility of this new unit is equal to MIN_INITIAL_VALUE. | if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility | else
	 *       new.getAgility() == MIN_INITIAL_VALUE
	 * @post ... If the given toughness is a valid toughness for any unit, the
	 *       toughness of this new unit is equal to the given toughness.
	 *       Otherwise, the toughness of this new unit is equal to
	 *       MIN_INITIAL_VALUE. | if (isValidToughness(toughness)) | then
	 *       new.getToughness() == toughness | else new.getToughness() ==
	 *       MIN_INITIAL_VALUE
	 * @post ... If the given weight is a valid weight for any unit, the weight
	 *       of this new unit is equal to the given weight. Otherwise, the
	 *       weight of this new unit is equal to MAX_INITIAL_VALUE. | if
	 *       (isValidWeight(weight)) | then new.getWeight() == weight | else
	 *       new.getWeight() == MAX_INITIAL_VALUE
	 * @post ... The number of hitPoints of this new unit is equal to the
	 *       maximum value of hitPoints. | new.getHitPoints() == hitPoints
	 * @post ... The number of staminaPoints of this new unit is equal to the
	 *       maximum value of staminaPoints. | new.getStaminaPoints() ==
	 *       staminaPoints
	 * @effect ... The name of this new unit is set to the given name. |
	 *         this.setName(name)
	 * @effect ... The position of this new unit is set to the
	 *         positionCenterCube, which is equal to a double[] of the given
	 *         initialPosition. | this.setPosition(positionCenterCube)
	 * @throws IllegalArgumentException
	 *             ... if the name is not a valid name. | (! isValidName(name))
	 * @throws IllegalArgumentException
	 *             ... if the initial position is not in the playfield. | (!
	 *             isValidPosition(positionCubeCenter)
	 * 
	 */
	public Unit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enabletBehavior) throws IllegalArgumentException {
		if (!isValidName(name))
			throw new IllegalArgumentException();
		setName(name);
		if (isValidInitialStrength(strength))
			setStrength(strength);
		else {
			setStrength(MIN_INITIAL_VALUE);
		}
		if (isValidInitialAgility(agility))
			setAgility(agility);
		else {
			setAgility(MIN_INITIAL_VALUE);
		}
		if (isValidInitialToughness(toughness))
			setToughness(toughness);
		else {
			setToughness(MIN_INITIAL_VALUE);
		}
		if (isValidInitialWeight(weight))
			setWeight(weight);
		else {
			setWeight(MAX_INITIAL_VALUE);
		}
		if (initialPosition.length == 3) {
			double[] positionCenterCube = new double[] { initialPosition[0] + LC / 2, initialPosition[1] + LC / 2,
					initialPosition[2] + LC / 2 };
			if (!isValidPosition(positionCenterCube)) {
				throw new IllegalArgumentException();
			}
			setPosition(positionCenterCube);
		} else {
			throw new IllegalArgumentException();
		}

		setHitPoints(this.getMaxHitPoints());
		setStaminaPoints(this.getMaxStaminaPoints());
	}

	/**
	 * Constant value referencing to the length of a cube.
	 */
	private static final double LC = 1;

	/**
	 * Return the name of this unit.
	 */
	@Basic
	@Raw
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether the given name is a valid name for any unit.
	 * 
	 * @param name
	 *            The name to check.
	 * @return ... | result is true if the name consists only of letters,
	 *         spaces, single quotes or double quotes and the first letter has
	 *         to be a capital letter.
	 * 
	 */
	public static boolean isValidName(String name) {
		for (int i = 0; i < name.length(); i++) {
			if (!Character.isLetter(name.charAt(i)) && !Character.isSpaceChar(name.charAt(i))
					&& !String.valueOf(name.charAt(i)).equals("'") && !String.valueOf(name.charAt(i)).equals("\"")) {
				return false;
			}
		}
		return name.length() >= 2 && Character.isUpperCase(name.charAt(0));
	}

	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param name
	 *            The new name for this unit.
	 * @post The name of this new unit is equal to the given name. |
	 *       new.getName() == name
	 * @throws IllegalArgumentException
	 *             The given name is not a valid name for any unit. |
	 *             !isValidName(getName())
	 */
	@Raw
	public void setName(String name) throws IllegalArgumentException {
		if (!isValidName(name))
			throw new IllegalArgumentException();
		this.name = name;
	}

	/**
	 * Variable registering the name of this unit.
	 */
	private String name;

	/**
	 * Constant values limiting the strength, agility and toughness.
	 */
	public static final int MIN_VALUE = 1;
	public static final int MAX_VALUE = 200;
	public static final int MIN_INITIAL_VALUE = 25;
	public static final int MAX_INITIAL_VALUE = 100;

	/**
	 * Return the strength of this unit.
	 */
	@Basic
	@Raw
	public int getStrength() {
		return this.strength;
	}

	/**
	 * Check whether the given strength is a valid strength for any unit.
	 * 
	 * @param strength
	 *            The strength to check.
	 * @return ... | result == (strength >= MIN_VALUE && strength <= MAX_VALUE)
	 */
	public static boolean isValidStrength(int strength) {
		return (strength >= MIN_VALUE && strength <= MAX_VALUE);
	}

	/**
	 * Check whether the given strength is a valid initial strength for any
	 * unit.
	 * 
	 * @param strength
	 *            The strength to check.
	 * @return ... | result == (strength >= MIN_INITIAL_VALUE && strength <=
	 *         MAX_INITIAL_VALUE)
	 */
	public static boolean isValidInitialStrength(int strength) {
		return (strength >= MIN_INITIAL_VALUE && strength <= MAX_INITIAL_VALUE);
	}

	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param strength
	 *            The new strength for this unit.
	 * @post ... If the given strength is a valid strength for any unit, the
	 *       strength of this new unit is equal to the given strength. Otherwise
	 *       it will be set equal to the max/min-value, it exceeds. | if
	 *       (isValidStrength(strength)) | then new.getStrength() == strength |
	 *       else if (strength < MIN_VALUE) | then new.getStrength() ==
	 *       MIN_VALUE | else if (strength > MAX_VALUE) | then new.getStrength()
	 *       == MAX_VALUE
	 */
	@Raw
	public void setStrength(int strength) {
		if (isValidStrength(strength))
			this.strength = strength;
		else if (strength < MIN_VALUE)
			this.strength = MIN_VALUE;
		else if (strength > MAX_VALUE)
			this.strength = MAX_VALUE;
	}

	/**
	 * Variable registering the strength of this unit.
	 */
	private int strength;

	/**
	 * Return the agility of this unit.
	 */
	@Basic
	@Raw
	public int getAgility() {
		return this.agility;
	}

	/**
	 * Check whether the given agility is a valid agility for any unit.
	 * 
	 * @param agility
	 *            The agility to check.
	 * @return ... | result == (agility >= MIN_VALUE && agility <= MAX_VALUE)
	 */
	public static boolean isValidAgility(int agility) {
		return (agility >= MIN_VALUE && agility <= MAX_VALUE);
	}

	/**
	 * Check whether the given agility is a valid initial agility for any unit.
	 * 
	 * @param agility
	 *            The agility to check.
	 * @return ... |result == (agility >= MIN_INITIAL_VALUE && agility <=
	 *         MAX_INITIAL_VALUE)
	 */
	public static boolean isValidInitialAgility(int agility) {
		return (agility >= MIN_INITIAL_VALUE && agility <= MAX_INITIAL_VALUE);
	}

	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param agility
	 *            The new agility for this unit.
	 * @post ... If the given agility is a valid agility for any unit, the
	 *       agility of this new unit is equal to the given agility. Otherwise
	 *       it will be set equal to the max/min-value, it exceeds. | if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility | else
	 *       if (agility < MIN_VALUE) | then new.getAgility() == MIN_VALUE |
	 *       else if (agility > MAX_VALUE) | then new.getAgility() == MAX_VALUE
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidStrength(agility))
			this.agility = agility;
		else if (agility < MIN_VALUE)
			this.agility = MIN_VALUE;
		else if (agility > MAX_VALUE)
			this.agility = MAX_VALUE;
	}

	/**
	 * Variable registering the agility of this unit.
	 */
	private int agility;

	/**
	 * Return the toughness of this unit.
	 */
	@Basic
	@Raw
	public int getToughness() {
		return this.toughness;
	}

	/**
	 * Check whether the given toughness is a valid toughness for any unit.
	 * 
	 * @param toughness
	 *            The toughness to check.
	 * @return ... | result == (toughness >= MIN_VALUE && toughness <=
	 *         MAX_VALUE)
	 */
	public static boolean isValidToughness(int toughness) {
		return (toughness >= MIN_VALUE && toughness <= MAX_VALUE);
	}

	/**
	 * Check whether the given toughness is a valid initial toughness for any
	 * unit.
	 * 
	 * @param toughness
	 *            The toughness to check.
	 * @return ... | result == (toughness >= MIN_INITIAL_VALUE && toughness <=
	 *         MAX_INITIAL_VALUE)
	 */
	public static boolean isValidInitialToughness(int toughness) {
		return (toughness >= MIN_INITIAL_VALUE && toughness <= MAX_INITIAL_VALUE);
	}

	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param toughness
	 *            The new toughness for this unit.
	 * @post ... If the given toughness is a valid toughness for any unit, the
	 *       toughness of this new unit is equal to the given toughness.
	 *       Otherwise it will be set equal to the max/min-value, it exceeds. |
	 *       if (isValidToughness(toughness)) | then new.getToughness() ==
	 *       toughness | else if (toughness < MIN_VALUE) | then
	 *       new.getToughness() == MIN_VALUE | else if (toughness > MAX_VALUE) |
	 *       then new.getToughness() == MAX_VALUE
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidStrength(toughness))
			this.toughness = toughness;
		else if (toughness < MIN_VALUE)
			this.toughness = MIN_VALUE;
		else if (toughness > MAX_VALUE)
			this.toughness = MAX_VALUE;
	}

	/**
	 * Variable registering the toughness of this unit.
	 */
	private int toughness;

	/**
	 * Return the weight of this unit.
	 */
	@Basic
	@Raw
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Check whether the given weight is a valid weight for any unit.
	 * 
	 * @param weight
	 *            The weight to check.
	 * @return ... | result == (weight >= (this.getStrength() +
	 *         this.getAgility()) /2 && weight <= MAX_VALUE)
	 */
	public boolean isValidWeight(int weight) {
		return weight >= (this.getStrength() + this.getAgility()) / 2 && weight <= MAX_VALUE;
	}

	/**
	 * Check whether the given weight is a valid initial weight for any unit.
	 * 
	 * @param weight
	 *            The weight to check.
	 * @return ... | result == (weight >= (this.getStrength() +
	 *         this.getAgility()) /2 && weight <= MAX_INITIAL_VALUE)
	 */
	public boolean isValidInitialWeight(int weight) {
		return (weight >= (this.getStrength() + this.getAgility()) / 2 && weight <= MAX_INITIAL_VALUE);
	}

	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param weight
	 *            The new weight for this unit.
	 * @post ... If the given weight is a valid weight for any unit, the weight
	 *       of this new unit is equal to the given weight. Otherwise it will be
	 *       set equal to the max/min-value, it exceeds. | if
	 *       (isValidWeight(weight)) | then new.getWeight() == weight | else if
	 *       (weight < MIN_VALUE) | then new.getWeight() == MIN_VALUE; | else if
	 *       (weight > MAX_VALUE) | then new.getWeight() == MAX_VALUE;
	 */
	@Raw
	public void setWeight(int weight) {
		if (isValidStrength(weight))
			this.weight = weight;
		else if (weight < MIN_VALUE)
			this.weight = MIN_VALUE;
		else if (weight > MAX_VALUE)
			this.weight = MAX_VALUE;
	}

	/**
	 * Variable registering the weight of this unit.
	 */
	private int weight;

	/**
	 * Return the position of this unit.
	 */
	@Basic
	@Raw
	public double[] getPosition() {
		return this.position;
	}

	/**
	 * Check whether the given position is a valid position for any unit.
	 * 
	 * @param position
	 *            The position to check.
	 * @return ... | result is true if the position has 3 coordinates, which are
	 *         equal and larger than 0 and smaller than 50.
	 */
	public boolean isValidPosition(double[] position) {
		//TODO checken overal waar valid position gecheckt wordt of position geset wordt, of het passable is
		if (position.length == 3) {
			for (double coordinate : position) {
				if (coordinate >= 50 || coordinate < 0)
					return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Set the position of this unit to the given position.
	 * 
	 * @param position
	 *            The new position for this unit.
	 * @post ... The position of this new unit is equal to the given position. |
	 *       new.getPosition() == position
	 * @throws IllegalArgumentException
	 *             The given position is not a valid position for any unit. |
	 *             !isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(double[] position) throws IllegalArgumentException {
		if (!isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}

	/**
	 * Variable registering the position of this unit.
	 */
	private double[] position;

	/**
	 * Return the cube position of this unit. This is the top-left-back-corner
	 * of the cube.
	 * 
	 * @return cubePosition
	 */
	public int[] getCubeCoordinate() {
		int xCoordinate = (int) Math.floor(this.getPosition()[0]);
		int yCoordinate = (int) Math.floor(this.getPosition()[1]);
		int zCoordinate = (int) Math.floor(this.getPosition()[2]);
		int[] cubePosition = new int[] { xCoordinate, yCoordinate, zCoordinate };
		return cubePosition;
	}

	/**
	 * Return the maximum amount of hitPoints of this unit. This is calculated
	 * by the formula (200 * weight/100 * toughness/100) and rounded up to the
	 * next integer.
	 * 
	 * @return ... | result == (int) Math.ceil(200 * (this.getWeight() / 100.0)
	 *         * (this.getToughness() / 100.0));
	 */
	public int getMaxHitPoints() {
		return (int) Math.ceil(200 * (this.getWeight() / 100.0) * (this.getToughness() / 100.0));
	}

	/**
	 * Return the current amount of hitPoints of this unit. When it exceeds the
	 * boundaries (0 and this.getMaxHitPoints()), it will be set to the exceeded
	 * boundary.
	 * 
	 * @return ... | result == | if(this.getHitPoints() <0) | then return 0; |
	 *         else if (this.getHitPoints() > this.getMaxHitPoints()) | return
	 *         this.getMaxHitPoints(); | else | return this.getHitPoints();
	 */
	public int getCurrentHitPoints() {
		if (this.getHitPoints() < 0)
			return 0;
		else if (this.getHitPoints() > this.getMaxHitPoints())
			return this.getMaxHitPoints();
		else
			return this.getHitPoints();
	}

	/**
	 * Return the hitPoints of this unit.
	 */
	@Basic
	@Raw
	public int getHitPoints() {
		return this.hitPoints;
	}

	/**
	 * Check whether the given hitPoints is a valid hitPoints for any unit. It
	 * is valid when it doesn't exceed the boundaries ( 0 and
	 * this.getMaxHitPoints()).
	 * 
	 * @param hitPoints
	 *            The hitPoints to check.
	 * @return ... | result == (hitPoints <= this.getMaxHitPoints() && hitPoints
	 *         >= 0)
	 */
	public boolean isValidHitPoints(int hitPoints) {
		return (hitPoints <= this.getMaxHitPoints() && hitPoints >= 0);
	}

	/**
	 * Set the hitPoints of this unit to the given hitPoints.
	 * 
	 * @param hitPoints
	 *            The new hitPoints for this unit.
	 * @pre ... The given hitPoints must be a valid hitPoints for any unit. |
	 *      isValidHitpoints(hitPoints)
	 * @post ... The hitPoints of this unit is equal to the given hitPoints. |
	 *       new.getHitPoints() == hitPoints
	 */
	@Raw
	private void setHitPoints(int hitPoints) {
		assert isValidHitPoints(hitPoints);
		this.hitPoints = hitPoints;
	}

	/**
	 * Variable registering the hitPoints of this unit.
	 */
	private int hitPoints;

	/**
	 * Return the maximum amount of StaminaPoints of this unit. This is
	 * calculated by the formula (200 * weight/100 * toughness/100) and rounded
	 * up to the next integer.
	 * 
	 * @return ... | result == (int) Math.ceil(200 * (this.getWeight() / 100.0)
	 *         * (this.getToughness() / 100.0));
	 */
	public int getMaxStaminaPoints() {
		return (int) Math.ceil(200 * (this.getWeight() / 100.0) * (this.getToughness() / 100.0));
	}

	/**
	 * Return the current amount of staminaPoints of this unit. When it exceeds
	 * the boundaries (0 and this.getMaxStaminaPoints()), it will be set to the
	 * exceeded boundary.
	 * 
	 * @return ... | result == | if(this.getStaminaPoints() <0) | then return 0;
	 *         | else if (this.getStaminaPoints() > this.getMaxStaminaPoints())
	 *         | return this.getMaxStaminaPoints(); | else | return
	 *         this.getStaminaPoints();
	 */
	public int getCurrentStaminaPoints() {
		if (this.getStaminaPoints() < 0) {
			return 0;
		} else if (this.getStaminaPoints() > this.getMaxStaminaPoints())
			return this.getMaxStaminaPoints();
		else {
			return this.getStaminaPoints();
		}
	}

	/**
	 * Return the staminaPoints of this unit.
	 */
	@Basic
	@Raw
	public int getStaminaPoints() {
		return this.staminaPoints;
	}

	/**
	 * Check whether the given staminaPoints is a valid staminaPoints for any
	 * unit. It is valid when it doesn't exceed the boundaries (0 and
	 * this.getMaxStaminaPoints()).
	 * 
	 * @param staminaPoints
	 *            The staminaPoints to check.
	 * @return ... | result == (staminaPoints <= this.getMaxStaminaPoints() &&
	 *         staminaPoints >= 0)
	 */
	public boolean isValidStaminaPoints(int staminaPoints) {
		return (staminaPoints <= this.getMaxStaminaPoints() && staminaPoints >= 0);
	}

	/**
	 * Set the staminaPoints of this unit to the given staminaPoints.
	 * 
	 * @param staminaPoints
	 *            The new staminaPoints for this unit.
	 * @pre ... The given staminaPoints must be a valid staminaPoints for any
	 *      unit. | isValidStaminaPoints(staminaPoints)
	 * @post ... The staminaPoints of this unit is equal to the given
	 *       staminaPoints. | new.getStaminaPoints() == staminaPoints
	 */
	@Raw
	private void setStaminaPoints(int staminaPoints) {
		assert isValidStaminaPoints(staminaPoints);
		this.staminaPoints = staminaPoints;
	}

	/**
	 * Variable registering the staminaPoints of this unit.
	 */
	private int staminaPoints;

	/**
	 * Return the orientation of this unit.
	 */
	@Basic
	@Raw
	public double getOrientation() {
		return this.orientation;
	}

	/**
	 * Check whether the given orientation is a valid orientation for any unit.
	 * It is valid when it is between 0 and 2*pi.
	 * 
	 * @param orientation
	 *            The orientation to check.
	 * @return ... | result == !(orientation > 2*Math.PI || orientation < 0)
	 */
	public static boolean isValidOrientation(double orientation) {
		if (orientation > 2 * Math.PI || orientation < 0) {
			return false;
		}
		return true;
	}

	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param orientation
	 *            The new orientation for this unit.
	 * @post ... If the given orientation is a valid orientation for any unit,
	 *       the orientation of this new unit is equal to the given orientation.
	 *       |if (isValidOrientation(orientation)) | then new.getOrientation()
	 *       == orientation |else if (orientation > 2*Math.PI) | then
	 *       new.getOrientation() == orientation % (2 * Math.PI) |else if
	 *       (orientation < 0) | then new.getOrientation() == 2 * Math.PI +
	 *       (orientation % (2 * Math.PI))
	 */
	@Raw
	public void setOrientation(double orientation) {
		if (isValidOrientation(orientation))
			this.orientation = orientation;
		else if (orientation > 2 * Math.PI) {
			this.orientation = orientation % (2 * Math.PI);
		} else if (orientation < 0) {
			this.orientation = 2 * Math.PI + (orientation % (2 * Math.PI));
		}
	}

	/**
	 * Variable registering the orientation of this unit. Default value is pi/2.
	 */
	private double orientation = Math.PI / 2;

	/**
	 * Return the experiencePoints of this unit.
	 */
	@Basic
	@Raw
	public int getExperiencePoints() {
		return this.experiencePoints;
	}

	/**
	 * Check whether the given experiencePoints is a valid experiencePoints for
	 * any unit.
	 * 
	 * @param experiencePoints
	 *            The experiencePoints to check.
	 * @return | result ==
	 */
	public static boolean isValidExperiencePoints(int experiencePoints) {
		return experiencePoints >= 0;
	}

	/**
	 * Set the experiencePoints of this unit to the given experiencePoints.
	 * 
	 * @param experiencePoints
	 *            The new experiencePoints for this unit.
	 * @post The experiencePoints of this new unit is equal to the given
	 *       experiencePoints. | new.getExperiencePoints() == experiencePoints
	 * @throws ExceptionName_Java
	 *             The given experiencePoints is not a valid experiencePoints
	 *             for any unit. | !
	 *             isValidExperiencePoints(getExperiencePoints())
	 */
	@Raw
	public void setExperiencePoints(int experiencePoints) throws IllegalArgumentException {
		if (!isValidExperiencePoints(experiencePoints))
			throw new IllegalArgumentException();
		this.experiencePoints = experiencePoints;
	}

	/**
	 * Variable registering the experiencePoints of this unit.
	 */
	private int experiencePoints = 0;

	/**
	 * Constant value limiting the duration.
	 */
	public static final double MAX_DURATION = 0.2;
	
	//TODO commentaar experiencepoints
	/**
	 * Update the program every valid dt seconds. - When the unit is sprinting
	 * and his staminaPoints are 0, the unit will stop sprinting. - When the
	 * unit is sprinting, the staminaPoints will reduce with 1 every 0.1
	 * seconds. - When the unit is moving, the speed, orientation and position
	 * will be updated. - When the unit is moving a long distance, he can be
	 * interrupted by working, attacking and resting. - When the unit is
	 * resting, he will recover first hitPoints and secondly staminaPoints until
	 * he is recovered fully. He will recover at least one hitPoint, unless he
	 * is interrupted by attacking. After longer recovery, he can be interrupted
	 * by all other possible actions. - When the unit is working, he can be
	 * interrupted by attacking, resting, but not by moving. Working lasts
	 * 500/strength seconds. - When the unit is attacking, he will attack for 1
	 * second. - When the default behavior is enabled and the unit isn't doing
	 * anything, he will execute a random behavior. - The unit will rest
	 * automatically every 3 minutes. - EXPERIENCEPOINTS
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 * @throws IllegalArgumentException
	 *             The given duration is not a valid duration for any unit. |
	 *             !isValidDuration(dt)
	 * 
	 */
	public void advanceTime(double dt) throws IllegalArgumentException {
		if (this.getWorld().isValidDuration(dt)) {
			this.death(); //TODO moet je dan van unit changen?
			this.sprintingAdvanceTime(dt);
			if (this.isMoving())
				this.isMovingAdvanceTime(dt);
			if (this.isMovingTo && this.getPosition() == this.getNextPosition())
				this.isMovingToAdvanceTime(dt);
			if (this.isResting())
				this.isRestingAdvanceTime(dt);
			if (this.isWorking())
				this.isWorkingAdvanceTime(dt);
			if (this.isAttacking())
				this.isAttackingAdvanceTime(dt);
			if (this.isDefaultBehaviorEnabled())
				this.defaultBehaviorEnabledAdvanceTime(dt);
			this.falling();
			this.resting3MinutesTime += dt;
			if (this.resting3MinutesTime >= 3 * 60) {
				this.resting3MinutesTime = 0;
				this.rest();
			}
			if (this.experiencePoints >= 10)
				this.experiencePointsAdvanceTime();
		} else if (dt >= MAX_DURATION) {
			throw new IllegalArgumentException();
		}
	}
	
	public boolean isFallingPosition(int x,int y,int z){
		for (int i=-1; i<2; i++){
			for (int j=-1; j<2; j++){
				for (int k=-1; k<2; k++){
					if (x+i>=0 && x+i<15 
							&& y+j>=0 && y+j<15
							&& z+k>=0 && z+k<15){
						if (this.getWorld().getCubeType(x+i, y+j, z+k)==1 
								|| this.getWorld().getCubeType(x+i, y+j, z+k)==2){
							return false;
						}
					}						
				}
			}
		}
		return true;
	}

	public void experiencePointsAdvanceTime() {
		Random random = new Random();		
		int randomNumber = random.nextInt(3);
		
		if (randomNumber == 0){
			if (this.getAgility()!=MAX_VALUE){
				this.setAgility(this.getAgility() + 1);
			}else{
				randomNumber += 1;
			}
		}
		if (randomNumber == 1){
			if (this.getStrength()!=MAX_VALUE){
				this.setStrength(this.getStrength() + 1);
			}else{
				randomNumber += 1;
			}
		}
		if (randomNumber == 2){
			if (this.getToughness()!=MAX_VALUE)
				this.setToughness(this.getToughness() + 1);
			else if (this.getAgility()!=MAX_VALUE)
				this.setAgility(this.getAgility() + 1);
			else if (this.getStrength()!= MAX_VALUE)
				this.setStrength(this.getStrength() + 1);
		}
		this.experiencePoints -= 10;
	}

	/**
	 * Called when the unit is sprinting. When the unit is sprinting and his
	 * staminaPoints are 0, the unit will stop sprinting. When the unit is
	 * sprinting, the staminaPoints will reduce with 1 every 0.1 seconds.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 */
	public void sprintingAdvanceTime(double dt) throws IllegalArgumentException {
		if (this.isSprinting() && this.getCurrentStaminaPoints() == 0) {
			this.stopSprinting();
		} else if (this.isSprinting()) {
			this.fractionOfSprintStaminaPoint += 1 * (dt / 0.1);
			if (this.fractionOfSprintStaminaPoint >= 1) {
				this.setStaminaPoints((this.getStaminaPoints() - 1));
				this.fractionOfSprintStaminaPoint -= 1;
			}
		}
	}

	/**
	 * Called when the unit is moving to an adjacent cube. When the unit is
	 * moving, the speed, orientation and position will be updated.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 */
	public void isMovingAdvanceTime(double dt) throws IllegalArgumentException {
		double[] v = this.getMovingSpeed(dt);
		double[] fuzzyPositionUnder = new double[] { 0, 0, 0 };
		double[] fuzzyPositionUpper = new double[] { 0, 0, 0 };
		double[] distance = new double[] { 0, 0, 0 };
		for (int i = 0; i < v.length; i++) {
			distance[i] = Math.abs(v[i] * dt);
			fuzzyPositionUnder[i] = this.getNextPosition()[i] - distance[i];
			fuzzyPositionUpper[i] = this.getNextPosition()[i] + distance[i];
		}
		if (this.getPosition()[0] < fuzzyPositionUnder[0] || this.getPosition()[0] > fuzzyPositionUpper[0]
				|| this.getPosition()[1] < fuzzyPositionUnder[1] || this.getPosition()[1] > fuzzyPositionUpper[1]
				|| this.getPosition()[2] < fuzzyPositionUnder[2] || this.getPosition()[2] > fuzzyPositionUpper[2]) {
			double[] newPosition = new double[] { this.getPosition()[0] + v[0] * dt, this.getPosition()[1] + v[1] * dt,
					this.getPosition()[2] + v[2] * dt };
			this.setOrientation(Math.atan2(v[1], v[0]));
			if (isValidPosition(newPosition)&&this.getWorld().isPassable((int)Math.floor(newPosition[0]), (int)Math.floor(newPosition[1]), (int)Math.floor(newPosition[2])))
				this.setPosition(newPosition);
			else {
				this.setCurrentSpeed(0);
			}
		} else {
			this.setPosition(this.getNextPosition());
			this.setExperiencePoints(this.getExperiencePoints()+1);
			if (!this.isMovingTo)
				this.setCurrentSpeed(0);
			if(this.isFalling){
				if (this.getCurrentHitPoints()<10)
					this.setHitPoints(0);
				this.setHitPoints(this.getCurrentHitPoints()-10);
			}
		}
	}

	/**
	 * Called when the unit is moving a long distance. When the unit is moving a
	 * long distance, he can be interrupted by working, attacking and resting.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 */
	public void isMovingToAdvanceTime(double dt) throws IllegalArgumentException {
		if (this.isResting() || this.isAttacking() || this.isWorking()) {
			this.setCurrentSpeed(0);
		} else {
			if (!this.isSprinting())
				this.setCurrentSpeed(this.walkingSpeed);
			System.out.println("sub methode advance time");
			this.moveTo(this.cubeEndPosition);
		}
	}

	/**
	 * Called when the unit is resting. When the unit is resting, he will
	 * recover first hitPoints and secondly staminaPoints until he is recovered
	 * fully. He will recover at least one hitPoint, unless he is interrupted by
	 * attacking. After longer recovery, he can be interrupted by all other
	 * possible actions.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 */
	public void isRestingAdvanceTime(double dt) throws IllegalArgumentException {
		if (this.getCurrentHitPoints() - this.hitPointsBeforeRest <= 1 && this.isAttacking()) {
			this.isResting = false;
			this.setHitPoints(this.hitPointsBeforeRest);
			this.setStaminaPoints(this.staminaPointsBeforeRest);
		} else if (((this.getCurrentHitPoints() - this.hitPointsBeforeRest > 1)
				|| this.getCurrentHitPoints() == this.getMaxHitPoints())
				&& (this.isMoving() || this.isAttacking() || this.isWorking())) {
			if (!this.restAfterWork)
				this.isResting = false;
			this.restAfterWork = false;
		} else if ((this.getCurrentHitPoints() == this.getMaxHitPoints())
				&& (this.getCurrentStaminaPoints() == this.getMaxStaminaPoints())) {
			this.setPosition(this.getNextPosition());
			this.isResting = false;
		} else if ((this.getCurrentHitPoints() != this.getMaxHitPoints())) {
			this.fractionOfHitPoint += (this.getToughness() / 200.0) * (dt / 0.2);
			if (this.fractionOfHitPoint >= 1) {
				this.setHitPoints(this.getHitPoints() + 1);
				this.fractionOfHitPoint -= 1;
			}
		} else if ((this.getCurrentHitPoints() == this.getMaxHitPoints())
				&& (this.getCurrentStaminaPoints() != this.getMaxStaminaPoints())) {
			this.fractionOfStaminaPoint += (this.getToughness() / 100.0) * (dt / 0.2);
			if (this.fractionOfStaminaPoint >= 1) {
				this.setStaminaPoints(this.getStaminaPoints() + 1);
				this.fractionOfStaminaPoint -= 1;
			}
		}
	}

	/**
	 * Called when the unit is working. When the unit is working, he can be
	 * interrupted by attacking, resting, but not by moving. Working lasts
	 * 500/strength seconds.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 */
	public void isWorkingAdvanceTime(double dt) throws IllegalArgumentException {
		if (this.isAttacking() || this.isResting())
			this.isWorking = false;
		else if (this.isMoving()) {
			this.setCurrentSpeed(0);
			this.setPosition(this.getNextPosition());
		} else {
			this.workingTime += dt;
			if (this.workingTime >= (500 / this.getStrength())) {
				this.isWorking = false;
				this.resultWorkAt(this.workPosition[0], this.workPosition[1], this.workPosition[2]);
				this.workingTime = 0;
			}
		}
	}

	/**
	 * Called when the unit is attacking. When the unit is attacking, he will
	 * attack for 1 second.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 */
	public void isAttackingAdvanceTime(double dt) {
		this.attackingTime += dt;
		if (this.attackingTime >= 1) {
			this.isAttacking = false;
			this.attackingTime = 0;
		}
	}

	/**
	 * Called when the default behavior of the unit is enabled. When the default
	 * behavior is enabled and the unit isn't doing anything, he will execute a
	 * random behavior.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 */
	public void defaultBehaviorEnabledAdvanceTime(double dt) throws IllegalArgumentException {
		if (!this.isAttacking() && !this.isMoving() && !this.isWorking() && !this.isResting()) {
			this.defaultBehavior();
		}
	}

	/**
	 * Boolean referencing to check if the hillbilly want to rest when it's busy
	 * with working.
	 */
	private boolean restAfterWork = false;

	/**
	 * Constant values to keep notice of the amount of points recovered.
	 */
	private double fractionOfHitPoint = 0;
	private double fractionOfStaminaPoint = 0;
	private double fractionOfSprintStaminaPoint = 0;

	/**
	 * Return the actual moving speed, calculated by [v_x * (x' - x)/d, v_y *
	 * (y' - y)/d, v_z * (z' - z)/d], with d the distance x',y',z' the
	 * coordinates of the next position x,y,z the coordinates of the starting
	 * position v_x, v_y,v_z the speed of the movement: walking or sprinting.
	 * 
	 * @param dt
	 *            time needed to move.
	 * @return the actual moving speed v | result == v
	 */
	public double[] getMovingSpeed(double dt) {
		double d = Math.sqrt(Math.pow(this.getNextPosition()[0] - this.getPosition()[0], 2)
				+ Math.pow(this.getNextPosition()[1] - this.getPosition()[1], 2)
				+ Math.pow(this.getNextPosition()[2] - this.getPosition()[2], 2));
		if (this.isSprinting()) {
			double[] v = new double[] { this.sprintingSpeed * (this.getNextPosition()[0] - this.getPosition()[0]) / d,
					this.sprintingSpeed * (this.getNextPosition()[1] - this.getPosition()[1]) / d,
					this.sprintingSpeed * (this.getNextPosition()[2] - this.getPosition()[2]) / d };
			return v;
		} else {
			double[] v = new double[] { this.walkingSpeed * (this.getNextPosition()[0] - this.getPosition()[0]) / d,
					this.walkingSpeed * (this.getNextPosition()[1] - this.getPosition()[1]) / d,
					this.walkingSpeed * (this.getNextPosition()[2] - this.getPosition()[2]) / d };
			return v;
		}
	}

	/**
	 * Move to an adjacent cube, calculated with the given coordinates.
	 * 
	 * @param dx
	 *            The distance to the next x-coordinate.
	 * @param dy
	 *            The distance to the next y-coordinate.
	 * @param dz
	 *            The distance to the next z-coordinate.
	 * @post ... The currentSpeed of this unit is equal to the calculated speed
	 *       (walking or sprinting). | if (this.isSprinting()) | then
	 *       new.getCurrentSpeed() == this.sprintingSpeed | else
	 *       new.getCurrentSpeed() == this.walkingSpeed
	 */
	public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException {
		if ((!this.isMoving() || this.isMovingTo) && !this.isWorking()) {
			if (this.isFalling){
				this.setCurrentSpeed(this.fallingSpeed);
				this.calculateNextPosition(dx, dy, dz);
			}
			else if (this.isSprinting()) {
				this.calculateNextPosition(dx, dy, dz);
				this.calculateSpeed(this.getNextPosition());
				if (this.getWorld().isPassable((int)Math.floor(this.getNextPosition()[0]), (int)Math.floor(this.getNextPosition()[1]), (int)Math.floor(this.getNextPosition()[2]))){
					this.setCurrentSpeed(this.sprintingSpeed);
				}				
			} else {
				System.out.println("moving 1");
				this.calculateNextPosition(dx, dy, dz);
				this.calculateSpeed(this.getNextPosition()); 
				if (this.getWorld().isPassable((int)Math.floor(this.getNextPosition()[0]), (int)Math.floor(this.getNextPosition()[1]), (int)Math.floor(this.getNextPosition()[2]))){
					this.setCurrentSpeed(this.walkingSpeed);
					System.out.println("cube is passable");
				}
				else{
					System.out.println("cube is inpassable");
				}
			}
		}
	}

	/**
	 * Boolean registering whether the unit is moving to a cube far away.
	 */
	private boolean isMovingTo = false;

	/**
	 * Calculate the position of the adjacent cube where the hillbilly moves to
	 * next.
	 * 
	 * @param dx
	 *            The distance to the next x coordinate.
	 * @param dy
	 *            The distance to the next y coordinate.
	 * @param dz
	 *            The distance to the next z coordinate.
	 * 
	 * @post ... The next position of the cube center is saved in
	 *       this.nextPosition. | new.nextPosition = nextCubeCenterPosition
	 */
	public void calculateNextPosition(int dx, int dy, int dz) {
		int[] currentCubePosition = this.getCubeCoordinate();
		int[] nextCubePosition = new int[] { currentCubePosition[0] + dx, currentCubePosition[1] + dy,
				currentCubePosition[2] + dz };
		double[] nextCubeCenterPosition = new double[] { nextCubePosition[0] + LC / 2, nextCubePosition[1] + LC / 2,
				nextCubePosition[2] + LC / 2 };
		this.nextPosition = nextCubeCenterPosition;
	}

	/**
	 * Return the next position of this unit.
	 */
	@Basic
	public double[] getNextPosition() {
		return this.nextPosition;
	}

	/**
	 * Variable referencing to the next position of this unit.
	 */
	private double[] nextPosition;

	/**
	 * Calculate the baseSpeed, walkingSpeed and the sprintingSpeed depending on
	 * the nextPosition of a unit.
	 * 
	 * @param nextposition
	 *            The next position of the unit.
	 * @post ... The baseSpeed is saved in this.baseSpeed and is equal to 1.5 *
	 *       (this.getStrength() + this.getAgility()) / (200 * this.getWeight()
	 *       / 100). | new.baseSpeed = 1.5 * (this.getStrength() +
	 *       this.getAgility()) / (200 * this.getWeight() / 100)
	 * @post ... The walkingSpeed is saved in this.walkingSpeed and in case of
	 *       ascending equal to 0.5*this.baseSpeed. In case of descending, the
	 *       walkingSpeed is equal to 1.2*this.baseSpeed, otherwise it's equal
	 *       to this.baseSpeed. | if (this.getPosition()[2] - nextPosition[2] ==
	 *       -1) | then new.walkingSpeed = 0.5 * this.baseSpeed | else if
	 *       (this.getPosition()[2] - nextPosition[2] == 1) | then
	 *       new.walkingSpeed = 1.2 * this.baseSpeed | else | new.walkingSpeed =
	 *       this.baseSpeed
	 * @post ... The sprintingSpeed is saved in this.sprintingSpeed and is equal
	 *       to 2*this.walkingSpeed. | new.sprintingSpeed = 2 *
	 *       this.walkingSpeed.
	 */
	public void calculateSpeed(double[] nextPosition) {
		this.baseSpeed = 1.5 * (this.getStrength() + this.getAgility()) / (200 * this.getWeight() / 100);
		if (this.getPosition()[2] - nextPosition[2] == -1)
			this.walkingSpeed = 0.5 * this.baseSpeed;
		else if (this.getPosition()[2] - nextPosition[2] == 1)
			this.walkingSpeed = 1.2 * this.baseSpeed;
		else {
			this.walkingSpeed = this.baseSpeed;
		}
		this.sprintingSpeed = 2 * this.walkingSpeed;
	}

	/**
	 * Return the currentSpeed of this unit.
	 */
	@Basic
	public double getCurrentSpeed() {
		return this.currentSpeed;
	}

	/**
	 * Check whether the given currentSpeed is a valid currentSpeed for any
	 * unit.
	 * 
	 * @param currentspeed
	 *            The currentSpeed to check.
	 * @return ... | result == (currentSpeed >= 0)
	 */
	public static boolean isValidCurrentSpeed(double currentSpeed) {
		return (currentSpeed >= 0);
	}

	/**
	 * Set the current speed of this unit to the given speed.
	 * 
	 * @param speed
	 *            The new current speed for this unit.
	 * @post ... The current speed of this new unit is equal to the given speed.
	 *       | new.getCurrentSpeed() == speed
	 * @throws IllegalArgumentException
	 *             The given speed is not a valid speed for any unit. |
	 *             !isValidCurrentSpeed(getCurrentSpeed())
	 */
	@Raw
	public void setCurrentSpeed(double speed) throws IllegalArgumentException {
		if (!isValidCurrentSpeed(speed))
			throw new IllegalArgumentException();
		this.currentSpeed = speed;
	}

	/**
	 * Variable registering the currentSpeed of this unit.
	 */
	private double currentSpeed;

	/**
	 * Variables registering the baseSpeed, walkingSpeed and sprintingSpeed of
	 * this unit.
	 */
	private double baseSpeed;
	private double walkingSpeed;
	private double sprintingSpeed;
	private final double fallingSpeed = 3;

	/**
	 * Check if a unit is moving by comparing the current speed and 0.
	 * 
	 * @return ... | result == !Util.fuzzyEquals(this.getCurrentSpeed(), 0);
	 */
	public boolean isMoving() {
		return !Util.fuzzyEquals(this.getCurrentSpeed(), 0);
	}

	/**
	 * Set the current speed to the sprinting speed.
	 * 
	 * @post ... If the unit is already moving and has enough staminaPoints, it
	 *       starts sprinting. | if (this.isMoving() &&
	 *       this.getCurrentStaminaPoints() > 0) | then
	 *       new.currentSpeed=this.sprintingSpeed
	 */
	public void startSprinting() throws IllegalArgumentException {
		if (this.isMoving() && this.getCurrentStaminaPoints() > 0)
			this.setCurrentSpeed(this.sprintingSpeed);
	}

	/**
	 * Set the current speed back to walking speed after sprinting.
	 * 
	 * @post ... The current speed is set equal to the walking speed. |
	 *       new.currentSpeed = this.walkingSpeed
	 */
	public void stopSprinting() throws IllegalArgumentException {
		this.setCurrentSpeed(this.walkingSpeed);
	}

	/**
	 * Check if a unit is sprinting by checking if the current speed equals the
	 * sprint speed.
	 * 
	 * @return ... | result == Util.fuzzyEquals(this.getCurrentSpeed(),
	 *         this.sprintingSpeed) && this.sprintingSpeed != 0
	 */
	public boolean isSprinting() {
		return Util.fuzzyEquals(this.getCurrentSpeed(), this.sprintingSpeed) && this.sprintingSpeed != 0;
	}

	/**
	 * Path finding algorithm to calculate the path to the destination.
	 * 
	 * @param cube
	 *            The destination cube.
	 * @effect ... The unit will move to the adjacent cube, determined by the
	 *         calculated dx, dy and dz, towards the destination. |
	 *         this.moveToAdjacent(dx, dy, dz);
	 */
	public void moveTo(int[] cube) throws IllegalArgumentException {
		System.out.println("boolean move " + this.isMovingTo);
		this.cubeEndPosition = cube;
		if (!this.getWorld().isPassable(this.cubeEndPosition[0], this.cubeEndPosition[1], this.cubeEndPosition[2])){
			throw new IllegalArgumentException();
		}
		else{
			if (!this.isMovingTo){
				System.out.println("ismovingtopath");
				this.path = this.findPath();
				this.isMovingTo = true;
				System.out.println("boolean move na pathfinding " + this.isMovingTo);
			}
			if (this.getCubeCoordinate()[0] == this.cubeEndPosition[0] 
					&& this.getCubeCoordinate()[1] == this.cubeEndPosition[1]
					&& this.getCubeCoordinate()[2] == this.cubeEndPosition[2]){
				System.out.println("arrived");
				this.isMovingTo = false;
				this.setCurrentSpeed(0);
			}
			if (this.isMovingTo){
				System.out.println("move to");
				int dx = 0;
				int dy = 0;
				int dz = 0;
				System.out.println("size:"+path.size());
				
				dx = this.path.get(1).getX()-this.path.get(0).getX();
				dy = this.path.get(1).getY()-this.path.get(0).getY();
				dz = this.path.get(1).getZ()-this.path.get(0).getZ();
				this.path.remove(0);
				System.out.println(dx + " " + dy + " " + dz);
				this.moveToAdjacent(dx, dy, dz);
			}
		}
	}
	
	private ArrayList<Cube> path = new ArrayList<Cube>();
	
	public ArrayList<Cube> findPath(){
		Cube[][][] grid = new Cube[this.getWorld().getNbCubesX()][this.getWorld().getNbCubesY()][this.getWorld().getNbCubesZ()];
		for (int i=0; i<this.getWorld().getNbCubesX(); i++){
			for (int j=0; j<this.getWorld().getNbCubesY(); j++){
				for (int k=0; k<this.getWorld().getNbCubesZ(); k++){
					grid[i][j][k] = new Cube(i,j,k);
					grid[i][j][k].setHCost(this.cubeEndPosition);
				}
			}
		}
		System.out.println("find path");
		ArrayList<Cube> open = new ArrayList<Cube>();
		ArrayList<Cube> closed = new ArrayList<Cube>();
		Cube startCube = grid[this.getCubeCoordinate()[0]][this.getCubeCoordinate()[1]][this.getCubeCoordinate()[2]];
		open.add(startCube);
		Cube current = startCube;
		Cube minimum = startCube;
		
		while (!(current.getX() == this.cubeEndPosition[0])
				|| !(current.getY() == this.cubeEndPosition[1])
				|| !(current.getZ() == this.cubeEndPosition[2])){
			System.out.println("while lol");
			System.out.println("open size: "+open.size());
			for (Cube i: open){
				minimum = open.get(0);
				//System.out.println("check minimum");
				if (open.size()==1){
					current = open.get(0);
				}
				else if (i.getFCost()< minimum.getFCost() 
						|| (i.getFCost()==minimum.getFCost() && i.getHCost()<minimum.getHCost())){
					minimum = i;
					current = minimum;
				}
			}
			open.remove(current);
			closed.add(current);
			
			//aangepast dat geen falling position mag zijn en hierboven dat bij gelijke fcost, hcost gecheckt
			for (int i=-1; i<2; i++){
				for (int j=-1; j<2; j++){
					for (int k=-1; k<2; k++){
						//System.out.println("neigbours");
						if (current.getX()+i>=0 && current.getX()+i<this.getWorld().getNbCubesX() 
								&& current.getY()+j>=0 && current.getY()+j<this.getWorld().getNbCubesY() 
								&& current.getZ()+k>=0 && current.getZ()+k<this.getWorld().getNbCubesZ()){
							Cube adjacent = grid[current.getX()+i][current.getY()+j][current.getZ()+k];
							if (this.getWorld().isPassable(current.getX()+i, current.getY()+j, current.getZ()+k)
									&& !this.isFallingPosition(current.getX()+i, current.getY()+j, current.getZ()+k)){
								System.out.println("hcost:" + (current.getX()+i) +" "+(current.getY()+j) +" "+ (current.getZ()+k) +" " + adjacent.getHCost());
								if (((i==-1 || i==1) && j==0 && k==0) || ((j==-1 || j==1) && i==0 && k==0) || ((k==-1 || k==1) && j==0 && i==0)){
									this.updateCost(current, adjacent, current.getGCost()+10, open, closed);
								}
								else if (((i==-1||i==1)&&(j==-1||j==1)&&k==0)||((i==-1||i==1)&&(k==-1||k==1)&&j==0)||((k==-1||k==1)&&(j==-1||j==1)&&i==0)){
									this.updateCost(current, adjacent, current.getGCost()+14, open, closed);
								}
								else if ((i==-1||i==1)&&(j==-1||j==1)&&(k==-1||k==1)){
									this.updateCost(current, adjacent, current.getGCost()+17, open, closed);
								}	
							}
							else if (!this.getWorld().isPassable(current.getX()+i, current.getY()+j, current.getZ()+k)
									|| this.isFallingPosition(current.getX()+i, current.getY()+j, current.getZ()+k)){
								closed.add(adjacent);
							}
						}
					}
				}
			}
		}
		ArrayList<Cube> path = new ArrayList<Cube>();
		while (current.getParent()!=startCube){
			path.add(current);
			current = current.getParent();
		}
		path.add(current);
		path.add(startCube);
		Collections.reverse(path);
		System.out.println(path.get(0).getX() + " " +path.get(0).getY() + " " +path.get(0).getZ() );
		System.out.println(path.get(1).getX() + " " +path.get(1).getY() + " " +path.get(1).getZ() );
		//System.out.println(path.get(2).getX() + " " +path.get(2).getY() + " " +path.get(2).getZ() );
		return path;
	}
	
	public void updateCost(Cube current, Cube adjacent, int cost,ArrayList<Cube> open, ArrayList<Cube> closed){
		//System.out.println("update cost");
		int finalCost = adjacent.getHCost()+cost;
		if ((this.getWorld().isPassable(adjacent.getX(),adjacent.getY(),adjacent.getZ()) && !closed.contains(adjacent))
				&& (finalCost < adjacent.getFCost() || !open.contains(adjacent))){
			adjacent.setFCost(finalCost);
			adjacent.setGCost(cost);
			//System.out.println("set parent");
			adjacent.setParent(current);
			if (!open.contains(adjacent)){
				open.add(adjacent);
			}
		}
	}
	

	/**
	 * Variable referencing to the destination cube of the method moveTo(int[]
	 * cube)
	 */
	private int[] cubeEndPosition = new int[] { 0, 0, 0 };

	/**
	 * Make the unit work.
	 * 
	 * @post ... Make the unit work. | new.isWorking = true
	 */
	public void work() {
		this.isWorking = true;
	}

	/**
	 * Check if the unit is working.
	 * 
	 * @return ... | result == return this.isWorking
	 */
	public boolean isWorking() {
		return this.isWorking;
	}

	/**
	 * Boolean saving if the unit is working.
	 */
	private boolean isWorking = false;
	/**
	 * Variable keeping track of the time, spent working.
	 */
	private double workingTime = 0;

	/**
	 * The unit is part of a fight.
	 * 
	 * @param defender
	 *            The opponent of the unit that is attacking.
	 * @effect ... The unit attacks the defender and the defender defends
	 *         itself. | this.attack(defender); | defender.defend(this);
	 */
	public void fight(Unit defender) {
		if (!this.isFalling && this.getFaction()!=defender.getFaction()){
			this.attack(defender);
			defender.defend(this);
		}
	}

	/**
	 * The unit attacks another unit.
	 * 
	 * @param defender
	 *            The opponent of the unit that is attacking.
	 * @post ... The units orientation is updated so they face each other. |
	 *       new.setOrientation(Math.atan2(defender.getPosition()[1] -
	 *       this.getPosition()[1], | defender.getPosition()[0] -
	 *       this.getPosition()[0])); |
	 *       newdefender.setOrientation(Math.atan2(this.getPosition()[1] -
	 *       defender.getPosition()[1], | this.getPosition()[0] -
	 *       defender.getPosition()[0]));
	 * @post ... Make the unit attack. | new.isAttacking = true
	 */
	public void attack(Unit defender) {
		double d = Math.sqrt(Math.pow(defender.getPosition()[0] - this.getPosition()[0], 2)
				+ Math.pow(defender.getPosition()[1] - this.getPosition()[1], 2)
				+ Math.pow(defender.getPosition()[2] - this.getPosition()[2], 2));
		if (d <= Math.sqrt(2)) {
			this.setOrientation(Math.atan2(defender.getPosition()[1] - this.getPosition()[1],
					defender.getPosition()[0] - this.getPosition()[0]));
			defender.setOrientation(Math.atan2(this.getPosition()[1] - defender.getPosition()[1],
					this.getPosition()[0] - defender.getPosition()[0]));
			this.isAttacking = true;
		}
	}

	/**
	 * Check if the unit is attacking.
	 * 
	 * @return ... | result == this.isAttacking
	 */
	public boolean isAttacking() {
		return this.isAttacking;
	}

	/**
	 * Variable keeping track of the time, spent attacking.
	 */
	private double attackingTime = 0;

	/**
	 * Boolean saving if the unit is attacking.
	 */
	private boolean isAttacking = false;

	/**
	 * The unit can defend itself by dodging (with a chance of Pd) to another
	 * position or to block the attack (with a chance of Pb) or it can also fail
	 * and lose hitPoints.
	 * 
	 * @param attacker
	 *            The opponent of the defending unit.
	 * @effect ... If the unit has dodged, he will move to a random adjacent
	 *         cube. Else if the unit has lost, it loses hitPoints. | if
	 *         (random.nextInt(100) <= (Pd * 100)) | then
	 *         this.setPosition(dodgePosition) | else if (random.nextInt(100) >
	 *         (Pb * 100)) | then this.setHitPoints(this.getCurrentHitPoints() -
	 *         attacker.getStrength() / 10)
	 */
	public void defend(Unit attacker) throws IllegalArgumentException {
		double Pd = 0.20 * this.getAgility() / attacker.getAgility();
		Random random = new Random();
		if (random.nextInt(100) <= (Pd * 100)) {
			double[] dodgePos = this.searchDodgePosition();
			this.setPosition(dodgePos);
			this.setExperiencePoints(this.getExperiencePoints()+20);
		} else {
			double Pb = 0.25*((this.getStrength() + this.getAgility()) / (attacker.getStrength() 
					+ attacker.getAgility()));
			if (random.nextInt(100) > (Pb * 100)) {
				if (this.getCurrentHitPoints() >= attacker.getStrength() / 10) {
					this.setHitPoints(this.getCurrentHitPoints() - attacker.getStrength() / 10);
				}else{
					this.setHitPoints(0);
				}
				attacker.setExperiencePoints(this.getExperiencePoints()+20);
			}else{
				this.setExperiencePoints(this.getExperiencePoints()+20);
			}
		}
	}

	public double[] searchDodgePosition(){
		for (int i=-1;i<2;i++){
			for (int j=-1;j<2;j++){
				for (int k=-1;k<2;k++){
					if(this.getWorld().isPassable(this.getCubeCoordinate()[0]+i, 
							this.getCubeCoordinate()[1]+j, this.getCubeCoordinate()[2]+k)){
						double[] dodgePos = new double[]{ this.getPosition()[0] + i,
								this.getPosition()[1] + j, this.getPosition()[2]+k };
						return dodgePos;
					}
				}
			}
		}
		return new double[] {this.getPosition()[0], this.getPosition()[1], this.getPosition()[2]};
	}
	/**
	 * Make the unit rest.
	 * 
	 * @post ... The unit starts resting and stops moving. | new.isResting =
	 *       true | new.currentSpeed = 0
	 */
	public void rest() {
		if (!this.isFalling){
			this.isResting = true;
			if (this.isWorking()) {
				this.restAfterWork = true;
			}
			this.hitPointsBeforeRest = this.getCurrentHitPoints();
			this.staminaPointsBeforeRest = this.getCurrentStaminaPoints();
			this.setCurrentSpeed(0);
		}
	}

	/**
	 * Variables saving the amount of points the unit already had before it
	 * starts resting.
	 */
	private int hitPointsBeforeRest;
	private int staminaPointsBeforeRest;

	/**
	 * Check if the unit is resting.
	 * 
	 * @return ... | result == this.isResting
	 */
	public boolean isResting() {
		return this.isResting;
	}

	/**
	 * Boolean saving if the unit is resting.
	 */
	private boolean isResting = false;

	/**
	 * Variable saving the game time.
	 */
	private double resting3MinutesTime = 0;

	/**
	 * Start default behaviour.
	 * 
	 * @effect ... Set the default behaviour true. |
	 *         this.setDefaultBehaviorEnabled(true);
	 */
	public void startDefaultBehaviour() {
		this.setDefaultBehaviorEnabled(true);
	}

	/**
	 * Stop default behaviour.
	 * 
	 * @effect ... Set the default behaviour false. |
	 *         this.setDefaultBehaviorEnabled(false);
	 */
	public void stopDefaultBehaviour() {
		this.setDefaultBehaviorEnabled(false);
	}

	/**
	 * Set the default behaviour to the given value.
	 * 
	 * @param value
	 *            Value setting the default behaviour on or off.
	 * @post ... Default behaviour is set to the given value. |
	 *       new.defaultBehaviorEnabled = value
	 */
	public void setDefaultBehaviorEnabled(boolean value) {
		this.defaultBehaviorEnabled = value;
	}

	/**
	 * Check if default behavior is enabled.
	 * 
	 * @return ... | result == this.defaultBehaviorEnabled
	 */
	public boolean isDefaultBehaviorEnabled() {
		return this.defaultBehaviorEnabled;
	}

	/**
	 * Choose random task: move to a position, work, rest or sprint to a
	 * position.
	 * 
	 * @effect ... The unit will walk to a random position. |
	 *         this.moveTo(randomPosition)
	 * @effect ... The unit will work. | this.work()
	 * @effect ... The unit will rest. | this.rest()
	 * @effect ... The unit will sprint to a random position. |
	 *         this.setCurrentSpeed(this.sprintingSpeed) |
	 *         this.moveTo(randomPosition)
	 */
	public void defaultBehavior() throws IllegalArgumentException {
		Random random = new Random();
		int i = random.nextInt(4);
		int[] randomPosition = new int[] { random.nextInt(this.getWorld().getNbCubesX()), 
				random.nextInt(this.getWorld().getNbCubesY()), random.nextInt(this.getWorld().getNbCubesZ()) };
		double[] randomPosition2 = new double[] {randomPosition[0], randomPosition[1], randomPosition[2]};
		switch (i) {
		case 0:
			this.moveTo(randomPosition);
			break;
		case 1:
			if(this.getCubeCoordinate()[0] !=14)
				this.workAt(this.getCubeCoordinate()[0]+1,this.getCubeCoordinate()[1],this.getCubeCoordinate()[2]);
			break;
		case 2:
			//TODO check iets met validposition
			this.rest();
			break;
		case 3:
			this.calculateSpeed(randomPosition2);
			this.setCurrentSpeed(this.sprintingSpeed);
			this.moveTo(randomPosition);
			break;
		}
	}

	/**
	 * Boolean saving if the default behavior is enabled.
	 */
	private boolean defaultBehaviorEnabled = false;

	/**
	 * Return the faction of this unit.
	 */
	@Basic
	@Raw
	public Faction getFaction() {
		return this.faction;
	}

	public boolean isValidFaction(Faction faction) throws IllegalArgumentException {
		return faction.getNbUnitsOfFaction() < 50;
	}

	public void setFaction(Faction faction) {
		if (!isValidFaction(faction))
			throw new IllegalArgumentException();
		this.faction = faction;
	}

	private Faction faction;

	/**
	 * Return the world of this unit.
	 */
	@Basic
	@Raw
	public World getWorld() {
		return this.world;
	}
	
	public void setWorld(World world){
		this.world = world;
	}
	
	private World world;

	public boolean isAlive() {
		return (this.getHitPoints() > 0);
	}

	//TODO voorrang geven aan vallen
	public void falling(){
		if (this.isFallingPosition(this.getCubeCoordinate()[0], this.getCubeCoordinate()[1], this.getCubeCoordinate()[2])){
			this.isFalling = true;
			this.isWorking = false;
			this.isAttacking = false;
			this.isResting = false;
			this.isMovingTo = false;
			this.stopSprinting();
			this.moveToAdjacent(0, 0, -1);
		}
		else if (this.getWorld().isValidStandingPosition(this.getCubeCoordinate()[0], this.getCubeCoordinate()[1], this.getCubeCoordinate()[2])){
			this.isFalling = false;
		}
		else if (this.isFalling){
			this.moveToAdjacent(0, 0, -1);
		}
	}
	
	private boolean isFalling;
	//TODO log en boulder private maken?
	Log log;
	Boulder boulder;
	
	public void workAt(int x, int y, int z){
		if (!this.isFalling){
			this.setOrientation(Math.atan2(y - this.getPosition()[1], x - this.getPosition()[0]));
			this.work();
			this.workPosition = new int[] {x,y,z};
		}
	}
	
	private int[] workPosition;
	
	public void resultWorkAt(int x, int y, int z){
		if ((this.isCarryingLog() || this.isCarryingBoulder()) && this.getWorld().isPassable(x, y, z)){
			if (this.isCarryingLog()){
				this.isCarryingLog = false;
				this.log.setPosition(x+LC/2,y+LC/2,z+LC/2);
				this.setWeight(this.getWeight()-this.log.getLogWeight());
				this.getWorld().addLog(this.log);
			} else{
				this.isCarryingBoulder = false;
				this.boulder.setPosition(x+LC/2,y+LC/2,z+LC/2);
				this.setWeight(this.getWeight()-this.boulder.getBoulderWeight());
				this.getWorld().addBoulder(this.boulder);
			}
		}
		else if (this.getWorld().getCubeType(x, y, z)==3
				&& !this.getWorld().getBoulders().isEmpty() && this.getWorld().cubeContainsBoulder(x, y, z)
				&& !this.getWorld().getLogs().isEmpty() && this.getWorld().cubeContainsLog(x, y, z)){
			this.setWeight(this.getWeight()+1);
			this.setToughness(this.getToughness()+1);
			this.getWorld().removeLog(this.getWorld().getCubeLog(x, y, z));
			this.getWorld().removeBoulder(this.getWorld().getCubeBoulder(x, y, z));
		}
		else if (!this.getWorld().getBoulders().isEmpty() && this.getWorld().cubeContainsBoulder(x, y, z)){
			this.boulder = this.getWorld().getCubeBoulder(x, y, z);
			this.boulder.setWorld(this.getWorld());
			this.weight = (this.getWeight() + this.boulder.getBoulderWeight());
			this.getWorld().removeBoulder(this.boulder);
			this.isCarryingBoulder = true;
		}
		else if (!this.getWorld().getLogs().isEmpty() && this.getWorld().cubeContainsLog(x, y, z)){
			this.log = this.getWorld().getCubeLog(x, y, z);
			this.log.setWorld(this.getWorld());
			this.weight = (this.getWeight() + this.log.getLogWeight());
			this.getWorld().removeLog(this.log);
			this.isCarryingLog = true;
		}
		else if (this.getWorld().getCubeType(x, y, z)==2){
			List<int[]> changedCubes = this.getWorld().connectedToBorder.changeSolidToPassable(x,y,z);
			if (!changedCubes.isEmpty())
				this.getWorld().addCubesChanged(changedCubes);
			this.getWorld().setCubeType(x, y, z, 0);
			Log newlog = new Log();
			newlog.setWorld(this.getWorld());
			newlog.setPosition(x+LC/2, y+LC/2, z+LC/2);
			this.getWorld().addLog(newlog);
			this.getWorld().modelListener.notifyTerrainChanged(x, y, z);
		}
		else if (this.getWorld().getCubeType(x, y, z)==1){
			List<int[]> changedCubes = this.getWorld().connectedToBorder.changeSolidToPassable(x,y,z);
			if (!changedCubes.isEmpty())
				this.getWorld().addCubesChanged(changedCubes);
			this.getWorld().setCubeType(x, y, z, 0);
			Boulder newboulder = new Boulder();
			newboulder.setWorld(this.getWorld());
			newboulder.setPosition(x+LC/2, y+LC/2, z+LC/2);
			this.getWorld().addBoulder(newboulder);
			this.getWorld().modelListener.notifyTerrainChanged(x, y, z);
		}

		this.setExperiencePoints(this.getExperiencePoints()+10);
	}
	
	public boolean isCarryingLog(){
		return this.isCarryingLog;
	}
	
	private boolean isCarryingLog = false;
	
	public boolean isCarryingBoulder(){
		return this.isCarryingBoulder;
	}
	
	private boolean isCarryingBoulder = false;
	
	public void death(){
		if(!this.isAlive()){
			this.isFalling = false;
			this.isWorking = false;
			this.isAttacking = false;
			this.isResting = false;
			this.isMovingTo = false;
			this.stopSprinting();
			this.setCurrentSpeed(0);
			if (this.isCarryingBoulder){
				this.isCarryingBoulder = false;
				this.boulder.setPosition(this.getPosition()[0]+LC/2,this.getPosition()[1]+LC/2,this.getPosition()[2]+LC/2);
				this.setWeight(this.getWeight()-this.boulder.getBoulderWeight());
				this.getWorld().addBoulder(this.boulder);
			}
			else if (this.isCarryingLog){
				this.isCarryingLog = false;
				this.log.setPosition(this.getPosition()[0]+LC/2,this.getPosition()[1]+LC/2,this.getPosition()[2]+LC/2);
				this.setWeight(this.getWeight()-this.log.getLogWeight());
				this.getWorld().addLog(this.log);
			}
			this.getFaction().removeUnitFromFaction(this);
		}
	}
}