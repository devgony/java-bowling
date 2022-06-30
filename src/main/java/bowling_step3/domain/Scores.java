package bowling_step3.domain;

import java.util.*;

public class Scores {
    public static int MAX_SCORE = 10;
    private static Random Random = new Random();
    private final List<Integer> scores;
    private final int remainingPitch;

    public Scores() {
        this(new ArrayList(), 2);
    }

    public Scores(int remainingPitch) {
        this(new ArrayList(), remainingPitch);
    }

    public Scores(List<Integer> scores, int remainingPitch) {
        this.scores = scores;
        this.remainingPitch = remainingPitch;
    }

    public static Scores strike() {
        return new Scores(new ArrayList<>(Arrays.asList(10)),0);
    }

    public Scores pitch(int numPins) {
        this.scores.add(numPins);
        if (numPins == 10 && sum() != 20) {
            return new Scores(this.scores, remainingPitch - 2);
        }
        return new Scores(this.scores, remainingPitch - 1);
    }

    public Scores pitchLast(int numPins) {
        this.scores.add(numPins);
        return new Scores(this.scores, remainingPitch - 1);
    }

    public int getRandom() {
        return Random.nextInt(remainingPins());
    }

    private int remainingPins() {
        if (this.scores.size() == 0) {
            return MAX_SCORE + 1;
        }
        return MAX_SCORE - lastScore() + 1;
    }

    int lastScore() {
        int size = this.scores.size();
        if (size == 0) {
            throw new IllegalArgumentException("No scores yet.");
        }
        return this.scores.get(size - 1);
    }

    public boolean done() {
        return this.remainingPitch <= 0;
    }

    public int sum() {
        return this.scores.stream()
                .reduce(0, (acc, cur) -> acc + cur);
    }

    public List<Integer> scores() {
        return this.scores;
    }

    @Override
    public String toString() {
        return "Scores{" +
                "scores=" + scores +
                ", remainingPitch=" + remainingPitch +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scores scores1 = (Scores) o;
        return remainingPitch == scores1.remainingPitch && Objects.equals(scores, scores1.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores, remainingPitch);
    }

    public Integer getScore() {
        if (done()) {
            return sum();
        }
        throw new UnsupportedOperationException("Cannot get score yet");
    }

    public int getFirstScore() {
        if (this.scores.size() >= 1) {
            return this.scores.get(0);
        }
        throw new UnsupportedOperationException("Not enough scores");
    }

    public Integer sumOfTwo() {
        return this.scores
                .stream()
                .limit(2)
                .reduce((acc, cur) -> acc + cur)
                .orElseThrow(() -> new UnsupportedOperationException("cannot get sum of Two"));
    }

    public boolean isSpare() {
        return this.sumOfTwo() == MAX_SCORE;
    }

    public boolean isStrike() {
       return this.lastScore() == MAX_SCORE;
    }

    public int size() {
        return this.scores.size();
    }
}
