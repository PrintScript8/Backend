package austral.ingsis.backend.service.processing

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FileProcessingService {

    val processorFactory = ProcessorFactory()

    fun processFile(file: MultipartFile) {
        val originalFilename = file.originalFilename
        val extension = originalFilename?.substringAfterLast('.', "")
        val processor: FileProcessor? = extension?.let { processorFactory.getProcessor(it.lowercase()) }
        if (processor == null){
            // error porque no se sabe procesar
        }
        // usar filetype en el factory "getProcessor(filetype)"
        // todo: implement method
    }
}
