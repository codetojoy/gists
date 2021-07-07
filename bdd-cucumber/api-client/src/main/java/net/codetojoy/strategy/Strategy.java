package net.codetojoy.strategy;

import java.util.stream.IntStream;

public interface Strategy {
    int selectCard(int prizeCard, IntStream hand, int maxCard);
}
