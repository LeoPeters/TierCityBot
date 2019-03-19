/**
 * TierCityBot for CityMasters Discord
 * This Bot will automatically add users to the desired tier-/city roles and delete the messages afterwards.
 * 18.03.2019
 * @author Leo
 */

package events;

/**
 * The City class is place holder for the City Objects that will be created and
 * used in {@link events.CityRoleEvent}
 * 
 * @author Leo
 *
 */
public class City {
  private String name = "";
  private String ID = "";

  public String getID() {
    return ID;
  }

  public City(String name, String ID) {
    this.name = name;
    this.ID = ID;
  }

  @Override
  public String toString() {
    return name;
  }
}