package XMLconDOM.Zoo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

public class ZooD {
    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\XMLconDOM_Datos\\";
    private static final String NOMBRE_FICHERO = "Zoo.xml";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        try {
            // Cargar el documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));
            NodeList listaFelinos = documento.getElementsByTagName("Felino");

            // Bucle do-while para mostrar el menú hasta que salga
            do {
                System.out.println("\n¿Qué origen buscas?");
                System.out.println("    1. África");
                System.out.println("    2. Europa");
                System.out.println("    3. Asia");
                System.out.println("    4. América");
                System.out.println("    5. Salir");
                opcion = sc.nextInt();

                String origenBUscado = "";
                boolean mostrarResultados = true;

                // Elegir la opcion con un switch
                switch (opcion) {
                    case 1:
                        origenBUscado = "Africa";
                        break;
                    case 2:
                        origenBUscado = "Europa";
                        break;
                    case 3:
                        origenBUscado = "Asia";
                        break;
                    case 4:
                        origenBUscado = "America";
                        break;
                    case 5:
                        System.out.println("SALIENDO DEL PROGRAMA");
                        mostrarResultados = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Introduce un número del 1 al 5.");
                        mostrarResultados = false;
                        break;
                }

                // 4. Si se eligió un continente válido, filtramos los animales
                if (mostrarResultados) {
                    System.out.println("\n      Animales con origen en: " + origenBUscado );
                    boolean encontrado = false;

                    for (int i = 0; i < listaFelinos.getLength(); i++) {
                        Element felino = (Element) listaFelinos.item(i);

                        String nombre = felino.getElementsByTagName("Nombre").item(0).getTextContent();
                        String origen = felino.getElementsByTagName("Origen").item(0).getTextContent();
                        String peso = felino.getElementsByTagName("Peso").item(0).getTextContent();

                        // Comprobar si coincide el origen
                        if (origen.equalsIgnoreCase(origenBUscado)) {
                            System.out.println("    Animal: " + nombre + " | Peso: " + peso + " kg");
                            encontrado = true;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("No se encontraron animales para este origen.");
                    }
                }

            } while (opcion != 5);

        } catch (Exception e) {
            System.out.println("Error al leer el documento XML: " + e.getMessage());
        }

        // Cerramos el scanner al terminar
        sc.close();
    }
}