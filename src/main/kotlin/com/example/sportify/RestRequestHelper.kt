package com.example.sportify

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

/**
 * An abstract helper class to avoid boilerplate code when making REST requests
 *
 * @param objectMapper The Jackson object mapper
 * @param restTemplateBuilder The RestTemplateBuilder
 */
abstract class RestRequestHelper(
    val objectMapper: ObjectMapper,
    restTemplateBuilder: RestTemplateBuilder,
) {
    var restTemplate: RestTemplate = restTemplateBuilder.errorHandler(RestTemplateResponseErrorHandler()).build()

    /**
     * A generic method to make a GET request to an upstream service
     *
     * @param url The URL to make the request to
     * @param headers The headers to send with the request
     * @return The response from the upstream service as the specified type
     */
    inline fun <reified Response> get(url: String, headers: HttpHeaders): Response {
        val request = HttpEntity<HttpHeaders>(headers)
        val response = restTemplate.getForEntity<Response>(url, request)

        return requireNotNull(response.body) {
            "Upstream service returned a null response"
        }
    }

    /**
     * A generic method to make a POST request to an upstream service
     *
     * @param url The URL to make the request to
     * @param headers The headers to send with the request
     * @param body The body of the request
     * @return The response from the upstream service as the specified type
     */
    inline fun <reified Request, reified Response> post(url: String, headers: HttpHeaders, body: Request): Response {
        val request = HttpEntity(body, headers)
        val response = restTemplate.postForEntity(url, request, Response::class.java)

        return requireNotNull(response.body) {
            "Upstream service returned a null response"
        }
    }

}