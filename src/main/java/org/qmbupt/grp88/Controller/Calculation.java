package org.qmbupt.grp88.Controller;

import java.util.ArrayList;

public class Calculation {
    public static double calculateGPA(ArrayList<Integer> credits, ArrayList<Integer> scores) {
        double sumGPA = 0;
        int sumCredits = 0;
        for (int i = 0; i < scores.size(); i++) {
            sumGPA += (4-3.0*Math.pow(100-scores.get(i),2)/1600) * credits.get(i);
            // calculate GPA of each module: 4 - 3*(100-score)^2/1600, sum up with credit weight
            // System.out.println(sumGPA);
            sumCredits += credits.get(i);               // sum up the credits
        }
        return sumGPA / sumCredits;     // the GPA here still not only keeps 2 decimal digits
    }

    public static String calculateDegree(ArrayList<Integer> semesters, ArrayList<Integer> scores) {
        double finalScore = 0, percentage = 0;
        int count = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (semesters.get(i) > 2) {                            // Year 1 would not be considered in
                if (semesters.get(i) < 5) { percentage = 0.65; }        // Year 2
                else if (semesters.get(i) < 7) { percentage = 0.95; }   // Year 3
                else { percentage = 0.65; }                             // Year 4
                count++;
            }
            finalScore += scores.get(i) * percentage;
        }
        finalScore /= 100 * count;
        // System.out.println(finalScore);

        // 4 degrees of honor
        if (finalScore >= 0.7) { return "First"; }
        else if (finalScore >= 0.6) { return "Second Upper"; }
        else if (finalScore >= 0.5) { return "Second Lower"; }
        else if (finalScore >= 0.4) { return "Third"; }
        else { return "Nothing"; }
    }

    public static void main(String[] args) {
        ArrayList<Integer> credits = new ArrayList<>();
        credits.add(4);
        credits.add(3);
        credits.add(5);
        credits.add(2);
        credits.add(3);
        credits.add(2);
        ArrayList<Integer> scores = new ArrayList<>();
        scores.add(80);
        scores.add(75);
        scores.add(87);
        scores.add(90);
        scores.add(89);
        scores.add(88);
        ArrayList<Integer> semesters = new ArrayList<>();
        semesters.add(1);
        semesters.add(3);
        semesters.add(5);
        semesters.add(5);
        semesters.add(7);
        semesters.add(6);

        System.out.println("GPA:\t" + String.format("%.2f",calculateGPA(credits, scores)) + "/4.00");
        System.out.println("Degree of honor:\t" + calculateDegree(semesters, scores));
    }
}
