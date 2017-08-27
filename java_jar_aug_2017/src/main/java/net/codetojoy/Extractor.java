
package net.codetojoy;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class Extractor {
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

            /*
            // BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            final int blockSize = 8 * 1024;
            byte[] buf = new byte[blockSize];
            int len;

            while((len = in.read(buf)) != -1) {
                out.write(buf,0,len);
            }

            safeClose(out);
            safeClose(in);

            out = null;
            in = null;
            */
        } catch(Exception ex) {
            System.err.println("Internal Error! ex: " + ex.getMessage());
        }
    }

    public static void fastChannelCopy(final ReadableByteChannel src, 
                        final WritableByteChannel dest) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);

        while (src.read(buffer) != -1) {
            // prepare the buffer to be drained
            buffer.flip();
            // write to the channel, may block
            dest.write(buffer);
            // If partial transfer, shift remainder down
            // If buffer is empty, same as doing clear()
            buffer.compact();
        }

        // EOF will leave buffer in fill state
        buffer.flip();

        // make sure the buffer is fully drained.
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
