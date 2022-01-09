# language: es
@FeatureName:comprarProductos
Característica: Comprar productos
  yo como cliente
  Quiero poder enviar mensajes al equipo de atención al cliente
  Para que sepan mis opiniones.

  @ScenarioName:EnviarMensajesConCamposObligatorios
  Escenario: enviar mensajes con campos obligatorios
    Dado el cliente está en el formulario para enviar un mensaje al servicio al cliente
    Cuando el cliente llene todos los campos requeridos y envíe
    Entonces el cliente debería ver un mensaje de confirmación de envío


  @ScenarioName:enviarCuerpoDeMensajeVacío
  Escenario: enviar cuerpo de mensaje vacío
    Dado  el cliente está en el formulario para enviar un mensaje al servicio al cliente
    Cuando Cuando el cliente complete todos los campos requeridos excepto el mensaje y envíe
    Entonces Entonces el cliente debería ver un mensaje de error de envío