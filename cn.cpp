#include <iostream>
#include <vector>

namespace astd = std;

// Function to compute the one's complement checksum of the data
unsigned short computeChecksum(const astd::vector<unsigned char>& data) {
    unsigned int sum = 0;

    // Sum all bytes
    for (unsigned char byte : data) {
        sum += byte;
        // Wrap around if sum exceeds 16 bits
        if (sum > 0xFFFF) {
            sum = (sum & 0xFFFF) + 1;
        }
    }

    // Compute one's complement checksum
    return ~static_cast<unsigned short>(sum);
}

// Function to validate data and checksum
bool validateChecksum(const astd::vector<unsigned char>& data, unsigned short receivedChecksum) {
    unsigned int sum = 0;

    // Sum all data bytes
    for (unsigned char byte : data) {
        sum += byte;
        // Wrap around if sum exceeds 16 bits
        if (sum > 0xFFFF) {
            sum = (sum & 0xFFFF) + 1;
        }
    }

    // Add the received checksum to the sum
    sum += receivedChecksum;

    // Wrap around if sum exceeds 16 bits
    if (sum > 0xFFFF) {
        sum = (sum & 0xFFFF) + 1;
    }

    // Validate if all bits are zero
    return (sum == 0xFFFF);
}

int main() {
    // Example data
    astd::vector<unsigned char> data = {0x12, 0x34, 0x56, 0x78};

    // Compute checksum
    unsigned short checksum = computeChecksum(data);
    std::cout << "Computed Checksum: " << std::hex << checksum << std::endl;

    // Append checksum to data
    astd::vector<unsigned char> dataWithChecksum = data;
    dataWithChecksum.push_back(static_cast<unsigned char>(checksum & 0xFF));
    dataWithChecksum.push_back(static_cast<unsigned char>((checksum >> 8) & 0xFF));

    // Simulate received checksum from dataWithChecksum
    unsigned short receivedChecksum = static_cast<unsigned short>(dataWithChecksum[dataWithChecksum.size() - 1]);
    receivedChecksum |= static_cast<unsigned short>(dataWithChecksum[dataWithChecksum.size() - 2]) << 8;

    // Validate data and checksum
    if (validateChecksum(data, receivedChecksum)) {
        std::cout << "Data is valid." << std::endl;
    } else {
        std::cout << "Data is corrupted." << std::endl;
    }

    return 0;
}
