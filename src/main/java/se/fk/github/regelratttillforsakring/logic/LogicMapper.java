package se.fk.github.regelratttillforsakring.logic;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.integration.dto.*;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfResponse;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfResponse;

@ApplicationScoped
public class LogicMapper
{
   public LogicRtfResponse toLogic(FolkbokfordApiResponse folkbokfordResponse, ArbetsgivareApiResponse arbetsgivareResponse)
   {
      return ImmutableLogicRtfResponse.builder()
            .isBokford(folkbokfordResponse.isFolkbokford())
            .hasArbetsgivare(arbetsgivareResponse.hasArbetsgivare())
            .build();
   }

   public FolkbokfordApiRequest toFolkbokfordIntegration(LogicRtfRequest logic)
   {
      return ImmutableFolkbokfordApiRequest.builder()
            .personnummer(logic.personnummer())
            .build();
   }

   public ArbetsgivareApiRequest toArbetsgivareIntegration(LogicRtfRequest logic)
   {
      return ImmutableArbetsgivareApiRequest.builder()
            .personnummer(logic.personnummer())
            .build();
   }
}
