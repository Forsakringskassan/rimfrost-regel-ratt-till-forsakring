package se.fk.github.regelratttillforsakring.kafka;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfRequestMessagePayload;

public class VahRtfRequestMessagePayloadDeserializer extends ObjectMapperDeserializer<VahRtfRequestMessagePayload>
{
   public VahRtfRequestMessagePayloadDeserializer()
   {
      super(VahRtfRequestMessagePayload.class);
   }

}
