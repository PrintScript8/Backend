package austral.ingsis.backend.service.processing

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FileProcessingService {

    val processorFactory = ProcessorFactory()

    fun processFile(file: MultipartFile) {
        // obtener filetype
        // usar filetype en el factory "getProcessor(filetype)"
        // todo: implement method
    }
}