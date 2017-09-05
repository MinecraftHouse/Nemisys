package org.itxtech.nemisys.raknet.protocol.packet;

import org.itxtech.nemisys.raknet.RakNet;
import org.itxtech.nemisys.raknet.protocol.Packet;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class OPEN_CONNECTION_REQUEST_1 extends Packet {
    public static byte ID = (byte) 0x05;
    public byte protocol = RakNet.PROTOCOL;
    public short mtuSize;

    @Override
    public byte getID() {
        return ID;
    }

    @Override
    public void encode() {
        super.encode();
        this.put(RakNet.MAGIC);
        this.putByte(this.protocol);
        this.put(new byte[this.mtuSize - 18]);
    }

    @Override
    public void decode() {
        super.decode();
        this.offset += 16; //skip magic bytes
        this.protocol = this.getByte();
        this.mtuSize = (short) (this.get().length + 18);
    }

    public static final class Factory implements Packet.PacketFactory {

        @Override
        public Packet create() {
            return new OPEN_CONNECTION_REQUEST_1();
        }

    }

}
