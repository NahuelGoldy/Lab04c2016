package dam.isi.frsf.utn.edu.ar.laboratorio04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Reserva;

public class ReservaAdapter extends ArrayAdapter<Reserva> {
    private LayoutInflater inflater;
    private Context contexto;
    private List<Reserva> listaReservas;
    private TextView tvCiudad,tvfechaInicio,tvFechaFin,tvPrecio,tvUsuario,tvDepartamento_nombre;
    private CheckBox cbReservaConfirmada;
    public ReservaAdapter(Context contexto, List<Reserva> items)
    {
        super(contexto, R.layout.fila_reserva, items);
        inflater = LayoutInflater.from(contexto);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DecimalFormat df = new DecimalFormat("#.##");
        View row = convertView;
        if (row == null)
        {
            row = inflater.inflate(R.layout.fila_reserva, parent, false);
        }
        cargarVariables(row);
/*

        TextView txtPrecio = (TextView) row.findViewById(R.id.precio);
        txtPrecio.setText("$" + (df.format(this.getItem(position).getPrecio())));
*/
        return (row);
    }

    /**
     * Inicializa los elementos de una fila
     */
    private void cargarVariables(View row)
    {
        tvPrecio = (TextView) row.findViewById(R.id.precio_reserva);
        tvCiudad = (TextView) row.findViewById(R.id.ciudad_reserva);
        tvfechaInicio = (TextView) row.findViewById(R.id.fechaInicio_reserva);
        tvFechaFin = (TextView) row.findViewById(R.id.fechaFin_reserva);
        tvDepartamento_nombre = (TextView) row.findViewById(R.id.departamento_nombre_reserva);
        tvUsuario = (TextView) row.findViewById(R.id.usuario_reserva);
        cbReservaConfirmada = (CheckBox) row.findViewById(R.id.checkBox_reserva_confirmada);
    }
}
