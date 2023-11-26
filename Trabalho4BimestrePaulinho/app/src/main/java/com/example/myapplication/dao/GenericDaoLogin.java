package com.example.myapplication.dao;

import java.util.ArrayList;

public interface GenericDaoLogin <Object>{
    long insert(Object obj);
    long update(Object obj);
    long delete(Object obj);
    ArrayList<Object> getAll();
    Object getById(int id);
}
