package gotopark.buster.chinaLotgen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import gotopark.buster.chinaLotgen.Module.numtoimg;
import gotopark.buster.chinaLotgen.Module.randomNum;
import gotopark.buster.chinaLotgen.database.DatabaseHelper;
import gotopark.buster.chinaLotgen.qrCodeReader.FullScannerFragmentActivity;

public class MainActivity extends AppCompatActivity {


    public TextView text1;
    public TextView text10;

    private TextView Balltxt1;
    private TextView Balltxt2;
    private TextView Balltxt3;
    private TextView Balltxt4;
    private TextView Balltxt5;
    private TextView Balltxt6;
    private TextView Balltxt7;

    private TextView Rtext1;
    private TextView Rtext2;
    private TextView Rtext3;
    private TextView Rtext4;
    private TextView Rtext5;
    private TextView Rtext6;
    private TextView Rtext7;
    private TextView Rtext8;


    private ImageView RBall1;
    private ImageView RBall2;
    private ImageView RBall3;
    private ImageView RBall4;
    private ImageView RBall5;
    private ImageView RBall6;
    private ImageView RBall8;

    private TextView Rtilte;

    private InterstitialAd mInterstitialAd;

    private Switch sw1, sw2;

    public Model model;
    BackProcessHandler backHandler;
    DatabaseHelper db;


    public String ctextR, ctextRlist;
    String SUM_lotto_num;
    String LotDate;
    int ClickCount = 0;

    int MultiClick;
    int randconut;

    int tak, tok;
    SoundPool soundpool;

    private static final int ZXING_CAMERA_PERMISSION = 1;

    private Button.OnClickListener MDCT = new View.OnClickListener() {
        public void onClick(View v) {
            String url = getString(R.string.mdct_links);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    };

    private Button.OnClickListener Lot_share = new View.OnClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            soundpool.play(tak, 1, 1, 1, 0, 1);

            LotCOPY();
            ctextR = ctextR + "\n" + "彩票日 : " + LotDate;
            ctextR = ctextR + "\n" + SUM_lotto_num + "\n" + getString(R.string.twiter_CH);


            String comText = Balltxt1.getText().toString();

            if (Objects.equals("0", comText)) {

                text1.setText("首先开始生成数字！\n");
                text1.append(getString(R.string.app_Das));

            } else {
                BackProcessHandler.ClipsBoards();

            }
        }
    };


    Button.OnClickListener EXIT = new View.OnClickListener() {

        public void onClick(View v)
        {
            soundpool.play(tak, 1, 1, 1, 0, 1);
            onBackPressed();
        }
    };


    Button.OnClickListener copy = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @SuppressLint("SetTextI18n")
        public void onClick(View arg0) {
            soundpool.play(tak, 1, 1, 1, 0, 1);
            LotCOPY();
            String comText = getString(R.string.Main_text);

            if (Objects.equals(ctextR, comText)) {

                text1.setText("首先开始生成数字！\n");
                text1.append(getString(R.string.app_Das));


            } else {

                ClipData clip = ClipData.newPlainText("复制", ctextR);
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "幸运乐透号码已复制。", Toast.LENGTH_SHORT).show();
                text1.setText("!!! 乐透号码已复制 !!!");
            }
        }
    };

    Button.OnClickListener gen = new View.OnClickListener() {


        int count = 0;
        Random rand = new Random();
        int[] Times_Ran = {150, 250, 350, 550, 650, 850, 950, 1150, 1450, 1550};
        int primeWord = rand.nextInt(13) + 4;
        int millisec;

        public void onClick(View v) {
            soundpool.play(tak, 1, 1, 1, 0, 1);

//            MultiClick = Model.getClick();

//            Log.d("====MultiClick====", String.valueOf(MultiClick));

            if (MultiClick == 1) {
                // 반복 회수 지정

                int xnum = rand.nextInt(9);
                millisec = Times_Ran[xnum];
            } else {


                millisec = 50;
            }

            // 반복 회수 끝

            CountDownTimer start = new CountDownTimer(millisec, 25) {

                @SuppressLint("SetTextI18n")
                public void onTick(long millisUntilFinished) {
                    soundpool.play(tak, 1, 1, 1, 0, 1);
                    text10.setText(" - 小数分析 - " + millisUntilFinished / 25 + "00ms 留。");
                    GenNumber();
                }

                @SuppressLint("SetTextI18n")
                public void onFinish() {

                    text10.setText(getString(R.string.scr_text2));
                    count = count + 1;

                    if (count == primeWord) {
                        String saywords = BackProcessHandler.frontSay();
                        text1.setText(saywords);
                        count = 0;
                        primeWord = rand.nextInt(11) + 3;

                    }
                    ClickCount = 0;
                }

            }.start();
        }
    };


    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Show_front();
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }


    public void launchFullFragmentActivity(View v) {
        launchActivity(FullScannerFragmentActivity.class);

    }


    private void LotCOPY() {
        String App_links1 = getString(R.string.app_Google_Play_links);
        String App_Share = getString(R.string.app_share);

        //텍스트 변환
        String ctext1;
        String ctext2;
        String ctext3;
        String ctext4;
        String ctext5;
        String ctext6;
        String ctext7;

        ctext1 = Balltxt1.getText().toString();
        ctext2 = Balltxt2.getText().toString();
        ctext3 = Balltxt3.getText().toString();
        ctext4 = Balltxt4.getText().toString();
        ctext5 = Balltxt5.getText().toString();
        ctext6 = Balltxt6.getText().toString();
        ctext7 = Balltxt7.getText().toString();


        ctextRlist = ctext1 + ", " + ctext2 + ", " + ctext3 + ", " + ctext4 + ", " + ctext5 + ", " + ctext6 +
                "  (" + ctext7 + ")";

        ctextR = App_Share + ctextRlist + "\n" + App_links1;
    }


    // MDCT  클릭시 브라우저 실행 url  이동하여 보여준다.

    @SuppressLint("CutPasteId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new Model();

        db = new DatabaseHelper(this);
        backHandler = new BackProcessHandler(this);

        soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        tak = soundpool.load(this, R.raw.short_click2, 1);
        tok = soundpool.load(this, R.raw.click1_rebert1, 1);

        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        FloatingActionButton fab = findViewById(R.id.fab);

        // wifi 또는 모바일 네트워크 어느 하나라도 연결이 되어있다면,
        if (wifi.isConnected() || mobile.isConnected()) {

            new LotonumCall().execute();
            Admob_is();
            Admob_Front();
            BackProcessHandler.AnRate();


        } else {

            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            {

                ad.setTitle(getString(R.string.info_net1));
                ad.setMessage(getString(R.string.info_net2));
                ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        dialogInterface.dismiss();
                    }
                });
                ad.show();

            }
        }

        text1 = findViewById(R.id.text1);
        text10 = findViewById(R.id.text10);

        Balltxt1 = findViewById(R.id.balltext1);
        Balltxt2 = findViewById(R.id.balltext2);
        Balltxt3 = findViewById(R.id.balltext3);
        Balltxt4 = findViewById(R.id.balltext4);
        Balltxt5 = findViewById(R.id.balltext5);
        Balltxt6 = findViewById(R.id.balltext6);
        Balltxt7 = findViewById(R.id.balltext7);


        Rtext1 = findViewById(R.id.Rtext1);
        Rtext2 = findViewById(R.id.Rtext2);
        Rtext3 = findViewById(R.id.Rtext3);
        Rtext4 = findViewById(R.id.Rtext4);
        Rtext5 = findViewById(R.id.Rtext5);
        Rtext6 = findViewById(R.id.Rtext6);
        Rtext7 = findViewById(R.id.Rtext7);
        Rtext8 = findViewById(R.id.Rtext8);


        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);
        Button btn4 = findViewById(R.id.button4);
        Button btn5 = findViewById(R.id.button5);
        Button btn6 = findViewById(R.id.button6);


        //========================================================================

        RBall1 = findViewById(R.id.RBall1);
        RBall2 = findViewById(R.id.RBall2);
        RBall3 = findViewById(R.id.RBall3);
        RBall4 = findViewById(R.id.RBall4);
        RBall5 = findViewById(R.id.RBall5);
        RBall6 = findViewById(R.id.RBall6);
        RBall8 = findViewById(R.id.RBall8);

        //========================================================================

        Rtilte = findViewById(R.id.rtitle);

        sw1 = findViewById(R.id.switch1);
        sw2 = findViewById(R.id.switch2);

        /*
         *
         * 시작시 메세지 출력
         *
         * */
        String versionCode = BuildConfig.VERSION_NAME;
        String App_Mame = getString(R.string.app_name);
        text1.setText(getString(R.string.info_Mess));
        text1.append("\n" + getString((R.string.Main_text)));
        //엡 이름과 버젼 표시
        setTitle(App_Mame + " V" + versionCode);

        sw2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                soundpool.play(tak, 10, 10, 1, 0, 0);

                if (sw2.isChecked()) {

                    MultiClick = 1;
//                Model.setClick(MultiClick);

                } else {

                    MultiClick = 0;
//                    Model.setClick(MultiClick);

                }

            }
        });

        btn1.setOnClickListener(EXIT);
        btn2.setOnClickListener(gen);
        btn3.setOnClickListener(copy);
        btn4.setOnClickListener(Lot_share);
        btn5.setOnClickListener(Lot_save);
        btn6.setOnClickListener(Lot_list);
        text10.setOnClickListener(MDCT);


        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundpool.play(tak, 1, 1, 1, 0, 0);
                if (sw1.isChecked()) {

                    randconut = 1;


                } else {

                    randconut = 0;


                }

            }
        });


    }


    private Button.OnClickListener Lot_save = new View.OnClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            soundpool.play(tok, 1, 1, 1, 0, 1);

            if (ClickCount == 0) {

                LotCOPY();

                db.insertColumn(ctextRlist, "");

                ClickCount = 1;

                String Mesg1 = "保存号码已完成";

                //The number has been saved.
                text10.setText(ctextRlist + " -> " + Mesg1);
                Toast.makeText(MainActivity.this, Mesg1, Toast.LENGTH_SHORT).show();

            } else {

                text10.setText("请创建号码。");

            }
        }
    };

    private Button.OnClickListener Lot_list = new View.OnClickListener() {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            soundpool.play(tok, 1, 1, 1, 0, 1);

            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);

        }
    };


    @Override
    protected void onResume() {
        super.onResume();

    }


    public void Admob_is() {

        AdView mAdView = findViewById(R.id.adView);
        MobileAds.initialize(getApplicationContext(), getString(R.string.google_banner_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    public void Admob_Front() {
        Log.e("전면_Front_TEST =====> ", "=======================> OK");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);


    }

    public void Show_front() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }


    }


    @Override
    public void onBackPressed() {

        BackProcessHandler.onBackPressed();
    }


    @SuppressLint({"SetTextI18n", "ResourceType", "Assert"})
    public void GenNumber() {
        int[] res = new int[6];
        String blue_res;

        randomNum Num = new randomNum();
        numtoimg NumtoI = new numtoimg();
        Random random = new Random();
        /** 로또 번호 발생 메소드 */


        if (randconut == 1) {
            assert false;
            res[0] = random.nextInt(6 - 1 + 1) + 1;
            res[1] = random.nextInt(11 - 6 + 1) + 6;
            res[2] = random.nextInt(18 - 12 + 1) + 12;
            res[3] = random.nextInt(24 - 19 + 1) + 19;
            res[4] = random.nextInt(30 - 25 + 1) + 25;
            res[5] = random.nextInt(33 - 31 + 1) + 31;
            blue_res = String.valueOf(random.nextInt(16 - 1 + 1) + 1);


        } else {
            res = Num.lotArray(6, 33);
            blue_res = String.valueOf(random.nextInt(16 - 1 + 1) + 1);
        }


        Balltxt1.setText(String.valueOf(res[0]));
        Balltxt2.setText(String.valueOf(res[1]));
        Balltxt3.setText(String.valueOf(res[2]));
        Balltxt4.setText(String.valueOf(res[3]));
        Balltxt5.setText(String.valueOf(res[4]));
        Balltxt6.setText(String.valueOf(res[5]));
        Balltxt7.setText(blue_res);


    }


    @SuppressLint("StaticFieldLeak")
    public class LotonumCall extends AsyncTask<Void, Void, Void> {


        Elements F10;
        Elements F11;
        Elements F12;
        Elements F13;
        String F14;
        String F15;
        String tiTle;

        @Override
        public Void doInBackground(Void... params) {

//https 설정 ssl hanshare fail 적용 위한 code -->start
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @SuppressLint("TrustAllX509TrustManager")
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = null;
            try {
                sc = SSLContext.getInstance("TLS");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            try {
                assert sc != null;
                sc.init(null, trustAllCerts, new SecureRandom());
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//https 설정 ssl hanshare fail 적용 위한 code --> End

            String url = getString(R.string.JsoupOne);

            try {

                Document document = Jsoup.connect(url)
                        .timeout(10000)
                        .get();


                // Get the html document title
                tiTle = document.title();
                F10 = document.select(getString(R.string.jsoup_q1));
                F11 = document.select(getString(R.string.jsoup_q2));
                F12 = document.select(getString(R.string.jsoup_q3));
                F13 = document.select(getString(R.string.jsoup_q4));
                F14 = String.valueOf(document.select(getString(R.string.jsoup_q5)));
                F15 = String.valueOf(document.select(getString(R.string.jsoup_q6)));


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onPostExecute(Void result) {


            TextView rowview1, rowview2, rowview3;
            TextView rowview4, rowview5, rowview6;
            TextView rowview7, rowview8, rowview9;
            TextView rowview10, rowview11, rowview12;
            TextView rowview13, rowview14, rowview15;

            TextView stackMony1;
            TextView stackMony2;


            rowview1 = findViewById(R.id.rowview1);
            rowview2 = findViewById(R.id.rowview2);
            rowview3 = findViewById(R.id.rowview3);
            rowview4 = findViewById(R.id.rowview4);
            rowview5 = findViewById(R.id.rowview5);
            rowview6 = findViewById(R.id.rowview6);
            rowview7 = findViewById(R.id.rowview7);
            rowview8 = findViewById(R.id.rowview8);
            rowview9 = findViewById(R.id.rowview9);
            rowview10 = findViewById(R.id.rowview10);
            rowview11 = findViewById(R.id.rowview11);
            rowview12 = findViewById(R.id.rowview12);
            rowview13 = findViewById(R.id.rowview13);
            rowview14 = findViewById(R.id.rowview14);
            rowview15 = findViewById(R.id.rowview15);

            stackMony1 = findViewById(R.id.stackMony1);
            stackMony2 = findViewById(R.id.stackMony2);


            if (tiTle != null) {

                //추첨일
                String F10_1 = F10.toString().replaceAll("\\<.*?>", "");
                F10_1 = F10_1.replaceAll("\n", "");
                F10_1 = F10_1.replaceAll(" ", "");


                //쌍색구 번호
                String F11_1 = F11.toString().replaceAll("\\<.*?>", "");
                F11_1 = F11_1.replaceAll(" ", "");
                String[] aF11_1 = F11_1.split("\n");

                //당첨 정보
                String F12_1 = F13.toString().replaceAll("\\<.*?>", "");
                String[] aF12_1 = F12_1.split("\n");

                Log.d("==============", Arrays.toString(aF12_1));

                // 당첨 누적액
                String nowsell_mony = F14.replaceAll("\\<.*?>", "");
                String next1won = F15.replaceAll("\\<.*?>", "");


                //Impresion

                LotDate = F10_1;

                Rtilte.setText(F10_1);


                //당첨번호 표시
                RBall1.setImageResource(R.drawable.ball3);
                RBall2.setImageResource(R.drawable.ball3);
                RBall3.setImageResource(R.drawable.ball3);
                RBall4.setImageResource(R.drawable.ball3);
                RBall5.setImageResource(R.drawable.ball3);
                RBall6.setImageResource(R.drawable.ball3);
                RBall8.setImageResource(R.drawable.ball2);

                Rtext1.setText(String.valueOf(aF11_1[1]));
                Rtext2.setText(String.valueOf(aF11_1[2]));
                Rtext3.setText(String.valueOf(aF11_1[3]));
                Rtext4.setText(String.valueOf(aF11_1[4]));
                Rtext5.setText(String.valueOf(aF11_1[5]));
                Rtext6.setText(String.valueOf(aF11_1[6]));
                Rtext7.setText("+");
                Rtext8.setText(String.valueOf(aF11_1[7]));


                SUM_lotto_num = aF11_1[1] + " ," +
                        aF11_1[2] + " ," +
                        aF11_1[3] + " ," +
                        aF11_1[4] + " ," +
                        aF11_1[5] + " ," +
                        aF11_1[6] + "   (" +
                        aF11_1[7] + ")";


                // 로또넘버 맞추기 위해서 모델로 셋
                Model.setWeeknum(aF11_1);
                // 당첨된 번호 랜덤 번호 생성 부분에 표시
                Balltxt1.setText(String.valueOf(aF11_1[1]));
                Balltxt2.setText(String.valueOf(aF11_1[2]));
                Balltxt3.setText(String.valueOf(aF11_1[3]));
                Balltxt4.setText(String.valueOf(aF11_1[4]));
                Balltxt5.setText(String.valueOf(aF11_1[5]));
                Balltxt6.setText(String.valueOf(aF11_1[6]));
                Balltxt7.setText(String.valueOf(aF11_1[7]));

                rowview1.setText(aF12_1[0]);
                rowview2.setText(aF12_1[1]);
                rowview3.setText(aF12_1[2]);
                rowview4.setText(aF12_1[3]);
                rowview5.setText(aF12_1[4]);
                rowview6.setText(aF12_1[5]);
                rowview7.setText(aF12_1[6]);
                rowview8.setText(aF12_1[7]);
                rowview9.setText(aF12_1[8]);
                rowview10.setText(aF12_1[9]);
                rowview11.setText(aF12_1[10]);
                rowview12.setText(aF12_1[11]);
                rowview13.setText(aF12_1[12]);
                rowview14.setText(aF12_1[13]);
                rowview15.setText(aF12_1[14]);


                stackMony1.setText("本期销售额：" + nowsell_mony + "元");
                stackMony2.setText("下期一等奖奖池累计金额：" + next1won + "元");

            } else {

                /** 네트웍 품질 문제 발생시 메세지 출력 */

                Rtilte.setText(getString(R.string.net_Info1));
                stackMony1.setText(getString(R.string.net_Info2));
                stackMony2.setText(getString(R.string.net_Info3));

            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

}