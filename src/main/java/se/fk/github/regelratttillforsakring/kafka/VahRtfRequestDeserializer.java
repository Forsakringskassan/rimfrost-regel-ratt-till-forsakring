package se.fk.github.regelratttillforsakring.kafka;

import com.fasterxml.jackson.core.type.TypeReference;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import se.fk.github.regelratttillforsakring.presentation.dto.CloudEvent;
import se.fk.github.regelratttillforsakring.presentation.dto.VahRtfRequest;

public class VahRtfRequestDeserializer extends ObjectMapperDeserializer<CloudEvent<VahRtfRequest>>
{
   public VahRtfRequestDeserializer()
   {
      super(new TypeReference<CloudEvent<VahRtfRequest>>()
      {
      });
   }
}
