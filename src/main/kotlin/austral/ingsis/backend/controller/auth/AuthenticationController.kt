package austral.ingsis.backend.controller.auth

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

data class LoginRequest(val email: String, val password: String)

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(val restTemplate: RestTemplate) {

    // todo: Where should this credentials be? Does the .env make sense?
    private val dotenv = Dotenv.load()
    private val auth0Domain = dotenv["AUTH_SERVER_URI"]
    private val clientId = dotenv["AUTH_CLIENT_ID"]
    private val clientSecret = dotenv["AUTH_CLIENT_SECRET"]
    private val audience = dotenv["AUTH0_AUDIENCE"]

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {

        // Builds the uri for the Auth0 token endpoint
        val uri = UriComponentsBuilder.fromHttpUrl("$auth0Domain/oauth/token")
            .build().toUri()

        // Builds the body for the request
        val requestBody = mapOf(
            "grant_type" to "password",
            "client_id" to clientId,
            "client_secret" to clientSecret,
            "audience" to audience,
            "username" to loginRequest.email,
            "password" to loginRequest.password,
            "scope" to "read:snippets write:snippets"
        )

        // Sends request to Auth0 to validate credentials
        val response = restTemplate.postForEntity(uri, requestBody, Map::class.java)

        return if (response.statusCode.is2xxSuccessful) {
            // The body includes the credentials that the frontend should store for future validation
            // todo: Should the back store them?
            ResponseEntity.ok(response.body)
        } else {
            ResponseEntity.status(response.statusCode).body("Error: Invalid credentials or error in response")
        }
    }
}