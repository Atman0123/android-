package com.example.tictocgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

            //0=yello ,red=1
        int   activeplayer=0;
        boolean gameIsActive=true;

            //2 means unplayed
         int[]gamestate ={2,2,2,2,2,2,2,2,2};

         int [][] winningposition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public  void doping(View view) {

        ImageView couter = (ImageView) view;

        System.out.print(couter.getTag().toString());

        int tapedcounter = Integer.parseInt(couter.getTag().toString());

        if (gamestate[tapedcounter] == 2 && gameIsActive)    {

            gamestate[tapedcounter] = activeplayer;

            couter.setTranslationY(-1000f);

            if (activeplayer == 0) {

                couter.setImageResource(R.drawable.cross);

                activeplayer = 1;

            } else {
                couter.setImageResource(R.drawable.r);

                activeplayer = 0;

            }
            couter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningpostion : winningposition) {

                if (gamestate[winningpostion[0]] == gamestate[winningpostion[1]] &&
                        gamestate[winningpostion[1]] == gamestate[winningpostion[2]] &&
                        gamestate[winningpostion[0]] != 2) {

               //some has won

                    gameIsActive=false;
                String winner="Right";
                if (gamestate[winningpostion[0]]==0){

                    winner="Cross";


                }

                    TextView winnermessage=(TextView) findViewById(R.id.winnermessage);
                        winnermessage.setText(winner+"  Has Won!");
                    LinearLayout layout=(LinearLayout) findViewById(R.id.playAgainLayout);

                   layout.setVisibility(View.VISIBLE);

                }
            }
        }
    }
      public  void playAgin(View view){



        gameIsActive=true;
        LinearLayout layout=(LinearLayout) findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE );

           activeplayer=0;

           for(int  i=0;i<gamestate.length; i++){

           gamestate[i]=2;
       }
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);

           for(int i=0;i<gridLayout.getChildCount(); i++) {

               ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
           }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
