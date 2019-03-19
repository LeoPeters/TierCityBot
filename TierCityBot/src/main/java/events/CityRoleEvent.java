/**
 * TierCityBot for CityMasters Discord
 * This Bot will automatically add users to the desired tier-/city roles and delete the messages afterwards.
 * 18.03.2019
 * @author Leo
 */

package events;

import java.util.regex.Pattern;

import me.Legiamento.CityTierBot.App;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * This class will only be used if {@link me.Legiamento.CityTierBot.App#mode} is
 * set to false. It will handle the CityRoleEvent that will occur when a message
 * is sent on the guild(server).
 * 
 * @author Leo
 */
public class CityRoleEvent extends ListenerAdapter {

  // TODO Insert IDs from Discord!
  public static final String[] CITY_ID = {    //
      /* Hamburg-ID */ "",                    //
      /* Berlin-ID */ "",                     // Prevent Autoformatting
      /* Muenchen-ID */ "",                   //
      /* Frankfurt-ID */ ""                   //
  };                                          //
  public static final String STADT_CHANNEL_ID = "";

  public static final int NUMBER_OF_CITIES = CITIES.values().length;
  private static City[] cities = buildCityArray();
  public static final String REGEX_CITY = buildRegexCities();

  private JDA jda = App.getJDA();

  /**
   * This method will be called when a message is received on the guild(server).
   * It will check for the correct channel (#stadt) and will add the user, that
   * wrote the message, to the requested city. This will only work if the message
   * fits the regular expression built in {@link #buildRegexCities()}
   * 
   * @param GuildMessageReceivedEvent
   */
  public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

    TextChannel cityChannel = jda.getTextChannelById(STADT_CHANNEL_ID);
    boolean validToken = false;
    TextChannel channel = event.getChannel();
    Member member = event.getMember();
    String content = event.getMessage().getContentRaw();
    Guild guild = event.getGuild();

    // Check regular expression and parse the index of the city
    if (channel.getId().equals(cityChannel.getId())) {
      content = content.trim().toLowerCase();
      validToken = Pattern.matches(REGEX_CITY, content);
      if (validToken) {
        for (int i = 0; i < NUMBER_OF_CITIES; i++) {
          if (content.equals(cities[i].toString().toLowerCase())) {
            guild.getController().addRolesToMember(member, jda.getRoleById(CITY_ID[i])).queue();
            
            // Delete message
            event.getMessage().delete().queue();
            break;
          }
        }
      }
    }
  }

  private static City[] buildCityArray() {
    City[] cities = new City[NUMBER_OF_CITIES];
    for (int i = 0; i < NUMBER_OF_CITIES; i++) {
      cities[i] = new City(CITIES.values()[i].toString(), CITY_ID[i]);
    }
    return cities;
  }

  /**
   * Building the regular expression from the "CITIES"-enum which is used by the
   * event
   */
  private static String buildRegexCities() {
    String regexCitiesString = "";
    for (int i = 0; i < NUMBER_OF_CITIES; i++) {
      regexCitiesString += "(" + cities[i].toString().toLowerCase() + ")";
      if (i != NUMBER_OF_CITIES - 1) {
        regexCitiesString += "|";
      }
    }
    return regexCitiesString;
  }
}
