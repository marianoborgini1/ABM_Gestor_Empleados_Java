import java.util.ArrayList;
import java.util.List;
public class Empresa implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private List<Empleado> empleados = new ArrayList<>();

    public Empresa(){
        
    }

    public void agregarEmpleado(Empleado i){
        empleados.add(i);
    }

    public void mostrarTodosLosDatos(){
        for(Empleado i: empleados){
            i.mostrarDatos();
        }
    }

    public void buscarEmpleado(int dni){
        boolean encontrado = false; // bandera
        for(Empleado i: empleados){
            int dniActual = i.getDni();
            if (dniActual == dni) {
                System.out.println("Empleado encontrado!");
                i.mostrarDatos();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
                System.out.println("no se encontro ningun empleado!");
        }
    }

    public void eliminarEmpleado(int dni){
        boolean borrado = empleados.removeIf(i -> i.getDni() == dni);

        if (borrado) {
            System.out.println("empleado borrado exitosamente");
        }
        else{
            System.out.println("no se encontro ningun empleado con ese dni...");
        }
    }

    public void aumentoGeneral(double aumentoGeneral){
        for(Empleado i: empleados){
            double salarioActual = i.getSalario();
            double aumento = salarioActual + (salarioActual * aumentoGeneral/100);
            i.setSalario(aumento);
        }
    }

    public void mostrarDatosLista(){
        if (empleados.isEmpty()) {
            System.out.println("Lista vacia, ingrese empleados.");
        }else{
            for(Empleado i: empleados){
                i.mostrarDatos();
                System.out.println("-----------");
            }
        }
    }

    public Empleado obtenerEmpleadoPorDni(int dni){
        for(Empleado i: empleados){
            int dniActual = i.getDni();
            if (dniActual == dni) {
                return i;
            }
        }
        return null;
    }
    
}

