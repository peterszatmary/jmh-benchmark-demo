package com.szatmary.peter;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        // is not important here now
    }

    public long oldWay() {
        List<Integer> d = data();
        Long res = 0L;
        for (int i = 0; i < d.size(); i++) {
            res = res + d.get(i);
        }
        return res;
    }

    public long newWay() {
        return data().stream().reduce(0, (x, y) -> x + y);
    }


    private List<Integer> data() {
        List<Integer> result = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 100000; i++) {
            result.add(r.nextInt(100000));
        }
        return result;
    }
}
