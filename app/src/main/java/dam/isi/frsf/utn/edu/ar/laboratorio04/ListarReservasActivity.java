package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class ListarReservasActivity extends AppCompatActivity {
    private List<Reserva> listaReservas;
    private ListView lvReservas;
    private ReservaAdapter reservaAdapter;
    private Intent intentListaDepartamentos;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_reservas);
        try
        {
            /** Aca tomo el departamento y la lista de reservas que me enviaron desde la pantalla de lista de departamentos, sobre el que se genera la reserva */
            intentListaDepartamentos = getIntent();
            listaReservas = (ArrayList) intentListaDepartamentos.getIntegerArrayListExtra("listaReservas");
            if(listaReservas == null )
            {
                listaReservas = new ArrayList<Reserva>();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        reservaAdapter = new ReservaAdapter(ListarReservasActivity.this,listaReservas);
        lvReservas= (ListView ) findViewById(R.id.lvListaReservas);
        lvReservas.setAdapter(reservaAdapter);
    }

    @Override
    public void onBackPressed()
    {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
