import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.print.attribute.standard.JobHoldUntil;
import javax.print.attribute.standard.PDLOverrideSupported;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.Position;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.Option;
import javax.swing.colorchooser.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.tools.JavaFileManager;

import java.io.File;
import java.awt.Rectangle;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.JWindow;
import javax.swing.AbstractAction;
import javax.swing.ListSelectionModel;

import java.awt.TextArea;
import java.text.*;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.Color.*;
import java.awt.Font.*;
import java.awt.Frame;
import java.awt.image.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.List;
import java.awt.PopupMenu;
import java.awt.Shape;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfTextArray;
import com.itextpdf.text.pdf.parser.ContentByteUtils;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;  
import org.apache.pdfbox.util.PDFTextStripper; 
/**
 * Project Hail Storm.
 * 
 * @author rajiv & rohith
 *
 */

public class Window {
	private static JFrame mainFrame;
	private static JPanel mainPanel;
	private static Container mainPane;
	private static JMenuBar menuBar;
	private static JMenu File;
	private static JTextField Search;
	private static JToggleButton ts;
	private static JMenu Page;
	private static JMenu Styles;
	private static JTextPane tarea;
	private static JScrollPane js;
	private static int pages;
	private static String text;
	private static String font;
	private static int appearance;
	private static int size;
	private static int currentPage;
	private static JButton Go;
	private static JTextField go;
	private static JLabel noOfPages;
	private static Highlighter h;
	private static HighlightPainter painter;
	private static String libraryPath;
	private static JList<String> popdown ;
	private static JButton openBook;
	private static String home = "/home/rajiv/Desktop/Calibre" ;
	private static String dest;
	private static File quotes;
	private static File bookmark;
	private static Border border;
	private static String bookmarkStr = "";
	private static JButton bookmarkToggle;
	private static int pageNo = 1;
	private static JToggleButton Highlights;
	
/*------------------------START THE GUI ----------------------------------*/	
	public static void setupHome() {
		
		
		// main container.
		mainFrame = new JFrame();
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Border b = BorderFactory.createEtchedBorder(Color.blue, Color.white);
		
		// Container of the components.
		mainPane = new Container();
		mainPane = mainFrame.getContentPane();
		
		// Using a menuBar.
		menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(0, 20, 20));
		
		// List of all the components in the main Frame.
		File = new JMenu("File");
		Highlights = new JToggleButton("Highlights");
		Styles = new JMenu("Styles");
		Page = new JMenu("Page");
		final JTextField SearchWord = new JTextField("Search word", 15);
		final JTextField go = new JTextField(5);
		noOfPages = new JLabel();
		openBook = new JButton("Book");
		tarea = new JTextPane();
		js = new JScrollPane(tarea, js.VERTICAL_SCROLLBAR_ALWAYS,js.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		final JToggleButton nightMode = new JToggleButton("Night Mode");
		
		// Add Menu Bar contents.
		menuBar.add(File);
		menuBar.add(go);
		menuBar.add(noOfPages);
		
		// Goto attributes.
		go.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		go.setBorder(b);
		bookmarkToggle = new JButton("     ");
		bookmarkToggle.setBackground(Color.yellow);
		SearchWord.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		SearchWord.setBorder(b);
		
		String[] Fonts = { "Times New Roman", "Serif", "Sans", "Arial",
				"Bitstream Charter" };
		String[] Sizes = { "8", "10", "12", "14", "16", "18", "22", "24", "28",
				"30", "32", "34", "38", "40", "42" };
		String[] Appearances = { "Plain", "Bold", "Italic" };

		// Fonts ,Appearances and Sizes in a Combo Box.
		final JComboBox<String> f = new JComboBox<>(Fonts);
		final JComboBox<String> a = new JComboBox<>(Appearances);
		final JComboBox<String> s = new JComboBox<>(Sizes);
		
		f.setBorder(b);
		a.setBorder(b);
		s.setBorder(b);

		menuBar.add(f);
		menuBar.add(a);
		menuBar.add(s);
		menuBar.add(SearchWord);
		menuBar.add(openBook);
		menuBar.add(nightMode);
		menuBar.add(bookmarkToggle);
		menuBar.add(Highlights);
		menuBar.setVisible(true);
		

		JMenuItem Open = new JMenuItem("Open");
		JMenuItem Quit = new JMenuItem("Quit");
		final JMenuItem library = new JMenuItem("LibraryPath");
		
		
		File.add(Open);
		File.add(Quit);
		File.add(library);
		
		mainPane.add(menuBar, BorderLayout.PAGE_START);
		mainPane.add(js, BorderLayout.CENTER);

		tarea.setEditable(false);
		js.setVisible(true);
		
		font = "Times New Roman";
		appearance = 0;
		size = 30;

		bookmarkToggle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String temp ;
				if(bookmarkStr.charAt(pageNo-1) == '0')
				{
					temp = bookmarkStr.substring(0,pageNo - 1)+"1"+bookmarkStr.substring(pageNo);
					bookmarkToggle.setBackground(Color.GRAY);
				}
				else
				{
					temp = bookmarkStr.substring(0,pageNo - 1)+"0"+bookmarkStr.substring(pageNo);
					bookmarkToggle.setBackground(Color.YELLOW);
				}
				bookmarkStr = temp;
				FileWriter fw = null;
				try 
				{
					fw = new FileWriter(dest+"/BookMark.txt");
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedWriter bw = new BufferedWriter(fw);
				try 
				{
					bw.write(bookmarkStr);
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try 
				{
					bw.close();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
	// Converting to a required Font-Style.
		f.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				font = (String) f.getSelectedItem();
				displayBookInTextArea(text);
			}
		});
	
	// Adding the appearance of the text in tarea.	
		a.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String str = (String) a.getSelectedItem();
				if (str.equals("Plain"))
					appearance = 0;
				if (str.equals("Bold"))
					appearance = 1;
				if (str.equals("Italic"))
					appearance = 2;
				displayBookInTextArea(text);
			}
		});
	
	// s is the Size of the letters.	
		s.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = (String) s.getSelectedItem();
				size = Integer.parseInt(str);
				displayBookInTextArea(text);

			}
		});
	
	/* GO TO A CERTAIN PAGE : USES THE POSITION AND VALUES OF THE SCROLL BAR. */
		go.addCaretListener(new CaretListener() {

			private int maxValue;

			@Override
			public void caretUpdate(CaretEvent arg0) {
				String page = go.getText();
				System.out.println(page);
				if (page.length()!=0){
					
					int pageNo = Integer.parseInt(page);
					pageNo = pageNo -1;
					
					maxValue = js.getVerticalScrollBar().getMaximum();
					int value = pageNo * maxValue / pages;
	
					JScrollBar vb = js.getVerticalScrollBar();
					vb.setValue(value);
				}
			}
		});

	/* TO OPEN A BOOK(IN PDF FORMAT) DIRECTLY FROM THE HARD DISK
	 * USES "JFileChooser" METHOD */ 
		Open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Browse the folder to process");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					// s is the absolute path of the file.
					String s = chooser.getSelectedFile().toString();
					
					try {
						// Creates a folder for the book. Ignore if already present.
						makeBook(s);
						// OPEN THE FILE.
						openFile (s);						
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					System.out.println("No Selection ");
				}
			}
		});

	/* MOUSE EVENT LISTNER FOR THE TEXT-PANE.
	 * 	GET THE SELECTED TEXT FROM THE TEXT-PANE.
	 */
		tarea.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				if (tarea.getSelectedText() != null) {
					String str = tarea.getSelectedText();
					str = str.toLowerCase();
					
					String[] buffer = str.split(" ");

					/* IF SELECTION IS A WORD OPEN THE MEANING DIALOG */
					if (buffer.length == 1)
						try {
							openPopUpWord(str);
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					/* IF IT IS A SENTENCE OPEN THE SENTENCE OPEN THE ADD TO QUOTES DIALOG */
					if (buffer.length > 1){
						String lowerCase  =  text.toLowerCase();
						int offset = lowerCase.indexOf(str);
						int len = str.length();
						try {
							openPopUpSentence(offset,len);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				/*
				 * Incase there are any dialog boxes open implement this to
				 * close them
				 */

			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
			}

		});
		
		/* SEARCH THE TEXT FOR A PATTERN AS SOON AS THE CARET IS UPDATED */
		SearchWord.addCaretListener(new CaretListener() {
			Highlighter h = tarea.getHighlighter();
			HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(
					Color.yellow);

			@Override
			public void caretUpdate(CaretEvent e) throws NullPointerException {

				Highlighter.Highlight[] hilites = h.getHighlights();
				h.removeAllHighlights();
				String search = SearchWord.getText();
				int offset = 0;
				int len = search.length();
				try {
					String lowText = new String();
					lowText = text.toLowerCase();
					search = search.toLowerCase();
					int i = 0;
					while (i < 100000) {
						offset = lowText.indexOf(search, offset + len);
						h.addHighlight(offset, offset + len, painter);
						i++;
					}
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		SearchWord.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				SearchWord.setText("Search Word");
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				SearchWord.setText("");

			}
		});
		
		/* OPEN A NEW WINDOW TO SEARCH FOR VARIOUS BOOKS IN THE HARD DISK */
		openBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SearchBox.GUI();
				
			}
		});
		
		
		nightMode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (nightMode.isSelected()){
					tarea.setBackground(Color.black);
					tarea.setForeground(Color.white);
					border = new LineBorder(Color.black,120);
				}
				else{
					tarea.setBackground(Color.white);
					tarea.setForeground(Color.black);
					border = new LineBorder(Color.white,120);
				}
			}
		});
		
		
		customAdjustmentListener adjust = new customAdjustmentListener();
		js.getVerticalScrollBar().addAdjustmentListener(adjust);
		js.getHorizontalScrollBar().addAdjustmentListener(adjust);
		js.setAutoscrolls(true);
		
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/* GIVEN THE ABSOLUTE PATH OF AN OPEN FILE OPEN THE FILE */
	public static void openFile ( String newFilePath ) throws IOException {
		
		String[] g = newFilePath.split("[/]|.pdf");
		
		PdfReader te = new PdfReader(newFilePath);
		pages = te.getNumberOfPages();
		int i = 1;
		text = new String();
		while (i <= pages) {
			text = text.concat((PdfTextExtractor
					.getTextFromPage(te, i)));
			text = text.concat("\n");
			i++;
		}
		System.out.println(dest);
		
		FileReader fw2 = new FileReader(dest+"/BookMark.txt");
		BufferedReader bw2 = new BufferedReader(fw2);
		String str;
		while ((str = bw2.readLine()) != null) {
			bookmarkStr = bookmarkStr+str;
		}
		System.out.println(bookmarkStr.length());

		bw2.close();
		if(bookmarkStr.length() == 0)
		{
			
			FileWriter fw3 = new FileWriter(dest+"/BookMark.txt");
			BufferedWriter bw3 = new BufferedWriter(fw3);
			String str2 = "";
			System.out.println(pages);
			for(int k=0;k<pages;k++)
			{
				str2 = str2 + "0";
			}
			bw3.write(str2);
			bw3.close();
			bookmarkStr = str2;
		}
		
		
		Highlights.addActionListener(new ActionListener() {
			
			Highlighter h = tarea.getHighlighter();
			HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Highlights.isSelected()){
					try {
						
						System.out.println(dest+"/Quotes.txt");
						FileReader qr = new FileReader(dest+"/Quotes.txt");
						BufferedReader bqr = new BufferedReader(qr);
						String line = new String();
						int offset = 0 ;
						int len = 0 ;
						while ((line = bqr.readLine()).length() != 0){
							
							String[] buffer = line.split(" ");
							offset = Integer.parseInt(buffer[0]);
							len    = Integer.parseInt(buffer[1]);
							h.addHighlight(offset, offset+len, painter);
							
						}
						qr.close();
						bqr.close();
						
					} 
					catch (IOException | BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
				}
				else{
					h.removeAllHighlights();
				}
				
			}
		});
		
		// Display the book in text area.
		displayBookInTextArea(text);
		return ;
	}
	
	
	
	
	/* THIS FUNCTION CREATES THE LIBRARY TO KEEP THE 
	 * FOLDERS OF ALL THE BOOKS.
	 */
	public static void makeLibrary() {
		
		File lib = new File(home);
		if (!lib.exists()){
			lib.mkdir();
		}
	}
	
	/* THIS MAKES THE FOLDER INSIDE THE LIBRARY AND CREATES A 
	 * FOLDER TO KEEP THE DATABASE CORRESPONDING TO THE BOOK.
	 */
	
	public static void makeBook(String s) throws IOException {
		
		// Splits the file's absolute path to get a Folder Name.
		String[] tokens = s.split("\\.|/");
		// Splits the file's absolute path to get the File Name.
		String[] file   = s.split("/");
		
		String fileName = file[file.length-1];
		String bookName = tokens[tokens.length-2];
		
		File book = new File(home+"/"+bookName);
		// Creates a folder for each book.
		if (!book.exists()){
			book.mkdir();
		}
	    dest = new String();
		dest = dest.concat(home);
		dest = dest.concat("/");
		dest = dest.concat(bookName);
		
		File f1 = new File(dest+"/Quotes.txt");
		if(f1.exists() && !f1.isDirectory()) 
		{
		}
		else
		{
			FileWriter fw = new FileWriter(dest+"/Quotes.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.close();
		}
		
		File f2 = new File(dest+"/BookMark.txt");
		if(f2.exists() && !f2.isDirectory()) 
		{
			
		}
		else
		{
			FileWriter fw1 = new FileWriter(dest+"/BookMark.txt");
			BufferedWriter bw1 = new BufferedWriter(fw1);
			bw1.close();
		}

		File f=new File(s);
        OutputStream oos = new FileOutputStream(dest+"/"+fileName);
        byte[] buf = new byte[100000000];
        InputStream is = new FileInputStream(f);
        int c = 0;
        while ((c = is.read(buf, 0, buf.length)) > 0) {
            oos.write(buf, 0, c);
            oos.flush();
        }
        oos.close();
        System.out.println("stop");
        is.close();
		return;
	}
	
	public static void copyFile(String source,String dest) throws IOException{
		
		FileReader fr = new FileReader(source);
		BufferedReader br = new BufferedReader(fr);
		FileWriter fw = new FileWriter(dest);
		BufferedWriter bw = new BufferedWriter(fw);
		String line = new String();
		line = br.readLine();
		while (line != null){
			bw.write(line);
			line = br.readLine();
		}
		br.close();
		bw.close();
	}
	
	/* GENERATES THE HASH CODE FOR A GIVEN STRING.
	 * 
	 * uses : POLYNOMIAL HASH FUNCTION.   ORDER : 3 (PRIME NUBER TO AVOID COLLISIONS).
	 * Java Supports only until 2^32 - 1 unsigned Integer arithmetic.  
	 */
	public static int getHashCode(String s) {

		char[] buff = s.toCharArray();
		int len = s.length();
		int hash = 0;
		// For strings length less than 10.
		if (len <= 10) {
			for (int i = 0; i < len; i++) {
				hash += buff[i] * Math.pow(3, (double) (len - i - 1));
			}
		}
		// For strings length grater than 10.
		if (len > 10) {
			for (int i = 0; i < 10; i++) {
				hash += buff[i] * Math.pow(3, (double) (10 - i - 1));
			}
		}
		return hash;
	}
	
	/* DISPLAYS THE BOOK IN TEXT PANE */
	public static void displayBookInTextArea(String s) {

		// Give Font here.
		tarea.setText(s);
		int[] F = { Font.PLAIN, Font.BOLD, Font.ITALIC };
		tarea.setFont(new Font(font, F[appearance], size));
		border = new LineBorder(Color.white,120);
		tarea.setBorder(border);
		tarea.setBackground(Color.white);
		tarea.setForeground(Color.black);
		// Scroll to the beginning.
		tarea.setCaretPosition(0);
		
		noOfPages.setText("Pages: 0/0");
		
				
		
		
		
		
		
	}

	public static String[] getHashedDictionary(int maxHashCode) throws NumberFormatException, IOException {

		String[] dictionary = new String[maxHashCode + 1];
		FileReader fr1 = new FileReader("hashKeys.txt");
		FileReader fr2 = new FileReader("values.txt");
		BufferedReader br1 = new BufferedReader(fr1);
		BufferedReader br2 = new BufferedReader(fr2);
		String line1, line2;
		while ((line1 = br1.readLine()) != null
				&& (line2 = br2.readLine()) != null) {

			int index = Integer.parseInt(line1);
			String meaning = line2;
			// System.out.println(index + " "+meaning);
			dictionary[index] = meaning;

		}
		return dictionary;

	}

	@SuppressWarnings("static-access")
	public static void openPopUpWord(String word) throws NumberFormatException,
			IOException {

		Object[] iDo = { "Get Meaning", "Add To Vocabulary", "Just Like That" };

		String string = (String) JOptionPane.showInputDialog(tarea,
				"What do you want now?", "Word", 1, null, iDo, iDo[0]);

		if (string.equals("Get Meaning")) {
			searchForMeaning(word);
		}

		if (string.equals("Add To Vocabulary")) {
			// addToVocabulary(word);
		}

	}

	public static void openPopUpSentence(int offset,int len) throws IOException {

		int option = JOptionPane.showConfirmDialog(tarea,
				"Do you want to save this" + "\n" + "to quote database?");
		
			if (option == JOptionPane.YES_OPTION) 
				addToQuoteDatabase(offset,len);
		 

	}

	private static void addToQuoteDatabase(int offset,int len) throws IOException {
		
		FileWriter fw = new FileWriter(dest+"/Quotes.txt",true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append(Integer.toString(offset));
		bw.append(' ');
		bw.append((Integer.toString(len)));
		bw.append('\n');
		bw.close();		
		
		

	}

	public static void searchAndHighlight(String s, int offset)
			throws BadLocationException {

		return;
	}

	public static void searchForMeaning(String word) throws IOException {

		int maxHashCode = writeHash.writeHashCodes();
		int index = getHashCode(word);

		String[] dictionary = getHashedDictionary(maxHashCode);
		System.out.println(index + " " + dictionary[index] + " " + maxHashCode);

		if (dictionary[index] != null) {

			showMeaningDialog(dictionary[index]);

		} else
			showMeaningDialog("Sorry, Meaning not available");

	}

	public static void showMeaningDialog(String word) {

		JOptionPane.showMessageDialog(tarea, word, "Meaning", 1, null);

		return;

	}
	

	static class customAdjustmentListener implements AdjustmentListener {
		
		
		
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			Adjustable source = e.getAdjustable();

			if (e.getValueIsAdjusting())
				return;

			int orient = source.getOrientation();
			int type = e.getAdjustmentType();
			
			switch (type) {
			case AdjustmentEvent.UNIT_INCREMENT:
				// do something
				break;
			case AdjustmentEvent.BLOCK_INCREMENT:
				// do something
				break;
			case AdjustmentEvent.UNIT_DECREMENT:
				// do something
				break;
			case AdjustmentEvent.BLOCK_DECREMENT:
				// do something
				break;
			case AdjustmentEvent.TRACK:
				 setPageNumber(e.getValue());
				
				break;

			}
		
			

		}
		
		
		
	private void setPageNumber(int value) {
			int maxValue = js.getVerticalScrollBar().getMaximum();
			pageNo  = ((value+1)*pages)/maxValue +1 ;
			noOfPages.setText("Page: "+pageNo+"/"+pages);
	//		System.out.println("hai"+bookmarkStr.length());
			if(bookmarkStr.length() > 0)
			{
				if(bookmarkStr.charAt(pageNo-1) == '0')
				{
					bookmarkToggle.setBackground(Color.YELLOW);
				}
				else
				{
					bookmarkToggle.setBackground(Color.GRAY);
				}
			}
			return ;
		}
		

	}

	public static void main(String[] args) {

		makeLibrary();
	
		setupHome();
		
	}

}