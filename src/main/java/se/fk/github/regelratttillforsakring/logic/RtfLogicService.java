package se.fk.github.regelratttillforsakring.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.regelratttillforsakring.integration.RtfIntegrationService;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfResponse;

@ApplicationScoped
public class RtfLogicService
{

   @Inject
   RtfIntegrationService rtfIntegrationService;

   @Inject
   LogicMapper logicMapper;

   public LogicRtfResponse checkRattTillForsakring(LogicRtfRequest request)
   {
      var integrationRequest = logicMapper.toIntegration(request);
      var integrationResponse = rtfIntegrationService.checkRattTillForsakring(integrationRequest);
      return logicMapper.toLogic(integrationResponse);
   }
}
