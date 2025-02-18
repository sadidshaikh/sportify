package com.example.sportify

import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.client.ResponseErrorHandler
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream


@Component
class RestTemplateResponseErrorHandler : ResponseErrorHandler {
    @Throws(IOException::class)
    override fun hasError(httpResponse: ClientHttpResponse): Boolean {
        return httpResponse.statusCode.is4xxClientError || httpResponse.statusCode.is5xxServerError
    }

    fun InputStream.asMap() = BufferedReader(this.reader()).readText()
}