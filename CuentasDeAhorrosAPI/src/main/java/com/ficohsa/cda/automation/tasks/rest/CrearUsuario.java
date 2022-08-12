package com.ficohsa.cda.automation.tasks.rest;

import com.ficohsa.cda.automation.models.Empleado;
import com.ficohsa.cda.automation.utils.UtilidadesRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.apache.hc.core5.annotation.Internal;

public class CrearUsuario implements Task {

  private static final String ENDPOINT = "/api/users";
  public static final String VARIABLE_SESION_EMPLEADO = "Variable sesion empleado";
  private Empleado datosEmpleado;

  public CrearUsuario(Empleado datosEmpleado) {

    this.datosEmpleado = datosEmpleado;
  }

  @Internal
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Post.to(ENDPOINT)
            .with(
                request ->
                    request
                        .header("Content-Type", "application/json")
                        .body(UtilidadesRest.obtenerJson(datosEmpleado))));
    actor.remember(VARIABLE_SESION_EMPLEADO, datosEmpleado);
  }

  public static CrearUsuario enElSistema(Empleado empleado) {
    return Tasks.instrumented(CrearUsuario.class, empleado);
  }
}

