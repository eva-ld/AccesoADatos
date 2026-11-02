package PrimerTrimestre.XMLconDOM.Zoo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ZooB {
    // Como el fichero xml ya esta creado usamos la misma ruta que en ZooA, IMPORTANTE CON \\ AL FINAL
    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\PrimerTrimestre\\XMLconDOM_Datos\\";
    private static final String NOMBRE_FICHERO = "Zoo.xml";

    public static void main(String[] args) {
        try {
            // Configurar el analizador DOM (ignorando comentarios y espacios vacíos)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);

            // Creo el aanalizador y cargar el documento XML existente en memoria
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(RUTA_FICHERO + NOMBRE_FICHERO));

            // Obtener todos los elementos <felino>
            NodeList listaFelinos = documento.getElementsByTagName("Felino");

            System.out.println("FELINOS QUE PESAN MÁS DE 100 KG \n");

            // Recorrer la colección de nodos con un bucle for
            for (int i = 0; i < listaFelinos.getLength(); i++) {
                // Convertir el nodo actual a un objeto Element
                Element felino = (Element) listaFelinos.item(i);

                // Extraer el texto de las etiquetas
                String nombre = felino.getElementsByTagName("Nombre").item(0).getTextContent();
                String origen = felino.getElementsByTagName("Origen").item(0).getTextContent();
                String pesoStr = felino.getElementsByTagName("Peso").item(0).getTextContent();

                // Como el peso viene como texto (String), lo convertimos a número entero (int)
                int peso = Integer.parseInt(pesoStr);

                // Condición > 100 kg
                if (peso > 100) {
                    System.out.println("    - Felino: " + nombre);
                    System.out.println("    - Origen: " + origen);
                    System.out.println("    - Peso: " + peso + " kg");
                    System.out.println(" ");
                }
            }

        } catch (Exception e) {
            System.out.println("Error al leer el documento XML: " + e.getMessage());
        }
    }
}
