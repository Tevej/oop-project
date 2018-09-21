package se.tevej.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import se.tevej.game.libgdx.view.rendering.RenderingLibgdxFactory;
import se.tevej.game.view.rendering.RenderingFactory;
import se.tevej.game.view.rendering.TBatchRenderer;
import se.tevej.game.view.rendering.TTexture;

public class Game extends ApplicationAdapter {
	TBatchRenderer batchRenderer;
	TTexture texture;

	RenderingFactory renderingFactory;

	@Override
	public void create () {
		renderingFactory = new RenderingLibgdxFactory();

		batchRenderer = renderingFactory.createBatchRenderer();
		texture = renderingFactory.createTexture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batchRenderer.beginRendering();
		batchRenderer.renderTexture(texture, 50, 50);
		batchRenderer.endRendering();
	}
	
	@Override
	public void dispose () {
	}
}
