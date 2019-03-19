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
 * set to true. It will handle the TierRoleEvent that will occur when a message
 * is sent on the guild(server).
 * 
 * @author Leo
 */
public class TierRoleEvent extends ListenerAdapter {

  // TODO Insert IDs from Discord!
  public static final String TIER1_ROLE_ID = "";
  public static final String TIER2_ROLE_ID = "";
  public static final String TIER3_ROLE_ID = "";
  public static final String TIER4_ROLE_ID = "";
  public static final String TIERROLLE_CHANNEL_ID = "";

  // If you want to change the number of Tiers, be sure to add the corresponding
  // case in the onGuildMessageReceived(GuildMessageReceivedEvent event) method!!
  public static final int NUMBER_OF_TIERS = 4;
  public static final String REGEX_TIER = buildRegexTiers();

  private JDA jda = App.getJDA();

  /**
   * This method will be called when a message is received on the guild(server).
   * It will check for the correct channel (#tierrolle) and will add the user,
   * that wrote the message, to the requested Tier. This will only work if the
   * message fits the regular expression built in {@link #buildRegexTiers()}
   * 
   * @param GuildMessageReceivedEvent
   */
  public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

    // Declare Role variables - don't move outside of method! (Race condition)
    TextChannel tierRoleChannel = jda.getTextChannelById(TIERROLLE_CHANNEL_ID);

    boolean validToken = false;
    TextChannel channel = event.getChannel();
    Member member = event.getMember();
    String content = event.getMessage().getContentRaw();
    Guild guild = event.getGuild();

    // Check regular expression and parse the int of the tier.
    if (channel.getId().equals(tierRoleChannel.getId())) {
      validToken = Pattern.matches(REGEX_TIER, content.trim().toLowerCase());
      if (validToken) {
        int tierValue = Integer.parseInt(content.replaceAll("[\\D]", ""));

        // Adding the TierRole to the member
        switch (tierValue) {
        case 1:
          guild.getController().addRolesToMember(member, jda.getRoleById(TIER1_ROLE_ID)).queue();
          break;
        case 2:
          guild.getController().addRolesToMember(member, jda.getRoleById(TIER2_ROLE_ID)).queue();
          break;
        case 3:
          guild.getController().addRolesToMember(member, jda.getRoleById(TIER3_ROLE_ID)).queue();
          break;
        case 4:
          guild.getController().addRolesToMember(member, jda.getRoleById(TIER4_ROLE_ID)).queue();
          break;
        default:
          break;
        }

        // Reset Tier
        tierValue = 0;

        // Delete message
        event.getMessage().delete().queue();
      }
    }
  }

  /**
   * Building the regular expression with the NUMBER_OF_TIERS which is used to
   * confirm the correctness of the message
   */
  private static String buildRegexTiers() {
    String regexTiersString = "";
    regexTiersString += "\"?tier\\s?" + "[1-" + NUMBER_OF_TIERS + "]{1}\"?"; // Example for 4 Tiers: "tier\\s?[1-4]{1}"
    return regexTiersString;
  }
}
