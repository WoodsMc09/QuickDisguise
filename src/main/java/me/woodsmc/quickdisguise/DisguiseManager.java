package me.woodsmc.quickdisguise;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DisguiseManager {
    private final QuickDisguise qD = QuickDisguise.getPlugin(QuickDisguise.class);

    public void disguise(Player p, EntityType entity, boolean name){
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 40, 1, false, false, false));
        Entity e = p.getWorld().spawnEntity(p.getLocation(), entity);
        if(name){
            e.setCustomName(p.getDisplayName());
            e.setCustomNameVisible(true);
            return;
        }
        qD.getDisguiseYML().getConfig().set(p + ".disguised", true);
        qD.getDisguiseYML().getConfig().set(p + ".entity", e);
        qD.getDisguiseYML().saveConfig();
    }

    public void unDisquise(Player p){
        Entity entity = (Entity) qD.getDisguiseYML().getConfig().get(p + ".entity");
        entity.remove();
        qD.getDisguiseYML().getConfig().set(p + ".disguised", false);
        qD.getDisguiseYML().getConfig().set(p + ".entity", null);
        qD.getDisguiseYML().saveConfig();
        p.removePotionEffect(PotionEffectType.INVISIBILITY);
        
    }
}
