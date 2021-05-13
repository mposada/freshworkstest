package com.mposadar.freshworkstest.api.model

import com.mposadar.freshworkstest.model.Gif

data class ApiResponse(val data: List<Gif>, val pagination: Pagination)
