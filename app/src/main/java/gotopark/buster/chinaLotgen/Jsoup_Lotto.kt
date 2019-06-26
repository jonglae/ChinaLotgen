package gotopark.buster.chinaLotgen

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

@SuppressLint("StaticFieldLeak")
// 메인 엑티비티 텍스트 표시 접근
class Jsoup_Lotto(context: AppCompatActivity) : AsyncTask<Void, Void?, Void?>() {
    // 메인 엑티비티 텍스트 표시 접근
    companion object {

        // 메인 엑티비티 텍스트 표시 접근
        lateinit var activity: MainActivity
        lateinit var SUM_lotto_num: String
        lateinit var LotDate: String


    }

    init {
        activity = context as MainActivity
    }

    lateinit var f13: Elements
    lateinit var f14: Elements
    lateinit var f15: Elements
    lateinit var f16: Elements
    lateinit var f17: Elements
    lateinit var f18: Elements
    var tiTle: String? = null


    public override fun doInBackground(vararg params: Void): Void? {

        val url = "http://kaijiang.500.com/shtml/ssq/"
        val url2 = "https://cp.360.cn/kj/ssq.html"

        try {

            val doc = Jsoup.connect(url)
                    .timeout(0)
                    .get()

            val doc2 = Jsoup.connect(url2)
                    .timeout(0)
                    .get()

            // Get the html document title
            tiTle = doc.select("[title]").toString()
            f13 = doc.select(".ball_red")
            f14 = doc.select(".ball_blue")
            f15 = doc.select(".cfont1")
            f16 = doc2.select(".tar")
            f17 = doc.select(".cfont2")
            f18 = doc2.select("#kaijdata")


        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    @SuppressLint("SetTextI18n")
    override fun onPostExecute(result: Void?) {


        var lotto_num = arrayOfNulls<String>(7)

        if (tiTle != null) {


            val redball = f13.toString().replace("\\<.*?>".toRegex(), "")
            val blueball = f14.toString().replace("\\<.*?>".toRegex(), "")
            var mountmony = f15.toString().replace("\\<.*?>".toRegex(), "")
            val table_1 = f16.toString().replace("\\<.*?>".toRegex(), "")
            val result1 = table_1.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()


            val winday = f17.toString().replace("\\<.*?>".toRegex(), "")
            val winday2 = f18.toString().replace("\\<.*?>".toRegex(), "")
            mountmony = mountmony.replace("\u052A", "")
            println("=========aaa$winday")
            println("=========aaa$winday2")
            val Money = mountmony.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val areball = redball.split("\n").toTypedArray()

//        Log.d("====areball====", areball[0])

            lotto_num = foo(areball, blueball)

            activity.Rtilte.text = "双色球 第 $winday 期\n" + winday2


            // 당첨 번호 표시
            activity.Rtext1.text = lotto_num[0].toString()
            activity.Rtext2.text = lotto_num[1].toString()
            activity.Rtext3.text = lotto_num[2].toString()
            activity.Rtext4.text = lotto_num[3].toString()
            activity.Rtext5.text = lotto_num[4].toString()
            activity.Rtext6.text = lotto_num[5].toString()
            activity.Rtext7.text = "+"
            activity.Rtext8.text = lotto_num[6].toString()


            activity.Rtext1.setBackgroundResource(R.drawable.ball3)
            activity.Rtext2.setBackgroundResource(R.drawable.ball3)
            activity.Rtext3.setBackgroundResource(R.drawable.ball3)
            activity.Rtext4.setBackgroundResource(R.drawable.ball3)
            activity.Rtext5.setBackgroundResource(R.drawable.ball3)
            activity.Rtext6.setBackgroundResource(R.drawable.ball3)
            activity.Rtext8.setBackgroundResource(R.drawable.ball2)


            // 당첨된 번호 랜덤 번호 생성 부분에 표시
            activity.Balltxt1.text = lotto_num[0].toString()
            activity.Balltxt2.text = lotto_num[1].toString()
            activity.Balltxt3.text = lotto_num[2].toString()
            activity.Balltxt4.text = lotto_num[3].toString()
            activity.Balltxt5.text = lotto_num[4].toString()
            activity.Balltxt6.text = lotto_num[5].toString()
            activity.Balltxt7.text = lotto_num[6].toString()

            SUM_lotto_num = lotto_num[0] + " ," +
                    lotto_num[1] + " ," +
                    lotto_num[2] + " ," +
                    lotto_num[3] + " ," +
                    lotto_num[4] + " ," +
                    lotto_num[5] + "   (" +
                    lotto_num[6] + ")";

            // 로또넘버 맞추기 위해서 모델로 셋
            Model.setWeeknum(lotto_num);
            // 당첨된 번호 랜덤 번호 생성 부분에 표시
            activity.rowview1.setText("奖项/中奖条件")
            activity.rowview2.setText("全国中奖注数")
            activity.rowview3.setText("每注奖金")


            activity.rowview4.setText("一等奖:6+1")
            activity.rowview5.setText(result1[3])
            activity.rowview6.setText(result1[5])
            activity.rowview7.setText("二等奖:6+0")
            activity.rowview8.setText(result1[6])
            activity.rowview9.setText(result1[8])
            activity.rowview10.setText("三等奖:5+1")
            activity.rowview11.setText(result1[9])
            activity.rowview12.setText(result1[11])
            activity.rowview13.setText("四等奖:5+0/4+1")
            activity.rowview14.setText(result1[12])
            activity.rowview15.setText(result1[14])


            activity.stackMony1.text = ("本期销量：" + Money[0] + "元")
            activity.stackMony2.text = ("奖池滚存：" + Money[1] + "元")
        }
    }


    fun foo(old: Array<String>, NEW_STRING: String): Array<String?> {
        val result = old.copyOf(old.size + 1)
        result[old.size] = NEW_STRING
        return result

    }
}