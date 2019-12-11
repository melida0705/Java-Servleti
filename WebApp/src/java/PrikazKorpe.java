 
import java.io.*;
import java.util.ArrayList;
import javax.servlet.*; 
import javax.servlet.http.*; 
 
public class PrikazKorpe extends HttpServlet {   
    //metoda za obradu i HTTP GET i HTTP POST zahteva   
    protected void processRequest(HttpServletRequest request,  
            HttpServletResponse response)   
            throws ServletException, IOException { 
 HttpSession sesija = request.getSession();  
 ArrayList<String> prethodniProizvodi =    
         (ArrayList<String>) sesija.getAttribute("prethodniArtikli"); 
 if (prethodniProizvodi == null) {  
     prethodniProizvodi = new ArrayList<String>();       
     sesija.setAttribute("prethodniArtikli", prethodniProizvodi);   
 }    
 String novi = request.getParameter("noviProizvod");    
 if ((novi != null)        
         && (!novi.trim().equals("")))
 {     
     prethodniProizvodi.add(novi);  
 }   
         response.setContentType("text/html;charset=UTF-8");    
         PrintWriter out = response.getWriter();    
         
       
         String naslov = "Naruceni proizvodi:";   
         String docType =          
                 "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " 
                 + "Transitional//EN\">\n";   
         out.println(docType         
                 + "<html>\n"       
                 + "<head><title>"
                 + naslov 
                 + "</title></head>\n"  
                 + "<body bgcolor=\"#FDF5E6\">\n"    
                 + "<h1>" + naslov + "</h1>"); 
         if (prethodniProizvodi.isEmpty()) 
         {   
             out.println("<i>Nema nijednog artikla.</i>");   
         } else {     
             out.println("<ul>");     
             for (String stavka : prethodniProizvodi) {   
                 out.println("  <li>" + stavka);     
             }       out.println("</ul>");    
         }     out.println("<br/>");  
         out.println("<a href=\"index.html\"> Nazad na kupovinu </a>");    
         out.println("</body></html>");  
    }    
       
    
 
    
    
    protected void doGet(HttpServletRequest request,   
            HttpServletResponse response)   
            throws ServletException, IOException {     
        processRequest(request, response);   
    }  
 
    protected void doPost(HttpServletRequest request,   
            HttpServletResponse response)   
            throws ServletException, IOException {    
        processRequest(request, response); 
    } 
 
    public String getServletInfo() {      
        return "Short description";    
    } 
} 
 
 
 
 