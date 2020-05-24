package com.nina.example.pinblock;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

public class PinblockTool {
    /**
     * Decode pinblock format 0 (ISO 9564)
     * @param pin pin
     * @param pan primary account number (PAN/CLN/CardNumber)
     * @return pinblock in HEX format
     */
    public static String format0Encode(String pin, String pan) {
        try {
            final String pinLenHead = StringUtils.leftPad(Integer.toString(pin.length()), 2, '0')+pin;
            final String pinData = StringUtils.rightPad(pinLenHead, 16,'F');
            final byte[] bPin = Hex.decodeHex(pinData.toCharArray());
            final String panPart = extractPanAccountNumberPart(pan);
            final String panData = StringUtils.leftPad(panPart, 16, '0');
            final byte[] bPan = Hex.decodeHex(panData.toCharArray());

            final byte[] pinblock = new byte[8];
            for (int i = 0; i < 8; i++)
                pinblock[i] = (byte) (bPin[i] ^ bPan[i]);

            return Hex.encodeHexString(pinblock).toUpperCase();
        } catch (DecoderException e) {
            throw new RuntimeException("Hex decoder failed!",e);
        }
    }

    /**
     * @param accountNumber PAN - primary account number
     * @return extract right-most 12 digits of the primary account number (PAN)
     */
    public static String extractPanAccountNumberPart(String accountNumber) {
        String accountNumberPart = null;
        if (accountNumber.length() > 12)
            accountNumberPart = accountNumber.substring(accountNumber.length() - 13, accountNumber.length() - 1);
        else
            accountNumberPart = accountNumber;
        return accountNumberPart;
    }

    /**
     * decode pinblock format 0 - ISO 9564
     * @param pinblock pinblock in format 0 - ISO 9564 in HEX format
     * @param pan primary account number (PAN/CLN/CardNumber)
     * @return clean PIN
     */
    public static String format0decode(String pinblock, String pan) {
        try {
            final String panPart = extractPanAccountNumberPart(pan);
            final String panData = StringUtils.leftPad(panPart, 16, '0');
            final byte[] bPan = Hex.decodeHex(panData.toCharArray());

            final byte[] bPinBlock = Hex.decodeHex(pinblock.toCharArray());

            final byte[] bPin  = new byte[8];
            for (int i = 0; i < 8; i++)
                bPin[i] = (byte) (bPinBlock[i] ^ bPan[i]);

            final String pinData = Hex.encodeHexString(bPin);
            final int pinLen = Integer.parseInt(pinData.substring(0, 2));
            return pinData.substring(2,2+pinLen);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid pinblock format!");
        } catch (DecoderException e) {
            throw new RuntimeException("Hex decoder failed!",e);
        }
    }
}
