@Grapes([
    @Grab(group='io.rest-assured', module='rest-assured', version='5.3.0')
])

import static io.restassured.RestAssured.*
import static org.hamcrest.Matchers.*
import io.restassured.response.Response

// Set base URI
baseURI = "https://api.example.com"

// Step 1: Login and capture session ID
println "Step 1: Logging in..."

Response loginResponse = given()
    .header("Content-Type", "application/json")
    .body('{"username": "testuser", "password": "testpass"}')
.when()
    .post("/login")
.then()
    .statusCode(200)
    .body("session_id", notNullValue())
    .log().all()
    .extract().response()

// Extract session ID from response
String sessionId = loginResponse.path("session_id")
println "Session ID obtained: ${sessionId}"

// Step 2: Use session ID to get items
println "\nStep 2: Fetching items with session..."

given()
    .header("Content-Type", "application/json")
    .cookie("session-id", sessionId)  // Use session ID in cookie
    .queryParam("limit", 10)
.when()
    .get("/get-items")
.then()
    .statusCode(200)
    .body("items", notNullValue())
    .body("items.size()", greaterThan(0))
    .log().all()

println "\nTest completed successfully!"
