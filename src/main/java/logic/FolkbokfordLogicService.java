package logic;

import integration.FolkbokfordIntegrationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import logic.dto.LogicFolkbokfordRequest;
import logic.dto.LogicFolkbokfordResponse;

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