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
import se.fk.github.regelratttillforsakring.presentation.dto.CloudEvent;
import se.fk.github.regelratttillforsakring.presentation.dto.VahRtfRequest;
import se.fk.github.regelratttillforsakring.presentation.dto.VahRtfResponse;

import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class VahRtfProcessor
{
   private static final Logger LOGGER = LoggerFactory.getLogger(VahRtfProcessor.class);

   private final AtomicInteger receivedMessagesCount = new AtomicInteger(0);

   @Inject
   RtfLogicService rtfLogicService;

   @Inject
   PresentationMapper presentationMapper;

   @Incoming("vah-rtf-requests")
   @Outgoing("vah-rtf-responses")
   public CloudEvent<VahRtfResponse> process(CloudEvent<VahRtfRequest> vahRtfRequest)
   {
      MDC.put(MDCKeys.PROCESSID.name(), vahRtfRequest.data().processId().toString());
      LOGGER.info("Vah-rtf-request received, ID: " + vahRtfRequest.data().processId());
      receivedMessagesCount.incrementAndGet();

      var presentationRequest = presentationMapper.fromExternalApi(vahRtfRequest.data().pnr());
      var rattTillforsakringRequest = presentationMapper.toLogic(presentationRequest);
      var rattTillForsakringResponse = rtfLogicService.checkRattTillForsakring(rattTillforsakringRequest);
      var presentationResult = presentationMapper.toPresentation(rattTillForsakringResponse);

      return presentationMapper.toExternalApi(presentationResult, vahRtfRequest);
   }

   public int getReceivedMessagesCount()
   {
      return receivedMessagesCount.get();
   }

   public void resetCounter()
   {
      receivedMessagesCount.set(0);
   }
}
