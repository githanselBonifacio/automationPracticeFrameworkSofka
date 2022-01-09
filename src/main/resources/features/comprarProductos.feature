# language: es
@FeatureName:comprarProductos
Característica: Comprar productos
    Como cliente en línea
    quiero poder comprar en linea
    para no salir  de casa.

@ScenarioName:validacionPrecioTotal
Escenario: validación de precio total facturado
 Dado que el cliente ha iniciado sesión
 Cuando agregue productos al carrito y se dirija al carrito de compras
 Entonces la suma de los precios debe ser igual a la calculada por el sistema


 @ScenarioName:validarTerminosYCondiciones
 Escenario: el usuario no acepta los terminos y condiciones
   Dado  que el cliente ha iniciado sesión
   Cuando agregue productos al carrito y se dirija al carrito de compras
   E intente seguir a la sección de pagos sin aceptar los terminos y condiciones
   Entonces se mostrará un mensaje de error