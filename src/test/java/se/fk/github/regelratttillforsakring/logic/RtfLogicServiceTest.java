package se.fk.github.regelratttillforsakring.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.fk.github.regelratttillforsakring.integration.ArbetsgivareApiService;
import se.fk.github.regelratttillforsakring.integration.FolkbokfordApiService;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableArbetsgivareApiRequest;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableArbetsgivareApiResponse;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableFolkbokfordApiRequest;
import se.fk.github.regelratttillforsakring.integration.dto.ImmutableFolkbokfordApiResponse;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfRequest;
import se.fk.github.regelratttillforsakring.logic.dto.ImmutableLogicRtfResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(
{
      MockitoExtension.class
})
public class RtfLogicServiceTest
{
   @Mock
   FolkbokfordApiService folkbokfordApiService;
   @Mock
   ArbetsgivareApiService arbetsgivareApiService;
   @Mock
   LogicMapper logicMapper;

   @InjectMocks
   RtfLogicService logic;

   @Test
   void checkRattTillForsakring_shouldMapCallBothServices_andReturnMappedResult()
   {
      // Arrange
      String personnummer = "12345678-1234";
      var request = ImmutableLogicRtfRequest.builder()
            .personnummer(personnummer).build();
      var fbReq = ImmutableFolkbokfordApiRequest.builder()
            .personnummer(personnummer).build();
      var agReq = ImmutableArbetsgivareApiRequest.builder()
            .personnummer(personnummer).build();

      var fbResp = ImmutableFolkbokfordApiResponse.builder()
            .isFolkbokford(true).build();
      var agResp = ImmutableArbetsgivareApiResponse.builder()
            .hasArbetsgivare(true).build();

      var expected = ImmutableLogicRtfResponse.builder()
            .isBokford(true)
            .hasArbetsgivare(true)
            .build();

      when(logicMapper.toFolkbokfordIntegration(request)).thenReturn(fbReq);
      when(logicMapper.toArbetsgivareIntegration(request)).thenReturn(agReq);
      when(folkbokfordApiService.checkFolkbokford(fbReq)).thenReturn(fbResp);
      when(arbetsgivareApiService.checkArbetsgivare(agReq)).thenReturn(agResp);
      when(logicMapper.toLogic(fbResp, agResp)).thenReturn(expected);

      // Act
      var actual = logic.checkRattTillForsakring(request);

      // Assert
      assertEquals(expected, actual);

      InOrder inOrder = inOrder(folkbokfordApiService, arbetsgivareApiService, logicMapper);
      inOrder.verify(logicMapper).toFolkbokfordIntegration(request);
      inOrder.verify(logicMapper).toArbetsgivareIntegration(request);
      inOrder.verify(folkbokfordApiService).checkFolkbokford(fbReq);
      inOrder.verify(arbetsgivareApiService).checkArbetsgivare(agReq);
      inOrder.verify(logicMapper).toLogic(fbResp, agResp);
      verifyNoMoreInteractions(folkbokfordApiService, arbetsgivareApiService, logicMapper);
   }
}
