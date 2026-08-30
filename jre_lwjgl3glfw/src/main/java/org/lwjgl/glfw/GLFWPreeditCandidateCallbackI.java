package org.lwjgl.glfw;

/** Minimal stand-in for LWJGL's real GLFWPreeditCandidateCallbackI - see GLFWPreeditCallbackI. */
@FunctionalInterface
public interface GLFWPreeditCandidateCallbackI {
    void invoke(long window, int candidates_count, int selected_index, int page_start, int page_size);
}
