package austral.ingsis.backend

import austral.ingsis.backend.controller.FileUploadController
import austral.ingsis.backend.service.processing.FileProcessingService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(FileUploadController::class)
class FileUploadControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    @Suppress("UnusedPrivateProperty")
    private lateinit var fileProcessingService: FileProcessingService

    @Test
    fun `should respond with bad request when file is null`() {
        mockMvc.perform(multipart("/file/upload"))
            .andExpect(status().isBadRequest)
            .andExpect(content().string("File could not be uploaded as it was received null"))
    }

    @Test
    fun `should respond with success message when file is uploaded`() {
        val file = MockMultipartFile("file", "test.txt", "text/plain", "test content".toByteArray())
        mockMvc.perform(multipart("/file/upload").file(file))
            .andExpect(status().isOk)
            .andExpect(content().string("File uploaded successfully"))
    }
}
