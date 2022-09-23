package com.went.play.ground.exam1;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author White.Wen
 * @version 1.0
 * @date 2022/9/23 9:52
 */
public class Q1 {

    private List<String> baseList = new ArrayList<>();

    private Map<String, Boolean> visited = new LinkedHashMap<>();

    private boolean flag = false;

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Stream.of("hot", "dot", "dog", "lot", "log", "cog").collect(Collectors.toList());
        Q1 q1 = new Q1();
        System.out.println(q1.ladderLength(beginWord, endWord, wordList));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 0;
        }

        baseList.addAll(wordList);

        Queue<String> queue = new LinkedBlockingQueue<>();
        queue.add(beginWord);
        visited.put(beginWord, true);
        int depth = 1;
        while (queue.size() > 0) {
            int size = queue.size();
            while (size-- > 0) {
                String key = queue.poll();
                generateNeighbours(key).stream().filter(w -> !visited.containsKey(w) || !visited.get(w))
                        .forEach(w -> {
                            if (w.equals(endWord)) {
                                flag = true;
                            }

                            visited.put(w, true);
                            queue.add(w);
                        });

                if (flag) {
                    return depth + 1;
                }
            }

            if (queue.size() > 0) {
                depth++;
            }
        }
        if (!flag) {
            return 0;
        } else {
            return depth;
        }
    }

    private List<String> generateNeighbours(String word) {
        List<String> neighbours = new ArrayList<>();

        neighbours.addAll(baseList.stream().filter(w -> {
            int index = 0;
            int len = word.length();
            int l = w.length();
            if (len != l) {
                return false;
            }

            for (int i = 0; i < len; ++i) {
                if (word.charAt(i) == w.charAt(i)) {
                    index++;
                }
            }

            if (index + 1 == len) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList()));

        return neighbours;
    }

}
