package com.ducpv

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Created by pvduc9773 on 07/10/2022.
 */
class Test {
    @Test
    fun `test`() {
        val actual = 1 + 1
        val expected = 2
        assertThat(actual == expected).isTrue()
    }
}
