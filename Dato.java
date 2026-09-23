package aplicacion.dato;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aplicacion.utiles.Auto;
import aplicacion.utiles.Cliente;
import aplicacion.utiles.Conductor;
import aplicacion.utiles.Moto;
import aplicacion.utiles.Vehiculo;
//import aplicacion.utiles.Viaje;

public class Dato {
	
	public static List <Vehiculo> cargarVehiculos(String fileName) throws  FileNotFoundException  {
	    
		File archivo = new File(fileName);
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
	    try (Scanner lector = new Scanner(archivo)) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine(); 
	            String[] partes = linea.split(";");
	            
	            if (partes.length >= 3) {
	            String tipo = partes[0].trim();
	            String patente = partes[1].trim();
	            String modelo = partes[2].trim();

	            Vehiculo vehiculo=null;
	            if (tipo.equals("Auto")) {
	                vehiculo = new Auto(patente,modelo);
	            } else if (tipo.equals("Moto")) {
	                vehiculo = new Moto(patente,modelo);
	            } 
	            if (vehiculo != null) {
                    vehiculos.add(vehiculo);
                } else {
                    System.out.println("Tipo de vehículo desconoasdasdawdasdawgdagshdaghwghascido: " + tipo);
                }
	        }
	    }    
	}catch (FileNotFoundException e) {
        System.out.println(" EMOJI EMOJI No se encontró el archivo especificado: " + fileName);
        throw e;
    }
	    return vehiculos;
	}
    /**
     * Carga los conductores desde un archivo de texto.
     * Formato de cada línea: nombre;documento;telefono;email,disponible,vehiculo
     * @return lista
     */
    public static List <Conductor> cargarConductores(String fileName,List<Vehiculo> vehiculos)
            throws FileNotFoundException {
    	File archivo = new File(fileName);
        List <Conductor> conductores = new ArrayList <Conductor>();
        try (Scanner lector = new Scanner(archivo)) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                
                // 2. Separar los datos de la línea por comas (o el separador que uses)
                String[] partes = linea.split(";"); 
                
                if (partes.length >= 6) { // Validar que la línea tenga los datos completos
                    String nombre = partes[0].trim();
                    String documento = partes[1].trim();
                    String telefono = partes[2].trim();
                    String email=partes[3].trim();
                    Boolean disponible=Boolean.parseBoolean(partes[4].trim());
                    String patenteVehiculo = partes[5].trim();


                    Vehiculo vehiculo = buscarPorPatente(patenteVehiculo, vehiculos);
                    if (vehiculo == null) {
                        System.out.println("Vehículo no encontrado para patente: " + patenteVehiculo);
                        continue;
                    }

                    // 3. Crear el objeto conductor
                    Conductor nuevoConductor = new Conductor(nombre,documento,telefono,email,disponible,vehiculo); // (Ajusta según tu constructor de Libro)
                    
                    // 4. Guardar en la lista
                    conductores.add(nuevoConductor);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo especificado: " + fileName);
            throw e;
        }
             
        return conductores;
    }
    private static Vehiculo buscarPorPatente(String patente, List<Vehiculo> vehiculos) {
        for (Vehiculo v : vehiculos) {
            if (v.getPatente().equals(patente)) {
                return v;
            }
        }
        return null;
    }
    
    /* Carga los clintes desde un archivo de texto.
    * Formato de cada línea: nombre;documento;telefono;email
    * @return lista
    */
    
   public static List <Cliente> cargarCliente(String fileName)throws FileNotFoundException {
   	File archivo = new File(fileName);
       List <Cliente> clientes = new ArrayList <Cliente>();
       try (Scanner lector = new Scanner(archivo)) {
           while (lector.hasNextLine()) {
               String linea = lector.nextLine();
               
               // 2. Separar los datos de la línea por comas (o el separador que uses)
               String[] partes = linea.split(";"); 
               
               if (partes.length >= 3) { // Validar que la línea tenga los datos completos
                   String nombre = partes[0].trim();
                   String documento = partes[1].trim();
                   String telefono = partes[2].trim();
                   String email=partes[3].trim();
                 

                   // 3. Crear el objeto clientes
                   Cliente nuevoConductor = new Cliente(nombre,documento,telefono,email); // (Ajusta según tu constructor de Libro)
                   
                   // 4. Guardar en la lista
                   clientes.add(nuevoConductor);
               }
           }
       } catch (FileNotFoundException e) {
           System.out.println("No se encontró el archivo especificado: " + fileName);
           throw e; // 
       }
            
       return clientes;
   }
             
}
