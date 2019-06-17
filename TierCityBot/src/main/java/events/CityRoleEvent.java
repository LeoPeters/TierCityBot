/**
 * TierCityBot for CityMasters Discord
 * This Bot will automatically add users to the desired tier-/city roles and delete the messages afterwards.
 * 18.03.2019
 * @author Leo
 */

package events;

import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import Application.App;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.MessageReaction.ReactionEmote;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

/**
 * This class will only be used if {@link Application.App#mode} is set to false.
 * It will handle the CityRoleEvent that will occur when a message is sent on
 * the guild(server).
 * 
 * @author Leo
 */
public class CityRoleEvent extends ListenerAdapter {

  // TODO Insert IDs from Discord!

  public static String TEST = "557295853195231253";
  public static String HAMBURG_ROLE_ID = "552068978126618624";
  public static String BERLIN_ROLE_ID = "552069066265722888";
  public static String MUENCHEN_ROLE_ID = "552069188051664896";
  public static String FRANKFURT_ROLE_ID = "552069288803172352";

  public static final String STADT_CHANNEL_ID = "552068601100763136";
  public static final String TEST_CHANNEL = "585224223237079070";

  private JDA jda = App.getJDA();

  /**
   * This method will be called when a message is received on the guild(server).
   * It will check for the correct channel (#stadt) and will add the user, that
   * wrote the message, to the requested city. This will only work if the message
   * fits the regular expression built in {@link #buildRegexCities()}
   * 
   * @param GuildMessageReceivedEvent
   */
  public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
    TextChannel cityChannel = jda.getTextChannelById(TEST_CHANNEL);
    TextChannel channel = event.getChannel();

    if (channel == cityChannel) {
    Member member = event.getMember();
    MessageReaction reaction = event.getReaction();
    Guild guild = event.getGuild();
    ReactionEmote reactionEmote = reaction.getReactionEmote();
    ReactionEmote emote = ReactionEmote.fromCustom(jda.getEmoteById("585590923094917120"));
    
    System.out.println("richtiger Channel");
      if (reactionEmote == emote) {
        System.out.println("Emote ist korrekt");
        guild.getController().addRolesToMember(member, jda.getRoleById(TEST)).queue();
      }
    }
  }
}
