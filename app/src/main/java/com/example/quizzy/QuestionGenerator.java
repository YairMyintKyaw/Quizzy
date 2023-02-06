package com.example.quizzy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionGenerator {
    static Random random = new Random();

    private static int generateRandomNumber(){
        return random.nextInt(12)+1;
    }

    private static String[] operators = new String[]{"+","-","x","÷"};

    private static String generateRandomOperator(){
      int index = random.nextInt(4);
      return operators[index];
    };
    private static float generateAnswer(int num1, int num2, String operator){
        switch (operator){
            case "+":
                return num1 + num2;
            case "-":
                return Math.max(num1-num2,num2-num1);
            case "x":
                return num1 * num2;
            case "÷":
                return (float)num1/(float)num2;

        }
        return 0;
    }

    private static List<Float> generateOptions(float ans, String operator){
        List<Float> options = new ArrayList<Float>();
        options.add(ans);
        float option=0;
        while (options.size()<4){
            if(operator == "÷"){

            int num1 = generateRandomNumber();
            int num2 = generateRandomNumber();
            option = generateAnswer(num1, num2, operator);

            }else{
                option = generateRandomNumber();
            }
            if (!options.contains(option)){
                options.add(option);
            }
        }
        Collections.shuffle(options);
        return options;
    }

    public static Question generateQuestion(){
        int num1 = generateRandomNumber();
        int num2 = generateRandomNumber();
        String operator = generateRandomOperator();
        String question = null;
        if(operator.equals("-")){
            if(num1>num2) question = num1 +" "+operator+" "+num2;
            else question = num2 +" "+operator+" "+num1;

        }else{
            question = num1 +" "+operator+" "+num2;

        }
        float ans = generateAnswer(num1,num2,operator);
        List<Float> options = generateOptions(ans,operator);

        Question q = new Question(question,options.get(0),options.get(1),options.get(2),options.get(3),ans,operator);

        return q;
    }
}
