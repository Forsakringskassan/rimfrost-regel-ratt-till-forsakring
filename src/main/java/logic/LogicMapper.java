package logic;

import integration.dto.ImmutableIntegrationFolkbokfordRequest;
import integration.dto.IntegrationFolkbokfordRequest;
import integration.dto.IntegrationFolkbokfordResponse;
import jakarta.enterprise.context.ApplicationScoped;
import logic.dto.ImmutableLogicFolkbokfordResponse;
import logic.dto.LogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordResponse;

@ApplicationScoped
public class LogicMapper
{
   public LogicFolkbokfordResponse toLogic(IntegrationFolkbokfordResponse external)
   {
      return ImmutableLogicFolkbokfordResponse.builder()
            .isBokford(external.isBokford())
            .build();
   }

   public IntegrationFolkbokfordRequest toIntegration(LogicFolkbokfordRequest logic)
   {
      return ImmutableIntegrationFolkbokfordRequest.builder()
            .personnummer(logic.personnummer())
            .build();
   }
}
