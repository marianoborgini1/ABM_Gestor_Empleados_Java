import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
 
public class App {
    public static void main(String[] args) throws Exception {
        Empresa empresa = new Empresa();
        Scanner sc = new Scanner(System.in);
        int option;
        int subOption;

        File archivo = new File("datos.dat");
        if (archivo.exists()) {
            try (ObjectInputStream recuperarFichero = new ObjectInputStream(new FileInputStream(archivo))){
                empresa = (Empresa) recuperarFichero.readObject();
                System.out.println("datos cargados...");
            } catch (Exception e) {
                System.out.println("error al cargar." + e.getMessage());
                }
        } else{
            System.out.println("Iniciando sistema desde cero...");
        }
         
        do {

            // 1. Mostrar el menú de opciones
            System.out.println("\n=================================================");
            System.out.println("           SISTEMA DE GESTIÓN DE EMPLEADOS         ");
            System.out.println("=================================================");
            System.out.println("1. Agregar");
            System.out.println("2. Buscar");
            System.out.println("3. Mostrar Empleados");
            System.out.println("4. Borrar");
            System.out.println("5. Cambiar categoria a empleado");
            System.out.println("6. Aumentar sueldo");
            System.out.println("7. Guardar y salir");

            System.out.println("Elige una opcion: ");
            option = sc.nextInt();
            sc.nextLine();


           switch (option) {
            case 1:
                System.out.println("\n--- REGISTRO DE EMPLEADO ---");
                System.out.print("Nombre completo: ");
                String nombre = sc.nextLine();
                
                System.out.print("DNI: ");
                int dni = sc.nextInt();
                sc.nextLine();

                System.out.print("Sueldo base: ");
                double salario = sc.nextDouble();
                sc.nextLine();

                System.out.println("Categorías: [JUNIOR, SEMI_SENIOR, SENIOR]");
                System.out.print("Asignar categoría: ");
                String categoriaIngresada = sc.nextLine();
                
                try {
                    Categoria categoriaEmp = Categoria.valueOf(categoriaIngresada.toUpperCase().trim().replace(" ", "_"));
                    Empleado empleado1 = new Empleado(nombre, dni, salario, categoriaEmp);
                    empresa.agregarEmpleado(empleado1);
                    System.out.println(" Empleado registrado con éxito.");
                } catch (Exception e) {
                    System.out.println(" Error: Categoría no válida. Intente nuevamente.");
                }
                    System.out.println("\nPresione Enter para continuar...");
                    sc.nextLine();
                break;

            case 2:
                System.out.println("--- BUSCAR EMPLEADO ---");
                System.out.println("Ingrese DNI: ");
                int dniBuscar = sc.nextInt();
                sc.nextLine();
                empresa.buscarEmpleado(dniBuscar);
                    System.out.println("\nPresione Enter para continuar...");
                    sc.nextLine();
                break;

            case 3:
                System.out.println("--- MOSTRAR EMPLEADOS ---");
                empresa.mostrarDatosLista();
                    System.out.println("\nPresione Enter para continuar...");
                    sc.nextLine();
                break;

            case 4:
                System.out.println("--- BORRAR EMPLEADO ---");
                System.out.println("Ingrese DNI: ");
                int dniBorrar = sc.nextInt();
                empresa.eliminarEmpleado(dniBorrar);
                    System.out.println("\nPresione Enter para continuar...");
                    sc.nextLine();
                break;

            case 5:
                System.out.println("Ingrese DNI del empleado: ");
                int dniEmpCat = sc.nextInt();
                sc.nextLine();
                Empleado empCat = empresa.obtenerEmpleadoPorDni(dniEmpCat);
                if (empCat != null) {
                    System.out.println("Categorias existentes: JUNIOR - SEMI_SENIOR - SENIOR");
                    System.out.println("Ingrese categoria: ");
                    String cambiarCategoria = sc.nextLine();
                    try {
                        Categoria cambiarCatEmp = Categoria.valueOf(cambiarCategoria.toUpperCase().replace(" ", "_").replace("-", "_").replace("  ", "_"));
                        empCat.setCategoriaEmp(cambiarCatEmp);
                        System.out.println("Categoria cambiada con exito.");
                    } catch (Exception e) {
                        System.out.println("Error, vuelva a intentarlo");
                    }

                }else{
                    System.out.println("No existe un empleado con el dni ingresado...");
                }
                    System.out.println("\nPresione Enter para continuar...");
                    sc.nextLine();
                break;

            case 6:

                System.out.println("--- MENU AUMENTAR SUELDO ---");
                System.out.println("1. Aumento general");
                System.out.println("2. Aumento a empleado especifico");
                
                System.out.println("Elige una opcion: ");
                subOption = sc.nextInt();
                sc.nextLine();

                if (subOption == 1) {
                    System.out.println("--- AUMENTO GENERAL ---");
                    System.out.println("ACLARACION: debera ingresar numero decimal sin signo porcentaje (%)  ");
                    System.out.println("Ingrese aumento: ");
                    double aumentoGeneral = sc.nextDouble();
                    sc.nextLine();
                    empresa.aumentoGeneral(aumentoGeneral);  
                    System.out.println("Salarios aumentados con exito.");
                }
                else if(subOption == 2){
                    System.out.println("--- AUMENTO EMPLEADO ESPECIFICO ---");
                    System.out.println("ACLARACION: debera ingresar DNI del empleado y numero decimal sin signo porcentaje (%)  ");

                    System.out.println("Ingrese DNI del empleado: ");
                    int dniEmp = sc.nextInt();
                    sc.nextLine();
                    Empleado emp = empresa.obtenerEmpleadoPorDni(dniEmp);
                    if (emp != null) {
                        System.out.println("Ingrese aumento: ");
                        double aumentoEsp = sc.nextDouble();
                        sc.nextLine();
                        double salarioActual = emp.getSalario();
                        double aumento = salarioActual + (salarioActual * aumentoEsp/100);
                        emp.setSalario(aumento); 
                        System.out.println("Salario aumentado con exito.");
                    }else{
                        System.out.println("ERROR - no se encontro empleado con ese dni.");
                    }

                }else{
                    System.out.println("opcion no valida...");
                }
                    System.out.println("\nPresione Enter para continuar...");
                    sc.nextLine();
                 break;
                    
            case 7:
                try {
                    ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(archivo));
                    escribiendoFichero.writeObject(empresa);
                    escribiendoFichero.close();
                    System.out.println("¡Guardado con éxito! Saliendo...");
                } catch (Exception e) {
                    System.out.println("Error al guardar: " + e.getMessage());
                }
                break; 

            default:
                if (option != 7) { 
                    System.out.println("Opción no válida.");
                }
                    System.out.println("\nPresione Enter para continuar...");
                    sc.nextLine();
                break;
        } 

    } while (option != 7); 
    
    sc.close();
    System.out.println("Programa finalizado.");
    }
}
