import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SearchBox  extends Window{
	
	static JFrame frame;
	static JTextField getBook;
	static JScrollPane pan ;
	static JPanel panel ;
	static JMenuBar menuBar ;
	private static String[] listFiles;
	private static JList list1;
	private static ArrayList<String> returnList;
	private static Container mPane;
	private static String[] colors = {"black","red","green"};
	private static JComboBox<String> cb ;
	private static JButton enter ;
	private static String newFilePath;
	public static void GUI () {
		
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		pan = new JScrollPane(pan.VERTICAL_SCROLLBAR_ALWAYS,pan.HORIZONTAL_SCROLLBAR_ALWAYS);
		menuBar = new JMenuBar();
		getBook = new JTextField(10);
		mPane = new Container();
		mPane   = frame.getContentPane();
		cb = new JComboBox<String>();
		cb.setEditable(true);
		menuBar.setLayout(new GridLayout(1, 2));
		menuBar.add(cb);
		menuBar.add(getBook);
		mPane.add(menuBar,BorderLayout.PAGE_START);
		JButton enter = new JButton("Enter");
		menuBar.add(enter);
		panel = new JPanel();
		panel.setLayout(new GridLayout(10,1));
		frame.add(panel);
		menuBar.setVisible(true);
		
		
		getBook.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent arg0) {
				returnList = new ArrayList<>();
				File rootDir = new File("/home/rajiv");
				File[] roots = rootDir.listFiles();
				String regex = getBook.getText();
				System.out.println(regex);
				for (File f : roots) {
						showAllPdfsWithRegex (f.getAbsolutePath(),regex,returnList);
				}	
			}
		});
		
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = cb.getSelectedIndex();
				newFilePath = cb.getItemAt(index);
				try {
					makeBook (newFilePath);
					openFile (newFilePath);
					frame.dispose();
					
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		frame.pack();
		frame.setVisible(true);
		
		
		
		
		
		
		
		
	}
	
	public static void showAllPdfsWithRegex(String directoryName, String regex,ArrayList<String> returnList) {
		
		final String reg = regex ;
		File dir = new File(directoryName);
		if (dir.isDirectory()){
			FilenameFilter filter = new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					if (dir.getAbsolutePath().contains("/."))
						return false;
					if (dir.getAbsolutePath().contains(reg))
						return true;
					return true;
				}
			};
			File[] subFiles = dir.listFiles(filter);
			if (subFiles != null){
				for (File f : subFiles){
					showAllPdfsWithRegex (f.getAbsolutePath(),regex,returnList);
				}
			}
		}
		else if (dir.isFile()){
			regex = regex.toLowerCase();
			directoryName = directoryName.toLowerCase();
			String path = dir.getAbsolutePath().toLowerCase();
			if (path.endsWith(".pdf")){
				
				if (path.matches("(.*)"+regex+"(.*)")){
					returnList.add(dir.getAbsolutePath());
					listFiles = new String[returnList.size()];
					for (int i = 0 ; i < returnList.size(); i++){
						listFiles[i] = returnList.get(i);
						//System.out.println(listFiles[i]);
					}
					//showList(listFiles);
					JButton[] fields = new JButton[listFiles.length];
					cb.removeAllItems();
					for (int i = 0 ; i<listFiles.length ; i++){
						cb.addItem(listFiles[i]);
					}
				    
					
				}
			}
			
			return ;
		}

	}
	
	public static void showList(final String[] listFiles){
		
		JButton[] fields = new JButton[listFiles.length];
		
		for ( int i = 0 ; i < listFiles.length ; i++){
			fields[i] = new JButton(listFiles[i]);
			panel.add(fields[i]);
			
			fields[i].addActionListener(new ActionListener() {
				String s = listFiles[0];
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(s);
					
				}
			});
			
			
			
			
			
			
		}
		
		mPane.setVisible(true);
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	




	
	
}
