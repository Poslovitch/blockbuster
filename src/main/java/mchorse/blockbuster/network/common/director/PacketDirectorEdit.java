package mchorse.blockbuster.network.common.director;

import io.netty.buffer.ByteBuf;
import mchorse.blockbuster.common.tileentity.director.Replay;
import net.minecraft.util.math.BlockPos;

public class PacketDirectorEdit extends PacketDirector
{
    public Replay replay;
    public int index;
    public boolean update;

    public PacketDirectorEdit()
    {}

    public PacketDirectorEdit(BlockPos pos, Replay replay, int index, boolean update)
    {
        super(pos);

        this.replay = replay;
        this.index = index;
        this.update = update;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        super.toBytes(buf);

        this.replay.toBuf(buf);
        buf.writeInt(this.index);
        buf.writeBoolean(this.update);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        super.fromBytes(buf);

        Replay replay = new Replay();

        replay.fromBuf(buf);

        this.replay = replay;
        this.index = buf.readInt();
        this.update = buf.readBoolean();
    }
}
