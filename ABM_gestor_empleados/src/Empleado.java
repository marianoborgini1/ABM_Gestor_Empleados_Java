import java.io.Serializable;
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int dni;
    private double salario;
    private Categoria categoriaEmp;
    

    public Empleado(String nombre, int dni, double salario, Categoria categoriaEmp){
        this.nombre = nombre;
        this.dni = dni;
        this.salario = salario;
        this.categoriaEmp = categoriaEmp;
    }

    public void mostrarDatos(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Dni: " + dni);
        System.out.println("Salario: " + salario);
        System.out.println("Categoria: " + categoriaEmp);
        System.out.println("-----------------");

    }
        
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setDni(int dni){
        this.dni = dni;
    }

    public int getDni(){
        return dni;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

    public double getSalario(){
        return salario;
    }

    public void setCategoriaEmp(Categoria categoriaEmp){
        this.categoriaEmp = categoriaEmp;
    }

    public Categoria getCategoriaEmp(){
        return categoriaEmp;
    }

}
