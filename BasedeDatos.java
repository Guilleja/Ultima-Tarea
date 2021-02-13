package com.proventaja.tiendit.petagram;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class BasedeDatos extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "perritos";
    public static final int DATABASE_VERSION =1;


    public static final String TAG = BasedeDatos.class.getSimpleName();
    private Context context;
    public BasedeDatos(@Nullable Context context) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ConstantesBasedeDatos.TABLE_PERROS + " (" +
                ConstantesBasedeDatos.TABLE_PERROS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ConstantesBasedeDatos.TABLE_PERROS_NOMBRE + " TEXT," +
                ConstantesBasedeDatos.TABLE_PERROS_IMAGEN + " INTEGER)");
        sqLiteDatabase.execSQL("insert into ConstantesBasedeDatos.TABLE_PERROS (nombre,imagen) values('Lucas','imagen')");


        sqLiteDatabase.execSQL("CREATE TABLE "+ConstantesBasedeDatos.TABLE_MEGUSTA_PERROS+"("+
                "TABLE_MEGUSTA_PERROS_ID INTEGER,"+
                "TABLE_MEGUSTA_PERROS_NOMBRE_ID INTEGER,"+
                "TABLE_MEGUSTA_PERRO_NUMERO_MEGUSTA INTEGER,"+
                "FOREIGN KEY (TABLE_MEGUSTA_PERROS_NOMBRE_ID)"+
                "REFERENCES "+ConstantesBasedeDatos.TABLE_PERROS+"(TABLE_PERROS_ID)"+
                ")");

       }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ ConstantesBasedeDatos.TABLE_PERROS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ ConstantesBasedeDatos.TABLE_MEGUSTA_PERROS);
        onCreate(sqLiteDatabase);
    }
    public List<Mascota> obtenertodoslosperros(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        if(mascotas == null)
            mascotas = new ArrayList<>();
        String query ="SELECT * FROM  ConstantesBasedeDatos.TABLE_PERROS";
       SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor registros = sqLiteDatabase.rawQuery(query,null);
       while (registros.moveToNext()){
           Mascota mascotaActual = new Mascota();
           mascotaActual.setId(registros.getInt(0));
      mascotaActual.setNombre(registros.getString(2));
          mascotaActual.setImagen(registros.getInt(1));
        mascotas.add(mascotaActual);
       }
       sqLiteDatabase.close();
        return mascotas;
    }
    public void insertarMascota( ContentValues contentValues){
     SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(ConstantesBasedeDatos.TABLE_PERROS,null,contentValues);

        sqLiteDatabase.close();

    }
    public void insertarLikePerros(ContentValues contentValues){
        SQLiteDatabase db =this.getWritableDatabase();
        db.insert(ConstantesBasedeDatos.TABLE_MEGUSTA_PERROS,null,contentValues);
        db.close();
    }
    public int obtenerLikePerro(Mascota mascota) {
        int like = 0;
        String query = "SELECT COUNT(" + ConstantesBasedeDatos.TABLE_MEGUSTA_PERRO_NUMERO_MEGUSTA + ")" +
                "FROM " + ConstantesBasedeDatos.TABLE_MEGUSTA_PERROS +
                "WHERE " + ConstantesBasedeDatos.TABLE_MEGUSTA_PERROS_NOMBRE_ID + "=" + mascota.getId();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor registros = sqLiteDatabase.rawQuery(query, null);

        if (registros.moveToNext()){
            like = registros.getInt(0);
    }
        return like;
    }

}
