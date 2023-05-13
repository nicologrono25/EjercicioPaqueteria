import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TrackingSystem {
    private List<Package> paquetes;

    public TrackingSystem() {
        paquetes = new ArrayList<>();
    }

    public void agregarPaquete(Package paquete) {
        paquetes.add(paquete);
    }

    public boolean eliminarPaquete(String numeroSeguimiento) {
        for (Package paquete : paquetes) {
            if (paquete.getNumeroSeguimiento().equals(numeroSeguimiento)) {
                paquetes.remove(paquete);
                return true;
            }
        }
        return false;
    }

    public Package searchByTrackingNumber(String trackingNumber) {
        for (Package paquete : paquetes) {
            if (paquete.getTrackingNumber().equals(trackingNumber)) {
                return paquete;
            }
        }
        return null;
    }

    public Package searchByRecipientAddress(String recipientAddress) {
        for (Package paquete : paquetes) {
            if (paquete.getRecipientAddress().equals(recipientAddress)) {
                return paquete;
            }
        }
        return null;
    }

    public List<Package> searchByCity(String city) {
        List<Package> resultados = new ArrayList<>();
        for (Package paquete : paquetes) {
            if (paquete.getCity().equals(city)) {
                resultados.add(paquete);
            }
        }
        return resultados;
    }

    public List<Package> searchByState(String state) {
        List<Package> resultados = new ArrayList<>();
        for (Package paquete : paquetes) {
            if (paquete.getState().equals(state)) {
                resultados.add(paquete);
            }
        }
        return resultados;
    }

    public List<Package> searchByPostalCode(String postalcode) {
        List<Package> resultados = new ArrayList<>();
        for (Package paquete : paquetes) {
            if (paquete.getPostalCode().equals(postalcode)) {
                resultados.add(paquete);
            }
        }
        return resultados;
    }

    public List<Package> getPaquetes() {
        return paquetes;
    }

    public void mostrarPaquetesDisponibles() {
        List<Package> packages = getPaquetes();
        packages.sort(Comparator.comparing(Package::getTrackingNumber));

        StringBuilder sb = new StringBuilder();
        for (Package paquete : packages) {
            sb.append(paquete.toString()).append("\n\n");
        }

        System.out.println(sb.toString());
    }
}




