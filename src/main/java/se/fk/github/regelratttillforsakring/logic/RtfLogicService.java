package se.fk.github.regelratttillforsakring.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.regelratttillforsakring.integration.ArbetsgivareApiService;
import se.fk.github.regelratttillforsakring.integration.FolkbokfordApiService;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfResponse;
import se.fk.rimfrost.api.vahregelrtfspec.RattTillForsakring;

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

   private RattTillForsakring evaluateRattTillForsakring(boolean isFolkbokford, boolean hasArbetsgivare)
   {
      RattTillForsakring rattTillForsakring;
      if (isFolkbokford)
      {
         rattTillForsakring = RattTillForsakring.JA;
      }
      else if (!hasArbetsgivare)
      {
         rattTillForsakring = RattTillForsakring.NEJ;
      }
      else
      {
         rattTillForsakring = RattTillForsakring.UTREDNING;
      }
      return rattTillForsakring;
   }
}
