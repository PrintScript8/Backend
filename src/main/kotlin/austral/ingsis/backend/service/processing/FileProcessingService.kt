package austral.ingsis.backend.service.processing

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FileProcessingService {

    val processorFactory = ProcessorFactory()

    fun processFile(file: MultipartFile): Pair<Boolean, String> {
        return try {
            val originalFilename = file.originalFilename
            val extension = originalFilename?.substringAfterLast('.', "")
            val processor: FileProcessor? = extension?.let { processorFactory.getProcessor(it.lowercase()) }
            if (processor == null) {
                Pair(false, "Error: Filetype $extension")
            } else {
                // Implement the processing logic here
                Pair(true, "File processed successfully")
            }
        } catch (error: Exception) {
            Pair(false, "Error processing file: ${error.message}")
        }
    }
}
