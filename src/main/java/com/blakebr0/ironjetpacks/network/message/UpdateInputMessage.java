package com.blakebr0.ironjetpacks.network.message;

import com.blakebr0.cucumber.network.message.Message;
import com.blakebr0.ironjetpacks.handler.InputHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateInputMessage extends Message<UpdateInputMessage> {
	private final boolean up;
	private final boolean down;
	private final boolean forwards;
	private final boolean backwards;
	private final boolean left;
	private final boolean right;
	private final boolean sprint;

	public UpdateInputMessage() {
		this.up = false;
		this.down = false;
		this.forwards = false;
		this.backwards = false;
		this.left = false;
		this.right = false;
		this.sprint = false;
	}

	public UpdateInputMessage(boolean up, boolean down, boolean forwards, boolean backwards, boolean left, boolean right, boolean sprint) {
		this.up = up;
		this.down = down;
		this.forwards = forwards;
		this.backwards = backwards;
		this.left = left;
		this.right = right;
		this.sprint = sprint;
	}

	public UpdateInputMessage read(FriendlyByteBuf buffer) {
		return new UpdateInputMessage(buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean());
	}

	public void write(UpdateInputMessage message, FriendlyByteBuf buffer) {
		buffer.writeBoolean(message.up);
		buffer.writeBoolean(message.down);
		buffer.writeBoolean(message.forwards);
		buffer.writeBoolean(message.backwards);
		buffer.writeBoolean(message.left);
		buffer.writeBoolean(message.right);
		buffer.writeBoolean(message.sprint);
	}

	public void onMessage(UpdateInputMessage message, Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			var player = context.get().getSender();

			if (player != null) {
				InputHandler.update(player, message.up, message.down, message.forwards, message.backwards, message.left, message.right, message.sprint);
			}
		});

		context.get().setPacketHandled(true);
	}
}
