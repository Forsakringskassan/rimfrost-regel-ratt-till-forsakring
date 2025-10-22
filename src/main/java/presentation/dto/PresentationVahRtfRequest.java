package presentation.dto;

import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
public interface PresentationVahRtfRequest
{
   String personnummer();
}

//public class PresentationVahRtfRequest
//{
//
//   //   public UUID processId;
//   public String pnr;
//
//   public PresentationVahRtfRequest()
//   {
//   }
//
//   public PresentationVahRtfRequest(UUID processId, String pnr)
//   {
//      //      this.processId = processId;
//      this.pnr = pnr;
//   }
//}
