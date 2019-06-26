package gotopark.buster.chinaLotgen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

@SuppressLint("StaticFieldLeak")
// 메인 엑티비티 텍스트 표시 접근
class Jsoup_Lotto(context: Activity) : AsyncTask<Void, Void?, Void?>() {
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

    //    var f13: Elements? = null
//    var f14: Elements? = null
//    var f15: Elements? = null
//    var f16: Elements? = null
//    var f17: Elements? = null
//    var f18: Elements? = null
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
            f16 = doc.select(".kj_tablelist02 td")
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
        val LotCount: String
        var LotWin: String
        var prize_data: String
        val SUM_lotto_num1: String


//        if (tiTle != null) {

//            var KoLotto = ""
//            for (e in F12) {
//                val alt = e.attr("alt")
//
//                KoLotto += ", $alt"
//            }
//            KoLotto = KoLotto.replace(" ".toRegex(), "")
//
//            KoLotto = KoLotto.substring(1)
//            lotto_num = KoLotto.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//            SUM_lotto_num1 = lotto_num[0] + ", " +
//                    lotto_num[1] + ", " +
//                    lotto_num[2] + ", " +
//                    lotto_num[3] + ", " +
//                    lotto_num[4] + ", " +
//                    lotto_num[5]
//
//
//            SUM_lotto_num = SUM_lotto_num1 + "\n보너스 번호 :" + lotto_num[6]
//
//
//            LotCount = F10.toString().replace("\\<.*?>".toRegex(), "")
//            LotDate = F11.toString().replace("\\<.*?>".toRegex(), "")
//
//            // 로또 당첨 등수
//            LotWin = F13.toString().replace("\\<.*?>".toRegex(), "")
//            LotWin = LotWin.replace("  ".toRegex(), "")
//            LotWin = LotWin.replace("\n".toRegex(), "")
//            LotWin = LotWin.replace("".toRegex(), "")
//            LotWin = LotWin.replace("  ".toRegex(), "\n")
//            LotWin = LotWin.replace("당첨정보 상세보기".toRegex(), "")
//            LotWin = LotWin.replace("₩".toRegex(), " = (₩)")
//
//
//            Log.e("================>", F14.toString())
//            // 다음회차 정보
//            var Linfo = F14.toString().replace("\\<.*?>".toRegex(), "")
//            Linfo = Linfo.replace("\n".toRegex(), "")
//            Log.e("1Linfo================>", Linfo)
//
//            Linfo = Linfo.replace("   ".toRegex(), "\n")
//            Log.e("2Linfo===============>", Linfo)
//
//            Linfo = Linfo.replace("  ".toRegex(), "")
//            Log.e("3Linfo===============>", Linfo)
//            prize_data = F15.toString().replace("\\<.*?>".toRegex(), "")
//            prize_data = prize_data.replace("₩".toRegex(), "")
//            prize_data = prize_data.replace(",".toRegex(), "")
//
//            //                prize_data = String.valueOf(F15);


            val redball = f13.toString().replace("\\<.*?>".toRegex(), "")
            val blueball = f14.toString().replace("\\<.*?>".toRegex(), "")
            var mountmony = f15.toString().replace("\\<.*?>".toRegex(), "")
            val table_1 = f16.toString().replace("\\<.*?>".toRegex(), "")
            val winday = f17.toString().replace("\\<.*?>".toRegex(), "")
            val winday2 = f18.toString().replace("\\<.*?>".toRegex(), "")
            mountmony = mountmony.replace("\u052A", "")
        println("=========aaa$winday")
        println("=========aaa$winday2")
            val Money = mountmony.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val areball = redball.split("\n").toTypedArray()


        Log.d("====areball====", areball[0])
        Log.d("====areball====", areball[1])
        Log.d("====areball====", areball[2])
        Log.d("====areball====", areball[3])
        Log.d("====areball====", areball[4])

            lotto_num = foo(areball, blueball)


            val res = IntArray(7)


//            Model.setWeeknum(lotto_num)


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

        activity.stackMony1.text = ("本期销量：" + Money[0] + "元")
        activity.stackMony2.text = ("奖池滚存：" + Money[1] + "元")
//        }
    }


    fun foo(old: Array<String>, NEW_STRING: String): Array<String?> {
        val result = old.copyOf(old.size + 1)
        result[old.size] = NEW_STRING
        return result

    }
}