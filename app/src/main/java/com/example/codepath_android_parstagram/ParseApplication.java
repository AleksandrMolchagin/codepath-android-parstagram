package com.example.codepath_android_parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("mTi1oF7Gu3uvM1RA1VyH0HCFhfHmGP0hteg7UcRd")
                .clientKey("p66YhpyECtoF4aPUOYhZWFeznhtFRFk9ZRC1lCs5")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}

