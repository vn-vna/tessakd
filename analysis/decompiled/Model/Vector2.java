/*
 * Decompiled with CFR 0.152.
 */
package Model;

import Model.CRes;

public class Vector2 {
    public float x;
    public float y;

    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public void set(Vector2 vector2) {
        this.x = vector2.x;
        this.y = vector2.y;
    }

    public Vector2 substract(Vector2 vector2) {
        this.x -= vector2.x;
        this.y -= vector2.y;
        return this;
    }

    public Vector2 add(Vector2 vector2) {
        this.x += vector2.x;
        this.y += vector2.y;
        return this;
    }

    public Vector2 add(float f, float f2) {
        this.x += f;
        this.y += f2;
        return this;
    }

    public Vector2 mul(Vector2 vector2) {
        this.x += vector2.x;
        this.y += vector2.y;
        return this;
    }

    public float length() {
        return CRes.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2 normalize() {
        float f = this.length();
        if (f != 0.0f) {
            this.x /= f;
            this.y /= f;
        }
        return this;
    }

    public static float distance(Vector2 vector2, Vector2 vector22) {
        float f = vector22.x - vector2.x;
        float f2 = vector22.y - vector2.y;
        return CRes.sqrt(f * f + f2 * f2);
    }

    public static Vector2 substract(Vector2 vector2, Vector2 vector22) {
        float f = vector22.x - vector2.x;
        float f2 = vector22.y - vector2.y;
        return new Vector2(f, f2);
    }
}

