package th.su.finaltest.chanya1999.speedrecords.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


@Entity(tableName = "record")

public class Record {

    @PrimaryKey(autoGenerate = true)
    public final int id;

    @ColumnInfo(name = "distance")
    public final double distance;

    @ColumnInfo(name = "duration")
    public final Double duration;

    public Record(int id, double distance, double duration) {
        this.id = id;
        this.distance = distance;
        this.duration = duration;
    }
}
