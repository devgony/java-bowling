package bowling_step3.domain;

public class FrameLast extends FrameMutual implements Frame {

    public FrameLast() {
        super();
    }

    public FrameLast(Scores scores, Subtotal subtotal) {
        super(scores, subtotal);
    }

    protected void updateScore(Scores scores, Frames frames) {
        this.scores = scores.evaluateLastBonus();
    }

    protected State evaluateState(Scores scores) {
        if (scores.done()) {
            return State.DONE;
        }
        return State.INIT;
    }

}
