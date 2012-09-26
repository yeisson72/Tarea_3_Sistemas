package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import service.beans.Grupo;
import service.beans.Profesor;

public class ProfesorStore {
    private static String[][] data = {
    {"1","101110111","Economia","Carlos Perez","Licenciado","carlos@xyz.com","3456-7890"},
    {"2","202220222","Matematica","Luis Torres","Master","luis@xyz.com","6677-3456"},
    {"3","303330333","Administracion","Juan Castro","Licenciado","castro@xyz.com","67455-7788"},
    {"4","404440444","Quimica","Joaquin Soto","Ingeniero","joaquin@xyz.com","3333-7890"}};
    private static Map<String,Profesor> store;
    private static ProfesorStore instance = null;
    private ProfesorStore() {
        store = new HashMap<String,Profesor>();
        initStore();
    }
    public static Map<String,Profesor> getStore() {
        if(instance==null) {
            instance = new ProfesorStore();
            GrupoStore.getStore();
        }
        return store;
    }
    private void initStore() {
        for (int i=0;i<data.length;i++) {
            Profesor prof = new Profesor();
            prof.setId(data[i][0]);
          prof.setCedula(data[i][1]);
            prof.setArea(data[i][2]);
            prof.setNombre(data[i][3]);
            prof.setTitulo(data[i][4]);
            prof.setCorreo(data[i][5]);
            prof.setTelefono(data[i][6]);
            store.put(prof.getId(),prof);
        }
    }
}