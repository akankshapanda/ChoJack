package edu.illinois.cs.cs125.cho_jack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    ImageView dice1You, dice2You, dice1CPU, dice2CPU;
    Button roll1, roll2, stay, reset, home_button;
    TextView Bet, cpuMoney, youMoney, EnterAmount, fb_name;

    double money1 = 100.00;
    double money2 = 100.00;

    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bet = (TextView) findViewById(R.id.Bet);
        cpuMoney = (TextView) findViewById(R.id.cpuMoney);
        youMoney = (TextView) findViewById(R.id.youMoney);
        EnterAmount = (TextView) findViewById(R.id.EnterAmount);

        dice1You = (ImageView) findViewById(R.id.dice1You);
        dice2You = (ImageView) findViewById(R.id.dice2You);
        dice1CPU = (ImageView) findViewById(R.id.dice1CPU);
        dice2CPU = (ImageView) findViewById(R.id.dice2CPU);

        roll1 = (Button) findViewById(R.id.roll1);
        roll2 = (Button) findViewById(R.id.roll2);
        stay = (Button) findViewById(R.id.stay);
        reset = (Button) findViewById(R.id.reset);
        home_button = (Button) findViewById(R.id.home_button);
        fb_name = (TextView) findViewById(R.id.fb_name);

        random = new Random();


        roll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = "Welcome " + MainActivity.name_first;
                fb_name.setText(string);

                youMoney.setText(MainActivity.name + ": $" + money1);
                final int diceYouNum1 = random.nextInt(6) + 1;
                final int diceCPUNum1 = random.nextInt(6) + 1;
                diceCPURoll1(diceCPUNum1);
                diceYouRoll1(diceYouNum1);
                roll1.setVisibility(v.INVISIBLE);
                roll2.setVisibility(v.VISIBLE);
                stay.setVisibility(v.VISIBLE);
                EnterAmount.setText("Press Stay or Roll");

                Animation spin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                dice1You.startAnimation(spin);
                dice1CPU.startAnimation(spin);

                roll2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        roll1.setVisibility(v.VISIBLE);
                        roll2.setVisibility(v.INVISIBLE);
                        stay.setVisibility(v.INVISIBLE);
                        int diceYouNum2 = random.nextInt(6) + 1;
                        int diceCPUNum2 = random.nextInt(6) + 1;
                        int diceYouTotal = diceYouNum1 + diceYouNum2;
                        int diceCPUTotal = diceCPUNum1 + diceCPUNum2;
                        diceYouRoll2(diceYouNum2);
                        diceCPURoll2(diceCPUNum2);

                        Animation spin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                        dice2You.startAnimation(spin);
                        dice2CPU.startAnimation(spin);

                        if (diceYouTotal > 10) {
                            money1 -= 20.00;
                        }
                        if (diceCPUTotal > 10) {
                            money2 -= 20.00;
                        }
                        if (diceCPUTotal == 10) {
                            money2 += 40.00;
                        }
                        if (diceYouTotal == 10) {
                            money1 += 40.00;
                        }
                        if (diceCPUTotal < 10 && diceCPUTotal > diceYouTotal) {
                            money1 -= 20.00;
                            money2 += 20.00;
                        }
                        if (diceYouTotal < 10 && diceYouTotal > diceCPUTotal) {
                            money1 += 20.00;
                            money2 -= 20.00;
                        }
                        if (diceCPUTotal == diceYouTotal) {
                            if (money1 < money2) {
                                money2 = money1;
                            }
                            if (money2 < money1) {
                                money1 = money2;
                            }
                        }

                        if (money1 <= 0.00) {
                            money1 = 0.00;
                            youMoney.setText(MainActivity.name + ": $" + money1);
                            roll1.setVisibility(v.INVISIBLE);
                            roll2.setVisibility(v.INVISIBLE);
                            stay.setVisibility(v.INVISIBLE);
                            Bet.setText("GAME OVER!");
                            EnterAmount.setText("Please reset");
                        } else if (money2 <= 0.00) {
                            money2 = 0.00;
                            cpuMoney.setText("CPU: $" + money2);
                            roll1.setVisibility(v.INVISIBLE);
                            roll2.setVisibility(v.INVISIBLE);
                            stay.setVisibility(v.INVISIBLE);
                            Bet.setText("YOU WIN!!");
                            EnterAmount.setText("Please reset");
                        } else {
                            youMoney.setText(MainActivity.name + ": $" + money1);
                            cpuMoney.setText("CPU: $" + money2);
                            EnterAmount.setText("Press Roll");
                        }
                    }
                });

                stay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        roll1.setVisibility(v.VISIBLE);
                        roll2.setVisibility(v.INVISIBLE);
                        stay.setVisibility(v.INVISIBLE);
                        if (diceYouNum1 == 6) {
                            money1 += 20.00;
                        }
                        if (diceCPUNum1 == 6) {
                            money2 += 20.00;
                        }
                        if (diceCPUNum1 > diceYouNum1 && diceYouNum1 != 6 && diceCPUNum1 != 6) {
                            money1 -= 10.00;
                            money2 += 10.00;
                        }
                        if (diceCPUNum1 < diceYouNum1 && diceCPUNum1 != 6 && diceYouNum1 != 6) {
                            money1 += 10.00;
                            money2 -= 10.00;
                        }
                        if (diceCPUNum1 == diceYouNum1) {
                            if (money1 < money2) {
                                money2 = money1;
                            }
                            if (money2 < money1) {
                                money1 = money2;
                            }
                        }
                        if (money1 <= 0) {
                            money1 = 0;
                            youMoney.setText(MainActivity.name + ": $" + money1);
                            roll1.setVisibility(v.INVISIBLE);
                            roll2.setVisibility(v.INVISIBLE);
                            stay.setVisibility(v.INVISIBLE);
                            Bet.setText("GAME OVER!");
                            EnterAmount.setText("Please reset");
                        } else if (money2 <= 0) {
                            cpuMoney.setText("CPU: $" + money2);
                            money2 = 0;
                            roll1.setVisibility(v.INVISIBLE);
                            roll2.setVisibility(v.INVISIBLE);
                            stay.setVisibility(v.INVISIBLE);
                            Bet.setText("YOU WIN!!");
                            EnterAmount.setText("Please reset");
                        } else {
                            youMoney.setText(MainActivity.name + ": $" + money1);
                            cpuMoney.setText("CPU: $" + money2);
                            EnterAmount.setText("Press Roll");
                        }
                    }
                });

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money1 = 100.00;
                money2 = 100.00;
                dice1You.setImageResource(R.drawable.dice_6);
                dice2You.setImageResource(R.drawable.dice_6);
                dice1CPU.setImageResource(R.drawable.dice_6);
                dice2CPU.setImageResource(R.drawable.dice_6);
                Bet.setText("Let's Play");
                EnterAmount.setText("Press Roll");
                youMoney.setText(MainActivity.name + ": $" + money1);
                cpuMoney.setText("CPU: $" + money2);
                roll1.setVisibility(v.VISIBLE);
            }
        });

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
    }

    public void diceYouRoll1(int num) {
        switch (num) {
            case 1:
                dice1You.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                dice1You.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                dice1You.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                dice1You.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                dice1You.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                dice1You.setImageResource(R.drawable.dice_6);
                break;
        }
    }

    public void diceYouRoll2(int num) {
        switch (num) {
            case 1:
                dice2You.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                dice2You.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                dice2You.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                dice2You.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                dice2You.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                dice2You.setImageResource(R.drawable.dice_6);
                break;
        }
    }

    public void diceCPURoll1(int num) {
        switch (num) {
            case 1:
                dice1CPU.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                dice1CPU.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                dice1CPU.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                dice1CPU.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                dice1CPU.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                dice1CPU.setImageResource(R.drawable.dice_6);
                break;
        }
    }

    public void diceCPURoll2(int num) {
        switch (num) {
            case 1:
                dice2CPU.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                dice2CPU.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                dice2CPU.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                dice2CPU.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                dice2CPU.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                dice2CPU.setImageResource(R.drawable.dice_6);
                break;
        }
    }

    public void goHome() {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}
