package org.dataserver;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ToptalTest01 {
    public static void main(String[] args) {
        System.out.println(solution("We test coders. Give us a try?"));
        System.out.println(solution("Forget CVS..Save time . x x"));
    }

    public  static  int solution(String s) {
        List<String> sentences = Arrays.asList(s.split("[.?!]"));
        if (sentences.size() == 0) return 0;

        int maxWordCount = Integer.MIN_VALUE;
        for(String sentence : sentences) {
            int sentenceWordCount = (int) Arrays.stream(sentence.split(" "))
                    .filter(word -> !word.equals("")).count();
            if(sentenceWordCount > maxWordCount)
                maxWordCount = sentenceWordCount;
        }

        return maxWordCount;
    }
}
