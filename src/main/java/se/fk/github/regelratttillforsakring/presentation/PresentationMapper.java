package se.fk.github.regelratttillforsakring.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfResponse;
import se.fk.github.regelratttillforsakring.presentation.dto.*;

import java.util.UUID;

@ApplicationScoped
public class PresentationMapper
{
   public LogicRtfRequest toLogic(PresentationRtfRequest presentation)
   {
      return ImmutableLogicRtfRequest.builder()
            .personnummer(presentation.personnummer())
            .build();
   }

   public PresentationRtfResponse toPresentation(LogicRtfResponse logic)
   {
      return ImmutablePresentationRtfResponse.builder()
            .isBokford(logic.isBokford())
            .hasArbetsgivare(logic.hasArbetsgivare())
            .build();

   }

   public PresentationRtfRequest fromExternalApi(String externalRequest)
   {
      return ImmutablePresentationRtfRequest.builder()
            .personnummer(externalRequest)
            .build();
   }

   public VahRtfResponse toExternalApi(PresentationRtfResponse presentationResponse, UUID processId)
   {
      return ImmutableVahRtfResponse.builder()
            .processId(processId)
            .isBokford(presentationResponse.isBokford())
            .hasArbetsgivare(presentationResponse.hasArbetsgivare())
            .build();
   }
}
