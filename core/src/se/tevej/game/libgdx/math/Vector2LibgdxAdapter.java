package se.tevej.game.libgdx.math;

import com.badlogic.gdx.math.Vector2;
import se.tevej.game.math.TVector2;

public class Vector2LibgdxAdapter extends Vector2 implements TVector2 {
    @Override
    public float getX() {
        return super.x;
    }

    @Override
    public float getY() {
        return super.y;
    }

    @Override
    public void setXY(float x, float y){
        super.set(x, y);
    }

    @Override
    public Vector2LibgdxAdapter addWith(float x, float y) {
        return (Vector2LibgdxAdapter) super.add(x, y);
    }

    @Override
    public Vector2LibgdxAdapter addWith(TVector2 tVector) {
        Vector2LibgdxAdapter v = (Vector2LibgdxAdapter) tVector;
        return (Vector2LibgdxAdapter) super.add(v);
    }

    @Override
    public Vector2LibgdxAdapter scale(float scale) {
        return (Vector2LibgdxAdapter) super.scl(scale);
    }

    @Override
    public Vector2LibgdxAdapter rotateDegrees(float degrees) {
        return (Vector2LibgdxAdapter) super.rotate(degrees);
    }

    @Override
    public Vector2LibgdxAdapter copy() {
        return (Vector2LibgdxAdapter) super.cpy();
    }

    @Override
    public Vector2LibgdxAdapter invert() {
        Vector2LibgdxAdapter v = (Vector2LibgdxAdapter) new Vector2(-getX(), -getY());
        return v;
    }
}
