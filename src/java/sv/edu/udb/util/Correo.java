package sv.edu.udb.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Correo {

    private String usuario;
    private String clave;
    private String destinatario;
    private String asunto;
    private String mensaje;
    private String rutaAdjunto;
    private String nombreAdjunto;
    
    
    public Correo() {
        this.usuario = "nerolovo@gmail.com";
        this.clave = "DMaad249";
        this.destinatario = "";
        this.asunto = "";
        this.mensaje = "";
        this.rutaAdjunto = "";
        this.nombreAdjunto = "";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRutaAdjunto() {
        return rutaAdjunto;
    }

    public void setRutaAdjunto(String rutaAdjunto) {
        this.rutaAdjunto = rutaAdjunto;
    }

    public String getNombreAdjunto() {
        return nombreAdjunto;
    }

    public void setNombreAdjunto(String nombreAdjunto) {
        this.nombreAdjunto = nombreAdjunto;
    }
    
    
    public boolean enviarCorreo(){
      
        try{
            //creando un nuevo objeto de tipo Properties
            Properties p = new Properties();
            //definiendo el servidor SMTP
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            //definiendo el usuario emisor del correo
            p.setProperty("mail.smtp.user", usuario);
            p.setProperty("mail.smtp.auth", "true");
            
            //estableciendo la sesion con el servidor SMTP
            Session sesion = Session.getDefaultInstance(p, null);
            //definiendo el cuerpo del correo
            MimeMultipart cuerpo = new MimeMultipart();
            //definiendo el texto del correo
            BodyPart texto = new MimeBodyPart();
            //si se envia texto plano basta con: texto.setText(mensaje);
            //si quiero formatear con HTML entonces...
            texto.setContent(mensaje, "text/html");
            //le agrego el texto al cuerpo del correo
            
            cuerpo.addBodyPart(texto);
            
            //si hay un adjunto entonces
            if(!rutaAdjunto.isEmpty()){
                //creo un nuevo BodyPara los adjuntos
                BodyPart adjunto = new MimeBodyPart();
                //especifico la ubicacion del adjunto
                adjunto.setDataHandler(new DataHandler(new FileDataSource(rutaAdjunto)));
                //especifico el nombre que se le dara al adjunto
                adjunto.setFileName(nombreAdjunto);
                //agrego el adjunto el cuerpo del correo
                cuerpo.addBodyPart(adjunto);           
            }
            //creo el objeto mimemessaje el cual representa el mensaje del correo completo
            MimeMessage correo = new MimeMessage(sesion);
            //defino el remitente
            correo.setFrom(new InternetAddress(usuario));
            //defino el destinatario
            correo.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            //defino el asunto del correo
            correo.setSubject(asunto);
            //especifico el cuerpo del correo
            correo.setContent(cuerpo);
            
            //enviando el mensaje
            Transport t = sesion.getTransport("smtp");
            t.connect(usuario, clave);
            t.sendMessage(correo, correo.getAllRecipients());
            t.close();
            
            return true;
        }catch(MessagingException ex){
          Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
          return false;
        }
    }
    
}
