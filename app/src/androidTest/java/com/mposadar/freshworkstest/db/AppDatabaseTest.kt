package com.mposadar.freshworkstest.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mposadar.freshworkstest.database.AppDatabase
import com.mposadar.freshworkstest.database.GifDao
import com.mposadar.freshworkstest.database.model.GifEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var gifDao: GifDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        gifDao = database.gifDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeToDatabase() = runBlocking {
        val gif = GifEntity(
            id = "hRT3rKT8ahEDm",
            url = "https://media3.giphy.com/media/hRT3rKT8ahEDm/giphy-downsized.gif?cid=f29570b2o4skh4zklrnyecfdobpzdlept2fejbn9msmrti3o&rid=giphy-downsized.gif&ct=g",
            title = "Staring The Walking Dead GIF"
        )
        gifDao.insertGif(gif)
        val result = gifDao.getGifs().first()
        assertThat(result[0].id, equalTo("hRT3rKT8ahEDm"))
    }

}