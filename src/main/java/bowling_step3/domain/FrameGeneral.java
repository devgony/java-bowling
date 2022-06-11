package bowling_step3.domain;

public class FrameGeneral extends FrameMutual implements Frame {
    public FrameGeneral() {
        super();
    }

    public FrameGeneral(Scores scores, Subtotal subtotal) {
        super(scores, subtotal);
    }

    protected void updateScore(Scores scores, Frames frames) {
        this.scores = scores;
    }

    protected State evaluateState(Scores scores) {
        if (scores.isStrike()) {
            return State.WAIT_TWICE;
        }
        if (scores.sum() == 10) {
            return State.WAIT_ONCE;
        }
        return State.DONE;
    }
}
