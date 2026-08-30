package pojlib.util.download;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class StreamDL extends InputStream {
    private final InputStream in;
    private int count;
    private final Collection<StreamListener> listeners = new ArrayList<>();

    public StreamDL(InputStream in, long totalBytes) {
        this.in = in;
        DownloadManager.addTotalBytes(totalBytes);
    }

    @Override
    public int read() throws IOException {
        int b = in.read();
        byteReceived(b);
        return b;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int n = in.read(b, off, len);
        bytesReceived(n);
        return n;
    }

    public void addListener(StreamListener listener) {
        listeners.add(listener);
    }

    private void byteReceived(int b) {
        if (b != -1) {
            count++;
            DownloadManager.addBytes(1);
        }

        for (StreamListener l : listeners) {
            l.byteReceived(b, count);
        }
    }

    // Bulk-read counterpart of byteReceived(); avoids falling back to the JDK's default
    // read(byte[],int,int), which just loops read() one byte at a time and made every
    // download (LWJGL, client jar, libraries, the JRE) far slower than the network allowed.
    private void bytesReceived(int n) {
        if (n > 0) {
            count += n;
            DownloadManager.addBytes(n);
        }

        for (StreamListener l : listeners) {
            l.byteReceived(n > 0 ? 1 : -1, count);
        }
    }
}

