package se.tevej.game.view.rendering;

import se.tevej.game.math.TVector2;

public interface TCamera {

    float getViewportWidth();
    float getViewportHeight();

    void setViewport(float viewportWidth, float viewportHeight);

    default void setViewportWidth(float viewportWidth){
        setViewport(viewportWidth, getViewportHeight());
    }

    default void setViewportHeight(float viewportHeight){
        setViewport(getViewportWidth(), viewportHeight);
    }

    default float getPositionX(){
        return getPosition().getX();
    }

    default float getPositionY(){
        return getPosition().getY();
    }

    TVector2 getPosition();

    default void setPosition(float x, float y){
        getPosition().addWith(x, y);
    }

    void setPosition(TVector2 position);

    default void setPositionX(float x){
        setPosition(x, getPositionY());
    }

    default void setPositionY(float y){
        setPosition(getPositionX(), y);
    }

}
