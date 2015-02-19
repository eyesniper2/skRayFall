package net.rayfall.eyesniper2.skRayFall.utli;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;

public class ProtocolLibUtli implements Listener{
	public static void addGlow(ItemStack[] stacks) {
				for (ItemStack stack : stacks) {
					if (stack != null) {
						if (stack.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 70 || stack.getEnchantmentLevel(Enchantment.WATER_WORKER) == 70) {
							NbtCompound compound = (NbtCompound) NbtFactory.fromItemTag(stack);
							compound.put(NbtFactory.of("HideFlags", 1));
						}
					}
				}
			}
	
	public static void run() {
      
                ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(new PacketAdapter.AdapterParameteters().plugin(skRayFall.plugin).serverSide().listenerPriority(ListenerPriority.HIGH).types(PacketType.Play.Server.SET_SLOT, PacketType.Play.Server.WINDOW_ITEMS)) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        if (event.getPacketType() == PacketType.Play.Server.SET_SLOT) {
                            addGlow(new ItemStack[] { event.getPacket().getItemModifier().read(0) });
                        } else {
                            addGlow(event.getPacket().getItemArrayModifier().read(0));
                        }
                    }
                });

            }
    
}


