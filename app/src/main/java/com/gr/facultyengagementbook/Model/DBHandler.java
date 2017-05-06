package com.gr.facultyengagementbook.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by gaurav on 10/4/17.
 */

public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FacultyEngagementBook";
    public static final String FACULTIES_TABLE = "Faculties";
    public static final String RECORDS_TABLE = "Records";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FACULTY_NAME = "Faculty_Name";
    public static final String COLUMN_FACULTY_MOBILE_NO = "Faculty_Mobile_No";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_ENGAGED_FACULTY_MOBILE_NO = "Engaged_Faculty_Mobile_No";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_BRANCH = "Branch";
    public static final String COLUMN_SEMESTER = "Semester";
    public static final String COLUMN_ENGAGED_FACULTY_NAME = "Engaged_Faculty_Name";
    public static final String COLUMN_PERIOD = "Period";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query1 = "CREATE TABLE " + FACULTIES_TABLE + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FACULTY_NAME + " TEXT, " + COLUMN_FACULTY_MOBILE_NO + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD
                + " TEXT );";

        db.execSQL(query1);

        String query2 = "CREATE TABLE " + RECORDS_TABLE + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FACULTY_MOBILE_NO + " TEXT, " + COLUMN_ENGAGED_FACULTY_MOBILE_NO +
                " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_FACULTY_NAME + " TEXT, " + COLUMN_BRANCH + " TEXT, " +  COLUMN_SEMESTER + " TEXT, "
                +COLUMN_PERIOD + " INTEGER, "+ COLUMN_ENGAGED_FACULTY_NAME + " TEXT );";

        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + FACULTIES_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + RECORDS_TABLE + ";");
        onCreate(db);
    }

    public long addNewFaculty(FacultyDetails facultyDetails){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FACULTY_NAME,facultyDetails.getName());
        contentValues.put(COLUMN_FACULTY_MOBILE_NO,facultyDetails.getMobileNo());
        contentValues.put(COLUMN_EMAIL,facultyDetails.getEmail());
        contentValues.put(COLUMN_PASSWORD,facultyDetails.getPassword());

        return db.insert(FACULTIES_TABLE,null,contentValues);
    }

    public boolean checkAvailability(String mobileNo){

        SQLiteDatabase db = getReadableDatabase();
        String query = "Select * FROM " + FACULTIES_TABLE + " WHERE " + COLUMN_FACULTY_MOBILE_NO + " = '" + mobileNo + "';";
        Cursor cursor = null;
        cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
            return false;
        else
            return true;
    }

    public boolean checkAuthentication(String mobileNo, String password){

        SQLiteDatabase db = getReadableDatabase();
        String query = "Select * FROM " + FACULTIES_TABLE + " WHERE " + COLUMN_FACULTY_MOBILE_NO + " = '" + mobileNo + "' AND "
                + COLUMN_PASSWORD + " ='" + password + "';";
        Cursor cursor = null;
        cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
            return true;
        else
            return false;
    }

    public long addNewRecord(RecordDetails recordDetails){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FACULTY_MOBILE_NO,recordDetails.getFacultyMobileNo());
        contentValues.put(COLUMN_ENGAGED_FACULTY_MOBILE_NO,recordDetails.getEngagedFacultyMobileNo());
        contentValues.put(COLUMN_DATE,recordDetails.getDate());
        contentValues.put(COLUMN_FACULTY_NAME,recordDetails.getFacultyName());
        contentValues.put(COLUMN_BRANCH,recordDetails.getBranch());
        contentValues.put(COLUMN_SEMESTER,recordDetails.getSemester());
        contentValues.put(COLUMN_PERIOD,recordDetails.getPeriod());
        contentValues.put(COLUMN_ENGAGED_FACULTY_NAME,recordDetails.getEngagedFacultyName());

        return db.insert(RECORDS_TABLE,null,contentValues);
    }

    public boolean isRecordPresent(String mobileNo, String date, int period){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + RECORDS_TABLE + " WHERE " + COLUMN_FACULTY_MOBILE_NO + " = '" +mobileNo + "' AND "
                + COLUMN_DATE + " = '" + date + "' AND " + COLUMN_PERIOD + " = " + period + ";";
        Cursor cursor = null;
        cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        else{
            cursor.close();
            return false;
        }
    }

    public long updateExistingRecord(String mobileNo, String date, int period, RecordDetails recordDetails){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FACULTY_MOBILE_NO,recordDetails.getFacultyMobileNo());
        contentValues.put(COLUMN_ENGAGED_FACULTY_MOBILE_NO,recordDetails.getEngagedFacultyMobileNo());
        contentValues.put(COLUMN_DATE,recordDetails.getDate());
        contentValues.put(COLUMN_FACULTY_NAME,recordDetails.getFacultyName());
        contentValues.put(COLUMN_BRANCH,recordDetails.getBranch());
        contentValues.put(COLUMN_SEMESTER,recordDetails.getSemester());
        contentValues.put(COLUMN_PERIOD,recordDetails.getPeriod());
        contentValues.put(COLUMN_ENGAGED_FACULTY_NAME,recordDetails.getEngagedFacultyName());

        return db.update(RECORDS_TABLE,contentValues,COLUMN_FACULTY_MOBILE_NO + " ='" + mobileNo + "' AND " +
                COLUMN_DATE + " ='" + date + "' AND " + COLUMN_PERIOD + " = " + period,null);
    }

    public void createNewFacultyTable(String day, String mobileNo){

        SQLiteDatabase db = getWritableDatabase();
        String query = "CREATE TABLE " + day + "_" + mobileNo + " ( " + COLUMN_PERIOD + " INTEGER, " + COLUMN_BRANCH + " TEXT, "
                + COLUMN_SEMESTER + " TEXT );";
        db.execSQL(query);
    }

    public ArrayList<TimeTableDetails> getTimeTable(String day, String mobileNo){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        ArrayList<TimeTableDetails> timeTableDetailses = new ArrayList<TimeTableDetails>();
        TimeTableDetails timeTableDetails;
        String query = "SELECT * FROM " + day + "_" + mobileNo + ";";
        cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            timeTableDetails = new TimeTableDetails(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            timeTableDetailses.add(timeTableDetails);
        }
        cursor.close();
        return timeTableDetailses;
    }

    public ArrayList<FacultyRecordDetails> getFacultyRecords(String mobileNo){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + RECORDS_TABLE + " WHERE " + COLUMN_FACULTY_MOBILE_NO + " = '" + mobileNo + "';";
        Cursor cursor = null;
        ArrayList<FacultyRecordDetails> facultyRecordDetailses = new ArrayList<FacultyRecordDetails>();
        FacultyRecordDetails facultyRecordDetails;
        cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            facultyRecordDetails = new FacultyRecordDetails(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PERIOD)),cursor.getString(cursor.getColumnIndex(COLUMN_ENGAGED_FACULTY_NAME)));
            facultyRecordDetailses.add(facultyRecordDetails);
        }
        cursor.close();
        return facultyRecordDetailses;
    }

    public ArrayList<RecordDetails> getAllRecordsByMobileNo(String mobileNo){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + RECORDS_TABLE + " WHERE " + COLUMN_FACULTY_MOBILE_NO + " = '" + mobileNo + "';";
        Cursor cursor = null;
        ArrayList<RecordDetails> recordDetailses = new ArrayList<RecordDetails>();
        RecordDetails recordDetails;
        cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            recordDetails = new RecordDetails(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),
                    cursor.getString(6),cursor.getInt(7),cursor.getString(8));
            recordDetailses.add(recordDetails);
        }
        cursor.close();
        return recordDetailses;
    }

    public ArrayList<RecordDetails> getAllRecordsByMonth(String month, String year){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + RECORDS_TABLE + " WHERE " + COLUMN_DATE + " LIKE '%" + month + "-" + year + "';";
        Cursor cursor = null;
        ArrayList<RecordDetails> recordDetailses = new ArrayList<RecordDetails>();
        RecordDetails recordDetails;
        cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            recordDetails = new RecordDetails(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),
                    cursor.getString(6),cursor.getInt(7),cursor.getString(8));
            recordDetailses.add(recordDetails);
        }
        cursor.close();
        return recordDetailses;
    }

    public ArrayList<RecordDetails> getAllRecordsByDate(String day, String month, String year){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + RECORDS_TABLE + " WHERE " + COLUMN_DATE + " = '" + day + "-" + month + "-" + year + "';";
        Cursor cursor = null;
        ArrayList<RecordDetails> recordDetailses = new ArrayList<RecordDetails>();
        RecordDetails recordDetails;
        cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            recordDetails = new RecordDetails(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),
                    cursor.getString(6),cursor.getInt(7),cursor.getString(8));
            recordDetailses.add(recordDetails);
        }
        cursor.close();
        return recordDetailses;
    }

    public String getEngagedFacultyName(String date, String mobileNo, int period){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        String name;
        String query = "SELECT " + COLUMN_ENGAGED_FACULTY_NAME + " FROM " + RECORDS_TABLE + " WHERE " + COLUMN_DATE + " ='" + date +
                "' AND " + COLUMN_FACULTY_MOBILE_NO + " = '" + mobileNo + "' AND " + COLUMN_PERIOD + " =" + period + ";";
        cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            name = cursor.getString(0);
            cursor.close();
            return name;
        }
        else {
            cursor.close();
            return "No Faculty";
        }
    }

    public ArrayList<FacultyDetails> getAllFacultyDetails(String mobileNo){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        ArrayList<FacultyDetails> facultyDetailses = new ArrayList<FacultyDetails>();
        FacultyDetails facultyDetails;
        String query = "SELECT * FROM " + FACULTIES_TABLE + " WHERE " + COLUMN_FACULTY_MOBILE_NO + " <> '" + mobileNo + "';";
        cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            facultyDetails =new FacultyDetails(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            facultyDetailses.add(facultyDetails);
        }
        cursor.close();
        return facultyDetailses;
    }

    public FacultyDetails getFacultyDetails(String mobileNo){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        FacultyDetails facultyDetails;
        String query = "SELECT * FROM " + FACULTIES_TABLE + " WHERE " + COLUMN_FACULTY_MOBILE_NO + " = '" + mobileNo + "';";
        cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        facultyDetails =new FacultyDetails(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        cursor.close();
        return facultyDetails;
    }

    public long addTimetable(String day, String mobileNo, TimeTableDetails timeTableDetails){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PERIOD,timeTableDetails.getPeriod());
        contentValues.put(COLUMN_BRANCH,timeTableDetails.getBranch());
        contentValues.put(COLUMN_SEMESTER,timeTableDetails.getSemester());

        return db.insert(day + "_" + mobileNo,null,contentValues);
    }

    public long editTimetable(String day, String mobileNo, TimeTableDetails timeTableDetails){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PERIOD,timeTableDetails.getPeriod());
        contentValues.put(COLUMN_BRANCH,timeTableDetails.getBranch());
        contentValues.put(COLUMN_SEMESTER,timeTableDetails.getSemester());

        return db.update(day + "_" + mobileNo,contentValues,COLUMN_PERIOD + " = '" + timeTableDetails.getPeriod() + "'",null);
    }

    public long deleteTimetable(String day, String mobileNo, int period){

        SQLiteDatabase db = getWritableDatabase();
        return (db.delete(day+"_"+mobileNo,COLUMN_PERIOD+"="+period,null));
    }

    public void rearrangeLectures(String day, String mobileNo, int period){

        SQLiteDatabase db = getWritableDatabase();
        long lastPeriod = DatabaseUtils.longForQuery(getReadableDatabase(), "SELECT COUNT(" + COLUMN_PERIOD + ") FROM " + day + "_" + mobileNo + ";", null);
        while (period<=lastPeriod){
            db.execSQL("UPDATE " + day + "_" + mobileNo + " SET " + COLUMN_PERIOD + " = " + period + " WHERE " + COLUMN_PERIOD
                    + " = " + (period+1) + ";");
            period++;
        }
    }

    public ArrayList<AvailableFacultyDetails> getAvailableFaculties(String day, String mobileNo, int period){

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<AvailableFacultyDetails> availableFacultyDetailses = new ArrayList<AvailableFacultyDetails>();
        ArrayList<FacultyDetails>facultyDetailses = new ArrayList<FacultyDetails>();
        facultyDetailses = getAllFacultyDetails(mobileNo);
        Cursor cursor = null;
        String query;
        for(int i = 0; i < facultyDetailses.size(); i++){
            query = "SELECT * FROM " + day + "_" + facultyDetailses.get(i).getMobileNo() + " WHERE " + COLUMN_PERIOD +
                    " = " + period + " AND " + COLUMN_BRANCH + " = 'FREE';";
            cursor = db.rawQuery(query,null);
            if(cursor.moveToFirst())
                availableFacultyDetailses.add(new AvailableFacultyDetails(facultyDetailses.get(i).getName(),facultyDetailses.get(i).getMobileNo()));
        }
        return availableFacultyDetailses;
    }

    public String getFacultyName(String mobileNo){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + COLUMN_FACULTY_NAME + " FROM " + FACULTIES_TABLE + " WHERE " + COLUMN_FACULTY_MOBILE_NO +
                " = '" + mobileNo + "';";
        Cursor cursor = null;
        cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        String name = cursor.getString(0);
        cursor.close();
        return name;
    }
}
