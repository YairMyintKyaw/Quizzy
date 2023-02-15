package com.example.quizzy;

import static com.example.quizzy.HomePage.timerMillisecond;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.style.QuoteSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuizPage extends AppCompatActivity {

    RelativeLayout countDownContainer;
    TextView totalQuestionsTextView,countDown;
    TextView questionTextView;
    TextView ansA,ansB,ansC,ansD;

    CardView cardA,cardB, cardC, cardD;
    Button skipBtn;

    ImageView imgView;
    int currentQuestionIndex=0;
    static int score = 0;

    int totalQuestion = 10;
    float selectedAns = 0;

    Question currentQuestion;

    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        countDown = findViewById(R.id.countDown);
        // answers
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);

        //card
        cardA = findViewById(R.id.card_A);
        cardB = findViewById(R.id.card_B);
        cardC = findViewById(R.id.card_C);
        cardD = findViewById(R.id.card_D);

        //attach card and text
        cardA.setTag(ansA);
        cardB.setTag(ansB);
        cardC.setTag(ansC);
        cardD.setTag(ansD);

        //Image
        imgView = findViewById(R.id.img);
        countDownContainer = findViewById(R.id.countDownContainer);
        skipBtn = findViewById(R.id.skip_btn);
        // set listener
        cardA.setOnClickListener(cardClickListener);
        cardB.setOnClickListener(cardClickListener);
        cardC.setOnClickListener(cardClickListener);
        cardD.setOnClickListener(cardClickListener);

        // add event listener to the skip button
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerMillisecond!=0){
                    countDownTimer.cancel();
                }
                currentQuestionIndex++;
                loadNewQuestion();
            }
        });
        loadNewQuestion();

    }

    // disable back button of the device
    @Override
    public void onBackPressed() {
    }

    final View.OnClickListener cardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            TextView selectedTextView = (TextView) v.getTag();
            CardView selectedCard = (CardView) v;

            selectedAns = Float.parseFloat(selectedTextView.getText().toString()) ;

            if(selectedAns==currentQuestion.getAns()) {
                // play correct song
                MediaPlayer.create(QuizPage.this,R.raw.correct_sound).start();
                score++;
                selectedCard.setBackgroundColor(Color.GREEN);
                // show check mark
                imgView.setImageResource(R.drawable.check_mark);
            }else{
                switch (findAnswerCard()){
                    case "A":
                        cardA.setBackgroundColor(Color.GREEN);
                        break;
                    case "B":
                        cardB.setBackgroundColor(Color.GREEN);
                        break;
                    case "C":
                        cardC.setBackgroundColor(Color.GREEN);
                        break;
                    case "D":
                        cardD.setBackgroundColor(Color.GREEN);
                        break;
                }
                // show the red cross
                imgView.setImageResource(R.drawable.cancel);
                selectedCard.setBackgroundColor(Color.RED);
                // play wrong songs
                MediaPlayer.create(QuizPage.this,R.raw.wrong_ans).start();
            };
            if (timerMillisecond!=0){

                //stop timer when the user have answered the question
                countDownTimer.cancel();
            }

            // disable the buttons so that user cannot answer the question again
            cardA.setEnabled(false);
            cardB.setEnabled(false);
            cardC.setEnabled(false);
            cardD.setEnabled(false);
            skipBtn.setText("NEXT");
        }
    };



    void loadNewQuestion(){

        imgView.setImageDrawable(null);
        skipBtn.setText("SKIP");

        // enable the buttons so user can answer again
        cardA.setEnabled(true);
        cardB.setEnabled(true);
        cardC.setEnabled(true);
        cardD.setEnabled(true);
        cardA.setBackgroundColor(Color.WHITE);
        cardB.setBackgroundColor(Color.WHITE);
        cardC.setBackgroundColor(Color.WHITE);
        cardD.setBackgroundColor(Color.WHITE);
        if(currentQuestionIndex == totalQuestion){
            finishQuiz();
            return;
        }

        if (timerMillisecond!=0){
            //show countdown for level 1 and 2
            countDownContainer.setVisibility(View.VISIBLE);
            //set timer
            timer(timerMillisecond*1000);
        }else{
            // hide the counter for level 0
            countDownContainer.setVisibility(View.INVISIBLE);

        }
        // generate random question
        currentQuestion = QuestionGenerator.generateQuestion();

        // show current question number
        totalQuestionsTextView.setText(" Question: " + (currentQuestionIndex+1) + " / " + totalQuestion );
        //show question
        questionTextView.setText(currentQuestion.getQuestion());
        //show options
        ansA.setText(String.valueOf(currentQuestion.getOption1()));
        ansB.setText(String.valueOf(currentQuestion.getOption2()));
        ansC.setText(String.valueOf(currentQuestion.getOption3()));
        ansD.setText(String.valueOf(currentQuestion.getOption4()));

    }

    // find the correct answer button
    private String findAnswerCard(){
        if (currentQuestion.getAns() == currentQuestion.getOption1()) {
            return "A";
        }else if(currentQuestion.getAns() == currentQuestion.getOption2()){
            return "B";
        }else if(currentQuestion.getAns() == currentQuestion.getOption3()){
            return "C";
        }else if(currentQuestion.getAns() == currentQuestion.getOption4()){
            return "D";
        }else{
            return null;
        }
    }

    // this will go to result page
    private void finishQuiz() {
        Intent intent = new Intent(QuizPage.this,Result.class);
        startActivity(intent);

    }


    // timer function for level 1 and 2
    void timer(int totalPeriodTime){
        countDownTimer = new CountDownTimer(totalPeriodTime,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDown.setText(" : "+millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {
                MediaPlayer.create(QuizPage.this,R.raw.wrong_ans).start();
                // disable the card to be sure user no longer answer the question
                cardA.setEnabled(false);
                cardB.setEnabled(false);
                cardC.setEnabled(false);
                cardD.setEnabled(false);
                skipBtn.setText("NEXT");
                // show the correct answer
                switch (findAnswerCard()){
                    case "A":
                        cardA.setBackgroundColor(Color.GREEN);
                        break;
                    case "B":
                        cardB.setBackgroundColor(Color.GREEN);
                        break;
                    case "C":
                        cardC.setBackgroundColor(Color.GREEN);
                        break;
                    case "D":
                        cardD.setBackgroundColor(Color.GREEN);
                        break;
                }

                imgView.setImageResource(R.drawable.cancel);
            }
        }.start();
    }


}