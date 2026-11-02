package PrimerTrimestre.XMLconDOM.Zoo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ZooC {
    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\XMLconDOM_Datos\\";

    public static void main(String[] args) {
        try {
            // Configurar y cargar el documento original Zoo.xml
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(RUTA_FICHERO + "Zoo.xml"));

            // Obtener todos los elementos <Felino>
            NodeList listaFelinos = documento.getElementsByTagName("Felino");

            System.out.println("=== FELINOS CON PESO DISMINUIDO EN 5 KG ===");

            // Recorrer la lista de los felinos

            for (int i = 0; i < listaFelinos.getLength(); i++) {
                Element felino = (Element) listaFelinos.item(i);

                String nombre = felino.getElementsByTagName("Nombre").item(0).getTextContent();
                String origen = felino.getElementsByTagName("Origen").item(0).getTextContent();

                // Obtener el nodo y su valor de peso
                Element nodoPeso = (Element) felino.getElementsByTagName("Peso").item(0);
                int pesoActual = Integer.parseInt(nodoPeso.getTextContent());

                // Disminuir el peso en 5 kg
                int nuevoPeso = pesoActual - 5;

                // Actualizar el contenido del nodo en memoria
                nodoPeso.setTextContent(String.valueOf(nuevoPeso));

                // Mostrar el resultado por pantalla
                System.out.println("Nombre: " + nombre + " | Origen: " + origen +  " | Peso antiguo: " + pesoActual + " kg" + " | Nuevo Peso: " + nuevoPeso + " kg");
            }

        // Guardar los cambios en el nuevo fichero xml zoo2.xml
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(documento), new StreamResult(new File(RUTA_FICHERO + "Zoo2.xml")));

            System.out.println("\nFichero Zoo2.xml creado y guardado correctamente!!!!");

        } catch (Exception e) {
            System.out.println("Error al procesar el documento XML: " + e.getMessage());
        }
    }
}
