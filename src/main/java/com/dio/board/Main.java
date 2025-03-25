package com.dio.board;

import java.sql.SQLException;
import com.dio.board.ui.MainMenu;

public class Main 
{
    public static void main( String[] args ) throws SQLException
    {
       new MainMenu().execute();
    }
}
