package bowling_step3.view;

import bowling_step3.domain.*;

import java.util.List;

public class Output {
    public final static String HEADER_STR = "| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |";

    public static void print(String payload) {
        System.out.print(payload);
    }

    public static void printFrames(int round, Frames frames, Player player) {
        if (round > 0) {
            List<Integer> scores = frames.get(round - 1).scores().scores();
            if (scores.size() > 0) {
                print(round + " Frame pitch: " + scores.get(scores.size() - 1) + "\n");
            }
        }
        print(HEADER_STR + "\n");
        printScores(frames, player);
    }

    public static void printSubtotals(Subtotals subtotals, Player player) {
        List<Integer> listSubtotals = subtotals.subtotals();
        int emptySize = 10 - listSubtotals.size();
        String payload = "| " + formatName(player.name()) + " |" + listSubtotals.stream()
                .map(subtotal -> formatRecord(subtotal + ""))
                .reduce("", (acc, cur) -> acc + cur + "|") // empty
                + (formatRecord("") + "|").repeat(emptySize)
                + "\n";
        print(payload);
        System.out.println();
    }

    private static void printScores(Frames frames, Player player) {
        String payload = "| " + formatName(player.name()) + " |" + frames.frames()
                .stream()
                .map(frame -> formatScore(frame.scores().scores()))
                .reduce((acc, cur) -> acc + "|" + cur)
                .orElseThrow(() -> new UnsupportedOperationException())
                + "|\n";
        print(payload);
    }

    private static String formatName(String name) {
        return String.format("%-4s", name);
    }

    private static String formatScore(List<Integer> scores) {
        String payload = scores.stream()
                .map(String::valueOf)
                .reduce((acc, cur) -> getString(acc, cur, scores))
                .orElse("");
        return formatRecord(payload);
    }

    private static String getString(String acc, String cur, List<Integer> scores) {
        if (scores.size() == 2 && scores.stream().mapToInt(Integer::valueOf).sum() == 10) {
            cur = "/";
        }
        return toSymbol(acc) + "|" + toSymbol(cur);
    }

    private static String toSymbol(String number) {
        if (number.equals("10")) {
            return "X";
        }
        if (number.equals("0")) {
            return "-";
        }
        return number;
    }

    private static String formatRecord(String payload) {
        return String.format("%-8s", "  " + payload);
    }

}
