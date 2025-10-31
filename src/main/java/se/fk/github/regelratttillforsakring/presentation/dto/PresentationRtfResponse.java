package se.fk.github.regelratttillforsakring.presentation.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface PresentationRtfResponse
{
   Boolean isBokford();

   Boolean hasArbetsgivare();
}
