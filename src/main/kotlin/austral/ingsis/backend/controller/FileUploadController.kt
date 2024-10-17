package austral.ingsis.backend.controller

import austral.ingsis.backend.service.processing.FileProcessingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/upload")
class FileUploadController {
    @Autowired
    private val fileProcessingService: FileProcessingService = FileProcessingService()

    @PostMapping
    fun uploadFile(@RequestParam("file") file: MultipartFile?): ResponseEntity<String> {
        if (file == null) {
            return ResponseEntity.badRequest().body("File could not be uploaded as it was received null")
        }
        val response = fileProcessingService.processFile(file)
        return ResponseEntity.ok("File uploaded successfully")
    }
}
