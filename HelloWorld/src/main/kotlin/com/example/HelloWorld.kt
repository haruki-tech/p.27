package com.example

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val app: HttpHandler = routes(
    "/greetings" bind GET to ::greetings,
    "/data" bind Method.POST to ::receiveData,
)

val htmlPage = """
<html>
    <body>
	<h1 style="text-align:center; font-size:3em;" >
Hello Function World!
	</h1>
    </body>
</html>"""


fun greetings(req: Request): Response = Response(OK).body(htmlPage)
fun receiveData(req: Request): Response = Response(CREATED)
    .body("Received: ${req.bodyString()}")

fun main() {
    app.asServer(SunHttp(8080)).start()
}