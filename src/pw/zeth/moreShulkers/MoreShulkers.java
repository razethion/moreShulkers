package pw.zeth.moreShulkers;

import java.util.List;
import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MoreShulkers extends JavaPlugin implements Listener {

	final Plugin plugin = this;
	final FileConfiguration config = plugin.getConfig();

	@Override
	public void onEnable() {

		// Check if config exists, if not create it
		plugin.saveDefaultConfig();

		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void playerKilledShulker(EntityDeathEvent entity) {

		if (!(entity.getEntity() instanceof Shulker))
			return;
		if (entity.getDrops().size() < 1)
			return;

		List<ItemStack> plannedDrops = entity.getDrops();
		for (ItemStack item : plannedDrops) {

			int plannedAmount = item.getAmount();

			Random rand = new Random();
			double randomNo = rand.nextDouble();

			if (randomNo <= config.getDouble("dropchance"))
				item.setAmount(plannedAmount * 2);
		}

	}

}
