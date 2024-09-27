package net.wouterb.attributeresetfix.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.AttributeCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.wouterb.attributeresetfix.interfaces.IAttributeInstanceHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AttributeCommand.class)
public abstract class AttributeCommandMixin {

    @Shadow
    private static EntityAttributeInstance getAttributeInstance(Entity entity, RegistryEntry<EntityAttribute> attribute) throws CommandSyntaxException {
        return null;
    }

    @Inject(method = "executeBaseValueSet", at = @At("TAIL"))
    private static void executeBaseValueSet(ServerCommandSource source, Entity target, RegistryEntry<EntityAttribute> attribute, double value, CallbackInfoReturnable<Integer> cir) throws CommandSyntaxException {
        EntityAttributeInstance attributeInstance = getAttributeInstance(target, attribute);
        if (attributeInstance == null) return;

        if (attributeInstance.getAttribute() == EntityAttributes.GENERIC_MAX_HEALTH) {
            if (value < ((IAttributeInstanceHelper)attributeInstance).attributeresetfix$getRawValue())
                target.damage(source.getWorld().getDamageSources().fall(), 0.1F);
        }

    }
}
