package com.mobile.tool.stock.manager.listener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mobile.tool.stock.manager.entity.RecordKeeping;
import com.mobile.tool.stock.manager.repository.RecordKeepingRepository;

public class AddRecordKeepingListener extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6191510576296067659L;

	JLabel headline, typeLabel, commentLabel, attachmentLabel;
	JTextField uploadFileText;
	JButton uploadFileButton;
	JTextArea commentText;
	JComboBox<String> typeBox;
	String filePath;

	JButton createButton, clearButton;
	WindowAdapter adapter;
	JFrame frame;
	String recordFilePath = "D:/";

	private AdminRoleMouseListener listener;

	public AddRecordKeepingListener(AdminRoleMouseListener listener) {
		this.listener = listener;
	}

	public void createAndShowGUI() {
		setVisible(true);
		setSize(700, 300);
		setLayout(null);
		setTitle("Stock Management");

		headline = new JLabel("Record:");
		headline.setForeground(Color.blue);
		headline.setFont(new Font("Serif", Font.BOLD, 20));

		typeLabel = new JLabel("Type:");
		commentLabel = new JLabel("Comment:");
		attachmentLabel = new JLabel("Attachment:");

		uploadFileText = new JTextField();
		uploadFileButton = new JButton();
		uploadFileButton.setText("..");
		uploadFileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(null)) {
					File file = chooser.getSelectedFile();
					filePath = file.getAbsolutePath();
					uploadFileText.setText(file.getName());
				}

			}
		});

		String[] petStrings = { "File", "Text" };
		typeBox = new JComboBox<String>(petStrings);
		commentText = new JTextArea();
		createButton = new JButton("Create");
		clearButton = new JButton("Clear");

		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				long timestamp = System.currentTimeMillis();
				String dest = null;
				File sourceFile = null;
				if (filePath != null) {
					sourceFile = new File(filePath);
					dest = recordFilePath + "/" + sourceFile.getName() + "."
							+ timestamp;
				}
				RecordKeeping recordKeeping = new RecordKeeping(null, typeBox
						.getSelectedItem().toString(),
						dest,
						commentText.getText());
				RecordKeepingRepository.addRecordKeeping(recordKeeping);
				if(dest!=null && sourceFile!=null){
					try {
						Files.copy(sourceFile.toPath(),
								new File(dest).toPath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				listener.reloadRecordKeepingTableData();
				dispose();
			}
		});

		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uploadFileText.setText("");
				commentText.setText("");
				typeBox.setSelectedIndex(0);
			}
		});

		headline.setBounds(80, 30, 400, 30);
		typeLabel.setBounds(80, 70, 200, 30);
		commentLabel.setBounds(80, 110, 200, 30);
		attachmentLabel.setBounds(80, 190, 200, 30);
		typeBox.setBounds(300, 70, 200, 30);
		commentText.setBounds(300, 110, 200, 60);
		uploadFileText.setBounds(300, 190, 160, 30);
		uploadFileButton.setBounds(470, 190, 30, 30);
		createButton.setBounds(50, 230, 100, 30);
		clearButton.setBounds(170, 230, 100, 30);

		add(headline);
		add(typeLabel);
		add(attachmentLabel);
		add(uploadFileText);
		add(uploadFileButton);
		add(commentLabel);
		add(commentText);
		add(typeBox);
		add(createButton);
		add(clearButton);

		adapter = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// frame.setEnabled(true);
				super.windowClosing(e);
			}

		};
		addWindowListener(adapter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// this.frame.setEnabled(false);
		createAndShowGUI();

	}

}
