package com.example.quizzy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionGenerator {
    static Random random = new Random();
    // generate random numbers
    private static int generateRandomNumber(){
        return random.nextInt(12)+1;
    }

    //operators used
    private static String[] operators = new String[]{"+","-","x","÷"};

    // generate random operator
    private static String generateRandomOperator(){
      int index = random.nextInt(4);
      return operators[index];
    };

    //generate answer
    private static int generateAnswer(int num1, int num2, String operator){
        switch (operator){
            case "+":
                return num1 + num2;
            case "-":
                //making sure the answer is not negative
                return Math.max(num1-num2,num2-num1);
            case "x":
                return num1 * num2;
            case "÷":
                return num1/num2;

        }
        return 0;
    }

    // generate options for the question
    private static List<Integer> generateOptions(int ans, String operator){
        List<Integer> options = new ArrayList<Integer>();

        options.add(ans);
        int option=0;
        while (options.size()<4){
            if(operator == "÷"){

            int num1 = generateRandomNumber();
            int num2 = generateRandomNumber();
            float result = (float)num1/(float)num2;
            while (Math.floor(result)!=result){
                num1 = generateRandomNumber();
                num2 = generateRandomNumber();
                result = (float)num1/(float)num2;
            }
            option = generateAnswer(num1, num2, operator);

            }else{
                option = generateRandomNumber();
            }
            if (!options.contains(option)){
                options.add(option);
            }
        }

        //changing the index place of the elements random
        Collections.shuffle(options);
        return options;
    }

    //generate questoin
    public static Question generateQuestion(){
        String operator = generateRandomOperator();

        int num1 = generateRandomNumber();
        int num2 = generateRandomNumber();
        if(operator.equals("x")){
            //making sure that the answer is not over 12 and not 1
            while (num1*num2>12 || num1==1 || num2==1 ){
                num1 = generateRandomNumber();
                num2 = generateRandomNumber();
            }
        }else if(operator.equals("÷")){
            float result = (float)num1/(float)num2;
            //making sure that the answer is not decimal
            while (Math.floor(result)!=result){
                num1 = generateRandomNumber();
                num2 = generateRandomNumber();
                result = (float)num1/(float)num2;
            }
        }else if(operator.equals("+")){
            //making sure the answer does not exceed 12
            while (num1+num2>12){
                num1 = generateRandomNumber();
                num2 = generateRandomNumber();
            }
        }
        String question = null;
        if(operator.equals("-")){
            if(num1>num2) question = num1 +" "+operator+" "+num2;
            else question = num2 +" "+operator+" "+num1;
        }else{
            question = num1 +" "+operator+" "+num2;

        }
        int ans = generateAnswer(num1,num2,operator);
        List<Integer> options = generateOptions(ans,operator);

        Question q = new Question(question,options.get(0),options.get(1),options.get(2),options.get(3),ans,operator);

        return q;
    }
}
