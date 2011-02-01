package com.example.reclaadmin;

import java.io.FileOutputStream;
import java.io.InputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.StreamResource.StreamSource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class PDFGenerator implements StreamSource {
	
    ReclaadminApplication __app;

    
    /*
     * Constructor
     */
    public PDFGenerator() 
    {

    }

 
  
    /*
     * open PDF in Modal window, but a down load window show off
     * Not working for the moment
     */
    public void openPDFWindow()
    {
		Window window = new Window();
        ((VerticalLayout) window.getContent()).setSizeFull();
        window.setResizable(true);
        window.setWidth("800");
        window.setHeight("600");
        window.center();
        Embedded e = new Embedded();
        e.setSizeFull();
        e.setType(Embedded.TYPE_BROWSER);

     /*
      * Here we create a new StreamResource to get our PDF
      * Not working for the moment
      * __this__ must be replaced with a StreamSource
      * 
      */
        StreamResource resource = new StreamResource(this, "/home/matrix/Desktop/pv.pdf", __app);
        
        // Set the right MIME type
        resource.setMIMEType(resource.getMIMEType());
        e.setSource(new ClassResource(resource.getFilename(), __app));
        window.addComponent(e);
        __app.getMainWindow().addWindow(window);
    }
    
    
    public void savePDF()
    {
    	Embedded e = new Embedded();
        e.setSizeFull();
        e.setType(Embedded.TYPE_BROWSER);
     
        /*
         *  Here we create a new StreamResource to get our PDF
         *  Not working for the moment
         *  __this__ must be replaced with a StreamSource
         *  
         */
        StreamResource resource = new StreamResource(this, "/home/matrix/Desktop/pv.pdf", __app);
        
        // Set the right MIME type
        resource.setMIMEType(resource.getMIMEType());
        e.setSource(new ClassResource(resource.getFilename(), __app));
        __app.getMainWindow().addComponent(e);
    }

    
    /*
     * generate a PDF_version_1_6
     */
    public int generate(ReclaadminApplication application)
    {
    	__app = application;
    	
        Document document = null;

        try 
        {
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/matrix/Desktop/pv.pdf"));
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_6);
            //open document for writing
            document.open();
            Image _logo = Image.getInstance("/home/matrix/apps/workspace/reclaadmin/WebContent/VAADIN/themes/reindeer/layouts/images/logo.gif");
            Image logo = Image.getInstance("/home/matrix/apps/workspace/reclaadmin/WebContent/VAADIN/themes/reindeer/layouts/images/slogon.gif");
            
            _logo.setAlignment(Image.MIDDLE);
            _logo.scalePercent(50);
            
            logo.setAlignment(Image.MIDDLE);
            logo.scalePercent(50);
            
            document.add(_logo);
            document.add(logo);
            
            document.add(new Paragraph("--Header--"));
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        //closing the document
        document.close();
    	
    	return 0;
    }


	@Override
	public InputStream getStream() {
		
		return null;
		
	}
    
 
}
