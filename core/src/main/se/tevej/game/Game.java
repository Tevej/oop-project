package main.se.tevej.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import main.se.tevej.game.libgdx.view.rendering.RenderingLibgdxFactory;
import main.se.tevej.game.model.ashley.EntityManager;
import main.se.tevej.game.model.factories.WorldFactory;
import main.se.tevej.game.view.rendering.ui.*;
import main.se.tevej.game.view.View;
import main.se.tevej.game.view.rendering.RenderingFactory;

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

		TButton button = renderingFactory.createButton().image("hulk.jpeg").addListener(() -> System.out.println("Hej!"));
		TSelectableList selectableList = renderingFactory.createSelectableList().items("Glass", "Godis", "Dricka", "Choklad", "Asdf", "Hmmm", "Marabou").addListener(newSelected -> System.out.println("Selected: " + newSelected));

		TTextField textField = renderingFactory.createTextField().set("Hej").addListener(value -> {
			System.out.println("New value of textfield:" + value);
		});

		TLabel label = renderingFactory.createLabel().text("This is a label");

		table = renderingFactory.createTable().x((Gdx.graphics.getWidth() / 2f)).y(Gdx.graphics.getHeight() - 200).grid(2, 2).debug(true);

		table.addElement(button).width(200).height(50);
		table.addElement(textField).width(200).height(50);
		table.addElement(label).width(200).height(200);
		table.addElement(selectableList).width(200).height(200);

		// Look over naming of method / implementation (also adds the world to the engine.)
		WorldFactory.createWorldEntity(100,100, em);
	}

	@Override
	public void render () {
		em.update(1f / 60f);

		Gdx.gl.glClearColor(1, 1,1 ,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		view.render();

		table.update(1f / 60f);
		//table.render();
	}
	
	@Override
	public void dispose () {
	}
}
