package mods.Hileb.respawn;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class CommonClass {

    public static void init() {
        Constants.LOG.info("Hello to {}", Constants.MOD_ID);
    }

    public static void onDeath(EntityPlayer player) {
        String x = String.valueOf(Math.round(player.posX * 1000.0D) / 1000.0D);
        String y = String.valueOf(Math.round(player.posY * 1000.0D) / 1000.0D);
        String z = String.valueOf(Math.round(player.posZ * 1000.0D) / 1000.0D);
        ITextComponent component = new TextComponentString(x + ", " + y + ", " + z)
                .setStyle(new Style()
                        .setColor(TextFormatting.GREEN)
                        .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @s " + x + " " + y + " " + z)));
        player.sendMessage(new TextComponentTranslation("message.de").appendSibling(component));
    }
}
