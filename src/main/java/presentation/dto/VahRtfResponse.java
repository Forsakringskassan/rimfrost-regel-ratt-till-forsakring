package presentation.dto;

import java.util.UUID;

public class VahRtfResponse
{
   private UUID processId;
   private boolean result;

   public VahRtfResponse()
   {
   }

   public VahRtfResponse(UUID processId, boolean result)
   {
      this.processId = processId;
      this.result = result;
   }

   public UUID getProcessId()
   {
      return processId;
   }

   public void setProcessId(UUID processId)
   {
      this.processId = processId;
   }

   public boolean isResult()
   {
      return result;
   }

   public void setResult(boolean result)
   {
      this.result = result;
   }

}
