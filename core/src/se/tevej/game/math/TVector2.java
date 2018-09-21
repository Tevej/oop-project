package se.tevej.game.math;

public interface TVector2 {

    float getX();
    float getY();

    default void setX(float x){
        setXY(x, getY());
    }

    default void setY(float y){
        setXY(getX(), y);
    }

    default void set(TVector2 tVector){
        setXY(tVector.getX(), tVector.getY());
    }

    void setXY(float x, float y);

    TVector2 addWith(float x, float y);
    TVector2 addWith(TVector2 tVector);

    default TVector2 subWith(TVector2 tVector){
        return addWith(tVector.invert());
    }

    TVector2 scale(float scale);

    TVector2 rotateDegrees(float degrees);

    default TVector2 rotateRadians(float radians){
        return rotateDegrees((float) (radians * (180.0 / Math.PI)));
    }

    TVector2 copy();
    TVector2 invert();

}
