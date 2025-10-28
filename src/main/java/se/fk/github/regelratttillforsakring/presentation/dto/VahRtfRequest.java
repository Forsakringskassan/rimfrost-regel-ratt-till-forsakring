package se.fk.github.regelratttillforsakring.presentation.dto;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableVahRtfRequest.class)
public interface VahRtfRequest
{
   UUID processId();

   String pnr();
}
