package se.fk.github.regelratttillforsakring.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicFolkbokfordRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicFolkbokfordRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicFolkbokfordResponse;
import se.fk.github.regelratttillforsakring.presentation.dto.*;

import java.util.UUID;

@ApplicationScoped
public class PresentationMapper
{
   public LogicFolkbokfordRequest toLogic(PresentationVahRtfRequest presentation)
   {
      return ImmutableLogicFolkbokfordRequest.builder()
            .personnummer(presentation.personnummer())
            .build();
   }

   public PresentationVahRtfResponse toPresentation(LogicFolkbokfordResponse logic)
   {
      return ImmutablePresentationVahRtfResponse.builder()
            .isBokford(logic.isBokford())
            .hasArbetsgivare(logic.hasArbetsgivare())
            .build();

   }

   public PresentationVahRtfRequest fromExternalApi(String externalRequest)
   {
      return ImmutablePresentationVahRtfRequest.builder()
            .personnummer(externalRequest)
            .build();
   }

   public VahRtfResponse toExternalApi(PresentationVahRtfResponse presentationResponse, UUID processId)
   {
      return ImmutableVahRtfResponse.builder()
            .processId(processId)
            .isBokford(presentationResponse.isBokford())
            .hasArbetsgivare(presentationResponse.hasArbetsgivare())
            .build();
   }
}
