import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

public class Interfaz extends JFrame {
    private TrackingSystem trackingSystem;
    private JTextArea outputArea;

    public Interfaz() {
        trackingSystem = new TrackingSystem();
        initComponents();
        generarPaquetesPreestablecidos();
        mostrarPaquetesDisponibles();
    }

    private void initComponents() {
        setTitle("Sistema de seguimiento de paquetes");

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2));

        JLabel trackingNumberLabel = new JLabel("Número de seguimiento:");
        JTextField trackingNumberField = new JTextField();
        inputPanel.add(trackingNumberLabel);
        inputPanel.add(trackingNumberField);

        JLabel recipientAddressLabel = new JLabel("Dirección del destinatario:");
        JTextField recipientAddressField = new JTextField();
        inputPanel.add(recipientAddressLabel);
        inputPanel.add(recipientAddressField);

        JLabel cityLabel = new JLabel("Ciudad:");
        JTextField cityField = new JTextField();
        inputPanel.add(cityLabel);
        inputPanel.add(cityField);

        JLabel stateLabel = new JLabel("Estado:");
        JTextField stateField = new JTextField();
        inputPanel.add(stateLabel);
        inputPanel.add(stateField);

        JLabel postalCodeLabel = new JLabel("Código postal:");
        JTextField postalCodeField = new JTextField();
        inputPanel.add(postalCodeLabel);
        inputPanel.add(postalCodeField);

        contentPane.add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 0));

        JButton searchByTrackingNumberButton = new JButton("Buscar por número de seguimiento");
        searchByTrackingNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trackingNumber = trackingNumberField.getText();
                Package pkg = trackingSystem.searchByTrackingNumber(trackingNumber);
                if (pkg != null) {
                    outputArea.setText(pkg.toString());
                } else {
                    outputArea.setText("Paquete no encontrado.");
                }
            }
        });
        buttonPanel.add(searchByTrackingNumberButton);

        JButton searchByRecipientAddressButton = new JButton("Buscar por dirección del destinatario");
        searchByRecipientAddressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipientAddress = recipientAddressField.getText();
                Package pkg = trackingSystem.searchByRecipientAddress(recipientAddress);
                if (pkg != null) {
                    outputArea.setText(pkg.toString());
                } else {
                    outputArea.setText("Paquete no encontrado.");
                }
            }
        });
        buttonPanel.add(searchByRecipientAddressButton);

        JButton searchByCityButton = new JButton("Buscar por ciudad");
        searchByCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText();
                List<Package> packages = trackingSystem.searchByCity(city);
                if (!packages.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Package pkg : packages) {
                        sb.append(pkg.toString()).append("\n\n");
                    }
                    outputArea.setText(sb.toString());
                } else {
                    outputArea.setText("No se encontraron paquetes en la ciudad especificada.");
                }
            }
        });
        buttonPanel.add(searchByCityButton);

        JButton searchByStateButton = new JButton("Buscar por estado");
        searchByStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String state = stateField.getText();
                List<Package> packages = trackingSystem.searchByState(state);
                if (!packages.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Package pkg : packages) {
                        sb.append(pkg.toString()).append("\n\n");
                    }
                    outputArea.setText(sb.toString());
                } else {
                    outputArea.setText("No se encontraron paquetes en el estado especificado.");
                }
            }
        });
        buttonPanel.add(searchByStateButton);

        JButton searchByPostalCodeButton = new JButton("Buscar por código postal");
        searchByPostalCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String postalCode = postalCodeField.getText();
                List<Package> packages = trackingSystem.searchByPostalCode(postalCode);
                if (!packages.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (Package pkg : packages) {
                        sb.append(pkg.toString()).append("\n\n");
                    }
                    outputArea.setText(sb.toString());
                } else {
                    outputArea.setText("No se encontraron paquetes en el código postal especificado.");
                }
            }
        });
        buttonPanel.add(searchByPostalCodeButton);

        JButton addPackageButton = new JButton("Añadir paquete");
        addPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trackingNumber = trackingNumberField.getText();
                String recipientAddress = recipientAddressField.getText();
                String city = cityField.getText();
                String state = stateField.getText();
                String postalCode = postalCodeField.getText();

                Package pkg = new Package(trackingNumber, "", recipientAddress, city, state, postalCode);
                trackingSystem.agregarPaquete(pkg);

                mostrarPaquetesDisponibles();
                outputArea.setText("Paquete añadido correctamente.");
            }
        });
        buttonPanel.add(addPackageButton);

        JButton deletePackageButton = new JButton("Eliminar paquete");
        deletePackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trackingNumber = trackingNumberField.getText();
                boolean removed = trackingSystem.eliminarPaquete(trackingNumber);

                if (removed) {
                    mostrarPaquetesDisponibles();
                    outputArea.setText("Paquete eliminado correctamente.");
                } else {
                    outputArea.setText("No se encontró ningún paquete con el número de seguimiento especificado.");
                }
            }
        });
        buttonPanel.add(deletePackageButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void generarPaquetesPreestablecidos() {
        Package pkg1 = new Package("123456789", "Producto 1", "Villaflora", "Quito", "Entregado", "12345");
        Package pkg2 = new Package("987654321", "Producto 2", "Mena", "Cuenca", "En camino", "54321");
        Package pkg3 = new Package("123488789", "Producto 3", "Guangopolo", "Quevedo", "Recogido", "12225");
        Package pkg4 = new Package("987653344", "Producto 4", "Republica", "Baños", "En camino", "88421");
        Package pkg5 = new Package("123400020", "Producto 5", "6 de diciembre", "Ibarra", "Entregado", "55345");
        Package pkg6 = new Package("736363001", "Producto 6", "Diego de almagro", "Guayaquil", "Entregado", "57321");

        trackingSystem.agregarPaquete(pkg1);
        trackingSystem.agregarPaquete(pkg2);
        trackingSystem.agregarPaquete(pkg3);
        trackingSystem.agregarPaquete(pkg4);
        trackingSystem.agregarPaquete(pkg5);
        trackingSystem.agregarPaquete(pkg6);
    }

    private void mostrarPaquetesDisponibles() {
        List<Package> packages = trackingSystem.getPaquetes();
        packages.sort(Comparator.comparing(Package::getNumeroSeguimiento));

        StringBuilder sb = new StringBuilder();
        for (Package pkg : packages) {
            sb.append(pkg.toString()).append("\n\n");
        }
        outputArea.setText(sb.toString());
    }
}