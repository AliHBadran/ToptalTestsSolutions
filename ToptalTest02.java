package org.dataserver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ToptalTest02 {
    static String DATE_FORMAT = "yyyy-MM-dd";
    static class Transaction {
        public int amount;
        public Date date;

        public Transaction(int amount, String date) throws ParseException {
            this.amount = amount;
            this.date = new SimpleDateFormat(DATE_FORMAT).parse(date);
        }
    }

    public static int solution(int[] A, String[] D) throws Exception {
        ArrayList<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < A.length; i++)
            transactions.add(new Transaction(A[i], D[i]));

        int balance = transactions.stream().map(t -> t.amount).reduce(0, Integer::sum);

//        Set<Integer> uniqueMonths = Arrays.stream(D).map(date -> {
//            try {
//                return new SimpleDateFormat(DATE_FORMAT).parse(date).getMonth();
//            } catch (ParseException e) {
//                return -1;
//            }
//        }).collect(Collectors.toSet());

        List<Integer> uniqueMonths = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        for (int month : uniqueMonths) {
            List<Transaction> monthPayments = transactions.stream()
                    .filter(t -> t.date.getMonth() == month && t.amount < 0)
                    .collect(Collectors.toList());

            if (!(monthPayments.size() >= 3 && monthPayments.stream().map(t -> t.amount).reduce(0, Integer::sum) <= -100))
                balance -= 5;
        }

        return balance;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(solution(new int[] {100, 100, 100, -10}, new String[] {"2020-12-31", "2020-12-22", "2020-12-03", "2020-12-29"}));
        System.out.println(solution(new int[] {180, -50, -25, -25}, new String[] {"2020-01-01", "2020-01-01", "2020-01-01", "2020-01-31"}));
        System.out.println(solution(new int[] {1, -1, 0, -105, 1}, new String[] {"2020-12-31", "2020-04-04", "2020-04-04", "2020-04-14", "2020-07-12"}));
        System.out.println(solution(new int[] {100, 100, -10, -20, -30}, new String[] {"2020-01-01", "2020-02-01", "2020-02-11", "2020-02-05", "2020-02-08"}));
    }
}
