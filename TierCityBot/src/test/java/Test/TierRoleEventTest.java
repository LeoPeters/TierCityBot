package Test;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Test;

import events.TierRoleEvent;

public class TierRoleEventTest {

  @Test
  public void buildRegexTiersTest() {
    System.out.println(TierRoleEvent.REGEX_TIER);
    assertEquals(true, Pattern.matches(TierRoleEvent.REGEX_TIER,
        "tier     " + String.valueOf((int) (Math.random() * TierRoleEvent.NUMBER_OF_TIERS + 1))));
    assertEquals(true, Pattern.matches(TierRoleEvent.REGEX_TIER,
        " tier " + String.valueOf((int) (Math.random() * TierRoleEvent.NUMBER_OF_TIERS + 1))));
    assertEquals(true, Pattern.matches(TierRoleEvent.REGEX_TIER,
        "   tier" + String.valueOf((int) (Math.random() * TierRoleEvent.NUMBER_OF_TIERS + 1))));
    assertEquals(true, Pattern.matches(TierRoleEvent.REGEX_TIER,
        " tier" + String.valueOf((int) (Math.random() * TierRoleEvent.NUMBER_OF_TIERS + 1))));
    assertEquals(true, Pattern.matches(TierRoleEvent.REGEX_TIER,
        "tier " + String.valueOf((int) (Math.random() * TierRoleEvent.NUMBER_OF_TIERS + 1))));
    assertEquals(false, Pattern.matches(TierRoleEvent.REGEX_TIER,
        "teir " + String.valueOf((int) (Math.random() * TierRoleEvent.NUMBER_OF_TIERS + 1))));
    assertEquals(false, Pattern.matches(TierRoleEvent.REGEX_TIER,
        "ttier " + String.valueOf((int) (Math.random() * TierRoleEvent.NUMBER_OF_TIERS + 1))));
    assertEquals(false, Pattern.matches(TierRoleEvent.REGEX_TIER,
        "tie r" + String.valueOf((int) (Math.random() * TierRoleEvent.NUMBER_OF_TIERS + 1))));
    assertEquals(false, Pattern.matches(TierRoleEvent.REGEX_TIER,
        "tier" + String.valueOf((int) (Math.random() * TierRoleEvent.NUMBER_OF_TIERS + 7))));
    assertEquals(false, Pattern.matches(TierRoleEvent.REGEX_TIER,
        "tier" + String.valueOf((int) (Math.random() * 0))));
    assertEquals(false, Pattern.matches(TierRoleEvent.REGEX_TIER,
        "tier" + String.valueOf((int) (Math.random() * 999999))));
  }
}
