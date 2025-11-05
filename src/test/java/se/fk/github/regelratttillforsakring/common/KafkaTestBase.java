package se.fk.github.regelratttillforsakring.common;

import io.quarkus.test.common.QuarkusTestResource;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import se.fk.github.regelratttillforsakring.presentation.VahRtfProcessor;
import se.fk.github.regelratttillforsakring.presentation.dto.VahRtfRequest;
import se.fk.github.regelratttillforsakring.presentation.dto.VahRtfResponse;

@QuarkusTestResource(InMemoryKafkaResource.class)
public class KafkaTestBase
{
   @Inject
   @Any
   public InMemoryConnector connector;

   @Inject
   public VahRtfProcessor processor;

   public InMemorySource<VahRtfRequest> inMemorySource;
   public InMemorySink<VahRtfResponse> inMemorySink;

   @BeforeEach
   void setUp()
   {
      inMemorySource = connector.source("vah-rtf-requests");
      inMemorySink = connector.sink("vah-rtf-responses");
      inMemorySink.clear();
      processor.resetCounter();
   }

   @AfterEach
   void tearDown()
   {
      InMemoryConnector.clear();
   }
}
