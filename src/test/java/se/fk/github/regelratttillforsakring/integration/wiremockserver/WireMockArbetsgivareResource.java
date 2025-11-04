package se.fk.github.regelratttillforsakring.integration.wiremockserver;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class WireMockArbetsgivareResource implements QuarkusTestResourceLifecycleManager
{
   private WireMockServer server;

   @Override
   public Map<String, String> start()
   {
      server = new WireMockServer(options().dynamicPort());
      server.start();
      return Map.of(
            "arbetsgivare.api.base-url", "http://localhost:" + server.port());
   }

   @Override
   public void stop()
   {
      if (server != null)
      {
         server.stop();
      }
   }
}
