package se.fk.github.regelratttillforsakring.logic;

import integration.FolkbokfordIntegrationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.fk.github.regelratttillforsakring.logic.dto.LogicFolkbokfordRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicFolkbokfordResponse;

@ApplicationScoped
public class FolkbokfordLogicService
{

   @Inject
   FolkbokfordIntegrationService folkbokfordIntegration;

   @Inject
   LogicMapper logicMapper;

   public LogicFolkbokfordResponse checkFolkbokford(LogicFolkbokfordRequest request)
   {
      var integrationRequest = logicMapper.toIntegration(request);
      var integrationResponse = folkbokfordIntegration.checkFolkbokford(integrationRequest);
      return logicMapper.toLogic(integrationResponse);
   }
}
