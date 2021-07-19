package hospital.pacientes.view;

import hospital.pacientes.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;

public class App extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtDni;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtNro;
	private JTextField txtBuscar;
	private JTable table;

	static EntityManagerFactory factory;
	static EntityManager entityManager;;

	JRadioButton rdbtnMasculino = new JRadioButton("M");
	JRadioButton rdbtnFemenino = new JRadioButton("F");
	Paciente obj = new Paciente();

	ButtonGroup g1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void begin() {

		factory = Persistence.createEntityManagerFactory("PacienteUnit");
		entityManager = factory.createEntityManager();

		entityManager.getTransaction().begin();
	}

	private static void end() {

		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	private void cargarTabla() {
		begin();
		listarTabla(table);
		end();
	}
	private static void eliminar(int clave) {
		Paciente reference=entityManager.find(Paciente.class, clave);
		entityManager.remove(reference);
	}

	public void listarTabla(JTable tabla) {

		DefaultTableModel model = new DefaultTableModel(null, new Object[] {"ID_PACIENTE","DNI","NOMBRES","APELLIDOS","NRO CELULAR","GENERO"});
		

		String jpql = "SELECT p FROM Paciente p";
		Query query = entityManager.createQuery(jpql);

		@SuppressWarnings("unchecked")
		List<Paciente> listPaciente = query.getResultList();

		
		for (Paciente p : listPaciente) {
			model.addRow(new Object[] { p.getIdPaciente(), p.getDni(), p.getNombres(), p.getApellidos(), p.getNroCel(),
					p.getGenero() });
		}
		tabla.setModel(model);

	}
	public void listBuscar(JTable tabla,int clave){
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Id");
        model.addColumn("DNI");
        model.addColumn("NOMBRES");
        model.addColumn("APELLIDOS");
        model.addColumn("NROCELULAR");
        model.addColumn("GENERO");
		
		Paciente paciente = entityManager.find(Paciente.class, clave);
		
	    System.out.println(paciente.getNombres());
		
			model.addRow(new Object[] { paciente.getIdPaciente(), paciente.getDni(), paciente.getNombres(), paciente.getApellidos(), paciente.getNroCel(),
					paciente.getGenero()});
		//}
		tabla.setModel(model);
	    
		
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("HOSPITAL COVID (PACIENTES)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Maiandra GD", Font.BOLD, 15));
		lblNewLabel.setBounds(282, 0, 239, 23);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 36, 326, 252);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("ID                   :");
		lblNewLabel_1.setBounds(23, 32, 84, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("DNI                 :");
		lblNewLabel_2.setBounds(23, 65, 84, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Nombres      :");
		lblNewLabel_3.setBounds(23, 98, 84, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Apellidos      :");
		lblNewLabel_4.setBounds(23, 134, 84, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Nro Celular   :");
		lblNewLabel_5.setBounds(23, 173, 84, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Genero          :");
		lblNewLabel_6.setBounds(23, 209, 84, 14);
		panel.add(lblNewLabel_6);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(129, 29, 187, 20);
		panel.add(txtId);
		txtId.setColumns(10);

		txtDni = new JTextField();
		txtDni.setBounds(129, 62, 187, 20);
		panel.add(txtDni);
		txtDni.setColumns(10);

		txtNombres = new JTextField();
		txtNombres.setBounds(129, 95, 187, 20);
		panel.add(txtNombres);
		txtNombres.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(129, 131, 187, 20);
		panel.add(txtApellidos);
		txtApellidos.setColumns(10);

		txtNro = new JTextField();
		txtNro.setBounds(129, 170, 187, 20);
		panel.add(txtNro);
		txtNro.setColumns(10);
		rdbtnMasculino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				obj.setGenero(rdbtnMasculino.getLabel());
			}
		});

		rdbtnMasculino.setBounds(129, 205, 84, 23);
		panel.add(rdbtnMasculino);
		rdbtnFemenino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				obj.setGenero(rdbtnFemenino.getLabel());
			}
		});

		rdbtnFemenino.setBounds(217, 205, 99, 23);
		panel.add(rdbtnFemenino);

		JButton btnAgregar = new JButton("AGREGAR PA.");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String dni, nombres, apellidos, nrocel, genero;

				begin();

				dni = txtDni.getText();
				nombres = txtNombres.getText();
				apellidos = txtApellidos.getText();
				nrocel = txtNro.getText();
				genero = obj.getGenero();

				Paciente newPaciente = new Paciente();

				newPaciente.setDni(dni);
				newPaciente.setNombres(nombres);
				newPaciente.setApellidos(apellidos);
				newPaciente.setNroCel(nrocel);
				newPaciente.setGenero(genero);

				entityManager.persist(newPaciente);

				end();

				JOptionPane.showMessageDialog(null, "Paciente registrado con exito");

				txtDni.setText("");
				txtNombres.setText("");
				txtApellidos.setText("");
				txtNro.setText("");
				txtDni.requestFocus();

				cargarTabla();
			}
		});
		btnAgregar.setHorizontalAlignment(SwingConstants.LEFT);
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAgregar.setBounds(10, 306, 99, 23);
		contentPane.add(btnAgregar);

		JButton btnActualizar = new JButton("ACTUALIZAR PA.");
		btnActualizar.setHorizontalAlignment(SwingConstants.LEFT);
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnActualizar.setBounds(113, 307, 113, 23);
		contentPane.add(btnActualizar);

		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id;
				id=Integer.parseInt(txtBuscar.getText());
				begin();
				listBuscar(table,id);
				end();
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnBuscar.setBounds(239, 357, 89, 23);
		contentPane.add(btnBuscar);

		JLabel lblNewLabel_7 = new JLabel("ID Paciente");
		lblNewLabel_7.setBounds(10, 361, 81, 14);
		contentPane.add(lblNewLabel_7);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(83, 358, 146, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(346, 36, 428, 314);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnActTb = new JButton("ACTUALIZAR TABLA");
		btnActTb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla();
			}
		});
		btnActTb.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnActTb.setBounds(356, 357, 171, 23);
		contentPane.add(btnActTb);

		JButton btnEliminar = new JButton("ELIMINAR PA.");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idPaciente;
				idPaciente=Integer.parseInt(txtBuscar.getText());
				begin();
				eliminar(idPaciente);
				end();
				
				cargarTabla();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEliminar.setBounds(229, 306, 99, 23);
		contentPane.add(btnEliminar);

		g1.add(rdbtnMasculino);
		g1.add(rdbtnFemenino);
	}
}
