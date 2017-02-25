package com.pong.raymondhong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * An Entity representing the Player Board
 */
public class Player extends Entity {
    private static final float playerSpeed = 0.2f;

    /**
     * Constructs a Player Board
     */
    public Player(World world) {
        super(new Sprite(new Texture("pongboard.jpg")));
        initializeBody(world);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            float newPos = body.getPosition().x - playerSpeed;
            if (newPos * Pong.PIXELS_PER_METER - getWidth() / 2 <= 0) {
                body.setTransform(getWidth() / 2 /Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
            }
            else {
                body.setTransform(newPos, body.getPosition().y, body.getAngle());
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            float newPos = body.getPosition().x + playerSpeed;
            if (newPos * Pong.PIXELS_PER_METER + getWidth() / 2 >= Gdx.graphics.getWidth()) {
                body.setTransform((Gdx.graphics.getWidth() - getWidth() / 2) / Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
            }
            else {
                body.setTransform(newPos, body.getPosition().y, body.getAngle());
            }
        }
    }

    /**
     * Initializes the body
     * @param world the world to host the body
     */
    @Override
    public void initializeBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(Gdx.graphics.getWidth() / 2 / Pong.PIXELS_PER_METER, getHeight() / 2 / Pong.PIXELS_PER_METER);
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 / Pong.PIXELS_PER_METER, getHeight() / 2 / Pong.PIXELS_PER_METER);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.friction = 0f;
        body.createFixture(fixture);
        shape.dispose();
    }
}