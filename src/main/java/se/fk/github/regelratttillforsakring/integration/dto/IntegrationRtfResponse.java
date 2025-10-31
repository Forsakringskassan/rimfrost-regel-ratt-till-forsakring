package se.fk.github.regelratttillforsakring.integration.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface IntegrationRtfResponse
{
   Boolean isBokford();

   Boolean hasArbetsgivare();
}
