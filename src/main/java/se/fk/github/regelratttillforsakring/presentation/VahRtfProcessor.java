package se.fk.github.regelratttillforsakring.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import se.fk.github.logging.callerinfo.model.MDCKeys;
import se.fk.github.regelratttillforsakring.logic.RtfLogicService;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfRequestMessagePayload;
import se.fk.rimfrost.api.vahregelrtfspec.VahRtfResponseMessagePayload;

@ApplicationScoped
public class VahRtfProcessor
{
   private static final Logger LOGGER = LoggerFactory.getLogger(VahRtfProcessor.class);

   @Inject
   RtfLogicService rtfLogicService;

   @Inject
   PresentationMapper presentationMapper;

   @Incoming("vah-rtf-requests")
   @Outgoing("vah-rtf-responses")
   public VahRtfResponseMessagePayload process(VahRtfRequestMessagePayload vahRtfRequest)
   {
      MDC.put(MDCKeys.PROCESSID.name(), vahRtfRequest.getData().getProcessId().toString());
      LOGGER.info("Vah-rtf-request received, ID: " + vahRtfRequest.getData().getProcessId());

      var presentationRequest = presentationMapper.fromExternalApi(vahRtfRequest.getData().getPersonNummer());
      var rattTillforsakringRequest = presentationMapper.toLogic(presentationRequest);
      var rattTillForsakringResponse = rtfLogicService.checkRattTillForsakring(rattTillforsakringRequest);
      var presentationResult = presentationMapper.toPresentation(rattTillForsakringResponse);

      return presentationMapper.toExternalApi(presentationResult, vahRtfRequest);
   }
}
