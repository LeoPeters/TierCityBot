/**
 * TierCityBot for CityMasters Discord
 * This Bot will automatically add users to the desired tier-/city roles and delete the messages afterwards.
 * 18.03.2019
 * @author Leo
 */

package Application;

import javax.security.auth.login.LoginException;

import events.CityRoleEvent;
import events.TierRoleEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class App {

  public static final boolean mode = false; // TODO True = TierRoleBot; False = CityBot;
  private static JDA jda;

  public static void main(String[] args) throws LoginException {
    jda = new JDABuilder("NTU2MTM0NDQ2NTg0MTY4NDU5.D3Gdfw.yZ_YTsY_lIJbm22Gd-KCW7_xka8").build();
    if (mode) {
      jda.addEventListener(new TierRoleEvent());
    } else {
      jda.addEventListener(new CityRoleEvent());
    }
  }

  public static JDA getJDA() {
    return jda;
  }
}
