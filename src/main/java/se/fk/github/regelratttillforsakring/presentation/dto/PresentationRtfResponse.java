package se.fk.github.regelratttillforsakring.presentation.dto;

import org.immutables.value.Value;

import se.fk.rimfrost.api.vahregelrtfspec.RattTillForsakring;

@Value.Immutable
public interface PresentationRtfResponse
{
   RattTillForsakring rattTillForsakring();
}
