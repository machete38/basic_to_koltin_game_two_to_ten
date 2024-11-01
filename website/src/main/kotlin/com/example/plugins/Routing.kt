package com.example.plugins

import Game
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Application.configureRouting() {
    val txtList: ArrayList<String> = ArrayList()
    var glType = 0
    var b: Int? = 0
    val game = object: Game(){
        override fun textInput(type: Int, B: Int?) {
            glType = type
            b = B
        }

        override fun textOutput(text: String) {
          txtList.add(text)
        }

        override fun finish() {

        }
    }
    routing {
        post("/") {
            val formParameters = call.receiveParameters()
            val inputText = formParameters["textInp"]
            if (inputText != null) {
                game.continueThread(inputText, glType, b)
                call.respondRedirect("/")
            } else {
                call.respond(HttpStatusCode.BadRequest, "No input provided")
            }
        }
        get("/") {
                call.respondHtml(HttpStatusCode.OK) {
                        body {
                            p{
                                for (i in txtList.indices)
                                {
                                    br { +txtList.get(i) }
                                }
                            }
                            form{
                                method=FormMethod.post
                                action="/"
                                input{
                                    name="textInp"
                                    id="textInp"
                                }
                                input { type=InputType.submit }
                            }

                        }
                }


        }


        staticResources("/static", "static")
    }

}







