package csv2xmlConverter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FinalProject implements Runnable, ActionListener {
	private static JLabel success;
	private static JButton button;
	private static JTextField txtXMLFilename;
	private static JLabel lblXMLFilename;
	private static JTextField txtCSVFilename;
	private static JLabel label;

	// blank constructor
	public FinalProject() {
	}

	@Override
	public void run() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame("CSV to XML Converter");
		frame.setSize(320, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);
		panel.setLayout(null);

		label = new JLabel("CSV filename:");
		label.setBounds(10, 20, 80, 25);
		panel.add(label);

		txtCSVFilename = new JTextField(20);
		txtCSVFilename.setBounds(100, 20, 165, 25);
		panel.add(txtCSVFilename);
		lblXMLFilename = new JLabel("XML filename:");
		lblXMLFilename.setBounds(10, 50, 80, 25);
		panel.add(lblXMLFilename);

		txtXMLFilename = new JTextField("");
		txtXMLFilename.setBounds(100, 50, 165, 25);
		panel.add(txtXMLFilename);

		button = new JButton("Convert");
		button.setBounds(10, 80, 80, 25);
		button.addActionListener(new FinalProject());
		panel.add(button);

		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);

		// show frame in center
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String csvFileName = txtCSVFilename.getText();
		String xmlFileName = txtXMLFilename.getText();
		List<List<String>> fileContents = new ArrayList<>();
		List<String> headers = new ArrayList<>();

		System.out.println(csvFileName + "," + xmlFileName);

		if (!csvFileName.equals("") && !xmlFileName.equals("")) {

			CSVReader objCSVReader = new CSVReader();
			fileContents = objCSVReader.readCsvFile(csvFileName);
			headers = objCSVReader.getHeaders();

			XMLWriter objXMLWriter = new XMLWriter();
			objXMLWriter.writeXMLFile(headers, fileContents, xmlFileName);

			txtCSVFilename.setText("");
			txtXMLFilename.setText("");

			success.setText("File converted successfully");
		} else
			success.setText("Please enter the CSV and XML filenames to convert");
	}

}
