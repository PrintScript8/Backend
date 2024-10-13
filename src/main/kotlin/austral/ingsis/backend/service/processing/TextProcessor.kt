package austral.ingsis.backend.service.processing

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class TextProcessor : FileProcessor {
    override fun process(file: MultipartFile) {
        TODO("Not yet implemented")
    }
}
