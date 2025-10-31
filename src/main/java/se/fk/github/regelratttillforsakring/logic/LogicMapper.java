package se.fk.github.regelratttillforsakring.logic;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableIntegrationRtfRequest;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationRtfRequest;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationRtfResponse;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfResponse;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfResponse;

@ApplicationScoped
public class LogicMapper
{
   public LogicRtfResponse toLogic(IntegrationRtfResponse external)
   {
      return ImmutableLogicRtfResponse.builder()
            .isBokford(external.isBokford())
            .hasArbetsgivare(external.hasArbetsgivare())
            .build();
   }

   public IntegrationRtfRequest toIntegration(LogicRtfRequest logic)
   {
      return ImmutableIntegrationRtfRequest.builder()
            .personnummer(logic.personnummer())
            .build();
   }
}
