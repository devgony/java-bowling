package bowling.domain.bowlinggame;

import bowling.domain.KnockDownPins;
import bowling.domain.PlayerName;
import bowling.domain.frames.Frames;
import bowling.dto.BowlingGameDto;

public class BowlingGame {
    private final Frames frames;
    private final PlayerName playerName;

    private BowlingGame(Frames frames, PlayerName playerName) {
        this.frames = frames;
        this.playerName = playerName;
    }

    public static BowlingGame init(PlayerName playerName) {
        return new BowlingGame(Frames.init(), playerName);
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        frames.setKnockDownPins(knockDownPins);
    }

    public boolean isEnd() {
        return frames.isEnd();
    }

    public int getCurrentFrameIndex() {
        return frames.getCurrentFrameIndex();
    }

    public BowlingGameDto convertToDto() {
        return BowlingGameDto.of(frames.convertToDto(), playerName.getValue());
    }
}
