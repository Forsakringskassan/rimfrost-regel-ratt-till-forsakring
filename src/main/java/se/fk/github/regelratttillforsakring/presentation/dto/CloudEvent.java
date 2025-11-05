package se.fk.github.regelratttillforsakring.presentation.dto;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;
import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonDeserialize(as = ImmutableCloudEvent.class)
@JsonSerialize(as = ImmutableCloudEvent.class)
public interface CloudEvent<T>
{

   @Value.Default
   default String specversion()
   {
      return "1.0";
   }

   UUID id();

   URI source();

   String type();

   @Value.Default
   default OffsetDateTime time()
   {
      return OffsetDateTime.now();
   }

   UUID kogitoparentprociid();

   String kogitorootprocid();

   String kogitoproctype();

   UUID kogitoprocinstanceid();

   String kogitoprocist();

   String kogitoprocversion();

   UUID kogitorootprociid();

   String kogitoprocid();

   Optional<UUID> kogitoprocrefid();

   T data();

}
