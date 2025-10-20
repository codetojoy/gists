@Grapes([
    @Grab(group='io.rest-assured', module='rest-assured', version='5.3.0'),
    @Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1')
])

import static io.restassured.RestAssured.*
import static io.restassured.matcher.RestAssuredMatchers.*
import static org.hamcrest.Matchers.*

// Set base URI
baseURI = "https://api.example.com"

// Test the /get-items endpoint
given()
    .header("Content-Type", "application/json")
    .queryParam("limit", 10)
.when()
    .get("/get-items")
.then()
    .statusCode(200)
    .body("items", notNullValue())
    .body("items.size()", greaterThan(0))
    .log().all()

println "Test completed successfully!"
