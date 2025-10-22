package presentation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import logic.FolkbokfordLogicService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import presentation.dto.VahRtfRequest;
import presentation.dto.VahRtfResponse;

@ApplicationScoped
public class VahRtfProcessor
{

   @Inject
   FolkbokfordLogicService folkbokfordService;

   @Inject
   PresentationMapper presentationMapper;

   @Incoming("vah-rtf-request")
   @Outgoing("vah-rtf-response")
   public VahRtfResponse process(VahRtfRequest vahRtfRequest)
   {
      System.out.println("Vah-rtf-request received, ID: " + vahRtfRequest.processId);
      var presentationRequest = presentationMapper.fromExternalApi(vahRtfRequest.pnr);
      var logicRequest = presentationMapper.toLogic(presentationRequest);
      var bokford = folkbokfordService.checkFolkbokford(logicRequest);
      var presentationResult = presentationMapper.toPresentation(bokford);
      var vahRtfResponse = presentationMapper.toExternalApi(presentationResult);
      vahRtfResponse.setProcessId(vahRtfRequest.processId);
      //Placeholder until proper return type has been generated
      return vahRtfResponse;
   }
}
