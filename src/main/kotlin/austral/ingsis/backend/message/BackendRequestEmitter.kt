package austral.ingsis.backend.message

import org.austral.ingsis.redis.RedisStreamProducer
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate

class BackendRequestEmitter(
    @Value("geda") streamKey: String,
    redis: RedisTemplate<String, String>,
    ): RedisStreamProducer(streamKey, redis)  {

    fun emitRequest(language: String, code: String, action: String, inputs: String) {
        val request = ExecuteRequest(language, code, action, inputs)
        emit(request)
    }
}
