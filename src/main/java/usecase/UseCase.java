package usecase;

import domain.Board;
import domain.Crawler;
import usecase.interfaces.SearchUseCase;

import java.util.List;

public final class UseCase implements SearchUseCase {
    public List<Crawler.Counter> getReversiblePoints(final int x, final int y) {
        return  new Crawler().eatPanels(x, y);
    }

    public void initBoard(final int bombNumber) {
        Board.init();
        Board.setRandBombs(bombNumber);
    }
}
