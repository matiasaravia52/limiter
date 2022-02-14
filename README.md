# limiter

challenge lemon

## Contexto

El objetivo de este challenge es montar un server que exponga un endpoint `GET /message` a partir del cual se devuelva un mensaje del servicio de [Fuck Off as a Service](https://www.foaas.com/). El mensaje a obtener de este servicio esta totalmente a eleccion del candidato. 

La unica restriccion es que un usuario que consuma este endpoint pueda utilizarlo HASTA 5 veces dentro de un periodo de 10 segundos. 

No es necesario modelar ningun tipo de usuario, se puede utilizar el metodo de autenticacion que la persona mejor considere. Ninguna parte de la autenticacion sera tomada en cuenta para la evaluacion del ejercicio asi que se puede implementar algo tan simple como un header con un userId inventado.

- Se espera que se implemente el llamado a la API de foaas.
- Se espera que se implemente la lógica del limiter.

## Casos de Uso a evaluar

- Se consume una vez la API con un userId determinado y devuelve el mensaje del servicio
- Se consume la API 5 veces dentro de un periodo de 10 segundos y esta devuelve los 5 mensajes del servicio
- Se consume la API 6 veces dentro de un periodo de 10 segundos y el sexto llamado devuelve un error.
- Se consume la API 6 veces dentro de un periodo de 10 segundos, se hace un septimo llamado 10 segundos despues del primer llamado y este devuelve un mensaje del servicio
