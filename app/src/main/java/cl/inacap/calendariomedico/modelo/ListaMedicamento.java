package cl.inacap.calendariomedico.modelo;

import java.util.ArrayList;

public class ListaMedicamento {
    private static ListaMedicamento instancia=new ListaMedicamento();
    private ArrayList<Medicamento> listaMedicamentos;

    private ListaMedicamento()
    {
        listaMedicamentos=new ArrayList<>();

    }
    public static ListaMedicamento getInstancia()
    {
        return instancia;
    }
    public void agregarMedicamento(Medicamento medicamento)
    {
        listaMedicamentos.add(medicamento);
    }
    public Medicamento getMedicamento(int id)
    {
        return listaMedicamentos.get(id);
    }
    public ArrayList<Medicamento> getListaMedicamentos()
    {
        return listaMedicamentos;
    }
    public void eliminarComprados()
    {
        for(int i=0; i<listaMedicamentos.size(); i++)
        {
            if(listaMedicamentos.get(i).isEstado()==false)
            {
                listaMedicamentos.remove(i);
                i--;
            }
        }
    }
}
