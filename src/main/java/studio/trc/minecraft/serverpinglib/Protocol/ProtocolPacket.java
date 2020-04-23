package studio.trc.minecraft.serverpinglib.Protocol;

public class ProtocolPacket
{
    private static final byte T;
    private static final byte R;
    private static final byte C;
    private static final byte S;
    private static final byte t;
    private static final byte u;
    private static final byte d;
    private static final byte i;
    private static final byte o;
    private static final byte[] v1_7_2_to_v1_7_5;
    private static final byte[] v1_7_6_to_v1_7_10;
    private static final byte[] v1_8_X;
    private static final byte[] v1_9;
    private static final byte[] v1_9_1;
    private static final byte[] v1_9_2;
    private static final byte[] v1_9_3;
    private static final byte[] v1_9_4;
    private static final byte[] v1_10;
    private static final byte[] v1_10_1;
    private static final byte[] v1_10_2;
    private static final byte[] v1_11;
    private static final byte[] v1_11_1;
    private static final byte[] v1_11_2;
    private static final byte[] v1_12;
    private static final byte[] v1_12_1;
    private static final byte[] v1_12_2;
    private static final byte[] v1_13;
    private static final byte[] v1_13_1;
    private static final byte[] v1_13_2;
    private static final byte[] v1_14;
    private static final byte[] v1_14_1;
    private static final byte[] v1_14_2;
    private static final byte[] v1_14_3;
    private static final byte[] v1_14_4;
    private static final byte[] v1_15;
    private static final byte[] v1_15_1;
    private static final byte[] latest;
    
    public static byte[] getVersionProtocol(final ProtocolVersion version) {
        if (version == null) {
            return ProtocolPacket.latest;
        }
        switch (version) {
            case v1_7_2_to_v1_7_5: {
                return ProtocolPacket.v1_7_2_to_v1_7_5;
            }
            case v1_7_6_to_v1_7_10: {
                return ProtocolPacket.v1_7_6_to_v1_7_10;
            }
            case v1_8_X: {
                return ProtocolPacket.v1_8_X;
            }
            case v1_9: {
                return ProtocolPacket.v1_9;
            }
            case v1_9_1: {
                return ProtocolPacket.v1_9_1;
            }
            case v1_9_2: {
                return ProtocolPacket.v1_9_2;
            }
            case v1_9_3: {
                return ProtocolPacket.v1_9_3;
            }
            case v1_9_4: {
                return ProtocolPacket.v1_9_4;
            }
            case v1_10: {
                return ProtocolPacket.v1_10;
            }
            case v1_10_1: {
                return ProtocolPacket.v1_10_1;
            }
            case v1_10_2: {
                return ProtocolPacket.v1_10_2;
            }
            case v1_11: {
                return ProtocolPacket.v1_11;
            }
            case v1_11_1: {
                return ProtocolPacket.v1_11_1;
            }
            case v1_11_2: {
                return ProtocolPacket.v1_11_2;
            }
            case v1_12: {
                return ProtocolPacket.v1_12;
            }
            case v1_12_1: {
                return ProtocolPacket.v1_12_1;
            }
            case v1_12_2: {
                return ProtocolPacket.v1_12_2;
            }
            case v1_13: {
                return ProtocolPacket.v1_13;
            }
            case v1_13_1: {
                return ProtocolPacket.v1_13_1;
            }
            case v1_13_2: {
                return ProtocolPacket.v1_13_2;
            }
            case v1_14: {
                return ProtocolPacket.v1_14;
            }
            case v1_14_1: {
                return ProtocolPacket.v1_14_1;
            }
            case v1_14_2: {
                return ProtocolPacket.v1_14_2;
            }
            case v1_14_3: {
                return ProtocolPacket.v1_14_3;
            }
            case v1_14_4: {
                return ProtocolPacket.v1_14_4;
            }
            case v1_15: {
                return ProtocolPacket.v1_15;
            }
            case v1_15_1: {
                return ProtocolPacket.v1_15_1;
            }
            default: {
                return ProtocolPacket.latest;
            }
        }
    }
    
    public static byte[] getVersionProtocol(final ProtocolNumber pn) {
        if (pn == null) {
            return null;
        }
        switch (pn.getProtocolNumber()) {
            case v1_7_2_to_v1_7_5: {
                return ProtocolPacket.v1_7_2_to_v1_7_5;
            }
            case v1_7_6_to_v1_7_10: {
                return ProtocolPacket.v1_7_6_to_v1_7_10;
            }
            case v1_8_X: {
                return ProtocolPacket.v1_8_X;
            }
            case v1_9: {
                return ProtocolPacket.v1_9;
            }
            case v1_9_1: {
                return ProtocolPacket.v1_9_1;
            }
            case v1_9_2: {
                return ProtocolPacket.v1_9_2;
            }
            case v1_9_3: {
                return ProtocolPacket.v1_9_3;
            }
            case v1_9_4: {
                return ProtocolPacket.v1_9_4;
            }
            case v1_10: {
                return ProtocolPacket.v1_10;
            }
            case v1_10_1: {
                return ProtocolPacket.v1_10_1;
            }
            case v1_10_2: {
                return ProtocolPacket.v1_10_2;
            }
            case v1_11: {
                return ProtocolPacket.v1_11;
            }
            case v1_11_1: {
                return ProtocolPacket.v1_11_1;
            }
            case v1_11_2: {
                return ProtocolPacket.v1_11_2;
            }
            case v1_12: {
                return ProtocolPacket.v1_12;
            }
            case v1_12_1: {
                return ProtocolPacket.v1_12_1;
            }
            case v1_12_2: {
                return ProtocolPacket.v1_12_2;
            }
            case v1_13: {
                return ProtocolPacket.v1_13;
            }
            case v1_13_1: {
                return ProtocolPacket.v1_13_1;
            }
            case v1_13_2: {
                return ProtocolPacket.v1_13_2;
            }
            case v1_14: {
                return ProtocolPacket.v1_14;
            }
            case v1_14_1: {
                return ProtocolPacket.v1_14_1;
            }
            case v1_14_2: {
                return ProtocolPacket.v1_14_2;
            }
            case v1_14_3: {
                return ProtocolPacket.v1_14_3;
            }
            case v1_14_4: {
                return ProtocolPacket.v1_14_4;
            }
            case v1_15: {
                return ProtocolPacket.v1_15;
            }
            case v1_15_1: {
                return ProtocolPacket.v1_15_1;
            }
            default: {
                return ProtocolPacket.latest;
            }
        }
    }
    
    private static int getT() {
        return 44;
    }
    
    private static int getu() {
        return 64;
    }
    
    private static int getC() {
        return 0;
    }
    
    private static int getR() {
        return 384;
    }
    
    private static int getS() {
        return 64;
    }
    
    private static int geti() {
        return -8960;
    }
    
    private static int gett() {
        return 96;
    }
    
    private static int geto() {
        return 5120;
    }
    
    private static int getd() {
        return 12672;
    }
    
    static {
        T = (byte)(getT() >> 2);
        R = (byte)(getR() >> 3);
        C = (byte)getC();
        S = (byte)(getS() >> 4);
        t = (byte)(gett() >> 5);
        u = (byte)(getu() >> 6);
        d = (byte)(getd() >> 7);
        i = (byte)(geti() >> 8);
        o = (byte)(geto() >> 9);
        v1_7_2_to_v1_7_5 = new byte[] { ProtocolPacket.o, ProtocolPacket.C, ProtocolPacket.S, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_7_6_to_v1_7_10 = new byte[] { ProtocolPacket.o, ProtocolPacket.C, 5, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_8_X = new byte[] { ProtocolPacket.o, ProtocolPacket.C, 47, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_9 = new byte[] { ProtocolPacket.o, ProtocolPacket.C, 107, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_9_1 = new byte[] { ProtocolPacket.o, ProtocolPacket.C, 108, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_9_2 = new byte[] { ProtocolPacket.o, ProtocolPacket.C, 109, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_9_3 = new byte[] { ProtocolPacket.o, ProtocolPacket.C, 110, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_9_4 = new byte[] { ProtocolPacket.o, ProtocolPacket.C, 110, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_10 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -46, ProtocolPacket.u, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_10_1 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -46, ProtocolPacket.u, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_10_2 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -46, ProtocolPacket.u, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_11 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -69, 2, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_11_1 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -68, 2, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_11_2 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -68, 2, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_12 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -49, 2, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_12_1 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -46, 2, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_12_2 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -44, 2, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_13 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -119, ProtocolPacket.t, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_13_1 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -111, ProtocolPacket.t, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_13_2 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -108, ProtocolPacket.t, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_14 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, ProtocolPacket.i, ProtocolPacket.t, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_14_1 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -32, ProtocolPacket.t, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_14_2 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -27, ProtocolPacket.t, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_14_3 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -22, ProtocolPacket.t, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_14_4 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -14, ProtocolPacket.t, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_15 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -67, ProtocolPacket.S, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        v1_15_1 = new byte[] { ProtocolPacket.T, ProtocolPacket.C, -65, ProtocolPacket.S, ProtocolPacket.S, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.R, ProtocolPacket.d, ProtocolPacket.i, ProtocolPacket.u, ProtocolPacket.u, ProtocolPacket.C };
        latest = ProtocolPacket.v1_15_1;
    }
}
