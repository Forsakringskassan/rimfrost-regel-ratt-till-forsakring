package presentation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import logic.FolkbokfordLogicService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfRequestPayload;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfResponsePayload;

@ApplicationScoped
public class VahRtfProcessor
{

   @Inject
   FolkbokfordLogicService folkbokfordService;

   @Inject
   PresentationMapper presentationMapper;

   @Incoming("vah-rtf-request")
   @Outgoing("vah-rtf-response")
   public VahRtfResponsePayload process(VahRtfRequestPayload vahRtfRequest)
   {
      System.out.println("Vah-rtf-request received, ID: " + vahRtfRequest.getProcessId());
      var presentationRequest = presentationMapper.fromExternalApi(vahRtfRequest.getPersonNummer());
      var logicRequest = presentationMapper.toLogic(presentationRequest);
      var bokford = folkbokfordService.checkFolkbokford(logicRequest);
      var presentationResult = presentationMapper.toPresentation(bokford);
      var vahRtfResponse = presentationMapper.toExternalApi(presentationResult, vahRtfRequest.getProcessId());
      return vahRtfResponse;
   }
}
