package service.storage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import service.beans.Grupo;
import service.beans.Profesor;

public class GrupoStore {
    private static String[][] data =
  {{"1","1","ADM-001","Elementos de Administración","K,J 7:00-9:00am","A01","303330333"},
     {"2","1","ECN-001","Principios de Economía","L,M 9:00-11:00am","B02","101110111"},
     {"3","2","MAT-001","Matematica I","K,J 1:00-3:00pm","A02","202220222"},
     {"4","1","ADM-101","Administración Avanzada","K,J 13:00-15:00am","A01","303330333"},
     {"5","2","ECN-101","Economía I","K,J 9:00-11:00am","B02","101110111"},
     {"6","2","MAT-102","Matematica II","K,J 14:00-16:00pm","A02","202220222"}};

    private static Map<String,Grupo> store;
    private static GrupoStore instance = null;
    private GrupoStore() {
        store = new HashMap<String,Grupo>();
        initStore();
    }
    public static Map<String,Grupo> getStore() {
        if(instance==null) {
            instance = new GrupoStore();
        }
        return store;
    }
    private void initStore() {
        Map profs = ProfesorStore.getStore();
        for (int i=0;i<data.length;i++) {
            Grupo grupo = new Grupo();
          grupo.setId(data[i][0]);
            grupo.setNumero(data[i][1]);
            grupo.setSigla(data[i][2]);
            grupo.setDescripcion(data[i][3]);
            grupo.setHorario(data[i][4]);
            grupo.setAula(data[i][5]);
            store.put(grupo.getId(),grupo);
            Profesor prof = (Profesor)profs.get(data[i][6]);
            if (prof!=null) {
              List grupos = prof.getGrupos();
              grupos.add(grupo);
            }
        }
    }
}