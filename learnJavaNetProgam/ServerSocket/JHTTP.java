package learnJavaNetProgam.ServerSocket;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JHTTP {

	private static final Logger logger = Logger.getLogger(JHTTP.class.getCanonicalName());

	private static final int NUM_THREADS = 50;
	private static final String INDEX_FILE = "index.html";

	private final File rootDirectory;
	private final int port;

	public JHTTP(File rootDirectory, int port) throws IOException {

		if (!rootDirectory.isDirectory()) {
			throw new IOException(rootDirectory + "does not exist as a directory");
		}
		this.rootDirectory = rootDirectory;
		this.port = port;
	}

	public void start() throws IOException {

		ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
		try (ServerSocket server = new ServerSocket(port)) {
			logger.info("Accepting connections on port " + server.getLocalPort());
			logger.info("document root: " + rootDirectory);

			while (true) {
				try {
					Socket request = server.accept();
					Runnable r = new RequestProcessor(rootDirectory, INDEX_FILE, request);
					pool.submit(r);
				} catch (IOException e) {
					logger.log(Level.WARNING, "error accepting connection", e);
				}
			}
		}
	}

	public static void main(String[] args){
		File file = new File("/Users/chenhaoyu/Downloads");
		int port = 1333;
		
		try {
			JHTTP webserver = new JHTTP(file, port);
			webserver.start();
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Server could not start",e);
		}
		
		
	}
	
}
