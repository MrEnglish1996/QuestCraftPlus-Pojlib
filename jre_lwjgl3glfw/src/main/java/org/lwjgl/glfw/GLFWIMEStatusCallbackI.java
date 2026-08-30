package org.lwjgl.glfw;

/** Minimal stand-in for LWJGL's real GLFWIMEStatusCallbackI - see GLFWPreeditCallbackI. */
@FunctionalInterface
public interface GLFWIMEStatusCallbackI {
    void invoke(long window);
}
