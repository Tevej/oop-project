package se.tevej.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import se.tevej.game.model.ashley.EntityManager;
import se.tevej.game.libgdx.view.rendering.RenderingLibgdxFactory;
import se.tevej.game.model.components.PositionComponent;
import se.tevej.game.model.components.SizeComponent;
import se.tevej.game.model.components.TileComponent;
import se.tevej.game.view.View;
import se.tevej.game.view.rendering.RenderingFactory;
import se.tevej.game.view.rendering.TBatchRenderer;
import se.tevej.game.view.rendering.TTexture;

public class Game extends ApplicationAdapter {
	RenderingFactory renderingFactory;

	EntityManager em;
	View view;

	@Override
	public void create () {
		renderingFactory = new RenderingLibgdxFactory();

		em = new EntityManager();
		view = new View(em, renderingFactory);

		Entity entity = em.createEntity();
		PositionComponent pc = em.createComponent(PositionComponent.class);
		SizeComponent sc = em.createComponent(SizeComponent.class);
		TileComponent tc = em.createComponent(TileComponent.class);

		pc.setX(50);
		pc.setY(50);

		sc.setWidth(32);
		sc.setHeight(32);

		entity.add(pc);
		entity.add(sc);
		entity.add(tc);

		em.addEntityToEngine(entity);
	}

	@Override
	public void render () {
		em.update(1f / 60f);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		view.render();
	}
	
	@Override
	public void dispose () {
	}
}
