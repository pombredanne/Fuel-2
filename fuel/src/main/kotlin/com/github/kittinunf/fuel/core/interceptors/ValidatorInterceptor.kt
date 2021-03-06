package com.github.kittinunf.fuel.core.interceptors

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.HttpException
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response

fun validatorResponseInterceptor(validRange: IntRange) =
        { next: (Request, Response) -> Response ->
            { request: Request, response: Response ->
                if (!validRange.contains(response.statusCode)) {
                    val error = FuelError(HttpException(response.statusCode, response.responseMessage), response.data, response)
                    throw error
                } else {
                    next(request, response)
                }
            }
        }

