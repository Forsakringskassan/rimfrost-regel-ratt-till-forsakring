package se.fk.github.regelratttillforsakring.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.regelratttillforsakring.integration.ArbetsgivareApiService;
import se.fk.github.regelratttillforsakring.integration.FolkbokfordApiService;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfResponse;

@ApplicationScoped
public class RtfLogicService
{

   @Inject
   FolkbokfordApiService folkbokfordService;

   @Inject
   ArbetsgivareApiService arbetsgivareService;

   @Inject
   LogicMapper logicMapper;

   public LogicRtfResponse checkRattTillForsakring(LogicRtfRequest request)
   {
      var folkbokfordRequest = logicMapper.toFolkbokfordIntegration(request);
      var arbetsgivareRequest = logicMapper.toArbetsgivareIntegration(request);
      var folkbokfordResponse = folkbokfordService.checkFolkbokford(folkbokfordRequest);
      var arbetsgivareResponse = arbetsgivareService.checkArbetsgivare(arbetsgivareRequest);
      return logicMapper.toLogic(folkbokfordResponse, arbetsgivareResponse);
   }
}
