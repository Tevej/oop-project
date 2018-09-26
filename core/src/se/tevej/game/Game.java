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
import se.tevej.game.view.rendering.ui.TButton;
import se.tevej.game.view.rendering.ui.TTable;

public class Game extends ApplicationAdapter {
	RenderingFactory renderingFactory;

	EntityManager em;
	View view;
	TTable table;

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

		TButton button = renderingFactory.createButton().text("This is a button").addListener(() -> System.out.println("Hej!"));

		table = renderingFactory.createTable().x(Gdx.graphics.getWidth() / 2).y(Gdx.graphics.getHeight() - 50);
		table.addElement(button).width(200).height(50);
	}

	@Override
	public void render () {
		em.update(1f / 60f);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		view.render();

		table.update();
		table.render();
	}
	
	@Override
	public void dispose () {
	}
}
