import java.util.Random;

public class checksum {
    public static void main(String[] args) {
        // Example with no error
        int[] validData = generateRandomData(4); // 4 data bits
        int[] encodedData = encodeHamming(validData);

        System.out.println("Valid data example:");
        System.out.println("Original data: " + arrayToString(validData));
        System.out.println("Encoded data:  " + arrayToString(encodedData));
        int errorPosition = detectError(encodedData);
        System.out.println("Error position: " + (errorPosition == 0 ? "No error" : errorPosition));

        // Example with an error
        System.out.println("\nData with error example:");
        int[] dataWithError = introduceSingleError(encodedData);
        System.out.println("Received data: " + arrayToString(dataWithError));
        errorPosition = detectError(dataWithError);
        System.out.println("Error position: " + (errorPosition == 0 ? "No error" : errorPosition));
    }

    public static int[] encodeHamming(int[] data) {
        int[] encoded = new int[7]; // 7 bits: 4 data + 3 parity

        // Set data bits
        encoded[2] = data[0];
        encoded[4] = data[1];
        encoded[5] = data[2];
        encoded[6] = data[3];

        // Calculate parity bits
        encoded[0] = encoded[2] ^ encoded[4] ^ encoded[6]; // P1
        encoded[1] = encoded[2] ^ encoded[5] ^ encoded[6]; // P2
        encoded[3] = encoded[4] ^ encoded[5] ^ encoded[6]; // P4

        return encoded;
    }

    public static int detectError(int[] receivedData) {
        int p1 = receivedData[0] ^ receivedData[2] ^ receivedData[4] ^ receivedData[6];
        int p2 = receivedData[1] ^ receivedData[2] ^ receivedData[5] ^ receivedData[6];
        int p4 = receivedData[3] ^ receivedData[4] ^ receivedData[5] ^ receivedData[6];

        int errorPosition = p1 + (p2 * 2) + (p4 * 4);
        return errorPosition;
    }

    public static int[] generateRandomData(int length) {
        Random random = new Random();
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = random.nextInt(2);
        }
        return data;
    }

    public static int[] introduceSingleError(int[] data) {
        Random random = new Random();
        int errorPosition = random.nextInt(data.length);
        data[errorPosition] ^= 1; // Flip the bit
        return data;
    }

    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int bit : arr) {
            sb.append(bit);
        }
        return sb.toString();
    }
}