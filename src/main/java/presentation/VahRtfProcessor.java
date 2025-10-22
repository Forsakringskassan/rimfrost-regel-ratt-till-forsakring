package presentation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import logic.FolkbokfordLogicService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class VahRtfProcessor {

    @Inject
    FolkbokfordLogicService folkbokfordService;

    @Inject
    PresentationMapper presentationMapper;

    @Incoming("vah-rtf-request")
    @Outgoing("vah-rtf-response")
    public Boolean process(String personnummer /* Placeholder until proper request type has been generated */) throws InterruptedException
    {
        System.out.println("Vah-rtf-request received for PNR: " + personnummer);
        Thread.sleep(6000);
        var presentationRequest = presentationMapper.fromExternalApi(personnummer);
        var logicRequest = presentationMapper.toLogic(presentationRequest);
        var bokf = folkbokfordService.checkFolkbokford(logicRequest);
        var presentationResult =  presentationMapper.toPresentation(bokf);
        //Placeholder until proper return type has been generated
        return presentationResult.isBokford();
    }
}
