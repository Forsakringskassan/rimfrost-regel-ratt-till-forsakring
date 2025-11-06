package se.fk.github.regelratttillforsakring.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.regelratttillforsakring.integration.ArbetsgivareApiService;
import se.fk.github.regelratttillforsakring.integration.FolkbokfordApiService;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRattTillForsakring;
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
      var rattTillForsakring = evaluateRattTillForsakring(folkbokfordResponse.isFolkbokford(),
            arbetsgivareResponse.hasArbetsgivare());
      return logicMapper.toLogic(rattTillForsakring);
   }

   private LogicRattTillForsakring evaluateRattTillForsakring(boolean isFolkbokford, boolean hasArbetsgivare)
   {
      LogicRattTillForsakring rattTillForsakring;
      if (isFolkbokford)
      {
         rattTillForsakring = LogicRattTillForsakring.JA;
      }
      else if (!hasArbetsgivare)
      {
         rattTillForsakring = LogicRattTillForsakring.NEJ;
      }
      else
      {
         rattTillForsakring = LogicRattTillForsakring.UTREDNING;
      }
      return rattTillForsakring;
   }
}
