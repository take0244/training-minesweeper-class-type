package usecase.interfaces;

import domain.Crawler;

import java.util.List;

public interface SearchUseCase {
    List<Crawler.Counter> getReversiblePoints(final int x, final int y);
    void initBoard(int bombNumber);
}
