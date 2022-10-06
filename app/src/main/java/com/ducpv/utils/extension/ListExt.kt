package com.ducpv.utils.extension

/**
 * Created by pvduc9773 on 07/10/2022.
 */
fun <T> List<T>.split(size: Int): List<List<T>> {
    val n = size.coerceAtLeast(1)
    val chunkSize = (this.size + n - 1) / n
    return chunked(chunkSize.coerceAtLeast(1))
}
