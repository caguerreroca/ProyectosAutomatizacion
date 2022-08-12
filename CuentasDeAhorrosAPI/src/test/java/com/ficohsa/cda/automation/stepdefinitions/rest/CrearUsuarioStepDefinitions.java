package com.ficohsa.cda.automation.stepdefinitions.rest;

import static com.ficohsa.cda.automation.exceptions.CodigoRespuestaEsperadaIncorrectoException.CODIGO_ESPERADO_INCORRECTO;
import static com.ficohsa.cda.automation.exceptions.ValoresEsperadosIncorrectosException.VALORES_ESPERADOS_INCORRECTOS;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import com.ficohsa.cda.automation.exceptions.CodigoRespuestaEsperadaIncorrectoException;
import com.ficohsa.cda.automation.exceptions.ValoresEsperadosIncorrectosException;
import com.ficohsa.cda.automation.models.Empleado;
import com.ficohsa.cda.automation.questions.VerificarCodigoRespuesta;
import com.ficohsa.cda.automation.questions.rest.VerificarCreacionUsuario;
import com.ficohsa.cda.automation.tasks.rest.CrearUsuario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

public class CrearUsuarioStepDefinitions {

  private EnvironmentVariables environmentVariables;

  @Cuando("^(.*) crea su usuario con su nombre y su puesto de trabajo que es: (.*)$")
  public void crearUsuario(String nombreActor, String puestoTrabajo) {
    theActorCalled(nombreActor)
        .whoCan(CallAnApi.at(environmentVariables.getProperty("api.rest.baseUrl")))
        .attemptsTo(CrearUsuario.enElSistema(Empleado.builder().name(nombreActor).job(puestoTrabajo).build()));
  }

  @Entonces("^el observa que el usuario es creado exitosamente$")
  public void observarCreacionUsuarioExitoso() {
    theActorInTheSpotlight()
        .should(
            seeThat(VerificarCodigoRespuesta.delServicio(201))
                .orComplainWith(
                    CodigoRespuestaEsperadaIncorrectoException.class, CODIGO_ESPERADO_INCORRECTO));
    theActorInTheSpotlight()
        .should(
            seeThat(VerificarCreacionUsuario.deLaRespuesta())
                .orComplainWith(
                    ValoresEsperadosIncorrectosException.class, VALORES_ESPERADOS_INCORRECTOS));
  }
}
