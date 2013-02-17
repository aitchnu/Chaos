package com.project.chaos.objects.Terrain;

import com.badlogic.gdx.physics.box2d.Body;
import com.project.chaos.BaseObject;
import com.project.chaos.ObjectHandler;

public abstract class Terrain extends BaseObject
{
	Terrain(String pFileName, ObjectHandler pObjectManager)
	{
		super(pFileName, pObjectManager);
	}
}