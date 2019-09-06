package com.example.adikatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    String gridPlayed[] = {"","","","","","","","",""};

    char whoPlayed = 'B';

    int piecesPlayed = 0;

    String wonMessage = "";



    public void dropIn(View view){

        int pieceNumber = Integer.parseInt((String) view.getTag());
        Log.i("Info",this.gridPlayed[pieceNumber]);

        if(this.gridPlayed[pieceNumber] == "" && this.wonMessage == ""){
            ImageView counter = (ImageView) view;

            counter.setTranslationY(-1500);

            if(this.whoPlayed == 'B'){

                this.gridPlayed[pieceNumber] = "Red";
                counter.setImageResource(R.drawable.red);
                this.whoPlayed = 'R';
            }else{

                this.gridPlayed[pieceNumber] = "Black";
                counter.setImageResource(R.drawable.black);
                this.whoPlayed = 'B';
            }

            this.piecesPlayed++;


            if(this.piecesPlayed >= 5){

                for (int i = 0, x = 0; i < 9;  i+=3, x++){


                    if(this.gridPlayed[i] != "" && this.gridPlayed[i] == this.gridPlayed[i+1] && this.gridPlayed[i] == this.gridPlayed[i+2]){
                        Log.i("Info","rows" + this.gridPlayed[i] + " - " + this.gridPlayed[i+1] + " - " + this.gridPlayed[i+2] + " index is : " +  i);

                        this.wonMessage =  this.gridPlayed[i] + " pieces player won :D";
                        break;
                    }

                   if( this.gridPlayed[x] != "" &&  this.gridPlayed[x] == this.gridPlayed[x+3] && this.gridPlayed[x] == this.gridPlayed[x+6]){
                       Log.i("Info","columns" + this.gridPlayed[x] + " - " + this.gridPlayed[x+3] + " - " + this.gridPlayed[x+6]  + " index is : " +  x);
                       this.wonMessage =  this.gridPlayed[x] + " pieces player won :D";
                       break;
                    }

                }

                if(this.wonMessage == ""){
                    if(this.gridPlayed[0] != "" && this.gridPlayed[0] == this.gridPlayed[4] && this.gridPlayed[0] == this.gridPlayed[8]){
                        this.wonMessage =  this.gridPlayed[0] + " pieces player won :D";
                    }

                    if(this.gridPlayed[2] != "" && this.gridPlayed[2] == this.gridPlayed[4] && this.gridPlayed[2] == this.gridPlayed[6]){
                        this.wonMessage =  this.gridPlayed[2] + " pieces player won :D";
                    }
                }
            }

            counter.animate().translationYBy(1500).setDuration(300);

            if(this.wonMessage != ""){
                TextView textView = findViewById(R.id.textView2);
                textView.append(this.wonMessage);
                textView.animate().alpha(1).setDuration(150);
            }


        }
    }


    public void resetData(View view){
        Log.i("Info","piece");


        GridLayout myGrid = findViewById(R.id.gridLayout);



        for(int i = 0; i < this.gridPlayed.length; i++){
            this.gridPlayed[i] = "";

            ImageView imageView = (ImageView) myGrid.getChildAt(i);

            if(imageView != null){
                Log.i("Info","piece"+(i+1)+ "  found");

                imageView.setImageResource(android.R.color.transparent);
            }

        }

        TextView textView = findViewById(R.id.textView2);
        textView.setText("");
        textView.setAlpha(0);

        this.wonMessage = "";

        this.whoPlayed = 'B';

        this.piecesPlayed = 0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
