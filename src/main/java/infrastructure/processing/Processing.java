package infrastructure.processing;

import domain.Crawler;
import domain.PanelStatus;
import usecase.UseCase;
import usecase.interfaces.SearchUseCase;
import static domain.Config.*;

import java.util.List;
import processing.core.PApplet;

public class Processing extends PApplet {
    private final SearchUseCase useCase = new UseCase();
    private boolean isEnd = false;

    @Override
    public void settings() {
        size(HW,HW);
    }

    @Override
    public void setup() {
        frameRate(30);
        initPanels();
    }

    @Override
    public void draw() {
        if (this.isEnd) {
            delay(1000);
            this.initPanels();
            this.isEnd = false;
        }
    }

    @Override
    public void mousePressed(){
        final int x = mouseX *TROUT/HW;
        final int y = mouseY *TROUT/HW;
        final List<Crawler.Counter> counters = this.useCase.getReversiblePoints(x, y);
        counters.forEach(c -> openPanel(c.getX(), c.getY(), c.getStatus(), c.getCountAround()));
    }

    private void openPanel(final int x, final int y, final PanelStatus s, final int n)  {
        final float ratio = (float)HW / (float)TROUT;
        final float fontSize = 11;

        fill(255, 255, 255);
        rect(ratio*x + 1, ratio*y+1, ratio - 1, ratio - 1);


        if (s.equals(PanelStatus.Bomb)) {
            fill(0);
            ellipse(ratio*x + ratio/2, ratio*y + ratio/2, (float) (ratio*0.6), (float) (ratio*0.6));
            this.isEnd = true;
        } else if (n > 0) {
            fill(0);
            textSize(fontSize);
            text(String.valueOf(n), ratio*x+(ratio/2) - fontSize/2, ratio*y+(ratio/2) + fontSize/2);
        }
    }

    private void initPanels() {
        this.useCase.initBoard((int) ((float)TROUT*(float)TROUT*(float)0.1));
        background(200, 200, 200);
        final int ratio = HW /TROUT;
        for (int i = 0; i <TROUT; i ++) {
            line(0, i * (ratio),HW, i * (ratio));
            line(i * (ratio), 0, i * (ratio),HW);
        }
    }
}
