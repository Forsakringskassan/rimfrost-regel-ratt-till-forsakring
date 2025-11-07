package se.fk.github.regelratttillforsakring.tests.integration;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import se.fk.github.regelratttillforsakring.integration.FolkbokfordApiService;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableFolkbokfordApiRequest;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class FolkbokfordApiServiceTest
{

   @Inject
   FolkbokfordApiService service;

   @Test
   void testCheckFolkbokford()
   {
      String personnummer = "12345678-1234";

      var request = ImmutableFolkbokfordApiRequest.builder()
            .personnummer(personnummer)
            .build();

      var response = service.checkFolkbokford(request);

      assertThat(response).isNotNull();
      assertThat(response.isFolkbokford()).isTrue();
   }
}
