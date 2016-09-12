package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by maksym on 9/12/16.
 */
public class GeoIpServiceTests {
  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("8.8.8.8");
    assertThat(geoIP.getCountryCode(), is("USA"));
  }

  @Test
  public void testInvalidIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("8.8.8.xxx");
    assertThat(geoIP.getCountryCode(), is("USA"));
  }
}
