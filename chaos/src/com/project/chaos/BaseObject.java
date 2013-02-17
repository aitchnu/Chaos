package com.project.chaos;

import java.io.IOException;

import aurelienribon.bodyeditor.BodyEditorLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.XmlReader;

public class BaseObject
{
	private String mName;
	public Body mBody;

	protected BaseObject(String pFileName, ObjectHandler pObjectManager)
	{
		XmlReader lReader = new XmlReader();
		try
		{
			XmlReader.Element lMainTag = lReader.parse(Gdx.files
					.internal(pFileName));
			mName = lMainTag.getAttribute("name");
			// Loading bodydef
			XmlReader.Element lChildTag = lMainTag.getChildByName("body")
					.getChildByName("bodydef");
			BodyDef lBodyDef = new BodyDef();
			if (lChildTag.get("type").equals("dynamic"))
				lBodyDef.type = BodyType.DynamicBody;
			else if (lChildTag.get("type").equals("static"))
				lBodyDef.type = BodyType.StaticBody;
			else if (lChildTag.get("type").equals("kinematic"))
				lBodyDef.type = BodyType.KinematicBody;
			lBodyDef.position.set(
					(lChildTag.getChildByName("position").getFloat("x")),
					(lChildTag.getChildByName("position").getFloat("y")));
			lBodyDef.linearVelocity.set(
					(lChildTag.getChildByName("velocity").getFloat("x")),
					(lChildTag.getChildByName("velocity").getFloat("y")));
			lBodyDef.angle = lChildTag.getFloat("angle");
			mBody = pObjectManager.getWorld().createBody(lBodyDef);
			// Loading fixture
			lChildTag = lMainTag.getChildByName("body").getChildByName(
					"fixture");
			FixtureDef lFixtureDef = new FixtureDef();
			lFixtureDef.density = lChildTag.getFloat("density");
			lFixtureDef.restitution = lChildTag.getFloat("restitution");
			lFixtureDef.friction = lChildTag.getFloat("friction");
			BodyEditorLoader loader = new BodyEditorLoader(
					Gdx.files.internal(lChildTag.getChildByName("shape").get(
							"fileName")));
			loader.attachFixture(mBody,
					lChildTag.getChildByName("shape").get("tag"), lFixtureDef,
					1);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void update()
	{

	}

	public void render()
	{

	}
}
