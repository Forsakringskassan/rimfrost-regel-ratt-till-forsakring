package se.fk.github.regelratttillforsakring.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRattTillForsakring;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.LogicRtfResponse;
import se.fk.rimfrost.RattTillForsakring;
import se.fk.rimfrost.VahRtfRequestMessageData;
import se.fk.rimfrost.VahRtfRequestMessagePayload;
import se.fk.rimfrost.VahRtfResponseMessageData;
import se.fk.rimfrost.VahRtfResponseMessagePayload;

@ApplicationScoped
public class PresentationMapper
{
   public LogicRtfRequest toRattTillForsakringRequest(VahRtfRequestMessageData presentation)
   {
      return ImmutableLogicRtfRequest.builder()
            .personnummer(presentation.getPersonNummer())
            .build();
   }

   public VahRtfResponseMessagePayload toRtfResponsePayload(LogicRtfResponse logicResponse,
         VahRtfRequestMessagePayload request)
   {
      VahRtfResponseMessageData data = new VahRtfResponseMessageData();
      data.setProcessId(request.getData().getProcessId());
      data.setRattTillForsakring(logicRattTillForsakringToRattTillForsakring(logicResponse.rattTillForsakring()));
      data.setPersonNummer(request.getData().getPersonNummer());

      VahRtfResponseMessagePayload response = new VahRtfResponseMessagePayload();
      response.setId(request.getId());
      response.setSpecversion(request.getSpecversion());
      response.setSource(request.getSource());
      response.setType("vah-rtf-responses");
      response.setKogitorootprocid(request.getKogitorootprocid());
      response.setKogitorootprociid(request.getKogitorootprociid());
      response.setKogitoparentprociid(request.getKogitoparentprociid());
      response.setKogitoprocid(request.getKogitoprocid());
      response.setKogitoprocinstanceid(request.getKogitoprocinstanceid());
      response.setKogitoprocrefid(request.getKogitoprocinstanceid());
      response.setKogitoprocist(request.getKogitoprocist());
      response.setKogitoproctype(request.getKogitoproctype());
      response.setKogitoprocversion(request.getKogitoprocversion());
      response.setData(data);

      return response;
   }

   private RattTillForsakring logicRattTillForsakringToRattTillForsakring(LogicRattTillForsakring in)
   {
      RattTillForsakring out;
      switch (in)
      {
         case JA:
            out = RattTillForsakring.JA;
            break;
         case NEJ:
            out = RattTillForsakring.NEJ;
            break;
         case UTREDNING:
            out = RattTillForsakring.UTREDNING;
            break;
         default:
            out = RattTillForsakring.UTREDNING;
            break;
      }
      return out;
   }
}
