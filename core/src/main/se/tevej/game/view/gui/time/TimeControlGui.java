package main.se.tevej.game.view.gui.time;

import com.badlogic.gdx.Gdx;

import main.se.tevej.game.view.gui.base.GuiFactory;
import main.se.tevej.game.view.gui.base.OnButtonClickedListener;
import main.se.tevej.game.view.gui.base.TButton;
import main.se.tevej.game.view.gui.base.TLabel;
import main.se.tevej.game.view.gui.base.TTable;

public class TimeControlGui implements OnTimeChangeListener {

    private TTable timeControlTable;

    private ChangeTimeScale changeTimeScale;

    private TLabel scaleLabel;

    private int scalesIndex;
    private float[] possibleScales;

    public TimeControlGui(GuiFactory guiFactory) {
        timeControlTable = guiFactory.createTable();
        possibleScales = new float[] {
            1.0f, 2.0f, 5.0f, 20.0f
        };

        initializeTable();
        populateTable(guiFactory);
    }

    public void update(float deltaTime) {
        timeControlTable.update(deltaTime);
    }

    public void render() {
        timeControlTable.render();
    }

    public void setChangeTimeScale(ChangeTimeScale changeTimeScale) {
        this.changeTimeScale = changeTimeScale;
    }

    private void initializeTable() {
        timeControlTable
            .positionX(Gdx.graphics.getWidth() / 2.0f)
            .positionY(Gdx.graphics.getHeight() - 20)
            .backgroundColor(0, 0, 0, 0.7f)
            .grid(1, 4);

    }

    private void populateTable(GuiFactory guiFactory) {
        scaleLabel = guiFactory
            .createLabel()
            .text("x1.0");

        TButton playButton = guiFactory
            .createButton()
            .text("Play")
            .addListener(new OnButtonClickedListener() {
                @Override
                public void onClicked() {
                    changeTimeScale.setScale(1.0f);
                }
            });

        TButton pauseButton = guiFactory
            .createButton()
            .text("Pause")
            .addListener(new OnButtonClickedListener() {
                @Override
                public void onClicked() {
                    changeTimeScale.setScale(0.0f);
                }
            });

        TButton changeScaleButton = guiFactory
            .createButton()
            .text("Change")
            .addListener(new OnButtonClickedListener() {
                @Override
                public void onClicked() {
                    changeTimeScale.setScale(
                        possibleScales[scalesIndex++ % possibleScales.length]
                    );
                }
            });

        timeControlTable
            .addElement(pauseButton)
            .width(100)
            .height(40);

        timeControlTable
            .addElement(playButton)
            .width(100)
            .height(40);

        timeControlTable
            .addElement(changeScaleButton)
            .width(100)
            .height(40);

        timeControlTable
            .addElement(scaleLabel)
            .width(100)
            .height(40);

        timeControlTable.alignCenter();
    }

    @Override
    public void updateTimeMultiplier(float newMultiplier) {
        this.scaleLabel.text("x" + newMultiplier);
    }

}

