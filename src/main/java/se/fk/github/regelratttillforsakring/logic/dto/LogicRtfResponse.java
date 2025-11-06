package se.fk.github.regelratttillforsakring.logic.dto;

import org.immutables.value.Value;

import se.fk.rimfrost.api.vahregelrtfspec.RattTillForsakring;

@Value.Immutable
public interface LogicRtfResponse
{
   RattTillForsakring rattTillForsakring();
}
