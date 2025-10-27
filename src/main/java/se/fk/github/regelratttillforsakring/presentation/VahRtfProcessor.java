package se.fk.github.regelratttillforsakring.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import se.fk.github.logging.callerinfo.model.MDCKeys;
import se.fk.github.regelratttillforsakring.logic.FolkbokfordLogicService;
import se.fk.github.regelratttillforsakring.presentation.dto.VahRtfRequest;
import se.fk.github.regelratttillforsakring.presentation.dto.VahRtfResponse;

@ApplicationScoped
public class VahRtfProcessor
{
   private static final Logger LOGGER = LoggerFactory.getLogger(VahRtfProcessor.class);

   @Inject
   FolkbokfordLogicService folkbokfordService;

   @Inject
   PresentationMapper presentationMapper;

   @Incoming("vah-rtf-requests")
   @Outgoing("vah-rtf-responses")
   public VahRtfResponse process(VahRtfRequest vahRtfRequest)
   {
      MDC.put(MDCKeys.PROCESSID.name(), vahRtfRequest.processId().toString());
      LOGGER.info("Vah-rtf-request received, ID: " + vahRtfRequest.processId());
      var presentationRequest = presentationMapper.fromExternalApi(vahRtfRequest.pnr());
      var logicRequest = presentationMapper.toLogic(presentationRequest);
      var bokford = folkbokfordService.checkFolkbokford(logicRequest);
      var presentationResult = presentationMapper.toPresentation(bokford);
      var vahRtfResponse = presentationMapper.toExternalApi(presentationResult, vahRtfRequest.processId());
      return vahRtfResponse;
   }
}
