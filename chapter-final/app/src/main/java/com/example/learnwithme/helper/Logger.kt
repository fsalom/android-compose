package com.example.learnwithme.helper

import android.util.Log
import com.example.learnwithme.configuration.LOGGER_IDENTIFIER
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.Request
import okhttp3.Response

class Logger(private val identifier: String, private val style: Logger.Style) {
    enum class Style {
        COMPLETE, SHORT
    }
    fun log(message: String) {
        Log.i(identifier, message)
    }

    fun log(request: Request, response: Response, duration: Double) {
        logRequest(request)
        logResponse(response, duration)
    }

    private fun logRequest(request: Request) {
        log(String.format("☎️ ➡️ %s con headers: [%s]",
            request.url,
            request.headers.toString())
        )
    }

    private fun logResponse(response: Response, duration: Double) {
        val icon = if(response.code in 200..300) "✅" else "❌"
        log(String.format("\uD83D\uDD3D %s [%d] %s en %.1fms",
            icon,
            response.code,
            response.request.url,
            duration
        )
        )
        if (style == Style.COMPLETE) {
            log(bodyToString(response))
            Log.i(
                LOGGER_IDENTIFIER, String.format(
                    "\uD83D\uDD3C %s [%d] %s en %.1fms",
                    icon,
                    response.code,
                    response.request.url,
                    duration
                )
            )
        }
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
