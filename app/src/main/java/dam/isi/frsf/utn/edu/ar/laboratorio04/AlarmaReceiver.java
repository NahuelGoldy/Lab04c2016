package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Nahuel SG on 07/10/2016.
 */
public class AlarmaReceiver extends BroadcastReceiver {

    private static Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    @Override
    public void onReceive(Context context, Intent intent) {
        long tiempo = System.currentTimeMillis();
        if(tiempo % 3 == 0){
            //creamos y seteamos la notificacion
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, ListarReservasActivity.class), 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_menu_camera)
                            .setContentTitle("Reserva confirmada")
                            .setContentText("Su reserva ha sido confirmada!");
            mBuilder.setContentIntent(contentIntent);

            mBuilder.setSound(uri);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
            ListarReservasActivity.confirmarUltimaReserva();
        }
    }

    public void setUri(Uri uri_ext){
        this.uri = uri_ext;
    }

}
