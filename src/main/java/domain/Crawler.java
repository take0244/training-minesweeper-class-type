package domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

public final class Crawler {

    @RequiredArgsConstructor
    @Getter
    public final static class Counter {
        int countAround;
        private final int x, y;
        private final PanelStatus status;
        public String toString() {
            return String.format("x: %d, y: %d, count: %d. status: %s", this.x, this.y, this.countAround, this.status);
        }
    }

    public List<Counter> eatPanels(final int x, final int y) {
        if (Board.getValue(x, y).equals(PanelStatus.Opened) || !Board.isInside(x, y)) return Collections.emptyList();
        if (Board.getValue(x, y).equals(PanelStatus.Bomb)) return Collections.singletonList(new Counter(x, y, PanelStatus.Bomb));

        Board.open(x, y);
        final Counter counter = new Counter(x, y, PanelStatus.None);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((i == 0 && j == 0) || !Board.isInside(x+i, y+j)) continue;
                if (Board.getValue(x+i, y+j).equals(PanelStatus.Bomb)) counter.countAround++;
            }
        }

        final List<Counter> counters = new ArrayList<>(Collections.singletonList(counter));
        if (counter.countAround == 0) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++ ) {
                    if (i == 0 && j == 0) continue;
                    if (Board.isInside(x+i, y+j)) counters.addAll(eatPanels(x+i, y+j));
                }
            }
        }

        return counters;
    }
}