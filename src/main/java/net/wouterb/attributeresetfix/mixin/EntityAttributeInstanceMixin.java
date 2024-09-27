package net.wouterb.attributeresetfix.mixin;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.wouterb.attributeresetfix.interfaces.IAttributeInstanceHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityAttributeInstance.class)
public class EntityAttributeInstanceMixin implements IAttributeInstanceHelper {
    @Shadow
    private double value;

    @Unique
    public double attributeresetfix$getRawValue() {
        return value;
    }
}
