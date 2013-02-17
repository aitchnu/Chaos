package com.project.chaos;

import java.awt.image.SampleModel;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.project.chaos.objects.Terrain.SimpleTerrain;
import com.project.chaos.objects.Terrain.Terrain;

public class Chaos implements ApplicationListener
{
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	public World gameWorld;
	public ObjectHandler objectManager;
	public Box2DDebugRenderer renderer;
	public BaseObject sampleObject;
	public Terrain simpleTerrain;

	@Override
	public void create()
	{
		float w = 10f;
		float h = 10f * (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();

		camera = new OrthographicCamera(w,h);
		camera.position.set(w/2,h/2,0f);
		camera.update();
		batch = new SpriteBatch();
		Gdx.app.log(Float.toString(w), Float.toString(h));
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);

		sprite = new Sprite(region);
		//sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setSize(sprite.getWidth(), sprite.getHeight());
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		//sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
		sprite.setPosition(-w / 2, -h / 2);
		gameWorld = new World(new Vector2(0f, -9.8f), true);
		objectManager = new ObjectHandler(gameWorld);
		sampleObject = new BaseObject("objects/sampleObject.xml", objectManager);
		simpleTerrain = new SimpleTerrain("objects/terrain/simpleTerrain.xml", objectManager);
		renderer = new Box2DDebugRenderer();
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();
//		sprite.draw(batch);
//		batch.end();
		gameWorld.clearForces();
		gameWorld.step(1/60f, 6,2);
		renderer.render(gameWorld, camera.combined);
		//Gdx.app.log(Float.toString(sample.mBody.getPosition().y), Float.toString(sample.mBody.getLinearVelocity().y));
	}

	@Override
	public void resize(int width, int height)
	{
		float w = 10f;
		float h = 10f * (float)height/(float)width;
		camera.viewportWidth=w;
		camera.viewportHeight=h;
		camera.position.set(w/2,h/2,0f);
		camera.update();
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}
