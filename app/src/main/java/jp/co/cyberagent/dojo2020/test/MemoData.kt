package jp.co.cyberagent.dojo2020.test

import jp.co.cyberagent.dojo2020.data.model.Memo

object MemoData {
    val list = mutableListOf(
        Memo("101", "", "", 0.0, "none"),
        Memo("102", "title", "contents", 0.0, "none"),
        Memo(
            "102",
            "title",
            "cccccccccccccccccccccccccccccccccccccc aaaaaaaaaaaaaaaaaaaaaaaaa",
            0.0,
            "none"
        ),

        Memo("103", "", "", -1.1, "none"),
        Memo("103", "title", "contents", -1.1, "none"),
        Memo(
            "103",
            "title",
            "cccccccccccccccccccccccccccccccccccccc aaaaaaaaaaaaaaaaaaaaaaaaa",
            -1.1,
            "none"
        ),

        Memo("103", "", "", 1.1, "none"),
        Memo("103", "title", "contents", 1.1, "none"),
        Memo(
            "103",
            "title",
            "cccccccccccccccccccccccccccccccccccccc aaaaaaaaaaaaaaaaaaaaaaaaa",
            1.1,
            "none"
        )
    )
}