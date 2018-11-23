package com.rvprg.pc;

import java.util.*;

public class War {
    private final int[] friends;
    private final int n;

    public War(int n) {
        this.n = n;
        friends = new int[2 * (n + 1)];
        for (int i = 0; i < 2 * (n + 1); ++i) {
            friends[i] = i;
        }
    }

    public int enemy(int i) {
        return i + n;
    }

    public int find(int i) {
        if (friends[i] != i) {
            friends[i] = find(friends[i]);
        }
        return friends[i];
    }

    public void union(int i, int j) {
        if (find(i) != find(j)) {
            friends[find(j)] = find(i);
        }
    }

    private boolean setFriends(int i, int j) {
        if (areEnemies(i, j)) {
            return false;
        }
        union(i, j);
        union(enemy(i), enemy(j));
        return true;
    }

    private boolean setEnemies(int i, int j) {
        if (areFriends(i, j)) {
            return false;
        }
        union(i, enemy(j));
        union(j, enemy(i));
        return true;
    }

    private boolean areFriends(int i, int j) {
        return find(i) == find(j) ||
                find(enemy(i)) == find(enemy(j));
    }

    private boolean areEnemies(int i, int j) {
        return find(i) == find(enemy(j)) ||
                find(j) == find(enemy(i));
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        War war = new War(s.nextInt());
        while (s.hasNextInt()) {
            switch (s.nextInt()) {
                case 0:
                    return;
                case 1:
                    if (!war.setFriends(s.nextInt(), s.nextInt())) {
                        System.out.println(-1);
                    }
                    break;
                case 2:
                    if (!war.setEnemies(s.nextInt(), s.nextInt())) {
                        System.out.println(-1);
                    }
                    break;
                case 3:
                    System.out.println(war.areFriends(s.nextInt(), s.nextInt()) ? 1 : 0);
                    break;
                case 4:
                    System.out.println(war.areEnemies(s.nextInt(), s.nextInt()) ? 1 : 0);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}

