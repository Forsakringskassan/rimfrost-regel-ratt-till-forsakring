package se.fk.github.regelratttillforsakring.presentation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.fk.github.regelratttillforsakring.logic.RtfLogicService;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfResponse;
import se.fk.github.regelratttillforsakring.presentation.dto.ImmutablePresentationRtfRequest;
import se.fk.github.regelratttillforsakring.presentation.dto.ImmutablePresentationRtfResponse;
import se.fk.github.regelratttillforsakring.presentation.dto.ImmutableVahRtfRequest;
import se.fk.github.regelratttillforsakring.presentation.dto.ImmutableVahRtfResponse;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(
{
      MockitoExtension.class
})
public class VahRtfProcessorTest
{
   @Mock
   RtfLogicService logicService;

   @Mock
   PresentationMapper mapper;

   @InjectMocks
   VahRtfProcessor processor;

   @Test
   void checkProcess_shouldMapCall_andReturnMappedResult()
   {
      // Arrange
      String personnummer = "12345678-1234";
      UUID processId = UUID.randomUUID();

      var externalRequest = ImmutableVahRtfRequest.builder()
            .processId(processId)
            .pnr(personnummer)
            .build();

      var presentationRequest = ImmutablePresentationRtfRequest.builder()
            .personnummer(personnummer)
            .build();

      var logicRequest = ImmutableLogicRtfRequest.builder()
            .personnummer(personnummer)
            .build();

      var logicResponse = ImmutableLogicRtfResponse.builder()
            .isBokford(true)
            .hasArbetsgivare(true)
            .build();

      var presentationResponse = ImmutablePresentationRtfResponse.builder()
            .isBokford(true)
            .hasArbetsgivare(true)
            .build();

      var expected = ImmutableVahRtfResponse.builder()
            .isBokford(true)
            .hasArbetsgivare(true)
            .processId(processId)
            .build();

      when(mapper.fromExternalApi(personnummer)).thenReturn(presentationRequest);
      when(mapper.toLogic(presentationRequest)).thenReturn(logicRequest);
      when(logicService.checkRattTillForsakring(logicRequest)).thenReturn(logicResponse);
      when(mapper.toPresentation(logicResponse)).thenReturn(presentationResponse);
      when(mapper.toExternalApi(presentationResponse, processId)).thenReturn(expected);

      // Act
      var actual = processor.process(externalRequest);

      // Assert
      assertEquals(expected, actual);

      InOrder inOrder = inOrder(logicService, mapper);
      inOrder.verify(mapper).fromExternalApi(personnummer);
      inOrder.verify(mapper).toLogic(presentationRequest);
      inOrder.verify(logicService).checkRattTillForsakring(logicRequest);
      inOrder.verify(mapper).toPresentation(logicResponse);
      inOrder.verify(mapper).toExternalApi(presentationResponse, processId);
      verifyNoMoreInteractions(logicService, mapper);
   }
}
