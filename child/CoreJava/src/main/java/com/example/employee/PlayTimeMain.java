package com.example.employee;

import java.util.*;
import java.util.stream.Collectors;

public class PlayTimeMain {
    public static void main(String[] args) {

        List<PlayTime> playTimeList = List.of(
                new PlayTime("John", "Game1", 120, "TeamA", "Indore", 59.99),
                new PlayTime("Jane", "Game2", 90, "TeamB", "Outdoor", 49.99),
                new PlayTime("Dave", "Game3", 150, "TeamA", "Indore", 39.99),
                new PlayTime("Emily", "Game4", 80, "TeamC", "Outdoor", 29.99),
                new PlayTime("Tim", "Game1", 80, "TeamB", "Indore", 29.99)
               ,new PlayTime("Jenny", "Game2", 180, "TeamC", "Outdoor", 49.99)
                ,new PlayTime("Tom", "Game3", 90, "TeamD", "Indore", 39.99)
                ,new PlayTime("Jordan", "Game4", 120, "TeamA", "Outdoor", 19.99)
                ,new PlayTime("Mike", "Game1", 150, "TeamB", "Indore", 59.99)

        );

        List<PlayTime> playTimeListWithNull = List.of(
                new PlayTime("John", "Game1", 120, "TeamA", "Indore", 59.99),
                new PlayTime("Jane", "Game2", 90, "TeamB", "Outdoor", 49.99),
                new PlayTime("Dave", "Game3", 150, "TeamA", "Indore", 39.99),
                new PlayTime("Emily", "Game4", 80, "TeamC", "Outdoor", 29.99),
                new PlayTime(null, "Game1", 80, "TeamB", "Indore", 29.99)
                ,new PlayTime("Jenny", null, null, "TeamC", "Outdoor", 49.99)
                ,new PlayTime("Tom", "Game3", 90, null, "Indore", 39.99)
                ,new PlayTime("Jordan", "Game4", 120, "TeamA", null, 19.99)
                ,new PlayTime("Mike", "Game1", 150, "TeamB", "Indore", null)

        );
        Map<Integer, Map<Double, Map<String, Map<String, List<PlayTime>>>>> resultWithNull =
                playTimeListWithNull.stream()
                        .collect(Collectors.groupingBy(e-> Optional.ofNullable(e.getPlayTime()).orElse(0),
                                Collectors.groupingBy(e-> Optional.ofNullable(e.getGamePrice()).orElse(0.0),
                                        Collectors.groupingBy(e-> Optional.ofNullable(e.getGameName()).orElse("No Game Name"),
                                        Collectors.groupingBy(e-> Optional.ofNullable(e.getGameType()).orElse("No Game Type"))))));

        Map<Integer, Map<Double, Map<String, Map<String, List<PlayTime>>>>> resultWithoutNull =
                playTimeList.stream()
                        .collect(Collectors.groupingBy(PlayTime::getPlayTime,
                                Collectors.groupingBy(PlayTime::getGamePrice,
                                        Collectors.groupingBy(PlayTime::getTeamName,
                                                Collectors.groupingBy(PlayTime::getGameType)))));
        System.out.println("===============result======resultWithNull============");
        System.out.println(resultWithNull);

        System.out.println("===============result======resultWithoutNull============");
        System.out.println(resultWithoutNull);

        List<PlayTime> playTimeList1 = playTimeList.stream()
                .sorted(Comparator.comparing(PlayTime::getPlayTime, Comparator.nullsLast(Comparator.reverseOrder()))
                        .thenComparing(PlayTime::getGamePrice, Comparator.nullsLast(Comparator.reverseOrder()))
                        .thenComparing(PlayTime::getTeamName, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))
                        .thenComparing(PlayTime::getGameType, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))).toList();
        System.out.println("===============playTimeList1==================");
        playTimeList1.forEach((k) -> System.out.println(" "+ k.getPlayTime() + " " + k.getGamePrice() + " " + k.getTeamName() + " " + k.getGameType()));
    }
}
