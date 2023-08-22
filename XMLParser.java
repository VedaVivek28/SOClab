import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
    public static void main(String[] args) {
        try {
            // Load the XML file
            File inputFile = new File("booking.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            // Normalize the XML structure
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("booking");
            String[][] tableData = new String[nodeList.getLength()][5];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // Create table headers
                    Element bookingElement = (Element) nodeList.item(i);
                    String name = bookingElement.getElementsByTagName("userName").item(0).getTextContent();
                    String movieName = bookingElement.getElementsByTagName("mviName").item(0).getTextContent();
                    String movieDate = bookingElement.getElementsByTagName("mviDate").item(0).getTextContent();
                    String seats = bookingElement.getElementsByTagName("seats").item(0).getTextContent();
                    String classType = bookingElement.getElementsByTagName("classofseat").item(0).getTextContent();
                    tableData[i][0] = name;
                    tableData[i][1] = movieName;
                    tableData[i][2] = movieDate;
                    tableData[i][3] = seats;
                    tableData[i][4] = classType;
                }
            }
            generateHTMLTable(tableData,"table.html");
            System.out.println("HTML table generated successfully.");
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void generateHTMLTable(String[][] tableData, String outputFilePath) 
    {
       try 
        {
            
            FileWriter writer = new FileWriter(outputFilePath);

            writer.write("<html><body>");
            writer.write("<table border=\"1\">");

            // Generate table header
            writer.write("<tr>");
            writer.write("<th>UserName</th>");
            writer.write("<th>MovieName</th>");
            writer.write("<th>MovieDate</th>");
            writer.write("<th>seats</th>");
            writer.write("<th>classType</th>");
            writer.write("</tr>");

            
            // Generate table rows
            for (String[] row : tableData) 
            {
                writer.write("<tr>");
                for (String cell : row) 
                {
                    writer.write("<td>" + cell + "</td>");
                }
                writer.write("</tr>");
            }

            writer.write("</table>");
            writer.write("</body></html>");

            writer.close();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}

