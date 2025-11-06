package se.fk.github.regelratttillforsakring.kafka;

import com.fasterxml.jackson.core.type.TypeReference;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfRequestMessagePayload;

public class VahRtfRequestMessagePayloadDeserializer extends ObjectMapperDeserializer<VahRtfRequestMessagePayload>
{
   public VahRtfRequestMessagePayloadDeserializer()
   {
      super(new TypeReference<VahRtfRequestMessagePayload>()
      {

      });
   }

}
