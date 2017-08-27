
package net.codetojoy;

import java.io.Closeable;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;

import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class Extractor {
    private static final int BLOCK_SIZE = 16 * 1024;

    public static void main(String[] args) {
        try {
            Extractor extractor = new Extractor();
            extractor.extractFile("/unpack.sh", "unpack.sh");
            extractor.extractFile("/payload.zip", "payload.zip");
            App.main(args);
        } catch(Exception ex) {
            System.err.println("Internal Error! ex: " + ex.getMessage());
        }
    }

    private void extractFile(String src, String dest) {
        try {
            final FileOutputStream output = new FileOutputStream(new File(dest));

            final InputStream input = getClass().getResourceAsStream(src);

            final ReadableByteChannel inputChannel = Channels.newChannel(input);
            final WritableByteChannel outputChannel = Channels.newChannel(output);

            fastChannelCopy(inputChannel, outputChannel);

            safeClose(inputChannel, outputChannel, input, output);

        } catch(Exception ex) {
            System.err.println("Internal Error! ex: " + ex.getMessage());
        }
    }

    public static void fastChannelCopy(final ReadableByteChannel src, 
                        final WritableByteChannel dest) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(BLOCK_SIZE);

        while (src.read(buffer) != -1) {
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
          dest.write(buffer);
        }
    }

    private void safeClose(Closeable ...args) {
        try {
            for (Closeable c : args) {
                if (c != null) {
                    c.close();
                }
            }
        } catch(Exception ex) {
            System.err.println("Internal Error! ex: " + ex.getMessage());
        }
    }
}
