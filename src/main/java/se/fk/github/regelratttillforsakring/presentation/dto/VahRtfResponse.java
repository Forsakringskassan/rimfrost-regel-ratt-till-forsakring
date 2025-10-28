package se.fk.github.regelratttillforsakring.presentation.dto;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableVahRtfResponse.class)
public interface VahRtfResponse
{
   UUID processId();

   boolean result();
}
