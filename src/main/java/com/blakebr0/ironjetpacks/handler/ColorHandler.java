package com.blakebr0.ironjetpacks.handler;

import com.blakebr0.cucumber.iface.IColored;
import com.blakebr0.ironjetpacks.init.ModItems;
import com.blakebr0.ironjetpacks.registry.JetpackRegistry;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ColorHandler {
	@SubscribeEvent
	public void onItemColors(ColorHandlerEvent.Item event) {
		var colors = event.getItemColors();
		var registry = JetpackRegistry.getInstance();

		if (registry.isErrored())
			return;

		colors.register((stack, tint) -> {
			var item = (IColored) stack.getItem();
			return item.getColor(tint, stack);
		}, ModItems.JETPACK.get(), ModItems.CELL.get(), ModItems.THRUSTER.get(), ModItems.CAPACITOR.get());
	}
}
