package bowling_step3.controller;

import bowling_step3.domain.*;
import bowling_step3.view.Input;
import bowling_step3.view.Output;

import java.util.ArrayList;
import java.util.List;

public class BowlingApp {
    public static void main(String[] args) {
        int numPlayers = Input.scanNumPlayers();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
//            String name = Input.scanPlayer();
            Frames frames = Frames.create();
            players.add(new Player("ttt", frames));
        }
        System.out.println(players);
//        while 10 rounds
//            while n players
//
//        Frame frame = frames.first();
//        while (!frame.status().isFinished()) {
//            int randomPin = frame.scores().getRandom();
//            frame = frame.play(randomPin);
//            Subtotals subtotals = frames.first().createSubtotals();
//            Output.printFrames(10, frames, player);
//            Output.printSubtotals(subtotals, player);
//        }
    }
}
