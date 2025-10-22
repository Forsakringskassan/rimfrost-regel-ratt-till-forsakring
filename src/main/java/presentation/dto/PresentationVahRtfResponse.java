package presentation.dto;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface PresentationVahRtfResponse
{
   Boolean isBokford();
}

////   private UUID processId;
//   private boolean result;
//
//   public PresentationVahRtfResponse()
//   {
//   }
//
//   public PresentationVahRtfResponse(UUID processId, boolean result)
//   {
////      this.processId = processId;
//      this.result = result;
//   }
//
////   public UUID getProcessId()
////   {
////      return processId;
////   }
////
////   public void setProcessId(UUID processId)
////   {
////      this.processId = processId;
////   }
//
//   public boolean isResult()
//   {
//      return result;
//   }
//
//   public void setResult(boolean result)
//   {
//      this.result = result;
//   }
//
//}
