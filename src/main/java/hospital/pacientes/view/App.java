package hospital.pacientes.view;

import hospital.pacientes.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;

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
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.SystemColor;

public class App extends JFrame {

	private JPanel escritorio1;
	private JTextField txtId;
	private JTextField txtDni;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtNro;
	private JTextField txtBuscar;
	private JTable table;
	
	public static String pro;

	static EntityManagerFactory factory;
	static EntityManager entityManager;;

	JRadioButton rdbtnMasculino = new JRadioButton("M");
	JRadioButton rdbtnFemenino = new JRadioButton("F");
	Paciente obj = new Paciente();

	ButtonGroup g1 = new ButtonGroup();
	private JTable tabla2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void begin() {

		factory = Persistence.createEntityManagerFactory("HospitalUnit");
		entityManager = factory.createEntityManager();

		entityManager.getTransaction().begin();
	}
	
	public static void create() {
		
	}

	public static void end() {

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
		
		//initComponents();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 516);
		escritorio1 = new JPanel();
		escritorio1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(escritorio1);
		escritorio1.setLayout(null);
		
		JDesktopPane escritorio = new JDesktopPane();
		escritorio.setBackground(SystemColor.menu);
		escritorio.setBounds(0, 21, 1280, 700);
		escritorio1.add(escritorio);

		JLabel lblNewLabel = new JLabel("HOSPITAL COVID (PACIENTES)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Maiandra GD", Font.BOLD, 15));
		lblNewLabel.setBounds(296, 45, 239, 23);
		escritorio.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 92, 326, 269);
		escritorio.add(panel);
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

		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				

				begin();
				
				String dni, nombres, apellidos, nrocel, genero;
				Paciente newPaciente = new Paciente();

				dni = txtDni.getText();
				nombres = txtNombres.getText();
				apellidos = txtApellidos.getText();
				nrocel = txtNro.getText();
				genero = obj.getGenero();

				newPaciente.setDni(dni);
				newPaciente.setNombres(nombres);
				newPaciente.setApellidos(apellidos);
				newPaciente.setNroCel(nrocel);
				newPaciente.setGenero(genero);

				entityManager.persist(newPaciente);

				end();

				JOptionPane.showMessageDialog(null, "Paciente NUEVO REGISTRADO con EXITO!!");

				txtDni.setText("");
				txtNombres.setText("");
				txtApellidos.setText("");
				txtNro.setText("");
				g1.clearSelection();
				txtDni.requestFocus();

				cargarTabla();
			}
		});
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnAgregar.setBounds(20, 364, 99, 23);
		escritorio.add(btnAgregar);

		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				begin();
				
				int idPaciente;
				String dni, nombres, apellidos, nrocel, genero;
				Paciente existPaciente = new Paciente();

				idPaciente=Integer.parseInt(txtId.getText());
				dni = txtDni.getText();
				nombres = txtNombres.getText();
				apellidos = txtApellidos.getText();
				nrocel = txtNro.getText();
				genero = obj.getGenero();

				existPaciente.setIdPaciente(idPaciente);
				existPaciente.setDni(dni);
				existPaciente.setNombres(nombres);
				existPaciente.setApellidos(apellidos);
				existPaciente.setNroCel(nrocel);
				existPaciente.setGenero(genero);
				
				entityManager.merge(existPaciente);
				
				end();
				
				JOptionPane.showMessageDialog(null, "Paciente ACTUALIZADO con EXITO!!");
				
				txtId.setText("");
				txtDni.setText("");
				txtNombres.setText("");
				txtApellidos.setText("");
				txtNro.setText("");
				g1.clearSelection();
				txtDni.requestFocus();

				cargarTabla();
				
			}
		});
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnActualizar.setBounds(123, 365, 113, 23);
		escritorio.add(btnActualizar);

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
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnBuscar.setBounds(249, 415, 89, 23);
		escritorio.add(btnBuscar);

		JLabel lblNewLabel_7 = new JLabel("ID Paciente");
		lblNewLabel_7.setBounds(20, 419, 81, 14);
		escritorio.add(lblNewLabel_7);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(93, 416, 146, 20);
		escritorio.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(356, 94, 468, 310);
		escritorio.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table=(JTable)e.getSource();
				Point point=e.getPoint();
				int row=table.rowAtPoint(point);
				if(e.getClickCount()==1) {
					txtId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					txtDni.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtNombres.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					txtApellidos.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					txtNro.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
						
				}
			}
		});


		JButton btnActTb = new JButton("MOSTRAR TABLA");
		btnActTb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTabla();
			}
		});
		btnActTb.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnActTb.setBounds(522, 415, 171, 40);
		escritorio.add(btnActTb);

		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idPaciente;
				idPaciente=Integer.parseInt(txtBuscar.getText());
				begin();
				eliminar(idPaciente);
				end();
				JOptionPane.showMessageDialog(null, "Paciente ELIMINADO con EXITO!!");
				txtBuscar.setText("");
				cargarTabla();
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnEliminar.setBounds(239, 364, 99, 23);
		escritorio.add(btnEliminar);

		g1.add(rdbtnMasculino);
		g1.add(rdbtnFemenino);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtId.setText("");
				txtDni.setText("");
				txtNombres.setText("");
				txtApellidos.setText("");
				txtNro.setText("");
				g1.clearSelection();
				txtDni.requestFocus();
			}
		});
		btnLimpiar.setBounds(129, 235, 73, 23);
		panel.add(btnLimpiar);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 839, 22);
		escritorio1.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("SISTEMA");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Salir");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("MANTENIMIENTO");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Signos Vitales");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JSigno nuevaVentana=new JSigno();
				escritorio.add(nuevaVentana);

				//nuevaVentana.setRelativeLocation();
				//nuevaVentana.setVisible(true);
				/*int width=escritorio.getWidth();
				int heigth=escritorio.getHeight();
				nuevaVentana.setSize(width,heigth);
				nuevaVentana.setLocation(null);*/
				
				Dimension desktopSize = escritorio.getSize();
		        Dimension FrameSize = nuevaVentana.getSize();
		        //nuevaVentana.setMaximizable(true);
		        //nuevaVentana.setma;
		        nuevaVentana.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        nuevaVentana.show();
				
				
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		
	}
}
