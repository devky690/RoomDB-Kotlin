package edu.towson.cosc435.abrahams.roomdb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//exportSchema false, we don't need version history at this moment
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase(){

    //handles queries
    abstract fun userDao(): UserDao

    //every non-abstract class that implements will only have one instance inside class
    companion object{
        //volatile - writes to this field are immediately made available
        //to other threads
        @Volatile
        private var INSTANCE: UserDatabase? = null

        //pass in the application we are using along with database
        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE

            //already exists, and so return
            if(tempInstance != null){
                return tempInstance;
            }
            //create new instance, having it synchronized protects the UserDatabase
            //from concurrency issues like critical section, and reader writer
            //problems
            synchronized(lock = this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return INSTANCE!!
            }
        }
    }

}