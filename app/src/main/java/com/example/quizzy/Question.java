package com.example.quizzy;

public class Question {

    String operator;
    private String question;
    private float option1,option2,option3,option4,ans;

    public Question(String question, float option1, float option2, float option3, float option4, float ans, String operator) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ans = ans;
        this.operator=operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public float getOption1() {
        return option1;
    }

    public void setOption1(float option1) {
        this.option1 = option1;
    }

    public float getOption2() {
        return option2;
    }

    public void setOption2(float option2) {
        this.option2 = option2;
    }

    public float getOption3() {
        return option3;
    }

    public void setOption3(float option3) {
        this.option3 = option3;
    }

    public float getOption4() {
        return option4;
    }

    public void setOption4(float option4) {
        this.option4 = option4;
    }

    public float getAns() {
        return ans;
    }

    public void setAns(float ans) {
        this.ans = ans;
    }
}
