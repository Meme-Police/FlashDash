package com.example.drivetest;

import java.util.ArrayList;
import java.util.List;

public class Purpose
{
    List<String> purpose = new ArrayList<>();

    Purpose()
    {
        purpose.add("What is my purpose?");
        purpose.add("You pass butter.");
        purpose.add("Oh my god.");
    }

    public void whatIsMyPurpose()
    {
        for (int i = 0; i < purpose.size(); i++)
        {
            System.out.println(purpose.get(i));
        }
    }
}
