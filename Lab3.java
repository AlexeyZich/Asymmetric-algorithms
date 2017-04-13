import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.math.BigInteger;
import java.lang.Math;
import java.lang.Object;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.*;

public class Lab3 extends JFrame {
    String str = "";
		File file;
		BigInteger kNum;
    

    public Lab3() {
          super("Лаба 3");
          createGUI();
     }

    public void createGUI(){
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JPanel panel = new JPanel();
    	panel.setLayout(new FlowLayout());

        JButton coded = new JButton("Открыть файл");
        coded.setActionCommand(str);
        panel.add(coded);

        JButton decoded = new JButton("Дешифровать");
        decoded.setActionCommand(str);
        panel.add(decoded);

        ActionListener actionListener = new TestActionListener();
        ActionListener actionListener1 = new TActionListener();
        coded.addActionListener(actionListener);
		decoded.addActionListener(actionListener1); 

        getContentPane().add(panel);
        setResizable(false);
        setPreferredSize(new Dimension(200, 200));
    }

    public class TestActionListener implements ActionListener {
          public void actionPerformed(ActionEvent e) {
          	JFileChooser fileopen = new JFileChooser();
			int ret = fileopen.showDialog(null, "Открыть файл");                
			if (ret == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileopen.getSelectedFile();
			    file = selectedFile;
			    encode(file.getAbsolutePath());
			}
      	}
     }

     public class TActionListener implements ActionListener {
          public void actionPerformed(ActionEvent e) {
          	JFileChooser fileopen = new JFileChooser();
			int ret = fileopen.showDialog(null, "Открыть файл");                
			if (ret == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileopen.getSelectedFile();
			    file = selectedFile;
			    decode(file.getAbsolutePath());
			}
      	}
     }

     public void encode(String path) {
     	try {
     		FileWriter writer = new FileWriter(path + ".tmp", false);
     		
		    FileReader filereader = new FileReader(new File(path));

		    BufferedReader br = new BufferedReader(filereader);
		    
			String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					String encodedLine = "";
					for (char ch: line.toCharArray()) {
						int incr = (int)ch + kNum.intValue();
						char newChar = (char)incr;
						encodedLine += newChar;
					}
					System.out.println(encodedLine);
					writer.write(encodedLine);
					writer.append("\n");
					writer.flush();
				}
		}
		catch (IOException ex)  {
			System.out.println(ex.getMessage());
    	}

    	try {
	    	Files.move(Paths.get(path + ".tmp"), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
    	}
    	catch (IOException ex)  {
			System.out.println(ex.getMessage());
    	}
	}

	public void decode(String path) {
     	try {
     		FileWriter writer = new FileWriter(path + ".tmp", false);
     		
		    FileReader filereader = new FileReader(new File(path));

		    BufferedReader br = new BufferedReader(filereader);
		    
			String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					String encodedLine = "";
					for (char ch: line.toCharArray()) {
						int incr = (int)ch - kNum.intValue();
						char newChar = (char)incr;
						encodedLine += newChar;
					}
					System.out.println(encodedLine);
					writer.write(encodedLine);
					writer.append("\n");
					writer.flush();
				}
		}
		catch (IOException ex)  {
			System.out.println(ex.getMessage());
    	}

    	try {
	    	Files.move(Paths.get(path + ".tmp"), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
    	}
    	catch (IOException ex)  {
			System.out.println(ex.getMessage());
    	}
	}

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			KCalculation calc = new KCalculation(),
			kNum = calc.call();
			String text = "" + kNum;
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                Lab3 frame = new Lab3();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
	}
}
