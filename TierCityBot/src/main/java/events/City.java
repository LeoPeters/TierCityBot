/**
 * TierCityBot for CityMasters Discord
 * This Bot will automatically add users to the desired tier-/city roles and delete the messages afterwards.
 * 18.03.2019
 * @author Leo
 */

package events;

/**
 * Class for the cities used by {@link events.CityRoleEvent}. You can add more
 * cities to the enum in here, but note that you have to add the corresponding case in the
 * {@link events.CityRoleEvent#onGuildMessageReceived(net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent)}.
 * Note also, that the order of this Enum has to be the same as the order of the
 * switch case!!
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