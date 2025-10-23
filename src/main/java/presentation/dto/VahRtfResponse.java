package presentation.dto;

import java.util.UUID;
import org.immutables.value.Value;

@Value.Immutable
public interface VahRtfResponse
{
   UUID processId();

   boolean result();
}
