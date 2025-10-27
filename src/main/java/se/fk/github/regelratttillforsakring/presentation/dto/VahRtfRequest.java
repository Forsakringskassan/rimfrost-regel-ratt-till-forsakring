package se.fk.github.regelratttillforsakring.presentation.dto;

import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface VahRtfRequest
{
   UUID processId();

   String pnr();
}
