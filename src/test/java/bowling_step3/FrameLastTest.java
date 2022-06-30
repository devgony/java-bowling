package bowling_step3;

import bowling_step3.domain.*;
import bowling_step3.domain.state.Spare;
import bowling_step3.domain.state.Status;
import bowling_step3.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameLastTest {

    private Frames frames;
    private Frame lastFrame;

    @BeforeEach
    public void setup() {
        frames = Frames.create();
        lastFrame = frames.last();
    }

    @Test
    public void miss() {
        lastFrame = lastFrame.play(8);
        lastFrame = lastFrame.play(1);
        assertThat(lastFrame.status().isFinished()).isTrue();
    }

    @Test
    public void spare_isnot_endgame() {
        lastFrame = lastFrame.play(8);
        lastFrame = lastFrame.play(2);
        assertThat(lastFrame.status().isFinished()).isFalse();
    }

    @Test
    public void spare_is_endgame() {
        lastFrame = lastFrame.play(8);
        lastFrame = lastFrame.play(2);
        lastFrame = lastFrame.play(7);
        assertThat(lastFrame.status().isFinished()).isTrue();
    }

    @Test
    public void game_over() {
        lastFrame = lastFrame.play(8);
        lastFrame = lastFrame.play(2);
        lastFrame = lastFrame.play(7);
        assertThatThrownBy(() -> {
            lastFrame = lastFrame.play(3);
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void getScore_miss() {
        lastFrame = lastFrame.play(8);
        lastFrame = lastFrame.play(1);
        assertThat(lastFrame.scores().getScore()).isEqualTo(9);
    }

    @Test
    public void getScore_cannot_calculate() {
        assertThatThrownBy(() -> {
            lastFrame = lastFrame.play(8);
            lastFrame.scores().getScore();
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void getScore_spare() {
        lastFrame = lastFrame.play(8);
        lastFrame = lastFrame.play(2);
        lastFrame = lastFrame.play(9);
        assertThat(lastFrame.scores().getScore()).isEqualTo(19);
    }

    @Test
    public void getScore_twoStrike() {
        lastFrame = lastFrame.play(10).play(10).play(8);
        assertThat(lastFrame.scores().getScore()).isEqualTo(28);
    }

    @Test
    public void getScore_threeStrike() {
        lastFrame = lastFrame.play(10);
        lastFrame = lastFrame.play(10);
        lastFrame = lastFrame.play(10);
        assertThat(lastFrame.scores().getScore()).isEqualTo(30);
    }

    @Test
    public void getScore_9프레임_Strike() {
        Status status = new Strike();
        lastFrame = lastFrame.play(10);
        lastFrame = lastFrame.play(10);
        assertThat(lastFrame.calculateAdditionalScore(status)).isEqualTo(30);
    }

    @Test
    public void getScore_9프레임_Spare() {
        Scores scores = new Scores(List.of(9, 1), 0);
        Status status = new Spare(scores);
        lastFrame = lastFrame.play(9);
        assertThat(lastFrame.calculateAdditionalScore(status)).isEqualTo(19);
    }

    @Test
    public void getScore_9프레임_Strike_notReady() {
        assertThatThrownBy(() -> {
            Status status = new Strike();
            lastFrame = lastFrame.play(10);
            lastFrame.calculateAdditionalScore(status);
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}