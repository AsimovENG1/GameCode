package com.asimov.piazzapanic;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.ScreenAdapter;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;

public class SettingsScreen extends ScreenAdapter {
    final PiazzaPanic game;

    OrthographicCamera camera;

    public SettingsScreen(final PiazzaPanic game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}