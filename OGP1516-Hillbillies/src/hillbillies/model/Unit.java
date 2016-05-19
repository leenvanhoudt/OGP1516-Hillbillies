package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.scheduler.MyStatement;
import hillbillies.scheduler.Task;
import hillbillies.scheduler.TaskComponents;
import ogp.framework.util.Util;
import hillbillies.model.UtilCompareList;

/**
 * A class of Hillbillies with their given characteristics.
 * They can execute multiple activities: basic movements, work, attack, rest, fall,follow
 * and die. They can also execute tasks which are scheduled per faction.
 * Also the awarding or losing of 3 sorts of points are worked out.
 * 
 * @author Laura Vranken & Leen Van Houdt, 
 * 			2e bach Ingenieurswetenschappen: Objectgericht Programmeren 
 * 			link code repository: https://github.com/leenvanhoudt/OGP1516-Hillbillies
 * 
 * @invar The name of each unit must be a valid name for any unit. 
 * 		| isValidName(getName())
 * @invar The strength of each unit must be a valid strength for any unit. 
 * 		| isValidStrength(getStrength())
 * @invar The agility of each unit must be a valid agility for any unit.
 *      | isValidAgility(getAgility())
 * @invar The toughness of each unit must be a valid toughness for any unit. 
 * 		| isValidToughness(getToughness())
 * @invar The weight of each unit must be a valid weight for any unit.
 *      | isValidWeight(getWeight())
 * @invar The position of each unit must be a valid position for any unit.
 *      | isValidPosition(getPosition())
 * @invar The hitPoints of each unit must be a valid hitPoints for any unit.
 *      | isValidHitPoints(getHitPoints())
 * @invar The staminaPoints of each unit must be a valid staminaPoints for any unit.
 *      | isValidStaminaPoints(getStaminaPoints())
 * @invar The orientation of each unit must be a valid orientation for any unit.
 *      | isValidOrientation(getOrientation())
 * @invar The experiencePoints of each unit must be a valid experiencePoints for any unit.
 *      | isValidExperiencePoints(getExperiencePoints())
 * @invar The faction of each unit must be a valid faction for any unit.
 * 		| isValidFaction(getFaction())
 * @invar the currentSpeed of each unit must be a valid currentSpeed for any unit.
 * 		| isValidCurrentSpeed(getCurrentSpeed())
 */
public class Unit {
	/**
	 * Create a new unit with the given characteristics.
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
	 *            Check if default behaviour is enabled.
	 *
	 * @pre ... 
	 * 		| The given number of hitPoints must be a valid number of hitPoints for any unit. 
	 * 		| isValidHitPoints(hitPoints)
	 * @pre ... 
	 * 		| The given number of staminaPoints must be a valid number of staminaPoints for any unit. 
	 * 		| isValidStaminaPoints(staminaPoints)
	 * @post ... 
	 * 		| If the given strength is a valid strength for any unit, the strength of this new unit is equal 
	 * 		| to the given strength. Otherwise, the strength of this new unit is equal to MIN_INITIAL_VALUE. 
	 *      | if (isValidStrength(strength)) 
	 *      |	then new.getStrength() == strength 
	 *      | else new.getStrength() == MIN_INITIAL_VALUE
	 * @post ... 
	 * 		| If the given agility is a valid agility for any unit, the agility of this new unit is equal to 
	 * 		| the given agility. Otherwise, the agility of this new unit is equal to MIN_INITIAL_VALUE. 
	 * 		| if (isValidAgility(agility)) 
	 * 		|	then new.getAgility() == agility 
	 * 		| else 
	 * 		|	new.getAgility() == MIN_INITIAL_VALUE
	 * @post ... 
	 * 		| If the given toughness is a valid toughness for any unit, the toughness of this new unit is equal
	 * 		| to the given toughness. Otherwise, the toughness of this new unit is equal to MIN_INITIAL_VALUE. 
	 * 		| if (isValidToughness(toughness)) 
	 * 		|	then new.getToughness() == toughness 
	 * 		| else 
	 * 		|	new.getToughness() == MIN_INITIAL_VALUE
	 * @post ... 
	 * 		| If the given weight is a valid weight for any unit, the weight of this new unit is equal to 
	 * 		| the given weight. Otherwise, the weight of this new unit is equal to MAX_INITIAL_VALUE. 
	 * 		| if (isValidWeight(weight)) 
	 * 		|	then new.getWeight() == weight 
	 * 		| else 
	 * 		|	new.getWeight() == MAX_INITIAL_VALUE
	 * @post ... 
	 * 		| The number of hitPoints of this new unit is equal to the maximum value of hitPoints. 
	 * 		| new.getHitPoints() == hitPoints
	 * @post ... 
	 * 		| The number of staminaPoints of this new unit is equal to the maximum value of staminaPoints. 
	 * 		| new.getStaminaPoints() == staminaPoints
	 * @post ...
	 * 		| The defaultBehavior of this new unit is equal to the given enableDefaultBehavior parameter.
	 * 		| new.isDefaultBehaviorEnabled == true
	 * @effect ... 
	 * 		| The name of this new unit is set to the given name.
	 *      | this.setName(name)
	 * @effect ... 
	 * 		| The position of this new unit is set to the positionCenterCube, which is equal to a double[] 
	 * 		| of the given initialPosition plus the half of the cube length. 
	 * 		| this.setPosition(positionCenterCube)
	 * @throws IllegalArgumentException ...
	 *      | If the name is not a valid name. 
	 *      | (! isValidName(name))
	 * @throws IllegalArgumentException ...
	 *      | If the initial position doesn't has 3 coordinates. 
	 *      | (!isValidPosition(positionCubeCenter)
	 * 
	 */
	public Unit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws IllegalArgumentException {
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
			this.position = positionCenterCube;
		} 
		else {
			throw new IllegalArgumentException();
		}
		setHitPoints(this.getMaxHitPoints());
		setStaminaPoints(this.getMaxStaminaPoints());
		this.setDefaultBehaviorEnabled(enableDefaultBehavior);
	}

	/**
	 * Constant value referencing to the length of a cube.
	 */
	public static final double LC = 1;

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
	 * @return ... 
	 * 		| Result is true if the name consists only of letters,
	 *      | spaces, single quotes or double quotes and the first letter has
	 *      | to be a capital letter.
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
	 * @post The name of this new unit is equal to the given name. 
	 * 		| new.getName() == name
	 * @throws IllegalArgumentException
	 *      | The given name is not a valid name for any unit. 
	 *      | !isValidName(getName())
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
	private static final int MIN_VALUE = 1;
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
	 * @return ... 
	 * 		| Return true if the strength has a value between the MIN_VALUE and MAX_VALUE.
	 * 		| result == (strength >= MIN_VALUE && strength <= MAX_VALUE)
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
	 * @return ... 
	 * 		| Return true if the initial strength has a value between MIN_INITIAL_VALUE and MAX_INITIAL_VALUE.
	 * 		| result == (strength >= MIN_INITIAL_VALUE && strength <= MAX_INITIAL_VALUE)
	 */
	public static boolean isValidInitialStrength(int strength) {
		return (strength >= MIN_INITIAL_VALUE && strength <= MAX_INITIAL_VALUE);
	}

	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param strength
	 *            The new strength for this unit.
	 * @post ... 
	 * 		| If the given strength is a valid strength for any unit, the strength of this new unit 
	 * 		| is equal to the given strength. Otherwise it will be set equal to the max/min-value, it exceeds.
	 * 		| if (isValidStrength(strength)) 
	 * 		|	then new.getStrength() == strength
	 *      | else if (strength < MIN_VALUE) 
	 *      |	then new.getStrength() == MIN_VALUE 
	 *      | else if (strength > MAX_VALUE) 
	 *      |	then new.getStrength() == MAX_VALUE
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
	 * @return ... 
	 * 		| Return true if the agility has a value between the MIN_VALUE and MAX_VALUE. 		
	 * 		| result == (agility >= MIN_VALUE && agility <= MAX_VALUE)
	 */
	public static boolean isValidAgility(int agility) {
		return (agility >= MIN_VALUE && agility <= MAX_VALUE);
	}

	/**
	 * Check whether the given agility is a valid initial agility for any unit.
	 * 
	 * @param agility
	 *            The agility to check.
	 * @return ... 
	 * 		| Return true if the initial strength has a value between MIN_INITIAL_VALUE and MAX_INITIAL_VALUE.
	 * 		| result == (agility >= MIN_INITIAL_VALUE && agility <= MAX_INITIAL_VALUE)
	 */
	public static boolean isValidInitialAgility(int agility) {
		return (agility >= MIN_INITIAL_VALUE && agility <= MAX_INITIAL_VALUE);
	}

	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param agility
	 *            The new agility for this unit.
	 * @post ... 
	 * 		| If the given agility is a valid agility for any unit, the agility of this new unit 
	 * 		| is equal to the given agility. Otherwise it will be set equal to the max/min-value, it exceeds. 
	 * 		| if (isValidAgility(agility)) 
	 * 		|	then new.getAgility() == agility 
	 * 		| else if (agility < MIN_VALUE) 
	 * 		|	then new.getAgility() == MIN_VALUE
	 *      | else if (agility > MAX_VALUE) 
	 *      |	then new.getAgility() == MAX_VALUE
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
	 * @return ... 
	 * 		| Return true if the toughness has a value between MIN_VALUE and MAX_VALUE.
	 * 		| result == (toughness >= MIN_VALUE && toughness <= MAX_VALUE)
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
	 * @return ... 
	 * 		| Return true if the initial toughness has a value between MIN_INITIAL_VALUE and MAX_INITIAL_VALUE.
	 * 		| result == (toughness >= MIN_INITIAL_VALUE && toughness <= MAX_INITIAL_VALUE)
	 */
	public static boolean isValidInitialToughness(int toughness) {
		return (toughness >= MIN_INITIAL_VALUE && toughness <= MAX_INITIAL_VALUE);
	}

	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param toughness
	 *            The new toughness for this unit.
	 * @post ... 
	 * 		| If the given toughness is a valid toughness for any unit, the toughness of this new unit
	 * 		| is equal to the given toughness. Otherwise it will be set equal to the max/min-value, it exceeds.
	 *      | if (isValidToughness(toughness)) 
	 *      |	then new.getToughness() == toughness 
	 *      | else if (toughness < MIN_VALUE) 
	 *      |	then new.getToughness() == MIN_VALUE 
	 *      | else if (toughness > MAX_VALUE) 
	 *      |	then new.getToughness() == MAX_VALUE
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
	 * @return ... 
	 * 		| Return true if the weight has a value between MIN_VALUE and MAX_VALUE.
	 * 		| result == (weight >= (this.getStrength() + this.getAgility()) /2 && weight <= MAX_VALUE)
	 */
	public boolean isValidWeight(int weight) {
		return weight >= (this.getStrength() + this.getAgility()) / 2 && weight <= MAX_VALUE;
	}

	/**
	 * Check whether the given weight is a valid initial weight for any unit.
	 * 
	 * @param weight
	 *            The weight to check.
	 * @return ... 
	 * 		| Return true if the initial weight has a value between MIN_INITIAL_VALUE and MAX_INITIAL_VALUE.
	 * 		| result == (weight >= (this.getStrength() + this.getAgility()) /2 && weight <= MAX_INITIAL_VALUE)
	 */
	public boolean isValidInitialWeight(int weight) {
		return (weight >= (this.getStrength() + this.getAgility()) / 2 && weight <= MAX_INITIAL_VALUE);
	}

	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param weight
	 *            The new weight for this unit.
	 * @post ... 
	 * 		| If the given weight is a valid weight for any unit, the weight of this new unit 
	 * 		| is equal to the given weight. Otherwise it will be set equal to the max/min-value, it exceeds. 
	 * 		| if (isValidWeight(weight)) 
	 * 		|	then new.getWeight() == weight 
	 * 		| else if (weight < MIN_VALUE) 
	 * 		|	then new.getWeight() == MIN_VALUE 
	 * 		| else if (weight > MAX_VALUE) 
	 * 		|	then new.getWeight() == MAX_VALUE
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
	public int weight;

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
	 * @return ... 
	 * 		| Return true if the position has 3 coordinates, which are equal and larger than 0 
	 * 		| and smaller than the maximum dimensions of the world and if the cube is passable.
	 */
	public boolean isValidPosition(double[] position) {
		if (position.length != 3 
				|| (position[0] >= this.getWorld().getNbCubesX() || position[0] < 0)
				|| (position[1] >= this.getWorld().getNbCubesY() || position[1] < 0)
				|| (position[2] >= this.getWorld().getNbCubesZ() || position[2] < 0)
				|| (!this.getWorld().isPassable((int) Math.floor(position[0]), 
						(int) Math.floor(position[1]),(int) Math.floor(position[2])))) {
			return false;
			}
		return true;
	}

	/**
	 * Set the position of this unit to the given position.
	 * 
	 * @param position
	 *            The new position for this unit.
	 * @post ... 
	 * 		| The position of this unit is equal to the given position.
	 *      | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *      | The given position is not a valid position for any unit.
	 *      | !isValidPosition(getPosition())
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
	 * Return the cube position of this unit. This is the top-left-back-corner of the cube.
	 * 
	 * @return ...
	 * 		| Return the top-left-back-corner of the cube.
	 * 		| result == cubePosition
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
	 * by the formula (200 * weight/100 * toughness/100) and rounded up to the next integer.
	 * 
	 * @return ... 
	 * 		| Return the max amount of hitPoints.
	 * 		| result == (int) Math.ceil(200 * (this.getWeight() / 100.0) * (this.getToughness() / 100.0))
	 */
	public int getMaxHitPoints() {
		return (int) Math.ceil(200 * (this.getWeight() / 100.0) * (this.getToughness() / 100.0));
	}

	/**
	 * Return the current amount of hitPoints of this unit. When it exceeds the
	 * boundaries (0 and this.getMaxHitPoints()), it will be set to the exceeded boundary.
	 * 
	 * @return ... 
	 * 		| Return the current amount of hitPoints.
	 * 		| if(this.getHitPoints() <0) 
	 * 		|	then result == 0; 
	 * 		|else if (this.getHitPoints() > this.getMaxHitPoints()) 
	 * 		|	then result == this.getMaxHitPoints(); 
	 * 		|else 
	 * 		|	then result == this.getHitPoints();
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
	 * Check whether the given hitPoints are valid hitPoints for any unit. It is valid when
	 * it doesn't exceed the boundaries (0 and this.getMaxHitPoints() ).
	 * 
	 * @param hitPoints
	 *            The hitPoints to check.
	 * @return ... 
	 * 		| Return true if the amount of hitPoints is lower than the max boundary and bigger than or
	 * 		| equal to 0.
	 * 		| result == (hitPoints <= this.getMaxHitPoints() && hitPoints >= 0)
	 */
	public boolean isValidHitPoints(int hitPoints) {
		return (hitPoints <= this.getMaxHitPoints() && hitPoints >= 0);
	}

	/**
	 * Set the hitPoints of this unit to the given hitPoints.
	 * 
	 * @param hitPoints
	 *            The new hitPoints for this unit.
	 * @pre ... 
	 * 		| The given hitPoints must be valid hitPoints for any unit.
	 *      | isValidHitpoints(hitPoints)
	 * @post ... 
	 * 		| The hitPoints of this unit are equal to the given hitPoints.
	 *      | new.getHitPoints() == hitPoints
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
	 * Return the maximum amount of staminaPoints of this unit. This is
	 * calculated by the formula (200 * weight/100 * toughness/100) and rounded up to the next integer.
	 * 
	 * @return ... 
	 * 		| Return the max amount of staminaPoints.
	 * 		| result == (int) Math.ceil(200 * (this.getWeight() / 100.0) * (this.getToughness() / 100.0))
	 */
	public int getMaxStaminaPoints() {
		return (int) Math.ceil(200 * (this.getWeight() / 100.0) * (this.getToughness() / 100.0));
	}

	/**
	 * Return the current amount of staminaPoints of this unit. When it exceeds
	 * the boundaries (0 and this.getMaxStaminaPoints()), it will be set to the exceeded boundary.
	 * 
	 * @return ... 
	 * 		| Return the current amount of staminaPoints.
	 * 		| if(this.getStaminaPoints() <0) 
	 * 		|	then result == 0;
	 *      | else if (this.getStaminaPoints() > this.getMaxStaminaPoints())
	 *      |	result == this.getMaxStaminaPoints()
	 *      | else 
	 *      |	result == this.getStaminaPoints();
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
	 * Check whether the given staminaPoints are valid staminaPoints for any unit. It is valid when 
	 * it doesn't exceed the boundaries (0 and this.getMaxStaminaPoints()).
	 * 
	 * @param staminaPoints
	 *            The staminaPoints to check.
	 * @return ... 
	 * 		| Return true if the amount of staminaPoints is lower than the max boundary and bigger than or
	 * 		| equal to 0.
	 * 		| result == (staminaPoints <= this.getMaxStaminaPoints() &&
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
	 * @pre ... 
	 * 		| The given staminaPoints must be valid staminaPoints for any unit. 
	 * 		| isValidStaminaPoints(staminaPoints)
	 * @post ... 
	 * 		| The staminaPoints of this unit are equal to the given staminaPoints. 
	 * 		| new.getStaminaPoints() == staminaPoints
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
	 * @return ... 
	 * 		| Return true if the orientation is between 0 and 2*pi.
	 * 		| result == !(orientation > 2*Math.PI || orientation < 0)
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
	 * @post ... 
	 * 		| If the given orientation is a valid orientation for any unit, the orientation of this new unit 
	 * 		| is equal to the given orientation.
	 *      | if (isValidOrientation(orientation)) 
	 *      |	then new.getOrientation() == orientation 
	 *      | else if (orientation > 2*Math.PI) 
	 *      |	then new.getOrientation() == orientation % (2 * Math.PI) 
	 *      | else if (orientation < 0) 
	 *      |	then new.getOrientation() == 2 * Math.PI + (orientation % (2 * Math.PI))
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
	 * Check whether the given experiencePoints are valid experiencePoints for
	 * any unit.
	 * 
	 * @param experiencePoints
	 *            The experiencePoints to check.
	 * @return ...
	 * 		| Return true if the amount of experiencePoints is bigger than 0.
	 * 		| result == (experiencePoints >= 0)
	 */
	public static boolean isValidExperiencePoints(int experiencePoints) {
		return experiencePoints >= 0;
	}

	/**
	 * Set the experiencePoints of this unit to the given experiencePoints.
	 * 
	 * @param experiencePoints
	 *            The new experiencePoints for this unit.
	 * @post 
	 * 		| The experiencePoints of this new unit are equal to the given experiencePoints. 
	 * 		| new.getExperiencePoints() == experiencePoints
	 * @throws IllegalArgumentException
	 *      | The given experiencePoints are not valid experiencePoints for any unit. 
	 *      | !isValidExperiencePoints(this.getExperiencePoints())
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
	 * Update the program every valid dt seconds. 
	 * - When the unit is death, the unit will stop doing other activities and disappear from the world.
	 * - When the unit is falling, he loses 10 hitPoints for every z-level that he falls 
	 * 		and falling can't be interrupted.
	 * - When the unit has received 10 experiencePoints, he will gain 1 extra point for 
	 * 		agility, toughness or strength.
	 * - When the unit is sprinting and his staminaPoints are 0, the unit will stop sprinting. 
	 * - When the unit is sprinting, the staminaPoints will reduce with 1 every 0.1 seconds.
	 * - The unit will follow an other unit until he has catched up with that unit. 
	 * - When the unit is moving, the speed, orientation and position will be updated. 
	 * - When the unit is moving a long distance, he can be interrupted by working, attacking and resting. 
	 * - When the unit is resting, he will recover first hitPoints and secondly staminaPoints until
	 * 		he is recovered fully. He will recover at least one hitPoint, unless he is interrupted by attacking
	 * 		or unless the hitPoints have already reached their maximum value. After longer recovery, 
	 * 		he can be interrupted by all other possible actions. 
	 * - When the unit is working, he can be interrupted by attacking, resting, but not by moving. 
	 * 		Working lasts 500/strength seconds. 
	 * - When the unit is attacking, he will attack for 1 second. 
	 * - When the default behaviour is enabled and the unit isn't doing anything, he will execute 
	 * 		a random behaviour. 
	 * - The unit will rest automatically every 3 minutes.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 * @throws IllegalArgumentException
	 * 		| The given duration is not a valid duration for any unit.
	 *      | !isValidDuration(dt)
	 * 
	 */
	public void advanceTime(double dt) throws IllegalArgumentException, IndexOutOfBoundsException {
		if (this.getWorld().isValidDuration(dt)) {
			this.death();
			this.falling();
			if (this.getExperiencePoints() >= 10)
				this.experiencePointsAdvanceTime();
			this.sprintingAdvanceTime(dt);
			if (this.isFollowing)
				this.followAdvanceTime();
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
			this.resting3MinutesTime += dt;
			if (this.resting3MinutesTime >= 3 * 60) {
				this.resting3MinutesTime = 0;
				this.rest();
			}
		} else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Check if the unit is alive. In that case, he possesses more than 0 hitPoints.
	 * 
	 * @return ...
	 * 		| Return true if the unit is alive
	 * 		| result == (this.getCurrentHitPoints() > 0)
	 */
	public boolean isAlive() {
		return (this.getCurrentHitPoints() > 0);
	}
	
	/**
	 * If the unit died, he will stop performing any tasks and will be removed from his faction and the world.
	 * If he was carrying a boulder or a log, he will drop it.
	 * The boulder or log will than be added to the world again.
	 * 
	 * @post ...
	 * 		| The unit terminated every activity.
	 * 		| new.isFalling == false && new.isWorking == false && new.isAttacking == false
	 *		| && new.isResting == false && new.isMovingTo == false && new.getCurrentSpeed == 0
	 *		| && new.isCarryingBoulder == false && new.isCarryingLog == false 
	 *		| && new.isFollowing == false
	 * @effect ...
	 * 		| The unit is removed from the world and faction.
	 * 		| this.getFaction().removeUnitFromFaction(this)
	 * @effect ...
	 * 		| If he was carrying a boulder or a log, it was added to the world at the position of the dead unit.
	 * 		| this.getWorld().addBoulder(this.boulder) || this.getWorld().addLog(this.log)
	 */
	private void death() throws IllegalArgumentException{
		if(!this.isAlive()){
			this.isFalling = false;
			this.isWorking = false;
			this.isFollowing = false;
			this.isAttacking = false;
			this.isResting = false;
			this.isMovingTo = false;
			this.defaultBehaviorCase3 = false;
			this.stopSprinting();
			this.setCurrentSpeed(0);
			if (this.isCarryingBoulder()){
				this.setCarryingBoulder(false);
				this.boulder.setPosition(this.getPosition()[0]+LC/2,this.getPosition()[1]+LC/2,this.getPosition()[2]+LC/2);
				this.setWeight(this.getWeight()-this.boulder.getMaterialWeight());
				this.getWorld().addBoulder(this.boulder);
			}
			else if (this.isCarryingLog()){
				this.setCarryingLog(false);
				this.log.setPosition(this.getPosition()[0]+LC/2,this.getPosition()[1]+LC/2,this.getPosition()[2]+LC/2);
				this.setWeight(this.getWeight()-this.log.getMaterialWeight());
				this.getWorld().addLog(this.log);
			}
			this.getFaction().removeUnitFromFaction(this);
		}
	}
	
	/**
	 * Check if the unit is carrying a log.
	 * 
	 * @return ...
	 * 		| Return true if the unit is carrying a log.
	 * 		| result == this.isCarryingLog
	 */
	public boolean isCarryingLog(){
		return this.isCarryingLog;
	}
	
	/**
	 * Set if the unit is carrying a log to the given value.
	 * 
	 * @param value
	 * 		the value determining if the unit is carrying a log.
	 * @post ...
	 * 		| the carrying state of the unit is set to the given value.
	 * 		| new.isCarryingLog == value
	 */
	public void setCarryingLog(boolean value){
		this.isCarryingLog = value;
	}
	/**
	 * Variable registering if a unit is carrying a log.
	 */
	private boolean isCarryingLog = false;
	
	/**
	 * Check if the unit is carrying a boulder.
	 * 
	 * @return ...
	 * 		| Return true if the unit is carrying a boulder.
	 * 		| result == this.isCarryingBoulder
	 */
	public boolean isCarryingBoulder(){
		return this.isCarryingBoulder;
	}
	
	/**
	 * Set if the unit is carrying a boulder to the given value.
	 * 
	 * @param value
	 * 		the value determining if the unit is carrying a boulder.
	 * @post ...
	 * 		| the carrying state of the unit is set to the given value.
	 * 		| new.isCarryingBoulder == value
	 */
	public void setCarryingBoulder(boolean value){
		this.isCarryingBoulder = value;
	}
	/**
	 * Variable registering if a unit is carrying a boulder.
	 */
	private boolean isCarryingBoulder = false;
	
	/**
	 * Variables creating a boulder and a log, which the unit can carry.
	 */
	public Log log;
	public Boulder boulder;
	
	/**
	 * If the unit is moving to or standing on a falling position, he will fall down to 
	 * the next standing position. When he is falling, he will stop performing any other activities.
	 * 
	 * @effect ...
	 * 		| If the units position is a falling position, he start falling and stop doing other tasks.
	 * 		| this.setCurrentSpeed(0) && this.isFalling = true && this.isWorking = false
	 *		| && this.isAttacking = false && this.isResting = false && this.isMovingTo = false
	 *		| && this.moveToAdjacent(0, 0, -1)
	 * @post ...
	 * 		| If the unit has reached a standing position, he will stop falling.
	 * 		| new.isFalling == false
	 * @effect ...
	 * 		| If the unit is already falling, but hasn't reached a standing position yet, he will keep falling.
	 * 		| this.moveToAdjacent(0,0,-1)
	 */
	private void falling() throws IllegalArgumentException{
		if (this.isFallingPosition(this.getCubeCoordinate()[0], this.getCubeCoordinate()[1], this.getCubeCoordinate()[2])){
			this.interruptTask();
			this.setCurrentSpeed(0);
			this.isFalling = true;
			this.isWorking = false;
			this.isAttacking = false;
			this.isResting = false;
			this.isMovingTo = false;
			this.defaultBehaviorCase3 = false;
			this.moveToAdjacent(0, 0, -1);
		}
		else if (this.getWorld().isValidStandingPosition(this.getCubeCoordinate()[0], this.getCubeCoordinate()[1], this.getCubeCoordinate()[2])
				&& (this.isFalling && Util.fuzzyEquals(this.getPosition()[2],this.getNextPosition()[2]))){
			this.setPosition(this.getNextPosition());
			this.isFalling = false;
		}
		else if (this.isFalling){
			this.moveToAdjacent(0, 0, -1);
		}
	}
	
	/**
	 * Variable registering if the unit is falling.
	 */
	private boolean isFalling;
	
	/**
	 * Check if a unit can stand/hang in this cube, if not it is a falling position.
	 * 
	 * @param x
	 * 		the x coordinate of the cube.
	 * @param y
	 * 		the y coordinate of the cube.
	 * @param z
	 * 		the z coordinate of the cube.
	 * @return ...
	 * 		| Return false if the unit can stand in the cube.
	 * 		| if (!this.getWorld().isPassable(x+i, y+j, z+k))
			| 	then return false
	 */
	public boolean isFallingPosition(int x,int y,int z){
		for (int i=-1; i<2; i++){
			for (int j=-1; j<2; j++){
				for (int k=-1; k<2; k++){
					if (x+i>=0 && x+i<this.getWorld().getNbCubesX() 
							&& y+j>=0 && y+j<this.getWorld().getNbCubesY()
							&& z+k>=0 && z+k<this.getWorld().getNbCubesZ()){
						if (!this.getWorld().isPassable(x+i, y+j, z+k)){
							return false;
						}
					}						
				}
			}
		}
		return true;
	}

	/**
	 * Add an extra point to agility, toughness or strength; the choice of which is random,
	 * but it can't be with one of which the maximum limit is already reached.
	 * 10 experiencePoints will be lost after adding the extra point.
	 * @post ...
	 * 		| The agility, strength or toughness is increased by one.
	 * 		| new.getAgility() == this.getAgility() + 1 || new.getStrength() == this.getStrength() + 1
	 * 		| || new.getToughness() == this.getToughness() + 1
	 * @effect ...
	 * 		| He will lose 10 experiencePoints
	 * 		| this.setExperiencePoints(this.getExperiencePoints()-10)
	 */
	private void experiencePointsAdvanceTime() throws IllegalArgumentException{
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
		this.setExperiencePoints(this.getExperiencePoints()-10);
	}

	/**
	 * Called when the unit is sprinting. If the unit is sprinting and his
	 * staminaPoints become 0, the unit will stop sprinting. When the unit is
	 * sprinting, the staminaPoints will reduce with 1 every 0.1 seconds.
	 * 
	 * @param dt
	 *         		The time between each update of the unit.
	 * @effect ...
	 * 		| If he is out of staminaPoints, he will stop sprinting
	 * 		| if (this.getCurrentStaminaPoints() == 0)
	 *		| 	then this.stopSprinting();
	 * @effect ...
	 * 		| If he is sprinting, he will lose 1 staminaPoint every 0.1 seconds.
	 * 		| this.setStaminaPoints((this.getCurrentStaminaPoints() - 1))
	 */
	private void sprintingAdvanceTime(double dt) throws IllegalArgumentException {
		if (this.isSprinting() && this.getCurrentStaminaPoints() == 0) {
			this.stopSprinting();
		} else if (this.isSprinting()) {
			this.fractionOfSprintStaminaPoint += 1 * (dt / 0.1);
			if (this.fractionOfSprintStaminaPoint >= 1) {
				this.setStaminaPoints((this.getCurrentStaminaPoints() - 1));
				this.fractionOfSprintStaminaPoint -= 1;
			}
		}
	}

	/**
	 * Called when the unit is moving to an adjacent cube. If the unit is
	 * moving, the speed, orientation and position will be updated. When the unit has reached
	 * his end position, he will get 1 experiencePoint.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 * @effect ...
	 * 		| The unit has reached his new position.
	 * 		| this.setPosition(this.getNextPosition()) 
	 * 		| this.setExperiencePoints(this.getExperiencePoints()+1)
	 * @effect ...
	 * 		| If the unit hasn't reached his end position yet, 
	 * 		| he will keep moving to a position between start and end position.
	 * 		| His orientation will be updated.
	 * 		| this.setOrientation(Math.atan2(v[1], v[0]))
	 * 		| this.setPosition(newPosition)
	 */
	private void isMovingAdvanceTime(double dt) throws IllegalArgumentException {
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
			if (isValidPosition(newPosition))
				this.setPosition(newPosition);
			else {
				this.setCurrentSpeed(0);
				this.isMovingTo = false;
				this.defaultBehaviorCase3 = false;
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
	 * @effect ...
	 * 		| If the unit is interrupted, he will stop moving.
	 * 		| if (this.isResting() || this.isAttacking() || this.isWorking())
	 *		| 	then this.setCurrentSpeed(0)
	 * @effect ...
	 * 		| If he is not interrupted, he will continue his path.
	 * 		| this.moveTo(this.cubeEndPosition)
	 */
	private void isMovingToAdvanceTime(double dt) throws IllegalArgumentException, IndexOutOfBoundsException {
		if (this.isResting() || this.isAttacking() || this.isWorking()) {
			this.setCurrentSpeed(0);
		} else {
			if (!this.isSprinting())
				this.setCurrentSpeed(this.walkingSpeed);
			this.moveTo(this.cubeEndPosition);
		}
	}

	/**
	 * Called when the unit is resting. When the unit is resting, he will
	 * recover first hitPoints and secondly staminaPoints until he has recovered
	 * fully. He will recover at least one hitPoint, unless he is interrupted by
	 * attacking. After longer recovery, he can be interrupted by all other
	 * possible actions.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 * @effect ...
	 * 		| if the unit has gained less than 1 hitPoint and is attacked, he will stop resting and recover
	 * 		| no points.
	 * 		| this.isResting = false
	 *		| this.setHitPoints(this.hitPointsBeforeRest)
	 *		| this.setStaminaPoints(this.staminaPointsBeforeRest)
	 * @post ...
	 * 		| if the unit has recovered more than 1 hitPoint or he has max hitPoints and he is interrupted,
	 * 		| he will stop resting.
	 * 		| new.isResting == false
	 * @post ...
	 * 		| if the unit has recovered all points, he will stop resting.
	 * 		| new.isResting == false
	 * @effect ...
	 * 		| The unit gains toughness/200 hitPoints every 0.2 seconds. If the amount is bigger than 1, he
	 * 		| gains a whole hitPoint at once.
	 * 		| this.setHitPoints(this.getCurrentHitPoints() + 1)
	 * @effect ...
	 * 		| The unit gains toughness/100 staminaPoints every 0.2 seconds. If the amount is bigger than 1, he
	 * 		| gains a whole staminaPoint at once.
	 * 		| this.setStaminaPoints(this.getCurrentStaminaPoints() + 1)
	 */
	private void isRestingAdvanceTime(double dt) throws IllegalArgumentException {
		if (this.getCurrentHitPoints() - this.hitPointsBeforeRest <= 1 && this.isAttacking()) {
			this.isResting = false;
			this.setHitPoints(this.hitPointsBeforeRest);
			this.setStaminaPoints(this.staminaPointsBeforeRest);
		} 
		else if (((this.getCurrentHitPoints() - this.hitPointsBeforeRest > 1)
				|| this.getCurrentHitPoints() == this.getMaxHitPoints())
				&& (this.isMoving() || this.isAttacking() || this.isWorking())) {
			if (!this.restAfterWork)
				this.isResting = false;
			this.restAfterWork = false;
		} 
		else if ((this.getCurrentHitPoints() == this.getMaxHitPoints())
				&& (this.getCurrentStaminaPoints() == this.getMaxStaminaPoints())) {
			if (this.isMovingTo)
				this.setPosition(this.getNextPosition());
			this.isResting = false;
		} 
		else if ((this.getCurrentHitPoints() != this.getMaxHitPoints())) {
			this.fractionOfHitPoint += (this.getToughness() / 200.0) * (dt / 0.2);
			if (this.fractionOfHitPoint >= 1) {
				this.setHitPoints(this.getCurrentHitPoints() + 1);
				this.fractionOfHitPoint -= 1;
			}
		} 
		else if ((this.getCurrentHitPoints() == this.getMaxHitPoints())
				&& (this.getCurrentStaminaPoints() != this.getMaxStaminaPoints())) {
			this.fractionOfStaminaPoint += (this.getToughness() / 100.0) * (dt / 0.2);
			if (this.fractionOfStaminaPoint >= 1) {
				this.setStaminaPoints(this.getCurrentStaminaPoints() + 1);
				this.fractionOfStaminaPoint -= 1;
			}
		}
	}
	
	/**
	 * Initialize an object of the class ResultWork.
	 */
	private ResultWork resultWork = new ResultWork();

	/**
	 * Called when the unit is working. If the unit is working, he can be
	 * interrupted by attacking, resting, but not by moving. Working lasts
	 * 500/strength seconds.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 * @post ...
	 * 		| If the unit is interrupted by attacking or resting, he will stop working.
	 * 		| new.isWorking == false
	 * @effect ...
	 * 		| If the unit is moving, it will be interrupted by work.
	 * 		| this.setCurrentSpeed(0)
	 * @effect ...
	 * 		| The unit will complete his work after 500/strength seconds. Then the result will be executed.
	 * 		| if (this.workingTime >= (500 / this.getStrength())) 
	 *		|	then this.isWorking = false
	 *		|		 resultWork.resultWorkAt(this.workPosition[0], this.workPosition[1], this.workPosition[2])
	 * 
	 */
	private void isWorkingAdvanceTime(double dt) throws IllegalArgumentException {
		if (this.isAttacking() || this.isResting())
			this.isWorking = false;
		else if (this.isMoving()) {
			this.setCurrentSpeed(0);
			this.setPosition(this.getNextPosition());
		} else {
			this.workingTime += dt;
			if (this.workingTime >= (500 / this.getStrength())) {
				this.isWorking = false;
				resultWork.setUnit(this);
				resultWork.resultWorkAt(this.workPosition[0], this.workPosition[1], this.workPosition[2]);
				this.workingTime = 0;
			}
		}
	}

	/**
	 * Called when the unit is attacking. If the unit is attacking, he will
	 * attack for 1 second.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 * @post ...
	 * 		| If the unit has attacked for 1 second, he will stop attacking.
	 * 		| if (this.attackingTime >= 1)
	 *		|	then new.isAttacking == false
	 */
	private void isAttackingAdvanceTime(double dt) {
		this.attackingTime += dt;
		if (this.attackingTime >= 1) {
			this.isAttacking = false;
			this.attackingTime = 0;
		}
	}

	/**
	 * Called when the default behaviour of the unit is enabled. If the default
	 * behaviour is enabled and the unit isn't doing anything, he will execute a
	 * random behaviour.
	 * 
	 * @param dt
	 *            The time between each update of the unit.
	 * @effect ...
	 * 		| If the unit isn't doing anything, he will execute defaultBehavior.
	 * 		| if (!this.isAttacking() && !this.isMoving() && !this.isWorking() && !this.isResting())
	 *		|	then this.defaultBehavior()
	 */
	private void defaultBehaviorEnabledAdvanceTime(double dt) throws IllegalArgumentException,
			IndexOutOfBoundsException {
		if (!this.isAttacking() && !this.isMoving() && !this.isWorking() && !this.isResting() && !this.isFalling
				& !this.isFollowing) {
			this.defaultBehavior(dt);
		}
	}

	/**
	 * Boolean registering if the hillbilly wants to rest when it's busy with working.
	 */
	private boolean restAfterWork = false;

	/**
	 * Constant values to keep track of the amount of points recovered.
	 */
	private double fractionOfHitPoint = 0;
	private double fractionOfStaminaPoint = 0;
	private double fractionOfSprintStaminaPoint = 0;

	/**
	 * Return the actual moving speed, calculated by 
	 * [v_x * (x' - x)/d, v_y * (y' - y)/d, v_z * (z' - z)/d], with:
	 * d the distance 
	 * x',y',z' the coordinates of the next position 
	 * x,y,z the coordinates of the starting position 
	 * v_x, v_y,v_z the speed of the movement: walking or sprinting.
	 * 
	 * @param dt
	 *            time needed to move.
	 * @return ...
	 * 		| The actual moving speed v .
	 * 		| result == v
	 */
	private double[] getMovingSpeed(double dt) {
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
	 * @post ... 
	 * 		| The currentSpeed of this unit is equal to the calculated speed (walking or sprinting). 
	 * 		| if (this.isSprinting()) 
	 * 		|	then new.getCurrentSpeed() == this.sprintingSpeed 
	 * 		| else new.getCurrentSpeed() == this.walkingSpeed
	 */
	public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException {
		if ((!this.isMoving() || this.isMovingTo) && !this.isWorking()) {
			if (this.defaultBehaviorCase3)
				this.startSprinting();
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
				this.calculateNextPosition(dx, dy, dz);
				this.calculateSpeed(this.getNextPosition()); 
				if (this.getWorld().isPassable((int)Math.floor(this.getNextPosition()[0]), (int)Math.floor(this.getNextPosition()[1]), (int)Math.floor(this.getNextPosition()[2]))){
					this.setCurrentSpeed(this.walkingSpeed);
				}
			}
		}
	}

	/**
	 * Boolean registering if the unit is moving to a cube far away.
	 */
	public boolean isMovingTo = false;

	/**
	 * Calculate the position of the adjacent cube where the hillbilly moves to next.
	 * 
	 * @param dx
	 *            The distance to the next x coordinate.
	 * @param dy
	 *            The distance to the next y coordinate.
	 * @param dz
	 *            The distance to the next z coordinate.
	 * 
	 * @post ... 
	 * 		| The next position of the cube center is saved. 
	 * 		| new.nextPosition == nextCubeCenterPosition
	 */
	private void calculateNextPosition(int dx, int dy, int dz) {
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
	 * Variable registering the next position of this unit.
	 */
	private double[] nextPosition;

	/**
	 * Calculate the baseSpeed, walkingSpeed and the sprintingSpeed depending on
	 * the nextPosition of a unit.
	 * 
	 * @param nextposition
	 *            The next position of the unit.
	 * @post ... 
	 * 		| The baseSpeed is saved in this.baseSpeed and is equal to 
	 * 		| 1.5 * (this.getStrength() + this.getAgility()) / (200 * this.getWeight()/ 100). 
	 *      | new.baseSpeed == 1.5 * (this.getStrength() + this.getAgility()) / (200 * this.getWeight() / 100)
	 * @post ... 
	 * 		| The walkingSpeed is saved in this.walkingSpeed and in case of ascending equal to 
	 * 		| 0.5*this.baseSpeed. In case of descending, the walkingSpeed is equal to 1.2*this.baseSpeed,
	 * 		| otherwise it's equal to this.baseSpeed. 
	 * 		| if (this.getPosition()[2] - nextPosition[2] == -1) 
	 * 		|	then new.walkingSpeed == 0.5 * this.baseSpeed 
	 * 		| else if(this.getPosition()[2] - nextPosition[2] == 1) 
	 * 		|	then new.walkingSpeed == 1.2 * this.baseSpeed 
	 * 		| else 
	 * 		|	new.walkingSpeed == this.baseSpeed
	 * @post ... 
	 * 		| The sprintingSpeed is saved in this.sprintingSpeed and is equal to 2*this.walkingSpeed. 
	 * 		| new.sprintingSpeed == 2 * this.walkingSpeed
	 */
	private void calculateSpeed(double[] nextPosition) {
		this.baseSpeed = 1.5 * (this.getStrength() + this.getAgility()) / (200 * this.getWeight() / 100);
		if (this.getPosition()[2] - nextPosition[2] < 0)
			this.walkingSpeed = 0.5 * this.baseSpeed;
		else if (this.getPosition()[2] - nextPosition[2] > 0)
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
	@Raw
	public double getCurrentSpeed() {
		return this.currentSpeed;
	}

	/**
	 * Check whether the given currentSpeed is a valid currentSpeed for any
	 * unit.
	 * 
	 * @param currentspeed
	 *            The currentSpeed to check.
	 * @return ... 
	 * 		| Return true if the currentSpeed is bigger than or equal to 0.
	 * 		| result == (currentSpeed >= 0)
	 */
	public static boolean isValidCurrentSpeed(double currentSpeed) {
		return (currentSpeed >= 0);
	}

	/**
	 * Set the current speed of this unit to the given speed.
	 * 
	 * @param speed
	 *            The new current speed for this unit.
	 * @post ... 
	 * 		| The current speed of this new unit is equal to the given speed.
	 *      | new.getCurrentSpeed() == speed
	 * @throws IllegalArgumentException
	 *      | The given speed is not a valid speed for any unit.
	 *      | !isValidCurrentSpeed(getCurrentSpeed())
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
	 * @return ... 
	 * 		| Return true if the currentSpeed isn't 0.
	 * 		| result == !Util.fuzzyEquals(this.getCurrentSpeed(), 0)
	 */
	public boolean isMoving() {
		return !Util.fuzzyEquals(this.getCurrentSpeed(), 0);
	}

	/**
	 * Set the current speed to the sprinting speed.
	 * 
	 * @post ... 
	 * 		| If the unit is already moving and has enough staminaPoints, it starts sprinting. 
	 * 		| if (this.isMoving() && this.getCurrentStaminaPoints() > 0) 
	 * 		|	then new.currentSpeed == this.sprintingSpeed
	 */
	public void startSprinting() throws IllegalArgumentException {
		if (!this.isFalling && this.isMoving() && this.getCurrentStaminaPoints() > 0)
			this.setCurrentSpeed(this.sprintingSpeed);
	}

	/**
	 * Set the current speed back to walking speed after sprinting.
	 * 
	 * @post ... 
	 * 		| The current speed is set equal to the walking speed.
	 *      | new.currentSpeed == this.walkingSpeed
	 */
	public void stopSprinting() throws IllegalArgumentException {
		this.setCurrentSpeed(this.walkingSpeed);
	}

	/**
	 * Check if a unit is sprinting by checking if the current speed equals the
	 * sprint speed.
	 * 
	 * @return ... 
	 * 		| Return true if the unit is sprinting.
	 * 		| result == Util.fuzzyEquals(this.getCurrentSpeed(), this.sprintingSpeed)
	 */
	public boolean isSprinting() {
		return Util.fuzzyEquals(this.getCurrentSpeed(), this.sprintingSpeed) && this.sprintingSpeed != 0
				&& !this.isFalling;
	}
	
	/**
	 * Initialize an object of the class PathFinding.
	 */
	private PathFinding pathFinding = new PathFinding();
	
	/**
	 * Make the unit move to the end position, following the path calculate by pathFinding.findPath().
	 * 
	 * @param cube
	 *            The destination cube.
	 * @effect ... 
	 * 		| The unit will move to the adjacent cube, determined by the calculated dx, dy and dz,
	 * 		| towards the destination.
	 *      | this.moveToAdjacent(dx, dy, dz)
	 * @throws IllegalArgumentException
	 * 		| If the endposition is an inpassable position or a falling position.
	 * 		| (!this.getWorld().isPassable(cubeEndPosition)
	 * 		| (this.isFallingPosition)
	 */
	public void moveTo(int[] cube) throws IllegalArgumentException {
		this.cubeEndPosition = cube;
		if (!this.getWorld().isPassable(this.cubeEndPosition[0], this.cubeEndPosition[1], this.cubeEndPosition[2])
				|| this.isFallingPosition(this.cubeEndPosition[0], this.cubeEndPosition[1], this.cubeEndPosition[2])){
			this.defaultBehaviorCase3 = false;
			this.isMovingTo = false;
			this.setCurrentSpeed(0);
			throw new IllegalArgumentException();
		}
		else{
			if (!this.isMovingTo){
				pathFinding.setUnit(this);
				try{
					this.path = pathFinding.findPath(this.cubeEndPosition);
				}catch(IndexOutOfBoundsException e){
					throw new IllegalArgumentException();
				}
				this.isMovingTo = true;
			}
			if (UtilCompareList.compareIntList(this.getCubeCoordinate(), this.cubeEndPosition)){
				this.isMovingTo = false;
				this.defaultBehaviorCase3 = false;
				this.setCurrentSpeed(0);
			}
			if (this.isMovingTo){
				int dx = 0;
				int dy = 0;
				int dz = 0;
				dx = this.path.get(1).getX()-this.path.get(0).getX();
				dy = this.path.get(1).getY()-this.path.get(0).getY();
				dz = this.path.get(1).getZ()-this.path.get(0).getZ();
				this.path.remove(0);
				this.moveToAdjacent(dx, dy, dz);
			}
		}
	}
	
	/**
	 * List registering the calculated path.
	 */
	private ArrayList<Cube> path = new ArrayList<Cube>();
	
	/**
	 * Variable registering the destination cube of the method moveTo(int[]cube)
	 */
	private int[] cubeEndPosition = new int[] { 0, 0, 0 };

	/**
	 * Make the unit work.
	 * 
	 * @post ... 
	 * 		| Make the unit work. 
	 * 		| new.isWorking == true
	 */
	public void work() {
		this.isWorking = true;
	}

	/**
	 * Check if the unit is working.
	 * 
	 * @return ... 
	 * 		| Return true if the unit is working.
	 * 		| result == this.isWorking
	 */
	public boolean isWorking() {
		return this.isWorking;
	}

	/**
	 * Boolean registering if the unit is working.
	 */
	private boolean isWorking = false;
	
	/**
	 * Variable keeping track of the time, spent working.
	 */
	private double workingTime = 0;
	
	/**
	 * Unit can work at an adjacent cube, when he is not falling. His orientation will be set also.
	 * 
	 * @param x
	 * 		the x coordinate of the cube.
	 * @param y
	 * 		the y coordinate of the cube.
	 * @param z
	 * 		the z coordinate of the cube.
	 * @effect ...
	 * 		| The unit will work and his orientation will be updated.
	 * 		| this.setOrientation(Math.atan2(y+LC/2 - this.getPosition()[1], x+LC/2 - this.getPosition()[0]))
	 *		| this.work()
	 */
	public void workAt(int x, int y, int z){
		System.out.println("work at");
		double d = Math.sqrt(Math.pow(x-this.getCubeCoordinate()[0],2)+
				Math.pow(y-this.getCubeCoordinate()[1],2)+Math.pow(z-this.getCubeCoordinate()[2],2));
		if (!this.isFalling && d<=MAX_DISTANCE_ADJACENT_CUBE ){
			this.workingTime = 0;
			this.setOrientation(Math.atan2(y+LC/2 - this.getPosition()[1], x+LC/2 - this.getPosition()[0]));
			this.work();
			this.workPosition = new int[] {x,y,z};
		}
	}
	
	/**
	 * List registering the position the unit will work at.
	 */
	private int[] workPosition;

	/**
	 * The unit is part of a fight with a unit of an other faction and who is standing on an adjacent cube.
	 * The unit can not fall at the same time.
	 * 
	 * @param defender
	 *            The opponent of the unit that is attacking.
	 * @effect ... 
	 * 		| The unit attacks the defender and the defender defends itself. 
	 *      | if(!this.isFalling && this.getFaction()!=defender.getFaction() && defender != this){
	 *      | 	then this.attack(defender) 
	 *      | 		defender.defend(this)
	 * @IllegalArgumentException ...
	 * 		| throw an exception if the unit can not attack.
	 */
	public void fight(Unit defender) throws IllegalArgumentException{
		this.interruptTask();
		if (!this.isFalling && this.getFaction()!=defender.getFaction() 
				&& defender != this){
			try{
				this.attack(defender);
				defender.defend(this);
			} catch(Throwable e){
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * The unit attacks another unit.
	 * 
	 * @param defender
	 *            The opponent of the unit that is attacking.
	 * @effect ... 
	 * 		| Update the units orientation so they face each other.
	 *      | this.setOrientation(Math.atan2(defender.getPosition()[1] - this.getPosition()[1], 	
	 *      | 	defender.getPosition()[0] - this.getPosition()[0]))
	 *      | defender.setOrientation(Math.atan2(this.getPosition()[1] - defender.getPosition()[1], 
	 *      | 	this.getPosition()[0] - defender.getPosition()[0]))
	 * @post ... 
	 * 		| Make the unit attack. 
	 * 		| new.isAttacking == true
	 * @IllegalArgumentException ...
	 * 		| throw an exception if the other unit is to far.
	 */
	private void attack(Unit defender) throws IllegalArgumentException {
		System.out.println("attacking");
		double d = Math.sqrt(Math.pow(defender.getPosition()[0] - this.getPosition()[0], 2)
				+ Math.pow(defender.getPosition()[1] - this.getPosition()[1], 2)
				+ Math.pow(defender.getPosition()[2] - this.getPosition()[2], 2));
		if (d <= MAX_DISTANCE_ADJACENT_CUBE) {
			this.setOrientation(Math.atan2(defender.getPosition()[1] - this.getPosition()[1],
					defender.getPosition()[0] - this.getPosition()[0]));
			defender.setOrientation(Math.atan2(this.getPosition()[1] - defender.getPosition()[1],
					this.getPosition()[0] - defender.getPosition()[0]));
			this.isAttacking = true;
			System.out.println("close enough");
		}else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Constant value registering the maximum distance to an adjacent cube.
	 */
	private static final double MAX_DISTANCE_ADJACENT_CUBE = Math.sqrt(Math.pow(LC, 2)*3);

	/**
	 * Check if the unit is attacking.
	 * 
	 * @return ... 
	 * 		| Return true if the unit is attacking.
	 * 		| result == this.isAttacking
	 */
	public boolean isAttacking() {
		return this.isAttacking;
	}

	/**
	 * Variable keeping track of the time, spent attacking.
	 */
	private double attackingTime = 0;

	/**
	 * Boolean registering if the unit is attacking.
	 */
	private boolean isAttacking = false;

	/**
	 * The unit can defend itself by dodging (with a chance of Pd) to another
	 * position or by blocking the attack (with a chance of Pb) or he can also fail
	 * and lose hitPoints.
	 * If the unit dodges or blocks, he will gain 20 experiencePoints. If the attacker succeeds and the
	 * defender loses hitPoints, the attacker will gain 20 experiencePoints.
	 * 
	 * @param attacker
	 *            The opponent of the defending unit.
	 * @effect ... 
	 * 		| If the unit has dodged, he will move to a random adjacent cube. 
	 * 		| If the unit lost, he loses hitPoints. 
	 *      | if(random.nextInt(100) <= (Pd * 100)) 
	 *      |	then this.setPosition(dodgePosition)
	 *      |		this.setExperiencePoints(this.getExperiencePoints()+20)
	 *      | else if (random.nextInt(100) > (Pb * 100)) 
	 *      |	then this.setHitPoints(this.getCurrentHitPoints() - attacker.getStrength() / 10)
	 *      |		attacker.setExperiencePoints(attacker.getExperiencePoints()+20)
	 *      | else if(random.nextInt(100) > (Pb * 100)
	 *      |	then this.setExperiencePoints(this.getExperiencePoints()+20)
	 */
	private void defend(Unit attacker) throws IllegalArgumentException {
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
				attacker.setExperiencePoints(attacker.getExperiencePoints()+20);
			}else{
				this.setExperiencePoints(this.getExperiencePoints()+20);
			}
		}
	}
	
	/**
	 * Search a valid dodge position.
	 * 
	 * @return
	 * 		| Return dodgePosition if valid, else return currentPosition.
	 * 		| result == dodgePos
	 */
	private double[] searchDodgePosition(){
		for (int i=-1;i<2;i++){
			for (int j=-1;j<2;j++){
				for (int k=-1;k<2;k++){
					if(this.isValidPosition(new double[] {this.getCubeCoordinate()[0]+i,		
					this.getCubeCoordinate()[1]+j, this.getCubeCoordinate()[2]+k})){
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
	 * Make the unit rest. If the unit is executing a task, the task will be interrupted.
	 * @effect ...
	 * 		| interrupt the task if the unit is executing one.
	 * 		| this.interruptTask()
	 * @post ... 
	 * 		| The unit starts resting and stops moving. 
	 * 		| new.isResting == true 
	 * 		| new.getCurrentSpeed() == 0
	 */
	public void rest() {
		this.interruptTask();
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
	 * @return ... 
	 * 		| Return true if the unit is resting.
	 * 		| result == this.isResting
	 */
	public boolean isResting() {
		return this.isResting;
	}

	/**
	 * Boolean registering if the unit is resting.
	 */
	private boolean isResting = false;

	/**
	 * Variable registering the game time.
	 */
	private double resting3MinutesTime = 0;

	/**
	 * Start default behaviour.
	 * 
	 * @effect ... 
	 * 		| Set the default behaviour true.
	 *      | this.setDefaultBehaviorEnabled(true);
	 */
	public void startDefaultBehaviour() {
		this.setDefaultBehaviorEnabled(true);
	}

	/**
	 * Stop default behaviour.
	 * 
	 * @effect ... 
	 * 		| Set the default behaviour false.
	 *      | this.setDefaultBehaviorEnabled(false);
	 */
	public void stopDefaultBehaviour() {
		this.setDefaultBehaviorEnabled(false);
	}

	/**
	 * Set the default behaviour to the given value.
	 * 
	 * @param value
	 *            Value setting the default behaviour on or off.
	 * @post ... 
	 * 		| Default behaviour is set to the given value.
	 *      | new.defaultBehaviorEnabled == value
	 */
	public void setDefaultBehaviorEnabled(boolean value) {
		this.defaultBehaviorEnabled = value;
	}

	/**
	 * Check if default behaviour is enabled.
	 * 
	 * @return ... 
	 * 		| Return true if defaultBehavior is enabled.
	 * 		| result == this.defaultBehaviorEnabled
	 */
	public boolean isDefaultBehaviorEnabled() {
		return this.defaultBehaviorEnabled;
	}
	
	/**
	 * initializing an object of the class TaskComponents.
	 */
	private TaskComponents taskComponents;

	/**
	 * If defaultbehavior is enabled, the unit will first check if there still is an unassigned
	 * task in the scheduler, if so the unit will be assigned to the unassigned task with the 
	 * highest priority. 
	 * If the unit is already assigned to a task and not yet finished with it, it will go on
	 * executing it.
	 * If the unit is finished executing his task, he will be unassigned and the task will 
	 * be removed.
	 * If there are no unassigned scheduled tasks, the unit will execute random behavior.
	 * 
	 * @effect ...
	 * 		| assign unit to task with highest priority.
	 * 		| this.assignUnitToTask()
	 * @effect ...
	 * 		| execute the task.
	 * 		| this.executeTask(task)
	 * @effect ...
	 * 		| end task.
	 * 		| this.endTask()
	 * @effect ...
	 * 		| execute random behaviour.
	 * 		| this.randomDefaultBehavior()
	 * 
	 */
	private void defaultBehavior(double dt) throws IllegalArgumentException, IndexOutOfBoundsException {
		if (!this.getFaction().getScheduler().getScheduledTasks().isEmpty()
				&& this.getAssignedTask()==null && 
				this.getFaction().getScheduler().getTaskHighestPriority() != null){
			this.assignUnitToTask();
		}
		if (this.getAssignedTask()!= null && !this.getAssignedTask().getActivity().isExecuted()){
			this.executeTask(dt);
		} 
		else if (this.getAssignedTask()!= null && this.getAssignedTask().getActivity().isExecuted()){
			this.endTask();
		}
		else{
			this.randomDefaultBehavior();
		}
	}
	
	/**
	 * Assign a unit to a task and a task to a unit. The chosen task is the task with the 
	 * highest priority.
	 * 
	 * @post ...
	 * 		| the task is the task with the highest priority.
	 * 		| new.task == new.getFaction().getScheduler().getTaskHighestPriority()
	 * 
	 * @post ...
	 * 		| task will be assigned to a unit.
	 * 		| new.getAssignedTask() == task
	 * @post ...
	 * 		| unit will be assigned to a task.
	 * 		| new.getAssigendUnit() == unit
	 */
	private void assignUnitToTask(){
		Task task = this.getFaction().getScheduler().getTaskHighestPriority();
		this.setAssignedTask(task);
		task.setAssignedUnit(this);
		this.taskComponents = new TaskComponents(this.getWorld(), this, this.getAssignedTask().getSelectedCube());
	}
	
	/**
	 * The unit will execute the assigned task. Every statement in the task has a duration of
	 * 0.001 gametime. If an action (such as working, moving..) takes longer, the executing will
	 * be stopped temporarly and wait until the next advancetime. If the unit can't execute the
	 * current statement, the unit will be interrupted in executing the statement.
	 * 
	 * @param dt
	 * 		The time wherein an amount of statements can be executed.
	 * @effect ...
	 * 		| the current statement will be executed and gametime will be decreased with 0.001.
	 * 		| currentStatement.execute()
	 * 		| dt = dt - 0.001
	 * @Error ...
	 * 		| throw an error when the current statement can't be executed.
	 */
	private void executeTask(double dt) throws Error{
		MyStatement current = this.getAssignedTask().getActivity().getNext(this.taskComponents);
		if (current == null)
			this.getAssignedTask().getActivity().execute(this.taskComponents);
		while (!this.getAssignedTask().getActivity().isExecuted() && dt>0){
			System.out.println(current+" "+current.isExecuted());
			System.out.println(current.getParent());
			if (current.getNext(this.taskComponents)==null || current.getNext(this.taskComponents).isExecuted()){
				try{
				current.execute(this.taskComponents);
				current = this.getAssignedTask().getActivity().getNext(this.taskComponents);
				dt = dt - 0.001;
				if (this.isMovingTo || this.isAttacking() || this.isWorking() || this.isFollowing){
					break;
				}} catch(Throwable e){
					this.interruptTask();
					throw new Error("can not execute this task");
				}
			}else{
				current = current.getNext(this.taskComponents);
			}
		}
	}
	
	/**
	 * End a task by unassigning the unit and removing the task from the scheduler. 
	 * All statements' execute state are put back to false.
	 * 
	 * @post ...
	 * 		| The executed state of the statements are put to false.
	 * 		| new.getAssignedTask().getActivity().isExecuted == false
	 * @effect ...
	 * 		| The task will be removed from the scheduled list.
	 * 		| this.getFaction().getScheduler().removeTask(this.getAssignedTask())
	 * @effect ...
	 * 		| The task will be reset, so the unit will be unassigned from the task
	 * 		| and the task will be unassigned from the unit.
	 */
	private void endTask(){
		this.getAssignedTask().getActivity().setExecutedState(false);
		this.getFaction().getScheduler().removeTask(this.getAssignedTask());
		this.getFaction().getScheduler().reset(this.getAssignedTask(), this);
	}
	
	/**
	 * Execute default behaviour by choosing random task: move to a position, work, rest, sprint to a
	 * position, or attack possible enemy.
	 * 
	 * @effect ... 
	 * 		| The unit will walk to a random position.
	 *      | this.moveTo(randomPosition)
	 * @effect ... 
	 * 		| The unit will work. 
	 * 		| this.work()
	 * @effect ... 
	 * 		| The unit will rest. 
	 * 		| this.rest()
	 * @effect ... 
	 * 		| The unit will sprint to a random position.
	 *      | this.setCurrentSpeed(this.sprintingSpeed)
	 *      | this.moveTo(randomPosition)
	 * @effect ...
	 * 		| The unit will attack an adjacent rival unit.
	 * 		| this.fight(unit)
	 */
	private void randomDefaultBehavior(){
		Random random = new Random();
		int i=random.nextInt(5);
		int[] randomPosition = new int[] { random.nextInt(this.getWorld().getNbCubesX()), 
				random.nextInt(this.getWorld().getNbCubesY()), random.nextInt(this.getWorld().getNbCubesZ()) };
		double[] randomPosition2 = new double[] {randomPosition[0]+ LC/2, randomPosition[1]+LC/2, randomPosition[2]+LC/2};
		switch (i) {
		case 0:
			this.moveTo(randomPosition);
			break;
		case 1:
			if(this.getCubeCoordinate()[0] != this.getWorld().getNbCubesX()-1)
				this.workAt(this.getCubeCoordinate()[0]+1,this.getCubeCoordinate()[1],this.getCubeCoordinate()[2]);
			break;
		case 2:
			this.rest();
			break;
		case 3:
			this.defaultBehaviorCase3 = true;
			this.calculateSpeed(randomPosition2);
			this.moveTo(randomPosition);
			break;
		case 4:
			for (Unit unit:this.getWorld().getUnits()){
				double d = Math.sqrt(Math.pow(unit.getPosition()[0] - this.getPosition()[0],2) + 
						Math.pow(unit.getPosition()[1] - this.getPosition()[1],2) +
						Math.pow(unit.getPosition()[2] - this.getPosition()[2],2));
				if (d <= MAX_DISTANCE_ADJACENT_CUBE){
					this.fight(unit);
					break;
				}
			}
			break;
		}
	}
	
	/**
	 * Variable registering when the unit must start sprinting if he must execute 
	 * defaultBehavior case 3.
	 */
	public boolean defaultBehaviorCase3 = false;
	
	/**
	 * Interrupt task by unassigning the unit and reducing the priority with 100.
	 */
	public void interruptTask(){
		if (this.getAssignedTask() != null){
			this.getAssignedTask().getActivity().setExecutedState(false);
			this.getAssignedTask().setPriority(this.getAssignedTask().getPriority() - 100);
			this.getFaction().getScheduler().reset(this.getAssignedTask(), this);
		}
	}

	/**
	 * Boolean saving if the default behaviour is enabled.
	 */
	private boolean defaultBehaviorEnabled = false;
	
	/**
	 * The unit will follow the followedUnit.
	 * 
	 * @param followedUnit
	 * 		| the unit to be followed.
	 * @post ...
	 * 		| the unit will start following the followedUnit.
	 * 		| new.isFollowing == true;
	 */
	public void follow(Unit followedUnit){
		this.isFollowing = true;
		this.followedUnit = followedUnit;
	}
	
	/**
	 * The unit will follow the followedUnit until the followedUnit died or the unit arrived
	 * at the same cube as the followedUnit is standing.
	 * 
	 * @post ...
	 * 		| If the followedUnit died, this unit will stop following him. The unit will also
	 * 		| be placed to the next position in the centre of a cube.
	 * 		| new.isFollowing == false
	 * 		| new.position == this.nextPosition
	 * @post ...
	 * 		| If the unit catched up the followedUnit, the unit will stop following him.
	 * 		| new.isFollowing == false
	 * @effect ...
	 * 		| If the unit has not reached the followed unit yet, it will keep moving to
	 * 		| the position of the followedUnit. The oldposition will also be updated to the
	 * 		| most recent position of the followedUnit.
	 * 		| this.moveTo(positionFollowedUnit)
	 */
	private void followAdvanceTime(){
		if (UtilCompareList.compareIntList(this.followedUnit.getCubeCoordinate(),
				this.getCubeCoordinate()) || !this.followedUnit.isAlive()){
			this.isFollowing = false;
			this.isMovingTo = false;
			if (!this.followedUnit.isAlive())
				this.setPosition(this.getNextPosition());
		}
		else if ((this.oldPositionFollowedUnit == null || 
				UtilCompareList.compareIntList(this.oldPositionFollowedUnit, this.getCubeCoordinate()))){
			int [] posFollowedUnit = {this.followedUnit.getCubeCoordinate()[0],
					this.followedUnit.getCubeCoordinate()[1], this.followedUnit.getCubeCoordinate()[2]};
			this.oldPositionFollowedUnit = posFollowedUnit;
			this.moveTo(posFollowedUnit);
		}
	}

	/**
	 * Variables registering if the unit is following the followed unit, the followed unit 
	 * and the followed unit's first position
	 */
	private boolean isFollowing = false;
	private Unit followedUnit;
	private int[] oldPositionFollowedUnit;
	
	/**
	 * Return the assigned task of this unit.
	 */
	@Basic
	public Task getAssignedTask(){
		return this.assignedTask;
	}
	
	/**
	 * Assign a task to a unit.
	 * 
	 * @param task
	 * 		the new task to which the unit will be assigned.
	 * @post ...
	 * 		| The task of this new unit is equal to the given task. 
	 * 		| new.getAssignedTask() == task
	 */
	public void setAssignedTask(Task task){
		this.assignedTask = task;
	}
	
	/**
	 * Initializing an object of the class Task.
	 */
	private Task assignedTask;
	
	/**
	 * Return the faction of this unit.
	 */
	@Basic
	@Raw
	public Faction getFaction() {
		return this.faction;
	}

	/**
	 * Check if the faction doesn't contain too much units.
	 * 
	 * @param faction
	 * 		the faction to check
	 * @return
	 * 		| Return true if it is a valid faction.
	 * 		| result == faction.getNbUnitsOfFaction() < Faction.MAX_NUMBER_UNITS_IN_FACTION
	 */
	public static boolean isValidFaction(Faction faction) throws IllegalArgumentException {
		return faction.getNbUnitsOfFaction() < Faction.MAX_NUMBER_UNITS_IN_FACTION;
	}

	/**
	 * Set the faction of a unit to the given faction.
	 * 
	 * @param faction
	 * 		the new faction to which the unit has to be set.
	 * @post 
	 * 		| The faction of this new unit is equal to the given faction. 
	 * 		| new.getFaction() == faction
	 * @throws IllegalArgumentException
	 *      | The given faction is not a valid faction for any unit. 
	 *      | !isValidFaction(getFaction())
	 */
	@Raw
	public void setFaction(Faction faction) throws IllegalArgumentException{
		if (!isValidFaction(faction))
			throw new IllegalArgumentException();
		this.faction = faction;
	}

	/**
	 * Initializing an object of the class Faction.
	 */
	private Faction faction = new Faction();

	/**
	 * Return the world of this unit.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Set the world of a unit to the given world.
	 * 
	 * @param world
	 * 		the new world to which the unit has to be set.
	 * @post ...
	 * 		| The world of this new unit is equal to the given world. 
	 * 		| new.getWorld() == world
	 */
	public void setWorld(World world){
		this.world = world;
	}
	
	/**
	 * Initializing an object of the class World.
	 */
	private World world;

}