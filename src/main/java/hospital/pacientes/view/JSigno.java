package hospital.pacientes.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JSigno extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JSigno frame = new JSigno();
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
	public JSigno() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 747, 472);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 73, 260, 252);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID Paciente");
		lblNewLabel_1.setBounds(23, 41, 55, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(102, 38, 92, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Temperatura");
		lblNewLabel_2.setBounds(23, 105, 62, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Saturacion");
		lblNewLabel_3.setBounds(23, 164, 51, 14);
		panel.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(102, 102, 92, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(102, 161, 92, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("C\u00B0");
		lblNewLabel_4.setBounds(204, 105, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("%");
		lblNewLabel_5.setBounds(204, 164, 46, 14);
		panel.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Limpiar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(93, 205, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("SIGNOS VITALES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(343, 22, 146, 21);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("GUARDAR");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.setBounds(115, 336, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("ID Paciente");
		lblNewLabel_6.setBounds(30, 383, 55, 14);
		getContentPane().add(lblNewLabel_6);
		
		textField_3 = new JTextField();
		textField_3.setBounds(95, 380, 97, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("BUSCAR");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.setBounds(201, 379, 73, 23);
		getContentPane().add(btnNewButton_2);
		
		table = new JTable();
		table.setBounds(312, 83, 393, 276);
		getContentPane().add(table);
		
		JButton btnNewButton_3 = new JButton("MOSTRAR TABLA");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_3.setBounds(466, 370, 131, 34);
		getContentPane().add(btnNewButton_3);

	}

}
