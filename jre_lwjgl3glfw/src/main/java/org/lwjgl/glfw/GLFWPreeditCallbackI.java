package org.lwjgl.glfw;

/**
 * Minimal stand-in for LWJGL's real GLFWPreeditCallbackI: matches the invoke() signature callers
 * compile against, but doesn't extend the real CallbackI/libffi native-callback machinery, since
 * this GLFW shim has no real window system to ever actually fire it.
 */
@FunctionalInterface
public interface GLFWPreeditCallbackI {
    void invoke(long window, int preedit_count, long preedit_string, int block_count, long block_sizes, int focused_block, int caret);
}
