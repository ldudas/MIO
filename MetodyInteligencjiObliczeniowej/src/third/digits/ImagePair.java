package third.digits;

import java.io.File;

class ImagePair {
	private final File file;
	private final int identity;

	public ImagePair(final File file, final int identity) {
		super();
		this.file = file;
		this.identity = identity;
	}

	public File getFile() {
		return this.file;
	}

	public int getIdentity() {
		return this.identity;
	}
}