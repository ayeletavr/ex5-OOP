package filters;

/**
 * This helper class converts a given number of bytes to kb.
 */
public class BytesConverter {

    final static double ONE_KB_IN_BYTES = 1024;

    public BytesConverter() {
    }

    public static double kbToBytes(double kb) {
        return kb * ONE_KB_IN_BYTES;
    }

}
