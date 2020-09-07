package jp.co.cyberagent.dojo2020

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Context
// Kotlin Android Extensions
import kotlinx.android.synthetic.main.profile_tab.*

class ProfileFragment : Fragment() {

    //ビューを作成する関数
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_tab, container, false)
    }

    //ビューができたらここの処理(onCreate同様)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //SharedPreferenceを使うためのインスタンス
        val dataStore: SharedPreferences? = activity?.getPreferences(Context.MODE_PRIVATE)
        //ボタンの押された状態を管理するフラグ
        var param = 1

        //端末に保存された前回のアプリデータをgetStringで取得する。なければdefValueの値をとってくる
        person_name.text = dataStore?.getString("name", "名前")
        github_account.text = dataStore?.getString("github_account", "githubアカウント")
        twitter_account.text = dataStore?.getString("twitter_account", "twitterアカウント")



        edit_button.setOnClickListener {

            //編集ボタンを押した時の処理

            if (param == 1) {
                //TextView飛ばしてEditTextを表示させる
                person_name.visibility = View.GONE
                github_account.visibility = View.GONE
                twitter_account.visibility = View.GONE

                edit_name.visibility = View.VISIBLE
                edit_github_account.visibility = View.VISIBLE
                edit_twitter_account.visibility = View.VISIBLE

                param = 0

            } else {
                //もう一度編集ボタンを押すとTextViewに戻る

                person_name.text = edit_name.text
                github_account.text = edit_github_account.text
                twitter_account.text = edit_twitter_account.text

                if (dataStore != null) {
                    //SharedPreferenceに登録したデータを保存
                    with(dataStore.edit()) {
                        putString("name", person_name.text.toString())
                        putString("github_account", github_account.text.toString())
                        putString("twitter_account", twitter_account.text.toString())
                        apply()
                    }
                }

                //EditText飛ばしてTextViewを表示させる
                edit_name.visibility = View.GONE
                edit_github_account.visibility = View.GONE
                edit_twitter_account.visibility = View.GONE

                person_name.visibility = View.VISIBLE
                github_account.visibility = View.VISIBLE
                twitter_account.visibility = View.VISIBLE

                param = 1

            }
        }
    }
}