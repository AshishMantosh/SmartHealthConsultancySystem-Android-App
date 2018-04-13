package com.example.amb.shcs;

// This class handles all the database activities
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.widget.Toast;


public class MyUserDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "usersDB.db";

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "userName";
    public static final String COLUMN_EMAIL = "userEmail";
    public static final String COLUMN_PASSWORD = "userPassword";
    public static final String COLUMN_TYPE = "userType";

    public static final String TABLE_MEDICALFILE = "userMedicalFile";
    public static final String COLUMN_ID1 = "_id";
    public static final String COLUMN_NAME = "userName";
    public static final String COLUMN_DOB = "userDOB";
    public static final String COLUMN_SEX = "userSex";
    public static final String COLUMN_ADDRESS = "userAddress";
    public static final String COLUMN_CONTACT = "userContact";
    public static final String COLUMN_EMERGENCYCONTACTNAME = "userEmergencyContactName";
    public static final String COLUMN_EMERGENCYCONTACT = "userEmergencyContact";
    public static final String COLUMN_PREVIOUSAILMENTS = "userPreviousAilments";

    public static final String TABLE_DOCREG = "doctorRegFile";
    public static final String COLUMN_ID2 = "_id";
    public static final String COLUMN_NAME_DOC = "doctorName";
    public static final String COLUMN_DOB_DOC = "doctorDOB";
    public static final String COLUMN_SEX_DOC = "doctorSex";
    public static final String COLUMN_ADDRESS_DOC = "doctorAddress";
    public static final String COLUMN_CONTACT_DOC = "doctorContact";
    public static final String COLUMN_SPECIALTY = "doctorSpecialty";
    public static final String COLUMN_EXPERIENCE = "doctorExperience";
    public static final String COLUMN_QUALIFICATION = "doctorQualification";

    public static final String TABLE_APPT = "appointmentFile";
    public static final String COLUMN_ID3 = "_id";
    public static final String COLUMN_USER = "patientName";
    public static final String COLUMN_DOC_NAME = "doctorName";
    public static final String COLUMN_DATE = "dateAppt";
    public static final String COLUMN_TIME = "timeAppt";

    public static final String TABLE_BILL = "billRecord";
    public static final String COLUMN_ID4 = "_id";
    public static final String COLUMN_PATIENT_NAME = "user";
    public static final String COLUMN_AMOUNT = "chargedAmount";
    public static final String COLUMN_ISSUED_DATE = "dateIssued";
    public static final String COLUMN_INS_COVERED = "ifCovered";

    public static final String TABLE_INS = "insuranceRecord";
    public static final String COLUMN_ID5 = "_id";
    public static final String COLUMN_USER_NAME = "patientName";
    public static final String COLUMN_POLICY = "insPolicy";
    public static final String COLUMN_STATUS = "insStatus";


    //We need to pass database information along to superclass
    public MyUserDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_TYPE + " TEXT " +
                ");";

        sqLiteDatabase.execSQL(query);

        String query1 = "CREATE TABLE " + TABLE_MEDICALFILE + "(" +
                COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DOB + " TEXT, " +
                COLUMN_SEX + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_CONTACT + " TEXT, " +
                COLUMN_EMERGENCYCONTACTNAME + " TEXT, " +
                COLUMN_EMERGENCYCONTACT + " TEXT, " +
                COLUMN_PREVIOUSAILMENTS + " TEXT, " +
                " FOREIGN KEY ("+COLUMN_NAME+") REFERENCES "+ TABLE_USERS +"("+COLUMN_ID+")" +
                ");";

        sqLiteDatabase.execSQL(query1);

        String query2 = "CREATE TABLE " + TABLE_DOCREG + "(" +
                COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_NAME_DOC + " TEXT, " +
                COLUMN_DOB_DOC + " TEXT, " +
                COLUMN_SEX_DOC + " TEXT, " +
                COLUMN_ADDRESS_DOC + " TEXT, " +
                COLUMN_CONTACT_DOC + " TEXT, " +
                COLUMN_SPECIALTY + " TEXT, " +
                COLUMN_EXPERIENCE + " TEXT, " +
                COLUMN_QUALIFICATION + " TEXT " +
                ");";

        sqLiteDatabase.execSQL(query2);

        String query3 = "CREATE TABLE " + TABLE_APPT + "(" +
                COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_USER + " TEXT, " +
                COLUMN_DOC_NAME + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT " +
                ");";

        sqLiteDatabase.execSQL(query3);

        String query4 = "CREATE TABLE " + TABLE_BILL + "(" +
                COLUMN_ID4 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_PATIENT_NAME + " TEXT, " +
                COLUMN_AMOUNT + " TEXT, " +
                COLUMN_ISSUED_DATE + " TEXT, " +
                COLUMN_INS_COVERED + " TEXT " +
                ");";

        sqLiteDatabase.execSQL(query4);

        String query5 = "CREATE TABLE " + TABLE_INS + "(" +
                COLUMN_ID5 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_POLICY + " TEXT, " +
                COLUMN_STATUS + " TEXT " +
                ");";

        sqLiteDatabase.execSQL(query5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICALFILE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCREG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_APPT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BILL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INS);
        onCreate(sqLiteDatabase);
    }

    //Add a new row to the User Record
    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.get_name());
        values.put(COLUMN_EMAIL, user.get_email());
        values.put(COLUMN_PASSWORD, user.get_password());
        values.put(COLUMN_TYPE, user.get_type());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    //Add a new row to the Medical File Record
    public void addMedicalFile(String name, String dob, String sex, String address, String contact, String emergencyContactName, String emergencyContact, String previousAilments){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_SEX, sex);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_CONTACT, contact);
        values.put(COLUMN_EMERGENCYCONTACTNAME, emergencyContactName);
        values.put(COLUMN_EMERGENCYCONTACT, emergencyContact);
        values.put(COLUMN_PREVIOUSAILMENTS, previousAilments);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_MEDICALFILE, null, values);
        db.close();
    }

    //Add a new row to the Doctors Record
    public void addDocReg(String name, String dob, String sex, String address, String contact, String specialty, String experience, String qualifications){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_DOC, name);
        values.put(COLUMN_DOB_DOC, dob);
        values.put(COLUMN_SEX_DOC, sex);
        values.put(COLUMN_ADDRESS_DOC, address);
        values.put(COLUMN_CONTACT_DOC, contact);
        values.put(COLUMN_SPECIALTY, specialty);
        values.put(COLUMN_EXPERIENCE, experience);
        values.put(COLUMN_QUALIFICATION, qualifications);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_DOCREG, null, values);
        db.close();
    }

    //Add a new row to the Appointment Record
    public void addAppt(String userName, String doctorName, String date, String time){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, userName);
        values.put(COLUMN_DOC_NAME, doctorName);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_APPT, null, values);
        db.close();
    }

    //Add a new row to the Appointment Record
    public void addUserIns(String userName, String policy, String status){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, userName);
        values.put(COLUMN_POLICY, policy);
        values.put(COLUMN_STATUS, status);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_INS, null, values);
        db.close();
    }

    // Check if User exists in the Database for Log-in
    public boolean checkUser(String email, String password){

        // array of columns to fetch
        String[] columns = { COLUMN_ID };
        // selection arguments
        String[] selectionArgs = {email, password};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USERS,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    // To know which type of User(i.e Patient/Doctor) has logged-in
    public String checkUserType(String email, String password){

        // array of columns to fetch
        String[] columns = { COLUMN_TYPE };
        // selection arguments
        String[] selectionArgs = {email, password};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USERS,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        cursor.moveToFirst();

        String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));

        return type;

    }

    // To find Dcotor for appointment-booking
    public String findDoctor(String type){

        // array of columns to fetch
        String[] columns = { COLUMN_NAME_DOC };
        // selection arguments
        String[] selectionArgs = {type};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_SPECIALTY + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_DOCREG,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

//        cursor.moveToFirst();

        String ret;

        if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
            //cursor is empty
            ret = "No Doctors Available";
        }
        else
        {
            ret = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DOC));
        }

        return ret;

    }

    // To get the username of the specific logged-in patient
    public String getUserName(String email, String password){

        // array of columns to fetch
        String[] columns = { COLUMN_USERNAME };
        // selection arguments
        String[] selectionArgs = {email, password};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USERS,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        cursor.moveToFirst();

        String type = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));

        return type;

    }

    // This is the Users Record
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_USERS ;

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("userType")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("userName")) + " " +
                            recordSet.getString(recordSet.getColumnIndex("userEmail")) + " " +
                            recordSet.getString(recordSet.getColumnIndex("userPassword"));

                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

    // This is the Doctors Record
    public String doctorsDatabase(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_DOCREG ;

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("doctorQualification")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("doctorName")) + " " +
                        recordSet.getString(recordSet.getColumnIndex("doctorSpecialty")) ;

                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

    // To print out the Patients Table
    public String patientsDatabase(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_MEDICALFILE ;

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("userPreviousAilments")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("userName")) + " " +
                        recordSet.getString(recordSet.getColumnIndex("userEmergencyContactName")) ;

                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

    // To print out the Appointments
    public String appointmentDatabase(String userName){
        String dbString = "";

        // array of columns to fetch
        String[] columns = { COLUMN_DOC_NAME, COLUMN_DATE, COLUMN_TIME };
        // selection arguments
        String[] selectionArgs = {userName};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_APPT,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        cursor.moveToFirst();

        //Position after the last row means the end of the results
        while (!cursor.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (cursor.getString(cursor.getColumnIndex("timeAppt")) != null) {
                dbString += "Doctor - " + cursor.getString(cursor.getColumnIndex("doctorName")) + "\n " +
                            "Date - " + cursor.getString(cursor.getColumnIndex("dateAppt")) + "\n " +
                            "Time - " + cursor.getString(cursor.getColumnIndex("timeAppt")) + "\n" ;

                dbString += "\n";
            }
            cursor.moveToNext();
        }
        db.close();
        return dbString;
    }

    // To count to the number of existing Appointments in the Appointment Table
    public int getCountApptDatabase(String userName){
        int ret;

        // array of columns to fetch
        String[] columns = { COLUMN_DOC_NAME, COLUMN_DATE, COLUMN_TIME };
        // selection arguments
        String[] selectionArgs = {userName};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_APPT,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        ret = cursor.getCount();

        return ret;
    }

    // To print out each Appointment
    public String printApptDBperLine(String userName, int index){
        String dbString = "";

        // array of columns to fetch
        String[] columns = { COLUMN_DOC_NAME, COLUMN_DATE, COLUMN_TIME };
        // selection arguments
        String[] selectionArgs = {userName};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_APPT,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        cursor.moveToFirst();

        cursor.move(index);

        // null could happen if we used our empty constructor
        dbString += "Doctor - " + cursor.getString(cursor.getColumnIndex("doctorName")) + "\n" +
                    "Date - " + cursor.getString(cursor.getColumnIndex("dateAppt")) + "\n" +
                    "Time - " + cursor.getString(cursor.getColumnIndex("timeAppt")) + "\n" ;


        db.close();
        return dbString;
    }

    // Delete a row(i.e Appointment) from the Appointment table
    public void deleteAppt(String userName, String doctorName, String date, String time){

        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        db.delete(TABLE_APPT,
                COLUMN_USER + " = ? AND " + COLUMN_DOC_NAME + " = ? AND " + COLUMN_DATE + " = ? AND " + COLUMN_TIME + " = ?",
                new String[] {userName, doctorName, date, time});

        db.setTransactionSuccessful();
        db.endTransaction();

        db.close();
    }

    // To know which type of User(i.e Patient/Doctor) has logged-in
    public int findUserID(String username, String password){

        // array of columns to fetch
        String[] columns = { COLUMN_ID };
        // selection arguments
        String[] selectionArgs = {username, password};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USERNAME + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USERS,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        cursor.moveToFirst();

//        String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));

        return cursor.getInt(0);

    }

    // To print out each Appointment
    public void updatePassword(String strID, String userName, String email, String newPass, String checkType){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, strID);
        contentValues.put(COLUMN_USERNAME, userName);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, newPass);
        contentValues.put(COLUMN_TYPE, checkType);
        db.update(TABLE_USERS, contentValues, "_id = ?",new String[] { strID });

    }

    // To print out the Appointments
    public String printDoctorAppt(String doctorName){
        String dbString = "";

        // array of columns to fetch
        String[] columns = { COLUMN_USER, COLUMN_DATE, COLUMN_TIME };
        // selection arguments
        String[] selectionArgs = {doctorName};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_DOC_NAME + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_APPT,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        cursor.moveToFirst();

        //Position after the last row means the end of the results
        while (!cursor.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (cursor.getString(cursor.getColumnIndex("timeAppt")) != null) {
                dbString += "Patient - " + cursor.getString(cursor.getColumnIndex("patientName")) + "\n " +
                        "Date - " + cursor.getString(cursor.getColumnIndex("dateAppt")) + "\n " +
                        "Time - " + cursor.getString(cursor.getColumnIndex("timeAppt")) + "\n" ;

                dbString += "\n";
            }
            cursor.moveToNext();
        }
        db.close();
        return dbString;
    }

    // To count to the number of existing Appointments in the Appointment Table
    public int getCountBillDatabase(String userName){
        int ret;

        // array of columns to fetch
        String[] columns = { COLUMN_AMOUNT, COLUMN_ISSUED_DATE };
        // selection arguments
        String[] selectionArgs = {userName};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_PATIENT_NAME + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_BILL,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        ret = cursor.getCount();

        return ret;
    }

    // To print out each Appointment
    public String printBillDBperLine(String userName, int index){
        String dbString = "";

        // array of columns to fetch
        String[] columns = { COLUMN_AMOUNT, COLUMN_ISSUED_DATE };
        // selection arguments
        String[] selectionArgs = {userName};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_PATIENT_NAME + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_BILL,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        cursor.moveToFirst();

        cursor.move(index);

        // null could happen if we used our empty constructor
        dbString += "Doctor - " + cursor.getString(cursor.getColumnIndex("ifCovered")) + "\n" +
                "Date Issued - " + cursor.getString(cursor.getColumnIndex("dateIssued")) + "\n" +
                "Amount - " + cursor.getString(cursor.getColumnIndex("chargedAmount")) + "\n" ;


        db.close();
        return dbString;
    }

    // To print out the Appointments
    public String insuranceDB(String userName){
        String dbString = "";

        // array of columns to fetch
        String[] columns = { COLUMN_POLICY, COLUMN_STATUS };
        // selection arguments
        String[] selectionArgs = {userName};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_INS,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        cursor.moveToFirst();

        //Position after the last row means the end of the results
        while (!cursor.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (cursor.getString(cursor.getColumnIndex("insStatus")) != null) {
                dbString += "Insurance Policy - " + cursor.getString(cursor.getColumnIndex("insPolicy")) + "\n " +
                        "Status - " + cursor.getString(cursor.getColumnIndex("insStatus")) + "\n";


                dbString += "\n";
            }
            cursor.moveToNext();
        }
        db.close();
        return dbString;
    }

    // Check if User exists in the Database for Log-in
    public String checkUserIns(String username){

        // array of columns to fetch
        String[] columns = { COLUMN_STATUS };
        // selection arguments
        String[] selectionArgs = {username};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_INS,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        cursor.moveToFirst();

        String type = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS));

        return type;
    }

    // To get User(i.e Patient) ID from Insurance table
    public int findUserInsID(String username){

        // array of columns to fetch
        String[] columns = { COLUMN_ID5 };
        // selection arguments
        String[] selectionArgs = {username};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_NAME + " = ?";

        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_INS,    //Table to query
                columns,                            //columns to return
                selection,                          //columns for the WHERE clause
                selectionArgs,                      //The values for the WHERE clause
                null,                       //group the rows
                null,                        //filter by row groups
                null);

        cursor.moveToFirst();

//        String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));

        return cursor.getInt(0);

    }

    // To update Insurance database
    public void updateInsurance(String strID, String userName, String policy, String status){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID5, strID);
        contentValues.put(COLUMN_USER_NAME, userName);
        contentValues.put(COLUMN_POLICY, policy);
        contentValues.put(COLUMN_STATUS, status);

        db.update(TABLE_INS, contentValues, "_id = ?",new String[] { strID });

    }
}
