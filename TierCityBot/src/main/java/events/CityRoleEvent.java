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
  public static final String STADT_CHANNEL_ID = "557313447969292392";
  public static final String HAMBURG_CITY_ID = "557295853195231253";
  public static final String BERLIN_CITY_ID = "557298972402778185";
  public static final String MUENCHEN_CITY_ID = "";
  public static final String FRANKFURT_CITY_ID = "";

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
    int caseNumber = 0;

    // Check regular expression and parse the index of the city
    if (channel.getId().equals(cityChannel.getId())) {
      content = content.trim().toLowerCase();
      validToken = Pattern.matches(REGEX_CITY, content);
      if (validToken) {
        for (int i = 0; i < CITIES.values().length; i++) {
          if (content.equals(CITIES.values()[i].toString().toLowerCase())) {
            caseNumber = i;
            break;
          }
        }

        // Switch case for the associated role. If you want to add more cities please
        // read the Javadoc of the CITIES enum.
        switch (caseNumber) {
        case 0:
          guild.getController().addRolesToMember(member, jda.getRoleById(HAMBURG_CITY_ID)).queue();
          break;
        case 1:
          guild.getController().addRolesToMember(member, jda.getRoleById(BERLIN_CITY_ID)).queue();
          break;
        case 2:
          guild.getController().addRolesToMember(member, jda.getRoleById(FRANKFURT_CITY_ID)).queue();
          break;
        case 3:
          guild.getController().addRolesToMember(member, jda.getRoleById(MUENCHEN_CITY_ID)).queue();
          break;
        default:
          break;
        }
        // Reset CaseNumber
        caseNumber = 0;

        // Delete message
        event.getMessage().delete().queue();
      }
    }
  }

  /**
   * Building the regular expression from the "CITIES"-enum which is used by the
   * event
   */
  private static String buildRegexCities() {
    String regexCitiesString = "";
    CITIES[] cities = CITIES.values();
    for (int i = 0; i < cities.length; i++) {
      regexCitiesString += "(" + cities[i].toString().toLowerCase() + ")";
      if (i != cities.length - 1) {
        regexCitiesString += "|";
      }
    }
    return regexCitiesString;
  }
}
