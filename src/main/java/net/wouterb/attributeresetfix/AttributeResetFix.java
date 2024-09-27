package net.wouterb.attributeresetfix;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttributeResetFix implements ModInitializer {
	@Override
	public void onInitialize() {
		ServerPlayerEvents.AFTER_RESPAWN.register(AttributeResetFix::onPlayerRespawn);

	}

	private static void onPlayerRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
		newPlayer.getAttributes().setFrom(oldPlayer.getAttributes());
	}
}