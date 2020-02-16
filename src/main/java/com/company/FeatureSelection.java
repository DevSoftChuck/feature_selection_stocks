package com.company;

import com.google.common.collect.Sets;
import java.util.Set;

public class FeatureSelection {
    public static void main(String[] args)
    {
        Set<Integer> set = Sets.newHashSet(1, 2, 3);
        Set<Set<Integer>> powerSet = Sets.powerSet(set);

        for (Set<Integer> s: powerSet)
            System.out.println(s);
    }
}