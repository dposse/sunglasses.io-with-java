package sunglasses.io;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetBrandsTest {
  @LocalServerPort
  private int randomServerPort;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testGetBrandsSuccess() throws URISyntaxException {

    //arrange
    final String baseUrl = "http://localhost:" + randomServerPort + "/brands";
    URI uri = new URI(baseUrl);

    //act
    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

    //assert
    System.out.println(result.getStatusCodeValue());
    Assert.isTrue(result.getStatusCodeValue() == 200, "Response status must be 200");
  }
}