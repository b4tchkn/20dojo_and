package jp.co.cyberagent.dojo2020.test

import jp.co.cyberagent.dojo2020.data.model.Memo

object MemoData {
    val list = mutableListOf(
        Memo("101", "", "", 0.0, "none"),
        Memo("102", "title", "contents", 0.0, "none"),
        Memo(
            "103",
            "title",
            "cccccccccccccccccccccccccccccccccccccc aaaaaaaaaaaaaaaaaaaaaaaaa",
            0.0,
            "none"
        ),

        Memo("104", "", "", -1.1, "none"),
        Memo("105", "title", "contents", -1.1, "none"),
        Memo(
            "106",
            "title",
            "cccccccccccccccccccccccccccccccccccccc aaaaaaaaaaaaaaaaaaaaaaaaa",
            -1.1,
            "none"
        ),

        Memo("107", "", "", 1.1, "none"),
        Memo("108", "title", "contents", 1.1, "none"),
        Memo(
            "109",
            "title",
            "cccccccccccccccccccccccccccccccccccccc aaaaaaaaaaaaaaaaaaaaaaaaa",
            10.1,
            "none"
        )
    )
}