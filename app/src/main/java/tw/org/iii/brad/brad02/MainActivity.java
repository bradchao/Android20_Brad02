package tw.org.iii.brad.brad02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private TextView log;
    private Button guess;
    private String answer;
    private AlertDialog alertDialog = null;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("brad", "onCreate()");

        input = findViewById(R.id.input);
        log = findViewById(R.id.log);
        guess = findViewById(R.id.guess);
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guess();
            }
        });

        initNewGame(null);
    }

    private void showDialog(boolean isWinner, String mesg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(isWinner?"WINNER":"Loser");
        builder.setMessage(mesg);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                initNewGame(null);
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    private void guess(){
        counter++;
        String strInput = input.getText().toString();
        String result = checkAB(strInput);
        log.append(counter + ". " +strInput + " => " + result + "\n");
        input.setText("");

        if (result.equals(guessDig + "A0B")){
            showDialog(true, "WINNER");
        }else if (counter == 10){
            showDialog(false, "the answer is " + answer);
        }

    }

    public void initNewGame(View view){
        answer = createAnswer(guessDig);
        input.setText("");
        log.setText("");
        counter = 0;
        Log.v("brad", "answer = " + answer);
    }

    private String createAnswer(int d){
        int[] poker = new int[10];
        for (int i=0; i<poker.length; i++)poker[i]=i;

        for (int i = poker.length-1; i>0; i--) {
            int rand = (int)(Math.random()*(i+1));
            int temp = poker[rand];
            poker[rand] = poker[i];
            poker[i] = temp;
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<d; i++) {
            sb.append(poker[i]);
        }

        return sb.toString();
    }

    private String checkAB(String g){
        int A, B; A = B = 0;
        for (int i=0; i<answer.length(); i++) {
            if (answer.charAt(i) == g.charAt(i)) {
                A++;
            }else if(answer.indexOf(g.charAt(i)) != -1) {
                B++;
            }
        }
        return A + "A" + B + "B";
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("brad", "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("brad", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("brad", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("brad", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("brad", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("brad", "onDestroy()");
    }

    public void exit(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.v("brad", "onBackPress()");
    }

    private long lastTime = 0;

    @Override
    public void finish() {
        //super.finish();
        Log.v("brad", "finish()");

        if (System.currentTimeMillis() - lastTime <= 3*1000){
            super.finish();
        }else{
            Toast.makeText(this, "exit one more press",Toast.LENGTH_SHORT).show();
        }
        lastTime = System.currentTimeMillis();



    }

    public void setting(View view) {
        showDialog3();
    }

    private void showDialog1(){
        new AlertDialog.Builder(this)
                .setTitle("Info")
                .setMessage("Hello, World")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    private void showDialog2(){
        String[] items = {"Item1","Item2","Item3","Item4"};
        new AlertDialog.Builder(this)
                .setTitle("Info")
                .setCancelable(false)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("brad", "=> " + which);
                    }
                })
                .create()
                .show();
    }

    private int guessDig = 3;
    private int tempDig;

    private void showDialog3(){
        String[] items = {"3","4","5","6"};
        new AlertDialog.Builder(this)
                .setTitle("猜幾碼?")
                .setCancelable(false)
                .setSingleChoiceItems(items, guessDig-3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("brad", "==> " + which);
                        tempDig = which+3;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("brad", "OK");
                        guessDig = tempDig;
                        initNewGame(null);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


}
