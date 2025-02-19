package com.theokapi.vacuum_air;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WatchItem extends Item {
    public WatchItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        long ticks = world.getTimeOfDay();
        long extraHours = ticks % 1000;
        long hours = (ticks - extraHours) / 1000;
        long extraMinutes = extraHours % 17;
        long minutes = (extraHours - extraMinutes) / 17;
        String minuteString;
        if (minutes < 10) minuteString = ":0"+minutes;
        else minuteString = ":"+minutes;
        hours += 6;
        String amPm = "AM";
        if (hours > 11 && hours < 24) amPm = "PM";
        while (hours > 12) hours -= 12;

        user.sendMessage(Text.literal(hours+minuteString+" "+amPm), true);
        return super.use(world, user, hand);
    }
}
