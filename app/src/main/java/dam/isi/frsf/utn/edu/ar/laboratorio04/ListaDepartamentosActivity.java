package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BuscarDepartamentosTask;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

public class ListaDepartamentosActivity extends AppCompatActivity implements BusquedaFinalizadaListener<Departamento> {

    private TextView tvEstadoBusqueda;
    private ListView listaAlojamientos;
    private DepartamentoAdapter departamentosAdapter;
    private List<Departamento> lista;
    private MenuInflater menuInflater;
    private Intent intentVerReservas;
    private List<Reserva> listaReservas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alojamientos);
        lista= new ArrayList<>();
        listaAlojamientos= (ListView ) findViewById(R.id.listaAlojamientos);

        tvEstadoBusqueda = (TextView) findViewById(R.id.estadoBusqueda);

        /** Menu Contextual **/
        registerForContextMenu(listaAlojamientos);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Boolean esBusqueda = intent.getExtras().getBoolean("esBusqueda");
        if(esBusqueda)
        {
            FormBusqueda fb = (FormBusqueda ) intent.getSerializableExtra("frmBusqueda");
            new BuscarDepartamentosTask(ListaDepartamentosActivity.this).execute(fb);
            tvEstadoBusqueda.setText("Buscando....");
            tvEstadoBusqueda.setVisibility(View.VISIBLE);
        }
        else
        {
            tvEstadoBusqueda.setVisibility(View.GONE);
            lista=Departamento.getAlojamientosDisponibles();
        }
        departamentosAdapter = new DepartamentoAdapter(ListaDepartamentosActivity.this,lista);
        listaAlojamientos.setAdapter(departamentosAdapter);
    }

    @Override
    public void busquedaFinalizada(List<Departamento> listaDepartamento)
    {
        tvEstadoBusqueda.setText(R.string.resultado_busqueda);
        departamentosAdapter.addAll(listaDepartamento);
        departamentosAdapter.notifyDataSetChanged();
    }

    @Override
    public void busquedaActualizada(String msg) {

        tvEstadoBusqueda.setText(" Buscando..."+msg);
    }
    /**
     * Crea el menu contextual
     * @param menu menuContextual a crear
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu,v,menuInfo);
      //  menu.setHeaderTitle(R.string.menu_reservar_tittle);
        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context_reservar,menu);
    }

    /**
     * Analiza que opcion del menu contextual se selecciono y ejecuta la accion correspondiente
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menuoption_generar_reserva:
            {
                /** Este codigo obtiene el index del departamento al cual se le hizo clik, para luego reservar sobre ese depto */
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int index = info.position;
                accionMenuGenerarReserva((Departamento) listaAlojamientos.getItemAtPosition(index));
                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    /**
     * Accion que se realiza cuando se selecciona la opcion de generar reserva en el menu contextual
     */
    private void accionMenuGenerarReserva(Departamento deptoAReservar)
    {
        intentVerReservas = new Intent(this, AgregarReservasActivity.class);
        intentVerReservas.putExtra("nuevaReserva",deptoAReservar);
        /*
        if(listaReservas != null) // Si la lista de reservas no es nula significa que el usuario ya tenia reservas hechas asi que se las mando para que las vea
        {
            intentVerReservas.putIntegerArrayListExtra("listaReservas",(ArrayList) listaReservas);
        }
        */
        startActivityForResult(intentVerReservas,1);
    }

    /**
     * Metodo que se ejecuta cuando la aplicacion vuelve de ver las reservas
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode)
        {
            case RESULT_OK: // No hubo errores
            {
                switch (requestCode)
                {
                    case 1:
                    {
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
                break;
            }
            case 2: // Se ejecuta cuando el usuario vuelve a la pantalla anterior
            {
              //  guardarListaReservas(data);
                break;
            }
            default:
            {
                break;
            }
        }
    }

    /**
     * Cuando el usuario hace back y vuelve al a lista, almaceno en memoria su lista de reservas en el caso de que vuelva a reservar
     */
    private void guardarListaReservas(Intent data)
    {
        listaReservas = (ArrayList) data.getIntegerArrayListExtra("listaReservas");
    }


}
