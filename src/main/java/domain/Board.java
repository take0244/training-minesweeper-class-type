package domain;

import domain.error.OutSideException;

import java.util.Random;

public final class Board {
    static { init(); }

    private static PanelStatus[][] map;

    public static void init() {

        Board.map = new PanelStatus[Config.TROUT][Config.TROUT];
        for (int i = 0; i < Config.TROUT; i++) {
            for (int j = 0; j < Config.TROUT; j++) {
                Board.map[i][j] = PanelStatus.None;
            }
        }
    }

    public static boolean isInside (final int x, final int y) {
        return x >= 0 && x < Board.map.length && y >= 0 && y < Board.map[x].length;
    }

    public static boolean setBom(final int x, final int y) {
        if (!Board.isInside(x, y)) return false;
        Board.map[x][y] = PanelStatus.Bomb;
        return true;
    }

    public static void setRandBombs(int n) {
        final Random rand = new Random();
        for (int i = 0; i < n; i++) {
            Board.setBom(rand.nextInt(Config.TROUT), rand.nextInt(Config.TROUT));
        }
    }

    public static PanelStatus getValue(final int x, final int y) {
        if (!Board.isInside(x, y)) throw new OutSideException(x, y);
        return map[x][y];
    }

    public static void open(final int x, final int y) {
        if (!Board.isInside(x, y)) throw new OutSideException(x, y);
        Board.map[x][y] = PanelStatus.Opened;
    }
}
