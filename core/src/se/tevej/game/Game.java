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
import se.tevej.game.model.factories.WorldFactory;
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

		TButton button = renderingFactory.createButton().image("hulk.jpeg").addListener(() -> System.out.println("Hej!"));
		TSelectableList selectableList = renderingFactory.createSelectableList().items("Glass", "Godis", "Dricka", "Choklad", "Asdf", "Hmmm", "Marabou").addListener(newSelected -> System.out.println("Selected: " + newSelected));

		TTextField textField = renderingFactory.createTextField().set("Hej").addListener(value -> {
			System.out.println("New value of textfield:" + value);
		});

		TLabel label = renderingFactory.createLabel().text("This is a label");

		table = renderingFactory.createTable().x(Gdx.graphics.getWidth() / 2).y(Gdx.graphics.getHeight() - 200).grid(2, 2).debug(true);

		table.addElement(button).width(200).height(50);
		table.addElement(textField).width(200).height(50);
		table.addElement(label).width(200).height(200);
		table.addElement(selectableList).width(200).height(200);

		Entity worldEntitiy = WorldFactory.createWorldEntity(100,100,em);
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
