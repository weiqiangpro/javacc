package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import exception.FunException;
import exception.SwitchException;
import exception.VariableException;
import javacc.MyGrammar;
import javacc.ParseException;
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyUI frame = new MyUI();
					frame.setTitle("编译原理");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MyUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
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
		Font fontText1=new Font("微软雅黑",Font.CENTER_BASELINE,18);
		JTextPane in = new JTextPane();
		scrollPane.setViewportView(in);
			in.setFont(fontText1);
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
		Font fontText2=new Font("微软雅黑",Font.PLAIN,15);
		out.setFont(fontText2);
			out.setForeground(Color.RED);
		JPanel right = new JPanel();
		splitPane.setLeftComponent(right);
		right.setLayout(new GridLayout(6, 0, 0, 0));

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
						fiss = new FileInputStream(file);
						byte[] bs = new byte[1024];
						int count = 0;
						String str = "";
						while ((count = fiss.read(bs)) > 0)
							str += new String(bs, 0, count);
						in.setText(str);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
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
				String title = "输出(词法分析)";
				if (mes.replace(" ","").length() == 0) {
					showMes("输入不能为空", "词法分析出现错误");
					out.setText("输入不能为空");
					return;
				}
				VariableNameGenerator.clear();
				String str = "";
				try {
					is = new ByteArrayInputStream(mes.getBytes());
					MyGrammar parser = new MyGrammar(is);
					Token token = null;
					token = parser.getNextToken();
					while (token.kind != 0) {
						str += token.kind + "                   " + token.image + "\n";
						token = parser.getNextToken();
					}
					out.setText(str);
					out_text.setText("输出(词法分析)");
				} catch (FunException fu) {
					showMes("方法定义或调用出现错误", "语义分析出现错误");
					str = fu.getMessage();
					 title = "输出(出现错误)";
				} catch (SwitchException sw) {
					showMes("switch-case语句出现错误", "语义分析出现错误");
					str = sw.getMessage();
					 title = "输出(出现错误)";
				} catch (VariableException v) {
					showMes("变量定义或调用出现错误", "语义分析出现错误");
					str = v.getMessage();
					 title = "输出(出现错误)";
				} finally {
					if(!title.equals("输出(出现错误)"))
						out.setForeground(Color.BLACK);
					else
						out.setForeground(Color.RED);
					out.setText(str);
					out_text.setText(title);
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
				String str = null;
				String title = "输出(词法分析)";
				VariableNameGenerator.clear();
				if (mes.replace(" ","").length() == 0) {
					showMes("输入不能为空", "词法分析出现错误");
					out.setText("输入不能为空");
					return;
				}
				try {
					is = new ByteArrayInputStream(mes.getBytes());
					MyGrammar parser = new MyGrammar(is);
					SimpleNode n = parser.Start();
					 str = n.dump();
				} catch (FunException fu) {
					showMes("方法定义或调用出现错误", "语义分析出现错误");
					str = fu.getMessage();
					 title = "输出(出现错误)";
				} catch (SwitchException sw) {
					showMes("switch-case语句出现错误", "语义分析出现错误");
					str = sw.getMessage();
					 title = "输出(出现错误)";
				} catch (VariableException v) {
					showMes("变量定义或调用出现错误", "语义分析出现错误");
					str = v.getMessage();
					 title = "输出(出现错误)";
				} catch (ParseException e1) {
					showMes("语法分析出现错误", "语义分析出现错误");
					str = e1.getMessage();
					 title = "输出(出现错误)";
				} finally {
					if(!title.equals("输出(出现错误)"))
						out.setForeground(Color.BLACK);
					else
						out.setForeground(Color.RED);
					out.setText(str);
					out_text.setText(title);
					Constants.close(is);
				}
			}
		});
		right.add(yufa);
		yuyi = new JButton("\u8BED\u4E49\u5206\u6790");
		yuyi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VariableNameGenerator.clear();
				String title = "输出(语义分析)";
				InputStream is = null;
				VariableNameGenerator.clear();
				String str = null;
				String mes = in.getText();
				if (mes.replace(" ","").length() == 0) {
					showMes("输入不能为空", "词法分析出现错误");
					out.setText("输入不能为空");
					return;
				}
				try {	
					is = new ByteArrayInputStream(mes.getBytes());
					MyGrammar parser = new MyGrammar(is);
					parser.Start();
					 str = parser.qtList.getMes();
				} catch (FunException fu) {
					showMes("方法定义或调用出现错误", "语义分析出现错误");
					str = fu.getMessage();
					 title = "输出(出现错误)";
				} catch (SwitchException sw) {
					showMes("switch-case语句出现错误", "语义分析出现错误");
					str = sw.getMessage();
					 title = "输出(出现错误)";
				} catch (VariableException v) {
					showMes("变量定义或调用出现错误", "语义分析出现错误");
					str = v.getMessage();
					 title = "输出(出现错误)";
				} catch (ParseException e1) {
					showMes("语法分析出现错误", "语义分析出现错误");
					str = e1.getMessage();
					 title = "输出(出现错误)";
				} finally {
					if(!title.equals("输出(出现错误)"))
						out.setForeground(Color.BLACK);
					else
						out.setForeground(Color.RED);
					out.setText(str);
					out_text.setText(title);
					Constants.close(is);
				}
			}
		});
		right.add(yuyi);
		JButton	var = new JButton("字符表");
		var.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VariableNameGenerator.clear();
				String title = "输出(字符表)";
				InputStream is = null;
				VariableNameGenerator.clear();
				String str = null;
				String mes = in.getText();
				if (mes.replace(" ","").length() == 0) {
					showMes("输入不能为空", "词法分析出现错误");
					out.setText("输入不能为空");
					return;
				}
				try {	
					is = new ByteArrayInputStream(mes.getBytes());
					MyGrammar parser = new MyGrammar(is);
					parser.Start();
					 str = parser.tableList.getMes();
				} catch (FunException fu) {
					showMes("方法定义或调用出现错误", "语义分析出现错误");
					str = fu.getMessage();
					 title = "输出(出现错误)";
				} catch (SwitchException sw) {
					showMes("switch-case语句出现错误", "语义分析出现错误");
					str = sw.getMessage();
					 title = "输出(出现错误)";
				} catch (VariableException v) {
					showMes("变量定义或调用出现错误", "语义分析出现错误");
					str = v.getMessage();
					 title = "输出(出现错误)";
				} catch (ParseException e1) {
					showMes("语法分析出现错误", "语义分析出现错误");
					str = e1.getMessage();
					 title = "输出(出现错误)";
				} finally {
					if(!title.equals("输出(出现错误)"))
						out.setForeground(Color.BLACK);
					else
						out.setForeground(Color.RED);
					out.setText(str);
					out_text.setText(title);
					Constants.close(is);
				}
			}
		});
		right.add(var);
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

	public void showMes(String mes, String title) {
		JOptionPane option = new JOptionPane();
		// 设置字体
		option.setFont(new Font("幼圆", Font.ITALIC, 40));
		// 设置颜色
		option.setForeground(Color.BLUE);

		JOptionPane.showMessageDialog(null, mes, title, JOptionPane.INFORMATION_MESSAGE);
		//new Thread(() -> JOptionPane.showMessageDialog(null, mes, title, JOptionPane.INFORMATION_MESSAGE)).start();
	}
}
