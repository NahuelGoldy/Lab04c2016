package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class ListarReservasActivity extends AppCompatActivity {
    public static ArrayList<Reserva> listaReservas = new ArrayList<>(), listAux;
    private ListView lvReservas;
    private PendingIntent pendingIntent;
    private AlarmManager am;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_reservas);
        lvReservas = (ListView) findViewById(R.id.lvListaReservas);
    }


    @Override
    public void onStart() {
        super.onStart();
        listAux = new ArrayList<>();
        listAux.addAll(listaReservas);
        ReservaAdapter reservaAdapter = new ReservaAdapter(this, listAux);
        lvReservas.setAdapter(reservaAdapter);
        //se muestra un toast corto
        Toast toast = Toast.makeText(getApplicationContext(), "Se ha agregado una reserva a la lista", Toast.LENGTH_LONG);
        toast.show();
        //se crea y setea la alarma cada 15 segundos
        Intent alarmIntent = new Intent(ListarReservasActivity.this, AlarmaReceiver.class);
        am = (AlarmManager) ListarReservasActivity.this.getSystemService(Context.ALARM_SERVICE);
        pendingIntent = PendingIntent.getBroadcast(ListarReservasActivity.this, 0, alarmIntent, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, pendingIntent);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        //cuando el usuario apreta atrás, cancelo la alarma
        am.cancel(pendingIntent);
        super.onBackPressed();
    }

    public static void confirmarUltimaReserva(){
        if(listaReservas.size()>0){
            listaReservas.get(listaReservas.size()-1).setConfirmada(true);
        }
    }
}
