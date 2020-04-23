package studio.trc.minecraft.serverpinglib.Protocol;

public class ProtocolNumber
{
    private final int number;
    private static final int v1_7_2_to_v1_7_5 = 4;
    private static final int v1_7_6_to_v1_7_10 = 5;
    private static final int v1_8_X = 47;
    private static final int v1_9 = 107;
    private static final int v1_9_1 = 108;
    private static final int v1_9_2 = 109;
    private static final int v1_9_3 = 110;
    private static final int v1_9_4 = 110;
    private static final int v1_10 = 210;
    private static final int v1_10_1 = 210;
    private static final int v1_10_2 = 210;
    private static final int v1_11 = 315;
    private static final int v1_11_1 = 316;
    private static final int v1_11_2 = 316;
    private static final int v1_12 = 335;
    private static final int v1_12_1 = 338;
    private static final int v1_12_2 = 340;
    private static final int v1_13 = 393;
    private static final int v1_13_1 = 401;
    private static final int v1_13_2 = 404;
    private static final int v1_14 = 477;
    private static final int v1_14_1 = 480;
    private static final int v1_14_2 = 485;
    private static final int v1_14_3 = 490;
    private static final int v1_14_4 = 498;
    private static final int v1_15 = 573;
    private static final int v1_15_1 = 575;
    
    public ProtocolNumber(final int number) {
        this.number = number;
    }
    
    public static ProtocolNumber getInstance(final int number) {
        return new ProtocolNumber(number);
    }
    
    public ProtocolVersion getProtocolNumber() {
        switch (this.number) {
            case 4: {
                return ProtocolVersion.v1_7_2_to_v1_7_5;
            }
            case 5: {
                return ProtocolVersion.v1_7_6_to_v1_7_10;
            }
            case 47: {
                return ProtocolVersion.v1_8_X;
            }
            case 107: {
                return ProtocolVersion.v1_9;
            }
            case 108: {
                return ProtocolVersion.v1_9_1;
            }
            case 109: {
                return ProtocolVersion.v1_9_2;
            }
            case 110: {
                return ProtocolVersion.v1_9_4;
            }
            case 210: {
                return ProtocolVersion.v1_10_2;
            }
            case 315: {
                return ProtocolVersion.v1_11;
            }
            case 316: {
                return ProtocolVersion.v1_11_2;
            }
            case 335: {
                return ProtocolVersion.v1_12;
            }
            case 338: {
                return ProtocolVersion.v1_12_1;
            }
            case 340: {
                return ProtocolVersion.v1_12_2;
            }
            case 393: {
                return ProtocolVersion.v1_13;
            }
            case 401: {
                return ProtocolVersion.v1_13_1;
            }
            case 404: {
                return ProtocolVersion.v1_13_2;
            }
            case 477: {
                return ProtocolVersion.v1_14;
            }
            case 480: {
                return ProtocolVersion.v1_14_1;
            }
            case 485: {
                return ProtocolVersion.v1_14_2;
            }
            case 490: {
                return ProtocolVersion.v1_14_3;
            }
            case 498: {
                return ProtocolVersion.v1_14_4;
            }
            case 573: {
                return ProtocolVersion.v1_15;
            }
            case 575: {
                return ProtocolVersion.v1_15_1;
            }
            default: {
                return ProtocolVersion.latest;
            }
        }
    }
    
    public static int getProtocolNumber(final ProtocolVersion version) {
        if (version == null) {
            return -1;
        }
        switch (version) {
            case v1_7_2_to_v1_7_5: {
                return 4;
            }
            case v1_7_6_to_v1_7_10: {
                return 5;
            }
            case v1_8_X: {
                return 47;
            }
            case v1_9: {
                return 107;
            }
            case v1_9_1: {
                return 108;
            }
            case v1_9_2: {
                return 109;
            }
            case v1_9_3: {
                return 110;
            }
            case v1_9_4: {
                return 110;
            }
            case v1_10: {
                return 210;
            }
            case v1_10_1: {
                return 210;
            }
            case v1_10_2: {
                return 210;
            }
            case v1_11: {
                return 315;
            }
            case v1_11_1: {
                return 316;
            }
            case v1_11_2: {
                return 316;
            }
            case v1_12: {
                return 335;
            }
            case v1_12_1: {
                return 338;
            }
            case v1_12_2: {
                return 340;
            }
            case v1_13: {
                return 393;
            }
            case v1_13_1: {
                return 401;
            }
            case v1_13_2: {
                return 404;
            }
            case v1_14: {
                return 477;
            }
            case v1_14_1: {
                return 480;
            }
            case v1_14_2: {
                return 485;
            }
            case v1_14_3: {
                return 490;
            }
            case v1_14_4: {
                return 498;
            }
            case v1_15: {
                return 573;
            }
            case v1_15_1: {
                return 575;
            }
            case latest: {
                return 575;
            }
            default: {
                return -1;
            }
        }
    }
}
