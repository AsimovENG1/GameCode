
package com.asimov.piazzapanic;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.ScreenAdapter;
        import com.badlogic.gdx.audio.Sound;
        import com.badlogic.gdx.graphics.*;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.scenes.scene2d.ui.Image;
        import com.badlogic.gdx.scenes.scene2d.ui.Label;
        import com.badlogic.gdx.scenes.scene2d.ui.Skin;
        import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
        import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
        import com.badlogic.gdx.utils.Align;
        import com.badlogic.gdx.utils.ScreenUtils;
        import com.badlogic.gdx.utils.viewport.ScreenViewport;
        //import com.badlogic.gdx.graphics.g2d.Batch;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.graphics.g2d.Sprite;

public class InstructionScreen extends ScreenAdapter {
    final PiazzaPanic game;

    private Stage stage;
    public static Sound instclick;

    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();

    public TextButton backButton;
    Texture howto =new Texture(Gdx.files.internal("layout/dhdh.jpg"));

    Sprite sprite = new Sprite(howto);
    SpriteBatch batch = new SpriteBatch();

    OrthographicCamera camera;

    public InstructionScreen(final PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new ScreenViewport(), game.batch);

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        instclick = Gdx.audio.newSound(Gdx.files.internal("audio/Back-and-quit.wav"));



        backButton = new TextButton("Back", mySkin);

        backButton.setWidth(200);
        backButton.setHeight(100);


        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.instructionbuttoncl();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        stage.addActor(backButton);
        backButton.setPosition(10, 100);



        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

//        Label.LabelStyle style = new Label.LabelStyle();
//        style.font = game.font;
//        style.fontColor = Color.BLUE;
//
//        Label label1 = new Label("Instructions", style);
//        label1.setSize(Gdx.graphics.getWidth(),200);
//        label1.setFontScale(3);
//        label1.setPosition(100,800);
//        label1.setAlignment(Align.center);
//        stage.addActor(label1);



    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(game.color);
        stage.act(Gdx.graphics.getDeltaTime());
        backButton.setZIndex(1);
        stage.draw();
        batch.begin();
        //sprite.scale();
        sprite.setPosition(screenWidth / 2 - sprite.getWidth() / 2, screenHeight / 2 - sprite.getHeight() / 2);

        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
