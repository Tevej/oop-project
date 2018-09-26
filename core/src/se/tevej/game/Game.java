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
import se.tevej.game.view.rendering.ui.*;

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
		TSelectableList selectableList = renderingFactory.createSelectableList().items("Glass", "Godis", "Dricka", "Choklad", "Asdf", "Hmmm", "Marabou").addListener(newSelected -> System.out.println("Selected: " + newSelected));

		TTextField textField = renderingFactory.createTextField().addListener(value -> {
			System.out.println("New value of textfield:" + value);
		});

		TLabel label = renderingFactory.createLabel().text("This is a label");

		table = renderingFactory.createTable().x(Gdx.graphics.getWidth() / 2).y(Gdx.graphics.getHeight() - 200).grid(2, 2);

		table.addElement(button).width(200).height(50);
		table.addElement(textField).width(200).height(50);
		table.addElement(label).width(200).height(200);
		table.addElement(selectableList).width(200).height(200);
	}

	@Override
	public void render () {
		em.update(1f / 60f);

		Gdx.gl.glClearColor(1, 1,1 ,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		view.render();

		table.update(1f / 60f);
		table.render();
	}
	
	@Override
	public void dispose () {
	}
}
