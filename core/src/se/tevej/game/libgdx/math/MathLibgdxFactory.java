package se.tevej.game.libgdx.math;

import se.tevej.game.math.MathFactory;
import se.tevej.game.math.TVector2;

public class MathLibgdxFactory implements MathFactory {
    @Override
    public Vector2LibgdxAdapter createVector2() {
        return new Vector2LibgdxAdapter();
    }
}
