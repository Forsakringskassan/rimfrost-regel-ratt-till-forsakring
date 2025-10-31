package se.fk.github.regelratttillforsakring.integration.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface IntegrationRtfRequest
{
   String personnummer();
}
