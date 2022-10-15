package com.kudzaichasinda.starwarscharacters.data.network

import com.kudzaichasinda.starwarscharacters.data.network.Constants.CHARACTER_URL
import com.kudzaichasinda.starwarscharacters.data.network.Constants.FILMS_URL
import com.kudzaichasinda.starwarscharacters.data.network.Constants.PLANETS_URL
import com.kudzaichasinda.starwarscharacters.data.network.Constants.SEARCH_QUERY_FAIL_URL
import com.kudzaichasinda.starwarscharacters.data.network.Constants.SEARCH_QUERY_URL
import com.kudzaichasinda.starwarscharacters.data.network.Constants.SPECIE_URL
import com.kudzaichasinda.starwarscharacters.data.network.Constants.URL_NOT_FOUND
import com.kudzaichasinda.starwarscharacters.data.network.util.getJsonString
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class ServiceDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            CHARACTER_URL -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        getJsonString("mock/mock_character.json")
                    )
            }
            FILMS_URL -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        getJsonString("mock/mock_film.json")
                    )
            }
            PLANETS_URL -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        getJsonString("mock/mock_planet.json")
                    )
            }
            SPECIE_URL -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        getJsonString("mock/mock_specie.json")
                    )
            }
            SEARCH_QUERY_URL -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        getJsonString("mock/mock_search_success.json")
                    )
            }
            SEARCH_QUERY_FAIL_URL -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        getJsonString("mock/mock_search_empty.json")
                    )
            }
            URL_NOT_FOUND -> {
                MockResponse()
                    .setResponseCode(404)
                    .setBody(
                        getJsonString("mock/mock_not_found.json")
                    )
            }
            else -> throw IllegalArgumentException("${request.path} not found")
        }
    }
}
