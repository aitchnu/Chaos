package com.project.chaos;

import com.badlogic.gdx.physics.box2d.World;

public class ObjectHandler
{
	private World mWorld;
	
	ObjectHandler(World pWorld)
	{
		mWorld=pWorld;
	}
	
	public World getWorld()
	{
		return mWorld;
	}
}
