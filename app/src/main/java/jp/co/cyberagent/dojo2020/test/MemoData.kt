package jp.co.cyberagent.dojo2020.test

import jp.co.cyberagent.dojo2020.data.model.Memo

object MemoData {
    val list = mutableListOf(
        Memo("101", "", "", 0L, "none"),
        Memo("102", "title", "contents", 0L, "none"),
        Memo(
            "103",
            "title",
            "cccccccccccccccccccccccccccccccccccccc aaaaaaaaaaaaaaaaaaaaaaaaa",
            0L,
            "none"
        ),

        Memo("104", "", "", -1L, "none"),
        Memo("105", "title", "contents", -1L, "none"),
        Memo(
            "106",
            "title",
            "cccccccccccccccccccccccccccccccccccccc aaaaaaaaaaaaaaaaaaaaaaaaa",
            -10L,
            "none"
        ),


        Memo("107", "", "", 1L, "none"),
        Memo("108", "title", "contents", 1L, "none"),
        Memo(
            "109",
            "title",
            "cccccccccccccccccccccccccccccccccccccc aaaaaaaaaaaaaaaaaaaaaaaaa",
            10L,
            "none"
        )
    )
}