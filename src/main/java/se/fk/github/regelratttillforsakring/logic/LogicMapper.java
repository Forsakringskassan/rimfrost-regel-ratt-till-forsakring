package se.fk.github.regelratttillforsakring.logic;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableIntegrationFolkbokfordRequest;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationFolkbokfordRequest;
import se.fk.github.regelratttillforsakring.integration.dto.IntegrationFolkbokfordResponse;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicFolkbokfordResponse;
import se.fk.github.regelratttillforsakring.logic.dto.LogicFolkbokfordRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicFolkbokfordResponse;

@ApplicationScoped
public class LogicMapper
{
   public LogicFolkbokfordResponse toLogic(IntegrationFolkbokfordResponse external)
   {
      return ImmutableLogicFolkbokfordResponse.builder()
            .isBokford(external.isBokford())
            .hasArbetsgivare(external.hasArbetsgivare())
            .build();
   }

   public IntegrationFolkbokfordRequest toIntegration(LogicFolkbokfordRequest logic)
   {
      return ImmutableIntegrationFolkbokfordRequest.builder()
            .personnummer(logic.personnummer())
            .build();
   }
}
