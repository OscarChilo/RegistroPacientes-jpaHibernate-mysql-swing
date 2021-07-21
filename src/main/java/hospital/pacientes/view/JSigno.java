package hospital.pacientes.view;

import java.awt.EventQueue;

import hospital.pacientes.*;
import hospital.pacientes.view.*;

import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JSigno extends JInternalFrame {
	public JTextField txtIdpa;
	public JTextField txtTemp;
	public JTextField txtSatu;
	public JTextField txtBuscar;
	private JTable table;
	
	static EntityManagerFactory factory;
	static EntityManager entityManager;;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JSigno frame = new JSigno();
					//frame.setMaximizable(true);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static void begin() {
		factory = Persistence.createEntityManagerFactory("HospitalUnit");
		entityManager = factory.createEntityManager();

		entityManager.getTransaction().begin();
	}
	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	private void limpiar() {
		txtIdpa.setText("");
		txtTemp.setText("");
		txtSatu.setText("");
		txtBuscar.setText("");
		txtTemp.requestFocus();
		
	}
	

	/**
	 * Create the frame.
	 */
	public JSigno() {
		//setMaximum(true);
		
		setClosable(true);
		//setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 1100, 516);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 73, 260, 252);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID Paciente");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(23, 41, 55, 14);
		panel.add(lblNewLabel_1);
		
		txtIdpa = new JTextField();
		txtIdpa.setBounds(102, 38, 92, 20);
		panel.add(txtIdpa);
		txtIdpa.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Temperatura");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(23, 158, 62, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Saturacion");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(23, 98, 51, 14);
		panel.add(lblNewLabel_3);
		
		txtTemp = new JTextField();
		txtTemp.setBounds(102, 155, 92, 20);
		panel.add(txtTemp);
		txtTemp.setColumns(10);
		
		txtSatu = new JTextField();
		txtSatu.setBounds(102, 95, 92, 20);
		panel.add(txtSatu);
		txtSatu.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("C\u00B0");
		lblNewLabel_4.setBounds(204, 158, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("%");
		lblNewLabel_5.setBounds(204, 98, 46, 14);
		panel.add(lblNewLabel_5);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLimpiar.setBounds(93, 205, 89, 23);
		panel.add(btnLimpiar);
		
		JLabel lblNewLabel = new JLabel("SIGNOS VITALES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(343, 22, 146, 21);
		getContentPane().add(lblNewLabel);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int  temp,idPa;
				double satu;
				
				Date fecha=new Date();
				DateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
				System.out.println(fecha);
				Signo newSigno=new Signo();
				//String nombre=
				idPa=Integer.parseInt(txtIdpa.getText());
				temp=Integer.parseInt(txtTemp.getText());
				satu=Double.parseDouble(txtSatu.getText());
				
				begin();
				
				newSigno.setIdPaciente(idPa);
				newSigno.setApellidos("toledo");
				newSigno.setSaturacion(satu);
				newSigno.setTemperatura(temp);
				newSigno.setFecha(null);
				//newSigno.setIdPaciente(null);
				entityManager.persist(newSigno);
				System.out.println(fecha);
				end();
				JOptionPane.showMessageDialog(null, "Signos vitales NUEVO REGISTRADO con EXITO!!");
				limpiar();
				
				
				
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnGuardar.setBounds(115, 336, 89, 23);
		getContentPane().add(btnGuardar);
		
		JLabel lblNewLabel_6 = new JLabel("ID Paciente");
		lblNewLabel_6.setBounds(30, 383, 55, 14);
		getContentPane().add(lblNewLabel_6);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(95, 380, 97, 20);
		getContentPane().add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnBuscar.setBounds(201, 379, 73, 23);
		getContentPane().add(btnBuscar);
		
		table = new JTable();
		table.setBounds(312, 83, 393, 276);
		getContentPane().add(table);
		
		JButton btnMostrar = new JButton("MOSTRAR TABLA");
		btnMostrar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnMostrar.setBounds(466, 370, 131, 34);
		getContentPane().add(btnMostrar);

	}

}
