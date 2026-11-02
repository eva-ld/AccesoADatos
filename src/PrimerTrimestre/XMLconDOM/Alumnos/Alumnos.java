package PrimerTrimestre.XMLconDOM.Alumnos;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Alumnos {
    public static void main(String[] args) {

        try {
            // Leer el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document alumnos = builder.parse(new File("C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\XMLconDOM_Datos\\alumnos.xml"));

            Element raiz = alumnos.getDocumentElement(); // Obtener la raiz
            NodeList listaAlumnos = raiz.getElementsByTagName("alumno"); // Obtener todos los alumnos

            // Crear un nuevo documento XML
            Document notas = builder.newDocument();
            Element nuevaRaiz = notas.createElement("notas");
            nuevaRaiz.setAttribute("modulo", "Acceso a datos");
            notas.appendChild(nuevaRaiz);

            double suma = 0;

            // Recorrer los alumnos
            for (int i = 0; i < listaAlumnos.getLength(); i++) {

                Element alumno = (Element) listaAlumnos.item(i);

                String dni = alumno.getAttribute("dni");

                double practica1 = Double.parseDouble(alumno.getElementsByTagName("practica1").item(0).getTextContent());

                double practica2 = Double.parseDouble(alumno.getElementsByTagName("practica2").item(0).getTextContent());

                double examen = Double.parseDouble(alumno.getElementsByTagName("examen").item(0).getTextContent());

                double practicaFinal = Double.parseDouble(alumno.getElementsByTagName("final").item(0).getTextContent());

                // Calcular la media
                double practicas = (practica1 + practica2) / 2;

                double media = (practicas * 0.30) + (examen * 0.40) + (practicaFinal * 0.30);
                suma += media;

                System.out.println("El alumno con DNI: "+ dni + " tiene una nota media de " + media);

                // Añadir el alumno al nuevo XML
                Element nuevoAlumno = notas.createElement("alumno");
                nuevoAlumno.setAttribute("dni", dni);

                Element nota = notas.createElement("media");
                nota.setTextContent(String.valueOf(media));

                nuevoAlumno.appendChild(nota);
                nuevaRaiz.appendChild(nuevoAlumno);
            }

            // Calcular la media de la clase
            double mediaTotal = suma / listaAlumnos.getLength();

            System.out.println("Media total del módulo Acceso a datos = " + mediaTotal);

            // Añadir la media total al XML
            Element total = notas.createElement("mediaTotal");
            total.setTextContent(String.valueOf(mediaTotal));
            nuevaRaiz.appendChild(total);
            Transformer transformer = TransformerFactory.newInstance().newTransformer(); // Guardar el nuevo XML

            DOMSource source = new DOMSource(notas);
            StreamResult result = new StreamResult(new File("notas.xml"));

            transformer.transform(source, result);
            System.out.println("Documento XML creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
