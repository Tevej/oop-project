package se.tevej.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.tevej.game.libgdx.math.MathLibgdxFactory;
import se.tevej.game.libgdx.view.rendering.RenderingLibgdxFactory;
import se.tevej.game.math.MathFactory;
import se.tevej.game.view.rendering.RenderingFactory;
import se.tevej.game.view.rendering.TBatchRenderer;
import se.tevej.game.view.rendering.TTexture;

public class Game extends ApplicationAdapter {
	TBatchRenderer batchRenderer;
	TTexture texture;

	RenderingFactory renderingFactory;
	MathFactory mathFactory;
	
	@Override
	public void create () {
		renderingFactory = new RenderingLibgdxFactory();
		mathFactory = new MathLibgdxFactory();

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
