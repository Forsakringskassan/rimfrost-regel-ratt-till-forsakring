package se.fk.github.regelratttillforsakring.logic.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface LogicRtfResponse
{
   Boolean isBokford();

   Boolean hasArbetsgivare();
}
