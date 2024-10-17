package austral.ingsis.backend.service.processing

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProcessorFactory {
    @Autowired
    private val textProcessor: TextProcessor? = null

    private val fileProcessorMap: MutableMap<String, FileProcessor?> = HashMap()

    @PostConstruct
    fun init() {
        fileProcessorMap["txt"] = textProcessor
    }

    fun getProcessor(fileType: String): FileProcessor? {
        return fileProcessorMap[fileType]
    }
}
