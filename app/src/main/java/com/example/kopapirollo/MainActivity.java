package com.example.kopapirollo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewPlayer;
    private ImageView imageViewComputer;
    private TextView textViewResults;
    private Button buttonRock;
    private Button buttonPaper;
    private Button buttonScissors;
    private int win;
    private int lose;
    private Random random;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        buttonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(0);
            }
        });
        buttonPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(1);
            }
        });
        buttonScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(2);
            }
        });


    }
    public void check(int playerChoose){



        int help = random.nextInt(3);
        if (playerChoose==0){
            imageViewPlayer.setImageResource(R.drawable.rock);


        }
        else if(playerChoose==1) {
            imageViewPlayer.setImageResource(R.drawable.paper);
        }
        else {
            imageViewPlayer.setImageResource(R.drawable.scissors);
        }
        picture(help);
        if (help==playerChoose){
            Toast.makeText(this,"Döntetlen", Toast.LENGTH_SHORT).show();
        }else if(help == 0 && playerChoose==1) {

            win++;
            Toast.makeText(this,"Nyertél", Toast.LENGTH_SHORT).show();
            textViewResults.setText(String.format("Eredmény: Ember:%d Computer:%d",win,lose));
        } else if (help == 0 && playerChoose==2) {

            lose++;
            Toast.makeText(this,"Vesztettél", Toast.LENGTH_SHORT).show();
            textViewResults.setText(String.format("Eredmény: Ember:%d Computer:%d",win,lose));
        } else if (help == 1&&playerChoose==0) {
            lose++;
            Toast.makeText(this,"Vesztettél", Toast.LENGTH_SHORT).show();
            textViewResults.setText(String.format("Eredmény: Ember:%d Computer:%d",win,lose));
        } else if (help ==1 && playerChoose==2) {
            win++;
            Toast.makeText(this,"Nyertél", Toast.LENGTH_SHORT).show();
            textViewResults.setText(String.format("Eredmény: Ember:%d Computer:%d",win,lose));
        } else if (help==2 && playerChoose==0) {
            win++;
            Toast.makeText(this,"Nyertél", Toast.LENGTH_SHORT).show();
            textViewResults.setText(String.format("Eredmény: Ember:%d Computer:%d",win,lose));
        }
        else {
            lose++;
            Toast.makeText(this,"Vesztettél", Toast.LENGTH_SHORT).show();
            textViewResults.setText(String.format("Eredmény: Ember:%d Computer:%d",win,lose));
        }
        if (win==3){
            alertDialog.setMessage(String.format("Szeretnél új játékot?"));
            alertDialog.setTitle("Győzelem");
            alertDialog.show();}
        if (lose==3){
            alertDialog.setMessage(String.format("Szeretnél új játékot?"));
            alertDialog.setTitle("Vereség");
            alertDialog.show();}
    }
    public void picture(int computer){
        if (computer==0){
            imageViewComputer.setImageResource(R.drawable.rock);
        } else if (computer==1) {
            imageViewComputer.setImageResource(R.drawable.paper);
        }
        else {
            imageViewComputer.setImageResource(R.drawable.scissors);
        }
    }
    public void init(){
        imageViewComputer = findViewById(R.id.imageViewComputer);
        imageViewPlayer = findViewById(R.id.imageViewPlayer);
        textViewResults = findViewById(R.id.textViewResult);
        buttonRock = findViewById(R.id.buttonRock);
        buttonPaper = findViewById(R.id.buttonPaper);
        buttonScissors = findViewById(R.id.buttonScissors);
        win = 0;
        lose = 0;
        random  = new Random();
        alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("Vereség").setMessage("Szeretne új játékot játszani?").setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        win = 0;
                        lose = 0;
                        textViewResults.setText(String.format("Eredmény: Ember:%d Computer:%d",win,lose));
                        imageViewComputer.setImageResource(R.drawable.rock);
                        imageViewPlayer.setImageResource(R.drawable.rock);
                    }
                }).setCancelable(false).create();
    }
}