package se.fk.github.regelratttillforsakring.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "se.fk.github.regelratttillforsakring.integration")
public class ArchUnitSmokeTest
{
   @ArchTest
   static final ArchRule noCycles = slices().matching("se.fk.github.regelratttillforsakring.integration.(*)..").should()
         .beFreeOfCycles();
}
