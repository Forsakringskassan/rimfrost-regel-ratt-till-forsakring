package se.fk.github.regelratttillforsakring.tests.integration;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import se.fk.github.regelratttillforsakring.integration.ArbetsgivareApiService;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableArbetsgivareApiRequest;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class ArbetsgivareApiServiceTest
{

   @Inject
   ArbetsgivareApiService service;

   @Test
   void testCheckArbetsgivare()
   {
      String personnummer = "12345678-1234";

      var request = ImmutableArbetsgivareApiRequest.builder()
            .personnummer(personnummer)
            .build();

      var response = service.checkArbetsgivare(request);

      assertThat(response).isNotNull();
      assertThat(response.hasArbetsgivare()).isTrue();
   }
}
