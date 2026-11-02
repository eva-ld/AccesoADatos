package PrimerTrimestre.XMLconDOM.Zoo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ZooA {
    private static final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\XMLconDOM_Datos\\"; //Ruta para que el fichero xml se cree dentro
    // IMPORTANTE PONER LAS \\ AL FINAL DE LA RUTA PARA QUE NO DE PROBLEMA
    private static final String NOMBRE_FICHERO = "Zoo.xml"; //Nombre del documento xml

    static void main(String[] args) {
        try {
            // Asegurarse de que el directorio de destino existe
            File directorio = new File(RUTA_FICHERO);
            if (!directorio.exists()) {
                directorio.mkdir();
            }

            // Configurar y crear el documento XML vacío en memoria
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            // Crear el elemento raíz <Zoo>
            Element raizZoo = documento.createElement("Zoo");
            documento.appendChild(raizZoo);

            // Crear el contenedor <Felinos> y colgarlo de <Zoo>
            Element felinos = documento.createElement("Felinos");
            raizZoo.appendChild(felinos);

            // Datos de los felinos
            String[] nombres = {"Leon", "Tigre", "Lince", "PanteraTigris"};
            String[] origenes = {"Africa", "Asia", "Europa", "America"};
            String[] pesos = {"155", "215", "20", "95"};

            // Recorrer los datos para crear cada  <Felino> y sus hijos
            for (int i = 0; i < nombres.length; i++) {
                Element felino = documento.createElement("Felino");
                felinos.appendChild(felino);

                // Crear <Nombre>
                Element nombre = documento.createElement("Nombre");
                nombre.setTextContent(nombres[i]);
                felino.appendChild(nombre);

                // Crear <Origen>
                Element origen = documento.createElement("Origen");
                origen.setTextContent(origenes[i]);
                felino.appendChild(origen);

                // Crear <Peso>
                Element peso = documento.createElement("Peso");
                peso.setTextContent(pesos[i]);
                felino.appendChild(peso);
            }

            // Guardar el árbol DOM generado en memoria a un fichero físico en disco
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(new DOMSource(documento), new StreamResult(new File(RUTA_FICHERO + NOMBRE_FICHERO)));

            System.out.println("Fichero Zoo.xml creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al crear el documento XML.");
        }
    }
}
