package io.github.z0li

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class ExampleSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://computer-database.gatling.io")
    .acceptHeader("text/html")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val feeder = ssv("computers.csv").circular

  val scn = scenario("ExampleSimulation")
    .feed(feeder)
    .exec(
      http("Home")
        .get("/")
        .check(status.is(200))
    )
    .repeat(10) {
      exec(
        http("Search")
          .get("/computers?f={computerName}")
          .check(status.is(200))
      ).pause(500.millisecond)
    }

  setUp(
    scn.inject(
      rampUsers(10) during 10.seconds
    )
  )
    .protocols(httpProtocol)
    .maxDuration(10.minutes)
    .assertions(
      global.responseTime.mean.lt(100),
      forAll.failedRequests.percent.lt(1)
    )
}