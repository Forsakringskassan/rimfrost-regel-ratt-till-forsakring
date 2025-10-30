package se.fk.github.regelratttillforsakring.logic.dto;

import org.immutables.value.Value;

@Value.Immutable
public interface LogicFolkbokfordResponse
{
   Boolean isBokford();

   Boolean hasArbetsgivare();
}
