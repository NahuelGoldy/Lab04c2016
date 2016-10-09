package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Nahuel SG on 07/10/2016.
 */
public class AlarmaReceiver extends BroadcastReceiver {

    private SharedPreferences preferencias;

    @Override
    public void onReceive(Context context, Intent intent) {
        long tiempo = System.currentTimeMillis();
        if(tiempo % 3 == 0){
            //creamos y seteamos el intent al tocar la notificacion
            Intent intentReservas = new Intent(context, ListarReservasActivity.class);
            intentReservas.putExtra("mostrar_toast", false);

            //creamos y seteamos la notificacion
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intentReservas, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_menu_camera)
                            .setContentTitle("Reserva confirmada")
                            .setContentText("Su reserva ha sido confirmada!");
            mBuilder.setContentIntent(contentIntent);

            //Obtener las preferencias para leer el ringtone seleccionado y setearselo a la alarma
            preferencias = PreferenceManager.getDefaultSharedPreferences(context);
            String strRingtonePreference = preferencias.getString("notifications_alarm_ringtone", "DEFAULT_SOUND");
            mBuilder.setSound(Uri.parse(strRingtonePreference));

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());

            //Se confirma la ultima reserva (ELEGIDO ARBITRARIAMENTE; TODO cambiar con logica de negocio adecuada)
            ListarReservasActivity.confirmarUltimaReserva();
        }
    }

}
