package com.kestraa.crud

import org.http4k.core.Method.*
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.lens.Query
import org.http4k.routing.routes
import org.http4k.routing.bind
import org.http4k.server.ApacheServer
import org.http4k.server.asServer
import java.time.LocalDateTime
import java.util.*

typealias HttpHandler = (Request) -> Response


fun main() {
    var companies = Companies.data()

    val indexHandler: HttpHandler = {
        Response(Status.OK).body("$companies")
    }

    val addCompany: HttpHandler = { request ->
        val nameParam = Query.required("name")
        val emailParam = Query.required("email")
        val contactParam = Query.required("contact")

        val name = nameParam(request)
        val email = emailParam(request)
        val contact = contactParam(request)

        val responseAdd = DataCompany(Random(1000).nextLong(), "$name", "$email", "$contact", LocalDateTime.now())
        companies.add(responseAdd)

        Response(Status.CREATED).body("$companies")
    }

    val app = routes(
            "/companies/" bind GET to indexHandler,
            "/companies/" bind POST to addCompany
    )

    app.asServer(ApacheServer(8080)).start()
}
