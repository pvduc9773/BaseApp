package com.ducpv

import com.ducpv.utils.extension.split
import com.google.common.truth.Truth
import org.junit.Test

/**
 * Created by pvduc9773 on 07/10/2022.
 */
class ListExtTest {
    @Test
    fun `test func split`() {
        val original = List(31) { it }
        val splitResult = original.split(4)
        Truth.assertThat(splitResult.size == 4).isTrue()
    }
}
