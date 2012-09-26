package service.beans;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profesor {
  private String id;
  private String cedula;
  private String nombre;
  private String titulo;
  private String area;
  private String correo;
  private String telefono;
  private List<Grupo> grupos;

  public Profesor() {
    grupos = new ArrayList<Grupo>();
  }
  public Profesor(String id, String cedula, String nombre,
                    String titulo, String area, String correo,
                    String telefono, List<Grupo> grupos) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.nombre = nombre;
        this.titulo  = titulo;
        this.area = area;
        this.correo = correo;
        this.telefono = telefono;
        this.grupos = grupos;
    }
  public void setId(String id) {this.id = id;}
  public void setCedula(String ced) {this.cedula = ced;}
  public void setNombre(String nomb) {this.nombre = nomb;}
  public void setTitulo(String tit) {this.titulo = tit;}
  public void setArea(String area) {this.area = area;}
  public void setCorreo(String corr) {this.correo = corr;}
  public void setTelefono(String tel) {this.telefono = tel;}
  public String getId() { return id; }
  public String getCedula() { return cedula; }
  public String getNombre() { return nombre; }
  public String getTitulo() { return titulo; }
  public String getArea() { return area; }
  public String getCorreo() { return correo; }
  public String getTelefono() { return telefono; }
  @XmlElement(name="grupo")
  public List<Grupo> getGrupos() { return grupos; }
  public void setGrupos(List<Grupo> grupos) { this.grupos = grupos;}
}