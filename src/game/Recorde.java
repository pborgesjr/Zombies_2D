package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 *
 * @author Pedro Jr
 */
public class Recorde {

    private final String arquivo = "recordes.dat";
    int pontos;
    String nome;
    private Vector listaRecordes = new Vector();

    public Recorde(int aPontos, String aNome) {
        pontos = aPontos;
        nome = aNome;
    }

    public void serializaRecorde() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(listaRecordes);
        objOS.flush();
        objOS.close();
    }

    public void insereRecorde(String pNome, int pPontos) throws Exception {
        Recorde rec = new Recorde(pPontos, pNome);
        listaRecordes.add(rec);
        serializaRecorde();
    }

    public void desserializaRecorde() throws Exception {
        File objFile = new File(arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            listaRecordes = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    public String printOrdenado() throws Exception {
        desserializaRecorde();
        String result = "";
        Recorde objRecorde = null;
        for (int intIdx = 0; intIdx < listaRecordes.size(); intIdx++) {
            objRecorde = (Recorde) listaRecordes.elementAt(intIdx);
            result += imprimeRecorde(objRecorde, intIdx);
        }
        if (result.equalsIgnoreCase("")) {
            return "Não existem recordes cadastrados.";
        } else {
            return result;
        }
    }

    public String imprimeRecorde(Recorde rec, int i) {
        return (i + 1) + " - Nome :" + rec.getNome()
                + " -  Pontos : " + rec.getPontos()
                + "\n";
    }

    public void bubbleSort(Vector recordes) {
        Recorde objRecorde, objRecorde2 = null, objRecorde3;
        for (int i = recordes.size(); i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                objRecorde = (Recorde) listaRecordes.elementAt(j - 1);
                objRecorde2 = (Recorde) listaRecordes.elementAt(j);
                if (objRecorde.getPontos() < objRecorde2.getPontos()) {
                    objRecorde3 = objRecorde2;
                    listaRecordes.add(j, objRecorde);
                    listaRecordes.add(j - 1, objRecorde3);
                }
            }
        }
    }

    public int getPontos() {
        return pontos;
    }

    public String getNome() {
        return nome;
    }

    public Vector getListaRecordes() {
        return listaRecordes;
    }

}
