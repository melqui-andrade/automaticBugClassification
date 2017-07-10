package view;

import java.io.File;
import java.util.jar.JarFile;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class FileChooser extends JPanel {
	
	private String title;

	/**
	 * Create the panel.
	 */
	public FileChooser() {

	}
	
	public FileChooser(String title) {
		this.title = title;
	}
	
	
	public File getFile() {
		
		final JFileChooser fc = new JFileChooser();
		if(title != null) {
			fc.setDialogTitle(title);
		}
		int returnVal = fc.showOpenDialog(this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			return file;
		}
		else {
			return null;
		}
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

}
