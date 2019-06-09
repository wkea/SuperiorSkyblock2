package com.bgsoftware.superiorskyblock.gui.menus.types.panel;

import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.bgsoftware.superiorskyblock.gui.MenuTemplate;
import com.bgsoftware.superiorskyblock.gui.menus.YamlMenu;
import com.bgsoftware.superiorskyblock.wrappers.SSuperiorPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class MemberMenu extends YamlMenu {

    private SuperiorPlayer member;

    public MemberMenu(Player player, SuperiorPlayer member) {
        super(player, MenuTemplate.MEMBER.getFile());
        create(title.replace("{0}", member.getName()), rows);

        this.member = member;

        addAction("role", this::role);
        addAction("kick", this::kick);
        addAction("ban", this::ban);

        canExit = false;

        load();
        open();
    }

    private void role(Player clicker, ClickType type) {
        canExit = true;
        new MemberRoleMenu(player, member);
    }

    private void kick(Player clicker, ClickType type) {
        clicker.performCommand("is kick " + member.getName());
    }

    private void ban(Player clicker, ClickType type) {
        clicker.performCommand("is ban " + member.getName());
    }

    @Override
    public void onClose() {
        if (!canExit)
            new MembersMenu(player, SSuperiorPlayer.of(player).getIsland());
    }
}
