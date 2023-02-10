package com.example.quizzy;

public class Question {

    String operator;
    private String question;
    private int option1,option2,option3,option4,ans;

    public Question(String question, int option1, int option2, int option3, int option4, int ans,String operator) {
        this.operator = operator;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ans = ans;
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

    public int getOption1() {
        return option1;
    }

    public void setOption1(int option1) {
        this.option1 = option1;
    }

    public int getOption2() {
        return option2;
    }

    public void setOption2(int option2) {
        this.option2 = option2;
    }

    public int getOption3() {
        return option3;
    }

    public void setOption3(int option3) {
        this.option3 = option3;
    }

    public int getOption4() {
        return option4;
    }

    public void setOption4(int option4) {
        this.option4 = option4;
    }

    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }
}
