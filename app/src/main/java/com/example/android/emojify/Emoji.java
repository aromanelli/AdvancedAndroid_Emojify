package com.example.android.emojify;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

// TODO (1): Create an enum class called Emoji that contains all the possible emoji you can make
// (smiling, frowning, left wink, right wink, left wink frowning, right wink frowning, closed eye smiling, close eye frowning).
// https://developer.android.com/reference/android/support/annotation/IntDef
// https://www.myandroidsolutions.com/2015/10/31/android-intdef-annotation-example/

public class Emoji {

    // Category Eye Open: OPEN, CLOSED, LEFT, RIGHT
    // Category Mouth: SMILING, FROWNING

    final static int SMILING = 10;
    final static int FROWNING = 15;
    final static int WINK_LEFT = 20;
    final static int WINK_RIGHT = 25;
    final static int FROWNING_WINK_LEFT = 30;
    final static int FROWNING_WINK_RIGHT = 35;
    final static int EYE_CLOSED_SMILING = 40;
    final static int EYE_CLOSED_FROWNING = 45;

    @IntDef({
            SMILING,
            FROWNING,
            WINK_LEFT,
            WINK_RIGHT,
            FROWNING_WINK_LEFT,
            FROWNING_WINK_RIGHT,
            EYE_CLOSED_SMILING,
            EYE_CLOSED_FROWNING
    })

    @Retention(RetentionPolicy.SOURCE)

    @interface Face {}

    @Face private int face = Emoji.SMILING;

    @Face
    public int getFace() {
        return face;
    }

    public void setFace(@Face int face) {
        this.face = face;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emoji emoji = (Emoji) o;
        return face == emoji.face;
    }

    @Override
    public int hashCode() {

        return Objects.hash(face);
    }

    @Override
    public String toString() {
        return "Emoji{" +
                "face=" + face +
                '}';
    }
}