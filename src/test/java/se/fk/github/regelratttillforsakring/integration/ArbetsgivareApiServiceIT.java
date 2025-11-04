package se.fk.github.regelratttillforsakring.integration;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableArbetsgivareApiRequest;
import se.fk.github.regelratttillforsakring.integration.wiremockserver.WireMockArbetsgivareResource;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(WireMockArbetsgivareResource.class)
public class ArbetsgivareApiServiceIT
{
   @Inject
   ArbetsgivareApiService service;

   @InjectSpy
   IntegrationMapper mapper;

   @BeforeEach
   void setUpStubs()
   {
      reset();
      stubArbetsgivareApi();
   }

   @Test
   void checkArbetsgivare_shouldReturnTrue()
   {
      // Arrange
      var req = ImmutableArbetsgivareApiRequest.builder()
            .personnummer("12345678-1234")
            .build();

      // Act
      var resp = service.checkArbetsgivare(req);

      // Assert
      assertTrue(resp.hasArbetsgivare());

      verify(getRequestedFor(urlEqualTo("/arbetsgivare/12345678-1234")));
   }

   private void stubArbetsgivareApi()
   {
      stubFor(get(urlEqualTo("/arbetsgivare/12345678-1234"))
            .atPriority(1)
            .willReturn(aResponse()
                  .withStatus(200)
                  .withHeader("Content-Type", "application/json")
                  .withBody("{\"result\": true}")));

      stubFor(get(urlPathMatching("/arbetsgivare/\\d{8}-\\d{4}"))
            .atPriority(5)
            .willReturn(okJson("{\"result\": true}")));

      stubFor(get(urlPathMatching("/arbetsgivare/.+"))
            .atPriority(10)
            .willReturn(aResponse()
                  .withStatus(400)
                  .withHeader("Content-Type", "application/json")
                  .withBody("{\"error\": \"Bad request: invalid pnr format\"}")));
   }
}
