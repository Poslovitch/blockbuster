package mchorse.blockbuster.commands.path;

import mchorse.blockbuster.camera.Position;
import mchorse.blockbuster.camera.fixtures.AbstractFixture;
import mchorse.blockbuster.camera.fixtures.PathFixture;
import mchorse.blockbuster.commands.CommandCamera;
import mchorse.blockbuster.utils.L10n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

/**
 * Path's sub-command /camera path edit
 *
 * This sub-command is responsible for editing a point in a path fixture.
 */
public class SubCommandPathEdit extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "edit";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "blockbuster.commands.camera.path.edit";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 2)
        {
            throw new WrongUsageException(this.getCommandUsage(sender));
        }

        int index = CommandBase.parseInt(args[0]);
        int point = CommandBase.parseInt(args[1]);
        AbstractFixture fixture = CommandCamera.getProfile().get(index);

        if (!(fixture instanceof PathFixture))
        {
            L10n.error(sender, "profile.not_path", index);
            return;
        }

        PathFixture path = (PathFixture) fixture;

        if (!path.hasPoint(point))
        {
            L10n.error(sender, "profile.no_path_point", index, point);
            return;
        }

        path.editPoint(new Position((EntityPlayer) sender), point);
    }
}