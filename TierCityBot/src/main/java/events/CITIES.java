/**
 * TierCityBot for CityMasters Discord
 * This Bot will automatically add users to the desired tier-/city roles and delete the messages afterwards.
 * 18.03.2019
 * @author Leo
 */
package events;

/**
 * Enum for the cities used by {@link events.CityRoleEvent} and referred to by
 * {@link events.City}. You can add more cities to the enum in here, but note
 * that you have to add the corresponding ID in the
 * {@link events.CityRoleEvent#CITY_ID}. Note also, that the order of this Enum
 * has to be the same as the order of the IDs in
 * {@link events.CityRoleEvent#CITY_ID}!!
 * 
 * @author Leo
 *
 */
public enum CITIES {
  HAMBURG, BERLIN, MÜNCHEN, FRANKFURT
}
