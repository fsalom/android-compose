package com.example.learnwithme.helper

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class LoggingInterceptor : Interceptor {
    private var logger = Logger()
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val t1 = System.nanoTime()

        logger.log(String.format("☎️ ➡️ %s con headers: %s",
            request.url,
            request.headers.toString())
        )
        val response: Response = chain.proceed(request)
        val t2 = System.nanoTime()
        val icon = if(response.code in 200..300) "✅" else "❌"
        logger.log(String.format("\uD83D\uDD3D %s [%d] %s en %.1fms",
            icon,
            response.code,
            response.request.url,
            (t2 - t1) / 1e6)
        )
        logger.log(bodyToString(response))
        logger.log(String.format("\uD83D\uDD3C %s [%d] %s en %.1fms",
            icon,
            response.code,
            response.request.url,
            (t2 - t1) / 1e6)
        )
        return response
    }

    private fun bodyToString(response: Response): String {
        if (response.body == null) {
            ""
        }
        return GsonBuilder().setPrettyPrinting().create().toJson(
            JsonParser().parse(
                response.peekBody(Long.MAX_VALUE).string() ?: ""
            )
        )
    }
}