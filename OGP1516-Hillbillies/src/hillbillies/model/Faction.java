package hillbillies.model;

import java.util.Arrays;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;


/**
 * @invar Each faction can have its factionName as factionName. |
 *        canHaveAsFactionName(this.getFactionName())
 */
public class Faction {

	/**
	 * @param factionName
	 *            The factionName for this new faction.
	 * @post The factionName of this new faction is equal to the given
	 *       factionName. | new.getFactionName() == factionName
	 * @throws IllegalArgumentException
	 *             This new faction cannot have the given factionName as its
	 *             factionName. | ! canHaveAsFactionName(this.getFactionName())
	 */
	public Faction(FactionNames factionName) {
		this.factionName = factionName;
	}

	
	/**
	 * Return the factionName of this faction.
	 */
	@Basic
	@Raw
	@Immutable
	public FactionNames getFactionName() {
		return this.factionName;
	}

	/**
	 * Check whether this faction can have the given factionName as its
	 * factionName.
	 * 
	 * @param factionName
	 *            The factionName to check.
	 * @return | result ==
	 */
	@Raw
	public boolean canHaveAsFactionName(FactionNames factionName) {
		FactionNames[] factions = FactionNames.values();
		System.out.println(factions);
		return Arrays.asList(factions).contains(factionName);
	}

	/**
	 * Variable registering the factionName of this faction.
	 */
	private final FactionNames factionName;
	
	

	public Set<Unit> getUnitsOfFaction() {
		return null;
	}

	// kan alleen vechten tegen andere faction
	// max 5 factions in wereld
	// max 50 in 1 faction
	// unit bijgevoegd bij kleinste faction
}
