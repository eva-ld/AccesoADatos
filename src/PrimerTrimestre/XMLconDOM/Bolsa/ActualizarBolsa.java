package PrimerTrimestre.XMLconDOM.Bolsa;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ActualizarBolsa {

    public static void main(String[] args) {

        final String RUTA_FICHERO = "C:\\Users\\evalo\\Desktop\\Programación\\AccesoDatos\\src\\XMLconDOM_Datos\\";

        try {
            // Leer los archivos XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document bolsa = builder.parse(new File(RUTA_FICHERO + "bolsa.xml"));
            Document datos = builder.parse(new File(RUTA_FICHERO + "datos.xml"));

            Element raiz = bolsa.getDocumentElement();

            // Recorrer las operaciones
            NodeList operaciones = datos.getElementsByTagName("Operacion");

            for (int i = 0; i < operaciones.getLength(); i++) {

                Element op = (Element) operaciones.item(i);

                String tipo = op.getAttribute("id");
                String nombre = op.getElementsByTagName("Nombre").item(0).getTextContent();

                Element empresa = buscarEmpresa(raiz, nombre);

                // Realizar las operaciones
                switch (tipo) {

                    case "A":

                        if (empresa != null) {
                            System.out.println("Error: La empresa ya existe");
                        } else {

                            // Crear la empresa
                            Element nueva = bolsa.createElement("Empresa");

                            nueva.setAttribute("indice",
                                    op.getElementsByTagName("Indice").item(0).getTextContent());

                            // Crear el nombre
                            Element nom = bolsa.createElement("Nombre");
                            nom.setTextContent(nombre);
                            nueva.appendChild(nom);

                            // Crear el simbolo
                            Element sim = bolsa.createElement("Simbolo");
                            sim.setTextContent(
                                    op.getElementsByTagName("Simbolo").item(0).getTextContent());
                            nueva.appendChild(sim);

                            // Crear el precio
                            Element pre = bolsa.createElement("Precio");
                            pre.setTextContent(
                                    op.getElementsByTagName("Precio").item(0).getTextContent());
                            nueva.appendChild(pre);

                            // Añadir la empresa a Bolsa
                            raiz.appendChild(nueva);

                            System.out.println("Empresa añadida: " + nombre);
                        }

                        break;

                    case "B":

                        if (empresa == null) {
                            System.out.println("Error: La empresa no existe");
                        } else {
                            raiz.removeChild(empresa); // Eliminar la empresa
                            System.out.println("Empresa eliminada: " + nombre);
                        }

                        break;

                    case "M":

                        if (empresa == null) {
                            System.out.println("Error: La empresa no existe");
                        } else {
                            Element precio = (Element) empresa.getElementsByTagName("Precio").item(0);// Modificar el precio
                            String nuevoPrecio = op.getElementsByTagName("Precio").item(0).getTextContent();

                            precio.setTextContent(nuevoPrecio);
                            System.out.println("Precio modificado: " + nombre);
                        }
                        break;

                    default:
                        System.out.println("Operacion desconocida");
                }
            }

            // Guardar el nuevo XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            DOMSource source = new DOMSource(bolsa);
            StreamResult result = new StreamResult(new File(RUTA_FICHERO + "bolsaactualizada.xml"));

            transformer.transform(source, result);
            System.out.println("Documento XML creado correctamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Metodo para buscar una empresa por su nombre
    public static Element buscarEmpresa(Element raiz, String nombre) {

        NodeList empresas = raiz.getElementsByTagName("Empresa");

        for (int i = 0; i < empresas.getLength(); i++) {
            Element empresa = (Element) empresas.item(i);

            String nombreEmpresa = empresa.getElementsByTagName("Nombre").item(0).getTextContent();

            if (nombreEmpresa.trim().equalsIgnoreCase(nombre.trim())) {
                return empresa;
            }
        }
        return null;
    }
}
