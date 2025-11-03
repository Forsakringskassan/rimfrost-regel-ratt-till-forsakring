package se.fk.github.regelratttillforsakring.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfResponse;
import se.fk.github.regelratttillforsakring.presentation.dto.*;

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

   public CloudEvent<VahRtfResponse> toExternalApi(PresentationRtfResponse presentationResponse,
         CloudEvent<VahRtfRequest> request)
   {
      return ImmutableCloudEvent.<VahRtfResponse> builder()
            .id(request.id())
            .source(request.source())
            .type("vah-rtf-responses")
            .kogitorootprocid(request.kogitorootprocid())
            .kogitorootprociid(request.kogitorootprociid())
            .kogitoparentprociid(request.kogitoparentprociid())
            .kogitoprocid(request.kogitoprocid())
            .kogitoprocinstanceid(request.kogitoprocinstanceid())
            .kogitoprocrefid(request.kogitoprocinstanceid())
            .kogitoprocist(request.kogitoprocist())
            .kogitoproctype(request.kogitoproctype())
            .kogitoprocversion(request.kogitoprocversion())
            .data(ImmutableVahRtfResponse.builder()
                  .processId(request.data().processId())
                  .isBokford(presentationResponse.isBokford())
                  .hasArbetsgivare(presentationResponse.hasArbetsgivare())
                  .build())
            .build();
   }
}
