package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javacc.MyGrammar;
import javacc.SimpleNode;
import javacc.Token;
import util.Constants;
import util.VariableNameGenerator;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class MyUI extends JFrame {

	private JPanel contentPane;
	private JButton cifa;
	private JButton yuyi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyUI frame = new MyUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200,900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setDividerSize(1);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JSplitPane left = new JSplitPane();
		left.setDividerSize(0);
		left.setResizeWeight(0.5);
		splitPane.setRightComponent(left);

		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setResizeWeight(0.02);
		splitPane_3.setDividerSize(2);
		splitPane_3.setEnabled(false);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		left.setLeftComponent(splitPane_3);

		JLabel in_text = new JLabel("  \u8F93\u5165");
		splitPane_3.setLeftComponent(in_text);

		JScrollPane scrollPane = new JScrollPane();
		splitPane_3.setRightComponent(scrollPane);

		JTextPane in = new JTextPane();
		scrollPane.setViewportView(in);

		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setResizeWeight(0.02);
		splitPane_4.setDividerSize(2);
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_4.setEnabled(false);
		left.setRightComponent(splitPane_4);

		JLabel out_text = new JLabel("  \u8F93\u51FA");
		splitPane_4.setLeftComponent(out_text);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_4.setRightComponent(scrollPane_1);

		JTextPane out = new JTextPane();
		out.setEditable(false);
		scrollPane_1.setViewportView(out);

		JPanel right = new JPanel();
		splitPane.setLeftComponent(right);
		right.setLayout(new GridLayout(5, 0, 0, 0));

		JButton select = new JButton("\u9009\u62E9\u6587\u4EF6");
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				int returnVal = chooser.showOpenDialog(select); 
				FileInputStream fiss = null;
				if (returnVal == JFileChooser.APPROVE_OPTION) 
					try {
						File file = chooser.getSelectedFile();
						 fiss = new  FileInputStream(file);
						byte[] bs = new byte[1024];
						int count = 0;
						String str = "";
						while ((count = fiss.read(bs)) > 0) 
							str += new String(bs, 0, count); 
						in.setText(str);
					} catch (FileNotFoundException e1 ) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally {
						Constants.close(fiss);
					}
			}
		});
		right.add(select);
		cifa = new JButton("\u8BCD\u6CD5\u5206\u6790");
		cifa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputStream is = null;
				String mes = in.getText();
				if(mes==null || mes.length()==0) {
					showMes("输入不能为空", "词法分析出现错误");
					return;
				}
				try {
					is = new ByteArrayInputStream(mes.getBytes());
				MyGrammar parser = new MyGrammar(is);
				Token token = null;
				token = parser.getNextToken();
				String str = "";
				while (token.kind != 0) {
					str += token.kind + "                   " + token.image + "\n";
					token = parser.getNextToken();
				}
				out.setText(str);
				out_text.setText("输出(词法分析)");
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally {
					Constants.close(is);
				}
		}
		});
		right.add(cifa);

		JButton yufa = new JButton("\u8BED\u6CD5\u5206\u6790");
		yufa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InputStream is = null;
				String mes = in.getText();
				if(mes==null || mes.length()==0) {
					showMes("输入不能为空", "语法分析出现错误");
					return;
				}
				try {		
					is = new ByteArrayInputStream(mes.getBytes());
					MyGrammar parser = new MyGrammar(is);
					SimpleNode n = parser.Start();
					String str = n.dump();
					out.setText(str);
					out_text.setText("输出(语法分析)");
				} catch (Exception e) {
					System.out.println("Oops.");
					System.out.println(e.getMessage());
				}finally {
					Constants.close(is);
			}
			}
		});
		right.add(yufa);
		yuyi = new JButton("\u8BED\u4E49\u5206\u6790");
		yuyi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMes("该功能尚未开发完成","语义分析出现错误");
			
//				VariableNameGenerator.clear();
//				InputStream is = null;
//				try {
//					String mes = in.getText();
//					is = new ByteArrayInputStream(mes.getBytes());
//					MyGrammar parser = new MyGrammar(is);
//					 parser.Start();
//					String str = parser.qtList.getMes();
//					out.setText(str);
//					out_text.setText("输出(语义分析)");
//				} catch (Exception e1) {
//					System.out.println("Oops.");
//					System.out.println(e1.getMessage());
//				}finally {
//					Constants.close(is);
//				}
			}
		});
		right.add(yuyi);
		
		JButton clear = new JButton("\u6E05\u7A7A");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				in.setText("");
				out.setText("");
				out_text.setText("输出");
			}
		});
		right.add(clear);
		
	}
	public void showMes(String mes,String title) {
		new Thread(()->JOptionPane.showMessageDialog(null,mes, title, JOptionPane.INFORMATION_MESSAGE)).start();
	}
}
