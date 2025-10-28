package se.fk.github.regelratttillforsakring.kafka;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import se.fk.github.regelratttillforsakring.presentation.dto.VahRtfRequest;

public class VahRtfRequestDeserializer extends ObjectMapperDeserializer<VahRtfRequest>
{
   public VahRtfRequestDeserializer()
   {
      super(VahRtfRequest.class);
   }
}
