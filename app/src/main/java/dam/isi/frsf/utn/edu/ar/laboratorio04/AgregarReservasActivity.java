package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class AgregarReservasActivity extends AppCompatActivity implements View.OnClickListener{
    private Intent intentDeptoAReservar,intentListarReservasActivity;
    private Departamento deptoAReservar;
    private TextView tvNombreDepto,tvCantHabitaciones,tvCantCamas,tvCiudadDepartamento,tvPrecio;
    private DatePicker dpFechaInicio,dpFechaFin;
    private Button bConfirmar,bCancelar;
    private Reserva nuevaReserva;
    private List<Reserva> listaReservas;
    private DecimalFormat df = new DecimalFormat("#.##");
    private Date fechaInicio,fechaFin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_reservas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        intentDeptoAReservar = getIntent();
        deptoAReservar = (Departamento) intentDeptoAReservar.getExtras().get("nuevaReserva");
        cargarVariables();
        setearDatosDepartamento();
        bCancelar.setOnClickListener(this);
        bConfirmar.setOnClickListener(this);
    }
    private void cargarVariables()
    {
        tvNombreDepto = (TextView) findViewById(R.id.tvNombreDepto);
        tvCantCamas = (TextView) findViewById(R.id.tvCantCamas);
        tvCantHabitaciones = (TextView) findViewById(R.id.tvCantHabitaciones);
        tvCiudadDepartamento = (TextView) findViewById(R.id.tvCiudadDepartamento);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        dpFechaInicio = (DatePicker) findViewById(R.id.dpFechaInicio);
        dpFechaFin = (DatePicker) findViewById(R.id.dpFechaFin);

        bCancelar = (Button) findViewById(R.id.bCancelar);
        bConfirmar = (Button) findViewById(R.id.bConfirmar);

        listaReservas = new ArrayList<Reserva>();
    }
    private void setearDatosDepartamento()
    {
        tvNombreDepto.setText("Numero de departamento: "+deptoAReservar.getDescripcion());
        tvCantCamas.setText("Cantidad de camas: "+deptoAReservar.getCantidadCamas().toString());
        tvCantHabitaciones.setText("Cantidad de habitaciones: "+deptoAReservar.getCantidadHabitaciones().toString());
        tvCiudadDepartamento.setText("Ciudad: "+deptoAReservar.getCiudad().getNombre());
        tvPrecio.setText("Precio: $" + df.format( deptoAReservar.getPrecio() ));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bConfirmar:
            {
                generarReserva();
                intentListarReservasActivity = new Intent(this, ListarReservasActivity.class);
                //intentListarReservasActivity.putIntegerArrayListExtra("listaReservas",(ArrayList) listaReservas);
                intentListarReservasActivity.putExtra("mostrar_toast", true);
                startActivity(intentListarReservasActivity);
                break;
            }
            case R.id.bCancelar:
            {
                setResult(RESULT_CANCELED);
                finish();
                break;
            }
            default: break;
        }
    }
    private void generarReserva()
    {
        fechaInicio = new Date(dpFechaInicio.getYear() - 1900, dpFechaInicio.getMonth(),dpFechaInicio.getDayOfMonth());
        fechaFin = new Date(dpFechaFin.getYear() - 1900, dpFechaFin.getMonth(),dpFechaFin.getDayOfMonth());
        nuevaReserva = new Reserva();
        nuevaReserva.setPrecio(deptoAReservar.getPrecio());
        nuevaReserva.setAlojamiento(deptoAReservar);
        nuevaReserva.setConfirmada(false);
        nuevaReserva.setFechaInicio(fechaInicio);
        nuevaReserva.setFechaFin(fechaFin);

        //listaReservas.add(nuevaReserva);
        ListarReservasActivity.listaReservas.add(nuevaReserva);

        // TODO TERMINAR DE CARGAR LA RESERVA CON LA LOGICA CORRESPONDIENTE

        // TODO ASEGURAR QUE LA FECHA DE FIN ES MAYOR A LA DE INICIO
    }
}
