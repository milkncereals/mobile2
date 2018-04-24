package earl.com.assignment2;


public class Questions {

    //http://readymadepubquiz.com/quiz-10-round-4-true-or-false/

    private String questions [] = {

            "There are 259 steps up to the ‘Whispering Gallery’ in St Paul’s Cathedral.",
            "The reverse of the Nobel Peace Prize, shows three naked men, standing with their hands on each other’s shoulders.",
            "Centipedes always have 100 feet.",
            "The world record for a human to hold their breath underwater is 8 minutes 27 seconds.",
            "Olympus Mons, Mount Olympus on Mars, is taller than Mount Everest.",
            "The world’s oldest known tree is over 9000 years old.",
            "A cross between a horse and a zebra is called a Hobra.",
            "111,111,111 x 111,111,111 = 12,345,678,987,654,321",
            "Muscle turns to fat if you stop exercising.",
            "The world’s smallest book is 1cm wide, 1cm tall and 4mm deep."

    };

    private String Answers [] = {

        "True","True","False","False","True","True","False","True","False","False"

    };

    public int getQuestionSize(){
        return questions.length;
    }

    public String getQuestion(int a){
        String q = questions[a];
        return q;
    }

    public String getAnswer(int a){
        String answer = Answers[a];
        return answer;
    }

}
