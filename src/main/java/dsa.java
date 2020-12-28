import org.docx4j.XmlUtils;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;

import java.io.File;

public class dsa {
    public static void main(String[] args) throws Exception {

        // The input would generally be an XHTML document,
        // but for convenience, this sample can convert a
        // docx to XHTML first (ie round trip).
        String inputfilepath = System.getProperty("user.dir") + "/new 3.html";

        // Create an empty docx package
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

        NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
        wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
        ndp.unmarshalDefaultNumbering();


        XHTMLImporterImpl xHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
        xHTMLImporter.setHyperlinkStyle("Hyperlink");


        if (inputfilepath.endsWith("html")) {

            // Convert the XHTML, and add it into the empty docx we made
            wordMLPackage.getMainDocumentPart().getContent().addAll(
                    xHTMLImporter.convert(new File(inputfilepath), null) );

        }


        System.out.println(XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(), true, true));


        wordMLPackage.save(new java.io.File(System.getProperty("user.dir") + "/html_output.docx") );
        String s=wordMLPackage.getMainDocumentPart().getXML();
        P p = (P)wordMLPackage.getMainDocumentPart().getJaxbElement().getBody().getContent().get(1);
        R r = (R)p.getContent().get(0);
        Text t = (Text)r.getContent().get(0);

        //a.getClass().toString();
        //wordMLPackage.getMainDocumentPart().;
        if(wordMLPackage.getMainDocumentPart().getJaxbElement().getBody().getContent().get(2).getClass()==P.class)
        {
            System.out.printf("yes");
        }
        else
        {
            System.out.printf("No");
        }


    }
}
