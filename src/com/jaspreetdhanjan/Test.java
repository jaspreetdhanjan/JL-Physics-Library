package com.jaspreetdhanjan;

import java.util.*;

import com.jaspreetdhanjan.phys.*;
import com.jaspreetdhanjan.phys.shape.CircleShape;
import com.jaspreetdhanjan.vecmath.Vec2;

public class Test implements Runnable {
	private boolean stop = false;

	private PhysicsSpace physicsSpace = new PhysicsSpace(new AABB(0, 0, 800, 600));

	private Random random = new Random();
	private List<CircleShape> testCircles = new ArrayList<CircleShape>();
	private List<Integer> testCircleColours = new ArrayList<Integer>();

	public void run() {
		init();

		long lastTime = System.currentTimeMillis();
		int frames = 0;

		while (!stop) {
			tick();
			frames++;
			
			while (System.currentTimeMillis() - lastTime > 1000) {
				System.out.println(frames + " fps");
				lastTime += 1000;
				frames = 0;
			}
		}
	}

	private void init() {
	}

	private void addRandomCircle() {
		float randomRadius = 10 + (random.nextFloat() * 50f);
		float xx = random.nextInt((int) physicsSpace.getAABB().getWidth());
		float yy = randomRadius * 2;

		CircleShape circle = new CircleShape(new Vec2(xx, yy), randomRadius);
		testCircles.add(circle);
		physicsSpace.addDynamicShape(circle, randomRadius / 100f);
		testCircleColours.add(random.nextInt());
	}

	private void tick() {
		if (random.nextInt(50) == 0) {
			addRandomCircle();
		}
		
		physicsSpace.tick();
	}

	public static void main(String[] args) {
		new Thread(new Test()).start();
	}
}