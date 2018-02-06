package geoquiz.android.bignerdranch.com.ahlyquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrueFanActivity extends AppCompatActivity {

    private TextView mQuestionTextView;
    private  TextView mFirstChoiceTextView;
    private  TextView mSecondChoiceTextView;
    private  TextView mThirdChoiceTextView;
  //  private Button mCheatButton;
    private static final String KEY_INDEX="index";
    private ArrayList<Integer> mQuestionAsked = new ArrayList<Integer>(10);
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private int mTrueAnswer=0;
    private TextView mQuestionTimerTextView;
    private  int timer = 15000;
    private int mResponse=0;
    private CountDownTimer mCountDownTimer;
    private boolean isInForeGround;
    private Question[] mQuestionsBank= new Question[]{
            new Question(R.string.question_2, R.string.question_2_choice_1, R.string.question_2_choice_2, R.string.question_2_choice_3, R.string.question_2_choice_2),
            new Question(R.string.question_4, R.string.question_4_choice_1, R.string.question_4_choice_2, R.string.question_4_choice_3, R.string.question_4_choice_3),
            new Question(R.string.question_5, R.string.question_5_choice_1, R.string.question_5_choice_2, R.string.question_5_choice_3, R.string.question_5_choice_2),
            new Question(R.string.question_6, R.string.question_6_choice_1, R.string.question_6_choice_2, R.string.question_6_choice_3, R.string.question_6_choice_1),
            new Question(R.string.question_8, R.string.question_8_choice_1, R.string.question_8_choice_2, R.string.question_8_choice_3, R.string.question_8_choice_1),
            new Question(R.string.question_11, R.string.question_11_choice_1, R.string.question_11_choice_2, R.string.question_11_choice_3, R.string.question_11_choice_3),
            new Question(R.string.question_13, R.string.question_13_choice_1, R.string.question_13_choice_2, R.string.question_13_choice_3, R.string.question_13_choice_2),
            new Question(R.string.question_14, R.string.question_14_choice_1, R.string.question_14_choice_2, R.string.question_14_choice_3, R.string.question_14_choice_3),
            new Question(R.string.question_21, R.string.question_21_choice_1, R.string.question_21_choice_2, R.string.question_21_choice_3, R.string.question_21_choice_3),
            new Question(R.string.question_24, R.string.question_24_choice_1, R.string.question_24_choice_2, R.string.question_24_choice_3, R.string.question_24_choice_1),

    };
    private int mCurrentIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_fan);
        if(savedInstanceState!=null) {
            //  mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            //  mQuestionAsked.add(mCurrentIndex);
            // mQuestionAsked = savedInstanceState.getIntegerArrayList("myarray");
        }
        castUtils();
        updateMethod();
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Next();
            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prev();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCountDownTimer.cancel();
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateMethod();
            }
        });
        mFirstChoiceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Choice1=mQuestionsBank[mCurrentIndex].getChoice1();
                mQuestionAsked.add(mCurrentIndex);
                checkAns(Choice1);
               Next();
            }
        });
        mSecondChoiceTextView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                int Choice2=mQuestionsBank[mCurrentIndex].getChoice2();
                mQuestionAsked.add(mCurrentIndex);
                checkAns(Choice2);
               Next();
            }
        });
        mThirdChoiceTextView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                int Choice3=mQuestionsBank[mCurrentIndex].getChoice3();
                mQuestionAsked.add(mCurrentIndex);
                checkAns(Choice3);
               Next();
            }
        });

//        mCheatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int trueAnswer = mQuestionsBank[mCurrentIndex].getAnswerTrue();
//                Intent intent = CheatActivity.cheatIntent(TrueFanActivity.this, trueAnswer);
//                startActivity(intent);
//
//            }
//        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        // mQuestionAsked.add(mCurrentIndex);
        outState.putIntegerArrayList("myArray", mQuestionAsked);
    }


    private void castUtils() {
        mQuestionTextView=  findViewById(R.id.question_text_view);
        mFirstChoiceTextView=  findViewById(R.id.first_choice_text_view);
        mSecondChoiceTextView=  findViewById(R.id.second_choice_text_view);
        mThirdChoiceTextView=  findViewById(R.id.third_choice_text_view);
        mNextButton=  findViewById(R.id.next_button);
        mPrevButton=  findViewById(R.id.prev_button);
      //  mCheatButton=findViewById(R.id.cheat_button);
        mQuestionTimerTextView=findViewById(R.id.question_timer_text_view);
    }

    private void updateMethod() {
        int Question=mQuestionsBank[mCurrentIndex].getTextQuesid();
        mQuestionTextView.setText(Question);
        int Choice1=mQuestionsBank[mCurrentIndex].getChoice1();
        mFirstChoiceTextView.setText(Choice1);
        int Choice2=mQuestionsBank[mCurrentIndex].getChoice2();
        mSecondChoiceTextView.setText(Choice2);
        int Choice3=mQuestionsBank[mCurrentIndex].getChoice3();
        mThirdChoiceTextView.setText(Choice3);
        ShowPercent();
        setButtons();
        questionTimer();
    }


    private void checkAns ( int choice )
    {

        int answerIsTrue = mQuestionsBank[mCurrentIndex].getAnswerTrue();

        if(choice == answerIsTrue){
            mResponse++;
            mTrueAnswer++;
            Toast toast = new Toast(TrueFanActivity.this);
            toast.setDuration(Toast.LENGTH_LONG);

            LayoutInflater inflater = (LayoutInflater) TrueFanActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.correct_toast, null);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setView(view);
            if (isInForeGround) {
                toast.show();
            }
        }

        else if(choice!=answerIsTrue){
            mResponse++;
            Toast toast = new Toast(TrueFanActivity.this);
            toast.setDuration(Toast.LENGTH_LONG);
            LayoutInflater inflater = (LayoutInflater) TrueFanActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.incorrect_toast, null);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setView(view);
            if (isInForeGround) {
                toast.show();
            }
        }
        else{
            mResponse=0;
        }
        mQuestionsBank[mCurrentIndex].setmAnswered(1);
        setButtons();
    }
    @Override
    protected void onResume() {
        super.onResume();
        isInForeGround=true;
    }
    @Override
    protected void onStop() {
        isInForeGround=false;
        super.onStop();

    }
    @Override
    protected void onDestroy() {isInForeGround=false;
        super.onDestroy();
    }
    private  void  Prev (){
        if(mCurrentIndex==0){
            return;
        }

        if (mQuestionAsked.size() == mQuestionsBank.length + 1){
            mQuestionAsked.add(mCurrentIndex);
        }
        if (mQuestionsBank[mCurrentIndex].getmAnswered() > 0){
            if(mCountDownTimer!=null){
                mCountDownTimer.cancel();
                mCountDownTimer=null;
                mQuestionTimerTextView.setText("Done");
            }
        }
        else{
            mCountDownTimer.cancel();
        }
        mCurrentIndex = (mCurrentIndex - 1) % mQuestionsBank.length;
        updateMethod();
    }
    private void Next (){
        mCountDownTimer.cancel();
        if (mQuestionAsked.size() == mQuestionsBank.length  ){
            ShowPercent();
            mQuestionAsked.add(mCurrentIndex);
        }
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
        updateMethod();
    }

    private void setButtons() {

        if (mQuestionsBank[mCurrentIndex].getmAnswered() > 0) {
            // make buttons disabled
            mFirstChoiceTextView.setEnabled(false);
            mSecondChoiceTextView.setEnabled(false);
            mThirdChoiceTextView.setEnabled(false);

        }
        else
        {
            mFirstChoiceTextView.setEnabled(true);
            mSecondChoiceTextView.setEnabled(true);
            mThirdChoiceTextView.setEnabled(true);
        }
    }
    private void ShowPercent (){

        int resultResId = (mTrueAnswer*100) / 10;
        if (mQuestionAsked.size() == mQuestionsBank.length ) {

            if (isInForeGround) {

                Toast.makeText(this, Integer.toString(resultResId) + "% اجابات صح", Toast.LENGTH_LONG)
                        .show();
            }
        }
        if(resultResId==100){
            Toast.makeText(this, "مستوي الكابو اتفتح!", Toast.LENGTH_SHORT).show();
            Intent intent=ChooseLevelActivity.secondIntent(TrueFanActivity.this,resultResId);
            startActivity(intent);
            finish();
        }

    }

    private void questionTimer () {
        if (mQuestionsBank[mCurrentIndex].getmAnswered() == 0) {

            mCountDownTimer = new CountDownTimer(timer, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mQuestionTimerTextView.setText("Time: " + millisUntilFinished / 1000);
                }


                @Override
                public void onFinish() {

                    mQuestionTimerTextView.setText("Done");
                    mQuestionsBank[mCurrentIndex].setmAnswered(1);
                    setButtons();
                    if(mResponse<10){
                        mQuestionAsked.add(mCurrentIndex);
                        Toast toast = new Toast(TrueFanActivity.this);
                        toast.setDuration(Toast.LENGTH_LONG);
                        LayoutInflater inflater = (LayoutInflater)TrueFanActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.incorrect_toast, null);
                        toast.setGravity(Gravity.BOTTOM, 0, 0);
                        toast.setView(view);
                        if (isInForeGround) {
                            toast.show();
                        }
                        Next();
                    }
                }
            }.start();
        } else {
            mQuestionTimerTextView.setText("Done");
        }
    }

}
