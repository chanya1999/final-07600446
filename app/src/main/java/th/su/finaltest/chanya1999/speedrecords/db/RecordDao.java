package th.su.finaltest.chanya1999.speedrecords.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import th.su.finaltest.chanya1999.speedrecords.model.Record;


@Dao
public interface RecordDao {

    @Query("SELECT * FROM record")
    Record[] getAllRecord();

    @Query("SELECT * FROM record WHERE id = :id")
    Record getRecordById(int id);

    @Insert
    void addRecord(Record... users);

    @Delete
    void deleteRecord(Record user);
}
