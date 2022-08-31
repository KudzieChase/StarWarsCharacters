package com.kudzaichasinda.starwarscharacters.data.network.util // ktlint-disable filename

/**
 * This function fetches our JSON file from our resources folder and reads it
 * */
internal fun getJsonString(path: String): String {
    return ClassLoader.getSystemResource(path).readText()
}
