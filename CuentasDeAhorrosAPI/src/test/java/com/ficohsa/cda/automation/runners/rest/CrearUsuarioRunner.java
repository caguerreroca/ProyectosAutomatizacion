package com.ficohsa.cda.automation.runners.rest;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features/crear_usuario.feature",
    glue = {"com/ficohsa/cda/automation/stepdefinitions"})
public class CrearUsuarioRunner {}
