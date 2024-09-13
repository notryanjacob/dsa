public class crc {

    // Function to perform CRC calculation
    public static String calculateCRC(String data, String divisor) {
        int dataLength = data.length();
        int divisorLength = divisor.length();

        // Append zeros to the data based on the length of the divisor
        StringBuilder paddedData = new StringBuilder(data);
        for (int i = 0; i < divisorLength - 1; i++) {
            paddedData.append("0");
        }

        // Perform division using XOR operation
        String result = divide(paddedData.toString(), divisor);
        return result;
    }

    // Helper function to perform division using XOR
    private static String divide(String data, String divisor) {
        int pointer = divisor.length();
        String temp = data.substring(0, pointer);

        while (pointer < data.length()) {
            if (temp.charAt(0) == '1') {
                temp = xor(divisor, temp) + data.charAt(pointer);
            } else {
                temp = xor("0".repeat(divisor.length()), temp) + data.charAt(pointer);
            }
            pointer++;
            temp = temp.substring(1);
        }

        if (temp.charAt(0) == '1') {
            temp = xor(divisor, temp);
        } else {
            temp = xor("0".repeat(divisor.length()), temp);
        }

        return temp.substring(1); // CRC remainder
    }

    // XOR function for binary strings
    private static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    // Function to check if the received data is valid
    public static boolean checkCRC(String receivedData, String divisor) {
        // Remainder should be all zeros if no error
        String remainder = divide(receivedData, divisor);
        return !remainder.contains("1");
    }

    public static void main(String[] args) {
        System.out.println("\n Testcase 1 \n");
        String data = "11010011101100"; // Original data
        String divisor = "1011"; // CRC-3 Polynomial

        // Calculate CRC value
        String crc = calculateCRC(data, divisor);
        System.out.println("Calculated CRC: " + crc);

        // Append CRC to the original data
        String transmittedData = data + crc;
        System.out.println("Transmitted Data: " + transmittedData);

        // Check if the transmitted data is valid
        boolean isValid = checkCRC(transmittedData, divisor);
        System.out.println("Data is " + (isValid ? "valid" : "corrupted"));

        System.out.println("\n Testcase 2:\n");
        String data1 = "11010011101100"; // Original data
        String divisor1 = "1011"; // CRC-3 Polynomial

        // Calculate CRC value
        String crc1 = calculateCRC(data1, divisor1);
        System.out.println("Calculated CRC: " + crc);

        // Append CRC to the original data
        String transmittedData1 = data1 + crc1;
        System.out.println("Transmitted Data: " + transmittedData);

        // Check if the transmitted data is valid
        boolean isValid1 = checkCRC(transmittedData1, divisor1);
        System.out.println("Data is " + (isValid1 ? "valid" : "corrupted"));
    }
}
