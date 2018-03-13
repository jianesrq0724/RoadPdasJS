package com.ecar.epark.greendaolib;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ecar.epark.greendaolib.engine.UserEngine;
//import com.ecar.epark.greendaolib.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
//        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("com.ecar.epark.greendaolib.test", appContext.getPackageName());

//        UserEngine userEngine = new UserEngine();
//        userEngine.setupDataBase(appContext);
////        User user = new User(null,"name1",1,4,5,6);
////        userEngine.insert(user);
//        List<User> all = userEngine.findAll();
    }
}
