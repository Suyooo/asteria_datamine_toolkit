package be.suyo.toastoolkit.ccbi2ccb.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;

public class Biterator implements Iterator<Boolean> {
    // reads values from the data stream bit by bit instead of byte by byte
    // unneeded bits are discarded (other methods will continue from the next even byte)

    private byte currentByte = 0, bitsLeft = 0;
    private DataInputStream file;
    
    public Biterator(DataInputStream file) {
        this.file = file;
    }
    
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Boolean next() {
        if (bitsLeft == 0) {
            try {
                currentByte = file.readByte();
            } catch (IOException e) {
                throw new CcbiException("Unexpected EOF", e);
            }
            bitsLeft = 8;
        }
        boolean bit = (currentByte & 1) != 0;
        currentByte >>>= 1;
        bitsLeft--;
        return bit;
    }
}