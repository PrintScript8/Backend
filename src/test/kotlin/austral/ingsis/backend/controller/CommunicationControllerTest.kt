package austral.ingsis.backend.controller

import austral.ingsis.backend.message.RedisMessageEmitter
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CommunicationController::class)
class CommunicationControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Suppress("UnusedPrivateProperty")
    @MockBean
    private lateinit var redisMessageEmitter: RedisMessageEmitter

    @Test
    fun `should respond with greetings message`() {
        mockMvc.perform(get("/testRespondMessage"))
            .andExpect(status().isOk)
            .andExpect(content().string("Greetings from Backend!"))
    }

    @Test
    fun `should respond with online `() {
        mockMvc.perform(get("/testConnection"))
            .andExpect((status().isOk))
            .andExpect(content().string("Backend is online"))
    }

    @Test
    fun `should emit a request to redis`() {
        mockMvc.perform(
            post("/redis/test")
                .param("language", "test")
                .param("code", "test")
                .param("action", "test")
                .param("inputs", "test"),
        )
            .andExpect(status().isOk)
            .andExpect(content().string("Redis is working"))
    }
}
