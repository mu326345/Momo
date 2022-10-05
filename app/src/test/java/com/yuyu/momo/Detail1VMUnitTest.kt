package com.yuyu.momo

import com.google.common.truth.Truth.assertThat
import com.yuyu.momo.data.AResultItem
import com.yuyu.momo.data.ImportDate
import com.yuyu.momo.data.ResultItem
import com.yuyu.momo.detail1.Detail1ViewModel
import com.yuyu.momo.repository.DefaultRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class Detail1VMUnitTest {

    @Mock
    private lateinit var repository: DefaultRepository
    private lateinit var viewModel: Detail1ViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = Detail1ViewModel(repository)
    }

    @Test
    fun testFilterSameAnimal() {
        val item1 = genResultItem("")
        val item2 = genResultItem("大熊貓館")
        val item3 = genResultItem("企鵝館")
        val item4 = genResultItem("沙漠動物區")
        val item5 = genResultItem("大熊貓館")

        // test empty list
        assertThat(viewModel.testFilterSameAnimal(listOf())).isEmpty()
        // test result item not init
        assertThat(viewModel.testFilterSameAnimal(listOf(item1))).isEmpty()


        val list = listOf(item1)
        viewModel.setBundleData(
            ResultItem(
                1,
                ImportDate("", 1, ""),
                "",
                "",
                "大熊貓館",
                "",
                "",
                "",
                "",
                ""
            )
        )
        val result = viewModel.testFilterSameAnimal(list)
        // test data mismatch
        assertThat(result).isEmpty()

        val list2 = listOf(item1, item2, item3, item4, item5)
        // test data mismatch
        assertThat(viewModel.testFilterSameAnimal(list2).size).isEqualTo(2)
        assertThat(viewModel.testFilterSameAnimal(list2).contains(item2)).isTrue()
        assertThat(viewModel.testFilterSameAnimal(list2).contains(item5)).isTrue()
    }

    private fun genResultItem(location: String): AResultItem {
        return AResultItem(
            1,
            ImportDate("2", 1, ""),
            "", "", "", "",
            "", location, "", "", "",
            "", "", "", "", "", "",
            "", "", "", "", "", "",
            "", "", "", "", "", "", "",
            "", "", "", "", "", "", "",
            "", "", "", "", "", "",
            "", "", "", "", ""
        )
    }
}