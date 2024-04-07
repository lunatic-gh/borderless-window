package me.lunatic.borderlesswindow.mixin;

import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Window.class)
public class MixinWindow {

    @Redirect(method = "updateWindowRegion", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwSetWindowMonitor(JJIIIII)V"))
    public void setBorderless(long window, long monitor, int xpos, int ypos, int width, int height, int refreshRate) {
        if (monitor != 0L) {
            GLFW.glfwSetWindowSizeLimits(window, 0, 0, width, height);
        }
        GLFW.glfwSetWindowMonitor(window, 0L, xpos, ypos, width, height, refreshRate);
    }
}
